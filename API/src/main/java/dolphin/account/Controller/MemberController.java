package dolphin.account.Controller;

import dolphin.account.Business.MemberBusiness;
import dolphin.account.Request.MemberSignUpRequest;
import dolphin.account.Response.MemberIdResponse;
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
    public Response<MemberIdResponse> memberSignUp (@RequestBody @Validated MemberSignUpRequest request)
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

    @GetMapping("member")
    public Response<MemberResponse> getMember (@RequestHeader("Token") String memberToken)
    {
        return Response.success(memberBusiness.getMember(memberToken));
    }
}
