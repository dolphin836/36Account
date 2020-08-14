package dolphin.account.Entity;

import lombok.*;

import javax.persistence.*;

/**
 * @author dolphin
 */
@Entity
@Table(name = "member_content")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
public class MemberContent extends CommonEntity {
    /**
     * MemberId
     */
    private Long memberId;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 邮箱
     */
    private String mail;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 签名
     */
    private String sign;
}
