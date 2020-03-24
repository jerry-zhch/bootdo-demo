package cn.ucmed.common.db.system.service;

import cn.ucmed.common.db.system.entity.SysDept;
import cn.ucmed.common.db.system.entity.Tree;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * @auther 郄彦腾
 * @create 2019-01-24 13:20:02
 * @describe 部门管理服务类
 */
public interface ISysDeptService extends IService<SysDept> {

    Tree<SysDept> getTree(String hospitalId);

    List<Long> listChildrenIds(Long parentId);
}
