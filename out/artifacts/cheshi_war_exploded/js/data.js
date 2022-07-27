let students = [];
let currentPage = 1;

// 得到数据
function getData(page) {
    let data = "currentPage=" + currentPage;
    let xml = new XMLHttpRequest();
    xml.open("get", "/getData?" + data);
    xml.send()
    xml.onreadystatechange = function () {
        if (xml.readyState === 4) {
            let dataR = xml.responseText
            let json = JSON.parse(dataR)
            students = json.data
            renderData(page - 1, page)
        }
    }
}

getData(currentPage);


function render() {
    let tb = document.getElementById('myTable');
    let rowNum = tb.rows.length;
    for (let i = 1; i < rowNum; i++) {
        tb.deleteRow(i);
        rowNum = rowNum - 1;
        i = i - 1;
    }
}

let array = [];
let array1 = [];

//获取当前页的勾选信息
function f1() {
    array.splice(0);
    let items = document.getElementsByName('item');
    for (let i = 0; i < items.length; i++) {
        if (items[i].checked === true) {
            array.push(true);
        } else {
            array.push(false)
        }
    }
}

//获取下一页的勾选信息
function f2() {
    array1.splice(0);
    let items = document.getElementsByName('item');
    for (let i = 0; i < items.length; i++) {
        if (items[i].checked === true) {
            array1.push(true);
        } else {
            array1.push(false)
        }
    }
}

//设置当前页的勾选信息
function setf1() {
    let items = document.getElementsByName("item");
    for (let i = 0; i < items.length; i++) {
        items[i].checked = array[i];
    }
}

//设置下一页的勾选信息
function setf2() {
    let items = document.getElementsByName("item");
    for (let i = 0; i < items.length; i++) {
        items[i].checked = array1[i];
    }
}


function checkAll_2() {
    let items = document.getElementsByName('item');
    let index = -1;
    for (let i = 0; i < items.length; i++) {
        if (items[i].checked !== true) {
            index = i;
        }
    }
    document.getElementById('ck').checked = index === -1;
}

function checkAll(obj) {
    let status = obj.checked;
    let items = document.getElementsByName('item');
    for (let i = 0; i < items.length; i++) {
        items[i].checked = status;
    }
}

let p;

function previous() {
    p = 0;
    if (currentPage === 1) {
        p = 1;
        window.alert('当前为第一页，无法向前翻页')
    }
    if(p === 0) {
        f2();
        document.getElementById('pageNum').innerText = currentPage - 1 + "";
        currentPage--;
        renderData(currentPage - 1, currentPage);
        setf1();
        f1();
        checkAll_2();
    }
}

function next() {
    p = 0;
    let pageSum = Math.ceil(students.length / 10);
    if (currentPage >= pageSum) {
        p = 1;
        window.alert('当前为最后一页，无法向后翻页')
    }
    if(p===0) {
        f1();
        document.getElementById('pageNum').innerText = currentPage + 1 + "";
        currentPage++;
        renderData(currentPage - 1, currentPage);
        setf2();
        f2();
        checkAll_2();
    }
}

