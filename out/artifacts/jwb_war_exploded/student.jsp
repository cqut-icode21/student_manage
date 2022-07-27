
<%--
  Created by IntelliJ IDEA.
  User: AE
  Date: 2022/7/22
  Time: 21:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<title>学生信息管理系统</title>
<link href="css/student.css" rel="stylesheet">
</head>
<body>
<div id="title-line"></div>
<div id="title">学生信息管理系统</div>


<div id="change">
    <p><button class="add" onclick="createbox()">添加</button></p>
    <div class="model-box">

        <div id="model-content">
            <p id="title1">人员信息</p>

            <div class="input" id="input-box">
                <p>学号:<input type="text"  name="show" id="text-number"></p>
                <p>姓名:<input type="text"   name="show"  id="text-name"></p>
                <p>性别:<input type="text"   name="show"  id="text-gender"></p>
                <p>学院:<input type="text"   name="show"  id="text-college"></p>
                <p>专业:<input type="text"   name="show" id="text-major"></p>
                <p>年级:<input type="text"   name="show"  id="text-grade"></p>
                <p>班级:<input type="text"   name="show"  id="text-class"></p>
                <p>年龄:<input type="text"   name="show"  id="text-age"></p>
            </div>

            <div class="button-box">
                <div class="sure"> <span><button id="sure-box" onclick="sure1()">确定</button></span></div>
                <span>&nbsp;</span>
                <span>&nbsp;</span>
                <div class="back"><span><button id="back-box" >返回</button></span></div>
                <div class="set"><span><button id="set-box" onclick="outputshow()" >修改</button> </span></div>
                <span>&nbsp;</span>
                <span>&nbsp;</span>
                <div class="return"> <span><button id="return-box" onclick="deinput()">取消</button> </span></div>
            </div>
        </div>
    </div>
    <p>&nbsp;</p>
    <p><button class="delete" onclick="deleterow()">删除</button> </p>
</div>
<div id="search-box"><input type="text" id="search" placeholder="请输入学生姓名"> <button id="search-sure" onclick="searchfor()">搜索</button> </div>


<div>
    <table>
        <thead>
        <tr>
            <th> <input type="checkbox" name="check3" onclick="checkall(this)"></th>
            <th>序号</th>
            <th class="tl1">学号</th>
            <th class="tl2">姓名</th>
            <th class="tl3">性别</th>
            <th class="tl4">学院</th>
            <th class="tl5">专业</th>
            <th class="tl6">年级</th>
            <th class="tl7">班级</th>
            <th class="tl8">年龄</th>
            <th >操作</th>
        </tr>
        </thead>
        <tbody>
        </tbody>
    </table>
</div>
<div id="turn">
    <p><button class="up" onclick="uppage()">上一页</button></p>
    <p id="page">&nbsp;</p>
    <p><button class="next" onclick="nextpage()">下一页</button></p>
</div>

</body>
<script src="js/table.js" rel="stylesheet"></script>
</html>
