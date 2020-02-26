package com.videos.service.impl;


import com.videos.Utils.KeyUtil;
import com.videos.mapper.UsersFansMapper;
import com.videos.mapper.UsersLikeVideosMapper;
import com.videos.mapper.UsersMapper;
import com.videos.mapper.UsersReportMapper;
import com.videos.pojo.Users;
import com.videos.pojo.UsersFans;
import com.videos.pojo.UsersLikeVideos;
import com.videos.pojo.UsersReport;
import com.videos.service.WxUsersService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class WxUsersServiceImpl implements WxUsersService {

    @Autowired(required = false)
    private UsersMapper usersMapper;

    @Autowired(required = false)
    private UsersLikeVideosMapper usersLikeVideosMapper;

    @Autowired(required = false)
    private UsersFansMapper usersFansMapper;

    @Autowired(required = false)
    private UsersReportMapper usersReportMapper;

    @Override
    @Transactional(propagation= Propagation.SUPPORTS)
    public Users queryUser(String userId) {
        return usersMapper.selectOne(userId);
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED)
    public void saveUser(Users user) {
        usersMapper.insertOne(user);
    }

    @Override
    @Transactional(propagation= Propagation.SUPPORTS)
    public Users queryUserForLogin(String username, String password) {
        Users user = new Users();
        user.setUsername(username);
        user.setPassword(password);
        //TODO:要使用时需要修改
        return usersMapper.selectOne(username);
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED)
    public void updateUserInfo(Users user) {
        usersMapper.updateBySelective(user);
    }

    @Override
    @Transactional(propagation= Propagation.SUPPORTS)
    public Users queryUserInfo(String userId) {
        return usersMapper.selectOneById(userId);
    }


    @Override
    @Transactional(propagation= Propagation.SUPPORTS)
    public boolean isUserLikeVideo(String userId, String videoId) {
        if(StringUtils.isBlank(userId) || StringUtils.isBlank(videoId)) {
            return false;
        }
        UsersLikeVideos usersLikeVideos = new UsersLikeVideos();
        usersLikeVideos.setUserId(userId);
        usersLikeVideos.setVideoId(videoId);
        List<UsersLikeVideos> list = usersLikeVideosMapper.selectByEntity(usersLikeVideos);
        if(list !=null && list.size() > 0) {
            return true;
        }
        return false;
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED)
    public void saveUserFanRelation(String userId, String fanId) {
        String relId = KeyUtil.genUniqueKey();
        UsersFans userFan = new UsersFans();
        userFan.setId(relId);
        userFan.setUserId(userId);
        userFan.setFanId(fanId);
        usersFansMapper.insertOne(userFan);
        usersMapper.addFansCount(userId);
        usersMapper.addFollersCount(fanId);
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED)
    public void deleteUserFanRelation(String userId, String fanId) {
        UsersFans usersFans = new UsersFans();
        usersFans.setUserId(userId);
        usersFans.setFanId(fanId);
        usersFansMapper.deleteByEntity(usersFans);
        usersMapper.reduceFansCount(userId);
        usersMapper.reduceFollersCount(fanId);
    }

    @Override
    @Transactional(propagation= Propagation.SUPPORTS)
    public boolean queryIfFollow(String userId, String fanId) {
        UsersFans usersFans = new UsersFans();
        usersFans.setUserId(userId);
        usersFans.setFanId(fanId);
        List<UsersFans> list = usersFansMapper.selectByEntity(usersFans);
        if(list!=null && !list.isEmpty() && list.size() > 0) {
            return true;
        }
        return false;
    }

    @Override
    public void reportUser(UsersReport userReport) {
        String urId = KeyUtil.genUniqueKey();
        userReport.setId(urId);
        userReport.setCreateDate(new Date());
        if(StringUtils.isBlank(userReport.getContent())){
            userReport.setContent("");
        }
        usersReportMapper.insertOne(userReport);
    }
}
