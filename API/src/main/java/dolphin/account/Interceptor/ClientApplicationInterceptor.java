package dolphin.account.Interceptor;

import dolphin.account.Constant.ApplicationConstant;
import dolphin.account.Constant.ClientConstant;
import dolphin.account.Exception.Common.BusinessException;
import dolphin.account.Exception.CommonException;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author dolphin
 */
public class ClientApplicationInterceptor implements HandlerInterceptor {
    /**
     * 请求进入 Controller 前执行
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String client      = request.getHeader("Client");
        String application = request.getHeader("Application");

        if (null == client) {
            throw new BusinessException(CommonException.ExceptionCode.HTTP_HEADER_CLIENT_IS_NULL);
        }

        if (null == application) {
            throw new BusinessException(CommonException.ExceptionCode.HTTP_HEADER_APPLICATION_IS_NULL);
        }

        if (! ClientConstant.isClient(Integer.parseInt(client))) {
            throw new BusinessException(CommonException.ExceptionCode.HTTP_HEADER_CLIENT_IS_ERROR);
        }

        if (! ApplicationConstant.isApplication(Integer.parseInt(application))) {
            throw new BusinessException(CommonException.ExceptionCode.HTTP_HEADER_APPLICATION_IS_ERROR);
        }

        return true;
    }
}
