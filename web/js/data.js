//总人员
let students = []
//当前页数
let page=1;
//总页数
let allPage="1";
//所有学号
let ids= []
//全选框数据
let allCheck = []
//模式
let model = 1;
let seaPage=1;
let seaAll=1;
let seaShow = [];
//得到显示信息
function getData(page) {
    let xml = new XMLHttpRequest();
    xml.open("get", "/operation?currentPage=" + page+"&action=find")
    xml.send()
    xml.onreadystatechange = function () {
        if (xml.readyState === 4) {
            let text = xml.response
            let a = JSON.parse(text)
            students = a.data
            renderData(page,students)
            getBot(page)
            getAllId()
            setC()
            checkClick()
            judge()
        }
    }
}
getData(page)
function renderData(page) {
    if (model===1) {
        let table = "";
        for (let i = 0; i < students.length; i++) {
            let str = "<ul>";
            str += "<li><input type=\"checkbox\" id=\"c" + (10 * (page - 1) + i + 1) + "\" class=\""+students[i].id+"\"></li>";
            str += "<li class=\"num\">" + (10 * (page - 1) + i + 1) + "</li>";
            str += "<li class=\"stu-num\">" + students[i].id + "</li>";
            str += "<li class=\"name\">" + students[i].name + "</li>";
            str += "<li class=\"sex\">" + students[i].sex + "</li>";
            str += "<li class=\"sch\">" + students[i].college + "</li>";
            str += "<li class=\"maj\">" + students[i].professional + "</li>";
            str += "<li class=\"gra\">" + students[i].grade + "</li>";
            str += "<li class=\"cla\">" + students[i].sClass + "</li>";
            str += "<li class=\"age\">" + students[i].age + "</li>";
            str += "<li class=\"ope\"><span class=\"" + students[i].id + "\" onclick=setStudent(" + students[i].id + ")>修改 </span><span class=\"" + students[i].id + "\" onclick=findStudent(" + students[i].id + ")> 查看</span></li>";
            str += "</ul>";
            table += str;
        }
        document.getElementsByClassName("table")[0].innerHTML = table;
    }
}
    //初始化记录数组
{
    for (let i=0;i<ids.length;i++){
        allCheck[i]=false;
    }
}
    //得到页眉
    function getBot(page) {
        let xml1 = new XMLHttpRequest();
        xml1.open("get", "/Message?action=getAllPage");
        xml1.send();
        xml1.onreadystatechange = function () {
            if (xml1.readyState === 4) {
                allPage = xml1.responseText;
                let text;
                text = "第 " + page + " 页 , 共 " + allPage + " 页";
                document.getElementById("showPage").innerText = text;
                document.getElementsByClassName("table-bot")[0].style.display = 'block';
            }
        }
    }
//    上翻页
        document.getElementsByClassName("last")[0].onclick=function  turnUp(){
        if (page === 1)
            alert("当前为第一页！");
        else {
            down()
            page--;
            getData(page);
        }
}
//    下翻页
        document.getElementsByClassName("next")[0].onclick=function  turnDown(){
        if (page == allPage)
            alert("当前为最后页！");
        else {
            down()
            page++;
            getData(page);
        }
    }
//        返回按钮
function back() {
    document.getElementsByClassName("con-ope")[0].style.display = 'none';
    document.getElementsByClassName("err-num")[0].style.display = 'none';
    document.getElementsByClassName("err-sex")[0].style.display = 'none';
    document.getElementsByClassName("numI")[0].value = "";
    document.getElementsByClassName("namI")[0].value = "";
    document.getElementsByClassName("sexI")[0].value = "";
    document.getElementsByClassName("schI")[0].value = "";
    document.getElementsByClassName("majI")[0].value = "";
    document.getElementsByClassName("graI")[0].value = "";
    document.getElementsByClassName("claI")[0].value = "";
    document.getElementsByClassName("ageI")[0].value = "";
    document.getElementsByClassName("numI")[0].onclick=null;
    wantSet();
}
function wantSet() {
    document.getElementsByClassName("ageI")[0].readOnlu =false;
    document.getElementsByClassName("claI")[0].readOnly = false;
    document.getElementsByClassName("graI")[0].readOnly = false;
    document.getElementsByClassName("majI")[0].readOnly = false;
    document.getElementsByClassName("schI")[0].readOnly = false;
    document.getElementsByClassName("sexI")[0].readOnly = false;
    document.getElementsByClassName("namI")[0].readOnly = false;
    document.getElementsByClassName("numI")[0].readOnly = false;
}
//        增加按钮事件
        document.getElementsByClassName("add")[0].onclick=function showAdd(){
            document.getElementsByClassName("con-ope")[0].style.display="block";
            document.getElementsByClassName("ope-title")[0].innerHTML="新增学员信息";
            document.getElementsByClassName("choice-add")[0].style.display="block";
            document.getElementsByClassName("choice-rev")[0].style.display="none";
            document.getElementsByClassName("choice-see")[0].style.display="none";
        }