function renderData(first, last) {
    render();
    let k = last * 10;
    if (k >= students.length) {
        k = students.length;
    }
    let iTable = document.getElementById('myTable');
    for (let i = first * 10; i < k; i++) {
        let iTr = document.createElement('tr');
        let sel = document.createElement('input');
        sel.id = 'zx';
        sel.setAttribute('type', 'checkbox');
        sel.setAttribute('name', 'item');
        sel.setAttribute('onclick', 'checkAll_2()')

        let iTd1 = document.createElement('td');
        iTd1.className = "col1";
        iTd1.appendChild(sel);
        let iTd2 = document.createElement('td');
        iTd2.className = "col2";
        iTd2.appendChild(document.createTextNode(iTable.rows.length + (currentPage - 1) * 10 + ""));
        let iTd3 = document.createElement('td');
        iTd3.className = "col3";
        iTd3.appendChild(document.createTextNode(students[i].id));
        let iTd4 = document.createElement('td');
        iTd4.className = "col4";
        iTd4.appendChild(document.createTextNode(students[i].name));
        let iTd5 = document.createElement('td');
        iTd5.className = "col5";
        iTd5.appendChild(document.createTextNode(students[i].sex));
        let iTd6 = document.createElement('td');
        iTd6.className = "col6";
        iTd6.appendChild(document.createTextNode(students[i].college));
        let iTd7 = document.createElement('td');
        iTd7.className = "col7";
        iTd7.appendChild(document.createTextNode(students[i].professional));
        let iTd8 = document.createElement('td');
        iTd8.className = "col8";
        iTd8.appendChild(document.createTextNode(students[i].grade));
        let iTd9 = document.createElement('td');
        iTd9.className = "col9";
        iTd9.appendChild(document.createTextNode(students[i].clazz));
        let iTd10 = document.createElement('td');
        iTd10.className = "col10";
        iTd10.appendChild(document.createTextNode(students[i].age));
        let iTd11 = document.createElement('td');
        iTd11.className = "col11";
        let examine = document.createElement('input');
        examine.id = 'examine';
        examine.setAttribute('type', 'button');
        examine.setAttribute('value', '查看');
        examine.setAttribute('onclick', 'examine(this)');
        let update = document.createElement('input');
        update.id = 'update';
        update.setAttribute('type', 'button');
        update.setAttribute('value', '修改');
        update.setAttribute('onclick', 'update(this)');
        iTd11.appendChild(examine);
        iTd11.appendChild(update);

        // 将单元格添加到行
        iTr.appendChild(iTd1);
        iTr.appendChild(iTd2);
        iTr.appendChild(iTd3);
        iTr.appendChild(iTd4);
        iTr.appendChild(iTd5);
        iTr.appendChild(iTd6);
        iTr.appendChild(iTd7);
        iTr.appendChild(iTd8);
        iTr.appendChild(iTd9);
        iTr.appendChild(iTd10);
        iTr.appendChild(iTd11);


        // 隔行换色
        if (i % 2 !== 0) {
            iTr.className = 'mainTbodyTr1';
        } else {
            iTr.className = 'mainTbodyTr2';
        }
        // 将tr添加到table中
        iTable.appendChild(iTr);
        document.getElementById('pageNum').innerText = currentPage + "";
        document.getElementById('nums').innerText = students.length + "";
    }
}


function add() {
    // 打开新增框架
    document.getElementById('addBlock').style.display = 'block';
    document.getElementById('totalBackground').style.display = 'block';
}

// 提交按钮
function sumbit() {
    // 关闭新增框架
    document.getElementById('addBlock').style.display = 'none';
    document.getElementById('totalBackground').style.display = 'none';

    // 写入表单
    // 获取输入值
    let id = document.getElementById('stuId1').value;
    let name = document.getElementById('name1').value;
    let sex = document.getElementById('gender1').value;
    let college = document.getElementById('colg1').value;
    let professional = document.getElementById('profession1').value;
    let grade = document.getElementById('grade1').value;
    let clazz = document.getElementById('stuClass1').value;
    let age = document.getElementById('age1').value;

    let jsonObj = {
        "id": id,
        "name": name,
        "sex": sex,
        "college": college,
        "professional": professional,
        "grade": grade,
        "clazz": clazz,
        "age": age
    }
    $.ajax({
        type: "GET",
        url: "/add",
        data: jsonObj,
        success: function () {
            getData(currentPage);
        }
    })

    // 将新增框架中的输入框值初始化
    document.getElementById('stuId1').value = null;
    document.getElementById('name1').value = null;
    document.getElementById('gender1').value = null;
    document.getElementById('colg1').value = null;
    document.getElementById('profession1').value = null;
    document.getElementById('grade1').value = null;
    document.getElementById('stuClass1').value = null;
    document.getElementById('age1').value = null;

}

// 新增中的取消按钮
function addCancel() {
    // 关闭新增框架
    document.getElementById('addBlock').style.display = 'none';
    document.getElementById('totalBackground').style.display = 'none';
}

function update(obj) {
    // 打开修改框架
    document.getElementById('updateBlock').style.display = 'block';
    document.getElementById('totalBackground').style.display = 'block';

    // 获取当前行
    let iTr = obj.parentNode.parentNode;

    // 获取当前行中的所有单元格
    iTds = iTr.getElementsByTagName('td');

    // 将新增框架中的输入框中内容设为当前行对应的内容
    document.getElementById('stuId2').value = iTds[2].innerText;
    document.getElementById('name2').value = iTds[3].innerText;
    document.getElementById('gender2').value = iTds[4].innerText;
    document.getElementById('colg2').value = iTds[5].innerText;
    document.getElementById('profession2').value = iTds[6].innerText;
    document.getElementById('grade2').value = iTds[7].innerText;
    document.getElementById('stuClass2').value = iTds[8].innerText;
    document.getElementById('age2').value = iTds[9].innerText;
}

