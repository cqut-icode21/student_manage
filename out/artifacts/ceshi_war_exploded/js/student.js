let students = []
//计算页数
let currentPage = 1
//总人数
let allTr

//得到数据
function getData(current) {
    let data = "currentPage=" + currentPage;
    let xml = new XMLHttpRequest();
    xml.open("get", "/getData?" + data);
    xml.send()
    xml.onreadystatechange = function () {
        if (xml.readyState === 4) {
            let dataR = xml.responseText
            let json = JSON.parse(dataR)
            students = json.data
            allTr = json.allTr
            sum()
            if (current > 0) {
                pageRefresh((currentPage - 1) * 10, currentPage * 10)
            }
            //设置删除后刷新方法
            else {
                let begin = (currentPage - 1) * 10;
                let last = currentPage * 10;
                if (last > students.length) {
                    last = students.length;
                }
                //更新当前页数以及页面，防止当前页信息删完后页面仍然停留在空白页
                if (last === begin) {
                    if (currentPage > 1) {
                        currentPage = currentPage - 1;
                    }
                    begin = (currentPage - 1) * 10;
                }

                cTable.innerHTML = "";
                setSum1()
                check.checked = false
                if (students.length > 0) {
                    pageRefresh(begin, last);
                }

                if (students.length === 0) {
                    let pageSpan = document.getElementById("num");
                    pageSpan.innerHTML = "第" + currentPage + "/" + sum() + "页" + "(共" + 0 + "条)";
                }
            }
        }
    }
}

//渲染方法
let cTable = document.getElementById("demo")

function pageRefresh(begin, last) {
    if (last > students.length) {
        last = students.length;
    }
    for (let i = begin; i < last; i++) {
        let createTr = document.createElement("tr");
        for (let j = 0; j < 11; j++) {
            let createTd = document.createElement("td");
            createTd.style.height = "43px";
            createTd.style.fontFamily = "华文中宋, serif";

            if (j === 2 || j === 5) {
                createTd.style.width = "190px";
            } else if (j === 0) {
                createTd.style.width = "30px";
            } else {
                createTd.style.width = "140px";
            }
            let img1 = document.createElement("img");
            img1.src = "https://gd-hbimg.huaban.com/b276eac087a8ec9f5e1d00e8467febb17e078e0650fa-w6PtHD_fw658";
            img1.width = 20;
            img1.style.transform = "translate(-15px,2px)";
            img1.onclick = function () {
                setStudent(2, i);
            }

            let img2 = document.createElement("img");
            img2.src = "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimages.669pic.com%2Felement_pic%2F57%2F52%2F81%2F34%2Ff79fef5f7426e0cdde8a0a86e037b979.jpg%21w700wb&refer=http%3A%2F%2Fimages.669pic.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1659774410&t=00e11ba15ccffb69485f6d578a9e26c0";
            img2.width = 20;
            img2.style.transform = "translate(15px,2px)";
            img2.onclick = function () {
                setStudent(3, i);
            }

            switch (j) {
                case 0:
                    createTd.innerHTML = "<input type=\"checkbox\" name='signalCheck' onclick=differ()>";
                    break;
                case 1:
                    createTd.innerHTML = i + 1 + "";
                    break;
                case 2:
                    createTd.innerHTML = students[i].id;
                    break;
                case 3:
                    createTd.innerHTML = students[i].name;
                    break;
                case 4:
                    createTd.innerHTML = students[i].sex;
                    break;
                case 5:
                    createTd.innerHTML = students[i].college;
                    break;
                case 6:
                    createTd.innerHTML = students[i].professional;
                    break;
                case 7:
                    createTd.innerHTML = students[i].grade;
                    break
                case 8:
                    createTd.innerHTML = students[i].clazz;
                    break;
                case 9:
                    createTd.innerHTML = students[i].age;
                    break;
                case 10:
                    createTd.appendChild(img1).innerHTML;
                    createTd.appendChild(img2).innerHTML;
                    break;
            }

            createTd.style.textAlign = "center";
            createTr.appendChild(createTd);
        }
        if (i % 2 === 0) {
            createTr.style.backgroundColor = "#FCFCFC";
        } else {
            createTr.style.backgroundColor = "#D9FFFF";
        }
        cTable.appendChild(createTr);

        let pageSpan = document.getElementById("num");
        setSum1();
        pageSpan.innerHTML = "第" + currentPage + "/" + sum() + "页" + "(共" + sum1.length + "条)";
    }
}

//首次加载页面
window.onload = function () {
    getData(currentPage)
}

//计算总页数
function sum() {
    let i;
    if (allTr % 10 !== 0) {
        i = (allTr - allTr % 10) / 10 + 1;
    } else {
        i = allTr / 10;
    }

    if (i === 0) {
        i = 1;
    }
    return i;
}

