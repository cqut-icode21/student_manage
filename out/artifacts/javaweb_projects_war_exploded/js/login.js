let account = document.getElementsByClassName("count")[0];
let password = document.getElementsByClassName("password")[0];
//聚焦状态时账号栏value为空
account.onfocus = function () {
    account.value = "";
}
//失焦状态时若账号栏为空则value变为提示输入语句
account.onblur = function () {
    if (account.value === "")
        account.value = "请输入账号";
}
//聚焦状态时密码栏value为空
password.onfocus = function () {
    password.value = "";
}
//失焦状态时若密码栏为空则value变为提示输入语句
password.onblur = function () {
    if (password.value === "")
        password.value = "请输入密码";
}

