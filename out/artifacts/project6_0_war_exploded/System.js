// function getData(){
//    axios({
//        url: '/successServLet',
//        method: 'get',
//
//     }).then(
//         response => {
//             console.log(response);
//             users = JSON.parse(response.list);
//             fill();
//         },
//         error => {
//             alert(error.message);
//         }
//     )
//
//
// }

let users = [];
let totalPage = 0; //总页数
let e = 10; //一页数据行数
let totalCount = 0; //总共多少数据
let currentPage = 1; // 当前页面

let firstData = 0; //每个页面第一个元素的下标
let lastData = e - 1; //每一页最后一个元素下标
let j = [];
let data = document.getElementsByClassName("stData");

// let servlet = document.getElementById("httpServlet")

function getData() {
    let xml = new XMLHttpRequest();
    xml.open("get", "/successServLet", true);
    xml.send();
    xml.onreadystatechange = function () {
        if (xml.readyState === 4) {
            let dataR = xml.responseText;

            users = JSON.parse(dataR);
            computeData();
            fill();
        }
    }
}

//计算页数和数据个数
function computeData() {
    totalCount = users.length;
    totalPage = (totalCount % e === 0 ? totalCount / e : Math.floor(totalCount / e) + 1);
    for (let i = j.length; i < totalPage; i++) {
        j.push(false);
    }
}

//每次显示前先清空数据
function deleteTable() {
    let table = document.getElementById("table");
    table.innerHTML = "";
    showHead();
}

//显示表头
function showHead() {
    let table = document.getElementById("table");
    table.innerHTML = " <tr class='color3' style='background: #f8eae2'>" +
        '<th><input type="checkbox" id="all" onClick="select1(this)"></th>' +
        '<th>序号</th>' +
        '<th>学号</th>' +
        '<th>姓名</th>' +
        '<th>性别</th>' +
        '<th>学院</th>' +
        '<th>专业</th>' +
        '<th>年级</th>' +
        '<th>班级</th>' +
        '<th>年龄</th>' +
        '<th>操作</th></tr>';
}

//下一页
function showNextData() {
    //判断是否是边界
    if (currentPage >= totalPage) {
        alert("没有更多记录");
    } else {
        deleteTable();
        firstData += e;
        lastData += e;
        currentPage++;
        document.getElementById("page").innerHTML = "第 " + currentPage + " 页";
        fill();
        if (j[currentPage]) {
            let all = document.getElementById("all");
            all.checked = true;
            select1();
        }
    }
    addNum();
}

//上一页
function showPreData() {
    if (currentPage <= 1) {
        alert("已经是第一页")
    } else {
        deleteTable();
        firstData -= e;
        lastData -= e;
        currentPage--;
        document.getElementById("page").innerHTML = "第 " + currentPage + " 页";
        fill();
        if (j[currentPage]) {
            let all = document.getElementById("all");
            all.checked = true;
            select1();
        }
    }
    addNum();
}


//显示增添页面
function showAddBox() {
    headAdd();
    showBox();
}

function showBox() {
    document.getElementById("addBox").style.display = "block";
}

//添加、查看、修改的 Box 标题和 按钮
function headAdd() {
    document.getElementById("title").innerHTML = "添加学生信息";
    document.getElementById("btBox").innerHTML = "<button onclick=\"add1()\">添加</button>\n" +
        " <button onclick='hidden1()'>取消</button>"
}

function headLook() {
    document.getElementById("title").innerHTML = "查看学生信息";
    document.getElementById("btBox").innerHTML = "<button onclick='hidden1()'>完成</button>"
}

function headChange() {
    document.getElementById("title").innerHTML = "修改学生信息";
    document.getElementById("btBox").innerHTML = "<button onclick='modify()'>确认</button>\n" +
        " <button onclick='hidden1()'>取消</button>"
}

//隐藏增添页面
function hidden1() {
    document.getElementById("addBox").style.display = "none";
    empty();
    emptyInspect();
    for (let i = 0; i < data.length; i++) {
        data[i].readOnly = false;
    }
}

//批量删除信息
function remove1() {
    alert("是否删除信息");
    let box = document.getElementsByClassName("box");

    for (let i = box.length - 1; i >= 0; i--) {

        if (box[i].checked === true) {
            let cells = box[i].parentNode.parentNode.childNodes;
            console.log(cells[1].innerHTML);
            console.log(cells[2].innerHTML);
            box[i].parentNode.parentNode.parentNode.removeChild(box[i].parentNode.parentNode);
            users.splice(cells[1].innerHTML - 1, 1);

            delete1(cells[2].innerHTML);
        }
    }
    if (document.getElementById("table").rows.length === 1) {
        j[currentPage] = false;
        showPreData();
    }

    computeData(users); //计算删除后的数据与页数
    addNum();//重排序号

    deleteTable();//重新排表
    fill();//重填数据
}

