package dolphin.account.Business;

import dolphin.account.Constant.CacheConstant;
import dolphin.account.Constant.CommonConstant;
import dolphin.account.Constant.ImageContentTypeConstant;
import dolphin.account.Entity.Member;
import dolphin.account.Entity.MemberContent;
import dolphin.account.Entity.MemberSignIn;
import dolphin.account.Exception.Common.BusinessException;
import dolphin.account.Exception.CommonException;
import dolphin.account.Exception.MemberException;
import dolphin.account.Library.RedisLibrary;
import dolphin.account.Repository.MemberContentRepository;
import dolphin.account.Repository.MemberRepository;
import dolphin.account.Repository.MemberSignInRepository;
import dolphin.account.Request.MemberNicknameRequest;
import dolphin.account.Request.MemberSignUpRequest;
import dolphin.account.Response.MemberResponse;
import dolphin.account.Response.MemberTokenResponse;
import dolphin.account.Service.MemberService;
import dolphin.account.Service.MemberSignInService;
import dolphin.account.Service.OSSService;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * @author dolphin
 */
@Service
public class MemberBusinessImpl implements MemberBusiness {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberService memberService;

    @Autowired
    private RedisLibrary redis;

    @Autowired
    private OSSService ossService;

    @Autowired
    private MemberContentRepository memberContentRepository;

    @Autowired
    private MemberSignInRepository memberSignInRepository;

    @Autowired
    private MemberSignInService memberSignInService;

    /**
     * 用户注册
     *
     * @param request 注册信息
     * @return Response
     */
    @Override
    public MemberTokenResponse memberSignUp (MemberSignUpRequest request) {
        String username  = request.getUsername();
        boolean isExists = memberRepository.existsMemberByUsername(username);
        // 用户名已存在
        if (isExists) {
            throw new BusinessException(MemberException.ExceptionCode.USERNAME_REPEAT, username);
        }
        // 密码加密
        String password               = request.getPassword();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(CommonConstant.PASSWORD_HASH_LENGTH);
        String enPassword             = encoder.encode(password);
        // 设置 Member 实体
        Member member = new Member();
        member.setUsername(request.getUsername());
        member.setPassword(enPassword);
        // 设置客户端和应用
        memberService.setMemberClientAndApplication(member);

        try {
            memberRepository.save(member);
        } catch (Exception e) {
            throw new BusinessException(CommonException.ExceptionCode.DB_ERROR, e.getMessage());
        }

        Long memberId = member.getId();
        // 设置缓存
        String memberToken    = RandomStringUtils.randomAlphanumeric(CommonConstant.MEMBER_TOKEN_LENGTH);
        String cacheKey       = redis.getCompleteKey(CacheConstant.MEMBER_KEY, CacheConstant.TOKEN_KEY, memberToken);
        String cacheValue     = memberId.toString();
        Duration cacheTimeout = Duration.of(CacheConstant.TOKEN_TIMEOUT, ChronoUnit.SECONDS);

        redis.set(cacheKey, cacheValue, cacheTimeout);

        MemberTokenResponse memberTokenResponse = new MemberTokenResponse();
        memberTokenResponse.setToken(memberToken);
        //
        MemberResponse memberResponse = memberService.getMemberResponse(memberId);
        memberTokenResponse.setMember(memberResponse);

        return memberTokenResponse;
    }

    /**
     * 用户登录
     * @param  request 登录信息
     * @return MemberIdResponse
     */
    @Override
    public MemberTokenResponse memberSignIn(MemberSignUpRequest request) {
        String username = request.getUsername();
        Member member   = memberRepository.findByUsername(username);
        // 用户不存在
        if (null == member) {
            throw new BusinessException(MemberException.ExceptionCode.USERNAME_NOT_EXIST, username);
        }
        // MemberId
        Long memberId = member.getId();
        // 密码验证
        String enPassword             = member.getPassword();
        String password               = request.getPassword();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(CommonConstant.PASSWORD_HASH_LENGTH);
        boolean isPass                = encoder.matches(password, enPassword);

        if (! isPass) {
            throw new BusinessException(MemberException.ExceptionCode.PASSWORD_ERROR);
        }
        // 设置缓存
        String memberToken    = RandomStringUtils.randomAlphanumeric(CommonConstant.MEMBER_TOKEN_LENGTH);
        String cacheKey       = redis.getCompleteKey(CacheConstant.MEMBER_KEY, CacheConstant.TOKEN_KEY, memberToken);
        String cacheValue     = memberId.toString();
        Duration cacheTimeout = Duration.of(CacheConstant.TOKEN_TIMEOUT, ChronoUnit.SECONDS);

        redis.set(cacheKey, cacheValue, cacheTimeout);

        MemberTokenResponse memberTokenResponse = new MemberTokenResponse();
        memberTokenResponse.setToken(memberToken);
        // 登录记录
        MemberSignIn memberSignIn = new MemberSignIn();
        memberSignIn.setMemberId(memberId);
        memberSignInService.setMemberClientAndApplication(memberSignIn);

        try {
            memberSignInRepository.save(memberSignIn);
        } catch (Exception e) {
            throw new BusinessException(CommonException.ExceptionCode.DB_ERROR, e.getMessage());
        }
        //
        MemberResponse memberResponse = memberService.getMemberResponse(memberId);
        memberTokenResponse.setMember(memberResponse);

        return memberTokenResponse;
    }