let check = document.getElementById("checkId");
let p;

//翻页方法
function nextPage() {
    p = 0;
    if (currentPage === sum()) {
        alert("已经是最后一页");
        p = 1;
    }
    if (p === 0) {
        setSum1();
        saveInput();
        let page = document.getElementById("demo");
        page.innerHTML = "";
        if (currentPage < sum()) {
            currentPage = currentPage + 1;
        }
        pageRefresh((currentPage - 1) * 10, currentPage * 10);
        check.checked = false;
        setSum1();
        setInput1();
    }
}

function previousPage() {
    p = 0;
    if (currentPage === 1) {
        alert("已经是第一页");
        p = 1;
    }
    if (p === 0) {
        saveInput1();
        let page = document.getElementById("demo");
        page.innerHTML = "";
        if (currentPage > 1) {
            currentPage = currentPage - 1;
        }
        pageRefresh((currentPage - 1) * 10, currentPage * 10);
        check.checked = false;
        setSum1();
        setInput();
    }
}

//清除原有数据
function clear() {
    document.getElementById("number").value = "";
    document.getElementById("name").value = "";
    document.getElementById("gender").value = "";
    document.getElementById("college").value = "";
    document.getElementById("major").value = "";
    document.getElementById("grade").value = "";
    document.getElementById("class").value = "";
    document.getElementById("age").value = "";
}

//变为可更改
function setChange() {
    document.getElementById("number").readOnly = "";
    document.getElementById("name").readOnly = "";
    document.getElementById("gender").readOnly = "";
    document.getElementById("college").readOnly = "";
    document.getElementById("major").readOnly = "";
    document.getElementById("grade").readOnly = "";
    document.getElementById("class").readOnly = "";
    document.getElementById("age").readOnly = "";
}

//设置公用页面
let addTable = document.getElementById("addForm");

function setStudent(n, i) {
    //重置页面
    addTable.style.display = "block";
    //设置标题
    let title = document.getElementById("title");
    title.style.fontSize = "20px";

    if (n === 1) {
        //设置新增标题
        title.innerHTML = "新增学生信息";
        let setAddButton = document.getElementById("button");
        setAddButton.innerHTML = "";

        //设置新增中的按钮
        let buttonElement1 = document.createElement("button");
        buttonElement1.type = "button";
        buttonElement1.innerHTML = "提交";
        buttonElement1.style.textAlign = "center";
        buttonElement1.style.backgroundColor = "cadetblue";
        buttonElement1.style.width = "60px";
        buttonElement1.style.height = "35px";
        buttonElement1.style.transform = "translate(-50px,0)";
        buttonElement1.onclick = function () {
            setAddTable();
        }

        let buttonElement2 = document.createElement("button");
        buttonElement2.type = "button";
        buttonElement2.innerHTML = "取消";
        buttonElement2.style.textAlign = "center";
        buttonElement2.style.backgroundColor = "cadetblue";
        buttonElement2.style.width = "60px";
        buttonElement2.style.height = "35px";
        buttonElement2.style.transform = "translate(80px,0)";
        buttonElement2.onclick = function () {
            hide();
        }

        //清除查看和修改和新增后在框中的数据
        clear();

        setAddButton.appendChild(buttonElement1);
        setAddButton.appendChild(buttonElement2);

        setChange();
    }

    if (n === 2) {
        //标题
        title.innerHTML = "修改学生信息";

        let setButton = document.getElementById("button");
        setButton.innerHTML = "";

        //设置修改中的按钮
        let buttonElement3 = document.createElement("button");
        buttonElement3.type = "button";
        buttonElement3.innerHTML = "提交";
        buttonElement3.style.textAlign = "center";
        buttonElement3.style.backgroundColor = "cadetblue";
        buttonElement3.style.width = "60px";
        buttonElement3.style.height = "35px";
        buttonElement3.style.transform = "translate(-50px,0)";
        buttonElement3.onclick = function () {
            setModifyTable();
        }

        let buttonElement4 = document.createElement("button");
        buttonElement4.type = "button";
        buttonElement4.innerHTML = "取消";
        buttonElement4.style.textAlign = "center";
        buttonElement4.style.backgroundColor = "cadetblue";
        buttonElement4.style.width = "60px";
        buttonElement4.style.height = "35px";
        buttonElement4.style.transform = "translate(80px,0)";
        buttonElement4.onclick = function () {
            hide();
        }

        setButton.appendChild(buttonElement3);
        setButton.appendChild(buttonElement4);

        //获取原信息
        setLookTable(i);
        //更改只读
        setChange();
        document.getElementById("number").readOnly = "readOnly";
    }

    if (n === 3) {
        // 标题
        title.innerHTML = "查看学生信息";
        // 加入学生信息
        setLookTable(i);
        // 清空按钮
        let setButton = document.getElementById("button");
        setButton.innerHTML = "";
        //设置按钮
        let buttonElement = document.createElement("button");
        buttonElement.type = "button";
        buttonElement.onclick = function () {
            hide();
        }
        buttonElement.innerHTML = "确定"
        buttonElement.style.textAlign = "center";
        buttonElement.style.height = "35px";
        buttonElement.style.width = "60px";
        buttonElement.style.backgroundColor = "cadetblue";
        buttonElement.style.transform = "translate(10px,0)";

        setButton.appendChild(buttonElement);

        document.getElementById("number").readOnly = "readOnly";
        document.getElementById("name").readOnly = "readOnly";
        document.getElementById("gender").readOnly = "readOnly";
        document.getElementById("college").readOnly = "readOnly";
        document.getElementById("major").readOnly = "readOnly";
        document.getElementById("grade").readOnly = "readOnly";
        document.getElementById("class").readOnly = "readOnly";
        document.getElementById("age").readOnly = "readOnly";
    }
}

