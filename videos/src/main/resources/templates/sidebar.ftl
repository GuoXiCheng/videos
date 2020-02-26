<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>短视频后台管理</title>
    <link href="https://cdn.bootcss.com/twitter-bootstrap/3.0.1/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="../static/css/base.css">
    <link rel="stylesheet" href="../static/css/jquery-kq-nav-left.css">
    <script type="text/javascript" src="https://www.jq22.com/jquery/jquery-1.10.2.js"></script>
</head>

<body>
<ul class="nav-left-container">
    <!-- li的class为active表示点击选中 -->
    <!-- aria-hidden字符串。可选值为true和false,true表示元素隐藏(不可见)，false表示元素可见。 -->
    <!-- aria-expanded表示展开状态。默认为undefined, 表示当前展开状态未知。其它可选值：true表示元素是展开的；false表示元素不是展开的。 -->
    <li>

        <a href="#">

            <span class="glyphicon f12" aria-hidden="true"></span>
            <span>短视频后台管理系统</span>
            <span class="glyphicon glyphicon-menu-right f12 fr" aria-hidden="true"></span>
        </a>
    </li>
    <li>
        <a href="https://www.guoxicheng.top/videos/users/showList">
            <span class="glyphicon glyphicon-user f12" aria-hidden="true"></span>
            <span>用户信息</span>
            <span class="glyphicon glyphicon-menu-right f12 fr" aria-hidden="true"></span>
        </a>
    </li>
    <li class="bgm-li">
        <a href="#">
            <span class="glyphicon glyphicon-music f12" aria-hidden="true"></span>
            <span>Bgm管理</span>
            <span class="glyphicon glyphicon-menu-right f12 fr" aria-hidden="true"></span>
        </a>
        <ul class="nav-left-container-small">
            <li>
                <a class="J_menuItem" href="https://www.guoxicheng.top/videos/video/queryBgmList">bgm列表</a>
            </li>
            <li>
                <a class="J_menuItem" href="https://www.guoxicheng.top/videos/video/showAddBgm">添加bgm</a>
            </li>
        </ul>
    </li>
    <li>
        <a href="https://www.guoxicheng.top/videos/video/reportList">
            <span class="glyphicon glyphicon-exclamation-sign f12" aria-hidden="true"></span>
            <span>举报管理</span>
            <span class="glyphicon glyphicon-menu-right f12 fr" aria-hidden="true"></span>
        </a>
    </li>
    <li>
        <a href="https://www.guoxicheng.top/videos/users/logout">
            <span class="glyphicon glyphicon-log-out f12" aria-hidden="true"></span>
            <span>退出登录</span>
            <span class="glyphicon glyphicon-menu-right f12 fr" aria-hidden="true"></span>
        </a>
    </li>
</ul>
<script>
    $('.nav-left-container').on('click', 'li', function() {
        $(this).find('.glyphicon-menu-right').removeClass('glyphicon-menu-right').addClass('glyphicon-menu-down');
        $(this).addClass('active').children('.nav-left-container-small').slideDown()

        $(this).siblings().removeClass('active').children('.nav-left-container-small').slideUp()
        $(this).siblings().find('.glyphicon-menu-down').removeClass('glyphicon-menu-down').addClass('glyphicon-menu-right ');
    })
</script>
</body>

</html>