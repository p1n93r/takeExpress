<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<%--====================================引入css文件--start--==================================--%>

<%--引入sweetalert--start--%>
<link href="static/backstage/css/plugins/sweetalert/sweetalert.css" rel="stylesheet"/>
<%--引入sweetalert--end--%>

<!--引入feedbakc界面的css文件--start-->
<link href="static/backstage/css/feedback.css" rel="stylesheet"/>
<!--引入feedbakc界面的css文件--start-->

<%--====================================引入css文件--end--==================================--%>


<%--===============================主内容区--start--=========================================--%>
<div class="col-lg-12">

    <!--顶部页面说明--start-->
    <div style="margin: 0 0 15px;" class="row wrapper border-bottom white-bg page-heading">
        <div class="col-lg-10">
            <h2 id="whichPage" style="margin-top:28px">用户反馈信息处理</h2>
        </div>
        <div class="col-lg-2"></div>
    </div>
    <!--顶部页面说明--end-->

    <div class="ibox float-e-margins">
        <div class="ibox-title">
            <h5>用户反馈信息表格，请管理员审核并给予回复...</h5>
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
                <!--表格--start-->
                <table style="word-wrap:break-word;word-break:break-all;white-space:normal;" class="table table-striped table-bordered table-hover feedback-table">
                    <thead>
                        <tr>
                            <th>编号</th>
                            <th>用户</th>
                            <th>主题</th>
                            <th>内容</th>
                            <th>创建时间</th>
                            <th>联系邮箱</th>
                            <th>状态</th>
                            <th>回复</th>
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
<script src="static/backstage/js/feedback.js"></script>
<!--feedback模块的js--end-->

<%--====================================引入js文件--end--==================================--%>
