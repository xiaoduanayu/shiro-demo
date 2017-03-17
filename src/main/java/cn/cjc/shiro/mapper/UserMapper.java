package cn.cjc.shiro.mapper;

import cn.cjc.shiro.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author chenjc
 * @since 2017-03-17
 */
public interface UserMapper {
    User getUser(User userName);

    List<String> queryUserRoles(String username);

    List<String> queryUserPermissions(@Param("roles") List<String> roles);
}
