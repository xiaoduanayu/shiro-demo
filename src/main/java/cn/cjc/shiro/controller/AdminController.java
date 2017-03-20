package cn.cjc.shiro.controller;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @RequestMapping("/index")
    public String index() {
        return "admin/index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "admin/login";
    }

    /**
     * 只有登录验证失败才会进入该方法
     *
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String doLogin(HttpServletRequest request, RedirectAttributes model) {
        String errorClassName = (String) request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
        String error = "登录失败";
        if (UnknownAccountException.class.getName().equals(errorClassName)) {
            error = "帐号不存在";
        } else if (IncorrectCredentialsException.class.getName().equals(errorClassName)) {
            error = "密码错误";
        } else if (errorClassName != null) {
            error = "未知错误：" + errorClassName;
        }
        model.addFlashAttribute("error", error);
        return "redirect:/admin/login";
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

    @RequestMapping("/loginSuccess")
    public String loginSuccess() {
        return "admin/login_success";
    }

}  