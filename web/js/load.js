function load (){
    let btn=document.getElementById("load");
    let user=document.getElementById("user").value;
    let password=document.getElementById("password").value;
    $.ajax({
        type:"post",
        url:"/load",
        data:{
            "user":user,
            "password":password,
        },
        dataType:"json",
        success(data){
           if(data===1){
               alert("用户名错误或密码错误");
           }else {
             window.location.href=data;
           }
        },
        error(){

        }
    })
}