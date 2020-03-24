package cn.ucmed.common.db.user.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * @auther 郄彦腾
 * @create 2019-01-15 15:49:25
 * @describe 实体类
 */
@TableName("pv_user")
@ApiModel(value="pvUser对象", description="")
public class PvUser implements Serializable {
    private static final long serialVersionUID = -1425666061960484470L;
    @ApiModelProperty(value = "UUID")
    @TableId(value = "user_id", type = IdType.UUID)
    private String userId;

    @ApiModelProperty(value = "医院ID")
    @TableField("hospital_id")
    private String hospitalId;

    @ApiModelProperty(value = "院区ID")
    @TableField("branch_id")
    private String branchId;

    @ApiModelProperty(value = "注册来源，1微信，2支付宝，3rubikU，4第三方")
    @TableField("source")
    private String source;

    @ApiModelProperty(value = "账号，根据各种情况，可能是openid，手机号码")
    @TableField("account")
    private String account;

    @ApiModelProperty(value = "第三方openid，unique")
    @TableField("third_open_id")
    private String thirdOpenId;

    @ApiModelProperty(value = "密码")
    @TableField("password")
    private String password;

    @ApiModelProperty(value = "密钥，一般用来加密密码")
    @TableField("security_key")
    private String securityKey;

    @ApiModelProperty(value = "姓名")
    @TableField("user_name")
    private String userName;

    @ApiModelProperty(value = "手机号码")
    @TableField("phone")
    private String phone;

    @ApiModelProperty(value = "证件号")
    @TableField("id_card")
    private String idCard;

    @ApiModelProperty(value = "证件号类型")
    @TableField("id_card_type")
    private String idCardType;

    @ApiModelProperty(value = "卡号")
    @TableField("card")
    private String card;

    @ApiModelProperty(value = "卡类型")
    @TableField("card_type")
    private String cardType;

    @ApiModelProperty(value = "住址")
    @TableField("address")
    private String address;

    @ApiModelProperty(value = "账户状态，0正常，1黑名单")
    @TableField("state")
    private String state;

    @ApiModelProperty(value = "对state的说明，如进入预约黑名单，进入支付黑名单")
    @TableField("state_descr")
    private String stateDescr;

    @ApiModelProperty(value = "是否删除：0有效，1注销")
    @TableField("delete_state")
    @TableLogic
    private String deleteState;

    @ApiModelProperty(value = "备用字段1")
    @TableField("tmp1")
    private String tmp1;

    @ApiModelProperty(value = "备用字段2")
    @TableField("tmp2")
    private String tmp2;

    @ApiModelProperty(value = "备用字段1")
    @TableField("tmp3")
    private String tmp3;

    @TableField("union_id")
    private String unionId;

    @TableField("wechat")
    private String wechat;

    @TableField("ali_user_id")
    private String aliUserId;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField("update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;

    @TableField("remark")
    private String remark;

    @ApiModelProperty(value = "注册时候关联的第三方渠道号")
    @TableField("relation_third_source")
    private String relationThirdSource;




    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSecurityKey() {
        return securityKey;
    }

    public void setSecurityKey(String securityKey) {
        this.securityKey = securityKey;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getIdCardType() {
        return idCardType;
    }

    public void setIdCardType(String idCardType) {
        this.idCardType = idCardType;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStateDescr() {
        return stateDescr;
    }

    public void setStateDescr(String stateDescr) {
        this.stateDescr = stateDescr;
    }

    public String getDeleteState() {
        return deleteState;
    }

    public void setDeleteState(String deleteState) {
        this.deleteState = deleteState;
    }

    public String getTmp1() {
        return tmp1;
    }

    public void setTmp1(String tmp1) {
        this.tmp1 = tmp1;
    }

    public String getTmp2() {
        return tmp2;
    }

    public void setTmp2(String tmp2) {
        this.tmp2 = tmp2;
    }

    public String getTmp3() {
        return tmp3;
    }

    public void setTmp3(String tmp3) {
        this.tmp3 = tmp3;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getAliUserId() {
        return aliUserId;
    }

    public void setAliUserId(String aliUserId) {
        this.aliUserId = aliUserId;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "User{" +
        "userId=" + userId +
        ", hospitalId=" + hospitalId +
        ", branchId=" + branchId +
        ", source=" + source +
        ", account=" + account +
        ", password=" + password +
        ", securityKey=" + securityKey +
        ", userName=" + userName +
        ", phone=" + phone +
        ", idCard=" + idCard +
        ", idCardType=" + idCardType +
        ", card=" + card +
        ", cardType=" + cardType +
        ", address=" + address +
        ", state=" + state +
        ", stateDescr=" + stateDescr +
        ", deleteState=" + deleteState +
        ", tmp1=" + tmp1 +
        ", tmp2=" + tmp2 +
        ", tmp3=" + tmp3 +
        ", wechat=" + wechat +
        ", aliUserId=" + aliUserId +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        ", remark=" + remark +
        "}";
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }


    public String getThirdOpenId() {
        return thirdOpenId;
    }

    public void setThirdOpenId(String thirdOpenId) {
        this.thirdOpenId = thirdOpenId;
    }

    public String getRelationThirdSource() {
        return relationThirdSource;
    }

    public void setRelationThirdSource(String relationThirdSource) {
        this.relationThirdSource = relationThirdSource;
    }
}
