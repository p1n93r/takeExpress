<%--
  Created by IntelliJ IDEA.
  User: fyzn12
  Date: 2020/4/12
  Time: 16:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="bp" uri="/setBasePath" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>评论回复以及显示</title>
    <bp:basePath request="<%=request%>"/>
    <meta name="description" content="" />
    <link href="static/user/comment/css/comment.css" rel="stylesheet">
    <link href="static/public/bootstrap-3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link href="static/backstage/css/plugins/sweetalert/sweetalert.css" rel="stylesheet"/>
    <script src="static/public/js/jquery-1.12.3.min.js" type="text/javascript"></script>
    <script src="static/public/bootstrap-3.3.7/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="static/user/comment/js/comment.js"></script>
    <script src="static/backstage/js/plugins/sweetalert/sweetalert.min.js"></script>
    <style>
        .container{
            width:100%;
            padding-right:.75rem;
            padding-left:.75rem;
            margin-right:auto;
            margin-left:auto
        }
        @media (min-width:576px){
            .container{
                max-width:540px
            }

        }
        @media (min-width:768px){
            .container{
                max-width:720px
            }


        }
        @media (min-width:992px){
            .container{
                max-width:960px
            }


        }
        @media (min-width:1200px){
            .container{
                max-width:900px
            }

        }

    </style>
</head>
<body>


    <!--显示评论-->
    <div class="container">
        <input type="text" id="pic" style="display: none" value="${pic}"/>
        <input type="text" id="nickname" style="display: none" value="${nickname}"/>
        <div class="row">
        <div class="col-md-10">
        <c:forEach var="comment" items="${comments}">
            <div class="comments" style="margin-top: 20px">
                <a class="comments-avatar" style="float: left">
                    <img style="width: 40px;height: 40px" class="img-circle" src="${comment.avatar}" alt=""/>
                </a>
                <div class="content" style="margin-left: 60px">
                    <a class="author-name" style="float: left;">
                        <span>${comment.nickname}</span>
                    </a>
                    <div class="metadata" style="margin-left: 70px">
                        <span class="date">${comment.time}</span>
                    </div>
                    <div class="text">
                        <span style="float:left;">${comment.content}</span>
                        <div style="float: right;margin-right:20px;">
                             <span><c:if test="${comment.userId != userid}"><a href="javascript:reply('${comment.commentId}','${comment.nickname}')">回复</a></c:if></span>
                         </div>
                    </div>
                </div>
                <!-- 用于显示提交新评论的位置 -->
                <div id="${comment.commentId}"></div>
                <!--子级评论-->
                <c:forEach items="${comment.replyComments}" var="childComment">
                    <div class="comments" style="margin-left: 35px;margin-top: 30px;margin-bottom: 30px;">
                        <!-- 父级评论 -->
                        <div class="comment" style="margin-top: 20px;margin-bottom: 20px;">
                             <a class="comments-avatar" style="float: left">
                                <img style="width: 40px;height: 40px" class="img-circle" src="${childComment.avatar}">
                             </a>
                            <div class="content" style="margin-left: 60px">
                                <a class="author-name" style="float: left;">
                                    <span>${childComment.nickname}</span>
                                </a>
                                <a class="author-name" style="float: left;margin-right: 10px">
                                    <span>&nbsp;回复&nbsp;
                                        <!-- 父级评论回复子集评论-->
                                        <c:if test="${childComment.commentId==comment.commentId}">
                                            <c:forEach items="${comment.replyComments}" var="ch_comment">
                                                <c:if test="${ch_comment.commentId==childComment.parentCommitId}">
                                                    ${ch_comment.nickname}
                                                </c:if>
                                            </c:forEach>
                                        </c:if>
                                        <!-- 子级评论回复父级评论或者子级评论之间的回复-->
                                        <c:if test="${childComment.commentId!=comment.commentId}">
                                            <c:forEach items="${comment.replyComments}" var="ch_comment">
                                                <!-- 子级评论之间的回复-->
                                                <c:if test="${ch_comment.commentId==childComment.parentCommitId}">
                                                    ${ch_comment.nickname}
                                                </c:if>
                                            </c:forEach>
                                            <!-- 子级评论回复父集评论 -->
                                            <c:if test="${childComment.parentCommitId==comment.commentId}">
                                                ${comment.nickname}
                                            </c:if>

                                        </c:if>
                                    </span>
                                </a>
                                <div class="date-picker-popup" >
                                    <a class="date-cell">${childComment.time}</a>
                                </div>
                                <div class="text">
                                       <span style="float: left"> ${childComment.content}</span>
                                       <div style="float: right;margin-right:20px;">
                                           <span><c:if test="${childComment.userId != userid}"><a href="javascript:childrenReply('${childComment.commentId}','${childComment.nickname}')">回复</a></c:if></span>
                                       </div>
                                </div>
                            </div>
                         </div>
                        <!-- 用于显示提交新评论的位置 -->
                        <div id="child-${childComment.commentId}"></div>
                    </div>
                </c:forEach>
            </div>
        </c:forEach>
        </div>
    </div>
    </div>
