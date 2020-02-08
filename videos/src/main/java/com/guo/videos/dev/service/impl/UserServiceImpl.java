package com.guo.videos.dev.service.impl;

import com.guo.videos.Utils.KeyUtil;
import com.guo.videos.dev.pojo.Users;
import com.guo.videos.dev.pojo.UsersFans;
import com.guo.videos.dev.pojo.UsersLikeVideos;
import com.guo.videos.dev.pojo.UsersReport;
import com.guo.videos.dev.pojo.mapper.UsersFansMapper;
import com.guo.videos.dev.pojo.mapper.UsersLikeVideosMapper;
import com.guo.videos.dev.pojo.mapper.UsersMapper;
import com.guo.videos.dev.pojo.mapper.UsersReportMapper;
import com.guo.videos.dev.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

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
    public boolean queryUsernameIsExist(String username) {
        Users user = new Users();
        user.setUsername(username);
        Users result = usersMapper.selectOne(user);
        return result==null ? false : true;
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED)
    public void saveUser(Users user) {
        String userId = KeyUtil.genUniqueKey();
        user.setId(userId);
        usersMapper.insertOne(user);
    }

    @Override
    @Transactional(propagation= Propagation.SUPPORTS)
    public Users queryUserForLogin(String username, String password) {
        Users user = new Users();
        user.setUsername(username);
        user.setPassword(password);
        return usersMapper.selectOne(user);
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
        usersReportMapper.insertOne(userReport);
    }
}
