button.onclick = function () {
    let xml = new XMLHttpRequest();
    let data = "username=" + count.value + "&" + "password=" + password.value
    xml.open("get", "/login?" + data, true)
    xml.send()
    xml.onreadystatechange = function () {
        if (xml.readyState === 4) {
            if (xml.responseText !== "true") {
                alert("账号或密码错误")
            } else {
                window.location.href = "html/main.html"
            }
        }
    }
}


