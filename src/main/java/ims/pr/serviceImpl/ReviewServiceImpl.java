package ims.pr.serviceImpl;

import ims.pr.pojo.ReviewProduct;
import ims.pr.pojo.ReviewResult;
import ims.pr.pojo.ReviewSeason;
import ims.pr.pojo.ReviewUser;
import ims.pr.pojo.form.ReviewSearchForm;
import ims.pr.repository.ReviewProductRepo;
import ims.pr.repository.ReviewResultRepo;
import ims.pr.repository.ReviewSeasonRepo;
import ims.pr.repository.ReviewUserRepo;
import ims.pr.service.ReviewService;
import ims.pr.utils.DataGridResult;
import ims.pr.utils.NormalException;
import ims.pr.utils.SelectOption;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewSeasonRepo rsRepo;
    @Autowired
    private ReviewResultRepo resultRepo;
    @Autowired
    private ReviewUserRepo ruRepo;
    @Autowired
    private ReviewResultRepo rrRepo;
    @Autowired
    private ReviewProductRepo rpRepo;


    @Override
    public DataGridResult getReviewSeasonList(ReviewSearchForm form) {
        Pageable pageable = PageRequest.of(form.getPage() - 1, form.getRow(), Sort.Direction.DESC, "id");
        ExampleMatcher match = ExampleMatcher.matching();
        ReviewSeason rs = new ReviewSeason();
        if (form.getStatus() != null && !"".equals(form.getStatus())) {
            rs.setStatus(form.getStatus());
        }
        if (form.getName() != null && !"".equals(form.getName())) {
            rs.setName(form.getName());
            match = match.withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains());
        }
        Example<ReviewSeason> example = Example.of(rs, match);
        Page<ReviewSeason> page = rsRepo.findAll(example, pageable);
        DataGridResult result = new DataGridResult();
        result.setRows(page.getContent());
        result.setTotal(page.getTotalElements());
        return result;
    }

    @Override
    public ReviewSeason getReviewById(Integer id) {
        ReviewSeason reviewSeason = rsRepo.getOne(id);
        return reviewSeason;
    }

    @Override
    @Transactional
    public void addReview(ReviewSeason reviewSeason) throws NormalException {

        if (null == reviewSeason.getId()) {
            List<ReviewSeason> list = rsRepo.findByName(reviewSeason.getName());
            if (list.size() != 0) {
                throw new NormalException("名称不允许重复！");
            }
        } else {
            List<ReviewSeason> list = rsRepo.findByNameAndIdIsNot(reviewSeason.getName(), reviewSeason.getId());
            if (list.size() != 0) {
                throw new NormalException("名称不允许重复！");
            }
        }
        try {
            rsRepo.save(reviewSeason);
        } catch (Exception e) {
            e.printStackTrace();
            throw new NormalException("操作失败");
        }
    }

    @Override
    @Transactional
    public void addResultComment(ReviewResult rr) throws NormalException {
        String cc = rr.getComment();
        rr.setComment(null);
        Example<ReviewResult> example = Example.of(rr);
        List<ReviewResult> list = rrRepo.findAll(example);
        ReviewResult rr1 = list.get(0);
        rr1.setComment(cc);
        try {
            rrRepo.save(rr1);
        } catch (Exception e) {
            e.printStackTrace();
            throw new NormalException("操作失败");
        }
    }

    @Override
    public List<SelectOption> getReviewSeasonOptions() {
        List<ReviewSeason> list = rsRepo.findAll(Sort.by("id").descending());
        List<SelectOption> list1 = new ArrayList<>();
        for (ReviewSeason xx : list) {
            SelectOption option = new SelectOption();
            option.setLabel(xx.getName());
            option.setValue(xx.getId());
            list1.add(option);
        }
        return list1;
    }

    //user result
    @Override
    public List<ReviewResult> getReviewResultByUserId(Integer userId) {
        List<ReviewResult> list = resultRepo.findByUserId(userId);
        return list;
    }

    @Override
    public List<ReviewResult> getReviewResultByProdId(Integer prodId) {
        List<ReviewResult> list = resultRepo.findByProdId(prodId);
        return list;

    }

    @Override
    public List<ReviewResult> getReviewResultByUserIdAndProdId(Integer userId, Integer prodId) {
        List<ReviewResult> list = resultRepo.findByUserIdAndProdId(userId, prodId);
        return list;
    }

    @Override
    public List<ReviewResult> getReviewResultBySeasonId(Integer seasonId) {
        List<ReviewResult> list = resultRepo.findBySeasonId(seasonId);
        return list;
    }

    //获取当季该用户结果
    @Override
    public List<ReviewResult> getReviewResultBySeasonIdAndUserId(Integer seasonId, Integer userId) {
        List<ReviewResult> list = resultRepo.findBySeasonIdAndUserId(seasonId, userId);
        if (list.size() == 0) {
            List<ReviewProduct> list1 = rpRepo.findByReviewSeasonId(seasonId);
            List<ReviewResult> list2 = new ArrayList<>();
            for (ReviewProduct prod : list1) {
                ReviewResult rr = new ReviewResult();
                rr.setProdId(prod.getId());
                rr.setSeasonId(seasonId);
                rr.setUserId(userId);
                rr.setStatus(4);
                list2.add(rr);
            }
            resultRepo.saveAll(list2);
            List<ReviewResult> list3 = resultRepo.findBySeasonIdAndUserId(seasonId, userId);
            return list3;
        } else {
            List<ReviewProduct> list1 = rpRepo.findByReviewSeasonId(seasonId);
            for (ReviewProduct prod : list1) {
                List<ReviewResult> list2 = resultRepo.findBySeasonIdAndUserIdAndProdId(seasonId, userId, prod.getId());
                if (list2.size() == 0) {
                    ReviewResult rr = new ReviewResult();
                    rr.setProdId(prod.getId());
                    rr.setSeasonId(seasonId);
                    rr.setUserId(userId);
                    rr.setStatus(4);
                    list.add(rr);
                }


            }
            return list;
        }

    }

    @Override
    public List<ReviewResult> getReviewResultBySeasonIdAndProdId(Integer seasonId, Integer prodId) {
        List<ReviewResult> list = resultRepo.findBySeasonIdAndProdId(seasonId, prodId);
        return list;

    }

    //保存结果
    @Override
    @Transactional
    public void addReviewResult(List<ReviewResult> list) throws NormalException {
        try {
            rrRepo.saveAll(list);
            ReviewResult rr = list.get(0);
            ReviewUser ru = ruRepo.findBySeasonIdAndUserId(rr.getSeasonId(), rr.getUserId());
            if (ru == null) {
                ReviewUser ru1 = new ReviewUser();
                ru1.setStatus(2);
                ru1.setSeasonId(rr.getSeasonId());
                ru1.setUserId(rr.getUserId());
                ruRepo.save(ru1);
            } else if (ru.getStatus() != 2) {
                ru.setStatus(2);
                ruRepo.save(ru);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new NormalException("操作失败");
        }
    }

    //保存单个结果
    @Override
    @Transactional
    public void addSingleReviewResult(ReviewResult rr) throws NormalException {
        try {
            rrRepo.save(rr);
        } catch (Exception e) {
            e.printStackTrace();
            throw new NormalException("操作失败");
        }
    }

    //获取当季该用户状态true已评
    @Override
    public Boolean getUserReviewStatus(Integer seasonId, Integer userId) {
        ReviewUser ru = ruRepo.findBySeasonIdAndUserId(seasonId, userId);
        if (ru == null || ru.getStatus() == 1) {
            return false;
        } else {
            return true;
        }
    }
}
