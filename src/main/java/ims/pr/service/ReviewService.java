package ims.pr.service;


import ims.pr.pojo.ReviewResult;
import ims.pr.pojo.ReviewSeason;
import ims.pr.pojo.form.ReviewSearchForm;
import ims.pr.utils.DataGridResult;
import ims.pr.utils.NormalException;
import ims.pr.utils.SelectOption;

import java.util.List;

public interface ReviewService {


    DataGridResult getReviewSeasonList(ReviewSearchForm form);

    void addReview(ReviewSeason rs) throws NormalException;

    void addResultComment(ReviewResult rr) throws NormalException;

    ReviewSeason getReviewById(Integer id);

    List<SelectOption> getReviewSeasonOptions();

    //user result
    List<ReviewResult> getReviewResultByUserIdAndProdId(Integer userId, Integer prodId);

    List<ReviewResult> getReviewResultByProdId(Integer prodId);

    List<ReviewResult> getReviewResultByUserId(Integer userId);

    List<ReviewResult> getReviewResultBySeasonId(Integer seasonId);

    List<ReviewResult> getReviewResultBySeasonIdAndUserId(Integer seasonId, Integer userId);

    List<ReviewResult> getReviewResultBySeasonIdAndProdId(Integer seasonId, Integer prodId);

    void addReviewResult(List<ReviewResult> list) throws NormalException;

    //保存单个
    void addSingleReviewResult(ReviewResult rr) throws NormalException;

    Boolean getUserReviewStatus(Integer sid, Integer uid);


}
