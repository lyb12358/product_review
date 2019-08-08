package ims.pr.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * Description: 评审产品视图
 *
 * @lyb
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "viw_product_lyb")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ViewProduct implements Serializable {
    private static final long serialVersionUID = -7604254423496766640L;
    /**
     * 唯一标识
     */
    @Id
    private Integer id;
    private String devCode;
    private String name;
    private Integer reviewSeasonId;
    private String reviewSeasonName;
    private Integer priceZoneId;
    private String priceZoneName;
    private Integer speId;
    private String speName;
    private String prodMat;
    private Integer middleTypeId;
    private String middleTypeName;
    private String retailPrice;
    private String supplyPrice;
    private String costPrice;
    private String numModel;
    private String prodDesc;
    private String image;
    private String thumbnail;
    private Integer status;
    private Integer comId;
    private Integer orderId;
    private Boolean isDel;
    private Date gmtCreate;
    private Date gmtModified;
    private Integer tg;
    private Integer tt;
    private Integer dd;

}
