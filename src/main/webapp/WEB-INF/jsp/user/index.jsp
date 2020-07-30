<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--引入网页的头--%>
<%@include file="head.jsp"%>

<body style="background-color: #fff9f9">

<!-- 预加载动画 -->
<div id="preloader">
    <div class="outer">
        <div class="spinner">
            <div class="dot1"></div>
            <div class="dot2"></div>
        </div>
    </div>
</div>


<!-- site wrapper -->
<div class="site-wrapper">
    <!-- 移动端的顶部状态栏 -->
    <div class="mobile-header py-2 px-3 mt-4">
        <button class="menu-icon mr-2"><span></span><span></span><span></span></button>
        <!--注意：此处后续需要添加超链接-->
        <a href="index.html" class="logo"><img class="headpic" src="static/user/picture/logo.jpg" alt="用户名" /></a>
        <a href="index.html" class="site-title dot ml-2">用户名</a>
    </div>

    <!--大屏端-->
    <!-- 左侧导航以及顶部状态栏 -->
    <header class="left float-left shadow-dark" id="header">
        <button type="button" class="close" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <div class="header-inner d-flex align-items-start flex-column">
            <a href="index.html" class="logo"><img class="headpic" src="static/user/picture/logo.jpg" alt="用户名" /></a>
            <a href="index.html" class="site-title dot ml-2">用户名</a>
            <span class="site-slogan">个性签名~~白天不懂夜的黑~~</span>
            <!-- 左侧导航栏 -->
            <nav>
                <ul class="vertical-menu scrollspy">
                    <li><a class="nav-link" href="#home"><span class="iconify" data-icon="ion:home" data-inline="false"></span>主页</a></li>
                    <li><a class="nav-link" href="#demandSideOrder"><span class="iconify" data-icon="ion:receipt-sharp" data-inline="false"></span>需求方订单</a></li>
                    <li><a class="nav-link" href="#otherSideOrder"><span class="iconify" data-icon="ion:receipt-sharp" data-inline="false"></span>代取方订单</a></li>
                    <li><a class="nav-link" href="#expressPage"><span class="iconify" data-icon="ion:search-circle-sharp" data-inline="false"></span>快递查询</a></li>
                    <li><a class="nav-link" href="#applicationJurisdiction"><span class="iconify" data-icon="wpf:administrator" data-inline="false"></span>权限申请</a></li>
                    <li><a class="nav-link" href="#contact"><span class="iconify" data-icon="ion:logo-whatsapp" data-inline="false"></span>用户反馈</a></li>
                    <li><a class="nav-link" href="logout"><span class="iconify" data-icon="mdi:logout" data-inline="false"></span>logout</a></li>
                </ul>
            </nav>

            <!-- 左侧底部第三方链接 -->
            <div class="footer mt-auto">
                <!-- social icons -->
                <ul class="social-icons list-inline">
                    <li class="list-inline-item"><a href="https://p1n93r.github.io"><span class="iconify" data-icon="ion:logo-github" data-inline="false"></span></a></li>
                    <li class="list-inline-item"><a href="#"><span class="iconify" data-icon="ion:logo-google-playstore" data-inline="false"></span></a></li>
                    <li class="list-inline-item"><a href="#"><span class="iconify" data-icon="ion:logo-firefox" data-inline="false"></span></a></li>
                    <li class="list-inline-item"><a href="#"><span class="iconify" data-icon="ion:logo-twitter" data-inline="false"></span></a></li>
                </ul>
                <!-- 版权 -->
                <span class="copyright">&copy; 2020 Take-Express</span>
            </div>
        </div>
    </header>

    <!-- 右侧主内容区域 -->
    <main class="content float-right">
        <%--引入一个页面：首页介绍界面--%>
        <%@include file="introducePage.jsp"%>
        <%--引入需求方订单管理界面--%>
        <%@include file="demandSideOrder.jsp"%>
        <%--引入代取方方订单管理界面--%>
        <%@include file="otherSideOrder.jsp"%>
        <!--引入快递查询界面-->
        <%@include file="expressPage.jsp"%>
        <!-- 引入权限申请的页面-->
        <%@include file="applicationJurisdiction.jsp"%>
        <%--引入用户反馈界面--%>
        <%@include file="feedbackPage.jsp"%>
    </main>
</div>


<!-- 返回顶部-->
<a href="javascript:" id="return-to-top"><span class="iconify" data-icon="ion:arrow-up-circle-outline" data-inline="false"></span></a>


<!-- 引入JS脚本 -->
<script src="static/public/js/jquery-1.12.3.min.js"></script>
<script src="static/public/js/bootstrap.min.js"></script>
<script src="static/user/js/jquery.easing.min.js"></script>
<script src="static/user/js/popper.min.js"></script>
<script src="static/user/js/jquery.waypoints.min.js"></script>
<script src="static/user/js/jquery.counterup.min.js"></script>
<script src="static/user/js/jquery.mcustomscrollbar.concat.min.js"></script>
<script src="static/user/js/isotope.pkgd.min.js"></script>
<script src="static/user/js/infinite-scroll.min.js"></script>
<script src="static/user/js/imagesloaded.pkgd.min.js"></script>
<script src="static/user/js/slick.min.js"></script>
<script src="static/user/js/validator.js"></script>
<script src="static/user/js/custom.js"></script>
<!--typeahead插件--start-->
<script src="static/user/js/jquery.typeahead.min.js"></script>
<!--typeahead插件--end-->
<!--字体图标框架--start-->
<script src="static/public/js/iconify.min.js"></script>
<!--字体图标框架--end-->
<!--bootstrap-table插件--start-->
<script src="https://unpkg.com/bootstrap-table@1.15.3/dist/bootstrap-table.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap-table/1.16.0/locale/bootstrap-table-zh-CN.js"></script>
<!--bootstrap-table插件--end-->
<!--user模块的js--start-->
<script src="static/user/js/user.js"></script>
<script src="static/user/js/applicationJurisdiction.js"></script>
<!--user模的js--end-->
</body>
</html>