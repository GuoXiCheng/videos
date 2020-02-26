package com.videos.service;

import com.videos.Utils.PagedResult;

public interface AdmUsersService {
    PagedResult queryUsers(Integer page, Integer pageSize);
}
