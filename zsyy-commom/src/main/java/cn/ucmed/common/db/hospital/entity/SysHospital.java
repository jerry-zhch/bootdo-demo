package cn.ucmed.common.db.hospital.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @auther 郄彦腾
 * @create 2019-11-29 11:38:19
 * @describe 医院管理表实体类
 */
@TableName("sys_hospital")
@ApiModel(value="SysHospital对象", description="医院管理表")
public class SysHospital implements Serializable {

    private static final long serialVersionUID = -2007442468744315611L;
    @ApiModelProperty(value = "医院id")
    @TableId(value = "hospital_id", type = IdType.UUID)
    private String hospitalId;

    @ApiModelProperty(value = "父级医院id，第一级0")
    @TableField("parent_id")
    private String parentId;

    @ApiModelProperty(value = "所属项目id")
    @TableField("project_id")
    private String projectId;

    @ApiModelProperty(value = "项目名称")
    @TableField("project_name")
    private String projectName;

    @ApiModelProperty(value = "his医院id 不是区域医院值为1")
    @TableField("his_hospital_id")
    private String hisHospitalId;

    @ApiModelProperty(value = "医院名称")
    @TableField("hospital_name")
    private String hospitalName;

    @ApiModelProperty(value = "是否开启维护 0不开启1开启")
    @TableField("is_maintenance")
    private String isMaintenance;

    @ApiModelProperty(value = "维护提示语")
    @TableField("maintenance_notice")
    private String maintenanceNotice;

    @ApiModelProperty(value = "排序")
    @TableField("seq")
    private Integer seq;

    @ApiModelProperty(value = "0 有效，1 无效")
    @TableField("delete_state")
    private String deleteState;

    @ApiModelProperty(value = "扩展字段（电子健康卡appid appkey termId）")
    @TableField("extend")
    private String extend;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    @TableField("update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;


    public String getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(String hospitalId) {
        this.hospitalId = hospitalId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getHisHospitalId() {
        return hisHospitalId;
    }

    public void setHisHospitalId(String hisHospitalId) {
        this.hisHospitalId = hisHospitalId;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getIsMaintenance() {
        return isMaintenance;
    }

    public void setIsMaintenance(String isMaintenance) {
        this.isMaintenance = isMaintenance;
    }

    public String getMaintenanceNotice() {
        return maintenanceNotice;
    }

    public void setMaintenanceNotice(String maintenanceNotice) {
        this.maintenanceNotice = maintenanceNotice;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getDeleteState() {
        return deleteState;
    }

    public void setDeleteState(String deleteState) {
        this.deleteState = deleteState;
    }

    public String getExtend() {
        return extend;
    }

    public void setExtend(String extend) {
        this.extend = extend;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "SysHospital{" +
        "hospitalId=" + hospitalId +
        ", parentId=" + parentId +
        ", projectId=" + projectId +
        ", projectName=" + projectName +
        ", hisHospitalId=" + hisHospitalId +
        ", hospitalName=" + hospitalName +
        ", isMaintenance=" + isMaintenance +
        ", maintenanceNotice=" + maintenanceNotice +
        ", seq=" + seq +
        ", deleteState=" + deleteState +
        ", extend=" + extend +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
