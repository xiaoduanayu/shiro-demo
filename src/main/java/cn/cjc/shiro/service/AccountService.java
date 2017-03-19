package cn.cjc.shiro.service;

import cn.cjc.shiro.entity.User;
import cn.cjc.shiro.mapper.UserMapper;
import cn.cjc.shiro.util.PasswordUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import static org.apache.shiro.web.filter.mgt.DefaultFilter.user;

/**
 * @author chenjc
 * @since 2017-03-17
 */
@Service
public class AccountService {

    @Resource
    private UserMapper userMapper;

    public void addUser(User user){
        PasswordUtil.encryptPassword(user);
        userMapper.addUser(user);
    }

    public User findUserByLoginName(String username) {
        User param = new User();
        param.setUsername(username);
        return userMapper.getUser(param);
    }

    public List<String> queryUserRoles(String username) {
        return userMapper.queryUserRoles(username);
    }

    public List<String> queryUserPermissions(List<String> roles) {
        return userMapper.queryUserPermissions(roles);
    }
}
