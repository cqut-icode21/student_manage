<%--
  Created by IntelliJ IDEA.
  User: 13253
  Date: 2021/7/29
  Time: 20:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>
        数据展示
    </title>
    <style>
        tr,th,td{
            border: solid blanchedalmond;
        }
    </style>
    <script src="../js/data.js"></script>
</head>
<body>
<table style="border: bisque solid">
    <tr>
        <th style="width: 50px"><label>
            <input type="checkbox"/>
        </label></th>
        <th>sno</th>
        <th>sgender</th>
        <th>sdept</th>
        <th>sage</th>
        <th>操作</th>
    </tr>
    <tbody id="tb" >
    </tbody>
</table>

</body>
</html>
