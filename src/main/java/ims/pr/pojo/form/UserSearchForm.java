package ims.pr.pojo.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author lyb
 * @create 2019-07-25 09:17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSearchForm {
    private Integer page;
    private Integer row;
    private String account;
    private String name;
    private Integer status;
    private Integer departId;
    private Integer comId;
}