//从数据库中数据是否删除
function delete1(num){
    let json = "number=" + num;
    console.log("json" + json);

    let xml = new XMLHttpRequest();
    xml.open("get", "/delete?" + json, true);
    xml.send();
    xml.onreadystatechange = function () {   //监听函数
        if (xml.readyState === 4) {    //readyState表示实例当前状态
            let t = xml.responseText;
            if (t === "true") {       //判断数据库中数据是否删除
                    console.log("成功删除")
            } else alert("出错了")
        }
    }
}

//默认填充
function fill() {
    computeData();
    if (lastData < users.length) {
        for (let i = firstData; i <= lastData; i++) {  //遍历数组
            showResult(i);
        }
    } else {
        for (let i = firstData; i < users.length; i++) {
            showResult(i);
        }
    }
}

//添加数据到表格中
function showResult(a) {
    let table = document.getElementById("table");
    let newRow = table.insertRow();

    let cellSelect = newRow.insertCell();
    let cellNum = newRow.insertCell();
    let cellNumber = newRow.insertCell();
    let cellName = newRow.insertCell();
    let cellGender = newRow.insertCell();
    let cellCollege = newRow.insertCell();
    let cellMajor = newRow.insertCell();
    let cellGrade = newRow.insertCell();
    let cellClass = newRow.insertCell();
    let cellAge = newRow.insertCell();
    let cellDelete = newRow.insertCell();

    cellSelect.innerHTML = "<input type='checkbox' class='box' onclick='select2()'>" //添加选择框
    cellNum.className = "num";
    cellNumber.innerHTML = users[a].number;
    cellNumber.className = "index";
    cellName.innerHTML = users[a].name;
    cellGender.innerHTML = users[a].gender;
    cellCollege.innerHTML = users[a].college;
    cellMajor.innerHTML = users[a].major;
    cellGrade.innerHTML = users[a].grade;
    cellClass.innerHTML = users[a].cla;
    cellAge.innerHTML = users[a].age;
    cellDelete.innerHTML = "<span class='iconfont' onclick='changed(this)'>&#xe543;</span> " +
        "<span class=\"iconfont\" onclick='lookedBox(this)'>&#xec4c;</span>";
    //添加颜色
    color1();
    //添加序号
    addNum();
}

//添加序号
function addNum() {
    let index = document.getElementsByClassName("index");
    let m = [];
    for (let j = 0; j < index.length; j++) {
        for (let i = 0; i < users.length; i++) {
            if (index[j].innerHTML === users[i].number) {
                m.push(i);
            }
        }
    }
    let num = document.getElementsByClassName("num");
    for (let i = 0; i < num.length; i++) {
        num[i].innerHTML = m[i] + 1;
    }
}

//对表格上色
function color1() {
    let table = document.getElementById("table");
    let rows = table.rows;
    for (let i = 1; i < rows.length; i++) {
        if (i % 2 === 0) {
            rows[i].style.background = "#e4fafa";
        } else rows[i].style.background = "#f0f8ea"
    }
}

//全选框事件处理
function select1() {
    let box = document.getElementsByClassName("box");
    let all = document.getElementById("all");
    if (all.checked)
        j[currentPage] = true;
    else j[currentPage] = false;
    for (let i = 0; i < box.length; i++) {
        box[i].checked = all.checked;
    }
}

//判断子框是否全被选择
function select2() {
    let all = document.getElementById("all");
    let box = document.getElementsByClassName("box");
    for (let i = 0; i < box.length; i++) {
        if (!box[i].checked)
            j[currentPage] = false;
        all.checked = false;
    }
    for (let i = 0; i < box.length; i++) {
        if (!box[i].checked) {
            all.checked = false;
            break
        } else {
            if (i === box.length - 1)
                j[currentPage] = true;
            all.checked = true;
        }
    }
}

//添加学生信息
function add1() {
    for (let i = 0; i < data.length; i++) {
        data[i].style.disabled = false;
    }
    if (judge() && judgeInspect()) {  //判空并判断格式是否正确

        let student = new Student(data[0].value, data[1].value, data[2].value, data[3].value,
            data[4].value, data[5].value, data[6].value, data[7].value);

        let json = "number=" + student.number + "&" + "name=" + student.name + "&" + "gender=" + student.gender +
            "&college=" + student.college + "&major=" + student.major + "&grade=" + student.grade +
            "&cla=" + student.cla + "&age=" + student.age;

        let xml = new XMLHttpRequest();

        xml.open("get", "/add?" + json, true);
        xml.send();
        xml.onreadystatechange = function () {
            if (xml.readyState === 4) {
                let t = xml.responseText;

                if (t === "true") {       //判断学号是否存在
                    users.push(student);
                    computeData();
                    if (currentPage === totalPage)
                        showResult(users.length - 1);
                    empty();  //清空文本信息
                    alert("添加完成");
                } else alert("学号已经存在")
            }
        }
    } else alert("请正确填完所有信息");
}

//学生类
class Student {
    constructor(number, name, gender, college, major, grade, class1, age) {
        this.number = number;
        this.name = name;
        this.gender = gender;
        this.college = college;
        this.major = major;
        this.grade = grade;
        this.cla = class1;
        this.age = age;
    }
}

