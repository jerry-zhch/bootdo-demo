package cn.ucmed.common.db.hospital.service;

import cn.ucmed.common.db.hospital.entity.SysHospital;
import cn.ucmed.common.db.system.entity.Tree;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @auther 郄彦腾
 * @create 2019-11-29 11:38:19
 * @describe 医院管理表服务类
 */
public interface ISysHospitalService extends IService<SysHospital> {
    List<SysHospital> getUserHospitals(Long userId);

    Tree<SysHospital> getTree(Long userId);

    Tree<SysHospital> getTreeSuper();

    Tree<JSONObject> getTree();

    JSONArray getHospitalList(String type);

}
