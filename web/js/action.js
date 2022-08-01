let array = [];

// 得到数据
function getData(currentPage) {
    let data = "currentPage=" + currentPage;

    let xml = new XMLHttpRequest();
    xml.open("get", "/getData?" + data, true);
    xml.send();
    xml.onreadystatechange = function () {
        if (xml.readyState === 4) {
            let dataR = xml.responseText;
            let json = JSON.parse(dataR);
            array = json.data;
            allTr = json.allTr;
            refresh();
            calculate(currentPage);
            tableBody(currentPage);
            check();
            change();
            bind1();
        }
    }
}

//刷新
function refresh() {
    $("#table").html("");
}


/*表头*/
let headDate = ["序号", "学号", "姓名", "性别", "学院", "专业", "年级", "班级", "年龄", "操作"];

function tableHead() {
    let input = $("<input/>").prop("id", "checkall").prop("type", "checkbox");
    let table = $("#table");
    let tr = $("<tr/>").addClass("center color2");
    table.append(tr);
    for (let m = 0; m < headDate.length + 1; m++) {
        let th = $("<th/>");
        if (m === 0) {
            th.append(input);
        } else
            th.html(headDate[m - 1]);
        tr.append(th);
    }
}

//制作表格并填入数据
let head = ["id", "name", "sex", "college", "professional", "grade", "clazz", "age"];
//一共有多少学生
let allTr;
//总页数
let allPage = Math.floor(allTr / 10) + 1;
//当前页数
let currentPage = 1;
//初末索引
let starIndex = 0;
let endIndex = 10;
//当前页信息条数
let currentPageTr = endIndex - starIndex;
//最大索引
let maxIndex = allTr - 1;
//每页有多少学生
// let pageMaxTr = 10;

//计算索引
function calculate(currentPage1) {
    allPage = Math.floor(allTr / 10) + 1;
    if (currentPage1 === allPage) {
        endIndex = allTr % 10;
    } else {
        endIndex = 10;
    }
    currentPageTr = endIndex - starIndex;
    maxIndex = allTr - 1;
}

//创建表格
function tableBody(currentPage1) {
    console.log(array);
    calculate(currentPage1);
    tableHead();
    for (let i = starIndex; i < endIndex; i++) {
        let table1 = $("#table");
        let tr = $("<tr/>");
        table1.append(tr);
        if (i % 2 === 0)
            tr.addClass("color1");
        else
            tr.addClass("color2");
        for (let j = 0; j < headDate.length + 1; j++) {
            let td = $("<td/>").addClass("center");
            if (j === 0) {
                let arrayCheck = $("<input type='checkbox'/>");
                td.append(arrayCheck);
                arrayCheck.addClass("check").prop("class", 2 * i).prop("checked", false);
            } else if (j === 1) {
                td.html((i + 1) + (currentPage1 - 1) * 10);
            } else if (j === 10) {
                let buttonAll1 = $("<button/>").addClass("iconfont icon-chaxun").prop("id", 2 * i);
                let buttonAll2 = $("<button/>").addClass("iconfont icon-bianji").prop("id", 2 * i + 1);
                td.append(buttonAll1, buttonAll2);
            } else
                td.html(array[i][head[j - 2]]);
            tr.append(td);
        }
    }
    let pageDate = $("#pageDate").text("第" + currentPage + "页，全部信息共" + allTr+"条");
}

//绑定
function bind1() {
    calculate(currentPage);
    let count = 0;
    let check01 = $("#checkall");
    check01.click(function () {
        for (let i = starIndex; i < endIndex; i++) {
            let arrayCheck = $("." + 2 * i);
            let boolean = check01.prop("checked");
            arrayCheck.prop("checked", boolean);
        }
        count = currentPageTr;
    })
    for (let i = starIndex; i < endIndex; i++) {
        let arrayCheck = $("." + 2 * i);
        arrayCheck.click(function () {
            if (arrayCheck.prop("checked") === true)
                count++;
            else if (arrayCheck.prop("checked") === false)
                count--;

            if (count === currentPageTr) {
                check01.prop("checked", true);
            }

            if(count !== currentPageTr){
                check01.prop("checked", false);
            }
            console.log(count);

        })
    }
}

//删除
function dele01() {
    calculate(currentPage);
    $("#delete").click(function () {
        let temp = [];
        for (let i = starIndex; i < endIndex; i++) {
            let arrayCheck = $("." + 2 * i);
            if (arrayCheck.prop("checked") === true) {
                temp.push(i);
            }
        }
        let count = [];
        for (let j = 0; j < temp.length; j++) {
            let k = temp[j];
            count.push(array[k]["id"]);
        }
        console.log("count",count)
        $.ajax({
            type: "GET",
            url: "/deleteReq",
            data: {"count": count},
            success: function (text) {
                console.log("传入删除数据成功了！")
                refresh();
                getData(currentPage);
            },
            error: function (){
                console.log("传入删除数据失败了！")
            }
        })
    })
}

//调用函数创建第一页表格并填入数据
getData(currentPage);
dele01();

//查询
function check() {
    calculate(currentPage);
    for (let i = starIndex; i < endIndex; i++) {
        let btnDouble = $("#" + 2 * i);
        btnDouble.click(function () {
            let studentDate = ["", "", "", "", "", "", "", ""];
            for (let j = 0; j < head.length; j++) {
                studentDate[j] = array[i][head[j]];
            }
            form(studentDate, i, "学生信息");
        })
    }
}

