<%--
  Created by IntelliJ IDEA.
  User: ZI
  Date: 2022/7/24
  Time: 10:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>登录界面</title>
    <link rel="stylesheet" href="css/register.css">
</head>

<body>
<div class="setBackGround headLine9">
    <form class="setForm">
        <div class="setRegister">
            登录界面
        </div>
        <div class="setAccount">
            <span>
            <img src="img/224用户.png" style="width: 28px" alt="账号" class="setImg">
        </span>
            <label>
                <input class="setInput" placeholder="请输入账号" id="username">
            </label>
        </div>
        <div class="setPassword">
            <span>
            <img src="img/锁定.png" style="width: 28px" alt="密码" class="setImg">
        </span>
            <label>
                <input class="setInput" type="password" placeholder="请输入密码" id="password">
            </label>
        </div>
        <button class="setButtonRegister" onclick="register()">
            登录
        </button>
    </form>
</div>
</body>

<script src="js/register.js"></script>
</html>