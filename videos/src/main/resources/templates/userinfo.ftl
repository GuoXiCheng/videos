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
                    <a href="#">用户信息</a>
                </li>
                <li class="active">
                    用户列表
                </li>
            </ul>
            <table class="table table-hover table-bordered table-condensed">
                <caption><strong>短视频用户列表</strong></caption>
                <thead>
                <tr>
                    <th>序号</th>
                    <th>头像</th>
                    <th>用户名</th>
                    <th>昵称</th>
                    <th>粉丝数</th>
                    <th>关注数</th>
                    <th>获赞数</th>
                </tr>
                </thead>
                <tbody>
                <#list result.rows as row>
                    <tr>
                        <td>${row_index + (result.page-1)*5+1}</td>
                        <td><img src="${row.faceImage}" width="50px"></td>
                        <td>${row.username}</td>
                        <td>${row.nickname}</td>
                        <td>${row.fansCounts}</td>
                        <td>${row.followCounts}</td>
                        <td>${row.receiveLikeCounts}</td>
                    </tr>
                </#list>
                </tbody>
            </table>
        </div>
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
                        <a href="https://www.guoxicheng.top/videos/users/showList?page=${result.page-1}">上一页</a>
                    </li>
                </#if>

                    <#list 1..result.totalPage as index>
                        <#if result.page == index>
                            <li class="disabled"><a href="#">${index}</a></li>
                        <#else>
                            <li><a href="https://www.guoxicheng.top/videos/users/showList?page=${index}">${index}</a></li>
                        </#if>
                    </#list>
                <#if result.page gte result.totalPage>
                    <li class="disabled">
                        <a href="#">下一页</a>
                    </li>
                <#else>
                    <li>
                        <a href="https://www.guoxicheng.top/videos/users/showList?page=${result.page+1}">下一页</a>
                    </li>
                </#if>
            </ul>
        </div>
    </div>
</div>

</body>
</html>