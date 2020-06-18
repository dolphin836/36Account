package dolphin.account.Exception.Common;

import dolphin.account.Constant.ExceptionConstant;
import lombok.Getter;

/**
 * @author dolphin
 */
public class BusinessException extends RuntimeException {
    /**
     * 状态码
     */
    @Getter
    private final String code;

    /**
     * 提示消息
     */
    @Getter
    private final String message;

    public BusinessException (ExceptionCodeDefine exception) {
        super(exception.getMessage());

        this.code    = ExceptionConstant.APP_EXCEPTION_CODE + exception.getCode();
        this.message = exception.getMessage();
    }

    public BusinessException (ExceptionCodeDefine exception, String ... message) {
        super(exception.getMessage());

        this.code    = ExceptionConstant.APP_EXCEPTION_CODE + exception.getCode();
        this.message = String.format(exception.getMessage(), message);
    }
}
