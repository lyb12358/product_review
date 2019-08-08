package ims.pr.repository;

import ims.pr.pojo.ViewProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author lyb
 * @create 2019-04-08 16:19
 */

public interface ViewProductRepo extends JpaRepository<ViewProduct, Integer> {
    List<ViewProduct> findByReviewSeasonId(Integer id);
}
