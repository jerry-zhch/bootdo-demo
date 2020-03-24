package cn.ucmed.common.db.system.service.impl;

import cn.ucmed.common.db.system.entity.SysDept;
import cn.ucmed.common.db.system.entity.Tree;
import cn.ucmed.common.db.system.mapper.SysDeptMapper;
import cn.ucmed.common.db.system.service.ISysDeptService;
import cn.ucmed.common.utils.BuildTree;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @auther 郄彦腾
 * @create 2019-01-24 13:20:02
 * @describe 部门管理服务实现类
 */
@Service
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDept> implements ISysDeptService {

    @Autowired
    private SysDeptMapper sysDeptMapper;


    @Override
    public Tree<SysDept> getTree(String hospitalId) {
        List<Tree<SysDept>> trees = new ArrayList<Tree<SysDept>>();
        List<SysDept> sysDepts = sysDeptMapper.selectList(new QueryWrapper<SysDept>().eq("hospital_id", hospitalId));
        for (SysDept sysDept : sysDepts) {
            Tree<SysDept> tree = new Tree<SysDept>();
            tree.setId(sysDept.getDeptId().toString());
            tree.setParentId(sysDept.getParentId().toString());
            tree.setText(sysDept.getName());
            Map<String, Object> state = new HashMap<>(16);
            state.put("opened", true);
            tree.setState(state);
            trees.add(tree);
        }
        // 默认顶级菜单为０，根据数据库实际情况调整
        Tree<SysDept> t = BuildTree.build(trees);
        return t;
    }


    @Override
    public List<Long> listChildrenIds(Long parentId) {
        List<SysDept> SysDeptS = sysDeptMapper.selectList(new QueryWrapper<SysDept>());
        return treeMenuList(SysDeptS, parentId);
    }

    List<Long> treeMenuList(List<SysDept> menuList, long pid) {
        List<Long> childIds = new ArrayList<>();
        for (SysDept mu : menuList) {
            //遍历出父id等于参数的id，add进子节点集合
            if (mu.getParentId() == pid) {
                //递归遍历下一级
                treeMenuList(menuList, mu.getDeptId());
                childIds.add(mu.getDeptId());
            }
        }
        return childIds;
    }

}
