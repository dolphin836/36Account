package dolphin.account.Exception;

import dolphin.account.Constant.ExceptionConstant;
import dolphin.account.Exception.Common.ExceptionCodeDefine;
import lombok.Getter;
import lombok.Setter;

/**
 * @author dolphin
 */
public interface CommonException {
    enum ExceptionCode implements ExceptionCodeDefine {
        /**
         * 记录不存在
         */
        RECORD_NOT_EXIST( ExceptionConstant.COMMON_MODULE_CODE + "00", "记录不存在");

        /**
         * 状态码
         */
        @Getter
        @Setter
        private String code;

        /**
         * 提示消息
         */
        @Getter
        @Setter
        private String message;

        ExceptionCode (String code, String message) {
            this.code    = code;
            this.message = message;
        }
    }
}
