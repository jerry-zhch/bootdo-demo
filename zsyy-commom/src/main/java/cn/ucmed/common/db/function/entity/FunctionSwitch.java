package cn.ucmed.common.db.function.entity;

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
 * @create 2020-01-16 10:24:26
 * @describe 功能开关实体类
 */
@TableName("function_switch")
@ApiModel(value="FunctionSwitch对象", description="功能开关")
public class FunctionSwitch implements Serializable {

    private static final long serialVersionUID = 6290034501699909483L;
    @ApiModelProperty(value = "医院id")
    @TableId(value = "hospital_id", type = IdType.UUID)
    private String hospitalId;

    @ApiModelProperty(value = "预约流程是否开启1开启 0关闭")
    @TableField("order_is_open")
    private String orderIsOpen;

    @ApiModelProperty(value = "门诊流程是否开启1开启 0关闭")
    @TableField("clinic_is_open")
    private String clinicIsOpen;

    @ApiModelProperty(value = "报告单流程是否开启1开启 0关闭")
    @TableField("report_is_open")
    private String reportIsOpen;

    @ApiModelProperty(value = "住院流程是否开启1开启 0关闭")
    @TableField("beinhospital_is_opem")
    private String beinhospitalIsOpem;

    @ApiModelProperty(value = "提示语")
    @TableField("tip")
    private String tip;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    @TableField("update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;

    @ApiModelProperty(value = "扩展字段")
    @TableField("extend")
    private String extend;


    public String getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(String hospitalId) {
        this.hospitalId = hospitalId;
    }

    public String getOrderIsOpen() {
        return orderIsOpen;
    }

    public void setOrderIsOpen(String orderIsOpen) {
        this.orderIsOpen = orderIsOpen;
    }

    public String getClinicIsOpen() {
        return clinicIsOpen;
    }

    public void setClinicIsOpen(String clinicIsOpen) {
        this.clinicIsOpen = clinicIsOpen;
    }

    public String getReportIsOpen() {
        return reportIsOpen;
    }

    public void setReportIsOpen(String reportIsOpen) {
        this.reportIsOpen = reportIsOpen;
    }

    public String getBeinhospitalIsOpem() {
        return beinhospitalIsOpem;
    }

    public void setBeinhospitalIsOpem(String beinhospitalIsOpem) {
        this.beinhospitalIsOpem = beinhospitalIsOpem;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
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

    public String getExtend() {
        return extend;
    }

    public void setExtend(String extend) {
        this.extend = extend;
    }

    @Override
    public String toString() {
        return "FunctionSwitch{" +
        "hospitalId=" + hospitalId +
        ", orderIsOpen=" + orderIsOpen +
        ", clinicIsOpen=" + clinicIsOpen +
        ", reportIsOpen=" + reportIsOpen +
        ", beinhospitalIsOpem=" + beinhospitalIsOpem +
        ", tip=" + tip +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        ", extend=" + extend +
        "}";
    }
}
