package dolphin.account.Business;

import dolphin.account.Request.MemberSignUpRequest;
import dolphin.account.Response.MemberResponse;
import dolphin.account.Response.MemberTokenResponse;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author dolphin
 */
public interface MemberBusiness {
    /**
     * 用户注册
     * @param  request 注册信息
     * @return MemberIdResponse
     */
    MemberTokenResponse memberSignUp (MemberSignUpRequest request);

    /**
     * 用户登录
     * @param  request 登录信息
     * @return MemberIdResponse
     */
    MemberTokenResponse memberSignIn (MemberSignUpRequest request);

    /**
     * 查询用户信息
     * @param memberToken 用户 Token
     * @return MemberIdResponse
     */
    MemberResponse getMember (String memberToken);

    /**
     * 上传用户头像
     * @param file 上传文件
     * @param memberToken 用户 Token
     * @return MemberIdResponse
     */
    MemberResponse memberAvatar (MultipartFile file, String memberToken);
}
