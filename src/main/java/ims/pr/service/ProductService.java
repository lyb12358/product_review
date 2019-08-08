package ims.pr.service;


import ims.pr.pojo.PriceZone;
import ims.pr.pojo.ProductMiddleType;
import ims.pr.pojo.ProductSpe;
import ims.pr.pojo.ReviewProduct;
import ims.pr.pojo.ViewProduct;
import ims.pr.pojo.form.ProductSearchForm;
import ims.pr.utils.DataGridResult;
import ims.pr.utils.NormalException;
import ims.pr.utils.SelectOption;

import java.util.List;

public interface ProductService {


    DataGridResult getProductList(ProductSearchForm form);

    void addProduct(ReviewProduct rp) throws NormalException;

    ViewProduct getProductById(Integer id);

    List<ViewProduct> getProductByReviewSeasonId(Integer id);

    ReviewProduct getProdById(Integer id);

    //options
    List<SelectOption> getPriceZoneOptions();

    List<PriceZone> getPriceZoneList();

    List<SelectOption> getMiddleTypeOptions();

    List<ProductMiddleType> getMiddleTypeList();

    List<SelectOption> getSpeOptions();

    List<ProductSpe> getSpeList();

    //param
    void addPriceZone(PriceZone pz);

    void addMiddleType(ProductMiddleType mt);

    void addSpe(ProductSpe spe);

    PriceZone getPriceZoneById(Integer id);

    ProductMiddleType getMiddleTypeById(Integer id);

    ProductSpe getSpeById(Integer id);


}