//清空文本信息
function empty() {
    for (let i = 0; i < data.length; i++) {
        data[i].value = "";
    }
}

//链接文本与表格信息
function linkInformation(obj) {
    let cells = obj.parentNode.parentNode.childNodes;
    for (let i = 0; i < data.length; i++) {
        data[i].value = cells[i + 2].innerHTML;
    }
}

//显示查看信息
function lookedBox(obj) {
    headLook();
    showBox();
    linkInformation(obj);
    for (let i = 0; i < data.length; i++) {
        data[i].readOnly = true;
    }
}

//显示修改信息box
let place;

function changed(obj) {
    headChange();
    showBox();
    linkInformation(obj);
    place = obj;
    data[0].readOnly = true;
    for (let i = 1; i < data.length; i++) {
        data[i].readOnly = false;
    }
}

//显示修改界面,确认
function modify() {
    let cells = place.parentNode.parentNode.childNodes;
    if (judge() && judgeInspect()) {  //判空和信息是否正确

        for (let i = 0; i < data.length; i++) {
            cells[i + 2].innerHTML = data[i].value;
        }

        let num = cells[1].innerHTML - 1;    //点击事件发生所在对象的下标

        users[cells[1].innerHTML - 1].number = data[0].value;
        users[cells[1].innerHTML - 1].name = data[1].value;
        users[cells[1].innerHTML - 1].gender = data[2].value;
        users[cells[1].innerHTML - 1].college = data[3].value;
        users[cells[1].innerHTML - 1].major = data[4].value;
        users[cells[1].innerHTML - 1].grade = data[5].value;
        users[cells[1].innerHTML - 1].cla = data[6].value;
        users[cells[1].innerHTML - 1].age = data[7].value;

        let json = "number=" + users[num].number + "&" + "name=" + users[num].name + "&" + "gender=" + users[num].gender +
            "&college=" + users[num].college + "&major=" + users[num].major + "&grade=" + users[num].grade +
            "&cla=" + users[num].cla + "&age=" + users[num].age;

        let xml = new XMLHttpRequest();

        xml.open("get", "/change?" + json, true);
        xml.send();
        xml.onreadystatechange = function () {
            if (xml.readyState === 4) {
                let t = xml.responseText;

                if (t === "true") {
                    alert("修改完成");

                } else alert("出错了")
            }
        }

        empty();//清空文本框
        hidden1();

    } else alert("请正确填完所有信息");
}

//提示框
// 学号格式
let num = data[0];
let inspect0 = document.getElementById("number");
num.onblur = function () {
    if (isNaN(num.value) || num.value.length !== 11) {
        inspect0.innerHTML = "请输入11位数字";
        setStyle(inspect0);
    } else inspect0.innerHTML = "";
}

//性别格式
let gender = data[2];
let inspect2 = document.getElementById("gender");
gender.onblur = function () {
    if (gender.value !== "男" && gender.value !== "女") {
        console.log(age.value);
        inspect2.innerHTML = "请输入男或女";
        setStyle(inspect2);
    } else inspect2.innerHTML = "";
}

// age的格式
let age = data[7];
let inspect7 = document.getElementById("age");
age.onblur = function () {
    if (isNaN(age.value) || age.value < 0 || age.value > 120) {
        inspect7.innerHTML = "请输入数字且在 0 ~ 120 之间";
        setStyle(inspect7);
    } else inspect7.innerHTML = "";
}

//设置提示框格式
function setStyle(obj) {
    obj.style.fontStyle = "0.5px";
    obj.style.color = "red";
}

//判断文本框内容
let inspect = document.getElementsByClassName("inspect");

function judgeInspect() {
    for (let i = 0; i < data.length; i++) {
        if (inspect[i].innerHTML !== "") {
            return false;
        }
    }
    return true
}

//判断文本框内容是否为空
function judge() {
    for (let i = 0; i < data.length; i++) {
        if (data[i].value === "") {
            return false;
        }
    }
    return true;
}

//清空提示框
function emptyInspect() {
    for (let i = 0; i < inspect.length; i++) {
        inspect[i].innerHTML = "";
    }
}

//搜索 --> 正则表达式
let number1 = [];
let students = [];
let next = document.getElementById("next");
let pre = document.getElementById("pre");

function research() {
    let input = document.getElementById("key").value.toString();
    let temp = new RegExp(input);
    for (let i = 0; i < users.length; i++) {
        console.log(temp.test(users[i].number));
        if (temp.test(users[i].number)) {
            students.push(users[i]);
            number1.push(i);
        }
    }
    if (students.length === 0)
        alert("没有相关结果");
    else {
        deleteTable();
        showHead();
        for (let i = 0; i < students.length; i++) {
            showResult(number1[i]);
        }
    }
    //currentPage = totalPage;
    next.disabled = true;
    pre.disabled = true;
    students.splice(0, students.length);
    number1.length = 0;
}

//返回
function return1() {
    next.disabled = false;
    pre.disabled = false;
    deleteTable();
    showHead();
    fill();
}