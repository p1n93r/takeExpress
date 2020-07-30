<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<%--====================================引入css文件--start--==================================--%>

<%--引入sweetalert--start--%>
<link href="static/backstage/css/plugins/sweetalert/sweetalert.css" rel="stylesheet"/>
<%--引入sweetalert--end--%>

<!--引入loginLog界面的css文件(此处的feedback界面的css可以通用，就不单独写loginLog.css了)--start-->
<link href="static/backstage/css/feedback.css" rel="stylesheet"/>
<!--引入loginLog界面的css文件--start-->

<%--====================================引入css文件--end--==================================--%>


<%--===============================主内容区--start--=========================================--%>
<div class="col-lg-12">

    <!--顶部页面说明--start-->
    <div style="margin: 0 0 15px;" class="row wrapper border-bottom white-bg page-heading">
        <div class="col-lg-10">
            <h2 id="whichPage" style="margin-top:28px">管理员登录日志统计</h2>
            <ol class="breadcrumb">
                <li>
                    <a>Home</a>
                </li>
                <li>
                    <a>日志统计</a>
                </li>
                <li class="active">
                    <strong>管理员登录日志</strong>
                </li>
            </ol>
        </div>
        <div class="col-lg-2"></div>
    </div>
    <!--顶部页面说明--end-->

    <div class="ibox float-e-margins">
        <div class="ibox-title">
            <h5>以下是后台管理员登录信息的记录日志...</h5>
            <div class="ibox-tools">
                <a class="collapse-link">
                    <i class="fa fa-chevron-up"></i>
                </a>
                <a class="close-link">
                    <i class="fa fa-times"></i>
                </a>
            </div>
        </div>

        <div class="ibox-content">
            <div class="table-responsive">
                <!--一个按钮：用于删除所选行-->
                <a id="remove-some" class="btn btn-default buttons-remove-some" tabindex="0" aria-controls="DataTables_Table_14" href="#"><span>remove</span></a>

                <!--表格--start-->
                <table style="word-wrap:break-word;word-break:break-all;white-space:normal;" class="table table-striped table-bordered table-hover loginLog-table">
                    <thead>
                        <tr>
                            <th>编号</th>
                            <th>时间记录</th>
                            <th>IP记录</th>
                            <th>位置记录</th>
                            <th>是否登录成功</th>
                            <th>操作</th>
                        </tr>
                    </thead>
                    <tbody></tbody>
                </table>
                <!--表格--end-->
            </div>
        </div>
    </div>
</div>
<%--===============================主内容区--end--=========================================--%>

<%--====================================引入js文件--start--==================================--%>

<%--引入sweetalert--start--%>
<script src="static/backstage/js/plugins/sweetalert/sweetalert.min.js"></script>
<%--引入sweetalert--end--%>

<!--feedback模块的js--start-->
<script src="static/backstage/js/loginLog.js"></script>
<!--feedback模块的js--end-->

<%--====================================引入js文件--end--==================================--%>
