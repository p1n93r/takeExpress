<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--====================================引入css文件--start--==================================--%>
<link href="static/backstage/css/plugins/sweetalert/sweetalert.css" rel="stylesheet">
<%--====================================引入css文件--end--==================================--%>
<style>
    .loginid-color{
        color: #1cc09f;
    }
</style>
<%--===============================主内容区--start--=========================================--%>
<div class="col-lg-12">
    <div style="margin: 0 0 15px;" class="row wrapper border-bottom white-bg page-heading">
    <div class="col-lg-10">
        <ol class="breadcrumb">
            <li>
                <a >Home</a>
            </li>
            <li>
                <a>后台用户管理</a>
            </li>
            <li class="active">
                <strong>审核用户注册申请</strong>
            </li>
        </ol>
    </div>
    <div class="col-lg-2"></div>
</div>
    <div class="ibox float-e-margins">
        <div class="ibox-title">
            <h5>审核用户注册申请...</h5>
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
                <table class="table table-striped table-bordered table-hover register-table">
                    <thead>
                        <tr>
                            <th>注册id</th>
                            <th>电话</th>
                            <th>email</th>
                            <th>账号</th>
                            <th>审核</th>
                        </tr>
                    </thead>
                    <tbody></tbody>
                </table>
                <!--表格--end-->
                <!-- 用户详情-->
                <!-- 用户详情-->
                <div class="modal fade" id="Register-scantion" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" onclick="close();" data-dismiss="modal" aria-hidden="true">
                                    &times;
                                </button>
                                <h4 class="modal-title" id="myModalLabel">
                                    审核用户注册信息
                                </h4>
                            </div>
                            <div class="modal-body">
                                <form class="form-horizontal" role="form" method="post" id="form" onsubmit="return false;">
                                    <div class="form-group">
                                        <label for="registerId" class="col-sm-3 control-label">注册id</label>
                                        <div class="col-sm-9">
                                            <input type="text" readonly="readonly" class="form-control" id="registerId" name="registerId" value="">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="loginId" class="col-sm-3 control-label">登录账号</label>
                                        <div class="col-sm-9">
                                            <input type="text" readonly="readonly" class="form-control" id="loginId" name="loginId" value="">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="phone" class="col-sm-3 control-label">手机号</label>
                                        <div class="col-sm-9">
                                            <input type="text" class="form-control" readonly="readonly" name="phone" value="" id="phone"
                                                   placeholder="手机号">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="email" class="col-sm-3 control-label">email</label>
                                        <div class="col-sm-9">
                                            <input type="text" class="form-control" readonly="readonly" name="email" value="" id="email">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="stuPic" class="col-sm-3 control-label">学生证</label>
                                        <div class="col-sm-9">
                                            <img src="" id="stuPic" style="width: 70%;height: 30%;border: solid 1ch blue" alt="学生证">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="sell" class="col-sm-3 control-label">审核</label>
                                        <div class="col-sm-9">
                                            <!-- Single button -->
                                            <select class="form-control sell" id="sell" >
                                                <option  value="0">同意注册</option>
                                                <option  value="1">不同意注册</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group showTexArea" style="display: none" id="showTexArea">
                                        <label for="textareaInfo" class="col-sm-3 control-label">失败原因</label>
                                        <div class="col-sm-9">
                                            <textarea class="form-control" rows="1" id="textareaInfo"  placeholder="请输入注册失败的原因"></textarea>
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
            </div>
        </div>
    </div>
</div>
<%--===============================主内容区--end--=========================================--%>

<%--====================================引入js文件--start--==================================--%>
<script src="static/backstage/js/managerRegister.js"></script>
<script src="static/backstage/js/plugins/sweetalert/sweetalert.min.js"></script>
<%--====================================引入js文件--end--==================================--%>
