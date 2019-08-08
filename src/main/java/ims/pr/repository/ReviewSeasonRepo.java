package ims.pr.repository;

import ims.pr.pojo.ReviewSeason;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author lyb
 * @create 2019-04-08 16:19
 */

public interface ReviewSeasonRepo extends JpaRepository<ReviewSeason, Integer> {
    List<ReviewSeason> findByName(String name);


    List<ReviewSeason> findByNameAndIdIsNot(String name, Integer id);
}
