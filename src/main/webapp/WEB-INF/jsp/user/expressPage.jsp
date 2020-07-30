<!-- 快递查询界面 -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<section id="expressPage" class="shadow-blue white-bg padding">
    <h3 class="section-title">查询快递</h3>
    <div class="spacer" data-height="80"></div>
    <div class="col-10 offset-1">
        <!-- 快递查询表单--start -->
        <form id="express-form" class="contact-form" method="post" action="">
            <!--一个查询回调显示-->
            <div class="messages"></div>
                <!-- 快递编号 -->
                <div class="form-group typeahead__container">
                        <input type="text" id="expressNo" autocomplete="off" class="form-control" name="no" placeholder="快递单号" required="required" data-error="必须输入快递单号" />
                        <div class="help-block with-errors"></div>
                </div>
            <button type="submit" id="postQueryExpress" class="btn btn-default"><span class="iconify" data-icon="ion:search-outline" data-inline="false"></span>查询</button>
        </form>
        <!-- 快递查询表单--end -->
    </div>
    <br/>

    <!--快递的一些基本信息-->
    <div class="message" id="express-msg"></div>
    <br/>
    <!-- 快递时间轴 -->
    <div class="timeline" id="express-timeline"></div>
</section>