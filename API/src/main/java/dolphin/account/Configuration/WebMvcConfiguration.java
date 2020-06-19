package dolphin.account.Configuration;

import dolphin.account.Interceptor.ClientApplicationInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author dolphin
 */
@Configuration
public class WebMvcConfiguration extends WebMvcConfigurationSupport {
    /**
     * 设置拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ClientApplicationInterceptor())
                .addPathPatterns("/**")
                .order(0);

        super.addInterceptors(registry);
    }
}
