package com.guo.videos.dev.controller;

import com.guo.videos.Utils.JsonResult;
import com.guo.videos.dev.pojo.Users;
import com.guo.videos.dev.pojo.UsersReport;
import com.guo.videos.dev.pojo.vo.PublisherVideoVO;
import com.guo.videos.dev.pojo.vo.UsersVO;
import com.guo.videos.dev.service.UserService;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    //上传用户头像
    @PostMapping("/uploadFace")
    public JsonResult uploadFace(String userId, @RequestParam("file") MultipartFile[] files){

        if(StringUtils.isBlank(userId)){
            return JsonResult.errorMsg("用户id不能为空");
        }

        //文件保存的命名空间
        String fileSpace = "C:/guoxicheng_videos_dev";

        //保存到数据库中的相对路径
        String uploadPathDB = "/" + userId + "/face";
        FileOutputStream fileOutputStream = null;
        InputStream inputStream;
        try{
            if(files != null && files.length > 0){
                String fileName = files[0].getOriginalFilename();
                if(StringUtils.isNotBlank(fileName)){
                    //文件上传的最终保存路径
                    String finalFacePath = fileSpace + uploadPathDB + "/" + fileName;
                    //设置数据库保存的路径
                    uploadPathDB = uploadPathDB + ("/" + fileName);

                    File outFile = new File(finalFacePath);
                    if(outFile.getParentFile() !=null || !outFile.getParentFile().isDirectory()){
                        //创建父文件夹
                        outFile.getParentFile().mkdirs();
                    }
                    fileOutputStream = new FileOutputStream(outFile);
                    inputStream = files[0].getInputStream();
                    IOUtils.copy(inputStream,fileOutputStream);
                }
            }else{
                return JsonResult.errorMsg("上传出错！");
            }
        }catch(IOException e){
            e.printStackTrace();
            return JsonResult.errorMsg("上传出错！");
        }finally {
            if(fileOutputStream != null){
                try {
                    fileOutputStream.flush();
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        Users user = new Users();
        user.setId(userId);
        user.setFaceImage(uploadPathDB);
        userService.updateUserInfo(user);
        return JsonResult.ok(uploadPathDB);
    }

    @GetMapping("/queryUserInfo")
    public JsonResult queryUserInfo(String userId){
        if(StringUtils.isBlank(userId))
            return JsonResult.errorMsg("用户id不能为空");
        Users userInfo = userService.queryUserInfo(userId);
        return JsonResult.ok(userInfo);
    }

    //从数据库查询用户信息
    @PostMapping("/query")
    public JsonResult query(String userId,String fanId){
        if(StringUtils.isBlank(userId))
            return JsonResult.errorMsg("用户id不能为空");

        Users userInfo = userService.queryUserInfo(userId);

        UsersVO usersVO = new UsersVO();
        BeanUtils.copyProperties(userInfo,usersVO);
        usersVO.setFollow(userService.queryIfFollow(userId,fanId));
        return JsonResult.ok(usersVO);
    }

    @PostMapping("/queryPublisher")
    public JsonResult queryPublisher(String loginUserId,String videoId,String publisherId){
        if(StringUtils.isBlank(publisherId))
            return JsonResult.errorMsg("发布者id不能为空");

        //1.查询视频发布者信息
        Users userInfo = userService.queryUserInfo(publisherId);
        UsersVO publisher = new UsersVO();
        BeanUtils.copyProperties(userInfo,publisher);

        //2.查询当前登录者和视频点赞关系
        boolean userLikeVideo = userService.isUserLikeVideo(loginUserId,videoId);

        PublisherVideoVO bean = new PublisherVideoVO();

        bean.setPublisher(publisher);
        bean.setUserLikeVideo(userLikeVideo);

        return JsonResult.ok(bean);
    }

    @PostMapping("/queryIsLikeVideo")
    public JsonResult queryIsLikeVideo(String userId,String videoId){
        boolean isUserLikeVideo = userService.isUserLikeVideo(userId,videoId);
        return JsonResult.ok(isUserLikeVideo);
    }

    @PostMapping("/beyourfans")
    public JsonResult beyourfans(String userId,String fanId){
        if(StringUtils.isBlank(userId) || StringUtils.isBlank(fanId))
            return JsonResult.errorMsg("用户或粉丝id不能为空");
        userService.saveUserFanRelation(userId,fanId);
        return JsonResult.ok("关注成功!");
    }

    @PostMapping("/dontbeyourfans")
    public JsonResult dontbeyourfans(String userId,String fanId){
        if(StringUtils.isBlank(userId) || StringUtils.isBlank(fanId))
            return JsonResult.errorMsg("用户或粉丝id不能为空");
        userService.deleteUserFanRelation(userId,fanId);
        return JsonResult.ok("取消关注成功!");
    }

    @PostMapping("/reportUser")
    public JsonResult reportUser(@RequestBody UsersReport usersReport){
        //保存举报信息
        userService.reportUser(usersReport);

        return JsonResult.ok("举报成功");
    }
}
