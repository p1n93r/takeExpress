<!-- 用户反馈界面 -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="static/backstage/css/plugins/sweetalert/sweetalert.css" rel="stylesheet"/>
<section id="applicationJurisdiction" class="shadow-blue white-bg padding">
    <h3 class="section-title">权限申请</h3>
    <div class="spacer" data-height="80"></div>
    <!--一个查询回调显示-->
    <div class="messages"></div>
    <div class="row">
        <div class="col-md-4 mb-4 mb-md-0">
            <!-- 站点联系信息 -->
            <div class="contact-info mb-5">
                <input value="${user.userId}" style="display: none" id="userID">
<%--                <i class="icon-phone"></i>--%>
                <span class="iconify" data-icon="fa-solid:phone-square-alt" data-inline="false"></span>
                <div class="details">
                    <h5>Phone</h5>
                    <span>+86 ${user.phone}</span>
                </div>
            </div>
            <div class="contact-info mb-5">
<%--                <i class="icon-envelope"></i>--%>
                <span class="iconify" data-icon="fa-solid:envelope-open-text" data-inline="false"></span>
                <div class="details">
                    <h5>Email address</h5>
                    <span>${user.email}</span>
                </div>
            </div>
            <div class="contact-info">
<%--                <i class="icon-location-pin"></i>--%>
                <span class="iconify" data-icon="fa-solid:house-user" data-inline="false"></span>
                <div class="details">
                    <h5>Location</h5>
                    <span>天津理工大学华信软件学院</span>
                </div>
            </div>
        </div>
        <div class="col-md-8">
            <!-- 用户填写反馈信息--start -->
            <form id="contact-form" class="contact-form" enctype="multipart/form-data">
<%--                <div class="messages"></div>--%>
                <div class="row">
                    <div class="column col-md-12">
                        <div class="form-group">
                            <span class="form-control" readonly="readonly" name="status" id="status">
                                <c:if test="${user.status==0}">信誉良好</c:if>
                                <c:if test="${user.status==1}">发布需求权限已被限制</c:if>
                                <c:if test="${user.status==2}">代取权限已被限制</c:if>
                                <c:if test="${user.status==3}">所有权限已被限制</c:if>
                            </span>
                        </div>
                    </div>
                    <div class="column col-md-12">
                        <div class="form-group">
                            <select class="form-control sell">
                                <option value="1">恢复发布需求权限</option>
                                <option value="2">恢复代取权限</option>
                                <option value="0">恢复所有权限</option>
                            </select>
                            <div class="help-block with-errors"></div>
                        </div>
                    </div>
                    <div class="column col-md-12">
                        <!-- Message textarea -->
                        <div class="form-group">
                            <textarea name="content" id="application-Text" class="form-control" rows="5" placeholder="申请理由" required="required" ></textarea>
                        </div>
                    </div>
                    <div class="column col-md-12">
                        <div class="form-group">
                            <span><i class="fa fa-user" aria-hidden="true"></i></span>
                            <P>申请图片</P>
                            <input id="file_upload" name="stupic" type="file" style="display: none" accept="image/png,image/jpg,image/gif,image/JPEG"/>
                            <div class="col-md-6 col-lg-6 col-sm-6 image_container" style="text-align: center">
                                <img src="static/visitor/img/upload_img.png" id="preview" style="width: 400px;height: 150px" alt="点击上传图片">
                            </div>
                        </div>
                    </div>

                </div>
                <button type="button" name="submit" id="submit-application" value="Submit" class="btn btn-default"><span class="iconify" data-icon="ion:paper-plane-outline" data-inline="false"></span>application</button>
                <!-- Send Button -->
            </form>
            <!-- 用户填写反馈信息--end -->
        </div>
    </div>
</section>

<%--引入sweetalert--start--%>
<script src="static/backstage/js/plugins/sweetalert/sweetalert.min.js"></script>
