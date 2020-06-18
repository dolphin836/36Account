package dolphin.account.Request;

import lombok.Data;

/**
 * @author dolphin
 */
@Data
public class MemberSignUpRequest {
    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;
}
