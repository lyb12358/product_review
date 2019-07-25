package ims.pr.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Description: 公司明细表
 *
 * @lyb
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_company_lyb")
@DynamicInsert
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Company implements Serializable {
    private static final long serialVersionUID = -7604254423496766640L;
    /**
     * 唯一标识
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 父id
     */
    private Integer parentId;

    /**
     * 所属集团id
     */
    private Integer groupId;

    /**
     * 组织名称
     */

    private String name;

    /**
     *
     */
    private String orgCode;

    /**
     * 状态（1正常）
     */

    private Integer status;

    /**
     * 级别
     */

    private Integer depth;

    /**
     * 节点图标
     */

    private String icon;

    /**
     * 显示顺序
     */
    private Integer orderId;

    /**
     * 是否删除
     */
    private Boolean isDel;
}
