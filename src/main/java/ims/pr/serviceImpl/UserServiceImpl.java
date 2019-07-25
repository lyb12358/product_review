package ims.pr.serviceImpl;

import ims.pr.pojo.Permission;
import ims.pr.pojo.Role;
import ims.pr.pojo.RolePermission;
import ims.pr.pojo.User;
import ims.pr.pojo.UserRole;
import ims.pr.pojo.form.UserSearchForm;
import ims.pr.repository.PermissionRepo;
import ims.pr.repository.RolePermissionRepo;
import ims.pr.repository.RoleRepo;
import ims.pr.repository.UserRepo;
import ims.pr.repository.UserRoleRepo;
import ims.pr.service.UserService;
import ims.pr.utils.DataGridResult;
import ims.pr.utils.NormalException;
import ims.pr.utils.SelectOption;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RoleRepo roleRepo;
    @Autowired
    private UserRoleRepo userRoleRepo;
    @Autowired
    private RolePermissionRepo rolePermissionRepo;
    @Autowired
    private PermissionRepo permissionRepo;


    @Override
    public Boolean login(User user) {
        String account = user.getAccount();
        String password = user.getPassword();
        String MD5Password = DigestUtils.md5DigestAsHex(password.getBytes());
        User newUser = userRepo.findByAccountAndPasswordAndStatusAndIsDel(account, MD5Password, 1, false);
        if (null == newUser) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public User getRbac(Integer id) {
        User user = userRepo.getOne(id);
        user.setPassword("");
        List<UserRole> roleList = userRoleRepo.findByUserId(id);
        List<Integer> permissions = new ArrayList<>();
        LinkedHashSet<Integer> set = new LinkedHashSet<>();
        Integer roleId = null;
        for (UserRole ur : roleList) {
            roleId = ur.getRoleId();
            List<RolePermission> rolePermissionList = rolePermissionRepo.findByRoleId(roleId);
            for (RolePermission rp : rolePermissionList) {
                set.add(rp.getPermissionId());
            }
        }
        permissions.addAll(set);
        user.setPermissions(permissions);
        return user;
    }

    @Override
    public User selectByAccount(String account) {
        User user = userRepo.findByAccount(account);
        return user;
    }

    @Override
    public DataGridResult getUserList(UserSearchForm form) {
        Pageable pageable = PageRequest.of(form.getPage(), form.getRow(), Sort.Direction.DESC, "id");
        ExampleMatcher match = ExampleMatcher.matching();
        if (form.getAccount() != null && !"".equals(form.getAccount())) {
            match.withMatcher(form.getAccount(), ExampleMatcher.GenericPropertyMatchers.contains());
        }
        if (form.getName() != null && !"".equals(form.getName())) {
            match.withMatcher(form.getName(), ExampleMatcher.GenericPropertyMatchers.contains());
        }
        User user = new User();
        Example<User> example = Example.of(user, match);
        Page<User> page = userRepo.findAll(example, pageable);
        DataGridResult result = new DataGridResult();
        result.setRows(page.getContent());
        result.setTotal(page.getTotalElements());
        return result;
    }

    // 修改密码
    @Override
    public void updatePassword(Integer id, String password) throws NormalException {
        User user = userRepo.getOne(id);
        String MD5Password = DigestUtils.md5DigestAsHex(password.getBytes());
        user.setPassword(MD5Password);
        try {
            userRepo.save(user);
        } catch (Exception e) {
            e.printStackTrace();
            throw new NormalException("修改失败");
        }

    }

    // role manage
    // 获取角色表
    @Override
    public DataGridResult getRoleList(UserSearchForm form) {
        Pageable pageable = PageRequest.of(form.getPage(), form.getRow(), Sort.Direction.DESC, "id");
        ExampleMatcher match = ExampleMatcher.matching();
        if (form.getName() != null && !"".equals(form.getName())) {
            match.withMatcher(form.getName(), ExampleMatcher.GenericPropertyMatchers.contains());
        }
        Role role = new Role();
        Example<Role> example = Example.of(role, match);
        Page<Role> page = roleRepo.findAll(example, pageable);
        DataGridResult result = new DataGridResult();
        result.setRows(page.getContent());
        result.setTotal(page.getTotalElements());
        return result;
    }

    @Override
    public void addRole(Role role) throws NormalException {
        List<Role> list = roleRepo.findByName(role.getName());
        if (list.size() != 0) {
            throw new NormalException("角色名称不允许重复！");
        }
        try {
            roleRepo.save(role);
        } catch (Exception e) {
            e.printStackTrace();
            throw new NormalException("添加失败");
        }
    }

    @Override
    public void updateRole(Role role) throws NormalException {
        List<Role> list = roleRepo.findByIdIsNotAndName(role.getId(), role.getName());
        if (list.size() != 0) {
            throw new NormalException("角色名称不允许重复！");
        }
        try {
            roleRepo.save(role);
        } catch (Exception e) {
            e.printStackTrace();
            throw new NormalException("修改失败");
        }
    }

    @Override
    public List<SelectOption> getRoleOptions() {
        List<Role> list = roleRepo.findAll();
        List<SelectOption> options = new ArrayList<>();
        for (Role role : list) {
            SelectOption option = new SelectOption();
            option.setLabel(role.getName() + "(" + role.getRemark() + ")");
            option.setValue(role.getId());
            options.add(option);
        }
        return options;

    }

    // user role
    @Override
    public List<Integer> getUserRole(Integer id) {
        List<UserRole> list = userRoleRepo.findByUserId(id);
        List<Integer> userRoleList = new ArrayList<>();
        for (UserRole userRole : list) {
            userRoleList.add(userRole.getRoleId());
        }
        return userRoleList;
    }

    @Override
    public void updateUserRole(Integer id, List<Integer> list) throws NormalException {
        try {
            userRoleRepo.deleteByUserId(id);
            List<UserRole> userRoleList = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                UserRole userRole = new UserRole();
                userRole.setUserId(id);
                userRole.setRoleId(list.get(i));
                userRoleList.add(userRole);
            }
            userRoleRepo.saveAll(userRoleList);
        } catch (Exception e) {
            e.printStackTrace();
            throw new NormalException("更新失败");
        }
    }

    // role permission
    @Override
    public List<Integer> getRolePermission(Integer id) {
        List<RolePermission> list = rolePermissionRepo.findAll();
        LinkedHashSet<Integer> set = new LinkedHashSet<>(list.size());
        for (RolePermission rolePermission : list) {
            set.add(rolePermission.getPermissionId());
        }
        List<Integer> permissionList = new ArrayList<>();
        permissionList.addAll(set);
        return permissionList;
    }

    @Override
    public void updateRolePermission(Integer id, List<Integer> list) throws NormalException {
        try {
            rolePermissionRepo.deleteByRoleId(id);
            List<RolePermission> permissionList = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                RolePermission rolePermission = new RolePermission();
                rolePermission.setRoleId(id);
                rolePermission.setPermissionId(list.get(i));
                permissionList.add(rolePermission);
            }
            rolePermissionRepo.saveAll(permissionList);
        } catch (Exception e) {
            e.printStackTrace();
            throw new NormalException("更新失败");
        }
    }

    // permission
    @Override
    public List<Integer> getStaticPermissionArrayByParent(Integer id) {
        List<Permission> list = permissionRepo.findByParentId(id);
        List<Integer> arraylist = new ArrayList<>(list.size());
        for (int i = 0; i < list.size(); i++) {
            arraylist.add(list.get(i).getId());
        }
        return arraylist;
    }

    @Override
    public List<Permission> getStaticPermissionByParent(Integer id) {
        List<Permission> list = permissionRepo.findByParentId(id);
        return list;
    }
}
