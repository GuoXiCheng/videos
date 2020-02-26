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
                    <a href="#">Bgm管理</a>
                </li>
                <li class="active">
                    添加Bgm
                </li>
            </ul>
            <form role="form" style="width: 200px;" onsubmit="return false" method="post" action="#" id="addBgmForm">
                <div class="form-group">
                    <label for="singer">歌手</label><input type="text" class="form-control" id="singer" placeholder="1-4个字" name="singerName"/>
                </div>
                <div class="form-group">
                    <label for="song">歌曲</label><input type="text" class="form-control" id="song" placeholder="1-50个字" name="songName"/>
                </div>
                <div class="form-group">
                    <label for="file">音乐片段</label><input id="file" type="file" name="file" accept=".mp3" onchange="upload()" formenctype="multipart/form-data"/>
                </div>
                </div> <button type="submit" class="btn btn-success" onclick="mySubmit()">提交</button>
            </form>
        </div>
    </div>
</div>
</body>
<script>
    window.onload=function(){
        $('.bgm-li').addClass('active').children('.nav-left-container-small').slideDown()
    }

    var path = ""
    function upload() {
        var formdata = new FormData();
        console.log($("#file")[0].files[0])
        formdata.append("file",$("#file")[0].files[0])
        $.ajax({
            //几个参数需要注意一下
            type: "POST",//方法类型
            dataType: "json",//预期服务器返回的数据类型
            url: "/videos/video/bgmUpload" ,//url
            data: formdata,
            processData:false,
            contentType:false,
            success: function (result) {
                // console.log(result.data);//打印服务端返回的数据(调试用)
                path = result.data
            },
            error : function() {
                alert("异常！");
            }
        });
    }
    function mySubmit() {
        var singerName = "";
        if($("#singer").val() === ""){
            alert("歌手名不能为空!")
            return;
        }
        else
            singerName = $("#singer").val();
        var songName = "";
        if($("#song").val() === ""){
            alert("歌曲名不能为空")
            return;
        }
        else
            songName = $("#song").val();
        if(path === "") {
            alert("请先选择文件");
            return;
        }
        $.ajax({
            type:"post",
            dataType:"json",
            url:"/videos/video/addBgm",
            data:{
                singerName:singerName,
                songName:songName,
                path:path
            },
            success:function (result) {
                if (result.status == 200 && result.msg == "OK") {
                    alert("上传成功！");
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