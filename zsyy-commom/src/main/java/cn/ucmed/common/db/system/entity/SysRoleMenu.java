package cn.ucmed.common.db.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @auther 郄彦腾
 * @create 2019-01-24 13:22:46
 * @describe 角色与菜单对应关系实体类
 */
@TableName("sys_role_menu")
@ApiModel(value="SysRoleMenu对象", description="角色与菜单对应关系")
public class SysRoleMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "角色ID")
    @TableField("role_id")
    private Long roleId;

    @ApiModelProperty(value = "菜单ID")
    @TableField("menu_id")
    private Long menuId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    @Override
    public String toString() {
        return "SysRoleMenu{" +
        "id=" + id +
        ", roleId=" + roleId +
        ", menuId=" + menuId +
        "}";
    }
}
