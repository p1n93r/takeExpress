<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="bp" uri="/setBasePath" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <bp:basePath request="<%=request%>"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>管理员登录</title>
    <link href="static/visitor/css/facelogin/facelogin.css" rel="stylesheet">
    <link rel="stylesheet" href="static/public/css/bootstrap.min.css" />
    <link rel="stylesheet" href="static/visitor/css/login.css" />
    <link href="static/backstage/css/plugins/sweetalert/sweetalert.css" rel="stylesheet"/>
    <script type="text/javascript" src="static/public/js/jquery-1.12.3.min.js"></script>
    <script type="text/javascript" src="static/visitor/js/login.js"></script>
    <script type="text/javascript" src="static/public/js/jquery.tips.js"></script>
    <script type="text/javascript" src="static/public/js/jquery.cookie.js"></script>
    <script src="static/backstage/js/plugins/sweetalert/sweetalert.min.js"></script>
</head>
<style>
    #code{
        font-size:12px;
    }


</style>
<body class="bg-gradient-primary">
    <div class="container">
    <!-- Outer Row -->
    <div class="row justify-content-center">

        <div class="col-xl-10 col-lg-12 col-md-9">

            <div class="card o-hidden border-0 shadow-lg my-5">
                <div class="card-body p-0">
                    <!-- Nested Row within Card Body -->
                    <div class="row">
                        <div class="col-lg-6 d-none d-lg-block bg-login-image" >
                            <div style="text-align: center;padding-top: 40%">项目内容介绍</div>
                        </div>
                        <div class="col-lg-6">
                            <div class="p-5" >
                                <div class="text-center">
                                    <h1 class="h4 text-gray-900 mb-4">欢迎回来!</h1>
                                </div>
                                <div id="loginbox">
                                <div id="login1">
                                    <form class="user" id="loginForm" name="loginForm" onsubmit="return false;">
                                        <div class="form-group">
                                            <input type="text" class="form-control form-control-user table_input" id="id" name="id" placeholder="  请输入账号" onkeydown="keyLogin()">
                                        </div>
                                        <div class="form-group">
                                            <input type="password" class="form-control form-control-user table_input" id="password" name="password" placeholder="请输入密码" onkeydown="keyLogin()">
                                        </div>
                                        <div class="form-group">
                                            <div class="custom-control custom-checkbox small">
                                                <input type="checkbox" class="custom-control-input" id="customCheck">
                                                <label class="custom-control-label" for="customCheck">Remember Me</label>
                                            </div>
                                        </div>
                                        <font color="red"></font>
                                        <button type="button" class="btn btn-success btn-user btn-block" id="loginsubmit" onkeydown="keyLogin()" >登录</button>
                                        <hr>
                                    </form>
                                </div>
                                <div id = "phone_login" style="display: none;">
                                    <form class="user" action="">
                                        <div class="form-group">
                                            <input type="text" class="form-control form-control-user table_input" id="phone" name="phone" placeholder="请输入手机号" onkeydown="keyPhoneLogin()" style="width: 100%">
                                        </div>
                                        <div class="form-group">
                                            <input type="text" class="table_input" id="code" name="code" placeholder="    请输入验证码" style="width: 70%;padding-left: 15px" onkeydown="keyPhoneLogin()">
                                           <input type="button" class="getcode btn-primary" value="获取验证码" onclick="yunpiancode()" id="getcode" name="getcode" style="width: 29%;margin-left: -4px">
                                        </div>
                                        <font color="red"></font>
                                        <button type="button" class="btn btn-primary btn-user btn-block" id="phonelogin" onClick="submit_phone() ;" >登录</button>
                                        <hr>
                                    </form>
                                </div>
                                <div id = "face_login" style="display: none;">
                                    <dl class="admin_login">
                                        <dt>
                                            <strong>人脸识别</strong>
                                        </dt>
                                        <div id="media">
                                            <video id="video" style="width: 100%;height: 100%" autoplay></video>
                                            <canvas id="canvas" width="400" height="300"></canvas>
                                        </div>
                                        <dd>
                                            <input type="button" onclick="query()" value="立即登录" class="submit_btn" onkeydown="keyFaceLogin()"/>
                                        </dd>
                                    </dl>
                                </div>
                                </div>
                                <span style="margin-left: 22px">
                                <a class="small" href="javascript:changeLoginType(0);">短信快捷登录</a>
                                &nbsp;
                                <a class="small" href="javascript:changeLoginType(1);">人脸识别登录</a>
                                </span>
                                <hr>
                                <div class="text-center">
                                    <a class="small" href="register.html">注册账号!</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    //实现上下键切换input框
    $(function () {
        //实现进入页面锁定在输入账号一栏
        $("#id").focus();
        //设置cust_index， 在按enter键的时候跳到下一个输入框
        $('.table_input').each(function(i,o){
            $(this).attr('cust_index', i);
        })
        $(".table_input").keydown(function (event) {
            var keycode = event.which;
            var ti = $(this).attr('cust_index');
            if (40 == keycode) {
                ti++;
            }else if (38 == keycode){
                ti--;
            }
            if (ti<0){
                ti = 0;
            }
            var next_obj = $('.table_input[cust_index='+ti+']');
            if(next_obj){
                next_obj.focus();
            }
        })
    })
    /*
    //这里我将这些js移到login.js里面在进行拦截器验证的时候无法将redirect传回
    */
    //检查电话号码是否存在
    function  checkPhone() {
        var phone = $("#phone").val();//获取学生学号
        if(phone.length!=11 || phone ==''){
            $("#phone").tips({
                side : 2,
                msg : '手机号格式不正确',
                bg : '#AE81FF',
                time : 3
            });
            showfh();
            $("#phone").focus();
            return false;
        }//post里面三个参数url，json键值对，function（data）：data用于接收后台传回的值
        var bool=false;
        $.ajax({
            type:"post",
            url:"./backstage/checkPhone",
            data:{phone:phone},
            async:false,
            success:function(data){
               if(data.status==400){
                    $("#phone").tips({
                        side : 2,
                        msg : '手机号未注册',
                        bg : '#AE81FF',
                        time : 3
                    });
                   bool=false;
                    showfh();
                    $("#phone").focus();
                    return false;
                }if (data.status==200){
                    bool=true;
                    return false;
                }

            }
        });
        if (bool){
            return bool;
        }
        return false;
    }
    //window.setTimeout(showfh,3000);
    var redirectUrl = "${redirect}";
    function changeLoginType(value){
        if (value==0){
            //按钮的隐藏
            $("#phone_login").show();
            $("#face_login").hide();
            $("#login1").hide();
        } else if(value==1){
            $("#login1").hide();
            $("#face_login").show();
            $("#phone_login").hide();
            getVideoAndContext();
        }else if(value==2){
            $("#login1").hide();
            $("#phone_login").hide();
            $("#face_login").show();
            getVideoAndContext();
        }
    }
    //获取验证码
    function yunpiancode() {

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
                showfh();
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
                    }
                },
                error: function (data) {
                    var msg = data.msg
                    swal(msg, "请继续操作", "error");
                }
            })
        }
    }
    jQuery(function() {
        var id = $.cookie('id');
        var password = $.cookie('password');
        if (typeof(id) != "undefined"
            && typeof(password) != "undefined") {
            $("#id").val(id);
            $("#password").val(password);
            $("#saveid").attr("checked", true);
            $("#code").focus();
        }
    });
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
            alert("你的浏览器不支持访问用户媒体设备");
        }
        //导航 获取用户媒体对象
        /*
        navigator.mediaDevices.getUserMedia(con)
            .then(function(stream){
                video.srcObject = stream;
                video.onloadmetadate = function(e){
                    video.play();
                }
            });
            */
    }
    var LOGIN = {
        checkInput:function (){
            if ($("#id").val().length<6 || $("#id").val().length>8) {
                $("#id").tips({
                    side : 2,
                    msg : '请输入6到8位的账号',
                    bg : '#AE81FF',
                    time : 3
                });
                showfh();
                $("#id").focus();
                return false;
            } else {
                $("#id").val(jQuery.trim($('#id').val()));
            }
            if ($("#password").val().length<6) {
                $("#password").tips({
                    side : 2,
                    msg : '请输入至少6位登录密码',
                    bg : '#AE81FF',
                    time : 3
                });
                showfh();
                $("#password").focus();
                return false;
            }
            $("#loginbox").tips({
                side : 1,
                msg : '正在登录 , 请稍后 ...',
                bg : '#68B500',
                time : 10
            });
            return true
        },
        doLogin:function() {
            $.post("./admin/login", $("#loginForm").serialize(),function(data){
                if (data.status == 200) {
                    if (redirectUrl == "") {
                        location.href = "./backstage/index";
                    } else {
                        location.href = redirectUrl;
                    }
                } else {
                    var msg = data.msg
                    swal(msg,"请继续操作","error");
                    $("#id").select();
                }
            });
        },
        login:function() {
            if (this.checkInput()) {
                this.doLogin();
            }
        }

    };
    function keyLogin(){
        if (event.keyCode==13) {  //回车键的键值为13
            LOGIN.login();
        }
    }
    //普通账号登录
    $(function(){
        $("#loginsubmit").click(function(){
            LOGIN.login();
        });
    });
    function keyPhoneLogin(){
        if (event.keyCode==13) {  //回车键的键值为13
            submit_phone();
        }
    }
    //电话登录提交
    function submit_phone() {
        var phone = $("#phone").val();
        var code = $("#code").val();
        if (phone.length!=11) {
            $("#phone").tips({
                side : 1,
                msg : '电话号码格式不正确',
                bg : '#AE81FF',
                time : 3
            });
            showfh();
            $("#phone").focus();
            return false;
        }
        if ($("#code").val().length!=4) {
            $("#code").tips({
                side : 1,
                msg : '请输入4位验证码',
                bg : '#AE81FF',
                time : 3
            });
            showfh();
            $("#code").focus();
            return false;
        }
        $("#loginbox").tips({
            side : 1,
            msg : '正在登录 , 请稍后 ...',
            bg : '#68B500',
            time : 10
        });
        $.ajax({
            type:"post",
            url:"./admin/phonelogin",
            data:{"phone":phone,"code":code},
            success:function(data){
                if(data.status == 200){
                    if (redirectUrl == "") {
                        location.href = "./backstage/index";
                    } else {
                        location.href = redirectUrl;
                    }
                } else {
                    var msg = data.msg
                    swal(msg,"请继续操作","error");
                }
            }
        });
    }
    function keyFaceLogin(){
        if (event.keyCode==13) {  //回车键的键值为13
            query()
        }
    }
    //人脸识别登录
    function query(){
        //把流媒体数据画到convas画布上去
        var context = canvas.getContext("2d");
        context.drawImage(video,0,0,400,300);
        var base = getBase64();
        $("#loginbox").tips({
            side : 1,
            msg : '正在登录 , 请稍后 ...',
            bg : '#68B500',
            time : 10
        });
        $.ajax({
            type:"post",
            url:"./admin/facelogin",
            data:{"img":base},
            success:function(data){
                if(data.status == 200){
                    if (redirectUrl == "") {
                        location.href = "./backstage/index";
                    } else {
                        location.href = redirectUrl;
                    }
                } else {
                    var msg = data.msg
                    swal(msg,"请继续操作","error");
                }
            }
        });
    }
    function getBase64() {
        var imgSrc = document.getElementById("canvas").toDataURL(
            "image/png");
        return imgSrc.split("base64,")[1];

    };

</script>
</body>
</html>