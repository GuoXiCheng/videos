package com.guo.videos.admin.service;

import com.guo.videos.Utils.PagedResult;
import com.guo.videos.admin.pojo.Users;

public interface UsersService {
    PagedResult queryUsers(Users user, Integer page, Integer pageSize);
}