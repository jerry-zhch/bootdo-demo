package cn.ucmed.common.db.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @auther 郄彦腾
 * @create 2019-05-09 20:13:58
 * @describe 实体类
 */
@TableName("sys_user")
@ApiModel(value="SysUser对象", description="")
public class SysUser implements Serializable {
    private static final long serialVersionUID = 1345031978057327091L;
    @ApiModelProperty(value = "用户id")
    @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;

    @ApiModelProperty(value = "用户名")
    @TableField("username")
    private String username;

    @ApiModelProperty(value = "是否为超级管理员 0 不是，1 是")
    @TableField("is_superman")
    private String isSuperman;

    @ApiModelProperty(value = "是否为项目管理员 0 不是，1 是")
    @TableField("is_project")
    private String isProject;

    @ApiModelProperty(value = "真实姓名")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "密码")
    @TableField("password")
    private String password;

    @ApiModelProperty(value = "医院id")
    @TableField("hospital_id")
    private String hospitalId;

    //医院名称
    @TableField(exist = false)
    private String hospitalName;

    @ApiModelProperty(value = "所属项目id")
    @TableField("project_id")
    private String projectId;

    //项目名称
    @TableField(exist = false)
    private String ProjectName;

    @ApiModelProperty(value = "部门id")
    @TableField("dept_id")
    private Long deptId;

    @TableField(exist = false)
    private String deptName;

    @ApiModelProperty(value = "状态 0:禁用，1:正常")
    @TableField("status")
    private Integer status;

    @ApiModelProperty(value = "创建用户id")
    @TableField("user_id_create")
    private Long userIdCreate;

    @ApiModelProperty(value = "最后一次登录的医院id")
    @TableField("last_login_hospital")
    private String lastLoginHospital;

    @ApiModelProperty(value = "创建时间")
    @TableField("created_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createdTime;

    @ApiModelProperty(value = "修改时间")
    @TableField("update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;

    //角色
    @TableField(exist = false)
    private List<Long> roleIds;

    //角色
    @TableField(exist = false)
    private Long roleId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIsSuperman() {
        return isSuperman;
    }

    public void setIsSuperman(String isSuperman) {
        this.isSuperman = isSuperman;
    }

    public String getIsProject() {
        return isProject;
    }

    public void setIsProject(String isProject) {
        this.isProject = isProject;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(String hospitalId) {
        this.hospitalId = hospitalId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getUserIdCreate() {
        return userIdCreate;
    }

    public void setUserIdCreate(Long userIdCreate) {
        this.userIdCreate = userIdCreate;
    }

    public String getLastLoginHospital() {
        return lastLoginHospital;
    }

    public void setLastLoginHospital(String lastLoginHospital) {
        this.lastLoginHospital = lastLoginHospital;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public List<Long> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<Long> roleIds) {
        this.roleIds = roleIds;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getProjectName() {
        return ProjectName;
    }

    public void setProjectName(String projectName) {
        ProjectName = projectName;
    }

    @Override
    public String toString() {
        return "SysUser{" +
        "userId=" + userId +
        ", username=" + username +
        ", isSuperman=" + isSuperman +
        ", isProject=" + isProject +
        ", name=" + name +
        ", password=" + password +
        ", hospitalId=" + hospitalId +
        ", projectId=" + projectId +
        ", deptId=" + deptId +
        ", status=" + status +
        ", userIdCreate=" + userIdCreate +
        ", lastLoginHospital=" + lastLoginHospital +
        ", createdTime=" + createdTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
