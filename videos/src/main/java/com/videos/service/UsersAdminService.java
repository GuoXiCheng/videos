package com.videos.service;

import com.videos.Utils.PagedResult;

public interface UsersAdminService {
    PagedResult queryUsers(Integer page, Integer pageSize);
}
