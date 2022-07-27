let array = [];
//表头数据
let head = ["id", "name", "sex", "college", "professional", "grade", "clazz", "age"];
//表头名
let headDate = ["序号", "学号", "姓名", "性别", "学院", "专业", "年级", "班级", "年龄", "操作"];
//总行数
let allTr;
//总页数
let allPage = Math.floor(allTr / 10) + 1;
//当前页数
let currentPage = 1;
//始末索引
let starIndex = 0;
let endIndex = 10;
//当前页数据条数
let currentPageTr = endIndex - starIndex;
//最大索引
let maxIndex = allTr - 1;
//每页最大行数
let pageMaxTr = 10;

/**
 * 首先获取页面数据
 * */
function getData(currentPage) {
    $.ajax({
        type: "GET",
        url: "/getData",
        data: {"currentPage": currentPage},//‘键’为发送请求输入的参数名，’值‘为其数值
        dataType: "json",//把json字符串自动解析正确的数据，就不用再写JSON.parse
        success: function (dataResp) {//ajax请求dataType值为json，jquery就会把后端返回的字符串尝试通过JSON.parse()尝试解析为js对象。
            array = dataResp.data;//js对象（一般为数组），{"allTr":allTr,"data":data}中的data（tbody）赋给array
            allTr = dataResp.allTr;//将总行数赋值给allTr
            clear();
            calculate(currentPage);//得到当前页行数和始末索引
            tBody(currentPage);//创建thead和tbody
            query();//操作框中的查询方法
            update();//操作框中的修改方法
            check();//全选和复选方法
        },
    })
}

//调用函数创建第一页表格并填入数据
getData(currentPage);

/**
 * 清空table方法
 **/
function clear() {
    $("#table").html("");//即document.getElementById("table").innerHTML="";
}

/**
 * 计算索引得到当前页的行数和始末索引
 **/
function calculate(currentPage1) {//currentPage1为网页当前页
    allPage = Math.floor(allTr / 10) + 1;
    //找到末索引
    if (currentPage1 === allPage) {
        endIndex = allTr % 10;
    } else {
        endIndex = 10;
    }
    currentPageTr = endIndex - starIndex;
    maxIndex = allTr - 1;
}

/**
 * 制作表头方法
 **/
function tHead() {
    let input = $("<input/>").prop("id", "checkAll").prop("type", "checkbox");
    let table = $("#table");
    let tr = $("<tr/>").addClass("center color2");
    table.append(tr);
    for (let m = 0; m < headDate.length + 1; m++) {//+1为复选款栏
        let th = $("<th/>");
        th.addClass("center")
        if (m === 0) {
            th.append(input);
        } else
            th.html(headDate[m - 1]);
        tr.append(th);
    }
}

/**
 * 创建表格
 **/
function tBody(currentPage1) {
    calculate(currentPage1);
    tHead();//表头表格一起创建
    //执行多行
    for (let i = starIndex; i < endIndex; i++) {
        let table1 = $("#table");
        let tr = $("<tr/>");
        table1.append(tr);
        if (i % 2 === 0)
            tr.addClass("color1");
        else
            tr.addClass("color2");
        //每行td数据加入
        for (let j = 0; j < headDate.length + 1; j++) {
            //每加一，每创建一个td
            let td = $("<td/>").addClass("center");
            if (j === 0) {
                let arrayCheck = $("<input type='checkbox'/>");
                td.append(arrayCheck);
                arrayCheck.addClass("query").prop("class", 2 * i).prop("checked", false);//特殊td单独处理
            } else if (j === 1) {
                //根据当前页设置序号
                td.html((i + 1) + (currentPage1 - 1) * 10);
            } else if (j === 10) {//设置操作栏两个button
                let buttonAll1 = $("<button/>").addClass("iconfont icon-chazhaoyonghu").prop("id", 2 * i);
                let buttonAll2 = $("<button/>").addClass("iconfont icon-bianji").prop("id", 2 * i + 1);
                td.append(buttonAll1, buttonAll2);
            } else//其余学生数据
                td.html(array[i][head[j - 2]]);
            tr.append(td);
        }
    }
    let pageDate = $("#pageDate").text("第" + currentPage + "页，总共有" + allTr + "条数据");

}
/**
 * 创建表单方法
 **/
