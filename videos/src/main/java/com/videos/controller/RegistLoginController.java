package com.videos.controller;

import com.videos.Utils.*;
import com.videos.pojo.Users;
import com.videos.pojo.WXSessionModel;
import com.videos.service.UserService;
import com.videos.vo.UsersVO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class RegistLoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisOperator redis;

    @PostMapping("/regist")
    public JsonResult regist(@RequestBody Users user) throws Exception{

        //1.判断用户名和密码不为空
        if(StringUtils.isBlank(user.getUsername()) || StringUtils.isBlank(user.getPassword())) {
            return JsonResult.errorMsg("用户名不能为空");
        }

        //2.判断用户名是否存在
//        boolean usernameIsExist = userService.queryUsernameIsExist(user.getUsername());
        boolean usernameIsExist = true;

        //3.保持用户，注册信息
        if(!usernameIsExist) {
            user.setNickname(user.getUsername());
            user.setPassword(MD5Utils.getMD5Str(user.getPassword()));
            user.setFansCounts(0);
            user.setReceiveLikeCounts(0);
            user.setFollowCounts(0);
            user.setFaceImage("/defaultImage/face/noneface.png");
            userService.saveUser(user);
        }else {
            return JsonResult.errorMsg("用户名已存在，请换一个再试");
        }
        user.setPassword("");
        UsersVO usersVO = setUserRedisSessionToken(user);
        return JsonResult.ok(usersVO);
    }

    private UsersVO setUserRedisSessionToken(Users userModel){
        String uniqueToken = UUID.randomUUID().toString();
        redis.set("user-redis-session" + ":" + userModel.getId(),uniqueToken,1000*60*30);

        UsersVO usersVO = new UsersVO();
        BeanUtils.copyProperties(userModel,usersVO);
        usersVO.setUserToken(uniqueToken);
        return usersVO;
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
            UsersVO usersVO = setUserRedisSessionToken(userResult);
            return JsonResult.ok(usersVO);
        } else {
            return JsonResult.errorMsg("用户名或密码不正确, 请重试...");
        }
    }

    @PostMapping("/logout")
    public JsonResult logout(String userId) throws Exception {
        redis.del("user-redis-session" + ":" + userId);
        return JsonResult.ok();
    }

    @PostMapping("/wxLogin")
    public JsonResult wxLogin(String code,String nickName,String faceImage) throws Exception {
        //获取openid和sessioncode
        String appId = "wxb0c9998dbc9c3896";
        String appSecret = "1f2223f38d8282eebd3435503cf3d12b";
        String url = "https://api.weixin.qq.com/sns/jscode2session";
        Map<String,String> param = new HashMap<>();
        param.put("appid",appId);
        param.put("secret",appSecret);
        param.put("js_code",code);
        param.put("grant_type","authorization_code");
        String result = HttpClientUtil.doGet(url,param);

        WXSessionModel wxSessionModel = JsonUtils.jsonToPojo(result,WXSessionModel.class);
        Users user = userService.queryUser(wxSessionModel.getOpenid());
        //判断是否需要进行注册
        if(user == null){
            user = new Users();
            user.setId(wxSessionModel.getOpenid());
            user.setUsername(nickName);
            user.setNickname(nickName);
            user.setPassword(MD5Utils.getMD5Str(wxSessionModel.getOpenid()));
            user.setFaceImage(faceImage);
            user.setFansCounts(0);
            user.setFollowCounts(0);
            user.setReceiveLikeCounts(0);
            userService.saveUser(user);
        }

        //存入session到redis
        redis.set("user-redis-session" + ":" + wxSessionModel.getOpenid(),
                wxSessionModel.getSession_key(),1000*60*30);
        return JsonResult.ok(user);
    }

}
