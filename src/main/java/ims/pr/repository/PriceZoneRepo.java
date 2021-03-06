package ims.pr.repository;

import ims.pr.pojo.PriceZone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author lyb
 * @create 2019-04-08 16:19
 */

public interface PriceZoneRepo extends JpaRepository<PriceZone, Integer> {
    List<PriceZone> findByName(String name);


    List<PriceZone> findByNameAndIdIsNot(String name, Integer id);
}
