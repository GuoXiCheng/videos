package com.videos.controller;


import com.videos.Utils.JsonResult;
import com.videos.Utils.PagedResult;
import com.videos.pojo.AdminUser;
import com.videos.service.AdmUsersService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/users")
public class AdmUsersController {
    @Autowired(required = false)
    private AdmUsersService admUsersService;

    //登录
    @GetMapping("/login")
    public ModelAndView login(){
        return new ModelAndView("login");
    }

    //登录校验
    @PostMapping("/login")
    @ResponseBody
    public JsonResult userLogin(String username, String password,
                                HttpServletRequest request, HttpServletResponse response){
        //模拟登录
        if(StringUtils.isBlank(username) || StringUtils.isBlank(password)){
            return JsonResult.errorMsg("用户名或密码不能为空！");
        }else if(username.equals("guo") && password.equals("vaijuaefiaowjefijaa")){
            String token = UUID.randomUUID().toString();
            AdminUser user = new AdminUser(username,password,token);
            request.getSession().setAttribute("sessionUser",user);
            return JsonResult.ok();
        }
        return JsonResult.errorMsg("登录失败，请重试！");
    }

    //用户信息
    @GetMapping("/showList")
    public ModelAndView showList(Integer page,Map<String,Object> map){
        if(page == null || page <= 0)
            page = 1;
        PagedResult result = admUsersService.queryUsers(page,5);
        if(result != null)
            map.put("result",result);
        return new ModelAndView("userinfo",map);
    }


    //登出
    @GetMapping("/logout")
    public String logout(HttpServletRequest request,HttpServletResponse response){
        request.getSession().removeAttribute("sessionUser");
        return "login";
    }
}
