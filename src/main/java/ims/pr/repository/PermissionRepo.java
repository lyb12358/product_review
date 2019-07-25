package ims.pr.repository;

import ims.pr.pojo.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author lyb
 * @create 2019-04-08 16:19
 */

public interface PermissionRepo extends JpaRepository<Permission, Integer> {
    List<Permission> findByParentId(Integer id);
}
