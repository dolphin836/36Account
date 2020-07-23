package dolphin.account.Entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * @author dolphin
 */
@Data
@Entity
@Table(name = "member_content")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@DynamicInsert
@DynamicUpdate
public class MemberContent {
    /**
     * MemberId，自增
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

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
