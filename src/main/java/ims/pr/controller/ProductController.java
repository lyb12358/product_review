package ims.pr.controller;

import ims.pr.pojo.PriceZone;
import ims.pr.pojo.ProductMiddleType;
import ims.pr.pojo.ProductSpe;
import ims.pr.pojo.ReviewProduct;
import ims.pr.pojo.ViewProduct;
import ims.pr.pojo.form.ProductSearchForm;
import ims.pr.service.ProductService;
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
public class ProductController {
    @Autowired
    public ProductService productService;

    // product manage
    // 获取产品表
    @PostMapping(value = "/products")
    public ResponseBean getProductList(@RequestBody ProductSearchForm form) {
        DataGridResult result = productService.getProductList(form);
        return new ResponseBean(20000, "success", result);
    }

    // 获取单个产品
    @RequestMapping(value = "/product/{id}", method = RequestMethod.GET)
    public ResponseBean getProductById(@PathVariable Integer id) {
        ViewProduct rp = productService.getProductById(id);
        return new ResponseBean(20000, "操作成功", rp);
    }

    // 获取所属评审会产品
    @RequestMapping(value = "/product/reviewSeasonId/{reviewSeasonId}", method = RequestMethod.GET)
    public ResponseBean getProductByReviewSeasonId(@PathVariable Integer reviewSeasonId) {
        List<ViewProduct> list = productService.getProductByReviewSeasonId(reviewSeasonId);
        return new ResponseBean(20000, "操作成功", list);
    }

    // 添加/修改产品
    @RequestMapping(value = "/product", method = RequestMethod.POST)
    public ResponseBean addProduct(@RequestBody ReviewProduct rp) {
        productService.addProduct(rp);
        return new ResponseBean(20000, "操作成功", null);
    }

    //options
    //获取options
    @RequestMapping(value = "/priceZones/options", method = RequestMethod.GET)
    public ResponseBean getPriceZoneOptions() {
        List<SelectOption> list = productService.getPriceZoneOptions();
        return new ResponseBean(20000, "操作成功", list);
    }

    @RequestMapping(value = "/priceZones", method = RequestMethod.GET)
    public ResponseBean getPriceZoneList() {
        List<PriceZone> list = productService.getPriceZoneList();
        return new ResponseBean(20000, "操作成功", list);
    }

    @RequestMapping(value = "/middleTypes/options", method = RequestMethod.GET)
    public ResponseBean getMiddleTypeOptions() {
        List<SelectOption> list = productService.getMiddleTypeOptions();
        return new ResponseBean(20000, "操作成功", list);
    }

    @RequestMapping(value = "/middleTypes", method = RequestMethod.GET)
    public ResponseBean getMiddleTypeList() {
        List<ProductMiddleType> list = productService.getMiddleTypeList();
        return new ResponseBean(20000, "操作成功", list);
    }

    @RequestMapping(value = "/spes/options", method = RequestMethod.GET)
    public ResponseBean getSpeOptions() {
        List<SelectOption> list = productService.getSpeOptions();
        return new ResponseBean(20000, "操作成功", list);
    }

    @RequestMapping(value = "/spes", method = RequestMethod.GET)
    public ResponseBean getSpeList() {
        List<ProductSpe> list = productService.getSpeList();
        return new ResponseBean(20000, "操作成功", list);
    }

    // 添加/修改priceZone
    @RequestMapping(value = "/priceZone", method = RequestMethod.POST)
    public ResponseBean addPriceZone(@RequestBody PriceZone pz) {
        productService.addPriceZone(pz);
        return new ResponseBean(20000, "操作成功", null);
    }

    //添加/修改middleType
    @RequestMapping(value = "/middleType", method = RequestMethod.POST)
    public ResponseBean addMiddleType(@RequestBody ProductMiddleType mt) {
        productService.addMiddleType(mt);
        return new ResponseBean(20000, "操作成功", null);
    }

    // 添加/修改spe
    @RequestMapping(value = "/spe", method = RequestMethod.POST)
    public ResponseBean addSpe(@RequestBody ProductSpe spe) {
        productService.addSpe(spe);
        return new ResponseBean(20000, "操作成功", null);
    }

    @RequestMapping(value = "/priceZone/{id}", method = RequestMethod.GET)
    public ResponseBean getPriceZoneById(@PathVariable Integer id) {
        PriceZone xx = productService.getPriceZoneById(id);
        return new ResponseBean(20000, "操作成功", xx);
    }

    @RequestMapping(value = "/middleType/{id}", method = RequestMethod.GET)
    public ResponseBean getMiddleTypeById(@PathVariable Integer id) {
        ProductMiddleType xx = productService.getMiddleTypeById(id);
        return new ResponseBean(20000, "操作成功", xx);
    }

    @RequestMapping(value = "/spe/{id}", method = RequestMethod.GET)
    public ResponseBean getSpeById(@PathVariable Integer id) {
        ProductSpe xx = productService.getSpeById(id);
        return new ResponseBean(20000, "操作成功", xx);
    }

}
