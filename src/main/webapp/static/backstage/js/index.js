
$(document).ready(function() {
    /**
     * 点击左侧选项菜单，右侧局部刷新
     * @author cassie
     */
    $("#leftNav a").click(function (e) {
        //ajax请求页面,并且填充右侧主内容
        $.post($(this).attr('href'), function (data) {
            $("#rightContent").html(data);
        });
        //填充右侧上部的页面提示
        // $("#whichPage").html($(this).children("span:last").html());
        //阻止超链接的跳转
        e.preventDefault();
    });
    //多级菜单的子页面局部刷新
    $(".leftNav ul li a").click(function (e) {
        //ajax请求页面,并且填充右侧主内容
        $.post($(this).attr('href'), function (data) {
            $("#rightContent").html(data);
        });
        e.preventDefault();
    });
    //解决ajax重定向问题
    $(document).ajaxComplete(function (event, xhr, settings) {
        // console.log("ajaxComplete ");
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
});