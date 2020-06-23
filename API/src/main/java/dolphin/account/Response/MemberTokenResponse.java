package dolphin.account.Response;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

/**
 * @author dolphin
 */
@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class MemberTokenResponse {
    /**
     * 用户 Id
     */
    private Long memberId;

    /**
     * Token
     */
    private String token;
}
