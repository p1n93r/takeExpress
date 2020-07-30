<%--
  Created by IntelliJ IDEA.
  User: fyzn12
  Date: 2020/3/21
  Time: 18:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="bp" uri="/setBasePath" %>
<bp:basePath request="<%=request%>"/>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>个人中心基本信息修改</title>
    <link href="static/backstage/css/managermodifyInfo.css" type="text/css" rel="stylesheet">
    <link href="static/backstage/css/plugins/sweetalert/sweetalert.css" rel="stylesheet"/>
    <script type="text/javascript" src="static/public/js/jquery.tips.js"></script>
    <script type="text/javascript" src="static/public/js/jquery.cookie.js"></script>
    <script src="static/backstage/js/plugins/sweetalert/sweetalert.min.js"></script>
</head>
<body >
<div style="margin: 0 0 15px;" class="row wrapper border-bottom white-bg page-heading">
    <div class="col-lg-10">
        <ol class="breadcrumb">
            <li>
                <a >Home</a>
            </li>
            <li>
                <a>个人中心信息管理</a>
            </li>
            <li class="active">
                <strong>修改基本信息</strong>
            </li>
        </ol>
    </div>
    <div class="col-lg-2"></div>
</div>
<div class="container" style="margin-top: 30px;font-family: 楷体;">
    <div class="row" style="background:#e9ebef;margin-left: 0px;margin-right: 0px;">
        <div class="formpage">
            <div class="clear" style="height:20px"></div>
            <h2>账户信息</h2>
            <div class="clear" style="height:30px"></div>
            <span class="information">
                <form id="loginForm" method="post" name="loginForm" enctype="multipart/form-data" action="./admin/modifyManagerBaseInfo" onsubmit="return false;">
            	    <div class="clear" style="height:10px"></div>
            	    <a class="headpic1">
                        <input id="file_upload" name="handpic" value="" type="file" style="display: none" accept="image/png,image/jpg,image/gif,image/JPEG" multiple="multiple"/>
                        <img src="${requestScope.manager.pic}" id="preview" class="img-circle">
                        <span>点击头像修改</span>
                    </a>
                    <table style="margin-left: 10px">
                        <tr>
                            <td>帐号：</td>
                            <td colspan="2">${requestScope.manager.loginId}</td>
                            <td><input type="text" id="loginid" style="display: none;" value="${requestScope.manager.loginId}"></td>
                        </tr>

                        <tr>
                            <td>昵  称：</td>
                            <td><input class="textput" type="text" id="nickname" value="${requestScope.manager.nickname}"/></td>
                        </tr>
                        <tr>
                            <td>性别：</td>
                            <td><label id="sexall">女<input name="sex" type="radio" value="女"></label><label>男<input name="sex" value="男" type="radio"></label></td>
                        </tr>
                        <tr>
                            <td>email：</td>
                            <td><input class="textput" type="text" id="email" value="${requestScope.manager.email}"/></td>
                        </tr>
                         <tr>
                            <td>电话：</td>
                            <td><input class="textput" type="text" id="phone" readonly="readonly" value="${requestScope.manager.phone}"/></td>
                        </tr>
                        <tr>
                            <td>个性说明：</td>
                            <td><textarea class="textput" id="signature" style="width: 200px">${requestScope.manager.signature}</textarea></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td colspan="2">
                                <button class="greenbtn" onclick="modifyInfo();">修改</button></td>
                        </tr>
                    </table>
                </form>
                <div class="clear"></div>
            </span>
            <div class="clear" style="height:50px"></div>

        </div>
        <div class="clearh" style="height:0"></div>
    </div>

</div>
</div>
</body>
</html>
<script>
    $(function() {
        $("#preview").click(function () {
            $("#file_upload").click();
        });
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

    function modifyInfo(){
        var loginid = $("#loginid").val();
        var nickname = $("#nickname").val();
        if (nickname.length>12) {
            $("#nickname").tips({
                side : 2,
                msg : '昵称长度不能超过12个字符',
                bg : '#AE81FF',
                time : 3
            });
            $("#nickname").focus();
            return false;
        }
        var a = document.getElementsByName("sex");
        var sex ="";
        for (i=0;i<a.length;i++){
            if (a[i].checked)
                sex=a[i].value;
        }
        var signature = $("#signature").val();
        if (signature.length>30) {
            $("#signature").tips({
                side : 2,
                msg : '个性签名不能超过30个字符！',
                bg : '#AE81FF',
                time : 3
            });
            $("#signature").focus();
            return false;
        }
        var email = $("#email").val();
        var uploadFile = new FormData();

        var file = $('#file_upload')[0].files[0];
        if (typeof file !='undefined' ) {
            uploadFile.append("handpic",file);
        }
        uploadFile.append('loginid',loginid);
        uploadFile.append('email',email);
        uploadFile.append('nickname',nickname);
        uploadFile.append('sex',sex);
        uploadFile.append('content',signature);
        $.ajax({
            type: "POST",
            url: "./admin/modifyManagerBaseInfo",
            data: uploadFile,
            cache: false,//文件不设置缓存
            processData: false,
            contentType: false,//代表发送信息至服务器时内容编码类型,设置为 false 是为了避免 JQuery 对其操作

            success: function (data) {
                if (data.status==200){
                    swal("请刷新网页后查看修改效果","请继续操作","success");
                } else {
                    var msg = data.msg
                    swal(msg,"请继续操作","error");
                }
            },
            error:function (data) {
                var msg = data.msg
                swal(msg,"请继续操作","error");
            }
        });
    }

</script>