function createForm(studentDataArray, index, string) {
    let back = $("#box")[0];
    back.style.pointerEvents = "none";//虚化table使其不可点击
    let body = document.body;//返回当前body文档的元素
    let div = $("<div></div>").addClass("float divRadius").appendTo(body);//div插到body
    //头部
    $("<div><div/>").addClass("box boxRadius").appendTo(div).text(string);
    //body
    let ul = $("<ul></ul>")
    let li = [];
    let input = [];
    for (let i = 0; i < headDate.length - 2; i++) {
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
        //创建每行的li与input标签
        li[i] = document.createElement("li");
        input[i] = document.createElement("input");
        input[i].type = "text";
        input[i].value = studentDataArray[i];
        li[i].innerHTML = temp;
        li[i].appendChild(input[i]);
        ul.append(li[i]);
        if (string === "学生信息") {
            input[i].disabled = "disable";//查询时为不可编辑状态
        }
    }
    $("<div><div/>").addClass("boxFlex").appendTo(div).append(ul);
    //确定，取消按钮放在boxLast
    let boxLast = $("<div><div/>").addClass("boxLast").appendTo(div);
    let btn01 = $("<button/>").addClass("style1").appendTo(boxLast).text("确定");
    let btn02 = $("<button/>").addClass("style2").appendTo(boxLast).text("取消");
    btn01.click(function () {
        back.style.pointerEvents = "auto";//恢复
        div.remove();//移去div块内容
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
        if (index >= 0 && index < allTr) {//由最大下标确定是执行更新还是增加方法
            for (let j = 0; j < head.length; j++) {
                if ((input[j].value===""
                    || isNaN(input[0].value) || isNaN(input[5].value) || isNaN(input[6].value) || isNaN(input[7].value)
                    || (input[2].value !== "男" &&  input[2].value!== "女"))) {
                    alert("请注意输入格式！");
                    return;
                }
                jsonObj[head[j]] = input[j].value;
            }
            $.ajax({
                type: "GET",
                url: "/updateReq",
                data: jsonObj,//向后端输入数据
                success: function () {
                    clear();
                    getData(currentPage);
                }
            })
        } else {
            for (let j = 0; j < head.length; j++) {
                if ((input[j].value===""
                    || isNaN(input[0].value) || isNaN(input[5].value) || isNaN(input[6].value) || isNaN(input[7].value)
                    || (input[2].value !== "男" &&  input[2].value!== "女"))) {
                    alert("请注意输入格式！");
                    return;
                }
                    jsonObj[head[j]] = input[j].value;
            }
            $.ajax({
                type: "GET",
                url: "/addReq",
                data: jsonObj,
                success: function () {
                    clear();
                    getData(currentPage);
                }
            })
        }
    });
    btn02.click(function () {//取消触发事件
        back.style.pointerEvents = "auto";//恢复
        div.remove();
    });
}

/**
 * 全选与反选方法
 **/
function check() {
    calculate(currentPage);
    let count = 0;//根据每页的选中数判断反选情况
    let check01 = $("#checkAll");
    check01.click(function () {
        for (let i = starIndex; i < endIndex; i++) {
            let arrayCheck = $("." + 2 * i);
            let boolean = check01.prop("checked");//获取该属性的值
            arrayCheck.prop("checked", boolean);
        }
        count = currentPageTr;//总的复选款数应与当前页面的行数对应
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

            if (count !== currentPageTr) {
                check01.prop("checked", false);
            }
        })
    }
}

/**
 * 增加方法
 **/
function add() {
    calculate(currentPage);
    $("#add").click(function () {
        let empty = ["", "", "", "", "", "", "", ""];//清空text
        maxIndex++;
        createForm(empty, maxIndex, "新增学生信息");
    })
}

add();//设为可使用状态

/**
 * 删除方法
 **/
function del() {
    calculate(currentPage);
    $("#delete").click(function () {
        let temp = [];//存储选中的复选款所在行的索引
        for (let i = starIndex; i < endIndex; i++) {
            let arrayCheck = $("." + 2 * i);
            if (arrayCheck.prop("checked") === true) {
                temp.push(i);
            }
        }
        let count = [];
        for (let j = 0; j < temp.length; j++) {
            let k = temp[j];//取出索引
            count.push(array[k]["id"]);//push要删除的多行学生数据的id
        }
        $.ajax({
            type: "GET",
            url: "/deleteData",
            data: {"count": count},
            success: function () {//后端输出文本的返回值，这里为无
                clear();
                getData(currentPage);//渲染
            }
        })
    })
}

del();//设为可使用状态

/**
 * 修改方法
 **/
function update() {
    calculate(currentPage);
    for (let i = starIndex; i < endIndex; i++) {
        let single = 2 * i + 1;
        let btnSingle = $("#" + single);
        btnSingle.click(function () {
            let studentDate = ["", "", "", "", "", "", "", ""];
            for (let j = 0; j < head.length; j++) {
                studentDate[j] = array[i][head[j]];
            }
            createForm(studentDate, i, "修改学生信息");
        })
    }
}

/**
 * 查询方法
 **/
function query() {
    calculate(currentPage);
    for (let i = starIndex; i < endIndex; i++) {
        let btnDouble = $("#" + 2 * i);
        btnDouble.click(function () {
            let studentDate = ["", "", "", "", "", "", "", ""];
            for (let j = 0; j < head.length; j++) {
                studentDate[j] = array[i][head[j]];
            }
            createForm(studentDate, i, "学生信息");
        })
    }
}

/**
 * 翻页方法
 **/
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

//上一页
$("#pre").click(function () {
    currentPage--;
    if (currentPage === 0)
        alert("已经是第一页");
    else {
        getData(currentPage);
        calculate(currentPage);
    }
})

/**
 * 模糊搜索方法
 **/
$("#search").addClass("iconfont icon-guanliyuansousuo");

function search() {
    let data = $(".input").val();
    $.ajax({
        type: "GET",
        url: "/search",
        data: {"data": data},
        dataType: 'json',
        success: function (text) {//text为从服务器端返回文本字符串，由于dataType而转为json对象
            array = text.students;
            allTr = text.length;//返回符合条件的学生数据的行数
            clear();
            calculate(currentPage);
            tBody(currentPage);
            query();
            update();
            check();
            $(".input").val("");
        }
    })

}

