package dolphin.account.Response;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author dolphin
 */
@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class MemberResponse {
    /**
     * 用户 Id
     */
    @Schema(description = "MemberId")
    private Long memberId;

    /**
     * 用户名
     */
    @Schema(description = "用户名")
    private String username;

    /**
     * 昵称
     */
    @Schema(description = "昵称")
    private String nickname;

    /**
     * 邮箱
     */
    @Schema(description = "邮箱")
    private String mail;

    /**
     * 头像
     */
    @Schema(description = "头像")
    private String avatar;

    /**
     * 签名
     */
    @Schema(description = "签名")
    private String sign;
}
