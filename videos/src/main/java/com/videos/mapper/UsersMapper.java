package com.videos.mapper;

import com.videos.pojo.Users;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UsersMapper {
    Users selectOne(String userId);

    void insertOne(Users user);

    void updateBySelective(Users user);

    Users selectOneById(String userId);

    void addFansCount(String userId);

    void addFollersCount(String userId);

    void reduceFansCount(String userId);

    void reduceFollersCount(String userId);

    void addReceiveLikeCount(String userId);

    void reduceReceiveLikeCount(String userId);
}
