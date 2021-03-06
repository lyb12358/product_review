package ims.pr.repository;

import ims.pr.pojo.Company;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author lyb
 * @create 2019-04-08 16:19
 */

public interface CompanyRepo extends JpaRepository<Company, Integer> {
}
