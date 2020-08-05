package dolphin.account.Entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * @author dolphin
 */
@Data
@MappedSuperclass
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@DynamicInsert
@DynamicUpdate
public class CommonEntity {
    /**
     * 自增 Id
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    /**
     * 插入时间
     */
    @Column(updatable = false)
    @CreationTimestamp
    private Date insertTime;

    /**
     * 最后更新时间
     */
    @UpdateTimestamp
    private Date lastUpdateTime;
}
