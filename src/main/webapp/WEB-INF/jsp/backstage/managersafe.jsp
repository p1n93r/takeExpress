<%--
  Created by IntelliJ IDEA.
  User: 11499
  Date: 2020/4/27
  Time: 11:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="bp" uri="/setBasePath" %>
<html>
<head>
    <title>Title</title>
    <bp:basePath request="<%=request%>"/>
    <link href="static/visitor/css/facelogin/facelogin.css" rel="stylesheet" type="text/css">
    <link href="static/backstage/css/managerSafe.css" rel="stylesheet" type="text/css">
    <link href="static/backstage/css/plugins/sweetalert/sweetalert.css" rel="stylesheet"/>
    <script type="text/javascript" src="static/public/js/jquery.tips.js"></script>
    <script type="text/javascript" src="static/public/js/jquery.cookie.js"></script>
    <script src="static/backstage/js/plugins/sweetalert/sweetalert.min.js"></script>
</head>
<script>
    /*切换页面*/
    $(document).ready(function(){
        var $tab_li = $('#tab-my ul li');
        $tab_li.hover(function(){
            $(this).addClass('selected').siblings().removeClass('selected');
            var index = $tab_li.index(this);
            if (index==1){
                getVideoAndContext();
            }else {
                mediaStreamTrack && mediaStreamTrack.stop();
            }
            $('div.tab_box > div').eq(index).show().siblings().hide();
        });
    });

    //修改密码验证

    function rcheck(){
        if($("#loginid").val().length<6 || $("#loginid").val().length>8){
            $("#loginid").tips({
                side:3,
                msg:'请输入6到8位账号',
                bg:'#AE81FF',
                time:2
            });
            $("#loginid").focus();
            $("#loginid").val('');
            return false;
        }
        if($("#password").val().length<6){
            $("#password").tips({
                side:3,
                msg:'输入密码',
                bg:'#AE81FF',
                time:2
            });
            $("#password").focus();
            return false;
        }
        if($("#password").val()!=$("#passwordcheck").val()){
            $("#passwordcheck").tips({
                side:3,
                msg:'两次密码不相同',
                bg:'#AE81FF',
                time:3
            });
            $("#passwordcheck").focus();
            return false;
        }
        if($("#phone").val().length != 11){
            $("#phone").tips({
                side:3,
                msg:'输入电话格式不对',
                bg:'#AE81FF',
                time:3
            });
            $("#phone").focus();
            return false;
        }
        if ($("#code").val().length !=4) {
            $("#code").tips({
                side : 1,
                msg : '请输入4位验证码',
                bg : '#AE81FF',
                time : 3
            });
            $("#code").focus();
            return false;
        }
        return true;
    }
    function  checkPhone() {
        var phone = $("#phone1").val();//获取学生学号
        if(phone.length!=11 || phone ==''){
            $("#phone1").tips({
                side : 2,
                msg : '手机号格式不正确',
                bg : '#AE81FF',
                time : 3
            });
            //showfh();
            $("#phone1").focus();
            return false;
        }//post里面三个参数url，json键值对，function（data）：data用于接收后台传回的值
        var bool = false;
        $.ajax({
            type:"post",
            url:"./admin/modifyphonecheck",
            data:{"phone":phone},
            async:false,
            success:function(data){
                if(data.status==400){
                    $("#phone1").tips({
                        side : 2,
                        msg : '手机号已注册',
                        bg : '#AE81FF',
                        time : 3
                    });
                    bool = false;
                    $("#phone").focus();
                    return false;
                }
                bool = true;
                return true;
            }
        });
        return bool;
    }
    //获取验证码
    //修改密码获取验证码
    function yunpiancode() {
        var phone = $("#phone").val();
        console.log(phone);
        if (phone.length != 11 || phone == '') {
            $("#phone").tips({
                side: 2,
                msg: '手机号格式不正确',
                bg: '#AE81FF',
                time: 3
            });
            //showfh();
            $("#phone").focus();
            return false;
        }
        time = 60;
        var btn = $("#getcode");
        btn.attr("disabled", true);  //按钮禁止点击
        btn.val(time <= 0 ? "获取验证码" : ("" + (time) + "秒后可发送"));
        var hander = setInterval(function () {
            if (time <= 0) {
                clearInterval(hander); //清除倒计时
                btn.val("发送动态密码");
                btn.attr("disabled", false);
                return false;
            } else {
                btn.val("" + (time--) + "秒后可发送");
            }
        }, 1000);
        $.ajax({
            url: "./getcode.do",
            type: "post",
            data: {"phone": phone},
            success: function (data) {
                if (data != null) {
                    console.log(data.msg);
                    $("#code").tips({
                        side: 2,
                        msg: data.data,
                        bg: '#AE81FF',
                        time: 3
                    });
                    // showfh();
                    $("#code").focus();
                    return true;
                }
            }
        })

    }
    //绑定手机号获取验证码
    function yunpiancode1() {
        if (checkPhone()) {
            var phone = $("#phone").val();
            console.log(phone);
            if (phone.length != 11 || phone == '') {
                $("#phone").tips({
                    side: 2,
                    msg: '手机号格式不正确',
                    bg: '#AE81FF',
                    time: 3
                });
                //showfh();
                $("#phone").focus();
                return false;
            }
            time = 60;
            var btn = $("#getcode");
            btn.attr("disabled", true);  //按钮禁止点击
            btn.val(time <= 0 ? "获取验证码" : ("" + (time) + "秒后可发送"));
            var hander = setInterval(function () {
                if (time <= 0) {
                    clearInterval(hander); //清除倒计时
                    btn.val("发送动态密码");
                    btn.attr("disabled", false);
                    return false;
                } else {
                    btn.val("" + (time--) + "秒后可发送");
                }
            }, 1000);
            $.ajax({
                url: "./getcode.do",
                type: "post",
                data: {"phone": phone},
                async:false,
                success: function (data) {
                    if (data != null) {
                        console.log(data.msg);
                        $("#code").tips({
                            side: 2,
                            msg: data.data,
                            bg: '#AE81FF',
                            time: 3
                        });
                        // showfh();
                        $("#code").focus();
                        return true;
                    }
                }
            })
        }
        return false;

    }
    function modifyPassword() {
        if (rcheck()){
            var loginid = $("#loginid").val();
            var password = $("#password").val();
            var passwordcheck = $("#passwordcheck").val();
            var phone = $("#phone").val();
            var code = $("#code").val();
            $.ajax({
                type:"POST",
                url:"./admin/modifyPassword",
                data:{"loginid":loginid,"password":password,"passwordcheck":passwordcheck,"phone":phone,"code":code},
                beforeSend:function()
                {
                    $("#tip").html("<span style='color:blue'>正在处理...</span>");
                    return true;
                },
                success:function(data){
                    if (data.status==200){
                        swal("密码修改成功","请继续操作","success");
                        $("#closemodify").click();
                    }else {
                        var msg = data.msg
                        swal(msg,"请继续操作","error");
                        return false;
                    }
                }
            });
        }

    }

    //打开摄像头
    var mediaStreamTrack;
    function getVideoAndContext() {
        //var 是定义变量
        var video = document.getElementById("video"); //获取video标签
        var con  ={
            audio:false,
            video:{
                width:1980,
                height:1024,
            }
        };

        if(navigator.mediaDevices.getUserMedia){
            //最新标准API
            navigator.mediaDevices.getUserMedia(con)
                .then(function(stream){
                    video.srcObject = stream;
                    mediaStreamTrack =stream.getVideoTracks()[0];
                    video.onloadmetadate = function(e){
                        video.play();
                    }
                });
        } else if (navigator.webkitGetUserMedia){
            //webkit内核浏览器
            navigator.mediaDevices.getUserMedia(con)
                .then(function(stream){
                    video.srcObject = stream;
                    mediaStreamTrack =stream.getVideoTracks()[0];
                    video.onloadmetadate = function(e){
                        video.play();
                        alert()
                    }
                });
        } else if (navigator.mozGetUserMedia){
            navigator.mediaDevices.getUserMedia(con)
                .then(function(stream){
                    video.srcObject = stream;
                    mediaStreamTrack =stream.getVideoTracks()[0];
                    video.onloadmetadate = function(e){
                        video.play();
                    }
                });
        } else if (navigator.getUserMedia){
            navigator.mediaDevices.getUserMedia(con)
                .then(function(stream){
                    video.srcObject = stream;
                    mediaStreamTrack =stream.getVideoTracks()[0];
                    video.onloadmetadate = function(e){
                        video.play();
                    }
                });
        }else {
            swal("你的浏览器不支持访问用户媒体设备","请继续操作","error");
        }
    }
    //关闭摄像头,

    $(function () {
        var close = document.getElementById("close");
        close.addEventListener('click', function() {
            mediaStreamTrack && mediaStreamTrack.stop();
        }, false);
    });
    //人脸识别认证
    function query(){
        //把流媒体数据画到convas画布上去
        var context = canvas.getContext("2d");
        context.drawImage(video,0,0,400,300);
        var base = getBase64();
        $.ajax({
            type:"post",
            url:"./admin/bindface",
            data:{"img":base},
            beforeSend:function()
            {
                $("#tip").html("<span style='color:blue'>正在处理...</span>");
                return true;
            },
            success:function(data){
                if(data.status == 200){
                    swal("人脸绑定成功","请继续操作","success");
                    mediaStreamTrack && mediaStreamTrack.stop();
                    $("#close").click();
                } else {
                    alert(data.msg)
                }
            }
        });
    }
    function getBase64() {
        var imgSrc = document.getElementById("canvas").toDataURL(
            "image/png");
        return imgSrc.split("base64,")[1];

    };
    //修改绑定手机号
    function modifyphone(){
        var managerid = ${requestScope.manager.managerId};
        $.ajax({
            type:"POST",
            url:"./admin/modifyphone",
            data:{"managerid":managerid,"phone":$("#phone").val(),"code":$("#code").val()},
            beforeSend:function()
            {
                $("#tip").html("<span style='color:blue'>正在处理...</span>");
                return true;
            },
            success:function (data) {
                if (data.status==200){
                    swal("修改成功","请继续操作","success");
                } else {
                    var msga = data.msg
                    swal(msga,"请继续操作","error");

                }
            }
        })
    }
