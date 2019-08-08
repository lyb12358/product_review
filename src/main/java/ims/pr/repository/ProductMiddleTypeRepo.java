package ims.pr.repository;

import ims.pr.pojo.ProductMiddleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author lyb
 * @create 2019-04-08 16:19
 */

public interface ProductMiddleTypeRepo extends JpaRepository<ProductMiddleType, Integer> {
    List<ProductMiddleType> findByName(String name);


    List<ProductMiddleType> findByNameAndIdIsNot(String name, Integer id);
}
