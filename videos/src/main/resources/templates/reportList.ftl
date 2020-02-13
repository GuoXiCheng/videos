<html>
<#include "sidebar.ftl">
<body style="padding-left: 250px;padding-top:20px">
<div class="container-fluid">
    <div class="row-fluid">
        <div class="span12">
            <ul class="breadcrumb">
                <li>
                    <a href="#">短视频后台管理系统</a>
                </li>
                <li>
                    <a href="#">举报管理</a>
                </li>
                <li class="active">
                    举报列表
                </li>
            </ul>
            <table class="table table-hover table-bordered table-condensed">
                <caption><strong>举报列表</strong></caption>
                <thead>
                <tr>
                    <th>序号</th>
                    <th>ID</th>
                    <th>举报类型</th>
                    <th>举报内容</th>
                    <th>被举报人</th>
                    <th>被举报视频id</th>
                    <th>被举报视频</th>
                    <th>视频状态</th>
                    <th>提交用户</th>
                    <th>举报日期</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <#list result.rows as row>
                <tr>
                    <td>${row_index + (result.page-1)*5+1}</td>
                    <td>${row.id}</td>
                    <td>${row.title}</td>
                    <td>${row.content}</td>
                    <td>${row.dealUsername}</td>
                    <td>${row.dealVideoId}</td>
                    <td><a href="http://192.168.129.1:8080/${row.videoPath}" style="color:deepskyblue">点我播放</a></td>
                    <#if row.status == 0>
                        <td>正常</td>
                    <#else>
                        <td>禁播</td>
                    </#if>
                    <td>${row.submitUsername}</td>
                    <td>${row.createDate?string('yyyy-MM-dd HH:mm:ss')}</td>
                    <#if row.status == 0>
                        <td><a onclick="videoAction(this)" href="#" style="border:1px solid">禁止播放</a></td>
                    <#else>
                        <td><a onclick="videoAction(this)" href="#" style="border:1px solid">允许播放</a></td>
                    </#if>

                </tr>
                </#list>
                </tbody>
            </table>
        </div>
        <div class="row clearfix">
            <div class="col-md-12 column">
                <ul class="pagination">
                <#if result.page lte 1>
                    <li class="disabled">
                        <a href="#">上一页</a>
                    </li>
                <#else>
                    <li>
                        <a href="http://192.168.129.1:8080/video/queryBgmList?page=${result.page-1}">上一页</a>
                    </li>
                </#if>

                    <#list 1..result.total as index>
                        <#if result.page == index>
                            <li class="disabled"><a href="#">${index}</a></li>
                        <#else>
                            <li><a href="http://192.168.129.1:8080/video/reportList?page=${index}">${index}</a></li>
                        </#if>
                    </#list>
                <#if result.page gte result.total>
                    <li class="disabled">
                        <a href="#">下一页</a>
                    </li>
                <#else>
                    <li>
                        <a href="http://192.168.129.1:8080/video/reportList?page=${result.page+1}">下一页</a>
                    </li>
                </#if>
                </ul>
            </div>
        </div>
    </div>
</div>
<script>
function videoAction(node) {
    var videoId = node.parentNode.parentNode.childNodes[11].innerText;
    var videoStatus = node.parentNode.parentNode.childNodes[15].innerText;
    var statusCode = 0;
    if(videoStatus === "正常")
        statusCode = 1;

    $.ajax({
        type:"post",
        dataType:"json",
        url:"/video/forbidVideo",
        data:{
            videoId:videoId,
            statusCode:statusCode
        },
        success:function (result) {
            if (result.status == 200 && result.msg == "OK") {
                window.location.reload();
                alert("操作成功！")
            }
        },
        error:function () {
            alert("异常");
        }
    })
}
</script>
</body>
</html>