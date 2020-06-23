package dolphin.account.Business;

import dolphin.account.Constant.CacheConstant;
import dolphin.account.Constant.CommonConstant;
import dolphin.account.Entity.Member;
import dolphin.account.Exception.Common.BusinessException;
import dolphin.account.Exception.CommonException;
import dolphin.account.Exception.MemberException;
import dolphin.account.Library.RedisLibrary;
import dolphin.account.Repository.MemberRepository;
import dolphin.account.Request.MemberSignUpRequest;
import dolphin.account.Response.MemberIdResponse;
import dolphin.account.Response.MemberResponse;
import dolphin.account.Response.MemberTokenResponse;
import dolphin.account.Service.MemberService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

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

    /**
     * 用户注册
     *
     * @param request 注册信息
     * @return Response
     */
    @Override
    public MemberIdResponse memberSignUp (MemberSignUpRequest request) {
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

        MemberIdResponse memberIdResponse = new MemberIdResponse();
        memberIdResponse.setMemberId(memberId);

        return memberIdResponse;
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
        String cacheValue     = member.getId().toString();
        Duration cacheTimeout = Duration.of(CacheConstant.TOKEN_TIMEOUT, ChronoUnit.SECONDS);

        redis.set(cacheKey, cacheValue, cacheTimeout);

        MemberTokenResponse memberTokenResponse = new MemberTokenResponse();
        memberTokenResponse.setMemberId(member.getId());
        memberTokenResponse.setToken(memberToken);

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
        String cacheKey   = redis.getCompleteKey(CacheConstant.MEMBER_KEY, CacheConstant.TOKEN_KEY, memberToken);
        String cacheValue = redis.get(cacheKey);

        if (null == cacheValue) {
            throw new BusinessException(MemberException.ExceptionCode.TOKEN_NOT_EXIST);
        }

        Long memberId           = (long) Integer.parseInt(cacheValue);

        Optional<Member> member = memberRepository.findById(memberId);

        if (member.isPresent()) {
            return memberService.getMemberResponse(member.get());
        }

        throw new BusinessException(MemberException.ExceptionCode.USER_NOT_EXIST);
    }
}
