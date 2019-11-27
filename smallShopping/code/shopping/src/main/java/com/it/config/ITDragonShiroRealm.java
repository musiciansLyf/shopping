
package com.it.config;

import com.it.entity.Permission;
import com.it.entity.Role;
import com.it.entity.User;
import com.it.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * 自定义安全数据Realm，重点
 *
 * @author itdragon
 */

public class ITDragonShiroRealm extends AuthorizingRealm {
    private static final transient Logger log = LoggerFactory.getLogger(ITDragonShiroRealm.class);

    @Autowired
    private UserService userService;


    /**
     * 授权
     */

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        log.info("^^^^^^^^^^^^^^^^^^^^ ITYao 配置当前用户权限");
        String username = (String) principals.getPrimaryPrincipal();
        User user = userService.findUserByUserName(username);
        if (null == user) {
            return null;
        }
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        for (Role role : user.getRoleList()) {
            // 添加角色
            authorizationInfo.addRole(role.getRole());
            for (Permission permission : role.getPermissions()) {
                // 添加具体权限
                authorizationInfo.addStringPermission(permission.getPermissionMark());
            }
        }
        return authorizationInfo;
    }


    /**
     * 身份认证
     */

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
        log.info("^^^^^^^^^^^^^^^^^^^^ ITYao 认证用户身份信息");
        // 获取用户登录账号
        String username = (String) token.getPrincipal();
        // 通过账号查加密后的密码和盐，这里一般从缓存读取
        User userInfo = userService.findUserByUserName(username);
        if (null == userInfo) {
            return null;
        }
        // 1). principal: 认证的实体信息. 可以是 username, 也可以是数据表对应的用户的实体类对象. 
        Object principal = username;
        // 2). credentials: 加密后的密码. 
        Object credentials = userInfo.getPassword();
        // 3). realmName: 当前 realm 对象的唯一名字. 调用父类的 getName() 方法
        String realmName = getName();
        // 4). credentialsSalt: 盐值. 注意类型是ByteSource
        ByteSource credentialsSalt = ByteSource.Util.bytes(userInfo.getSalt());
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principal, credentials, credentialsSalt, realmName);
        return info;
    }
}
