<%--
  Created by IntelliJ IDEA.
  User: P1n93r
  Date: 2020/4/13
  Time: 17:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="bp" uri="/setBasePath" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>快递代取系统后台管理</title>
    <bp:basePath request="<%=request%>"/>
    <link href="static/backstage/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="static/backstage/font-awesome/css/font-awesome.css" rel="stylesheet"/>
    <link href="static/backstage/css/plugins/dataTables/datatables.min.css" rel="stylesheet"/>
    <link href="static/backstage/css/animate.css" rel="stylesheet"/>
    <link href="static/backstage/css/style.css" rel="stylesheet"/>
    <link href="static/backstage/css/plugins/sweetalert/sweetalert.css" rel="stylesheet"/>
</head>
<body>

<div id="wrapper">
    <!--左侧导航菜单start-->
    <nav class="navbar-default navbar-static-side" role="navigation">
        <div class="sidebar-collapse">
            <ul class="nav metismenu" id="side-menu">
                <!--左侧顶部头像区域start-->
                <li class="nav-header">
                    <div class="dropdown profile-element">
							<span>
								<img style="width:80px;height: 80px " alt="image" class="img-circle" src="${manager.pic}" />
                            </span>
                        <a data-toggle="dropdown" class="dropdown-toggle">
                            <span class="clear">
								<span class="block m-t-xs">
									<strong class="font-bold">管理员<b class="caret"></b></strong>
								</span>
							</span>
                        </a>
                        <ul class="dropdown-menu animated fadeInRight m-t-xs">
                            <li><a href="user/showUserIndex">去前台</a></li>
                            <li class="divider"></li>
                            <li><a href="backstage/logout">登出</a></li>
                        </ul>
                    </div>
                    <div class="logo-element">
                        IN+
                    </div>
                </li>
                <!--左侧顶部头像区域--end-->

                <%--====================================添加页面--start--==================================--%>
                <!--左侧下拉项菜单区域--start-->

                <!--feedback界面--start-->
                <li id="leftNav">
                    <a href="backstage/feedback"><i class="fa fa-bug"></i><span class="nav-label">用户反馈管理</span></a>
                </li>
                <!--feedback界面--end-->

                <!--日志统计界面--start-->
                <li class="leftNav">
                    <a href="#"><i class="fa fa-bar-chart-o"></i> <span class="nav-label">日志统计</span><span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level collapse">
                        <li><a href="backstage/loginLog" class="active">管理员登录日志</a></li>
                        <li><a href="backstage/loginLog">后台操作日志</a></li>
                    </ul>
                </li>
                <!--日志统计界面--start-->


                <li class="leftNav">
                    <a href="backstage/index#"><i class="fa fa-files-o"></i> <span class="nav-label">后台用户管理</span><span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level collapse">
                        <li><a href="admin/scantionUser" class="active">制裁用户</a></li>
                        <li><a href="admin/applyScantionUser">审核用户权限申请</a></li>
                        <li><a href="admin/scantionRegister">审核用户注册申请</a></li>
                    </ul>
                </li>
                <li class="leftNav">
                    <a href="#">
                        <i class="fa fa-edit"></i>
                        <span class="nav-label">个人中心信息管理</span><span class="fa arrow"></span>
                    </a>
                    <ul class="nav nav-second-level collapse">
                        <li><a href="admin/managermodifySafe">账户安全管理</a></li>
                        <li><a href="admin/modifyBaseInfo">修改基本信息</a></li>
                    </ul>
                </li>
                <!--左侧下拉项菜单区域--end-->
                <%--====================================添加页面--end---==================================--%>
            </ul>
        </div>
    </nav>
    <!--左侧导航菜单--end-->
    <div id="page-wrapper" class="gray-bg">
        <!--右侧顶部--start-->
        <div class="row border-bottom">
            <nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0">
                <div class="navbar-header">
                    <a class="navbar-minimalize minimalize-styl-2 btn btn-primary">
                        <i class="fa fa-bars"></i>
                    </a>
                </div>
                <ul class="nav navbar-top-links navbar-right">
                    <li class="dropdown">
                        <a class="dropdown-toggle count-info" data-toggle="dropdown" href="index.html#">
                            <i class="fa fa-bell"></i>  <span class="label label-primary">8</span>
                        </a>
                        <ul class="dropdown-menu dropdown-alerts">
                            <li>
                                <a href="mailbox.html">
                                    <div>
                                        <i class="fa fa-envelope fa-fw"></i> You have 16 messages
                                        <span class="pull-right text-muted small">4 minutes ago</span>
                                    </div>
                                </a>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <a href="profile.html">
                                    <div>
                                        <i class="fa fa-twitter fa-fw"></i> 3 New Followers
                                        <span class="pull-right text-muted small">12 minutes ago</span>
                                    </div>
                                </a>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <a href="grid_options.html">
                                    <div>
                                        <i class="fa fa-upload fa-fw"></i> Server Rebooted
                                        <span class="pull-right text-muted small">4 minutes ago</span>
                                    </div>
                                </a>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <div class="text-center link-block">
                                    <a href="notifications.html">
                                        <strong>See All Alerts</strong>
                                        <i class="fa fa-angle-right"></i>
                                    </a>
                                </div>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <span class="m-r-sm text-muted welcome-message">欢迎登陆！</span>
                    </li>
                    <li>
                        <a href="admin/logout">
                            <i class="fa fa-sign-out"></i>安全退出
                        </a>
                    </li>
                </ul>
            </nav>
        </div>
        <!--右侧顶部--end-->
        <!--面包屑导航栏--start-->
<%--        <div class="row wrapper border-bottom white-bg page-heading" id="showLogin">--%>
<%--            <div class="col-lg-10">--%>
<%--                <h2 id="whichPage" style="margin-top: ">欢迎登陆！</h2>--%>
<%--            </div>--%>
<%--        </div>--%>
        <!--面包屑导航栏--end-->
        <!--主内容区域--start-->
        <div class="wrapper wrapper-content animated fadeInRight">
            <div class="row" id="rightContent">
            </div>
        </div>

        <!--主内容区域--end-->

        <!--底部版权--start-->
        <div class="footer" style="border: solid 1px #b0b5c2;background-color: #0c5460;padding-top: 10px;text-align: center">
            <div style="color: white">
                <strong>Copyright</strong> TakeExpress &copy; 2019-2020
            </div>
        </div>
        <!--底部版权--end-->
    </div>
</div>

<!-- Mainly scripts -->
<script src="static/backstage/js/jquery-3.1.1.min.js"></script>
<script src="static/backstage/js/bootstrap.min.js"></script>
<script src="static/backstage/js/plugins/metisMenu/jquery.metisMenu.js"></script>
<script src="static/backstage/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
<script src="static/backstage/js/plugins/dataTables/datatables.min.js" chartset="utf8"></script>
<!-- Custom and plugin javascript -->
<script src="static/backstage/js/inspinia.js"></script>
<script src="static/backstage/js/plugins/pace/pace.min.js"></script>
<!--index页面的js--start-->
<script src="static/backstage/js/index.js"></script>
<%--引入sweetalert--start--%>
<script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.js"></script>

</body>

</html>
