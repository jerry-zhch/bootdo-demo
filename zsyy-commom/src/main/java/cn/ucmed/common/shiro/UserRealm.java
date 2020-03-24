package cn.ucmed.common.shiro;

import cn.ucmed.common.db.system.entity.SysUser;
import cn.ucmed.common.db.system.mapper.SysUserMapper;
import cn.ucmed.common.db.system.service.ISysMenuService;
import cn.ucmed.common.db.system.service.ISysUserService;
import cn.ucmed.common.exception.BusinessException;
import cn.ucmed.common.utils.SpringBean;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class UserRealm extends AuthorizingRealm {
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        SysUser user = ShiroUtils.getUser();
        ISysMenuService sysMenuService = SpringBean.getBean(ISysMenuService.class);
        ISysUserService sysUserService = SpringBean.getBean(ISysUserService.class);
        Set<String> perms;
        if (sysUserService.isSuperman(user.getUserId())){ // 超级管理员
            perms = sysMenuService.superPerms();
        }else {
            perms = sysMenuService.listPerms(user.getUserId(),user.getHospitalId());
        }
        info.setStringPermissions(perms);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();
        String password = new String((char[]) authenticationToken.getCredentials());
        SysUserMapper userMapper = SpringBean.getBean(SysUserMapper.class);
        // 查询用户信息
        SysUser user = userMapper.selectOne(new QueryWrapper<SysUser>().eq("username",username));
        // 账号不存在
        if (user == null) {
            throw new BusinessException(500,"账号或密码不正确");
        }
        // 密码错误
        if (!password.equals(user.getPassword())) {
            throw new BusinessException(500,"账号或密码不正确");
        }
        // 账号锁定
        if (user.getStatus() == 0) {
            throw new BusinessException(500,"账号已被锁定,请联系管理员");
        }
        return new SimpleAuthenticationInfo(user, password, getName());
    }

}
