package com.guo.videos.dev.service.impl;

import com.guo.videos.Utils.KeyUtil;
import com.guo.videos.dev.pojo.Users;
import com.guo.videos.dev.pojo.mapper.UsersMapper;
import com.guo.videos.dev.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired(required = false)
    private UsersMapper usersMapper;

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
}
