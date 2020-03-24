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
 * @create 2019-03-06 16:13:30
 * @describe 实体类
 */
@TableName("sys_project")
@ApiModel(value="Project对象", description="")
public class Project implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "project_id", type = IdType.UUID)
    private String projectId;

    @ApiModelProperty(value = "项目名称")
    @TableField("project_name")
    private String projectName;

    @ApiModelProperty(value = "项目类型（1.平台，2.单医院）")
    @TableField("project_type")
    private String projectType;

    @ApiModelProperty(value = "项目图片")
    @TableField("img_url")
    private String imgUrl;

    @ApiModelProperty(value = "新建者")
    @TableField("createdby")
    private String createdby;

    @ApiModelProperty(value = "新建日期")
    @TableField("createdon")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createdon;

    @ApiModelProperty(value = "修改者")
    @TableField("modifiedby")
    private String modifiedby;

    @ApiModelProperty(value = "修改日期")
    @TableField("modifiedon")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date modifiedon;

    @ApiModelProperty(value = "删除状态,0未删除，1已删除")
    @TableField("deletion_state")
    @TableLogic
    private String deletionState;

    @ApiModelProperty(value = "备注")
    @TableField("description")
    private String description;

    @ApiModelProperty(value = "排序，越大越排前")
    @TableField("seq")
    private Integer seq;

    @ApiModelProperty(value = "项目等级字段")
    @TableField("project_level")
    private String projectLevel;


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

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getCreatedby() {
        return createdby;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    public Date getCreatedon() {
        return createdon;
    }

    public void setCreatedon(Date createdon) {
        this.createdon = createdon;
    }

    public String getModifiedby() {
        return modifiedby;
    }

    public void setModifiedby(String modifiedby) {
        this.modifiedby = modifiedby;
    }

    public Date getModifiedon() {
        return modifiedon;
    }

    public void setModifiedon(Date modifiedon) {
        this.modifiedon = modifiedon;
    }

    public String getDeletionState() {
        return deletionState;
    }

    public void setDeletionState(String deletionState) {
        this.deletionState = deletionState;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getProjectLevel() {
        return projectLevel;
    }

    public void setProjectLevel(String projectLevel) {
        this.projectLevel = projectLevel;
    }

    @Override
    public String toString() {
        return "Project{" +
        "projectId=" + projectId +
        ", projectName=" + projectName +
        ", projectType=" + projectType +
        ", imgUrl=" + imgUrl +
        ", createdby=" + createdby +
        ", createdon=" + createdon +
        ", modifiedby=" + modifiedby +
        ", modifiedon=" + modifiedon +
        ", deletionState=" + deletionState +
        ", description=" + description +
        ", seq=" + seq +
        ", projectLevel=" + projectLevel +
        "}";
    }
}
