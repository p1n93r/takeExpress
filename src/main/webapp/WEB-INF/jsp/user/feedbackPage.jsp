<!-- 用户反馈界面 -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<section id="contact" class="shadow-blue white-bg padding">
    <h3 class="section-title">用户反馈</h3>
    <div class="spacer" data-height="80"></div>
    <!--一个查询回调显示-->
    <div class="messages"></div>
    <div class="row">
        <div class="col-md-4 mb-4 mb-md-0">
            <!-- 站点联系信息 -->
            <div class="contact-info mb-5">
<%--                <i class="icon-phone"></i>--%>
                <span class="iconify" data-icon="fa-solid:phone-square-alt" data-inline="false"></span>
                <div class="details">
                    <h5>Phone</h5>
                    <span>+86 1334 202330</span>
                </div>
            </div>
            <div class="contact-info mb-5">
<%--                <i class="icon-envelope"></i>--%>
                <span class="iconify" data-icon="fa-solid:envelope-open-text" data-inline="false"></span>
                <div class="details">
                    <h5>Email address</h5>
                    <span>pin83r@gmail.com</span>
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
            <form id="contact-form" class="contact-form">
<%--                <div class="messages"></div>--%>
                <div class="row">
                    <div class="column col-md-12">
                        <!-- Email input -->
                        <div class="form-group">
                            <input type="email" class="form-control" id="InputEmail" name="email" placeholder="邮箱地址" required="required" data-error="邮箱地址为空，需要填写~~" />
                            <div class="help-block with-errors"></div>
                        </div>
                    </div>
                    <div class="column col-md-12">
                        <!-- Subject input -->
                        <div class="form-group">
                            <input type="text" class="form-control" id="InputSubject" name="subject" placeholder="邮件主题" required="required" data-error="主题不能为空哦~~" />
                            <div class="help-block with-errors"></div>
                        </div>
                    </div>
                    <div class="column col-md-12">
                        <!-- Message textarea -->
                        <div class="form-group">
                            <textarea name="content" id="contentText" class="form-control" rows="5" placeholder="反馈信息" required="required" data-error="反馈信息还没写嗷~~"></textarea>
                            <div class="help-block with-errors"></div>
                        </div>
                    </div>
                </div>

                <button type="submit" name="submit" id="submit" value="Submit" class="btn btn-default"><span class="iconify" data-icon="ion:paper-plane-outline" data-inline="false"></span>Send Message</button>
                <!-- Send Button -->
            </form>
            <!-- 用户填写反馈信息--end -->
        </div>
    </div>
</section>
