/**
 * @author BaoXiangjie
 */
let date = [];

// 得到数据
function getData() {
    let xml = new XMLHttpRequest();
    xml.open("get", "/getData", true);
    xml.send();
    xml.onreadystatechange = function () {
        let dataR = xml.responseText;
        let json = JSON.parse(dataR);
        date = json.data;
    }
}

getData();
// 储存学生信息
// var date = [
//     {
//     number: "11921380131",
//     name: "蒋锡培",
//     gender: "男",
//     college: "人工智能学院",
//     major: "软件工程",
//     grade: "2019",
//     class: "1",
//     age: "22"
// },{
//     number: "11921380132",
//     name: "向文波",
//     gender: "女",
//     college: "人工智能学院",
//     major: "软件工程",
//     grade: "2019",
//     class: "1",
//     age: "22"
// },{
//     number: "11921380133",
//     name: "牛根生",
//     gender: "男",
//     college: "人工智能学院",
//     major: "软件工程",
//     grade: "2019",
//     class: "1",
//     age: "22"
// },{
//     number: "11921380134",
//     name: "于清教",
//     gender: "女",
//     college: "人工智能学院",
//     major: "软件工程",
//     grade: "2019",
//     class: "1",
//     age: "22"
// },{
//     number: "11921380135",
//     name: "谢清海",
//     gender: "男",
//     college: "人工智能学院",
//     major: "软件工程",
//     grade: "2019",
//     class: "1",
//     age: "22"
// },{
//     number: "11921380136",
//     name: "刘旗辉",
//     gender: "女",
//     college: "人工智能学院",
//     major: "软件工程",
//     grade: "2019",
//     class: "1",
//     age: "22"
// },{
//     number: "11921380137",
//     name: "陈达夫",
//     gender: "男",
//     college: "人工智能学院",
//     major: "软件工程",
//     grade: "2019",
//     class: "1",
//     age: "22"
// },{
//     number: "11921380138",
//     name: "叶茂中",
//     gender: "女",
//     college: "人工智能学院",
//     major: "软件工程",
//     grade: "2019",
//     class: "1",
//     age: "22"
// },{
//     number: "11921380139",
//     name: "李士福",
//     gender: "男",
//     college: "人工智能学院",
//     major: "软件工程",
//     grade: "2019",
//     class: "1",
//     age: "22"
// },{
//     number: "11921380110",
//     name: "王进生",
//     gender: "女",
//     college: "人工智能学院",
//     major: "软件工程",
//     grade: "2019",
//     class: "1",
//     age: "22"
// },{
//     number: "11921380111",
//     name: "任志强",
//     gender: "男",
//     college: "人工智能学院",
//     major: "软件工程",
//     grade: "2019",
//     class: "1",
//     age: "22"
// },{
//     number: "11921380112",
//     name: "孙虹钢",
//     gender: "女",
//     college: "人工智能学院",
//     major: "软件工程",
//     grade: "2019",
//     class: "1",
//     age: "22"
// },{
//     number: "11921380113",
//     name: " 宋新宇",
//     gender: "男",
//     college: "人工智能学院",
//     major: "软件工程",
//     grade: "2019",
//     class: "1",
//     age: "22"
// },{
//     number: "11921380114",
//     name: "翁向东",
//     gender: "女",
//     college: "人工智能学院",
//     major: "软件工程",
//     grade: "2019",
//     class: "1",
//     age: "22"
// }]
var dates = date;

// 渲染当前table，control用于控制渲染开始的位置
var control = 0;

