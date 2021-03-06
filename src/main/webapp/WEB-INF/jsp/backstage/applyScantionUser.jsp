<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--====================================引入css文件--start--==================================--%>

<link href="static/backstage/css/plugins/sweetalert/sweetalert.css" rel="stylesheet"/>

<%--====================================引入css文件--end--==================================--%>
<%--===============================主内容区--start--=========================================--%>

<div class="col-lg-12">

    <!--顶部页面说明--start-->
    <div style="margin: 0 0 15px;" class="row wrapper border-bottom white-bg page-heading">
        <div class="col-lg-10">
            <h2 id="whichPage" style="margin-top:28px">审核用户权限申请</h2>
            <ol class="breadcrumb">
                <li>
                    <a >Home</a>
                </li>
                <li>
                    <a>后台用户管理</a>
                </li>
                <li class="active">
                    <strong>审核用户权限申请</strong>
                </li>
            </ol>
        </div>
        <div class="col-lg-2"></div>
    </div>
    <!--顶部页面说明--end-->

    <div class="ibox float-e-margins">
        <div class="ibox-title">
            <h5>审核用户权限申请...</h5>
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
                <table class="table table-striped table-bordered table-hover applyScantion-table">
                    <thead>
                        <tr>
                            <th>用户id</th>
                            <th>姓名</th>
                            <th>需求发布积分</th>
                            <th>代取积分</th>
                            <th>状态</th>
                            <th>申请权限</th>
                            <th>审核</th>
                        </tr>
                    </thead>
                    <tbody></tbody>
                </table>
                <!--表格--end-->
                <!-- 用户详情-->
                <div class="modal fade" id="userinfo" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display:none;">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" onclick="close();" data-dismiss="modal" aria-hidden="true">
                                    &times;
                                </button>
                                <h4 class="modal-title" id="myModalLabel">
                                    审核用户权限申请
                                </h4>
                            </div>
                            <div class="modal-body">
                                <form class="form-horizontal" role="form" method="post" id="form" onsubmit="return false;">
                                    <div class="form-group">
                                        <label for="userId" class="col-sm-3 control-label">用户id</label>
                                        <div class="col-sm-9">
                                            <input type="text" readonly="readonly" class="form-control userid" id="userId" name="id" value="">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="name" class="col-sm-3 control-label">姓名</label>
                                        <div class="col-sm-9">
                                            <input type="text" readonly="readonly" class="form-control" id="name" name="name" value="">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="demandScore" class="col-sm-3 control-label">需求发布积分</label>
                                        <div class="col-sm-9">
                                            <input type="text" class="form-control" readonly="readonly" name="demandScore" value="" id="demandScore"
                                                   placeholder="">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="fetchScore" class="col-sm-3 control-label">代取积分</label>
                                        <div class="col-sm-9">
                                            <input type="text" class="form-control" readonly="readonly" name="fetchScore" value="" id="fetchScore">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="type" class="col-sm-3 control-label">状态</label>
                                        <div class="col-sm-9">
                                                <input class="form-control" readonly="readonly" name="type" id="type"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="applicationStatus" class="col-sm-3 control-label">申请权限</label>
                                        <div class="col-sm-9">
                                            <input class="form-control" readonly="readonly" name="applicationStatus" id="applicationStatus" value=""/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="pic" class="col-sm-3 control-label">附带图片</label>
                                        <div class="col-sm-9">
                                            <img src="" id="pic" style="width: 120px;height: 120px;border: solid 1ch blue" alt="申请理由附带图片">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="sell" class="col-sm-3 control-label">恢复用户权限</label>
                                        <div class="col-sm-9">
                                            <!-- Single button -->
                                            <select class="form-control sell" id="sell" >
                                                <option value="0">同意</option>
                                                <option value="1">不同意</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group showTexArea" style="display: none" id="showTexArea">
                                        <label for="my-textarea" class="col-sm-3 control-label">审核失败原因</label>
                                        <div class="col-sm-9">
                                            <textarea class="form-control my-textarea" rows="1" id="my-textarea"  placeholder="请输入制裁用户的原因"></textarea>
                                        </div>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal" id="closemodify">关闭
                                        </button>
                                        <button type="button" class="btn btn-primary" id="scantion-my-submit">
                                            提交
                                        </button><span id="tip"> </span>
                                    </div>
                                </form>
                            </div>
                        </div><!-- /.modal-content -->
                    </div><!-- /.modal -->
                </div>
            </div>
        </div>
    </div>
</div>
<%--===============================主内容区--end--=========================================--%>

<%--====================================引入js文件--start--==================================--%>
<script src="static/backstage/js/applyScantionUser.js"></script>
<%--引入sweetalert--start--%>
<script src="static/backstage/js/plugins/sweetalert/sweetalert.min.js"></script>
<%--====================================引入js文件--end--==================================--%>
