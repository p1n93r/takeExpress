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
    $("#submit-application").click(function () {
        var userid = $("#userID").val();
        var status = $(".sell option:selected").val();
        var applContent = $("#application-Text").val();
        var uploadFile = new FormData();
        var file = $('#file_upload')[0].files[0];
        if (typeof file !='undefined' ) {
            uploadFile.append("uploadfile",file);
        }
        if (applContent == null){
            swal("申请理由不能为空", '请继续操作', 'error');
            return false;
        }
        uploadFile.append("userid",userid);
        uploadFile.append("status",status);
        uploadFile.append("applContent",applContent);
        $.ajax({
            type:"POST",
            //dataType:"application/json;charset=utf-8",这个位置不能乱指定
            url:"./user/JurisdictionApplication",
            data:uploadFile,
            cache: false,//文件不设置缓存
            processData: false,
            contentType: false,//代表发送信息至服务器时内容编码类型,设置为 false 是为了避免 JQuery 对其操作
            success:function (data) {
                if (data.status==200){
                    swal("等待管理员审核", '申请已提交...', 'success');
                    return false;
                }
                if (data.status==400){
                    var msg = data.msg;
                    swal(msg, '申请已提交...', 'error');
                    return false;
                }
            },
            error:function (data) {
                swal("请检查网络...", '申请提交失败...', 'error');
            }
        })

    })
});

//解决ajax重定向问题
$(document).ajaxComplete(function (event, xhr, settings) {
    redirectHandle(xhr);
});
function redirectHandle(xhr) {
    //获取后台返回的参数
    var url = xhr.getResponseHeader("redirectUrl");
    console.log("redirectUrl = " + url);
    var enable = xhr.getResponseHeader("enableRedirect");
    if((enable == "true") && (url != "")){
        var win = window;
        while(win != win.top){
            win = win.top;
        }
        win.location.href = url;
    }
}