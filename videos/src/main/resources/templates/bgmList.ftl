<html>
<#include "sidebar.ftl">
<body style="padding-left: 250px;padding-top:20px"></body>
<div class="container-fluid">
    <div class="row-fluid">
        <div class="span12">
            <ul class="breadcrumb">
                <li>
                    <a href="#">短视频后台管理系统</a>
                </li>
                <li>
                    <a href="#">Bgm管理</a>
                </li>
                <li class="active">
                    bgm列表
                </li>
            </ul>
            <table class="table table-hover table-bordered table-condensed">
                <caption><strong>所有bgm列表</strong></caption>
                <thead>
                <tr>
                    <th>序号</th>
                    <th>ID</th>
                    <th>歌曲名称</th>
                    <th>作者</th>
                    <th>保存路径</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <#list result.rows as row>
                <tr>
                    <td>${row_index + (result.page-1)*5+1}</td>
                    <td>${row.id}</td>
                    <td>${row.author}</td>
                    <td>${row.name}</td>
                    <td><a href="http://192.168.129.1:8080/mvc-bgm/${row.path}" style="color:deepskyblue">点我播放</a></td>
                    <td><a onclick="delBgm(this)" href="#" style="border:solid 1px">删除</a></td>
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
                            <li><a href="http://192.168.129.1:8080/video/queryBgmList?page=${index}">${index}</a></li>
                        </#if>
                    </#list>
                <#if result.page gte result.total>
                    <li class="disabled">
                        <a href="#">下一页</a>
                    </li>
                <#else>
                    <li>
                        <a href="http://192.168.129.1:8080/video/queryBgmList?page=${result.page+1}">下一页</a>
                    </li>
                </#if>
                </ul>
            </div>
        </div>
    </div>
<script>
    window.onload=function(){
        $('.bgm-li').addClass('active').children('.nav-left-container-small').slideDown()
    }
    function delBgm(node) {
        var result = window.confirm("您确定要删除吗？");
        if(!result)
            return;
        var bgmId = node.parentNode.parentNode.childNodes[3].innerText;
        var formdata = new FormData();
        formdata.append("bgmId",bgmId);
        $.ajax({
            type:"post",
            dataType:"json",
            url:"/video/delBgm",
            data:formdata,
            processData:false,
            contentType:false,
            success:function (result) {
                if (result.status == 200 && result.msg == "OK") {
                    alert("删除成功！");
                    window.location.reload();
                }
            },
            error:function () {
                alert("异常")
            }
        })
    }
</script>
</html>