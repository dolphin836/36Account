package dolphin.account;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

/**
 * @author dolphin
 */
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@OpenAPIDefinition(
		info = @Info(
				title = "36Account",
				version = "1.0",
				description = "用户中心"
		)
)
public class AccountApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountApplication.class, args);
	}
}