//        增加人员
        function addStudent() {
            let num, nam, gen, col, maj, gra, clas, ag;
            if (judgeIn()) {
                num = document.getElementsByClassName("numI")[0].value;
                nam = document.getElementsByClassName("namI")[0].value;
                gen = document.getElementsByClassName("sexI")[0].value;
                col = document.getElementsByClassName("schI")[0].value;
                maj = document.getElementsByClassName("majI")[0].value;
                gra = document.getElementsByClassName("graI")[0].value;
                clas = document.getElementsByClassName("claI")[0].value;
                ag = document.getElementsByClassName("ageI")[0].value;

                let count=0;
                for (let i=0;i<ids.length;i++) {
                    if (num === ids[i].id)
                    {count++;break;}
                        }
                if (count===0) {
                    let xml = new XMLHttpRequest();
                    xml.open("get", "/operation?add-num=" + num + "&add-name=" + nam + "&add-sex="
                        + gen + "&add-college=" + col + "&add-major=" + maj + "&add-grade=" + gra + "&add-class=" + clas + "&add-age=" + ag + "&action=add");
                    xml.send();
                    xml.onreadystatechange = function () {
                        if (xml.readyState === 4) {
                            let text = xml.responseText;
                            if (text === "true")
                                alert("添加成功");
                            else {
                                alert("添加失败");
                            }
                            getData(page);
                            back();
                        }
                    }
                }
                else alert("输入学号已存在");
            }
            else alert("输入有空或输入出错");
 }
//删除人员
function deleteStudent(){
    let res;
    let tmp=[];
    let int=0;
    let x;
    if (model===2) {
        x = seaShow.length;
        page = 1;
    }
    else
        x=students.length;
        for (let i=0;i<x;i++){
            if(document.getElementById("c" + (((page - 1) * 10) + 1 + i)).checked){
                int++;
                tmp.splice(0,0, (((page - 1) * 10) + 1 + i));
            }
        }
        let nums = []
    for (let i=0;i<int;i++){
        nums[i]=document.getElementById("c" + tmp[i]).className;
    }
    let count=0;
    for (let i=0;i<nums.length;i++) {
        let xml = new XMLHttpRequest();
        xml.open("get", "/operation?action=delete&deleteInfo=" + nums[i]);
        xml.send();
        xml.onreadystatechange = function () {
            if (xml.readyState === 4) {
                res=xml.responseText;
                if (res==="false")
                    count++;
            }
        }
    }
if (count === 0)
        alert("删除成功");
    else {
        alert("删除失败");
    }
    if (model===2)
        backMa();
    else getData(page);
}
//查看框
function findStudent(i){
    document.getElementsByClassName("con-ope")[0].style.display = 'block';
    document.getElementsByClassName("ope-title")[0].innerHTML = "查询学员信息";
    document.getElementsByClassName("choice-add")[0].style.display = 'none';
    document.getElementsByClassName("choice-rev")[0].style.display = 'none';
    document.getElementsByClassName("choice-see")[0].style.display = 'flex';
    see(i);
}
//查看人员
 function see(i) {
     let xml=new XMLHttpRequest();
     xml.open("get", "/operation?action=see&seeInfo=" + i);
     xml.send();
     xml.onreadystatechange = function () {
         if (xml.readyState === 4) {
             let text = xml.response
             let a = JSON.parse(text)
             let student = a.data
             addInto(student)
         }
     }
 }
 function addInto(student){
     document.getElementsByClassName("numI")[0].value = student.id;
     document.getElementsByClassName("namI")[0].value = student.name;
     document.getElementsByClassName("sexI")[0].value = student.sex;
     document.getElementsByClassName("schI")[0].value = student.college;
     document.getElementsByClassName("majI")[0].value = student.professional;
     document.getElementsByClassName("graI")[0].value = student.grade;
     document.getElementsByClassName("claI")[0].value = student.sClass;
     document.getElementsByClassName("ageI")[0].value = student.age;
     if (document.getElementsByClassName("ope-title")[0].innerHTML === "查询学员信息")
         not();
     if (document.getElementsByClassName("ope-title")[0].innerHTML === "修改学员信息")
         document.getElementsByClassName("numI")[0].readOnly = true;
 }
 function not() {
     document.getElementsByClassName("ageI")[0].readOnlu = true;
     document.getElementsByClassName("claI")[0].readOnly = true;
     document.getElementsByClassName("graI")[0].readOnly = true;
     document.getElementsByClassName("majI")[0].readOnly = true;
     document.getElementsByClassName("schI")[0].readOnly = true;
     document.getElementsByClassName("sexI")[0].readOnly = true;
     document.getElementsByClassName("namI")[0].readOnly = true;
     document.getElementsByClassName("numI")[0].readOnly = true;
 }