//修改信息
function change() {
    calculate(currentPage);
    for (let i = starIndex; i < endIndex; i++) {
        let single = 2 * i + 1;
        let btnSingle = $("#" + single);
        btnSingle.click(function () {
            let studentDate = ["", "", "", "", "", "", "", ""];
            for (let j = 0; j < head.length; j++) {
                studentDate[j] = array[i][head[j]];
            }
            form(studentDate, i, "修改学生信息");
        })
    }
}

/*翻页*/
//上一页
$("#pre").click(function () {
    currentPage--;
    if (currentPage === 0)
        alert("已经第一页");
    else {
        getData(currentPage);
        calculate(currentPage);
    }
})

//下一页
$("#next").click(function () {
    currentPage++;
    if (currentPage === allPage + 1)
        alert("已经是最后一页");
    else if (allTr < 11)
        alert("已经是最后一页");
    else {
        getData(currentPage);
        calculate(currentPage);

    }
})

//搜索
function search() {
    let data = $(".input").val();
    setTimeout(search, 1000);
    getText(data);
}

function getText(data) {
    $.ajax({
        type: "GET",
        url: "/search",
        data: {"data": data},
        dataType: 'json',
        success: function (text) {
            array = text.students;
            allTr = text.length;
            refresh();
            calculate(currentPage);
            tableBody(currentPage);
            check();
            change();
            bind1();
        }
    })
}

function add() {
    calculate(currentPage);
    $("#add").click(function () {
        let empty = ["", "", "", "", "", "", "", ""];
        maxIndex++;
        form(empty, maxIndex, "新增学生信息");
    })
}

add();


//创建表单（并判断增加还是修改）
function form(arrayconten, index, string) {
    let back = $("#backGround")[0];
    //拦截预处理
    back.style.filter = "blur(2px)";//模糊处理
    back.style.pointerEvents = "none";//取消背景点击
    let body = document.body;
    let div = $("<div></div>").addClass("float divradius").appendTo(body);
    //头部
    $("<div><div/>").addClass("box boxradius").appendTo(div).text(string);

    //body
    let ul = $("<ul></ul>")
    let li = [0, 0, 0, 0, 0, 0, 0, 0];
    let inputx = [0, 0, 0, 0, 0, 0, 0, 0];
    for (let i = 0; i < 8; i++) {
        let temp = "";
        switch (i) {
            case 0:
                temp = "学号 : ";
                break;
            case 1:
                temp = "姓名 : ";
                break;
            case 2:
                temp = "性别 : ";
                break;
            case 3:
                temp = "学院 : ";
                break;
            case 4:
                temp = "专业 : ";
                break;
            case 5:
                temp = "年级 : ";
                break;
            case 6:
                temp = "班级 : ";
                break;
            case 7:
                temp = "年龄 : ";
                break;
        }
        li[i] = document.createElement("li");
        inputx[i] = document.createElement("input");
        inputx[i].type = "text";
        inputx[i].value = arrayconten[i];
        li[i].innerHTML = temp;
        li[i].appendChild(inputx[i]);
        ul.append(li[i]);
        if (string === "学生信息") {
            inputx[i].disabled = "disable";
        }
    }
    let box02 = $("<div><div/>").addClass("boxf2").appendTo(div).append(ul);
    //foot:确定，取消按钮
    let boxLast = $("<div><div/>").addClass("boxLast").appendTo(div);
    let btn01 = $("<button/>").addClass("style1").appendTo(boxLast).text("确定");
    let btn02 = $("<button/>").addClass("style2").appendTo(boxLast).text("取消");
    btn01.click(function () {
        back.style.filter = "blur(0)";//清晰
        back.style.pointerEvents = "auto";//恢复
        div.remove();
        let jsonObj = {
            "id": "",
            "name": "",
            "sex": "",
            "college": "",
            "professional": "",
            "grade": "",
            "clazz": "",
            "age": ""
        }
        //修改
        //根据行数变更来判断修改和添加（+1）
        if (index >= 0 && index < allTr) {
            for (let j = 0; j < head.length; j++) {
                // if (inputx[j]===""||isNaN(inputx[0].value)||isNaN(inputx[5].value)||isNaN(inputx[6].value)||isNaN(inputx[7].value)||
                // inputx[2].value!=="男"||inputx[2].value!=="女"){
                //     alert("请输入正确内容！")
                //     return;
                // }
                jsonObj[head[j]] = inputx[j].value;
            }
            //修改
            $.ajax({
                type: "GET",
                url: "/updateReq",
                data: jsonObj,
                success: function (text) {
                    refresh();
                    getData(currentPage);
                }
            })
            //增加
        } else {
            // for (let i=0;i<head.length;i++){
            //     if (inputx[i]==null) alert("请输入值")
            //     break;
            //     for (let i=0;i<allTr;i++){
            //
            //     }
            // }
            for (let j = 0; j < head.length; j++) {
                // if (inputx[j]===""||isNaN(inputx[0].value)||isNaN(inputx[5].value)||isNaN(inputx[6].value)||isNaN(inputx[7].value)||
                //     inputx[2].value!=="男"||inputx[2].value!=="女"){
                //     alert("请输入正确内容！")
                //     return;
                // }
                jsonObj[head[j]] = inputx[j].value;
            }
            $.ajax({
                type: "GET",
                url: "/addReq",
                data: jsonObj,
                success: function (res) {
                    refresh();
                    getData(currentPage);
                }
            })
        }
    });
    btn02.click(function () {
        back.style.filter = "blur(0)";
        back.style.pointerEvents = "auto";
        div.remove();
    });
}