function setAddTable() {
    let number = document.getElementById("number").value;
    let name = document.getElementById("name").value;
    let gender = document.getElementById("gender").value;
    let college = document.getElementById("college").value;
    let major = document.getElementById("major").value;
    let grade = document.getElementById("grade").value;
    let clas = document.getElementById("class").value;
    let age = document.getElementById("age").value;

    let s = [number, name, gender, college, major, grade, clas, age]
    let j = 0

    for (let i = 0; i < s.length; i++) {
        if (s[i] === '') {
            alert("数据不能为空\n新增失败")
            j = 1
            break
        }
    }

    if (j === 0) {
        for (let i = 0; i < students.length; i++) {
            if (students[i].id === number) {
                alert("学号不可重复\n新增失败")
                j = 1
                break
            }
        }
    }

    if (j === 0) {
        let jsonObj = {
            id: number,
            name: name,
            sex: gender,
            college: college,
            professional: major,
            grade: grade,
            clazz: clas,
            age: age
        }

        $.ajax({
            type: "GET",
            url: "/addData",
            data: jsonObj,
            success: function () {
                let page = document.getElementById("demo");
                page.innerHTML = "";
                getData(currentPage);
            }
        })
    }
    addTable.style.display = "none";
}

function setLookTable(i) {
    let number = students[i].id;
    let name = students[i].name;
    let gender = students[i].sex;
    let college = students[i].college;
    let major = students[i].professional;
    let grade = students[i].grade;
    let clas = students[i].clazz;
    let age = students[i].age;

    document.getElementById("number").value = number + "";
    document.getElementById("name").value = name + "";
    document.getElementById("gender").value = gender + "";
    document.getElementById("college").value = college + "";
    document.getElementById("major").value = major + "";
    document.getElementById("grade").value = grade + "";
    document.getElementById("class").value = clas + "";
    document.getElementById("age").value = age + "";
}

function setModifyTable() {
    let number0 = document.getElementById("number").value;
    let name0 = document.getElementById("name").value;
    let gender0 = document.getElementById("gender").value;
    let college0 = document.getElementById("college").value;
    let major0 = document.getElementById("major").value;
    let grade0 = document.getElementById("grade").value;
    let clas0 = document.getElementById("class").value;
    let age0 = document.getElementById("age").value;

    let s0 = [number0, name0, gender0, college0, major0, grade0, clas0, age0]
    let j0 = 0

    for (let i = 0; i < s0.length; i++) {
        if (s0[i] === '') {
            alert("数据不能为空\n修改失败")
            j0 = 1
            break
        }
    }

    if (j0 === 0) {
        let jsonObj = {
            id: number0,
            name: name0,
            sex: gender0,
            college: college0,
            professional: major0,
            grade: grade0,
            clazz: clas0,
            age: age0
        }

        $.ajax({
            type: "GET",
            url: "/updateData",
            data: jsonObj,
            success: function () {
                let page = document.getElementById("demo");
                page.innerHTML = "";
                getData(currentPage);
            }
        })
    }

    addTable.style.display = "none";
}

