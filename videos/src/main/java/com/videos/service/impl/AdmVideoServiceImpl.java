package com.videos.service.impl;

import com.videos.Utils.PagedResult;
import com.videos.mapper.BgmAdminMapper;
import com.videos.mapper.VideoAdminMapper;
import com.videos.pojo.Bgm;
import com.videos.service.AdmVideoService;
import com.videos.vo.Reports;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdmVideoServiceImpl implements AdmVideoService {

    @Autowired(required = false)
    private BgmAdminMapper bgmAdminMapper;

    @Autowired(required = false)
    private VideoAdminMapper videoAdminMapper;

    //添加bgm
    @Override
    @Transactional(propagation= Propagation.REQUIRED)
    public void addBgm(Bgm bgm) {
        bgmAdminMapper.addBgm(bgm);
    }

    //查询bgm列表
    @Override
    @Transactional(propagation= Propagation.SUPPORTS)
    public PagedResult queryBgmList(Integer page, Integer pageSize) {
        List<Bgm> bgmList = bgmAdminMapper.selectAll((page-1)*pageSize,pageSize);

        Integer Records = bgmAdminMapper.selectCount();
        Integer totalPage = Records % pageSize ==0 ? (Records / pageSize) : ((Records / pageSize) + 1);
        PagedResult pagedResult = new PagedResult();
        pagedResult.setPage(page);
        pagedResult.setRecords(Records);
        pagedResult.setRows(bgmList);
        pagedResult.setTotalPage(totalPage);

        return pagedResult;
    }

    //删除bgm
    @Override
    @Transactional(propagation= Propagation.REQUIRED)
    public void deleteBgm(String bgmId) {
        bgmAdminMapper.deleteBgm(bgmId);
    }

    //查询视频列表
    @Override
    @Transactional(propagation= Propagation.SUPPORTS)
    public PagedResult queryReportList(Integer page, Integer pageSize) {
        List<Reports> reportsList = videoAdminMapper.queryReportList((page-1)*pageSize,pageSize);
        PagedResult result = new PagedResult();

        Integer Records = videoAdminMapper.selectCounts();
        Integer total = Records % pageSize ==0 ? (Records / pageSize) : ((Records / pageSize) + 1);

        result.setPage(page);
        result.setRecords(Records);
        result.setTotalPage(total);
        result.setRows(reportsList);
        return result;
    }

    //更新视频状态
    @Override
    @Transactional(propagation= Propagation.REQUIRED)
    public void updateVideoStatus(String videoId, Integer statusCode) {
        videoAdminMapper.updateVideoStatus(videoId,statusCode);
    }

}