    /**
     * 查询用户信息
     *
     * @param memberToken 用户 Token
     * @return MemberIdResponse
     */
    @Override
    public MemberResponse getMember(String memberToken) {
        Long memberId = memberService.getMemberIdByToken(memberToken);
        // 返回用户信息
        return memberService.getMemberResponse(memberId);
    }

    /**
     * 上传用户头像
     *
     * @param file 上传文件
     * @param memberToken 用户 Token
     * @return MemberIdResponse
     */
    @Override
    public MemberResponse memberAvatar(MultipartFile file, String memberToken) {
        // 获取 MemberId
        Long memberId = memberService.getMemberIdByToken(memberToken);
        // 上传文件不存在
        if (file.isEmpty()) {
            throw new BusinessException(CommonException.ExceptionCode.FILE_UPLOAD_EMPTY);
        }
        // 上传 ContentType 校验
        String contentType = file.getContentType();

        if (! ImageContentTypeConstant.isImage(contentType)) {
            throw new BusinessException(CommonException.ExceptionCode.FILE_UPLOAD_TYPE_ERROR);
        }
        // 上传文件大小校验
        long size = file.getSize();

        if (size > CommonConstant.MEMBER_AVATAR_MAX_SIZE) {
            throw new BusinessException(CommonException.ExceptionCode.FILE_UPLOAD_SIZE_ERROR);
        }
        // 上传文件名称
        String fileName = file.getOriginalFilename();

        if (null == fileName) {
            throw new BusinessException(CommonException.ExceptionCode.FILE_UPLOAD_ERROR);
        }
        // 上传 OSS
        LocalDate now         = LocalDate.now();
        String fileExtension  = FilenameUtils.getExtension(fileName);
        String randomFileName = RandomStringUtils.randomAlphanumeric(CommonConstant.UPLOAD_FILE_RANDOM_FILE_NAME);

        String ossPathName    = String.format("%s/%s/%s/%s.%s", now.getYear(), now.getMonthValue(), now.getDayOfMonth(), randomFileName, fileExtension);

        try {
            ossService.uploadFile(CommonConstant.BUCKET_NAME, ossPathName, file.getInputStream());
        } catch (IOException exception) {
            throw new BusinessException(CommonException.ExceptionCode.FILE_UPLOAD_ERROR);
        }
        // 更新用户头像
        MemberContent memberContent = memberContentRepository.findByMemberId(memberId);

        if (null == memberContent) {
            memberContent = new MemberContent();
            memberContent.setMemberId(memberId);
        }

        memberContent.setAvatar(ossPathName);

        try {
            memberContentRepository.save(memberContent);
        } catch (Exception e) {
            throw new BusinessException(CommonException.ExceptionCode.DB_ERROR, e.getMessage());
        }
        // 返回用户信息
        return memberService.getMemberResponse(memberId);
    }

    /**
     * 更新用户昵称
     *
     * @param memberToken 用户 Token
     * @param request     Body
     * @return MemberResponse
     */
    @Override
    public MemberResponse updateMemberNickname(String memberToken, MemberNicknameRequest request) {
        // 获取 MemberId
        Long memberId = memberService.getMemberIdByToken(memberToken);
        // 更新昵称
        MemberContent memberContent = memberContentRepository.findByMemberId(memberId);

        if (null == memberContent) {
            memberContent = new MemberContent();
        }

        memberContent.setNickname(request.getNickname());

        try {
            memberContentRepository.save(memberContent);
        } catch (Exception e) {
            throw new BusinessException(CommonException.ExceptionCode.DB_ERROR, e.getMessage());
        }
        // 返回用户信息
        return memberService.getMemberResponse(memberId);
    }
}