function render(date) {
    tbody = document.querySelector('tbody');
    tbody.remove();
    tbody = document.createElement("tbody");
    var table = document.querySelector('table');
    for (var i = 0; i < 10; i++) {
        if (date[i + 10 * control] == null) break;
        var tr = document.createElement('tr');
        tbody.appendChild(tr);
        var tdCheck = document.createElement('td');
        tdCheck.innerHTML = "<input type='checkbox' style='height:20px'>";
        tr.appendChild(tdCheck);
        var tdNum = document.createElement('td');
        tdNum.innerHTML = i + 10 * control + 1;
        tr.appendChild(tdNum);
        tr.style.height = "55px";
        for (let j in date[i + 10 * control]) {
            var td = document.createElement('td');
            td.innerHTML = date[i + 10 * control][j];
            tr.appendChild(td);
        }
        var tdOperate = document.createElement('td');
        tdOperate.innerHTML = "<img src='../img/修改.svg' margin-right:10px;'><img src='../img/查看.svg' >";
        tdOperate.style.maxWidth = "40px";
        for (var j = 0; j < 2; j++) {
            tdOperate.children[j].index = i + 10 * control + 1;
        }
        tr.appendChild(tdOperate);
        tr.style.height = "55px";
    }
    table.appendChild(tbody);
    var span = document.querySelector('span');
    var text = (control + 1) + '/' + parseInt(date.length / 10 + 1);
    if (date.length % 10 === 0) {
        text = (control + 1) + '/' + parseInt(date.length / 10);
    }
    span.innerHTML = text;
    registerEventListener()
    noAllCheck();
}

render(dates);

// 打开添加学生信息界面
function addInfo() {
    showWindow();
    var lTable = document.querySelector(".lTable");
    lTable.children[0].innerHTML = "添加学生信息";
    lTable.style.display = "block";
    var ul = lTable.querySelector('ul');
    lTable.children[lTable.children.length - 2].onclick = ensureAddInfo;
    var ul = lTable.querySelector('ul');
    for (var i = 0; i < ul.children.length / 2; i++) {
        ul.children[2 * i].childNodes[1].value = "";
        ul.children[2 * i].childNodes[1].readOnly = false;
    }
}

// 确认添加学生信息
function ensureAddInfo() {
    var lTable = document.querySelector(".lTable");
    if (!ensureList(lTable)) return;
    var ul = lTable.querySelector('ul');
    for (var i = 0; i < ul.children.length / 2; i++) {
        if (ul.children[2 * i].childNodes[1].value === "") {
            alert("表单信息不符合要求");
            return false;
        }
    }
    var newInfo = createInfo();
    dates[dates.length] = newInfo;
    $.ajax({
        type: "GET",
        url: "/addReq",
        data: newInfo,
        success: function (text) {
            render(dates);
            changeClose();
            alert("添加成功！");
        }
    })
}

// 新建学生信息
function createInfo() {
    var lTable = document.querySelector(".lTable");
    var ul = lTable.querySelector('ul');
    return {
        id: ul.children[0].childNodes[1].value,
        name: ul.children[2].childNodes[1].value,
        gender: ul.children[4].childNodes[1].value,
        college: ul.children[6].childNodes[1].value,
        major: ul.children[8].childNodes[1].value,
        grade: ul.children[10].childNodes[1].value,
        class: ul.children[12].childNodes[1].value,
        age: ul.children[14].childNodes[1].value
    };
}

// 删除学生信息
function delInfo() {
    var tbody = document.querySelector('tbody');
    var tr = tbody.children;
    let temp = [];
    let count = [];
    for (let i = 0; i < tr.length; i++) {
        if (tr[i].children[0].children[0].checked) {
            temp.push(tr[i].children[1].children[0])
            for (let j = 0; j < date.length; j++) {
                if (tr[i].children[2].innerHTML === date[j].id) {
                    date.splice(j, 1);
                    for (let k = 0; k < dates.length; k++) {
                        if (tr[i].children[2].innerHTML === dates[k].id) {
                            count.push(tr[i].children[2].innerHTML);
                            dates.splice(k, 1);
                        }
                    }
                }
            }
        }
    }
    $.ajax({
        type: "GET",
        url: "/deleteData",
        data: {"count": count},
        success: function (text) {
            if (allCheck.checked) {
                back();
            } else {
                render(dates);
                dates = date;
                document.getElementById('allCheck').checked = false;
            }
            allCheck.checked = false;
        }
    })
}

// 打开修改学生信息界面
function changeInfo() {
    showInfo();
    var lTable = document.querySelector(".lTable");
    lTable.children[0].innerHTML = "修改学生信息";
    lTable.children[lTable.children.length - 2].onclick = ensureChangeInfo;
    var ul = lTable.querySelector('ul');
    for (var i = 1; i < ul.children.length / 2; i++) {
        ul.children[2 * i].childNodes[1].readOnly = false;
    }
}

