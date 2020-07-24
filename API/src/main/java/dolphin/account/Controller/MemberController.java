package dolphin.account.Controller;

import dolphin.account.Business.MemberBusiness;
import dolphin.account.Request.MemberNicknameRequest;
import dolphin.account.Request.MemberSignUpRequest;
import dolphin.account.Response.MemberResponse;
import dolphin.account.Response.MemberTokenResponse;
import dolphin.account.Response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author dolphin
 */
@RestController
public class MemberController {

    @Autowired
    private MemberBusiness memberBusiness;

    /**
     * 注册
     * @param  request 注册信息
     * @return MemberIdResponse
     */
    @PostMapping("signup")
    public Response<MemberTokenResponse> memberSignUp (@RequestBody @Validated MemberSignUpRequest request)
    {
        return Response.success(memberBusiness.memberSignUp(request));
    }

    /**
     * 登录
     * @param  request 登录信息
     * @return MemberIdResponse
     */
    @PostMapping("signin")
    public Response<MemberTokenResponse> memberSignIn (@RequestBody @Validated MemberSignUpRequest request)
    {
        return Response.success(memberBusiness.memberSignIn(request));
    }

    /**
     * 查询用户信息
     * @param memberToken Token
     * @return MemberResponse
     */
    @GetMapping("member")
    public Response<MemberResponse> getMember (@RequestHeader("Token") String memberToken)
    {
        return Response.success(memberBusiness.getMember(memberToken));
    }

    /**
     * 更新用户昵称
     * @param memberToken Token
     * @param request     Body
     * @return MemberResponse
     */
    @PostMapping("/nickname")
    public Response<MemberResponse> updateMemberNickname (@RequestHeader("Token") String memberToken, @RequestBody @Validated MemberNicknameRequest request)
    {
        return Response.success(memberBusiness.updateMemberNickname(memberToken, request));
    }
}
