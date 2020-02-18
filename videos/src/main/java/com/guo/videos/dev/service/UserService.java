package com.guo.videos.dev.service;

import com.guo.videos.dev.pojo.Users;
import com.guo.videos.dev.pojo.UsersReport;

public interface UserService {
    //根据id检索用户
    Users queryUser(String userId);

    //保存用户（用户注册）
    void saveUser(Users user);

    //用户登录
    Users queryUserForLogin(String username, String password);

    //修改用户信息
    void updateUserInfo(Users user);

    //查询用户信息
    Users queryUserInfo(String userId);

    //查询用户是否喜欢点赞视频
    boolean isUserLikeVideo(String userId, String videoId);

    //增加用户和粉丝的关系
    void saveUserFanRelation(String userId,String fanId);

    //删除用户和粉丝的关系
    void deleteUserFanRelation(String userId,String fanId);

    //查询用户是否关注
    boolean queryIfFollow(String userId,String fanId);

    //举报用户
    void reportUser(UsersReport userReport);
}
