function login() {
    let username = document.getElementById("username").value
    let password = document.getElementById("password").value
    // console.log(username);
    let xml = new XMLHttpRequest();
    let cookie = document.cookie;
    let data = "username=" + username + "&" + "password=" + password
    xml.open("get", "login?" + data)
    xml.send()
    xml.onreadystatechange = function () {
        if (xml.readyState === 4) {
            if (xml.responseText !== "true") {
                console.log(xml.responseText)
                alert("账号或密码错误")
            }else {
                window.location.href = "../html/data.html"
            }
        }
    }
}