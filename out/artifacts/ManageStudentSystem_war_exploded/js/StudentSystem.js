let page = 1;
let amountPerPage = 5;
let totalPage;
let totalAmount;
let searchText = "";

findAll();

function findAll() {
    axios({
        url: 'http://localhost:8080/findAll',
        method: 'GET',
        params: {
            page: page,
            amountPerPage: amountPerPage,
            text : searchText
        }
    })
        .then(
            response => {
                showFindAll(response.data.data);
                totalPage = response.data.totalPage;
                totalAmount = response.data.totalAmount;
                if (totalAmount===0)
                    totalPage = 1
                else
                    totalPage = Math.ceil(totalAmount/amountPerPage)
                if (page>totalPage)  page = totalPage


            },
            error => {
                alert(error.message)
            }
        )
}

function submitAdd(windowName,className) {

    let values
    if ( getTableValue()===false){
        return
    }


    values = getTableValue()


    axios({
        url: 'http://localhost:8080/add',
        method: 'GET',
        params: {
            values: values.toString()
        }
    }).then(
            response => {
                totalAmount = response.data.totalAmount;
                page = Math.ceil(totalAmount/amountPerPage);
                if(response.data.result){
                    alert("添加成功！")
                    closeWindow(windowName, className);
                    findAll();
                }
                else   alert("学号不能重复！");

            },
            error => {
                alert(error.message);
            }
        )
}



function findById(id) {
    axios({
        url: 'http://localhost:8080//findOne',
        method: 'GET',
        params: {
            id: id
        }
    })
        .then(
            response => {
                putValuesToInput(response.data.values);
            },
            error => {
                alert(error.message);
            }
        )
}

function putValuesToInput(values){
    let showArea = document.getElementsByClassName("showArea");
    for (let i = 0; i < showArea.length; i++) {
        showArea[i].value = values[i];
    }
}

function removeIt(ids) {
    axios({
        url: 'http://localhost:8080/delete',
        method: 'GET',
        params: {
            ids : ids.toString()
        }
    })
        .then(
            response => {
                totalAmount = response.data.totalAmount;
                if (totalAmount===0)
                totalPage = 1
                else
                    totalPage = Math.ceil(totalAmount/amountPerPage)
                if (page>totalPage)  page = totalPage
                console.log(page)
                console.log(totalPage)
                console.log(totalAmount)

               findAll()
            },
            error => {
                alert(error.message)
            }
        )
}

function showFindAll(students) {
    document.getElementById("table-body").innerHTML = "";
    document.getElementById("全选框").checked = false;
    let tabel = document.getElementById("table-body");
    for (let student in students) {
        let thisTr = tabel.appendChild(document.createElement("tr"));
        let thisInput = document.createElement("input")
        thisInput.className = "复选框";
        thisInput.type = "checkbox";
        thisTr.appendChild(document.createElement("td")).appendChild(thisInput);
        thisInput.onclick = function () {
            selectAllBack();
        }

        for (let message in students[student]) {
            let thisTd = thisTr.appendChild(document.createElement("td"));
            thisTd.innerHTML = students[student][message];
        }
        let thisOp1 = document.createElement("img")
        thisOp1.src = '../imgs/查询 .png';
        thisOp1.onclick = function () {
            let id = window.event.target.parentNode.parentNode.childNodes[1].firstChild.nodeValue;
            showDetails(id);
        }
        let thisOp2 = document.createElement("img")
        thisOp2.src = '../imgs/修改.png';
        thisOp2.onclick = function () {
            let id = window.event.target.parentNode.parentNode.childNodes[1].firstChild.nodeValue;
            showChangeWindow(id)
        }
        let Ops = document.createElement("td");
        Ops.appendChild(thisOp1);
        Ops.appendChild(thisOp2);
        thisTr.appendChild(Ops);
    }
}


