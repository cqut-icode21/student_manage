let count = document.getElementsByClassName("count")[0];
let password = document.getElementsByClassName("password")[0];
let button = document.getElementById("btn");
count.onfocus = function () {
    count.value = "";
}
count.onblur = function () {
    if (count.value === "")
        count.value = "请输入账户";
}

password.onfocus = function () {
    password.value = "";
}
password.onblur = function () {
    if (password.value === "")
        password.value = "请输入密码";
}
