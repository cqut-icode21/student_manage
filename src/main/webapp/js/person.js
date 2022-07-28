let page = 1;//记录当前页数
let infs//数据库的内容
let xmlHttp;

let table  = document.querySelector("table");
let tbody = document.querySelector("tbody");


window.onload = function (){
    xmlHttp = new XMLHttpRequest();

    xmlHttp.open("GET","/student");
    xmlHttp.send();

    xmlHttp.onreadystatechange = function (){
        if(xmlHttp.readyState===4){
            infs = JSON.parse(xmlHttp.responseText);
            createAll(infs);
        }
    }
}
function createAll(infs){
    let len = infs.length;
    for (let i = (page-1)*10; i < len&&i<page*10; i++) {
        let tr = document.createElement("tr");
        //创建复选框
        createCheckBox(tr);
        //创建序号
        createNumber(tr,i);
        //添加数据
        //学号
        let idTd = document.createElement("td");
        idTd.innerHTML = infs[i].id;
        tr.appendChild(idTd);
        //姓名
        let nameTd = document.createElement("td");
        nameTd.innerHTML = infs[i].name;
        tr.appendChild(nameTd);
        //性别
        let genderTd = document.createElement("td");
        genderTd.innerHTML = infs[i].gender;
        tr.appendChild(genderTd);
        //学院
        let collegeTd = document.createElement("td");
        collegeTd.innerHTML = infs[i].college;
        tr.appendChild(collegeTd);
        //专业
        let majorTd = document.createElement("td");
        majorTd.innerHTML = infs[i].major;
        tr.appendChild(majorTd);
        //年级
        let gradeTd = document.createElement("td");
        gradeTd.innerHTML = infs[i].grade;
        tr.appendChild(gradeTd);
        //班级
        let classTd = document.createElement("td");
        classTd.innerHTML = infs[i].sClass;
        tr.appendChild(classTd);
        //年龄
        let ageTd = document.createElement("td");
        ageTd.innerHTML = infs[i].age;
        tr.appendChild(ageTd);
        //创建操作按钮
        createWorkButton(tr);
        //设置操作按钮的事件
        setWorking(tr);
        tbody.appendChild(tr);
    }
    //鼠标悬停
    changeBackColor(tbody);
}
//创建复选框的方法
function createCheckBox(tr){
    let tempTd = document.createElement('td');
    let tempCheckbox = document.createElement('input');
    tempCheckbox.type = 'checkbox';

    sonChooseAll();
    tempCheckbox.onclick = sonChooseAll;

    tempTd.appendChild(tempCheckbox);
    tr.appendChild(tempTd);
}
//创建序号
function createNumber(tr,index){
    let tempTd = document.createElement('td');
    tempTd.innerHTML = (index+1)+'';
    tempTd.className = 'index';
    tr.appendChild(tempTd);
}
//创建操作按钮
function createWorkButton(tr){
    let tempTd = document.createElement("td");
    let buttonInspect = document.createElement("button");
    let buttonChange = document.createElement("button");
    buttonInspect.className = "workButton";
    buttonInspect.innerHTML = "查看"

    buttonChange.className = "workButton";
    buttonChange.innerHTML = "修改"

    tempTd.appendChild(buttonInspect);
    tempTd.appendChild(buttonChange);
    tempTd.offsetWidth = 100;
    tr.appendChild(tempTd);
}
//设置操作按钮的事件
function setWorking(tr){
    let workButton = tr.querySelectorAll(".workButton");
    workButton[0].onclick = function (){
        let modal = document.createElement("div");
        let td = tr.querySelectorAll("td");
        modal.className = "modal-data";
        modal.innerHTML = "<div class=\"title2\">查看人员信息</div>" +
            " <div class=\"inf\">" +
            " 学号:<input type=\"text\" disabled=true><br><br>" +
            " 姓名:<input type=\"text\" disabled=true><br><br>" +
            "  性别:<input type=\"text\" disabled=true><br><br>" +
            " 学院:<input type=\"text\" disabled=true><br><br>\n" +
            " 专业:<input type=\"text\" disabled=true><br><br>\n" +
            " 年级:<input type=\"text\" disabled=true><br><br>\n" +
            " 班级:<input type=\"text\" disabled=true><br><br>\n" +
            " 年龄:<input type=\"text\" disabled=true>\n" +
            "        </div>\n" +
            "        <div class=\"buttonBox2\">\n" +
            "            <button onclick='pull()'>取消</button>\n" +
            "        </div>\n"
        let input = modal.querySelectorAll("input");
        for (let i = 0; i < input.length; i++) {
            input[i].placeholder = td[i+2].innerHTML;
        }
        document.body.appendChild(modal);
    }
    workButton[1].onclick = function (){
        let modal = document.createElement("div");
        let td = tr.querySelectorAll("td");
        modal.className = "modal-data";
        modal.innerHTML = "<div class=\"title2\">修改人员信息</div>" +
            " <div class=\"inf\">" +
            " 学号:<input type=\"text\"><br>" +
            " <span class=\"error\">请输入11位阿拉伯数字</span>" +
            "<span class=\'error\'>已有该学号，请重新输入！</span><br>" +
            " 姓名:<input type=\"text\"><br><br>" +
            "  性别:<input type=\"text\"><br>" +
            "<span class=\"error\">请输入男或女</span><br>\n" +
            " 学院:<input type=\"text\"><br><br>\n" +
            " 专业:<input type=\"text\"><br><br>\n" +
            " 年级:<input type=\"text\"><br>\n" +
            "<span class=\'error\'>请输入阿拉伯数字</span><br>\n" +
            " 班级:<input type=\"text\"><br><br>\n" +
            " 年龄:<input type=\"text\"><br>\n" +
            "<span class='error'>请输入阿拉伯数字</span>\n" +
            "        </div>\n" +
            "        <div class=\"buttonBox2\">\n" +
            "            <button>修改</button>\n" +
            "            <button onclick='pull()'>取消</button>    "+
            "        </div>\n"
        let input = modal.querySelectorAll("input");
        for (let i = 0; i < input.length; i++) {
            input[i].value = td[i+2].innerHTML;
            input[i].style.floodColor = "#999999";
        }
        let error = modal.querySelectorAll(".error");
        check(error,input,false);
        document.body.appendChild(modal);
        change(td[2].innerHTML);
    }
}
//修改人员
function change(id2){
    let button = document.querySelector(".modal-data").querySelectorAll("button");
    button[0].onclick = function (){
        let input = document.querySelector(".modal-data").querySelectorAll("input");
        let error = document.querySelector(".modal-data").querySelectorAll(".error");
        let flag = true;
        for (let i = 0; i < input.length; i++) {
            let text = input[i].value;
            console.log(text);
            if (/' '/.test(text) || text === '') {
                flag = false;
                alert("有内容为空或输入包含空格！");
                break;
            }
        }
        if(flag){
            xmlHttp = new XMLHttpRequest();
            //获取信息
            let text = document.querySelector(".modal-data").querySelectorAll("input");
            xmlHttp.open("GET","/change?id="+text[0].value+"&name=" +
                text[1].value+"&gender="+text[2].value+"&college="+text[3].value+
                "&major="+text[4].value+"&grade="+text[5].value+"&sClass="+text[6].value+
                "&age="+text[7].value+"&id2="+id2);
            xmlHttp.send();
            xmlHttp.onreadystatechange = function (){
                if(xmlHttp.readyState===4){
                    if (xmlHttp.responseText==='true'){
                        alert("修改成功");
                        window.location.reload();
                    }else{
                        alert("修改失败")
                    }
                }
            }
        }
    }
}
//设置全选
function chooseAll(){
    let checkBox = document.querySelectorAll("input");
    if(checkBox[1].checked){
        for (let i = 2; i < checkBox.length; i++) {
            checkBox[i].checked = true;
        }
    }else{
        for (let i = 2; i < checkBox.length; i++) {
            checkBox[i].checked = false;
        }
    }
}
//全选
function sonChooseAll(){
   let checkBox = document.querySelectorAll("input");
    for(let j = 2;j<checkBox.length;j++){
        checkBox[j].onclick = function (){
            let flag = true;
            for (let i = 2; i < checkBox.length; i++) {
                if(!checkBox[i].checked){
                    flag = false;
                    break;
                }
            }
            checkBox[1].checked = flag;
        }
    }
}
//当鼠标移动到相应行时的事件
function changeBackColor(tbody){
    let tr = tbody.querySelectorAll('tr');
    for (let i = 0; i < tr.length; i++) {
        tr[i].onmouseover = function (){
            tr[i].style.backgroundColor = 'wheat';
        }
        tr[i].onmouseout = function (){
            tr[i].style.backgroundColor = '';
        }
    }
}
//下一页
function nextPage(){
    if(page*10>=infs.length){
        alert("当前已是最后一页！");
    }else{
        page++;
        tbody.remove();
        tbody = document.createElement("tbody");
        delectAll();
        createAll(infs);
        table.appendChild(tbody);
    }
}
//上一页
function previous(){
    if(page===1){
        alert("当前已为第一页！");
    }else{
        page--;
        tbody.remove();
        tbody = document.createElement("tbody");
        delectAll();
        createAll(infs);
        table.appendChild(tbody)
    }
}
function delectAll(){
    let checkBox = document.querySelector(".Main");
    checkBox.checked = false;
}
//添加人员事件
function addPerson(){
    let modal = document.createElement("div");
    modal.className = "modal-data";
    modal.innerHTML = "<div class=\"title2\">增加人员</div>" +
        " <div class=\"inf\">" +
        " 学号:<input type=\"text\"><br>" +
        " <span class=\"error\">请输入11位阿拉伯数字</span>" +
        "<span class=\'error\'>已有该学号，请重新输入！</span><br>" +
        " 姓名:<input type=\"text\"><br><br>" +
        "  性别:<input type=\"text\"><br>" +
        "<span class=\"error\">请输入男或女</span><br>\n" +
        " 学院:<input type=\"text\"><br><br>\n" +
        " 专业:<input type=\"text\"><br><br>\n" +
        " 年级:<input type=\"text\"><br>" +
        "<span class=\'error\'>请输入阿拉伯数字</span><br>\n" +
        " 班级:<input type=\"text\"><br><br>\n" +
        " 年龄:<input type=\"text\"><br>" +
        "<span class='error'>请输入阿拉伯数字</span>\n" +
        "        </div>\n" +
        "        <div class=\"buttonBox2\">\n" +
        "            <button onclick='addPerson2()'>添加</button>\n" +
        "            <button onclick='pull()'>取消</button>\n" +
        "        </div>\n" +
        "    </div>"
    let input = modal.querySelectorAll("input");
    let error = modal.querySelectorAll(".error");
    check(error,input,true);
    document.body.appendChild(modal);
}
function addPerson2(){
    let input = document.querySelector(".modal-data").querySelectorAll("input");
    let error = document.querySelector(".modal-data").querySelectorAll(".error");
    let flag = true;
    for (let i = 0; i < input.length; i++) {
        let text = input[i].value;
        if (/' '/.test(text) || text === '') {
            flag = false;
            alert("有内容为空或输入包含空格！");
            break;
        }
    }
    if(flag){
        xmlHttp = new XMLHttpRequest();
        //获取信息
        let text = document.querySelector(".modal-data").querySelectorAll("input");
        xmlHttp.open("GET","/add?id="+text[0].value+"&name=" +
            text[1].value+"&gender="+text[2].value+"&college="+text[3].value+
            "&major="+text[4].value+"&grade="+text[5].value+"&sClass="+text[6].value+
            "&age="+text[7].value);
        xmlHttp.send();
        xmlHttp.onreadystatechange = function (){
            if(this.readyState===4){
                if(this.responseText==="true"){
                    alert("添加成功!");
                    window.location.reload();
                }else{
                    alert("添加失败!");
                }
            }
        }
    }
}
function pull(){//取消按钮事件
    let modal = document.querySelector(".modal-data");
    document.body.removeChild(modal);
}
//删除事件
function deletePerson(){
    delectAll();
    let str = "";
    let flag = true;
    let checkBox = document.querySelector("tbody").querySelectorAll("input");
    for (let i = 0; i < checkBox.length; i++) {
        let td = checkBox[i].parentNode.parentNode.querySelectorAll("td");
        if(checkBox[i].checked){
            str+=td[2].innerHTML+",";
            flag = false;
        }
    }
    if(flag){
        alert("未选择删除人员！");
    }else{
        xmlHttp = new XMLHttpRequest();
        xmlHttp.open("GET","/delete?id="+str);
        xmlHttp.send();
        xmlHttp.onreadystatechange = function (){
            if(xmlHttp.readyState===4){
                if(xmlHttp.responseText==="true"){
                    alert("删除成功!");
                    window.location.reload();
                }else{
                    alert("删除失败");
                }
            }
        }
    }
}
//表单验证
function check(error,input,sign){
    input[0].onblur = function (){
        if(sign){
            for (let i = 0; i < infs.length; i++) {
                if(input[0].value===infs[i].id){
                    error[1].style.display = "inline-block";
                    break;
                }
            }
        }
        if(input[0].value.length!==11){
            error[0].style.display = "inline-block";
        }
    }
    input[0].onfocus = function (){
        error[1].style.display = "none";
        error[0].style.display = "none";
    }

    input[2].onblur = function (){
        if(input[2].value!=='女'&&input[2].value!=='男'){
            error[2].style.display = "inline-block";
        }
    }
    input[2].onfocus = function (){
        error[2].style.display = "none";
    }

    input[5].onblur = function (){
        if(/\D|' '/.test(input[5].value)||input[5].value===''){
            error[3].style.display = "inline-block";
        }
    }
    input[5].onfocus = function (){
        error[3].style.display = "none";
    }

    input[7].onblur = function (){
        if(/\D|' '/.test(input[7].value)||input[7].value===''){
            error[4].style.display = "inline-block";
        }
    }
    input[7].onfocus = function (){
        error[4].style.display = "none";
    }
}
//搜索功能
function scout(){
    let input = document.querySelector("input");
    let inf = input.value;
    if(/' '/.test(inf)||inf===''){
        alert("搜索内容为空或包含空格！");
    }else{
        xmlHttp = new XMLHttpRequest();

        xmlHttp.open("GET","/scout?inf="+inf);
        xmlHttp.send();
        xmlHttp.onreadystatechange = function (){
            if(xmlHttp.readyState===4){
                infs = JSON.parse(this.responseText);
                console.log(infs);
                if(infs.length===0){
                    alert("无搜索内容");
                    window.location.reload();
                }else{
                    page = 1;
                    tbody.remove();
                    tbody = document.createElement("tbody");
                    createAll(infs);
                    let buttonBox = document.querySelector(".buttonBox");
                    let button = buttonBox.querySelectorAll("button");
                    let backButton = document.createElement("button");
                    backButton.className = "back";
                    backButton.innerHTML = "返回";
                    backButton.onclick = beBack;
                    buttonBox.insertBefore(backButton,button[0]);
                    table.appendChild(tbody);
                }
            }
        }
    }
}
//返回事件
function beBack(){
    window.location.reload();
}