</script>
<script type="text/javascript">
    $(function(){
        $(".screenbg ul li").each(function(){
            $(this).css("opacity","0");
        });
        $(".screenbg ul li:first").css("opacity","1");
        var index = 0;
        var t;
        var li = $(".screenbg ul li");
        var number = li.size();
        function change(index){
            li.css("visibility","visible");
            li.eq(index).siblings().animate({opacity:0},3000);
            li.eq(index).animate({opacity:1},3000);
        }
        function show(){
            index = index + 1;
            if(index<=number-1){
                change(index);
            }else{
                index = 0;
                change(index);
            }
        }
        t = setInterval(show,8000);
        //根据窗口宽度生成图片宽度
        var width = $(window).width();
        $(".screenbg ul img").css("width",width+"px");
    });
</script>
<body>

    <div style="width: 99.2%;margin-left: 5px" class="row wrapper border-bottom white-bg page-heading" >
        <div class="col-lg-10">
            <ol class="breadcrumb">
                <li>
                    <a >Home</a>
                </li>
                <li>
                    <a>个人中心信息管理</a>
                </li>
                <li class="active">
                    <strong>账户安全管理</strong>
                </li>
            </ol>
        </div>
        <div class="col-lg-2"></div>
    </div>
    <div class="container">
        <div class="row">
            <div class="tab-my" id = "tab-my">
                <!-- 导航菜单 -->
                <ul class="tab_menu">
                    <li class="selected">修改密码</li>
                    <li>绑定人脸识别</li>
                    <li>绑定手机号</li>
                </ul>
                <div class="tab_box">
                    <!-- 修改密码 -->
                    <div class="col-md-9 updatePwd" style="margin-top: 80px;width: 70%;margin-left: 10%">
                        <form class="" method="post" id="modifyform">
                            <div class="form-group" style="padding-bottom: 50px">
                                <label for="loginid" class="col-sm-3 control-label">账号</label>
                                <div class="col-sm-9">
                                    <input type="text" readonly="readonly" class="form-control" id="loginid" name="loginid" value="${requestScope.manager.loginId}"
                                           placeholder="请输入用户ID">
                                </div>
                            </div>

                            <div class="form-group" style="padding-bottom: 50px">
                                <label for="password" class="col-sm-3 control-label">新密码</label>
                                <div class="col-sm-9">
                                    <input type="password" class="form-control"   name="manager_name" value="" id="password"
                                           placeholder="输入更改密码">
                                </div>
                            </div>
                            <div class="form-group" style="padding-bottom: 50px">
                                <label for="passwordcheck" class="col-sm-3 control-label">确定密码</label>
                                <div class="col-sm-9">
                                    <input type="password" class="form-control" id="passwordcheck" name="passwordcheck" value=""
                                           placeholder="确定密码">
                                </div>
                            </div>
                            <div class="form-group" style="padding-bottom: 50px">
                                <label for="phone" class="col-sm-3 control-label">手机号</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" readonly="readonly" name="phone" value="${requestScope.manager.phone}" id="phone"
                                           placeholder="手机号">
                                </div>
                            </div>
                            <div class="form-group" style="padding-bottom: 50px">
                                <label for="code" class="col-sm-3 control-label">验证码</label>
                                <div class="col-sm-9">
                                    <input class="" type="text" name="code" id="code" placeholder="   请输入验证码" required="" style="width: 70%;padding-left: 15px">
                                    <span><input  type="button" class="btn-success"  value="获取验证码" onclick="yunpiancode();" id="getcode" name="getcode" style="width: 27%;" ></span>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-primary" onclick="modifyPassword();">
                                    提交
                                </button><span id="tip"> </span>
                            </div>
                        </form>
                    </div>
                    <!-- 绑定人脸识别 -->
                    <div class="col-md-12 bindFace" style="display: none;background-color: white;width: 70%;margin-left: 10%">
                        <form class="form-horizontal" role="form" method="post">
                            <dl class="admin_login">
                                <dt>
                                    <strong>人脸识别</strong>
                                </dt>
                                <div id="media">
                                    <video id="video" style="width: 100%;height: 100%" autoplay></video>
                                    <canvas id="canvas" width="400" height="300"></canvas>
                                </div>
                                <dd>
                                    <input type="button" onclick="query()" value="立即绑定" class="submit_btn btn-success" />
                                </dd>
                            </dl>
                        </form>
                    </div>
                    <!-- 绑定手机号 -->
                    <div class="col-md-12 bindPhone" style="display: none;width: 70%;height:400px;margin-left: 10%;margin-top: 80px;">
                        <form class="form-horizontal" method="post" id="modifyphoneform">
                            <div class="form-group">
                                <label for="phone1" class="col-sm-3 control-label" id="phone2">手机号</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" onblur="checkPhone();" name="phone1" value="${requestScope.manager.phone}" id="phone1"
                                           placeholder="手机号">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="code" class="col-sm-3 control-label">验证码</label>
                                <div class="col-sm-9">
                                    <input class="" type="text" name="code" id="code" placeholder="   请输入验证码" required="" style="width: 70%;padding-left: 15px">
                                    <span><input  type="button" class="btn-success"  value="获取验证码" onclick="yunpiancode1();" id="getcode" name="getcode" style="width: 27%;" ></span>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-primary" onclick="modifyphone();">
                                    提交
                                </button><span id="tip"> </span>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="screenbg">
        <ul>
            <li><a href="javascript:;"><img src="static/backstage/img/1.jpg"></a></li>
            <li><a href="javascript:;"><img src="static/backstage/img/0.jpg"></a></li>
        </ul>
    </div>
</body>
</html>
