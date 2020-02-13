<html>
<head>
    <meta charset="utf-8">
    <link href="https://cdn.bootcss.com/twitter-bootstrap/3.0.1/css/bootstrap.min.css" rel="stylesheet">
    <script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
    <title>登录</title>
</head>
<body style="padding-top: 200px">
    <h3 style="padding-left: 700px">短视频后台管理</h3>
    <div class="container" style="width:500px">
        <div class="row clearfix">
            <div class="col-md-12 column">
                <form id="loginForm" class="form-horizontal" onsubmit="return false" role="form" method="post" action="#">
                    <div class="form-group">
                        <label for="inputEmail3" class="col-sm-2 control-label">用户名</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="inputEmail3" name="username" value="guo"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputPassword3" class="col-sm-2 control-label">密码</label>
                        <div class="col-sm-10">
                            <input type="password" class="form-control" id="inputPassword3" name="password" value="vaijuaefiaowjefijaa"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="submit" class="btn btn-primary" onclick="login()">Login in</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
<script>
    function login() {
        $.ajax({
            //几个参数需要注意一下
            type: "POST",//方法类型
            dataType: "json",//预期服务器返回的数据类型
            url: "/users/login" ,//url
            data: $('#loginForm').serialize(),
            success: function (result) {
                console.log(result);//打印服务端返回的数据(调试用)
                if (result.status == 200 && result.msg == "OK") {
                    window.location.href = "http://192.168.129.1:8080/users/showList";
                }
            },
            error : function() {
                alert("异常！");
            }
        });
    }
</script>

</html>