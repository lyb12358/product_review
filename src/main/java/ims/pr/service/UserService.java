package ims.pr.service;


import ims.pr.pojo.Permission;
import ims.pr.pojo.Role;
import ims.pr.pojo.User;
import ims.pr.pojo.form.UserSearchForm;
import ims.pr.utils.DataGridResult;
import ims.pr.utils.NormalException;
import ims.pr.utils.SelectOption;

import java.util.List;

public interface UserService {


    // auth
    Boolean login(User user);

    User selectByAccount(String account);

    User getRbac(Integer id);

    // user manage
    // 获取用户表
    DataGridResult getUserList(UserSearchForm form);

    void addUser(User user) throws NormalException;

    User getUserById(Integer id);

    // 修改密码
    void updatePassword(Integer id, String password) throws NormalException;

    // // 重置密码
    // void resetPassword(Integer id);

    // role manage
    // 获取角色表
    DataGridResult getRoleList(UserSearchForm form);

    void addRole(Role role) throws NormalException;

    void updateRole(Role role) throws NormalException;

    List<SelectOption> getRoleOptions();

    // user role
    List<Integer> getUserRole(Integer id);

    void updateUserRole(Integer id, List<Integer> list) throws NormalException;

    // role permission
    List<Integer> getRolePermission(Integer id);

    void updateRolePermission(Integer id, List<Integer> list) throws NormalException;

    // permission
    List<Integer> getStaticPermissionArrayByParent(Integer id);

    List<Permission> getStaticPermissionByParent(Integer id);

}
