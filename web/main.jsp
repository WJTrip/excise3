
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/9/22
  Time: 8:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <link href="css/main.css" rel="stylesheet">
    <title>主界面</title>
</head>
<body>
    <div id="container">
        <div id="header">
            <div id="rightTop">
                当前用户:<span><%=session.getAttribute("currentUserName")%></span>
                <a href="${pageContext.request.contextPath}/LogoutController">[退出]</a>
            </div>
            <div id="menu">
                <img src="images/menu.png" alt="" style="width: 50px;height: 30px;" >
                <ul>
                    <li><a href="#">首页</a></li>
                    <li class="menuDiv"></li>
                    <li><a href="download.jsp">资源下载</a></li>
                    <li class="menuDiv"></li>
                    <li><a href="#">用户管理</a></li>
                    <li class="menuDiv"></li>
                    <li><a href="#">资源管理</a></li>
                    <li class="menuDiv"></li>
                    <li><a href="#">个人中心</a></li>
                </ul>
            </div>
            <hr id="hr">
<%--            <div id="banner"></div>--%>

        </div>
    </div>
</body>
</html>
