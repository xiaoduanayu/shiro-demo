package cn.cjc.shiro.base;

import cn.cjc.shiro.entity.User;
import cn.cjc.shiro.service.AccountService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.List;

public class UserRealm extends AuthorizingRealm {

    private static final Logger logger = LoggerFactory.getLogger(UserRealm.class);

    @Resource
    private AccountService accountService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection p) {
        logger.info("doGetAuthorizationInfo：" + p);
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
            AuthenticationToken token) throws AuthenticationException {
        logger.info("doGetAuthenticationInfo：" + token);
        String username = (String) token.getPrincipal();
        User user = accountService.findUserByLoginName(username);
        if (user == null) {
            throw new UnknownAccountException();//没找到帐号
        }
        return new SimpleAuthenticationInfo(
                user.getUsername(), //用户名
                user.getPassword(), //密码
                ByteSource.Util.bytes(user.getCredentialsSalt()),//salt=username+passwordSalt
                getName()  //realm name
        );
    }
}  