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
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Description: 用户表
 *
 * @lyb
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_user_lyb")
@DynamicInsert
@DynamicUpdate
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User implements Serializable {
    private static final long serialVersionUID = -7604254423496766640L;
    /**
     * 唯一标识
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String account;
    private String password;
    private Integer status;
    private Integer comId;
    private Integer departId;
    private String avatar;
    private Integer type;
    private String theme;
    private String mobileNum;
    private String mobileMac;
    private Boolean isDel;
    private Date gmtCreate;
    private Date gmtModified;
    @Transient
    private List<Integer> permissions;
}
