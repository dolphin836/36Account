package dolphin.account.Request;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author dolphin
 */
@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class MemberNicknameRequest {
    /**
     * 昵称
     */
    @NotBlank(message = "昵称 nickname 不能为空")
    @Schema(description = "昵称")
    private String nickname;
}
