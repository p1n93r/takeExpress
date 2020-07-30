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
  <title>注册</title>
  <link href="static/visitor/css/sb-admin-2.min.css" rel="stylesheet">
  <link rel="stylesheet" href="static/public/css/bootstrap.min.css" />
  <link href="static/backstage/css/plugins/sweetalert/sweetalert.css" rel="stylesheet"/>
  <script type="text/javascript" src="static/public/js/jquery-1.12.3.min.js"></script>
  <script type="text/javascript" src="static/public/js/jquery.tips.js"></script>
  <script type="text/javascript" src="static/public/js/jquery.cookie.js"></script>
  <script src="static/backstage/js/plugins/sweetalert/sweetalert.min.js"></script>
</head>
<style>
  #code{
    height: 3rem;
    font-size:1rem;
    border-top-left-radius:10rem;
    border-top-right-radius:0rem;
    border-bottom-right-radius:0rem;
    border-bottom-left-radius:10rem;

  }
  #getcode{
    height: 3rem;
    font-size:.8rem;
    border-top-right-radius:10rem;
    border-top-left-radius:0rem;
    border-bottom-left-radius:0rem;
    border-bottom-right-radius:10rem;

  }
</style>
<body class="bg-gradient-primary">

<div class="container">

  <div class="card o-hidden border-0 shadow-lg my-5">
    <div class="card-body p-0">
      <!-- Nested Row within Card Body -->
      <div class="row">
        <div class="col-lg-5 d-none d-lg-block bg-register-image">

        </div>
        <div class="col-lg-7">
          <div class="p-5">
            <div class="text-center">
              <h1 class="h4 text-gray-900 mb-4">创建账号!</h1>
            </div>
            <form class="user" method="post" name="loginForm" id="loginForm" enctype="multipart/form-data">
              <div class="form-group">
                <input type="text" class="form-control form-control-user table_input" id="loginid" name="loginid"  placeholder="请设置6-8位账号">
              </div>
              <div class="form-group">
                <input type="password" class="form-control form-control-user table_input" id="password" name="password"  placeholder="请设置6位以上的密码">
              </div>
              <div class="form-group">
                <input type="password" class="form-control form-control-user table_input" id="passwordcheck" name="passwordcheck"  placeholder="请确认密码">
              </div>
              <div class="form-group">
                <input type="text" class="form-control form-control-user table_input" onblur="checkEmail();" id="email" name="email"  placeholder="email">
              </div>
              <div class="form-group">
                <input type="text" class="form-control form-control-user table_input" id="phone" name="phone" onblur="checkPhone();" placeholder="注册手机号">
              </div>
              <div class="form-group">
                <input type="text" class="table_input" name="code" id="code"  placeholder="   请输入验证码" required="" style="width: 65%;padding-left: 15px;font-size: 10px">
                <span><input type="button" class="btn-success"  value="获取验证码" onclick="yunpiancode();" id="getcode" name="getcode" style="width: 30%;font-size: 12px"></span>
              </div>
              <div class="form-group">
                  <span><i class="fa fa-user" aria-hidden="true"></i></span>
                  <P>上传学生证</P>
                  <input id="file_upload" name="stupic" type="file" style="display: none" accept="image/png,image/jpg,image/gif,image/JPEG"/>
                  <div class="col-md-6 col-lg-6 col-sm-6 image_container" style="text-align: center">
                    <img src="static/visitor/img/upload_img.png" id="preview" style="width: 70%;height: 40%" alt="点击上传图片">
                  </div>
              </div>
              <font color="red"></font>
              <button type="button" class="btn btn-primary btn-user btn-block" id="registsubmit" onclick="register();" onkeydown="registerByKeyDown()">注册</button>
              <hr>
            </form>
            <hr>
            <div class="text-center">
              <a class="small" href="login">已有账号? 登录!</a>
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
    $("#loginid").focus();
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
  $(function() {
    $("#preview").click(function () {
      $("#file_upload").click();
    })
    $("#file_upload").change(function() {
      var $file = $(this);
      var fileObj = $file[0];
      var windowURL = window.URL || window.webkitURL||window.mozURL;
      var dataURL;
      var $img = $("#preview");
      if(fileObj && fileObj.files && fileObj.files[0]){
        dataURL = windowURL.createObjectURL(fileObj.files[0]);
        $img.attr('src',dataURL);
      }else{
        dataURL = $file.val();
        var imgObj = document.getElementById("preview");
        // 两个坑:
        // 1、在设置filter属性时，元素必须已经存在在DOM树中，动态创建的Node，也需要在设置属性前加入到DOM中，先设置属性在加入，无效；
        // 2、src属性需要像下面的方式添加，上面的两种方式添加，无效；
        imgObj.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
        imgObj.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = dataURL;

      }
    });
  });
  //检查电话号码是否存在
  function  checkPhone() {
    var phone = $("#phone").val();
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
    var bool = false;
    $.ajax({
      type:"post",
      url:"./checkRegisterphone",
      data:{"phone":phone},
      async : false,
      success:function(data){
       if(data.status==400){
          $("#phone").tips({
            side : 2,
            msg : '手机号已注册',
            bg : '#AE81FF',
            time : 3
          });
         bool= false;
          showfh();
          $("#phone").focus();
          return false;
        }
        bool=true;
       return true;
      }
    });
    if (bool){
      return bool;
    }
    return false;

  }
  //检查邮箱
  function  checkEmail() {
    var email = $("#email").val();
    if (ismail(email)) {
      $.ajax({
        type: "post",
        url: "./checkemail",
        data: {"email": email},
        success: function (data) {
          if (data.status == 400) {
            $("#email").tips({
              side: 2,
              msg: '邮箱已被注册',
              bg: '#AE81FF',
              time: 3
            });
            showfh();
            $("#email").focus();
            return false;
          }
          return true;
        }
      });
      return false;
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
        async:false,
        success: function (data) {
          if (data != null) {
            console.log(data.msg);
          }
        },
        error:function (data) {
          var msga = data.msg
          swal(msga,"请继续操作","error");
        }
      })
    }
  }
  function register() {
    if (rcheck()) {
      var loginid = $("#loginid").val();
      var password = $("#password").val();
      var passwordcheck = $("#passwordcheck").val();
      var phone = $("#phone").val();
      var code = $("#code").val();
      var email = $("#email").val();
      var uploadFile = new FormData();
      var file = $('#file_upload')[0].files[0];
      if (typeof file =='undefined' ) {
        $("#preview").tips({
          side : 2,
          msg : '上传的学生证不能为空',
          bg : '#AE81FF',
          time : 3
        });
        showfh();
        $("#preview").focus();
        return
      }
      uploadFile.append("stupic",file);
      uploadFile.append('loginid',loginid);
      uploadFile.append('password',password);
      uploadFile.append('passwordcheck',passwordcheck);
      uploadFile.append('phone',phone);
      uploadFile.append('code',code);
      uploadFile.append('email',email);
      if ($('#file_upload')[0].files[0]==null){
        $("#preview").tips({
          side:3,
          msg:'请输入6到8位账号',
          bg:'#AE81FF',
          time:2
        });
        $("#preview").focus();
        $("#preview").val('');
        return false;
      }
      $.ajax({
        type:"POST",
        url:"./register.do",
        data:uploadFile,
        cache: false,//文件不设置缓存
        processData: false,
        contentType:false,//代表发送信息至服务器时内容编码类型,设置为 false 是为了避免 JQuery 对其操作
        //async: true,
        success: function(data) {
          if (data.status == 200) {
            swal("等待管理员审核！","请继续操作","success");
            location.href = "./login";
          } else {
            var msga = data.msg
            swal(msga,"请继续操作","error");
            $("#loginid").select();
          }
        }
      });
    }
  }
  //注册
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
    }else{
      $("#loginid").val(jQuery.trim($('#loginid').val()));
    }
    if($("#password").val().length<6){
      $("#password").tips({
        side:3,
        msg:'输入至少6位密码',
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
    if($("#email").val()==""){
      $("#email").tips({
        side:3,
        msg:'输入邮箱',
        bg:'#AE81FF',
        time:3
      });
      $("#email").focus();
      return false;
    }else if(!ismail($("#email").val())){
      $("#email").tips({
        side:3,
        msg:'邮箱格式不正确',
        bg:'#AE81FF',
        time:3
      });
      $("#email").focus();
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
  function registerByKeyDown() {
      if (event.keyCode==13){
        register();
      }
  }
  //邮箱格式校验
  function ismail(mail){
    return(new RegExp(/^(?:[a-zA-Z0-9]+[_\-\+\.]?)*[a-zA-Z0-9]+@(?:([a-zA-Z0-9]+[_\-]?)*[a-zA-Z0-9]+\.)+([a-zA-Z]{2,})+$/).test(mail));
  }
</script>
</body>
</html>