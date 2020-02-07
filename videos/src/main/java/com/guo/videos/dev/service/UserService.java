package com.guo.videos.dev.service;

import com.guo.videos.dev.pojo.Users;

public interface UserService {
    //判断用户名是否存在
    boolean queryUsernameIsExist(String username);

    //保存用户（用户注册）
    void saveUser(Users user);

    //用户登录
    Users queryUserForLogin(String username, String password);
}
