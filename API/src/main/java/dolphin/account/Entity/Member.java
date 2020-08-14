package dolphin.account.Entity;

import dolphin.account.Constant.ApplicationConstant;
import dolphin.account.Constant.ClientConstant;
import lombok.*;

import javax.persistence.*;

/**
 * @author dolphin
 */
@Entity
@Table(name = "member")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
public class Member extends CommonEntity {
    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户注册客户端
     */
    private ClientConstant client;

    /**
     * 用户注册应用
     */
    private ApplicationConstant application;
}
