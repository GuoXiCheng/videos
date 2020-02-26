package com.videos.controller;

import com.videos.Utils.*;
import com.videos.pojo.Bgm;
import com.videos.pojo.Comments;
import com.videos.pojo.Videos;
import com.videos.service.BgmService;
import com.videos.service.WxVideoService;
import com.videos.vo.VideosVO;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/video")
public class WxVideoController {
    @Autowired
    private BgmService bgmService;

    @Autowired
    private WxVideoService wxVideoService;


    //随机取得一条视频数据
    @GetMapping(value="/selectOneVideo")
    public JsonResult selectOneVideo(){
        Videos video = wxVideoService.selectOneVideo();
        return JsonResult.ok(video);
    }


    @PostMapping(value="/upload",headers="content-type=multipart/form-data")
    public JsonResult upload(String userId, String bgmId, String videoSeconds,
                             String videoWidth, String videoHeight, String desc,
                             MultipartFile file) throws Exception {
        if(StringUtils.isBlank(userId)) {
            return JsonResult.errorMsg("用户id不能为空");
        }


        //保存到数据库中的相对路径
        String uploadPathDB = "/" + userId + "/video";
        String coverPathDB = "/" + userId + "/video";
        FileOutputStream fileOutputStream = null;
        InputStream inputStream;
        String finalVideoPath = "";
        try {
            if(file != null) {
                String fileName = file.getOriginalFilename();
                String fileNamePrefix = KeyUtil.genUniqueKey();
                if(StringUtils.isNotBlank(fileName)) {
                    //文件上传的最终保存路径
                    finalVideoPath = "C:/guoxicheng_videos_dev" + uploadPathDB + "/" + fileName;
                    //设置数据库保存的路径
                    uploadPathDB += ("/" + fileName);
                    coverPathDB = coverPathDB + "/" + fileNamePrefix + ".jpg";

                    File outFile = new File(finalVideoPath);
                    if(outFile.getParentFile() != null || !outFile.getParentFile().isDirectory()) {
                        //创建父文件夹
                        outFile.getParentFile().mkdirs();
                    }
                    fileOutputStream = new FileOutputStream(outFile);
                    inputStream = file.getInputStream();
                    IOUtils.copy(inputStream, fileOutputStream);
                }
            }else {
                return JsonResult.errorMsg("上传出错");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return JsonResult.errorMsg("上传出错");
        } catch (IOException e) {
            e.printStackTrace();
            return JsonResult.errorMsg("上传出错");
        }finally {
            if(fileOutputStream !=null) {
                fileOutputStream.flush();
                fileOutputStream.close();
            }
        }
        //判断bgmId是否为空，如果不为空，就查询bgm信息，并且合并视频，产生新的视频
        if(StringUtils.isNotBlank(bgmId)) {
            Bgm bgm = bgmService.queryBgmById(bgmId);
            String mp3InputPath = "C:/guoxicheng_videos_dev" + bgm.getPath();

            MergeVideoMp3 tool = new MergeVideoMp3("C:\\ffmpeg\\bin\\ffmpeg.exe");
            String videoInputPath = finalVideoPath;
            String videoOutputName = UUID.randomUUID().toString() + ".mp4";
            uploadPathDB = "/" + userId + "/video" + "/" + videoOutputName;
            finalVideoPath = "C:/guoxicheng_videos_dev" + uploadPathDB;
            tool.merge(videoInputPath, mp3InputPath,videoSeconds, finalVideoPath);
        }

        //对视频进行截图
        FetchVideoCover videoInfo = new FetchVideoCover("C:\\ffmpeg\\bin\\ffmpeg.exe");
        videoInfo.getCover(finalVideoPath, "C:/guoxicheng_videos_dev" + coverPathDB);

        //保存视频信息到数据库
        Videos video = new Videos();
        video.setAudioId(bgmId);
        video.setUserId(userId);
        video.setVideoSeconds(Float.valueOf(videoSeconds));
        video.setVideoHeight(Integer.valueOf(videoHeight));
        video.setVideoWidth(Integer.valueOf(videoWidth));
        video.setVideoDesc(desc);
        video.setVideoPath(uploadPathDB);
        video.setCoverPath(coverPathDB);
        video.setStatus(0);
        video.setCreateTime(new Date());

        String videoId = wxVideoService.saveVideo(video);

        return JsonResult.ok(videoId);
    }

    //上传视频封面
//    @PostMapping(value="/uploadCover",headers="content-type=multipart/form-data")
//        public JsonResult uploadCover(String userId,String videoId,MultipartFile file) throws IOException {
//            if(StringUtils.isBlank(videoId) || StringUtils.isBlank(userId)) {
//                return JsonResult.errorMsg("视频主键id和用户id不能为空");
//            }
//
//            //保存到数据库中的相对路径
//            String uploadPathDB = "/" + userId + "/video";
//            FileOutputStream fileOutputStream = null;
//            InputStream inputStream = null;
//            String finalCoverPath = "";
//            try {
//                if(file != null) {
//                    String fileName = file.getOriginalFilename();
//                    if(StringUtils.isNotBlank(fileName)) {
//                        //文件上传的最终保存路径
//                        finalCoverPath = "C:/guoxicheng_videos_dev" + uploadPathDB + "/" + fileName;
//                        //设置数据库保存的路径
//                        uploadPathDB += ("/" + fileName);
//
//                        File outFile = new File(finalCoverPath);
//                        if(outFile.getParentFile() != null || !outFile.getParentFile().isDirectory()) {
//                            //创建父文件夹
//                            outFile.getParentFile().mkdirs();
//                        }
//                        fileOutputStream = new FileOutputStream(outFile);
//                        inputStream = file.getInputStream();
//                        IOUtils.copy(inputStream, fileOutputStream);
//                    }
//                }else {
//                    return JsonResult.errorMsg("上传出错");
//                }
//            } catch (FileNotFoundException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//                return JsonResult.errorMsg("上传出错");
//            } catch (IOException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//                return JsonResult.errorMsg("上传出错");
//            }finally {
//                if(fileOutputStream !=null) {
//                    fileOutputStream.flush();
//                    fileOutputStream.close();
//                }
//            }
//
//            wxVideoService.updateVideo(videoId, uploadPathDB);
//
//            return JsonResult.ok(videoId);
//    }

    //我发的视频作品
    @PostMapping("/showMyWork")
    public JsonResult showMyWork(String userId,Integer page){
        if(StringUtils.isBlank(userId))
            return JsonResult.ok();
        if(page == null)
            page = 1;
        int pageSize = 6;
        PagedResult pagedResult = wxVideoService.queryMyWorkVideos(userId,page,pageSize);
        return JsonResult.ok(pagedResult);
    }

    //我关注的人发的视频
    @PostMapping("/showMyFollow")
    public JsonResult showMyFollow(String userId,Integer page){
        if(StringUtils.isBlank(userId))
            return JsonResult.ok();
        if(page == null)
            page = 1;
        int pageSize = 6;
        PagedResult pagedResult = wxVideoService.queryMyFollowVideos(userId,page,pageSize);
        return JsonResult.ok(pagedResult);
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
        PagedResult pagedResult = wxVideoService.queryMyLikeVideos(userId,page,pageSize);
        return JsonResult.ok(pagedResult);
    }

    //分页查询所有视频
    @PostMapping("/showAllVideos")
    public JsonResult showAllVideos(Integer page){
        if(page==null)
            page = 1;
        PagedResult pagedResult = wxVideoService.queryAllVideos(page,10);
        return JsonResult.ok(pagedResult);
    }

    //分页查询视频列表
//    @PostMapping(value="/showAll")
//    public JsonResult showAll(@RequestBody Videos video,Integer isSaveRecord,Integer page,Integer pageSize){
//        if(page == null)
//            page = 1;
//        if(pageSize == null)
//            pageSize = 5;
//        PagedResult result = wxVideoService.getAllVideos(video,isSaveRecord,page,pageSize);
//        return JsonResult.ok(result);
//    }




    @PostMapping(value="/hot")
    public JsonResult hot(){
        return JsonResult.ok(wxVideoService.getHotWords());
    }

    @PostMapping(value="/getVideoListByDesc")
    public JsonResult getVideoListByDesc(String videoDesc){
        List<VideosVO> list = wxVideoService.getVideoListByDesc(videoDesc);
        return JsonResult.ok(list);
    }

    @PostMapping(value="/userLike")
    public JsonResult userLike(String userId,String videoId,String videoCreaterId){
        wxVideoService.userLikeVideo(userId,videoId,videoCreaterId);
        return JsonResult.ok("收藏成功！");
    }

    @PostMapping(value="/userUnLike")
    public JsonResult userUnLike(String userId,String videoId,String videoCreaterId){
        wxVideoService.userUnLikeVideo(userId,videoId,videoCreaterId);
        return JsonResult.ok("取消收藏成功！");
    }

    @PostMapping(value="/saveComment")
    public JsonResult saveComment(@RequestBody Comments comment){
        wxVideoService.saveComment(comment);
        return JsonResult.ok();
    }

    @PostMapping("/getVideoComments")
    public JsonResult getVideoComments(String videoId,Integer page){
        if(StringUtils.isBlank(videoId))
            return JsonResult.errorMsg("视频id不能为空");
        if(page == null)
            page = 1;
        Integer pageSize = 5;
        PagedResult pagedResult = wxVideoService.getAllComments(videoId,page,pageSize);
        return JsonResult.ok(pagedResult);
    }
}
