package dolphin.account.Exception;

import dolphin.account.Constant.ExceptionConstant;
import dolphin.account.Exception.Common.ExceptionCodeDefine;
import lombok.Getter;
import lombok.Setter;

/**
 * @author dolphin
 */
public interface MemberException {
    enum ExceptionCode implements ExceptionCodeDefine {
        /**
         * 用户名重复
         */
        USERNAME_REPEAT( ExceptionConstant.MEMBER_MODULE_CODE + "00", "用户名 %s 已经被注册");

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
