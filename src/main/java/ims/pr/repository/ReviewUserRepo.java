package ims.pr.repository;

import ims.pr.pojo.ReviewUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author lyb
 * @create 2019-04-08 16:19
 */

public interface ReviewUserRepo extends JpaRepository<ReviewUser, Integer> {
    List<ReviewUser> findBySeasonId(Integer id);

    ReviewUser findBySeasonIdAndUserId(Integer sid, Integer uid);

    void deleteBySeasonIdAndUserId(Integer sid, Integer uid);


}
