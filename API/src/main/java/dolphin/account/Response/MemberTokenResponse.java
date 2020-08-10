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
public class MemberTokenResponse {
    /**
     * Token
     */
    @Schema(description = "Token")
    private String token;

    /**
     * 用户信息
     */
    @Schema(description = "用户信息")
    MemberResponse member;
}
