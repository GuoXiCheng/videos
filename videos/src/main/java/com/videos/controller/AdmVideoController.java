package com.videos.controller;

import com.videos.Utils.JsonResult;
import com.videos.Utils.KeyUtil;
import com.videos.Utils.PagedResult;
import com.videos.pojo.Bgm;
import com.videos.service.AdmVideoService;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.*;
import java.util.Map;

@Controller
@RequestMapping("/video")
public class AdmVideoController {

    @Autowired(required = false)
    private AdmVideoService admVideoService;


    //添加bgm页面
    @GetMapping("/showAddBgm")
    public ModelAndView showAddBgm(){
        return new ModelAndView("showAddBgm");
    }


    //上传bgm
    @PostMapping("/bgmUpload")
    @ResponseBody
    public JsonResult bgmUpload(@RequestParam("file") MultipartFile[] files) throws IOException {
        //文件保存的命名空间
        String fileSpace = "C:/guoxicheng_videos_dev";
        //保存数据到数据库中的相对路径
        String uploadPathDB = "/bgm";

        FileOutputStream fileOutputStream = null;
        InputStream inputStream;

        try{
            if(files != null){
                String fileName = files[0].getOriginalFilename();
                if(StringUtils.isNotBlank(fileName)){
                    //文件上传的最终保存路径
                    String finalPath = fileSpace + uploadPathDB + "/" + fileName;
                    //设置数据库保存的路径
                    uploadPathDB += ("/" + fileName);

                    File outFile = new File(finalPath);
                    if(outFile.getParentFile() != null || !outFile.getParentFile().isDirectory()){
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
        return JsonResult.ok(uploadPathDB);
    }


    //提交保存bgm
    @PostMapping("/addBgm")
    @ResponseBody
    public JsonResult addBgm(String singerName,String songName,String path){
        Bgm bgm = new Bgm();
        String bgmId = KeyUtil.genUniqueKey();
        bgm.setId(bgmId);
        bgm.setAuthor(singerName);
        bgm.setName(songName);
        bgm.setPath(path);
        admVideoService.addBgm(bgm);
        return JsonResult.ok();
    }

    //分页查询bgm
    @GetMapping("/queryBgmList")
    public ModelAndView queryBgmList(Integer page, Map<String,Object> map){
        if(page ==null || page <=0)
            page = 1;
        PagedResult result = admVideoService.queryBgmList(page,5);
        map.put("result",result);
        return new ModelAndView("bgmList",map);
    }

    //删除bgm
    @PostMapping("/delBgm")
    @ResponseBody
    public JsonResult delBgm(@RequestParam("bgmId") String bgmId){
        admVideoService.deleteBgm(bgmId);
        return JsonResult.ok();
    }

    //举报列表
    @GetMapping("/reportList")
    public ModelAndView reportList(Integer page,Map<String,Object> map){
        if(page == null || page <= 0)
            page = 1;
        PagedResult result = admVideoService.queryReportList(page,5);
        map.put("result",result);
        return new ModelAndView("reportList",map);
    }

    @PostMapping("/forbidVideo")
    @ResponseBody
    public JsonResult forbidVideo(@RequestParam("videoId") String videoId,@RequestParam("statusCode") Integer statusCode){
        admVideoService.updateVideoStatus(videoId, statusCode);
        return JsonResult.ok();
    }


}


