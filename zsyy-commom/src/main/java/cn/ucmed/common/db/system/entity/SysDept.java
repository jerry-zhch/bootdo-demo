package cn.ucmed.common.db.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @auther 郄彦腾
 * @create 2019-01-24 13:20:02
 * @describe 部门管理实体类
 */
@TableName("sys_dept")
@ApiModel(value="SysDept对象", description="部门管理")
public class SysDept implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "dept_id", type = IdType.AUTO)
    private Long deptId;

    @ApiModelProperty(value = "上级部门ID，一级部门为0")
    @TableField("parent_id")
    private Long parentId;

    @ApiModelProperty(value = "部门名称")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "排序")
    @TableField("order_num")
    private Integer orderNum;

    @ApiModelProperty(value = "是否删除  -1：已删除  0：禁用 1: 正常")
    @TableField("del_flag")
    private Integer delFlag;

    @ApiModelProperty(value = "医院id")
    @TableField("hospital_id")
    private String hospitalId;


    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public String getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(String hospitalId) {
        this.hospitalId = hospitalId;
    }

    @Override
    public String toString() {
        return "SysDept{" +
                "deptId=" + deptId +
                ", parentId=" + parentId +
                ", name='" + name + '\'' +
                ", orderNum=" + orderNum +
                ", delFlag=" + delFlag +
                ", hospitalId='" + hospitalId + '\'' +
                '}';
    }
}
