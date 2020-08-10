package dolphin.account;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
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
				description = "用户中心",
				contact = @Contact(name = "dolphin", url = "https://haibing.site")
		),
		servers = {
				@Server(url = "http://127.0.0.1:8080", description = "本地"),
				@Server(url = "https://account.haibing.site", description = "生产")
		}
)
public class AccountApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountApplication.class, args);
	}
}
