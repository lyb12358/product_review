package ims.pr.repository;

import ims.pr.pojo.ReviewProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author lyb
 * @create 2019-04-08 16:19
 */

public interface ReviewProductRepo extends JpaRepository<ReviewProduct, Integer> {
    List<ReviewProduct> findByName(String name);

    List<ReviewProduct> findByReviewSeasonId(Integer id);

    List<ReviewProduct> findByNameAndIdIsNot(String name, Integer id);

    List<ReviewProduct> findByDevCode(String devCode);


    List<ReviewProduct> findByDevCodeAndIdIsNot(String devCode, Integer id);

    //xx
    List<ReviewProduct> findByNameAndReviewSeasonId(String name, Integer seasonId);

    List<ReviewProduct> findByNameAndReviewSeasonIdAndIdIsNot(String name, Integer seasonId, Integer id);

    List<ReviewProduct> findByDevCodeAndReviewSeasonId(String devCode, Integer seasonId);


    List<ReviewProduct> findByDevCodeAndReviewSeasonIdAndIdIsNot(String devCode, Integer seasonId, Integer id);
}