// 关闭弹窗
function changeClose() {
    var lTable = document.querySelector(".lTable");
    lTable.style.display = "none";
    initial(lTable);
    closeWindow();
}

// 确认修改学生信息
function ensureChangeInfo() {
    var lTable = document.querySelector(".lTable");

    if (!ensureList(lTable)) return;
    var ul = lTable.querySelector('ul');
    var newInfo = {
        id: ul.children[0].childNodes[1].value,
        name: ul.children[2].childNodes[1].value,
        gender: ul.children[4].childNodes[1].value,
        college: ul.children[6].childNodes[1].value,
        major: ul.children[8].childNodes[1].value,
        grade: ul.children[10].childNodes[1].value,
        class: ul.children[12].childNodes[1].value,
        age: ul.children[14].childNodes[1].value
    }
    let jsonObj = [];
    for (let j = 0; j < head.length; j++) {
        jsonObj[head[j]] = newInfo[j].value;
    }
    $.ajax({
        type: "GET",
        url: "/updateReq",
        data: jsonObj,
        success: function (text) {
            for (let i = 0; i < date.length; i++) {
                if (newInfo.id === date[i].id) {
                    date[i] = newInfo;
                    break;
                }
            }
            dates[index + 10 * control] = newInfo;
            render(dates);
            changeClose();
        }
    })

}

// 展示学生信息
function showInfo() {
    showWindow();
    var lTable = document.querySelector(".lTable");
    lTable.children[lTable.children.length - 2].onclick = changeClose;
    lTable.children[0].innerHTML = "学生信息";
    lTable.style.display = "block";
    var ul = lTable.querySelector('ul');
    var tbody = document.querySelector('tbody');
    var tr = tbody.children[index];
    var n = 2;
    for (var i = 0; i < ul.children.length / 2; i++ , n++) {
        ul.children[2 * i].childNodes[1].readOnly = true;
        ul.children[2 * i].childNodes[1].value = tr.children[n].innerHTML;
    }
}

// 给当前页操作事件注册监听
let index;

function registerEventListener() {
    var tbody = document.querySelector('tbody');
    var tr = tbody.children;
    for (var i = 0; i < 2; i++) {
        if (i === 0) {
            for (var j = 0; j < tr.length; j++) {
                tr[j].children[10].children[i].onclick = function () {
                    index = this.index - 1 - 10 * control;
                    changeInfo();
                }
            }
        } else {
            for (var j = 0; j < tr.length; j++) {
                tr[j].children[10].children[i].onclick = function () {
                    index = this.index - 1 - 10 * control;
                    showInfo();
                }
            }
        }
    }
}

// 模糊查询
function search() {
    control = 0;
    var newDates = [];
    var searchText = document.getElementsByClassName("searchSpan")[0];
    if (!searchText.value) return;
    for (var i = 0; i < date.length; i++) {
        for (var key in date[i]) {
            if (date[i][key].search(searchText.value) !== -1)
                newDates.push(date[i]);
        }
    }
    if (newDates.length === 0) {
        alert("No Seaerch!!!");
    } else {
        dates = newDates;
        render(newDates);
    }
}

// 返回
function back() {
    control = 0;
    document.getElementsByClassName("searchSpan")[0].value = "";
    dates = date;
    render(dates);
}

// 选择-全选
var allCheck = document.getElementById('allCheck');
allCheck.onclick = function () {
    var symbolChecks = document.querySelector('tbody');
    var checks = symbolChecks.getElementsByTagName('input');
    for (let i = 0; i < checks.length; i++) {
        checks[i].checked = allCheck.checked;
    }
}

// 选择-反选
function noAllCheck() {
    var symbolChecks = document.querySelector('tbody');
    var checks = symbolChecks.getElementsByTagName('input');
    for (let i = 0; i < checks.length; i++) {
        checks[i].onclick = function () {
            var flag = true;
            for (let i = 0; i < checks.length; i++) {
                if (!checks[i].checked) {
                    flag = false;
                }
            }
            allCheck.checked = flag;
        }
    }
}

// 换页并重新渲染table且初始化当前table属性
function upPage() {
    if (control === 0) return;
    control--;
    render(dates);
    allCheck.checked = false;
}

