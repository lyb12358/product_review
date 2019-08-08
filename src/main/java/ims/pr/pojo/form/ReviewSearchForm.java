package ims.pr.pojo.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author lyb
 * @create 2019-07-25 09:17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewSearchForm implements Serializable {
    private static final long serialVersionUID = 6807412972137910331L;
    private Integer page;
    private Integer row;
    private String name;
    private Integer status;
    private Integer departId;
    private Integer comId;
    private Date gmtCreateStart;
    private Date gmtCreateEnd;
    private Date gmtModifiedStart;
    private Date gmtModifiedEnd;
}
