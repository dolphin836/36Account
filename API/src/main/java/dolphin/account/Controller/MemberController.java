package dolphin.account.Controller;

import dolphin.account.Business.MemberBusiness;
import dolphin.account.Request.MemberSignUpRequest;
import dolphin.account.Response.MemberIdResponse;
import dolphin.account.Response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}
