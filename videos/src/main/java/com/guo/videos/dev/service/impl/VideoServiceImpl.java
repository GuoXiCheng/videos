package com.guo.videos.dev.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guo.videos.Utils.KeyUtil;
import com.guo.videos.Utils.PagedResult;
import com.guo.videos.Utils.TimeAgoUtils;
import com.guo.videos.dev.pojo.Comments;
import com.guo.videos.dev.pojo.SearchRecords;
import com.guo.videos.dev.pojo.UsersLikeVideos;
import com.guo.videos.dev.pojo.Videos;
import com.guo.videos.dev.pojo.mapper.*;
import com.guo.videos.dev.pojo.vo.CommentsVO;
import com.guo.videos.dev.pojo.vo.VideosVO;
import com.guo.videos.dev.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public class VideoServiceImpl implements VideoService {


    @Autowired
    private VideosMapper videosMapper;

    @Autowired
    private SearchRecordsMapper searchRecordsMapper;

    @Autowired
    private UsersLikeVideosMapper usersLikeVideosMapper;

    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private CommentsMapper commentsMapper;

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
        List<VideosVO> list = videosMapper.queryAllVideos(desc,userId);
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
        List<VideosVO> list = videosMapper.queryMyLikeVideos(userId);
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
        List<VideosVO> list = videosMapper.queryMyFollowVideos(userId);
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
    public PagedResult getAllComments(String videoId, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);

        List<CommentsVO> list = commentsMapper.queryComments(videoId);

        for (CommentsVO c : list) {
            String timeAgo = TimeAgoUtils.format(c.getCreateTime());
            c.setTimeAgoStr(timeAgo);
        }

        PageInfo<CommentsVO> pageList = new PageInfo<>(list);

        PagedResult grid = new PagedResult();
        grid.setTotal(pageList.getPages());
        grid.setRows(list);
        grid.setPage(page);
        grid.setRecords(pageList.getTotal());

        return grid;

    }
}
