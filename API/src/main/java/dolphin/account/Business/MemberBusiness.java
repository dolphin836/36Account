package dolphin.account.Business;

import dolphin.account.Request.MemberSignUpRequest;
import dolphin.account.Response.MemberIdResponse;

/**
 * @author dolphin
 */
public interface MemberBusiness {
    /**
     * 用户注册
     * @param  request 注册信息
     * @return MemberIdResponse
     */
    MemberIdResponse memberSignUp (MemberSignUpRequest request);
}
