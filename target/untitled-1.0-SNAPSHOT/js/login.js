//获取密码和账号输入框
let text = document.querySelectorAll(".text");
let error = document.querySelectorAll(".error");//获取错误信息

document.querySelector(".into").onclick = function (){
    console.log(text[0].value)
    console.log(text[1].value)
    if(text[0].value.match("\s")||text[0].value===""){
        error[0].style.display = "block";
    }
    if(text[1].value.match("\s")||text[1].value===""){
        error[1].style.display = "block";
    }else{
        let xmlHttp = new XMLHttpRequest();
        //发送请求
        xmlHttp.open("GET","/servlet?username="+text[0].value+"&password="+text[1].value);
        xmlHttp.send();
        //获取
        xmlHttp.onreadystatechange = function (){
            if(xmlHttp.readyState===4){
                if(xmlHttp.responseText==='false'){
                    alert("账号或密码错误!");
                }else{
                    alert("登录成功！");
                    window.location.href = "../html/person.html";
                }
            }
        }
    }
}

for (let i = 0; i < 2; i++) {
    text[i].onblur = function (){
        if(text[i].value.match("\s")||text[i].value===""){
            error[i].style.display = "block";
        }else{
            error[i].style.display = "none";
        }
    }
}