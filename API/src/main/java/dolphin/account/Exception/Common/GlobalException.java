package dolphin.account.Exception.Common;

import dolphin.account.Response.Response;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author dolphin
 */
@ControllerAdvice
public class GlobalException {
    /**
     * 全局异常处理
     * @param  e 异常
     * @return Response
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    Response exception (Exception e) {
        return Response.exception(e.getMessage());
    }

    /**
     * 业务异常处理
     * @param e 异常
     * @return Response
     */
    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    Response businessException (BusinessException e) {
        return Response.exception(e);
    }

    /**
     * 参数校验异常处理
     * @param e 异常
     * @return Response
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    Response methodArgumentNotValidException (MethodArgumentNotValidException e) {
        return Response.exception(e);
    }
}
