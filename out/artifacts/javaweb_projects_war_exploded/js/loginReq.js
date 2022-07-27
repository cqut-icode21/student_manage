//发送请求方式一
$("#btn").click(function () {
    $.ajax({
        type: "GET",
        url: "/login",
        data: {"username": account.value, "password": password.value},//json对象
        dataType: 'json',
        success: function (text) {//ajax请求dataType值为json，jquery就会把后端返回的字符串尝试通过JSON.parse()尝试解析为js对象。
            if (text === true) {
                window.location.href = "html/table.html";
            } else {
                alert("账号或密码错误");
            }
        },
    })
})
//发送请求方式二
// button.onclick = function () {
//     let xml = new XMLHttpRequest();
// let data = "username=" + account.value + "&" + "password=" + password.value;
//     //初始化打开一个请求。
//     xml.open("get", "/login?" + data, true);
//     xml.send();
//     xml.onreadystatechange = function () {
//         if (xml.readyState === 4) {
//             if (xml.responseText !== "true") {
//                 alert("账号或密码错误")
//             } else {
//                 window.location.href = "html/table.html"
//             }
//         }
//     }
// }

