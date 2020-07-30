<%--需求方订单管理--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<section id="demandSideOrder" class="shadow-blue white-bg padding">
    <h3 class="section-title">需求订单管理</h3>
    <div class="spacer" data-height="80"></div>
    <div class="row">
        <!--主内容-->
        <div class="col-12">
            <!--一个查询回调显示-->
            <div class="messages"></div>
            <div id="toolbar" class="toolbar">
                <span class="btn-group btn-group-sm" role="group" id="demandOrderBtnGroup">
                    <!--默认看到的数据为待接单数据-->
                    <button class="btn btn-border-light-blackborder btn-border-light-active">待接单</button>
                    <button class="btn btn-border-light-blackborder">待送达订单</button>
                    <button class="btn btn-border-light-blackborder">退回订单</button>
                    <button class="btn btn-border-light-blackborder">所有订单</button>
                    <button class="btn btn-border-light-blackborder">查看统计</button>
                    <button class="btn btn-border-light-blackborder" isRefresh="true">刷新数据</button>
                </span>
            </div>


            <!--一个表格:显示订单信息-->
            <table id="demandSideTable" style="border-radius: 5px"></table>

        </div>
    </div>
</section>