function pageDown() {
    console.log("page"+page)
    console.log("totalpage"+totalPage)
    if (page === totalPage) {
        alert("已经是最后一页了")
        return;
    }

    page++;
    findAll();

}

function pageUp() {

    if (page === 1) {
        alert("已经是第一页了")
        return;
    }
    page--;
    findAll();
}

function getTableValue() {                                                              //获取新增页面中的学生信息
    let values = [];
    let i = 0;
    while (i < document.getElementsByClassName("showArea").length) {
        if (document.getElementsByClassName("showArea")[i].value===""){
            alert("请输入"+document.getElementsByClassName("property")[i].innerHTML)
            return  false
        }
        if (!checkDataLegal(i))
            return false


        values.push(document.getElementsByClassName("showArea")[i].value);
        i++;
    }
    return values;
}

function showChangeWindow(id){                                            //展示修改页面
    findById(id);
    shiftToChange()
}

function shiftToChange() {                                                          //向模态版加入修改页面需要的文字 按钮（包括事件） 模态版转化为修改页面
    let showArea = document.getElementsByClassName("showArea");
    document.getElementById("title").innerHTML="修改学生信息";
    basisInAdditionWindow()
    document.getElementById("leftButton").innerHTML = "修改";
    document.getElementById("leftButton").style.display = "inline";
    document.getElementById("rightButton").innerHTML="取消";
    document.getElementById("leftButton").onclick=function () {
        submitChange('additional-window','showArea');
    };
    document.getElementById("rightButton").onclick=function (){
        closeWindow('additional-window','showArea')
    };
    for (let i = 0; i < showArea.length; i++) {
        showArea[i].disabled=false;
    }
    showArea[0].disabled = true;
    document.getElementById("additional-window").style.display = "block";
}
function showDetails(id){                                                  //展示查询页面
    findById(id);
    shiftToDetail();
}


function shiftToDetail() {                                                           //向模态版加入查询页面需要的文字 按钮（包括事件） 模态版转化为查询页面
    let showArea = document.getElementsByClassName("showArea");
    document.getElementById("title").innerHTML="查看学生信息";
    basisInAdditionWindow();
    document.getElementById("leftButton").style.display = "none";
    document.getElementById("rightButton").innerHTML="取消";
    document.getElementById("rightButton").onclick=function(){
        closeWindow('additional-window','showArea');
    }
    for (let i = 0; i < showArea.length; i++) {
        showArea[i].disabled=true;
    }
    document.getElementById("additional-window").style.display = "block";
}

function submitChange(windowName,className){
    let values
    if ( getTableValue()===false){
        return
    }

    values = getTableValue()
    axios({
        url: 'http://localhost:8080/update',
        method: 'GET',
        params: {
            values: values.toString()
        }
    }).then(
        response => {
            if(response.data.result){
                alert("修改成功！")
                closeWindow(windowName, className);
                findAll();
            }
            else    alert("修改失败！")

        },
        error => {
            alert(error.message)
        }
    )
}

function deleteClickAction() {                                                      //屏蔽掉其他按钮的点击事件
    let buttons = document.getElementsByClassName("buttons");
    let imag = document.getElementsByClassName("操作");

    for (let i = 0; i < buttons.length; i++) {

        buttons[i].disabled = true;
    }
    for (let i = 0; i < imag.length; i++) {
        imag[i].onclick = null;
    }
}

function setClickAction() {                                                         //恢复其他按钮的点击事件
    let buttons = document.getElementsByClassName("buttons");
    let imag = document.getElementsByClassName("操作");
    for (let i = 0; i < buttons.length; i++) {
        buttons[i].disabled = false;
    }
    for (let i = 0; i < imag.length; i++) {
        if (i % 2 === 0) {

            imag[i].onclick = function () {
                showDetails(window.event, 'showArea')
            };
        } else {
            imag[i].onclick = function () {
                showChangeWindow(window.event, 'showArea')
            };
        }
    }

}

