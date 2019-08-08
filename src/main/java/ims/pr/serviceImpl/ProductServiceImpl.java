package ims.pr.serviceImpl;

import ims.pr.pojo.PriceZone;
import ims.pr.pojo.ProductMiddleType;
import ims.pr.pojo.ProductSpe;
import ims.pr.pojo.ReviewProduct;
import ims.pr.pojo.ViewProduct;
import ims.pr.pojo.form.ProductSearchForm;
import ims.pr.repository.PriceZoneRepo;
import ims.pr.repository.ProductMiddleTypeRepo;
import ims.pr.repository.ProductSpeRepo;
import ims.pr.repository.ReviewProductRepo;
import ims.pr.repository.ReviewResultRepo;
import ims.pr.repository.ReviewSeasonRepo;
import ims.pr.repository.ReviewUserRepo;
import ims.pr.repository.ViewProductRepo;
import ims.pr.service.ProductService;
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
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ReviewSeasonRepo rsRepo;
    @Autowired
    private ReviewUserRepo ruRepo;
    @Autowired
    private ReviewResultRepo rrRepo;
    @Autowired
    private ReviewProductRepo rpRepo;
    @Autowired
    private ViewProductRepo vpRepo;
    @Autowired
    private PriceZoneRepo pzRepo;
    @Autowired
    private ProductMiddleTypeRepo mtRepo;
    @Autowired
    private ProductSpeRepo sRepo;


    @Override
    public DataGridResult getProductList(ProductSearchForm form) {
        List<Sort.Order> list = new ArrayList<>();
        if (form.getSortFlag() != null && !"".equals(form.getSortFlag())) {
            if (form.getSortFlag() == 1) {
                Sort.Order order1 = new Sort.Order(Sort.Direction.DESC, "tg");
                list.add(order1);
            } else if (form.getSortFlag() == 2) {
                Sort.Order order1 = new Sort.Order(Sort.Direction.DESC, "tt");
                list.add(order1);
            } else {
                Sort.Order order1 = new Sort.Order(Sort.Direction.DESC, "dd");
                list.add(order1);
            }
        }
        Sort.Order order2 = new Sort.Order(Sort.Direction.ASC, "id");

        list.add(order2);
        Sort sort = Sort.by(list);

        Pageable pageable = PageRequest.of(form.getPage() - 1, form.getRow(), sort);
        ExampleMatcher match = ExampleMatcher.matching();
        ViewProduct prod = new ViewProduct();
        if (form.getOrderId() != null && !"".equals(form.getOrderId())) {
            prod.setOrderId(form.getOrderId());
        }
        if (form.getSpeId() != null && !"".equals(form.getSpeId())) {
            prod.setSpeId(form.getSpeId());
        }
        if (form.getMiddleTypeId() != null && !"".equals(form.getMiddleTypeId())) {
            prod.setMiddleTypeId(form.getMiddleTypeId());
        }
        if (form.getPriceZoneId() != null && !"".equals(form.getPriceZoneId())) {
            prod.setPriceZoneId(form.getPriceZoneId());
        }
        if (form.getReviewSeasonId() != null && !"".equals(form.getReviewSeasonId())) {
            prod.setReviewSeasonId(form.getReviewSeasonId());
        }
        if (form.getName() != null && !"".equals(form.getName())) {
            prod.setName(form.getName());
            match = match.withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains());
        }
        Example<ViewProduct> example = Example.of(prod, match);
        Page<ViewProduct> page = vpRepo.findAll(example, pageable);
        DataGridResult result = new DataGridResult();
        result.setRows(page.getContent());
        result.setTotal(page.getTotalElements());
        return result;
    }

    @Override
    public ViewProduct getProductById(Integer id) {
        ViewProduct vp = vpRepo.getOne(id);
        return vp;
    }

    @Override
    public List<ViewProduct> getProductByReviewSeasonId(Integer id) {
        List<ViewProduct> list = vpRepo.findByReviewSeasonId(id);
        return list;
    }

    @Override
    public ReviewProduct getProdById(Integer id) {
        ReviewProduct rp = rpRepo.getOne(id);
        return rp;
    }

    @Override
    @Transactional
    public void addProduct(ReviewProduct rp) throws NormalException {
        Integer seasonId = rp.getReviewSeasonId();
        if (null == rp.getId()) {
            List<ReviewProduct> list = rpRepo.findByNameAndReviewSeasonId(rp.getName(), seasonId);
            if (list.size() != 0) {
                throw new NormalException("同一个评审会名称不允许重复！");
            }
            List<ReviewProduct> list1 = rpRepo.findByDevCodeAndReviewSeasonId(rp.getDevCode(), seasonId);
            if (list.size() != 0) {
                throw new NormalException("同一个评审会研发编号不允许重复！");
            }
        } else {
            List<ReviewProduct> list = rpRepo.findByNameAndReviewSeasonIdAndIdIsNot(rp.getName(), seasonId, rp.getId());
            if (list.size() != 0) {
                throw new NormalException("同一个评审会名称不允许重复！");
            }
            List<ReviewProduct> list1 = rpRepo.findByDevCodeAndReviewSeasonIdAndIdIsNot(rp.getDevCode(), seasonId, rp.getId());
            if (list.size() != 0) {
                throw new NormalException("同一个评审会研发编号不允许重复！");
            }
        }
        try {
            rpRepo.save(rp);
        } catch (Exception e) {
            e.printStackTrace();
            throw new NormalException("操作失败");
        }
    }

    //options
    @Override
    public List<SelectOption> getPriceZoneOptions() {
        List<PriceZone> list = pzRepo.findAll(Sort.by("id").descending());
        List<SelectOption> list1 = new ArrayList<>();
        for (PriceZone xx : list) {
            SelectOption option = new SelectOption();
            option.setLabel(xx.getName());
            option.setValue(xx.getId());
            list1.add(option);
        }
        return list1;
    }

    @Override
    public List<PriceZone> getPriceZoneList() {
        List<PriceZone> list = pzRepo.findAll(Sort.by("id").descending());
        return list;
    }

    @Override
    public List<SelectOption> getMiddleTypeOptions() {
        List<ProductMiddleType> list = mtRepo.findAll(Sort.by("id").descending());
        List<SelectOption> list1 = new ArrayList<>();
        for (ProductMiddleType xx : list) {
            SelectOption option = new SelectOption();
            option.setLabel(xx.getName());
            option.setValue(xx.getId());
            list1.add(option);
        }
        return list1;
    }

    @Override
    public List<ProductMiddleType> getMiddleTypeList() {
        List<ProductMiddleType> list = mtRepo.findAll(Sort.by("id").descending());
        return list;
    }

    @Override
    public List<SelectOption> getSpeOptions() {
        List<ProductSpe> list = sRepo.findAll(Sort.by("id").descending());
        List<SelectOption> list1 = new ArrayList<>();
        for (ProductSpe xx : list) {
            SelectOption option = new SelectOption();
            option.setLabel(xx.getName());
            option.setValue(xx.getId());
            list1.add(option);
        }
        return list1;
    }

    @Override
    public List<ProductSpe> getSpeList() {
        List<ProductSpe> list = sRepo.findAll(Sort.by("id").descending());
        return list;
    }

    //param
    @Override
    @Transactional
    public void addPriceZone(PriceZone pz) throws NormalException {

        if (null == pz.getId()) {
            List<PriceZone> list = pzRepo.findByName(pz.getName());
            if (list.size() != 0) {
                throw new NormalException("名称不允许重复！");
            }
        } else {
            List<PriceZone> list = pzRepo.findByNameAndIdIsNot(pz.getName(), pz.getId());
            if (list.size() != 0) {
                throw new NormalException("名称不允许重复！");
            }
        }
        try {
            pzRepo.save(pz);
        } catch (Exception e) {
            e.printStackTrace();
            throw new NormalException("操作失败");
        }
    }

    @Override
    @Transactional
    public void addMiddleType(ProductMiddleType mt) throws NormalException {

        if (null == mt.getId()) {
            List<ProductMiddleType> list = mtRepo.findByName(mt.getName());
            if (list.size() != 0) {
                throw new NormalException("名称不允许重复！");
            }
        } else {
            List<ProductMiddleType> list = mtRepo.findByNameAndIdIsNot(mt.getName(), mt.getId());
            if (list.size() != 0) {
                throw new NormalException("名称不允许重复！");
            }
        }
        try {
            mtRepo.save(mt);
        } catch (Exception e) {
            e.printStackTrace();
            throw new NormalException("操作失败");
        }
    }

    @Override
    @Transactional
    public void addSpe(ProductSpe spe) throws NormalException {

        if (null == spe.getId()) {
            List<ProductSpe> list = sRepo.findByName(spe.getName());
            if (list.size() != 0) {
                throw new NormalException("名称不允许重复！");
            }
        } else {
            List<ProductSpe> list = sRepo.findByNameAndIdIsNot(spe.getName(), spe.getId());
            if (list.size() != 0) {
                throw new NormalException("名称不允许重复！");
            }
        }
        try {
            sRepo.save(spe);
        } catch (Exception e) {
            e.printStackTrace();
            throw new NormalException("操作失败");
        }
    }

    @Override
    public PriceZone getPriceZoneById(Integer id) {
        PriceZone xx = pzRepo.getOne(id);
        return xx;
    }


    @Override
    public ProductMiddleType getMiddleTypeById(Integer id) {
        ProductMiddleType xx = mtRepo.getOne(id);
        return xx;
    }


    @Override
    public ProductSpe getSpeById(Integer id) {
        ProductSpe xx = sRepo.getOne(id);
        return xx;
    }

}