// 弹修改框
function setStudent(i){
    document.getElementsByClassName("con-ope")[0].style.display = 'block';
    document.getElementsByClassName("ope-title")[0].innerHTML = "修改学员信息";
    document.getElementsByClassName("choice-add")[0].style.display = 'none';
    document.getElementsByClassName("choice-see")[0].style.display = 'none';
    document.getElementsByClassName("choice-rev")[0].style.display = 'block';
    see(i);
    document.getElementsByClassName("numI")[0].onclick= function () {
        alert("学号不允许修改");
    }
}
//修改人员
function set() {
    let num, nam, gen, col, maj, gra, clas, ag;
    if (judgeIn()) {
        num = document.getElementsByClassName("numI")[0].value;
        nam = document.getElementsByClassName("namI")[0].value;
        gen = document.getElementsByClassName("sexI")[0].value;
        col = document.getElementsByClassName("schI")[0].value;
        maj = document.getElementsByClassName("majI")[0].value;
        gra = document.getElementsByClassName("graI")[0].value;
        clas = document.getElementsByClassName("claI")[0].value;
        ag = document.getElementsByClassName("ageI")[0].value;

        let xml = new XMLHttpRequest();
        xml.open("get", "/operation?id=" + num + "&set-name=" + nam + "&set-sex="
                + gen + "&set-college=" + col + "&set-major=" + maj + "&set-grade=" + gra + "&set-class=" + clas + "&set-age=" + ag + "&action=set");
        xml.send();
        xml.onreadystatechange = function () {
            if (xml.readyState === 4) {
                let text = xml.responseText;
                if (text === "true")
                    alert("修改成功");
                else {
                    alert("修改失败");
                }
                getData(page);
                back();

                }
            }
    }
    else alert("输入有空或输入出错");

}
//判断输入
function isNum(){
    let num_in=document.getElementsByClassName("numI")[0].value;
    return !(num_in === "" || num_in.length !== 11);
}
function isNam(){
    let nam_in=document.getElementsByClassName("namI")[0].value;
    return nam_in !== "";
}
function isSex(){
    let sex_in=document.getElementsByClassName("sexI")[0].value;
    return sex_in === "男" || sex_in === "女"|| sex_in ==="male"||sex_in==="female";
}
function isSch(){
    let sch_in=document.getElementsByClassName("schI")[0].value;
    return sch_in !== "";
}
function isMaj(){
    let maj_in=document.getElementsByClassName("majI")[0].value;
    return maj_in !== "";
}
function isGra(){
    let gra_in=document.getElementsByClassName("graI")[0].value;
    return gra_in !== "";
}
function isCla(){
    let cla_in=document.getElementsByClassName("claI")[0].value;
    return cla_in !== "";
}
function isAge(){
    let age_in=document.getElementsByClassName("ageI")[0].value;
    return age_in !== "";
}
function judgeIn(){
    if(!isNum())
        return false;
    if(!isAge())
        return false;
    if(!isCla())
        return false;
    if(!isSex())
        return false;
    if(!isGra())
        return false;
    if(!isMaj())
        return false;
    if(!isNam())
        return false;
    return isSch();
}
//得到所有学号
function  getAllId(){
    let xml=new XMLHttpRequest();
    xml.open("get","/Message?action=findAllId");
    xml.send();
    xml.onreadystatechange=function (){
        if (xml.readyState===4){
            let text = xml.response;
            let a = JSON.parse(text)
            ids = a.data
        }
    }
}
//显示错误
document.getElementsByClassName("numI")[0].onchange=function (){
    if (!isNum())
        document.getElementsByClassName("err-num")[0].style.display='block';
    else {
            document.getElementsByClassName("err-num")[0].style.display='none';
    }
}
document.getElementsByClassName("sexI")[0].onchange=function (){
    if (!isSex())
        document.getElementsByClassName("err-sex")[0].style.display='block';
    else document.getElementsByClassName("err-sex")[0].style.display='none';
}
//当前页全选
function checkAll() {
    let x;
    if (model===1)
        x=students.length;
    else x=seaShow.length;
        for (let i = 0; i < x; i++) {
            document.getElementById("c" + (((page - 1) * 10) + 1 + i)).checked = document.getElementsByClassName("checkAll")[0].checked;
        }
}
//保留当前页数据
function down(){
        for (let i = 0; i < students.length; i++) {
                allCheck[(((page - 1) * 10) + i)] = document.getElementById("c" + (((page - 1) * 10) + 1 + i)).checked;
        }
}
//改勾选框状态
function setC(){
        for (let i = 0; i < students.length; i++) {
                document.getElementById("c" + (((page - 1) * 10) + 1 + i)).checked = allCheck[(((page - 1) * 10) + i)];
            }
}
//全选框完善
function checkClick(){
    let x;
    if (model===1)
        x=students.length;
    else {x=seaShow.length;page=1}
        for (let i = 0; i < x; i++){
                document.getElementById("c" + ((page - 1) * 10 + i + 1)).onclick = function () {
                    judge();
                }
        }
}
function judge(){
    let chAll=0;
    let x;
    if (model===1)
        x=students.length;
    else {x=seaShow.length;page=1}
    for (let i = 0; i < x; i++){
        if (!(document.getElementById("c" + (((page - 1) * 10) + 1 + i)).checked))
            chAll++;
    }
    document.getElementsByClassName("checkAll")[0].checked = chAll === 0;
}
//搜索

