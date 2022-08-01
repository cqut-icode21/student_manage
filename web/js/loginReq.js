button.onclick = function () {
    let xml = new XMLHttpRequest();
    let data = "username=" + count.value + "&" + "password=" + password.value
    xml.open("get", "/login?" + data, true)
    xml.send()
    xml.onreadystatechange = function () {
        /*请求已完成且响应已就绪*/
        if (xml.readyState === 4) {
            console.log(xml.responseText)
            if (xml.responseText !=="true") {
                alert("账号或密码错误")
            } else {
                window.location.href = "html/table.html"
            }
        }
    }
}
