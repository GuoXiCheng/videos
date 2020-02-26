package com.videos.service.impl;

import com.videos.Utils.PagedResult;
import com.videos.mapper.UsersAdminMapper;
import com.videos.pojo.Users;
import com.videos.service.AdmUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdmUsersServiceImpl implements AdmUsersService {

    @Autowired(required = false)
    private UsersAdminMapper usersAdminMapper;

    @Override
    @Transactional(propagation= Propagation.SUPPORTS)
    public PagedResult queryUsers(Integer page, Integer pageSize) {

        List<Users> userList = usersAdminMapper.selectAll((page-1)*pageSize,pageSize);

        Integer Records = usersAdminMapper.selectCount();
        Integer total = Records % pageSize ==0 ? (Records / pageSize) : ((Records / pageSize) + 1);

        PagedResult pagedResult = new PagedResult();
        pagedResult.setTotalPage(total);
        pagedResult.setRows(userList);
        pagedResult.setPage(page);
        pagedResult.setRecords(Records);

        return pagedResult;
    }
}
