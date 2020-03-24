package cn.ucmed.common.db.news.entity;

import com.alibaba.fastjson.annotation.JSONType;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @auther 郄彦腾
 * @create 2019-02-26 13:38:49
 * @describe 实体类
 */
@TableName("news_notice")
@ApiModel(value="Notice对象", description="")
public class Notice implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "栏目ID")
    @TableId(value = "channel_id", type = IdType.UUID)
    private String channelId;

    @ApiModelProperty(value = "发布类型")
    @TableField("channel_type")
    private String channelType;

    @ApiModelProperty(value = "上级栏目ID")
    @TableField("parent_channel_id")
    private String parentChannelId;

    @ApiModelProperty(value = "医院ID")
    @TableField("hospital_id")
    private String hospitalId;

    @ApiModelProperty(value = "院区ID")
    @TableField("branch_id")
    private String branchId;

    @ApiModelProperty(value = "内容标题")
    @TableField("title")
    private String title;

    @ApiModelProperty(value = "外部链接")
    @TableField("external_links")
    private String externalLinks;

    @ApiModelProperty(value = "作者")
    @TableField("auther")
    private String auther;

    @ApiModelProperty(value = "发布时间")
    @TableField("release_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date releaseDate;

    @ApiModelProperty(value = "类型(1.普通 2.图文 3.焦点 4.头条 5.其他)")
    @TableField("type")
    private String type;

    @ApiModelProperty(value = "排序")
    @TableField("sort")
    private String sort;

    @ApiModelProperty(value = "附件路径")
    @TableField("attachments_path")
    private String attachmentsPath;

    @ApiModelProperty(value = "内容")
    @TableField("txt")
    private String txt;

    @ApiModelProperty(value = "标题图片路径")
    @TableField("picture_path")
    private String picturePath;

    @ApiModelProperty(value = "是否开放(0：开放  1：不开放)")
    @TableField("is_open")
    private String isOpen;

    @ApiModelProperty(value = "状态( 0:草稿  1：已发布  2：已删除 )")
    @TableField("state")
    private String state;

    @ApiModelProperty(value = "浏览次数")
    @TableField("browse_times")
    private Integer browseTimes;

    @ApiModelProperty(value = "管理员ID")
    @TableField("user_Id")
    private String userId;

    @ApiModelProperty(value = "更新时间")
    @TableField("update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;

    @ApiModelProperty(value = "预留字段1")
    @TableField("temp1")
    private String temp1;

    @ApiModelProperty(value = "预留字段2")
    @TableField("temp2")
    private String temp2;

    @ApiModelProperty(value = "预留字段3")
    @TableField("temp3")
    private String temp3;


    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getChannelType() {
        return channelType;
    }

    public void setChannelType(String channelType) {
        this.channelType = channelType;
    }

    public String getParentChannelId() {
        return parentChannelId;
    }

    public void setParentChannelId(String parentChannelId) {
        this.parentChannelId = parentChannelId;
    }

    public String getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(String hospitalId) {
        this.hospitalId = hospitalId;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getExternalLinks() {
        return externalLinks;
    }

    public void setExternalLinks(String externalLinks) {
        this.externalLinks = externalLinks;
    }

    public String getAuther() {
        return auther;
    }

    public void setAuther(String auther) {
        this.auther = auther;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAttachmentsPath() {
        return attachmentsPath;
    }

    public void setAttachmentsPath(String attachmentsPath) {
        this.attachmentsPath = attachmentsPath;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public String getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(String isOpen) {
        this.isOpen = isOpen;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getBrowseTimes() {
        return browseTimes;
    }

    public void setBrowseTimes(Integer browseTimes) {
        this.browseTimes = browseTimes;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getTemp1() {
        return temp1;
    }

    public void setTemp1(String temp1) {
        this.temp1 = temp1;
    }

    public String getTemp2() {
        return temp2;
    }

    public void setTemp2(String temp2) {
        this.temp2 = temp2;
    }

    public String getTemp3() {
        return temp3;
    }

    public void setTemp3(String temp3) {
        this.temp3 = temp3;
    }

    @Override
    public String toString() {
        return "Notice{" +
        "channelId=" + channelId +
        ", parentChannelId=" + parentChannelId +
        ", hospitalId=" + hospitalId +
        ", branchId=" + branchId +
        ", title=" + title +
        ", externalLinks=" + externalLinks +
        ", auther=" + auther +
        ", releaseDate=" + releaseDate +
        ", type=" + type +
        ", sort=" + sort +
        ", attachmentsPath=" + attachmentsPath +
        ", txt=" + txt +
        ", picturePath=" + picturePath +
        ", isOpen=" + isOpen +
        ", state=" + state +
        ", browseTimes=" + browseTimes +
        ", userId=" + userId +
        ", updateTime=" + updateTime +
        ", temp1=" + temp1 +
        ", temp2=" + temp2 +
        ", temp3=" + temp3 +
        "}";
    }
}
