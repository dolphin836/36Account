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
public class MemberIdResponse {
    /**
     * 用户 Id
     */
    @Schema(description = "MemberId")
    private Long memberId;
}
