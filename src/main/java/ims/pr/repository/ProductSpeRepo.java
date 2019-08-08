package ims.pr.repository;

import ims.pr.pojo.ProductSpe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author lyb
 * @create 2019-04-08 16:19
 */

public interface ProductSpeRepo extends JpaRepository<ProductSpe, Integer> {
    List<ProductSpe> findByName(String name);


    List<ProductSpe> findByNameAndIdIsNot(String name, Integer id);
}
