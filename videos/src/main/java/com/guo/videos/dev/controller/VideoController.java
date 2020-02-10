package com.guo.videos.dev.controller;

import com.guo.videos.Utils.FetchVideoCover;
import com.guo.videos.Utils.JsonResult;
import com.guo.videos.Utils.MergeVideoMp3;
import com.guo.videos.Utils.PagedResult;
import com.guo.videos.dev.pojo.Bgm;
import com.guo.videos.dev.pojo.Comments;
import com.guo.videos.dev.pojo.Videos;
import com.guo.videos.dev.service.BgmService;
import com.guo.videos.dev.service.VideoService;
import com.guo.videos.enums.VideoStatusEnum;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import java.io.*;
import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/video")
public class VideoController {
    @Autowired
    private BgmService bgmService;

    @Autowired
    private VideoService videoService;

    @PostMapping(value="/upload",headers="content-type=multipart/form-data")
    public JsonResult upload(String userId, String bgmId, double videoSeconds,
                             int videoWidth, int videoHeight, String desc,
                             MultipartFile file) throws Exception {
        if(StringUtils.isBlank(userId))
            return JsonResult.errorMsg("用户id不能为空");

        //保存到数据库中的相对路径
        String uploadPathDB = "/" + userId + "/video";
        String coverPathDB = "/" + userId + "/video";
        FileOutputStream fileOutputStream = null;
        InputStream inputStream = null;
        String finalVideoPath = "";
        try{
            if(file != null){
                String fileName = file.getOriginalFilename();
                String fileNamePrefix = fileName.split("\\.")[0];
                if(StringUtils.isNotBlank(fileName)){
                    //文件上传的最终保存路径
                    finalVideoPath = "C:/guoxicheng_videos_dev";
                    //设置数据库保存的路径
                    uploadPathDB += ("/" + fileName);
                    coverPathDB = coverPathDB + "/" +fileNamePrefix + ".jpg";

                    File outFile = new File(finalVideoPath);
                    if(outFile.getParentFile() !=null || !outFile.getParentFile().isDirectory()){
                        //创建父文件夹
                        outFile.getParentFile().mkdirs();
                    }
                    fileOutputStream = new FileOutputStream(outFile);
                    inputStream = file.getInputStream();
                    IOUtils.copy(inputStream,fileOutputStream);
                }
            }else{
                return JsonResult.errorMsg("上传出错！");
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
            return JsonResult.errorMsg("上传出错！");
        }catch (IOException e){
            e.printStackTrace();
            return JsonResult.errorMsg("上传出错！");
        }finally {
            if(fileOutputStream != null){
                fileOutputStream.flush();
                fileOutputStream.close();
            }
        }
        if(StringUtils.isNotBlank(bgmId)){
            Bgm bgm = bgmService.queryBgmById(bgmId);
            String mp3InputPath = "C:/guoxicheng_videos_dev" + bgm.getPath();

            MergeVideoMp3 tool = new MergeVideoMp3("C:\\\\ffmpeg\\\\bin\\\\ffmpeg.exe");
            String videoInputPath = finalVideoPath;
            String videoOutputName = UUID.randomUUID().toString() + ".mp4";
            uploadPathDB = "/" + userId + "/video" + "/" + videoOutputName;
            finalVideoPath = "C:/guoxicheng_videos_dev" + uploadPathDB;
            tool.convertor(videoInputPath,mp3InputPath,videoSeconds,finalVideoPath);
        }

        //对视频进行截图
        FetchVideoCover videoInfo = new FetchVideoCover("C:\\\\ffmpeg\\\\bin\\\\ffmpeg.exe");
        videoInfo.getCover(finalVideoPath,"C:/guoxicheng_videos_dev" + coverPathDB);

        //保存视频信息到数据库
        Videos video = new Videos();
        video.setAudioId(bgmId);
        video.setUserId(userId);
        video.setVideoSeconds((float)videoSeconds);
        video.setVideoHeight(videoHeight);
        video.setVideoWidth(videoWidth);
        video.setVideoDesc(desc);
        video.setVideoPath(uploadPathDB);
        video.setCoverPath(coverPathDB);
        video.setStatus(VideoStatusEnum.SUCCESS.value);
        video.setCreateTime(new Date());
        String videoId = videoService.saveVideo(video);
        return JsonResult.ok(videoId);
    }

    @PostMapping(value="/uploadCover",headers="content-type=multipart/from-data")
    public JsonResult uploadCover(String userId,String videoId,MultipartFile file) throws IOException {
        if(StringUtils.isBlank(videoId) || StringUtils.isBlank(userId))
            return JsonResult.errorMsg("视频主键id和用户id不能为空！");
        //保存到数据库中的相对路径
        String uploadPathDB = "/" + userId + "/videoId";
        FileOutputStream fileOutputStream = null;
        InputStream inputStream = null;
        String finalCoverPath = "";
        try{
            if(file != null){
                String fileName = file.getOriginalFilename();
                if(StringUtils.isNotBlank(fileName)){
                    //文件上传的最终保存路径
                    finalCoverPath = "C:/guoxicheng_videos_dev" + uploadPathDB + "/" + fileName;
                    //设置数据库保存的路径
                    uploadPathDB += ("/" + fileName);

                    File outFile = new File(finalCoverPath);
                    if(outFile.getParentFile() !=null || !outFile.getParentFile().isDirectory()){
                        //创建父文件夹
                        outFile.getParentFile().mkdirs();
                    }
                    fileOutputStream = new FileOutputStream(outFile);
                    inputStream = file.getInputStream();
                    IOUtils.copy(inputStream,fileOutputStream);
                }
            }else{
                return JsonResult.errorMsg("上传出错！");
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
            return JsonResult.errorMsg("上传出错！");
        }catch (IOException e){
            e.printStackTrace();
            return JsonResult.errorMsg("上传出错！");
        }finally {
            if(fileOutputStream != null){
                fileOutputStream.flush();
                fileOutputStream.close();
            }
        }
        videoService.updateVideo(videoId,uploadPathDB);
        return JsonResult.ok(videoId);
    }

    //分页和搜索查询视频列表
    //isSaveRecord:0 需要保存，1 不需要保存或为空
    @PostMapping(value="/showAll")
    public JsonResult showAll(@RequestBody Videos video,Integer isSaveRecord,Integer page,Integer pageSize){
        if(page == null)
            page = 1;
        if(pageSize == null)
            pageSize = 5;
        PagedResult result = videoService.getAllVideos(video,isSaveRecord,page,pageSize);
        return JsonResult.ok(result);
    }

    //我关注的人发的视频
    @PostMapping("/showMyFollow")
    public JsonResult showMyFollow(String userId,Integer page){
        if(StringUtils.isBlank(userId))
            return JsonResult.ok();
        if(page == null)
            page = 1;
        int pageSize = 6;
        PagedResult videoList = videoService.queryMyFollowVideos(userId,page,pageSize);
        return JsonResult.ok(videoList);
    }

    //我收藏（点赞）的视频列表
    @PostMapping("/showMyLike")
    public JsonResult showMyLike(String userId,Integer page,Integer pageSize){
        if(StringUtils.isBlank(userId))
            return JsonResult.ok();
        if(page==null)
            page = 1;
        if(pageSize == null)
            pageSize = 6;
        PagedResult videoList = videoService.queryMyLikeVideos(userId,page,pageSize);
        return JsonResult.ok(videoList);
    }

    @PostMapping(value="/hot")
    public JsonResult hot(){
        return JsonResult.ok(videoService.getHotWords());
    }

    @PostMapping(value="/userLike")
    public JsonResult userLike(String userId,String videoId,String videoCreaterId){
        videoService.userLikeVideo(userId,videoId,videoCreaterId);
        return JsonResult.ok();
    }

    @PostMapping(value="/userUnLike")
    public JsonResult userUnLike(String userId,String videoId,String videoCreaterId){
        videoService.userUnLikeVideo(userId,videoId,videoCreaterId);
        return JsonResult.ok();
    }

    @PostMapping(value="/saveComment")
    public JsonResult saveComment(@RequestBody Comments comment,String fatherCommentId,String toUserId){
        if(StringUtils.isBlank(fatherCommentId))
            return JsonResult.ok();
        comment.setFatherCommentId(fatherCommentId);
        comment.setToUserId(toUserId);
        videoService.saveComment(comment);
        return JsonResult.ok();
    }

    @PostMapping("/getVideoComments")
    public JsonResult getVideoComments(String videoId,Integer page,Integer pageSize){
        if(StringUtils.isBlank(videoId))
            return JsonResult.ok();
        //分页查询视频列表，时间顺序倒序排序
        if(page==null)
            page = 1;
        if(pageSize==null)
            pageSize=10;
        PagedResult list = videoService.getAllComments(videoId,page,pageSize);
        return JsonResult.ok(list);
    }
}
