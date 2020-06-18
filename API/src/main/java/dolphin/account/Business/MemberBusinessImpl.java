package dolphin.account.Business;

import dolphin.account.Request.MemberSignUpRequest;
import dolphin.account.Response.MemberIdResponse;
import org.springframework.stereotype.Service;

/**
 * @author dolphin
 */
@Service
public class MemberBusinessImpl implements MemberBusiness {
    /**
     * 用户注册
     *
     * @param request 注册信息
     * @return Response
     */
    @Override
    public MemberIdResponse memberSignUp (MemberSignUpRequest request) {
        MemberIdResponse memberIdResponse = new MemberIdResponse();
        memberIdResponse.setMemberId(1L);

        return memberIdResponse;
    }
}
