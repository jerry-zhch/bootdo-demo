package cn.ucmed.common.db.system.mapper;

import cn.ucmed.common.db.system.entity.SysDept;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @auther 郄彦腾
 * @create 2019-01-24 13:20:02
 * @describe 部门管理mapper类
 */
public interface SysDeptMapper extends BaseMapper<SysDept> {

    SysDept get(Long deptId);

    List<SysDept> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int saveData(SysDept dept);

    int update(SysDept dept);

    int remove(Long deptId);

    int batchRemove(Long[] deptIds);

    @Select({
            "select DISTINCT parent_id from sys_dept"
    })
    Long[] listParentDept();

    int getDeptUserNumber(Long deptId);
}
