package cn.ucmed.admin.controller;

import cn.ucmed.common.db.hospital.entity.SysHospital;
import cn.ucmed.common.db.hospital.service.ISysHospitalService;
import cn.ucmed.common.db.system.entity.SysUser;
import cn.ucmed.common.db.system.service.ISysMenuService;
import cn.ucmed.common.db.system.service.ISysUserService;
import cn.ucmed.common.shiro.ShiroUtils;
import cn.ucmed.common.utils.MD5Utils;
import cn.ucmed.common.utils.result.Result;
import cn.ucmed.common.utils.result.ResultUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 后台登录
 */
@Controller
@RequestMapping("/admin/login")
public class SysLoginController extends SysBaseController {

    @Autowired
    private ISysMenuService sysMenuService;

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private ISysHospitalService hospitalService;

    @GetMapping("")
    public String login() {
        return "login";
    }

    @PostMapping("")
    @ResponseBody
    public Result ajaxLogin(String username, String password, String captcha) {
        String kaptcha = ShiroUtils.getKaptcha("KAPTCHA_SESSION_KEY");
        if (!captcha.equalsIgnoreCase(kaptcha)) {
            return ResultUtils.error("验证码不正确");
        }
        password = MD5Utils.encrypt(username, password);
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            setHospitalInfo();
            return ResultUtils.success();
        } catch (AuthenticationException e) {
            return ResultUtils.error("用户或密码错误");
        }
    }

    /**
     * 用户绑定医院项目信息
     */
    private void setHospitalInfo() {
        SysUser sysUser = ShiroUtils.getUser();
        String hospitalId = "";
        String hospitalName = "";
        String projectId = "";
        if ("1".equals(sysUser.getIsSuperman())) {
            //超级管理员
            SysHospital hospital = hospitalService.getOne(new QueryWrapper<SysHospital>()
                    .eq(StringUtils.isNotBlank(sysUser.getLastLoginHospital()),
                            "hospital_id", sysUser.getLastLoginHospital())
                    .last("limit 1"));
            if (null != hospital) {
                hospitalId = hospital.getHospitalId();
                hospitalName = hospital.getHospitalName();
                projectId = hospital.getProjectId();
            }
        } else if ("1".equals(sysUser.getIsProject())) {
            //项目管理员
            if (StringUtils.isNotBlank(sysUser.getLastLoginHospital())) {
                SysHospital hospital = hospitalService.getById(sysUser.getLastLoginHospital());
                hospitalId = hospital.getHospitalId();
                hospitalName = hospital.getHospitalName();
                projectId = hospital.getProjectId();
            } else {
                SysHospital hospital = hospitalService.getOne(new QueryWrapper<SysHospital>()
                        .eq("project_id", sysUser.getProjectId()).last("limit 1"));
                hospitalId = hospital.getHospitalId();
                hospitalName = hospital.getHospitalName();
                projectId = hospital.getProjectId();
            }

        } else {
            hospitalId = sysUser.getHospitalId();
            hospitalName = sysUser.getHospitalName();
            projectId = sysUser.getProjectId();
        }
        ShiroUtils.updateUser(hospitalId, hospitalName, projectId);
        SysUser entity = new SysUser();
        entity.setUserId(sysUser.getUserId());
        entity.setLastLoginHospital(hospitalId);
        sysUserService.updateById(entity);
    }

    //登录后获取该用户的权限菜单
    @GetMapping("/index")
    public String index(Model model) {
        if (sysUserService.isSuperman(getUserId())) {
            model.addAttribute("menus", sysMenuService.superMenuTree());
        } else {
            model.addAttribute("menus", sysMenuService.listMenuTree(getUserId(), getHospitalId()));
        }
        model.addAttribute("name", getUser().getName());
        model.addAttribute("username", getUser().getUsername());
        model.addAttribute("hospitalName", getHospitalName());
        return "index_v1";
    }

    @GetMapping("/logout")
    public String logout() {
        ShiroUtils.logout();
        return "redirect:/admin/login";
    }

    @GetMapping("/main")
    public String main() {
        return "main";
    }

    @PostMapping("changeLoginHospital")
    @ResponseBody
    public Result changeLoginHospital(SysHospital hospital) {
        SysUser sysUser = ShiroUtils.getUser();
        ShiroUtils.updateUser(hospital.getHospitalId(), hospital.getHospitalName(), hospital.getProjectId());
        SysUser entity = new SysUser();
        entity.setUserId(sysUser.getUserId());
        entity.setLastLoginHospital(hospital.getHospitalId());
        sysUserService.updateById(entity);
        return ResultUtils.success();
    }

}
