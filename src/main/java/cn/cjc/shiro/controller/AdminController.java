package cn.cjc.shiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @RequestMapping("/index")
    public String index() {
        System.out.println(SecurityUtils.getSubject().isAuthenticated());
        return "admin/index";
    }

    @RequestMapping("/login")
    public String login() {
        return "admin/login";
    }

    @RequestMapping("/doLogin")
    public String doLogin(HttpServletRequest request, @RequestParam String username, @RequestParam String password) {
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        SecurityUtils.getSubject().login(token);
        String url = WebUtils.getSavedRequest(request).getRequestUrl();
        return "redirect:" + url;
    }

    @RequiresRoles("user")
    @RequestMapping("/user")
    public String shiroUser() {
        return "admin/index";
    }

    @RequiresRoles("admin")
    @RequestMapping("/admin")
    public String shiroAdmin() {
        return "admin/index";
    }

    @RequestMapping("/logout")
    public String logout() {
        return "admin/logout";
    }

}  