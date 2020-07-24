package dolphin.account.Request;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
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
    private String nickname;
}
