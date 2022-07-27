<%--
  Created by IntelliJ IDEA.
  User: AE
  Date: 2022/7/22
  Time: 19:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>登陆</title>
  <link href="css/index.css" rel="stylesheet">
</head>
<title>登陆</title>
<body>
<div id="all">
<div id="box">
  <div id="content">
    <div id="in">欢迎来到学生管理系统</div>
    <div id="input-box">
      <p>账户：<input id="id" class="user" type="text" placeholder="请输入账户"></p>
      <p>密码：<input  id="password" class="user" type="password" placeholder="请输入密码"></p>
      <button id="sure" onclick="getDate()">登录</button>
      <p id="error">${error}</p>

    </div>
  </div>
</div>
</div>
</body>
<script src="js/index.js" rel="stylesheet"></script>
<script src="js/table.js" rel="stylesheet"></script>
</html>
