package dolphin.account.Entity;

import dolphin.account.Constant.ApplicationConstant;
import dolphin.account.Constant.ClientConstant;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author dolphin
 */
@Entity
@Table(name = "member_sign_in")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
public class MemberSignIn extends CommonEntity {
    /**
     * MemberId
     */
    private Long memberId;

    /**
     * 用户注册客户端
     */
    private ClientConstant client;

    /**
     * 用户注册应用
     */
    private ApplicationConstant application;
}
