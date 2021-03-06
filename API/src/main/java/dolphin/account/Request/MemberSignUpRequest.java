package dolphin.account.Request;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

/**
 * @author dolphin
 */
@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class MemberSignUpRequest {
    /**
     * 用户名
     */
    @NotBlank(message = "用户名 username 不能为空")
    @Schema(description = "用户名")
    private String username;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    @Length(min = 8, max = 50, message = "密码长度不能小于 8，大于 50")
    @Schema(description = "密码", minLength = 8, maxLength = 50)
    private String password;
}
