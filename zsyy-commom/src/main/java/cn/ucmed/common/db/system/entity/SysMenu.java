package cn.ucmed.common.db.system.entity;

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
 * @create 2019-01-24 13:21:17
 * @describe 菜单管理实体类
 */
@TableName("sys_menu")
@ApiModel(value="SysMenu对象", description="菜单管理")
public class SysMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "menu_id", type = IdType.AUTO)
    private Long menuId;

    @ApiModelProperty(value = "父菜单ID，一级菜单为0")
    @TableField("parent_id")
    private Long parentId;

    @ApiModelProperty(value = "菜单名称")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "菜单URL")
    @TableField("url")
    private String url;

    @ApiModelProperty(value = "授权(多个用逗号分隔，如：user:list,user:create)")
    @TableField("perms")
    private String perms;

    @ApiModelProperty(value = "类型   0：目录   1：菜单   2：按钮")
    @TableField("type")
    private Integer type;

    @ApiModelProperty(value = "菜单图标")
    @TableField("icon")
    private String icon;

    @ApiModelProperty(value = "排序")
    @TableField("order_num")
    private Integer orderNum;

    @ApiModelProperty(value = "创建时间")
    @TableField("gmt_create")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date gmtCreate;

    @ApiModelProperty(value = "修改时间")
    @TableField("gmt_modified")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date gmtModified;


    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPerms() {
        return perms;
    }

    public void setPerms(String perms) {
        this.perms = perms;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    @Override
    public String toString() {
        return "SysMenu{" +
        "menuId=" + menuId +
        ", parentId=" + parentId +
        ", name=" + name +
        ", url=" + url +
        ", perms=" + perms +
        ", type=" + type +
        ", icon=" + icon +
        ", orderNum=" + orderNum +
        ", gmtCreate=" + gmtCreate +
        ", gmtModified=" + gmtModified +
        "}";
    }
}
