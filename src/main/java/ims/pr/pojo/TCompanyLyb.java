package ims.pr.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.Column;
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
public class TCompanyLyb implements Serializable {
    private static final long serialVersionUID = -5513072982392304489L;
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
    @Column
    private String name;

    /**
     *
     */
    private String orgCode;

    /**
     * 状态（1正常）
     */
    @Column
    private Integer status;

    /**
     * 级别
     */
    @Column
    private Integer depth;

    /**
     * 节点图标
     */
    @Column
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
