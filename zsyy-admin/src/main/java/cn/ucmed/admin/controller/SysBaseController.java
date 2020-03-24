package cn.ucmed.admin.controller;

import cn.ucmed.common.db.system.entity.SysUser;
import cn.ucmed.common.shiro.ShiroUtils;
import cn.ucmed.common.utils.DateUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.beans.PropertyEditorSupport;
import java.util.Date;

@Controller
public class SysBaseController {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // Date 类型转换
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                setValue(DateUtil.StringToDate4(text));
            }
        });
    }

    public SysUser getUser() {
        return ShiroUtils.getUser();
    }

    public Long getUserId() {
        return getUser().getUserId();
    }

    public String getUsername() {
        return getUser().getUsername();
    }

    public String getHospitalId() {
        return getUser().getHospitalId();
    }

    public String getHospitalName() {
        return getUser().getHospitalName();
    }

    public String getProjectId() {
        return getUser().getProjectId();
    }
}