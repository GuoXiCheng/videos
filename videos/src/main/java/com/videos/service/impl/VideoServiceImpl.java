package com.videos.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.videos.Utils.KeyUtil;
import com.videos.Utils.PagedResult;
import com.videos.Utils.TimeAgoUtils;
import com.videos.mapper.*;
import com.videos.pojo.Comments;
import com.videos.pojo.SearchRecords;
import com.videos.pojo.UsersLikeVideos;
import com.videos.pojo.Videos;
import com.videos.service.VideoService;
import com.videos.vo.CommentsVO;
import com.videos.vo.VideosVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class VideoServiceImpl implements VideoService {


    @Autowired(required = false)
    private VideosMapper videosMapper;

    @Autowired(required = false)
    private SearchRecordsMapper searchRecordsMapper;

    @Autowired(required = false)
    private UsersLikeVideosMapper usersLikeVideosMapper;

    @Autowired(required = false)
    private UsersMapper usersMapper;

    @Autowired(required = false)
    private CommentsMapper commentsMapper;

    @Autowired(required = false)
    private CommentsMapperCustom commentsMapperCustom;

    @Autowired(required = false)
    private VideoMapperCustom videoMapperCustom;


    //随机取得一条视频数据
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Videos selectOneVideo() {
        return videosMapper.selectOneVideo();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public String saveVideo(Videos video) {
        String id = KeyUtil.genUniqueKey();
        video.setId(id);
        videosMapper.insertSelective(video);
        return id;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateVideo(String videoId, String coverPath) {
        Videos video = new Videos();
        video.setId(videoId);
        video.setCoverPath(coverPath);
        videosMapper.updateByPrimaryKeySelective(video);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public PagedResult getAllVideos(Videos video, Integer isSaveRecord, Integer page, Integer pageSize) {
        //保存热搜词
        String desc = video.getVideoDesc();
        String userId = video.getUserId();
        if(isSaveRecord!=null && isSaveRecord==1) {
            SearchRecords record = new SearchRecords();
            String recordId = KeyUtil.genUniqueKey();
            record.setId(recordId);
            record.setContent(desc);
            searchRecordsMapper.insertOne(record);
        }
        PageHelper.startPage(page,pageSize);
        List<VideosVO> list = videoMapperCustom.queryAllVideos(desc,userId);
        PageInfo<VideosVO> pageList = new PageInfo<>(list);
        PagedResult pagedResult = new PagedResult();
        pagedResult.setPage(page);
        pagedResult.setTotal(pageList.getPages());
        pagedResult.setRows(list);
        pagedResult.setRecords(pageList.getTotal());
        return pagedResult;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<String> getHotWords() {
        return searchRecordsMapper.getHotWords();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void userLikeVideo(String userId, String videoId, String videoCreaterId) {
        //1.保存用户和视频的喜欢点赞关联关系表
        String likeId = KeyUtil.genUniqueKey();
        UsersLikeVideos ulv = new UsersLikeVideos();
        ulv.setId(likeId);
        ulv.setUserId(userId);
        ulv.setVideoId(videoId);
        usersLikeVideosMapper.insertOne(ulv);
        //2.视频喜欢数量累加
        videosMapper.addVideoLikeCount(videoId);

        //3.用户受喜欢数量的累加
        usersMapper.addReceiveLikeCount(userId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void userUnLikeVideo(String userId, String videoId, String videoCreaterId) {
        //1.删除用户和视频的喜欢点赞关联关系表
        UsersLikeVideos usersLikeVideos = new UsersLikeVideos();
        usersLikeVideos.setUserId(userId);
        usersLikeVideos.setVideoId(videoId);
        usersLikeVideosMapper.deleteByEntity(usersLikeVideos);
        //2.视频喜欢数量累减
        videosMapper.reduceVideoLikeCount(videoId);

        //3.用户受喜欢数量的累减
        usersMapper.reduceReceiveLikeCount(userId);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public PagedResult queryMyLikeVideos(String userId, Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        List<VideosVO> list = videoMapperCustom.queryMyLikeVideos(userId);
        PageInfo<VideosVO> pageList = new PageInfo<>(list);
        PagedResult pagedResult = new PagedResult();
        pagedResult.setTotal(pageList.getPages());
        pagedResult.setRows(list);
        pagedResult.setPage(page);
        pagedResult.setRecords(pageList.getTotal());
        return pagedResult;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public PagedResult queryMyFollowVideos(String userId, Integer page, int pageSize) {
        PageHelper.startPage(page,pageSize);
        List<VideosVO> list = videoMapperCustom.queryMyFollowVideos(userId);
        PageInfo<VideosVO> pageList = new PageInfo<>(list);
        PagedResult pagedResult = new PagedResult();
        pagedResult.setTotal(pageList.getPages());
        pagedResult.setRows(list);
        pagedResult.setPage(page);
        pagedResult.setRecords(pageList.getTotal());
        return pagedResult;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveComment(Comments comment) {
        String id = KeyUtil.genUniqueKey();
        comment.setId(id);
        comment.setCreateTime(new Date());
        commentsMapper.insertOne(comment);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<CommentsVO> getAllComments(String videoId) {
        List<CommentsVO> list = commentsMapperCustom.queryComments(videoId);

        for (CommentsVO c : list) {
            String timeAgo = TimeAgoUtils.format(c.getCreateTime());
            c.setTimeAgoStr(timeAgo);
        }
        return list;

    }
}
