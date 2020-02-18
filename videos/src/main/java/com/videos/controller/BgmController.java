package com.videos.controller;

import com.videos.Utils.JsonResult;
import com.videos.service.BgmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bgm")
public class BgmController {

    @Autowired
    private BgmService bgmService;

    @PostMapping("/list")
    public JsonResult list() {
        return JsonResult.ok(bgmService.queryBgmList());
    }
}
