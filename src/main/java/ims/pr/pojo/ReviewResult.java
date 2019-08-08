package ims.pr.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * Description: 评审结果表
 *
 * @lyb
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_review_result_lyb")
@DynamicInsert
@DynamicUpdate
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ReviewResult implements Serializable {
    private static final long serialVersionUID = -7604254423496766640L;
    /**
     * 唯一标识
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer userId;
    private Integer seasonId;
    private Integer prodId;
    private Integer status;
    private String comment;
    private Date gmtCreate;
    private Date gmtModified;
}
