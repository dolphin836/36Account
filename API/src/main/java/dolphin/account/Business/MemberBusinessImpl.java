package dolphin.account.Business;

import dolphin.account.Entity.Member;
import dolphin.account.Exception.Common.BusinessException;
import dolphin.account.Exception.CommonException;
import dolphin.account.Repository.MemberRepository;
import dolphin.account.Request.MemberSignUpRequest;
import dolphin.account.Response.MemberIdResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author dolphin
 */
@Service
public class MemberBusinessImpl implements MemberBusiness {
    @Autowired
    private MemberRepository memberRepository;

    /**
     * 用户注册
     *
     * @param request 注册信息
     * @return Response
     */
    @Override
    public MemberIdResponse memberSignUp (MemberSignUpRequest request) {
        Member member = new Member();
        member.setUsername(request.getUsername());
        member.setPassword(request.getPassword());
        member.setClient((byte) 1);
        member.setApplication((byte) 1);

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
