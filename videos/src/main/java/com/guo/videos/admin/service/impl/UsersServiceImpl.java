package com.guo.videos.admin.service.impl;

import com.github.pagehelper.PageInfo;
import com.guo.videos.Utils.PagedResult;
import com.guo.videos.admin.pojo.Users;
import com.guo.videos.admin.pojo.mapper.UsersAdminMapper;
import com.guo.videos.admin.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired(required = false)
    private UsersAdminMapper usersAdminMapper;

    @Override
    public PagedResult queryUsers(Integer page, Integer pageSize) {

        List<Users> userList = usersAdminMapper.selectAll((page-1)*pageSize,pageSize);

        Integer Records = usersAdminMapper.selectCount();
        Integer total = Records % pageSize ==0 ? (Records / pageSize) : ((Records / pageSize) + 1);

        PagedResult pagedResult = new PagedResult();
        pagedResult.setTotal(total);
        pagedResult.setRows(userList);
        pagedResult.setPage(page);
        pagedResult.setRecords(Records);

        return pagedResult;
    }
}
