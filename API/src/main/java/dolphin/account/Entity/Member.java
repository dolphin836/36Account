package dolphin.account.Entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author dolphin
 */
@Data
@Entity
@Table(name = "member")
public class Member {
    /**
     * MemberId，自增
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

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
    private Byte client;

    /**
     * 用户注册应用
     */
    private Byte application;
}