// 保存按钮
function preservation() {
    // 将新内容写入
    let id = document.getElementById('stuId2').value;
    let name = document.getElementById('name2').value;
    let sex = document.getElementById('gender2').value;
    let college = document.getElementById('colg2').value;
    let professional = document.getElementById('profession2').value;
    let grade = document.getElementById('grade2').value;
    let clazz = document.getElementById('stuClass2').value;
    let age = document.getElementById('age2').value;

    let jsonObj = {
        "id": id,
        "name": name,
        "sex": sex,
        "college": college,
        "professional": professional,
        "grade": grade,
        "clazz": clazz,
        "age": age
    }
    $.ajax({
        type: "GET",
        url: "/update",
        data: jsonObj,
        success: function () {
            getData(currentPage);
        }
    })


    // 关闭修改框架
    document.getElementById('updateBlock').style.display = 'none';
    document.getElementById('totalBackground').style.display = 'none';
}

// 修改中的取消按钮
function updateCancel() {
    // 关闭修改框架
    document.getElementById('updateBlock').style.display = 'none';
    document.getElementById('totalBackground').style.display = 'none';
}

// 查看按钮
function examine(obj) {
    // 打开查看框架
    document.getElementById('examineBlock').style.display = 'block';
    document.getElementById('totalBackground').style.display = 'block';

    // 获取当前行
    let iTr = obj.parentNode.parentNode;

    // 获取当前行中的所有单元格
    let iTds = iTr.getElementsByTagName('td');

    // 将新增框架中的输入框中内容设为当前行对应的内容
    document.getElementById('stuId3').value = iTds[2].innerText;
    document.getElementById('name3').value = iTds[3].innerText;
    document.getElementById('gender3').value = iTds[4].innerText;
    document.getElementById('colg3').value = iTds[5].innerText;
    document.getElementById('profession3').value = iTds[6].innerText;
    document.getElementById('grade3').value = iTds[7].innerText;
    document.getElementById('stuClass3').value = iTds[8].innerText;
    document.getElementById('age3').value = iTds[9].innerText;
}

// 查看中的取消按钮
function examineCancel() {
    // 关闭修改框架
    document.getElementById('examineBlock').style.display = 'none';
    document.getElementById('totalBackground').style.display = 'none';
}


// 删除按钮
function del() {
    // 打开删除框架
    document.getElementById('delBlock').style.display = 'block';
    document.getElementById('totalBackground').style.display = 'block';

    let items = document.getElementsByName('item');
    let message = [];
    for (let j = 0; j < items.length; j++) {
        if (items[j].checked) //如果item被选中
        {
            let m = items[j].parentNode.parentNode.cells[3].innerText;
            message.push(m);
        }
    }

    let delNode = document.getElementById('delMessage');
    delNode.innerText = message.join('\t');
}

// 确认按钮
function confirm() {
    // 关闭删除框架
    document.getElementById('delBlock').style.display = 'none';
    document.getElementById('totalBackground').style.display = 'none';

    let items = document.getElementsByName('item');
    let numbers = (currentPage - 1) * 10;
    let count = [];
    let j = 0;
    for (let i = 0; i < items.length; i++) {
        if (items[i].checked) {
            count[j] = students[numbers + i].id;
            j++;
        }
    }
    console.log(count)
    let jsonObj = {
        "count[]": count
    }
    $.ajax({
        type: "GET",
        url: "/deleteData",
        data: jsonObj,
        success: function () {
            getData(currentPage);
        }
    })
}

// 删除中的取消按钮
function delCancel() {
    // 关闭删除框架
    document.getElementById('delBlock').style.display = 'none';
    document.getElementById('totalBackground').style.display = 'none';
}

function seek(){
    let str = document.getElementById("searched").value;
    let data = "data=" + str;
    let xml = new XMLHttpRequest();
    xml.open("get", "/search?" + data);
    xml.send()
    xml.onreadystatechange = function () {
        if (xml.readyState === 4) {
            let dataR = xml.responseText
            let json = JSON.parse(dataR)
            students = json.data
            renderData(currentPage - 1, currentPage)
        }
    }
}

function goBack() {
    getData(currentPage);
}
