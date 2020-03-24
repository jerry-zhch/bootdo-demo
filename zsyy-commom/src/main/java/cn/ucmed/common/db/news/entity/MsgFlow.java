package cn.ucmed.common.db.news.entity;

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
 * @create 2019-01-28 13:27:54
 * @describe 实体类
 */
@TableName("news_msg_flow")
@ApiModel(value="MsgFlow对象", description="")
public class MsgFlow implements Serializable {

    private static final long serialVersionUID = -6769866415891856999L;
    @TableId(value = "msg_flow_id", type = IdType.UUID)
    private String msgFlowId;

    @ApiModelProperty(value = "用户id")
    @TableField("user_id")
    private String userId;

    @ApiModelProperty(value = "项目编号")
    @TableField("project_id")
    private String projectId;

    @ApiModelProperty(value = "医院院区id")
    @TableField("hospital_id")
    private String hospitalId;

    @ApiModelProperty(value = "发送者")
    @TableField("sender")
    private String sender;

    @ApiModelProperty(value = "接受者")
    @TableField("recipient")
    private String recipient;

    @ApiModelProperty(value = "推送方式 0 短信，1 公众号推送， 2 微信小程序")
    @TableField("msg_type")
    private String msgType;

    @ApiModelProperty(value = "标签 01 注册，02 忘记密码，03 预约成功，04 取消预约，05 预约支付成功，06 预约支付取消，07 报告单推送,08 病历申请")
    @TableField("msg_tag")
    private String msgTag;

    @ApiModelProperty(value = "发送内容")
    @TableField("content")
    private String content;

    @ApiModelProperty(value = "发送结果")
    @TableField("result_desc")
    private String resultDesc;

    @ApiModelProperty(value = "发送状态 0成功 1失败")
    @TableField("msg_status")
    private String msgStatus;

    @TableField("create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    @TableField("update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;

    @ApiModelProperty(value = "1医生，2患者")
    @TableField("type")
    private String type;

    @ApiModelProperty(value = "删除状态,0未删除，1已删除")
    @TableField("deletion_state")
    private String deletionState;

    @ApiModelProperty(value = "预留字段1")
    @TableField("temp1")
    private String temp1;

    @ApiModelProperty(value = "预留字段2")
    @TableField("temp2")
    private String temp2;

    @ApiModelProperty(value = "预留字段3")
    @TableField("temp3")
    private String temp3;


    public String getMsgFlowId() {
        return msgFlowId;
    }

    public void setMsgFlowId(String msgFlowId) {
        this.msgFlowId = msgFlowId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(String hospitalId) {
        this.hospitalId = hospitalId;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getMsgTag() {
        return msgTag;
    }

    public void setMsgTag(String msgTag) {
        this.msgTag = msgTag;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getResultDesc() {
        return resultDesc;
    }

    public void setResultDesc(String resultDesc) {
        this.resultDesc = resultDesc;
    }

    public String getMsgStatus() {
        return msgStatus;
    }

    public void setMsgStatus(String msgStatus) {
        this.msgStatus = msgStatus;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDeletionState() {
        return deletionState;
    }

    public void setDeletionState(String deletionState) {
        this.deletionState = deletionState;
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
        return "MsgFlow{" +
        "msgFlowId=" + msgFlowId +
        ", userId=" + userId +
        ", projectId=" + projectId +
        ", hospitalId=" + hospitalId +
        ", sender=" + sender +
        ", recipient=" + recipient +
        ", msgType=" + msgType +
        ", msgTag=" + msgTag +
        ", content=" + content +
        ", resultDesc=" + resultDesc +
        ", msgStatus=" + msgStatus +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        ", type=" + type +
        ", deletionState=" + deletionState +
        ", temp1=" + temp1 +
        ", temp2=" + temp2 +
        ", temp3=" + temp3 +
        "}";
    }
}
