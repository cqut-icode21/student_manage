let  Account;
let  password;

function signIn(){
    Account =  document.getElementById("账号框").value
    password = document.getElementById("密码框").value

    if (Account===""){
        alert("请先输入账号再登录")
        return
    }
    if (password===""){
        alert("请先输入密码再登录")
        return
    }

    axios({
        url: 'http://localhost:8080/login',
        method: 'GET',
        params: {
            Account:  Account,
            password: password
        }
    })
        .then(
            response => {
                if (response.data){
                    window.location.href = "../html/StudentSystem.html"
                }
                else alert("账号或密码错误")

            },
            error => {
                alert(error.message)
            }
        )


}