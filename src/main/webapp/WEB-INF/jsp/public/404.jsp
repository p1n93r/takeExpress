<%--
  Created by IntelliJ IDEA.
  User: P1n93r
  Date: 2020/3/16
  Time: 10:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="bp" uri="/setBasePath" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8" />
    <title>找不到降落点啦！</title>
    <bp:basePath request="<%=request%>"/>
    <link rel="stylesheet" href="static/public/css/404.css" />
</head>
<body>
<div class="mars"></div>
<img src="static/public/images/404.svg" class="logo-404" />
<img src="static/public/images/404-meteor.svg" class="meteor" />
<p class="title">啊鸭~!!</p>
<p class="subtitle">飞船降落的地点不对嗷~<br />请重新定位飞船降落地点~~.</p>
<div align="center">
    <a class="btn-back" href="..">回仓</a>
</div>
<img src="static/public//images/404-astronaut.svg" class="astronaut" />
<img src="static/public/images/404-spaceship.svg" class="spaceship" />
</body>
</html>
