package com.guo.videos.admin.controller;


import com.guo.videos.Utils.JsonResult;
import com.guo.videos.Utils.PagedResult;
import com.guo.videos.admin.bean.AdminUser;
import com.guo.videos.admin.service.UsersService;
import com.guo.videos.admin.pojo.Users;
import com.guo.videos.admin.service.impl.UsersServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
@RequestMapping("/users")
public class UsersController {
    @Autowired(required = false)
    private UsersService usersService;

    @GetMapping("/showList")
    public String showList(){
        return "users/usersList";
    }

    @PostMapping("/list")
    @ResponseBody
    public PagedResult list(Users user, Integer page){
        PagedResult result = usersService.queryUsers(user,page==null ? 1:page,10);
        return result;
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("login")
    @ResponseBody
    public JsonResult userLogin(String username, String password,
                                HttpServletRequest request, HttpServletResponse response){
        //模拟登录
        if(StringUtils.isBlank(username) || StringUtils.isBlank(password)){
            return JsonResult.errorMsg("用户名或密码不能为空！");
        }else if(username.equals("lee") && password.equals("lee")){
            String token = UUID.randomUUID().toString();
            AdminUser user = new AdminUser(username,password,token);
            request.getSession().setAttribute("sessionUser",user);
            return JsonResult.ok();
        }
        return JsonResult.errorMsg("登录失败，请重试！");
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request,HttpServletResponse response){
        request.getSession().removeAttribute("sessionUser");
        return "login";
    }
}