function setDeleteTable() {
    check.checked = false;
    //重置数组，防止点击复选框后元素堆积
    checkArr = [];
    checkBox();
    if (checkArr.length === 0) {
        alert("未选中任何元素");
    }

    for (let i = 0; i < checkArr.length; i++) {
        let deleteId = students[checkArr[i]].id
        let jsonObj = {
            id: deleteId
        }

        if (i === checkArr.length - 1) {
            $.ajax({
                type: "GET",
                url: "/deleteData",
                data: jsonObj,
                success: function () {
                    let page = document.getElementById("demo");
                    page.innerHTML = "";
                    getData(-1);
                }
            })
            continue
        }
        $.ajax({
            type: "GET",
            url: "/deleteData",
            data: jsonObj,
        })
    }

    //重置数组
    checkArr = [];
}

function hide() {
    addTable.style.display = "none";
}

//获取复选框
let checkArr = [];

//根据复选框数量判断每页条数
let sum1;
//更新获取复选框元素
function setSum1() {
    sum1 = [];
    sum1 = document.getElementsByName("signalCheck");
}

//判断哪些复选框被选中
function checkBox() {
    setSum1();
    for (let i = 0; i < sum1.length; i++) {
        if (sum1[i].checked) {
            checkArr.push(i+(currentPage-1)*10);
        }
    }
}

//设置全选框
function allCheck() {
    setSum1();
    if (check.checked) {
        for (let i = 0; i < sum1.length; i++) {
            sum1[i].checked = true;
        }
    } else {
        for (let i = 0; i < sum1.length; i++) {
            sum1[i].checked = false;
        }
    }
}

//设置反选
function differ() {
    let begin = (currentPage - 1) * 10;
    let last = currentPage * 10;
    if (last > students.length) {
        last = students.length;
    }
    //防止已选框数量堆积，每次清零
    checkArr = [];
    //获取被选中数量
    checkBox();
    check.checked = (last - begin) === checkArr.length && last !== 0;
}


let tF = [];
let tF1 = [];
let allTf;
let allTf1;
//设置翻页保存复选框信息
function saveInput() {
    tF = [];
    for (let i = 0; i < sum1.length; i++) {
        tF.push(sum1[i].checked);
    }
    allTf = check.checked;
}
function setInput() {
    for (let j = 0; j < sum1.length; j++) {
        sum1[j].checked = tF[j];
    }
    check.checked = allTf;
}
function saveInput1() {
    tF1 = [];
    for (let i = 0; i < sum1.length; i++) {
        tF1.push(sum1[i].checked);
    }
    allTf1 = check.checked;
}
function setInput1() {
    for (let j = 0; j < sum1.length; j++) {
        sum1[j].checked = tF1[j];
    }
    check.checked = allTf1;
}


//是否确定删除界面
function setSure() {
    let sure = document.getElementById("sureDiv");
    sure.innerHTML = "";

    sure.style.transform = "translate(-230px,0px)";

    let sureDiv = document.createElement("div");
    sureDiv.innerHTML = "是否确定删除";
    sureDiv.style.textAlign = "center";
    sureDiv.style.width = "500px"
    sureDiv.style.fontSize = "28px";
    sureDiv.style.transform = "translate(10px,0)"
    sure.appendChild(sureDiv);

    let button1 = document.createElement("button");
    button1.type = "button";
    button1.innerHTML = "确定";
    button1.style.textAlign = "center";
    button1.style.backgroundColor = "rgba(124,208,208,0.96)";
    button1.style.width = "60px";
    button1.style.height = "35px";
    button1.style.transform = "translate(30px,100px)";
    button1.onclick = function () {
        setDeleteTable();
        sure.style.display = "none";
    }

    let button2 = document.createElement("button");
    button2.type = "button";
    button2.innerHTML = "取消";
    button2.style.textAlign = "center";
    button2.style.backgroundColor = "rgba(124,208,208,0.96)";
    button2.style.width = "60px";
    button2.style.height = "35px";
    button2.style.transform = "translate(350px,100px)";
    button2.onclick = function () {
        sure.style.display = "none";
    }

    let sureDiv1 = document.createElement("div");
    sureDiv1.appendChild(button1);
    sureDiv1.appendChild(button2);
    sure.appendChild(sureDiv1);
    sure.style.display = "block";
}


//搜索
function search() {
    check.checked = false;
    let str = document.getElementById("search").value;
    let xml1 = new XMLHttpRequest();
    let t = "str="+str;
    xml1.open("get", "/search?" + t);
    xml1.send()
    xml1.onreadystatechange = function () {
        if (xml1.readyState === 4) {
            let dataR = xml1.responseText
            let json = JSON.parse(dataR)
            students = json.data
            allTr = students.length
            sum()
            let page0 = document.getElementById("demo");
            page0.innerHTML = "";
            pageRefresh(0,10)
        }
    }
}

//搜索后返回初始页
function return0() {
    let page = document.getElementById("demo");
    page.innerHTML = "";
    currentPage = 1;
    getData(currentPage)
}