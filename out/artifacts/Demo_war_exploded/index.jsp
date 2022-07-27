<%--
  Created by IntelliJ IDEA.
  User: 13253
  Date: 2021/7/29
  Time: 16:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登陆</title>
    <script src="js/login.js"></script>

</head>

<title>登陆</title>
<body>
<div class="container demo-1">
    <div class="content">
        <div id="large-header" class="large-header">
            <canvas id="demo-canvas"></canvas>
            <div class="logo_box">
                <h3>登陆界面</h3>
                <div class="input_outer">
                    <span class="u_user"></span>
                    <input id="username" class="text" style="color: #FFFFFF !important" type="text"
                           placeholder="请输入账户">
                </div>
                <div class="input_outer">
                    <span class="us_uer"></span>
                    <input id="password" class="text"
                           style="color: #FFFFFF !important; position:absolute; z-index:100;" value=""
                           type="password" placeholder="请输入密码">
                </div>
                <div class="mb2"><p class="act-but submit" onclick="login()" style="color: #FFFFFF">登录</p></div>
            </div>
        </div>
    </div>
</div>
<div style="text-align:center;">
</div>


<link rel="stylesheet" type="text/css" href="css/normalize.css"/>
<link rel="stylesheet" type="text/css" href="css/demo.css"/>
<!--必要样式-->
<link rel="stylesheet" type="text/css" href="css/component.css"/>
<script src="js/TweenLite.min.js"></script>
<script src="js/EasePack.min.js"></script>
<script src="js/rAF.js"></script>
<script src="js/demo-1.js"></script>
</body>

<!--[if IE]>
<script src="js/html5.js"></script>
</html>
