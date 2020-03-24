package cn.ucmed.common.db.hospital.mapper;

import cn.ucmed.common.db.hospital.entity.SysHospital;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @auther 郄彦腾
 * @create 2019-11-29 11:38:19
 * @describe 医院管理表mapper类
 */
public interface SysHospitalMapper extends BaseMapper<SysHospital> {
    List<SysHospital> getUserHospitals(Long userId);
}
