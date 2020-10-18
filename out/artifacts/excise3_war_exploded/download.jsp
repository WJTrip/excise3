<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/9/22
  Time: 8:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>教学资源下载</title>
    <link rel="stylesheet" href="css/download.css" type="text/css">
</head>
<body>
    <div id="rightTop"><a href="main.jsp">返回首页</a></div>
    <h1>教学资源下载</h1>
    <div class="container">
        <ul>
            <li>
                <p class="name">《java学习》（word版）</p>
                <div class="pic-txt">
                    <img src="images/docx.png" alt="" class="img-area"/>
                    <p class="text-area">
                        <span> java学习教案</span>
                    </p>
                </div>
                <a href="${pageContext.request.contextPath}/DownloadController?id=1" class="dl-btn" title="点击下载">下载</a>
            </li>
        </ul>
        <ul>
            <li>
                <p class="name">《web学习》(xlsx版)</p>
                <div class="pic-txt">
                    <img src="images/xlsx.png" alt="" class="img-area" />
                    <p class="text-area">
                        <span>web学习教案</span>
                    </p>
                </div>
                <a href="${pageContext.request.contextPath}/DownloadController?id=2" class="dl-btn" title="点击下载">下载</a>
            </li>
        </ul>
    </div>
</body>
</html>
