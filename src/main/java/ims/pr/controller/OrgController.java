package ims.pr.controller;

import ims.pr.pojo.TCompanyLyb;
import ims.pr.service.OrgService;
import ims.pr.utils.ResponseBean;
import ims.pr.utils.TreeNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class OrgController {


    @Autowired
    public OrgService orgService;

    @GetMapping("/orgs")
    public ResponseBean getOrgList() {
        List<TreeNode> list = orgService.getOrgList();
        ResponseBean result = new ResponseBean(20000, "success", list);
        return result;
    }

    @GetMapping("/hello")
    public ResponseBean hello() {
        ResponseBean result = new ResponseBean(20000, "success", "hello");
        return result;
    }

    @GetMapping("/saveOrg")
    public ResponseBean saveOrg() {
        TCompanyLyb org = new TCompanyLyb();
        org.setId(44);
        org.setParentId(1);
        org.setName("测试");
        System.out.println(org.getParentId().toString());
        log.error(org.getParentId().toString());
        log.error(org.getName());
        orgService.insert(org);
        ResponseBean result = new ResponseBean(20000, "success", "save");
        return result;
    }
}
