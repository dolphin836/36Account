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
        USERNAME_REPEAT( ExceptionConstant.MEMBER_MODULE_CODE + "00", "用户名 %s 已经被注册"),
        /**
         * 用户名不存在
         */
        USERNAME_NOT_EXIST( ExceptionConstant.MEMBER_MODULE_CODE + "01", "用户 %s 不存在"),
        /**
         * 密码错误
         */
        PASSWORD_ERROR( ExceptionConstant.MEMBER_MODULE_CODE + "02", "密码错误"),
        /**
         * Token 不存在
         */
        TOKEN_NOT_EXIST( ExceptionConstant.MEMBER_MODULE_CODE + "03", "Token 不存在"),
        /**
         * 用户名不存在
         */
        USER_NOT_EXIST( ExceptionConstant.MEMBER_MODULE_CODE + "04", "用户不存在");

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