function search(){
    model = 2;
    let tar=document.getElementsByClassName("search-in")[0].value;
    let nums = [];

    for (let i=0;i<ids.length;i++){
        if (ids[i].id.match(tar) != null)
            nums.push(ids[i].id);
    }
    for (let i=0;i<nums.length;i++){
        document.getElementsByClassName("table-bot")[0].style.display = 'none';
        let xml=new XMLHttpRequest();
        xml.open("get", "/operation?action=see&seeInfo=" + nums[i]);
        xml.send();
        xml.onreadystatechange = function () {
            if (xml.readyState === 4) {
                let text = xml.response
                let a = JSON.parse(text)
                let student = a.data
                seaShow.push(student);

                    let table = "";
                    for (let i = 0; i < seaShow.length; i++) {
                        let str = "<ul>";
                        str += "<li><input type=\"checkbox\" id=\"c" + (10 * (seaPage - 1) + i + 1) + "\" class=\""+seaShow[i].id+"\"></li>";
                        str += "<li class=\"num\">" + (10 * (seaPage - 1) + i + 1) + "</li>";
                        str += "<li class=\"stu-num\">" + seaShow[i].id + "</li>";
                        str += "<li class=\"name\">" + seaShow[i].name + "</li>";
                        str += "<li class=\"sex\">" + seaShow[i].sex + "</li>";
                        str += "<li class=\"sch\">" + seaShow[i].college + "</li>";
                        str += "<li class=\"maj\">" + seaShow[i].professional + "</li>";
                        str += "<li class=\"gra\">" + seaShow[i].grade + "</li>";
                        str += "<li class=\"cla\">" + seaShow[i].sClass + "</li>";
                        str += "<li class=\"age\">" + seaShow[i].age + "</li>";
                        str += "<li class=\"ope\"><span class=\"rev" + (10 * (seaPage - 1) + i) + "\" onclick=setStudent(" + seaShow[i].id + ")>修改 </span><span class=\"see" + (10 * (seaPage - 1) + i) + "\" onclick=findStudent(" + seaShow[i].id + ")> 查看</span></li>";
                        str += "</ul>";
                        table += str;
                    }
                    document.getElementsByClassName("table")[0].innerHTML = table;
                    checkClick()
                    checkAll()
            }
        }
    }
        document.getElementsByClassName("back-main")[0].style.display = 'inline-block';

}
function backMa(){
    seaShow.splice(0 ,seaShow.length);
    model=1;
    page=1;
    getData(page);
    document.getElementsByClassName("back-main")[0].style.display='none';
    document.getElementsByClassName("search-in")[0].value="";
    seaPage=1;
}