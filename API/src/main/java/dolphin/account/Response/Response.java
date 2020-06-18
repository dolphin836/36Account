package dolphin.account.Response;

import dolphin.account.Exception.Common.BusinessException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import lombok.Data;

import java.io.Serializable;

/**
 * @author dolphin
 */
@Data
public class Response<T> implements Serializable {
    /**
     * 状态码
     */
    private String code;

    /**
     * 提示消息
     */
    private String message;

    /**
     * 数据
     */
    private T data;

    /**
     * Http 请求成功状态码
     */
    private static final String HTTP_RESPONSE_SUCCESS_CODE    = "0";

    /**
     * Http 请求成功提示消息
     */
    private static final String HTTP_RESPONSE_SUCCESS_MESSAGE = "success";

    /**
     * Http 请求服务端错误状态码
     */
    private static final String HTTP_RESPONSE_SERVER_ERROR_CODE    = "500";

    /**
     * Http 请求服务端错误提示消息
     */
    private static final String HTTP_RESPONSE_SERVER_ERROR_MESSAGE = "Server Error: ";

    /**
     * Http 请求参数校验异常状态码
     */
    private static final String HTTP_RESPONSE_ARGUMENT_VALID_ERROR_CODE = "400";

    private Response () {
        this.code    = HTTP_RESPONSE_SUCCESS_CODE;
        this.message = HTTP_RESPONSE_SUCCESS_MESSAGE;
    }

    private Response (T data) {
        this.code    = HTTP_RESPONSE_SUCCESS_CODE;
        this.message = HTTP_RESPONSE_SUCCESS_MESSAGE;
        this.data    = data;
    }

    private Response (String message) {
        this.code    = HTTP_RESPONSE_SERVER_ERROR_CODE;
        this.message = HTTP_RESPONSE_SERVER_ERROR_MESSAGE + message;
    }

    private Response (BusinessException e) {
        this.code    = e.getCode();
        this.message = e.getMessage();
    }

    private Response (MethodArgumentNotValidException e) {
        this.code    = HTTP_RESPONSE_ARGUMENT_VALID_ERROR_CODE;
        this.message = e.getBindingResult().getFieldError().getDefaultMessage();
    }

    public static <T> Response <T> success () {
        return new Response<>();
    }

    public static <T> Response <T> success (T data) {
        return new Response<T>(data);
    }

    public static <T> Response <T> exception (String message) {
        return new Response<>(message);
    }

    public static <T> Response <T> exception (BusinessException e) { return new Response<>(e); }

    public static <T> Response <T> exception (MethodArgumentNotValidException e) { return new Response<>(e); }
}
