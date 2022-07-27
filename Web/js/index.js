let sure=document.getElementById('sure');
sure.onclick=function () {
    let username = document.getElementById('id').value;
    if (username == null || username === "") {
        document.getElementById('error').innerHTML = "账户不能为空!"
        return;
    }
    let password = document.getElementById('password').value;
    if (password == null || password === "") {
        document.getElementById('error').innerHTML = "密码不能为空!"
        return;
    }
    window.location.href="stu?username="+username+"&password="+password;
}