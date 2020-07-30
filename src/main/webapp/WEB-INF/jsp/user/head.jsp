<%--此jsp包含网页的头、seo信息--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="bp" uri="/setBasePath" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>快递代取系统</title>
    <bp:basePath request="<%=request%>"/>
    <!--SEO优化要做好-->
    <meta name="description" content="快递代取系统" />
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
    <link rel="shortcut icon" type="image/x-icon" href="static/user/images/favicon.png" />
    <!-- STYLESHEETS -->
    <link rel="stylesheet" href="static/public/css/bootstrap.min.css" type="text/css" media="all" />
<%--    <link rel="stylesheet" href="static/user/css/all.min.css" type="text/css" media="all" />--%>
    <link rel="stylesheet" href="static/user/css/slick.css" type="text/css" media="all" />
    <link rel="stylesheet" href="static/user/css/jquery.mcustomscrollbar.min.css" type="text/css" media="all" />
    <link rel="stylesheet" href="static/user/css/style.css" type="text/css" media="all" />
    <!--typeahead插件--start-->
    <link rel="stylesheet" href="static/user/css/jquery.typeahead.min.css"/>
    <!--typeahead插件--end-->
    <!--bootstrap-table插件--start-->
    <link rel="stylesheet" href="https://unpkg.com/bootstrap-table@1.15.3/dist/bootstrap-table.min.css"/>
    <!--bootstrap-table插件--end-->
    <!--[if lt IE 9]>
    <script src="static/user/js/html5shiv.min.js"></script>
    <script src="static/user/js/respond.min.js"></script>
    <![endif]-->
</head>