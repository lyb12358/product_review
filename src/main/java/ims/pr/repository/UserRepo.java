package ims.pr.repository;

import ims.pr.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author lyb
 * @create 2019-04-08 16:19
 */

public interface UserRepo extends JpaRepository<User, Integer> {
    List<User> findByName(String name);

    User findByAccount(String account);

    List<User> findByNameAndIdIsNot(String name, Integer id);

    User findByAccountAndIdIsNot(String account, Integer id);

    User findByAccountAndPasswordAndStatusAndIsDel(String account, String password, Integer status, Boolean isDel);
}
