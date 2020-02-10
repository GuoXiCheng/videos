package com.guo.videos.admin.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guo.videos.Utils.PagedResult;
import com.guo.videos.admin.pojo.Users;
import com.guo.videos.admin.pojo.mapper.UsersAdminMapper;
import com.guo.videos.admin.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired(required = false)
    private UsersAdminMapper usersAdminMapper;

    @Override
    public PagedResult queryUsers(Users user, Integer page, Integer pageSize) {
        String username = "";
        String nickname = "";
        if (user != null) {
            username = user.getUsername();
            nickname = user.getNickname();
        }

        PageHelper.startPage(page, pageSize);

        Users users = new Users();
        user.setUsername(username);
        user.setNickname(nickname);

        List<Users> userList = usersAdminMapper.selectByEntity(users);

        PageInfo<Users> pageList = new PageInfo<>(userList);

        PagedResult grid = new PagedResult();
        grid.setTotal(pageList.getPages());
        grid.setRows(userList);
        grid.setPage(page);
        grid.setRecords(pageList.getTotal());

        return grid;
    }
}
