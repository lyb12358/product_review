package ims.pr.serviceImpl;

import ims.pr.pojo.Company;
import ims.pr.repository.CompanyRepo;
import ims.pr.service.OrgService;
import ims.pr.utils.TreeNode;
import ims.pr.utils.TreeNodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrgServiceImpl implements OrgService {

    @Autowired
    private CompanyRepo companyRepo;


    @Override
    public void insert(Company org) {
        companyRepo.save(org);
    }


    @Override
    public List<TreeNode> getOrgList() {
        List<Company> list = companyRepo.findAll();
        List<TreeNode> nodes = toListNode(list);
        List<TreeNode> treeNodes = TreeNodeUtil.tree(nodes, 0);
        return treeNodes;
    }

    public static TreeNode toNode(Company org) {
        TreeNode node = new TreeNode();
        node.setId(org.getId());
        node.setParentId(org.getParentId());
        node.setLabel(org.getName());
        node.setStatus(org.getStatus());
        node.setDepth(org.getDepth());
        node.setIsDel(org.getIsDel());
        Map<String, Object> attributes = new HashMap<>();
        // attributes.put("isCom", org.getIsCom());
        node.setAttributes(attributes);
        return node;
    }

    public static List<TreeNode> toListNode(List<Company> orgs) {
        List<TreeNode> nodes = new ArrayList<>();
        for (Company org : orgs) {
            nodes.add(toNode(org));
        }
        return nodes;
    }

}
