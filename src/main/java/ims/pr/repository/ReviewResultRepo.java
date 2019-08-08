package ims.pr.repository;

import ims.pr.pojo.ReviewResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author lyb
 * @create 2019-04-08 16:19
 */

public interface ReviewResultRepo extends JpaRepository<ReviewResult, Integer> {
    List<ReviewResult> findByUserId(Integer userId);

    List<ReviewResult> findByUserIdAndProdId(Integer userId, Integer prodId);

    List<ReviewResult> findByProdId(Integer prodId);

    List<ReviewResult> findBySeasonId(Integer seasonId);

    List<ReviewResult> findBySeasonIdAndUserId(Integer seasonId, Integer userId);

    List<ReviewResult> findBySeasonIdAndUserIdAndProdId(Integer seasonId, Integer userId, Integer prodId);

    List<ReviewResult> findBySeasonIdAndProdId(Integer seasonId, Integer prodId);


}
