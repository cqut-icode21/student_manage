function register() {
    let username = document.getElementById("username").value
    let password = document.getElementById("password").value
    let xml = new XMLHttpRequest();
    let data = "username=" + username + "&" + "password=" + password
    xml.open("get", "login?" + data)
    xml.send()
    xml.onreadystatechange = function () {
        if (xml.readyState === 4) {
            if (xml.responseText !== "true") {
                alert("账号或密码错误")
            } else {
                window.location.href = "index.jsp"
            }
        }
    }
}