function basisInAdditionWindow() {                                               //向模态版加入三个窗口（添加、查询、修改）共有的元素
    deleteClickAction();
    let properties = ["学号", "姓名", "性别", "学院", "专业", "年级", "班级", "年龄"];
    let rightTbs = document.getElementsByClassName("property");
    for (let i = 0; i < rightTbs.length; i++) {
        rightTbs[i].innerHTML = properties[i];
    }
}

function add() {                                                                         //展示新增页面
    let showArea = document.getElementsByClassName("showArea");
    document.getElementById("title").innerHTML = "添加学生信息";
    basisInAdditionWindow()
    document.getElementById("leftButton").innerHTML = "提交";
    document.getElementById("leftButton").style.display = "inline";
    document.getElementById("rightButton").innerHTML = "取消";
    document.getElementById("leftButton").onclick = function () {
        submitAdd('additional-window', 'showArea')
    };
    document.getElementById("rightButton").onclick = function () {
        closeWindow('additional-window', 'showArea')
    };
    for (let i = 0; i < showArea.length; i++) {
        showArea[i].disabled = false;
    }
    document.getElementById("additional-window").style.display = "block";
}

function closeWindow(windowName, className) {                                             //关闭小窗口
    setClickAction()
    document.getElementById(windowName).style.display = "none";
    cleanTable(className);
}

function cleanTable(className) {                                                       //清空指定表格（一般为input框）中的内容
    for (let i = 0; i < document.getElementsByClassName(className).length; i++) {
        document.getElementsByClassName(className)[i].value = "";
    }
}

function selectAllOrNot(){
    let selections = document.getElementsByClassName("复选框");
    if (document.getElementById("全选框").checked){
        for (let i = 0; i < selections.length; i++) {
            selections[i].checked=true;
        }
    }
    else {
        for (let i = 0; i < selections.length; i++) {
            selections[i].checked=false;
        }
    }
}

function selectAllBack() {                                                      //反选 当页所有学生的复选框勾选时 勾选全框 ； 当有一个学生复选框取消勾选时，取消全选框勾选；
        let x = true
        let selections = document.getElementsByClassName("复选框");
        for (let i = 0; i <selections.length; i++) {
            if ( selections[i].checked=== false) {
                x = false;
            }
        }
            document.getElementById("全选框").checked = x;
}

function remove() {
    let ids = [];
    let selections = document.getElementsByClassName("复选框");
    for (let i = 0; i < selections.length; i++) {
        if (selections[i].checked){
          let id = selections[i].parentNode.parentNode.childNodes[1].firstChild.nodeValue;
            ids.push(id);
        }
    }
    removeIt(ids);
}

function search(){
    searchText = document.getElementById("input-area").value;
    page=1;
    findAll();
}

function checkDataLegal(index){
    let data =  document.getElementsByClassName("showArea")[index].value
    switch (index){
        case 0 : if (data.length!==11){
            alert("学号长度为11")
            return false
        }
        else if (isNaN(data)){
            alert("学号由数字组成")
            return false
        }
            break;
        case 1 :
            if (data.length>5){
                alert("姓名长度最大为5")
                return false
            }
            break;
        case 2 :
            if(data==="男"||data==="女"){}
            else {
                alert("性别只能为男或女")
                return false
            }
            break;
        case 3 :
            if (data.length>10){
                alert("学院长度最大为10")
                return false
            }
            break;
        case 4 :
            if (data.length>10){
                alert("专业长度最大为10")
                return false
            }
            break;
        case 5 :
            if (data<=2030&&data>=1000){

            }
            else{
                alert("年级为1000-2030的整数 如：2022")
                return false
            }
            break;
        case 6 :
            if (data<=9&&data>=1){

            }
            else{
                alert("班级为1-9的整数")
                return false
            }
            break;
        case 7 :
            if (data>=0&&data<=100){

            }
            else{
                alert("年龄为0-100的整数")
                return false
            }
            break;
    }
    return true
}

