package ims.pr.repository;

import ims.pr.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author lyb
 * @create 2019-04-08 16:19
 */

public interface UserRepo extends JpaRepository<User, Integer> {
    User findByAccount(String account);

    User findByAccountAndPasswordAndStatusAndIsDel(String account, String password, Integer status, Boolean isDel);
}
