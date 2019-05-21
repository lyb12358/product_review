package ims.pr.service;


import ims.pr.pojo.TCompanyLyb;
import ims.pr.utils.TreeNode;

import java.util.List;

public interface OrgService {

    //Test
    List<TreeNode> getOrgList();

    void insert(TCompanyLyb org);


}
