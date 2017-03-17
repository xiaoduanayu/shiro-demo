package cn.cjc.shiro.base;

import cn.cjc.shiro.entity.User;
import cn.cjc.shiro.service.AccountService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.List;

public class ShiroDbRealm extends AuthorizingRealm {

    private static final Logger logger = LoggerFactory.getLogger(ShiroDbRealm.class);

    @Resource
    private AccountService accountService;

    /**
     * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection p) {
        logger.info("授权认证：" + p.getRealmNames());
        String username = (String) p.getPrimaryPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        List<String> roles = accountService.queryUserRoles(username);
        info.addRoles(roles);
        List<String> permissions = accountService.queryUserPermissions(roles);
        info.addStringPermissions(permissions);
        return info;

    }

    /**
     * 认证回调函数,登录时调用.
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken authcToken) throws AuthenticationException {
        logger.info("authc pass:");
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        logger.info("authc name:" + token.getUsername());
        User user = accountService.findUserByLoginName(token.getUsername());
        if (user != null && user.getPassword().equals(new String(token.getPassword()))) {
            return new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), getName());
        }
        return null;
    }
}  