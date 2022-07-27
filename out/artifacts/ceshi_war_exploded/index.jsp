<%--
  Created by IntelliJ IDEA.
  User: 龙
  Date: 2022/7/22
  Time: 17:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>学生管理系统</title>
  <link rel="stylesheet" href="css/student.css">
</head>

<body>
<div class="headLine box">
    <span class="emptySpan">
              &nbsp;
        </span>
  <span class="headLineT">
            学生信息管理系统
        </span>
  <span class="emptySpan1">
        </span>
</div>

<div class="box">
  <button class="setButtonAdd" onclick="setStudent(1,0)">新增</button>
  <label style="height: 0">
    <input class="setSearch" id="search">
    <img class="setSearchImage" src=
            "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fpic.16pic.com%2F00%2F19%2F23%2F16pic_1923897_b.jpg&refer=http%3A%2F%2Fpic.16pic.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1659767276&t=198de2e0e15003babe58a15dc9140259"
         width="28" alt="搜索" onclick="search()">
    <button class = "setReturn" onclick= return0()>返回</button>
  </label>
  <button class="setButtonDelete" onclick="setSure()">删除</button>
</div>

<div class="box">
  <table class="setFirstTable">
    <tr id="checkTr">
      <td class="firstRowCheck"><label for="checkId"></label><input type="checkbox" id = "checkId" onclick= allCheck()></td>
      <th class="firstRow">序号</th>
      <th class="firstRowId">学号</th>
      <th class="firstRow">姓名</th>
      <th class="firstRow">性别</th>
      <th class="firstRowId">学院</th>
      <th class="firstRow">专业</th>
      <th class="firstRow">年级</th>
      <th class="firstRow">班级</th>
      <th class="firstRow">年龄</th>
      <th class="firstRow">操作</th>
    </tr>
  </table>
</div>

<div class="box">
  <table class="setTable" id="demo">
  </table>
</div>

<div class="setPage">
  <button class="setButtonPage1" onclick="previousPage()">
    上一页
  </button>
  <span id="num"></span>
  <button class="setButtonPage2" onclick="nextPage()">
    下一页
  </button>
</div>

<div class="box">
  <form class="setAddForm" id="addForm" style="display: none">
    <div id="title" class="setFirst">
    </div>
    <div class="setBack">
      <label>学号 : </label>
      <label for="number"></label><input id="number">
    </div>
    <div class="setBack">
      <label>姓名 : </label>
      <label for="name"></label><input id="name">
    </div>
    <div class="setBack">
      <label>性别 : </label>
      <label for="gender"></label><input id="gender">
    </div>
    <div class="setBack">
      <label>学院 : </label>
      <label for="college"></label><input id="college">
    </div>
    <div class="setBack">
      <label>专业 : </label>
      <label for="major"></label><input id="major">
    </div>
    <div class="setBack">
      <label>年级 : </label>
      <label for="grade"></label><input id="grade">
    </div>
    <div class="setBack">
      <label>班级 : </label>
      <label for="class"></label><input id="class">
    </div>
    <div class="setBack">
      <label>年龄 : </label>
      <label for="age"></label><input id="age">
    </div>
    <div class="setBack" id="button">
    </div>
  </form>
</div>

<div>
  <div id = "sureDiv" class="setSure" style="display: none"></div>
</div>

</body>
<script src="js/student.js"></script>
<script src="jQuery/jquery.js" type="text/javascript"></script>
</html>