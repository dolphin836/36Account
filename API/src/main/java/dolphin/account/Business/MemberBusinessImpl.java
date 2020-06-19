package dolphin.account.Business;

import dolphin.account.Constant.CommonConstant;
import dolphin.account.Entity.Member;
import dolphin.account.Exception.Common.BusinessException;
import dolphin.account.Exception.CommonException;
import dolphin.account.Exception.MemberException;
import dolphin.account.Repository.MemberRepository;
import dolphin.account.Request.MemberSignUpRequest;
import dolphin.account.Response.MemberIdResponse;
import dolphin.account.Service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author dolphin
 */
@Service
public class MemberBusinessImpl implements MemberBusiness {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberService memberService;

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
}
