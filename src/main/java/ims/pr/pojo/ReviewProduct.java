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
import java.math.BigDecimal;
import java.util.Date;

/**
 * Description: 评审产品表
 *
 * @lyb
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_review_product_lyb")
@DynamicInsert
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ReviewProduct implements Serializable {
    private static final long serialVersionUID = -7604254423496766640L;
    /**
     * 唯一标识
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String devCode;
    private String name;
    private Integer reviewSeasonId;
    private Integer priceZoneId;
    private Integer speId;
    private String prodMat;
    private Integer middleTypeId;
    private BigDecimal retailPrice;
    private BigDecimal supplePrice;
    private BigDecimal costPrice;
    private Integer numModel;
    private String prodDesc;
    private String image;
    private String thumbnail;
    private Integer status;
    private Integer comId;
    private Integer orderId;
    private Boolean isDel;
    private Date gmtCreate;
    private Date gmtModified;
}
