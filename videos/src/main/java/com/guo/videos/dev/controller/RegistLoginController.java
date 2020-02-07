package com.guo.videos.dev.controller;

import com.guo.videos.Utils.JsonResult;
import com.guo.videos.Utils.MD5Utils;
import com.guo.videos.dev.pojo.Users;
import com.guo.videos.dev.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistLoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/regist")
    public JsonResult regist(@RequestBody Users user) throws Exception{

        //1.判断用户名和密码不为空
        if(StringUtils.isBlank(user.getUsername()) || StringUtils.isBlank(user.getPassword())) {
            return JsonResult.errorMsg("用户名不能为空");
        }

        //2.判断用户名是否存在
        boolean usernameIsExist = userService.queryUsernameIsExist(user.getUsername());

        //3.保持用户，注册信息
        if(!usernameIsExist) {
            user.setNickname(user.getUsername());
            user.setPassword(MD5Utils.getMD5Str(user.getPassword()));
            user.setFansCounts(0);
            user.setReceiveLikeCounts(0);
            user.setFollowCounts(0);
            userService.saveUser(user);
        }else {
            return JsonResult.errorMsg("用户名已存在，请换一个再试");
        }
        user.setPassword("");

        return JsonResult.ok(user);
    }

    @PostMapping("/login")
    public JsonResult login(@RequestBody Users user) throws Exception {
        String username = user.getUsername();
        String password = user.getPassword();

        // 1. 判断用户名和密码必须不为空
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            return JsonResult.ok("用户名或密码不能为空...");
        }

        // 2. 判断用户是否存在
        Users userResult = userService.queryUserForLogin(username,
                MD5Utils.getMD5Str(user.getPassword()));

        // 3. 返回
        if (userResult != null) {
            userResult.setPassword("");
            return JsonResult.ok(userResult);
        } else {
            return JsonResult.errorMsg("用户名或密码不正确, 请重试...");
        }
    }

    @PostMapping("/logout")
    public JsonResult logout(String userId) throws Exception {
        return JsonResult.ok();
    }
}
