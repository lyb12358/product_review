package ims.pr.controller;

import ims.pr.pojo.ReviewResult;
import ims.pr.pojo.ReviewSeason;
import ims.pr.pojo.form.ReviewSearchForm;
import ims.pr.service.ReviewService;
import ims.pr.utils.DataGridResult;
import ims.pr.utils.ResponseBean;
import ims.pr.utils.SelectOption;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class ReviewController {
    @Autowired
    public ReviewService reviewService;

    // review manage
    // 获取评审季度表
    @PostMapping(value = "/reviewSeasons")
    public ResponseBean getReviewSeasonList(@RequestBody ReviewSearchForm form) {
        DataGridResult result = reviewService.getReviewSeasonList(form);
        return new ResponseBean(20000, "success", result);
    }

    // 获取单个评审会
    @RequestMapping(value = "/review/{id}", method = RequestMethod.GET)
    public ResponseBean getReviewById(@PathVariable Integer id) {
        ReviewSeason rs = reviewService.getReviewById(id);
        return new ResponseBean(20000, "操作成功", rs);
    }

    // 添加/修改评审会
    @RequestMapping(value = "/review", method = RequestMethod.POST)
    public ResponseBean addReview(@RequestBody ReviewSeason rs) {
        reviewService.addReview(rs);
        return new ResponseBean(20000, "操作成功", null);
    }

    // 添加/修改评论
    @RequestMapping(value = "/reviewResult/comment", method = RequestMethod.POST)
    public ResponseBean addResultComment(@RequestBody ReviewResult rr) {
        reviewService.addResultComment(rr);
        return new ResponseBean(20000, "操作成功", null);
    }

    //获取options
    @RequestMapping(value = "/reviews/options", method = RequestMethod.GET)
    public ResponseBean getReviewOptions() {
        List<SelectOption> list = reviewService.getReviewSeasonOptions();
        return new ResponseBean(20000, "操作成功", list);
    }

    //user result
    @RequestMapping(value = "/reviewResult/userId/{id}", method = RequestMethod.GET)
    public ResponseBean getReviewResultByUserId(@PathVariable Integer id) {
        List<ReviewResult> list = reviewService.getReviewResultByUserId(id);
        return new ResponseBean(20000, "操作成功", list);
    }

    @RequestMapping(value = "/reviewResult/prodId/{id}", method = RequestMethod.GET)
    public ResponseBean getReviewResultByProdId(@PathVariable Integer id) {
        List<ReviewResult> list = reviewService.getReviewResultByProdId(id);
        return new ResponseBean(20000, "操作成功", list);
    }

    @RequestMapping(value = "/reviewResult/userId/{id1}/prodId/{id2}", method = RequestMethod.GET)
    public ResponseBean getReviewResultByUserIdAndProdId(@PathVariable Integer id1, @PathVariable Integer id2) {
        List<ReviewResult> list = reviewService.getReviewResultByUserIdAndProdId(id1, id2);
        return new ResponseBean(20000, "操作成功", list);
    }

    @RequestMapping(value = "/reviewResult/seasonId/{id}", method = RequestMethod.GET)
    public ResponseBean getReviewResultBySeasonId(@PathVariable Integer id) {
        List<ReviewResult> list = reviewService.getReviewResultBySeasonId(id);
        return new ResponseBean(20000, "操作成功", list);
    }

    @RequestMapping(value = "/reviewResult/seasonId/{id1}/userId/{id2}", method = RequestMethod.GET)
    public ResponseBean getReviewResultBySeasonIdAndUserId(@PathVariable Integer id1, @PathVariable Integer id2) {
        List<ReviewResult> list = reviewService.getReviewResultBySeasonIdAndUserId(id1, id2);
        return new ResponseBean(20000, "操作成功", list);
    }

    @RequestMapping(value = "/reviewResult/seasonId/{id1}/prodId/{id2}", method = RequestMethod.GET)
    public ResponseBean getReviewResultBySeasonIdAndProdId(@PathVariable Integer id1, @PathVariable Integer id2) {
        List<ReviewResult> list = reviewService.getReviewResultBySeasonIdAndProdId(id1, id2);
        return new ResponseBean(20000, "操作成功", list);
    }

    //保存结果
    @PostMapping(value = "/reviewResults")
    public ResponseBean addReviewResult(@RequestBody List<ReviewResult> list) {
        reviewService.addReviewResult(list);
        return new ResponseBean(20000, "success", null);
    }

    //保存单个结果
    @PostMapping(value = "/reviewResult")
    public ResponseBean addReviewResult(@RequestBody ReviewResult rr) {
        reviewService.addSingleReviewResult(rr);
        return new ResponseBean(20000, "success", null);
    }

    @RequestMapping(value = "/reviewResult/isReview/seasonId/{id1}/userId/{id2}", method = RequestMethod.GET)
    public ResponseBean getUserReviewStatus(@PathVariable Integer id1, @PathVariable Integer id2) {
        Boolean xx = reviewService.getUserReviewStatus(id1, id2);
        return new ResponseBean(20000, "操作成功", xx);
    }
}