function downPage() {
    if (dates.length === 10) {
        return;
    } else if (control === parseInt(dates.length / 10)) return;
    control++
    render(dates);
    allCheck.checked = false;
}

// 初始化表单
function initial(table) {
    var ul = table.querySelector('ul');
    for (var i = 0; i < ul.children.length / 2; i++) {
        ul.children[2 * i].childNodes[1].value = "";
        ul.children[2 * i + 1].innerHTML = "";
    }
}

var check = true;
// 表单验证
{
    // 修改学生信息表单验证
    {
        document.getElementById("number1").onchange = function () {
            var number = document.getElementById("number1").value;
            var numberReg = /[0-9]{11}$/;
            if (!numberReg.test(number)) {
                document.getElementById("number2").innerHTML = "请输入十一位数字";
            } else {
                document.getElementById("number2").innerHTML = "";
            }
        }

        document.getElementById("name1").onchange = function () {
            var name = document.getElementById("name1").value;
            var nameReg = /^[\u4e00-\u9fa5]{1,10}$/;
            if (!nameReg.test(name)) {
                document.getElementById("name2").innerHTML = "请输入汉字";
            }
            if (nameReg.test(name)) {
                document.getElementById("name2").innerHTML = "";
            }
        }

        document.getElementById("gender1").onchange = function () {
            var gender = document.getElementById("gender1").value;
            var genderReg = /^[男]|[女]$/;
            if (!genderReg.test(gender)) {
                document.getElementById("gender2").innerHTML = "请输入“男”或“女”";
            }
            if (genderReg.test(gender)) {
                document.getElementById("gender2").innerHTML = "";
            }
        }

        document.getElementById("college1").onchange = function () {
            var college = document.getElementById("college1").value;
            var collegeReg = /^[\u4e00-\u9fa5]{1,10}$/;
            if (!collegeReg.test(college)) {
                document.getElementById("college2").innerHTML = "请输入正确的学院名";
            }
            if (collegeReg.test(college)) {
                document.getElementById("college2").innerHTML = "";
            }
        }

        document.getElementById("changeMajor1").onchange = function () {
            var changeMajor = document.getElementById("changeMajor1").value;
            var changeMajorReg = /^[\u4e00-\u9fa5]{1,10}$/;
            if (!changeMajorReg.test(changeMajor)) {
                document.getElementById("changeMajor2").innerHTML = "请输入正确的专业名";
            }
            if (changeMajorReg.test(changeMajor)) {
                document.getElementById("changeMajor2").innerHTML = "";
            }
        }

        document.getElementById("grade1").onchange = function () {
            var grade = document.getElementById("grade1").value;
            var gradeReg = /^[0-9]{4}$/;
            if (!gradeReg.test(grade)) {
                document.getElementById("grade2").innerHTML = "请输入正确的年级";
            }
            if (gradeReg.test(grade)) {
                document.getElementById("grade2").innerHTML = "";
            }
        }

        document.getElementById("class1").onchange = function () {
            var classC = document.getElementById("class1").value;
            var classReg = /^[1-4]{1}$/;
            if (!classReg.test(classC)) {
                document.getElementById("class2").innerHTML = "请输入正确的班级";
            }
            if (classReg.test(classC)) {
                document.getElementById("class2").innerHTML = "";
            }
        }

        document.getElementById("age1").onchange = function () {
            var age = document.getElementById("age1").value;
            var ageReg = /^[0-9]{1,2}$/;
            if (!ageReg.test(age)) {
                document.getElementById("age2").innerHTML = "请输入正确的年龄";
            }
            if (ageReg.test(age)) {
                document.getElementById("age2").innerHTML = "";
            }
        }

    }
}

// 表单验证确定
function ensureList(table) {
    var ul = table.querySelector('ul');
    for (var i = 0; i < ul.children.length / 2; i++) {
        if (ul.children[2 * i + 1].innerText !== "") {
            alert("表单信息不符合要求");
            return false;
        }
    }
    return true;
}

// 遮盖层
function showWindow() {
    $('#cover').css('display', 'block');
    $('#cover').css('height', '100%');
}

function closeWindow() {
    $('#cover').css('display', 'none');
}