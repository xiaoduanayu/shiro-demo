package cn.cjc.shiro.controller;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model) {
        return "admin/index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {
        logger.info("login get");
        return "admin/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String doLogin(Model model) {
        logger.info("login post");
        return "admin/index";
    }

    @RequiresRoles("user")
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String shiroUser(Model model) {
        return "admin/index";
    }

    @RequiresRoles("admin")
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String shiroAdmin(Model model) {
        return "admin/index";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(Model model) {
        return "admin/logout";
    }

}  