<button class="btn btn-primary btn-sm" data-toggle="modal" data-target="#Register-scantion">测试评论对话框</button>
<div class="modal fade" id="Register-scantion" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" onclick="close();" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    <h3 style="font-size:14px;line-height:35px;height:35px;border-bottom:solid 1px #e8e8e8;padding-left:20px;background:#f8f8f8;color:#666;position:relative;">我要评论</h3>
                </h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form" method="post" id="form" onsubmit="return false;">
                    <div class="container">
                        <div class="row">
                            <div class="col-md-10">

                                <div class="quiz_content">
                                        <div class="goods-comm">
                                            <div class="goods-comm-stars">
                                                <span class="star_l">满意度：</span>
                                                <div id="rate-comm-1" class="rate-comm"></div>
                                            </div>
                                        </div>
                                        <div class="form-group" style="padding-left: 24px;position: relative;z-index: 7;">
                                            <label class="">内  容：</label>
                                            <div class="" >
                                                <textarea style="width: 100%;font-family: 楷体;padding-left: 10px" class="text" placeholder="字数限制为5-200个" ></textarea>
                                            </div>
                                        </div>
                                        <div class="form-group" style="margin-top: 20px;padding-left: 24px">
                                            <span><i class="fa fa-user" aria-hidden="true"></i></span>
                                            <P>上传图片</P>
                                            <input id="file_upload" name="stupic" type="file" style="display: none" accept="image/png,image/jpg,image/gif,image/JPEG"/>
                                            <div class="image_container" style="text-align: center;">
                                                <img src="static/visitor/img/upload_img.png" id="preview" style="width: 300px;height: 200px" alt="点击上传图片">
                                            </div>
                                        </div>
                                </div><!--quiz_content end-->
                            </div><!--quiz end-->
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal" id="closemodify">关闭
                        </button>
                        <button type="button" class="btn btn-primary" id="submit-scantion">
                            提交
                        </button><span id="tip"> </span>
                    </div>
                </form>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<script>
    /**************************************时间格式化处理************************************/
    function dateFtt(fmt,date) {
        var o = {
            "M+" : date.getMonth()+1,     //月份
            "d+" : date.getDate(),     //日
            "h+" : date.getHours(),     //小时
            "m+" : date.getMinutes(),     //分
            "s+" : date.getSeconds(),     //秒
            "q+" : Math.floor((date.getMonth()+3)/3), //季度
            "S" : date.getMilliseconds()    //毫秒
        };
        if(/(y+)/.test(fmt))
            fmt=fmt.replace(RegExp.$1, (date.getFullYear()+"").substr(4 - RegExp.$1.length));
        for(var k in o)
            if(new RegExp("("+ k +")").test(fmt))
                fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
        return fmt;
    }
    //创建时间格式化显示
    function crtTimeFtt(date){
        return top.dateFtt("yyyy-MM-dd hh:mm:ss",date);//直接调用公共JS里面的时间类处理的办法
    }
    function reply(replyCommentId,nickname){
        var commentId = replyCommentId;
        //弹出alert
        swal({
                title:'评论回复',
                text:'请输入回复内容~',
                type:'input',
                showCancelButton:true,
                confirmButtonColor:'#33ccff',
                closeOnConfirm: false,
                closeOnCancel: false,
                confirmButtonText:'回复',
                cancelButtonText:'取消',
                inputPlaceholder:'已回复......',
                showLoaderOnConfirm:true
            },function(inputValue){
                //console.debug(inputValue);
                if(jQuery.trim(inputValue)!=''&&inputValue==false){
                    swal("取消回复", "你没有进行回复！", "info");
                    return;
                }
                if(inputValue==''){
                    swal.showInputError('你没有填写任何回复信息！');
                    return;
                }
                //将父评论的commentId作为回复评论的
                //发送ajax
                $.ajax({
                    type:"post",
                    url:'user/comment',
                    traditional: true,
                    data:{"parentCommitId":commentId,"content":inputValue},
                    success:function (data) {
                        if(data.status==200){
                            swal("回复成功!", "您已进行此用户评论的回复！", "success");
                            //修改显示评论的
                            var time = crtTimeFtt(new Date());
                            $("#"+commentId).html('<div class="comments" style="margin-left: 35px;margin-top: 30px;margin-bottom: 30px;"> ' +
                                '<div class="comment" style="margin-top: 20px;margin-bottom: 20px;">' +
                                '            <a class="comments-avatar" style="float: left">' +
                                '                 <img style="width: 40px;height: 40px" class="img-circle" src="'+$("#pic").val()+'">' +
                                '            </a>' +
                                ' <div class="content" style="margin-left: 60px">'+
                                '               <a class="author-name" style="float: left;"> '+
                                '<span>'+$("#nickname").val()+'</span>' +
                                '</a>'+
                                '<a class="author-name" style="float: left;margin-right: 10px">' +
                                '                  <span>&nbsp;回复&nbsp;'+
                                nickname+
                                '</span>'+
                                '</a>'+
                                '<div class="date-picker-popup" >'+
                                '<a class="date-cell">'+time+'</a>'+
                                '</div>'+
                                ' <div class="text">'+
                                '<span style="float: left">'+ inputValue+'</span>'+
                                '</div>'+
                                '</div>'+
                                '</div>'+
                                '</div>')
                            return  ;
                        }else{
                            swal("回复失败！", "回复失败，请检查网络配置后重试！", "error");
                        }
                    },
                    error:function (data) {
                        swal("回复失败！", "回复失败，请检查网络配置后重试！", "error");
                    }
                });
            }
        );

    }
    function childrenReply(replyCommentId,nickname){
        var commentId = replyCommentId;
        //弹出alert
        swal({
                title:'评论回复',
                text:'请输入回复内容~',
                type:'input',
                showCancelButton:true,
                confirmButtonColor:'#33ccff',
                closeOnConfirm: false,
                closeOnCancel: false,
                confirmButtonText:'回复',
                cancelButtonText:'取消',
                inputPlaceholder:'已回复......',
                showLoaderOnConfirm:true
            },function(inputValue){
                //console.debug(inputValue);
                if(jQuery.trim(inputValue)!=''&&inputValue==false){
                    swal("取消回复", "你没有进行回复！", "info");
                    return;
                }
                if(inputValue==''){
                    swal.showInputError('你没有填写任何回复信息！');
                    return;
                }
                //将父评论的commentId作为回复评论的
                //发送ajax
                $.ajax({
                    type:"post",
                    url:'user/comment',
                    traditional: true,
                    data:{"parentCommitId":commentId,"content":inputValue},
                    success:function (data) {
                        if(data.status==200){
                            swal("回复成功!", "您已进行此用户评论的回复！", "success");
                            //修改显示评论的
                            var time = crtTimeFtt(new Date());
                            $("#child-"+commentId).html(
                                '<div class="comment" style="margin-top: 20px;margin-bottom: 20px;">' +
                                '            <a class="comments-avatar" style="float: left">' +
                                '                 <img style="width: 40px;height: 40px" class="img-circle" src="'+$("#pic").val()+'">' +
                                '            </a>' +
                                ' <div class="content" style="margin-left: 60px">'+
                                '               <a class="author-name" style="float: left;"> '+
                                '<span>'+$("#nickname").val()+'</span>' +
                                '</a>'+
                                '<a class="author-name" style="float: left;margin-right: 10px">' +
                                '                  <span>&nbsp;回复&nbsp;'+
                                nickname+
                                '</span>'+
                                '</a>'+
                                '<div class="date-picker-popup" >'+
                                '<a class="date-cell">'+time+'</a>'+
                                '</div>'+
                                ' <div class="text">'+
                                '<span style="float: left">'+ inputValue+'</span>'+
                                '</div>'+
                                '</div>'+
                                '</div>'
                            )
                            return  ;
                        }else{
                            swal("回复失败！", "回复失败，请检查网络配置后重试！", "error");
                        }
                    },
                    error:function (data) {
                        swal("回复失败！", "回复失败，请检查网络配置后重试！", "error");
                    }
                });
            }
        );

    }


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

        // 关闭模态框时还原所有数据
        $('.modal').on("hidden.bs.modal", function (e) {
            // window.location.reload(); 刷新页面
            $(this).removeData("bs.modal");
            document.getElementById("form").reset();
            $(".showTexArea").hide();//隐藏textarea
        });
    });
</script>
</body>
</html>