package ims.pr.serviceImpl;

import ims.pr.pojo.Company;
import ims.pr.repository.CompanyRepo;
import ims.pr.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private CompanyRepo companyRepo;


    @Override
    public void insert(Company org) {
        companyRepo.save(org);
    }


}
