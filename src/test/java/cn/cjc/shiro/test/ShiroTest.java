package cn.cjc.shiro.test;

import cn.cjc.shiro.entity.User;
import cn.cjc.shiro.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @author chenjc
 * @since 2017-03-19
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-context-test.xml")
public class ShiroTest {

    @Resource
    private AccountService accountService;

    @Test
    public void addUser() {
        User user = new User();
        user.setUsername("zhang");
        user.setPassword("123");
        accountService.addUser(user);
    }
}
