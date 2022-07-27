let students=[]

//添加人员
function sure1() {
    let number = document.getElementById("text-number").value
    let name = document.getElementById("text-name").value
    let gender = document.getElementById("text-gender").value
    let college = document.getElementById("text-college").value
    let major = document.getElementById("text-major").value
    let grade = document.getElementById("text-grade").value
    let class1 = document.getElementById("text-class").value
    let age = document.getElementById("text-age").value

    let xml = new XMLHttpRequest();
    xml.open("get", "/add?id=" + number + "&name=" + name + "&gender=" + gender + "&college=" + college +
        "&major=" + major + "&grade=" + grade + "&class=" + class1 + "&age=" + age);
    xml.send();
    xml.onreadystatechange = function () {

        if (xml.readyState === 4) {
           getDate()
            console.log("完成添加")
        }
    }
    let tr = document.querySelector('tbody').querySelectorAll('tr')

    for (let i = 0; i < tr.length; i++) {
        tr[i].remove()
    }

}


    let modal = document.querySelector('.model-box')
    let sure = document.getElementById("sure-box")

    sure.addEventListener('click', function () {
        modal.classList.toggle('show-modal')
    })


let tbody = document.querySelector('tbody')
let page=0

//获得数据
function getDate(){
    let  xml=new XMLHttpRequest();
    xml.open("get","/operation");
    xml.send();
    xml.onreadystatechange=function (){

        if (xml.readyState===4){
            let text=xml.response;
            let a = JSON.parse(text)
            students = a.data
            create();
            console.log(students)
        }
    }
}

getDate();

//渲染方法
function create() {

    for (let i = page*10; i <(page+1)*10 &&i<students.length ; i++){

        let tr = document.createElement('tr')
        tbody.appendChild(tr);

        //创建复选框
        let checktd = document.createElement('td')
        let check = document.createElement('input')
        check.type = "checkbox"
        check.name = 'check-all'
        checktd.appendChild(check)
        tr.appendChild(checktd)

        //创建序号列

        let num = document.createElement('td')
        num.innerHTML=(i+1).toString()
        tr.appendChild(num);

        //创建内容单元格
            let td1 = document.createElement('td')
            td1.className='tl1'
            td1.innerHTML=students[i].id
            tr.appendChild(td1)


           let td2=document.createElement('td')
            td2.className='tl2'
            td2.innerHTML = students[i].name
            tr.appendChild(td2)

            let td3 = document.createElement('td')
            td3.className='tl3'
            td3.innerHTML = students[i].gender
            tr.appendChild(td3)

            let td4 = document.createElement('td')
            td4.className='tl4'
            td4.innerHTML = students[i].college
            tr.appendChild(td4)

            let td5 = document.createElement('td')
            td5.className='tl5'
            td5.innerHTML = students[i].major
            tr.appendChild(td5)

            let td6 = document.createElement('td')
            td6.className='tl6'
            td6.innerHTML = students[i].grade
            tr.appendChild(td6)

            let td7 = document.createElement('td')
            td7.className='tl7'
            td7.innerHTML = students[i].class1
            tr.appendChild(td7)

            let td8 = document.createElement('td')
            td8.className='tl8'
            td8.innerHTML = students[i].age
            tr.appendChild(td8)

        //创建操作列
        let td = document.createElement('td')
        td.innerHTML = '<img src="imgs/查看.png"   onclick="look(this)">' +
            " " + '<img src="imgs/修改.png" onclick="settext(this)">'
        tr.appendChild(td);


    }
//鼠标事件

    let trs = document.querySelector('tbody').querySelectorAll('tr')
    for (let i = 0; i < trs.length; i++) {
        trs[i].onmouseover = function () {
            this.className = "mouse-color"
        }
        trs[i].onmouseout = function () {
            this.className = '';
        }
    }


    //初始展示十行
    if (trs.length !== 10) {
        for (let i = 10; i < trs.length; i++) {
            trs[i].remove()

        }

    }

}

//下一页
function nextpage(){

    let tr=document.querySelector('tbody').querySelectorAll('tr')
    if((page+1)*10>=students.length) {alert('最后一页')}
    else { page++
        for (let i = 0; i < tr.length; i++) {
            tr[i].remove()
        }
        create()
    }
}

//上一页
function uppage() {

    let tr1=document.querySelector('tbody').querySelectorAll('tr')
    if(page===0) {alert('第一页')}
    else { page--
        for (let i = 0; i <tr1.length; i++) {
            tr1[i].remove()
        }
        create()
    }


//创建表格

}

//创建添加人员模态框
function createbox() {
    let modal = document.querySelector(".model-box")
    modal.classList.toggle('show-modal')
    disabled(false)
    deinput()

    let setbox1 = document.getElementById('set-box')
    setbox1.style.display='none'
    let surebox1=document.getElementById('sure-box')
    let back=document.getElementById('back-box')
    let returnbox1 = document.getElementById("return-box")
    surebox1.style.display='block'
    returnbox1.style.display='block'
    back.style.display='none'
}

//input不可改动
function disabled(m) {
    let input1 = document.getElementById("text-number")
    input1.disabled = m;
    let input2 = document.getElementById('text-name')
    input2.disabled = m;

    let input3 = document.getElementById("text-gender")
    input3.disabled = m;
    let input4 = document.getElementById('text-college')
    input4.disabled = m;

    let input5 = document.getElementById("text-major")
    input5.disabled = m;
    let input6 = document.getElementById('text-grade')
    input6.disabled = m;

    let input7 = document.getElementById("text-class")
    input7.disabled = m;
    let input8 = document.getElementById('text-age')
    input8.disabled = m;



}

//根据节点获取信息
function inputshow(input1) {

    let all = input1.parentNode.parentNode;

    let demo1 = all.cells[2].innerText
    let input = document.getElementById('text-number')
    input.value=demo1

    let demo2 = all.cells[3].innerText
    let input2 = document.getElementById('text-name')
    input2.value = demo2

    let demo3 = all.cells[4].innerText
    let input3 = document.getElementById('text-gender')
    input3.value = demo3

    let demo4 = all.cells[5].innerText
    let input4 = document.getElementById('text-college')
    input4.value = demo4

    let demo5 = all.cells[6].innerText
    let input5 = document.getElementById('text-major')
    input5.value = demo5

    let demo6 = all.cells[7].innerText
    let input6 = document.getElementById('text-grade')
    input6.value = demo6

    let demo7 = all.cells[8].innerText
    let input7 = document.getElementById('text-class')
    input7.value = demo7

    let demo8 = all.cells[9].innerText
    let input8 = document.getElementById('text-age')
    input8.value = demo8
}

//修改人员数组内容
function outputshow(input1) {
    let all=input1.parentNode.parentNode
    console.log("all",all)
    console.log(all.rowIndex,"index")
    console.log(all.cells[2].innerText)
    all.cells[2].innerText =document.getElementById('text-number').value
    let number=document.getElementById('text-number').value
    all.cells[3].innerText = document.getElementById('text-name').value
    let name=document.getElementById('text-name').value
    all.cells[4].innerText = document.getElementById('text-gender').value
    let gender=document.getElementById('text-gender').value
    all.cells[5].innerText = document.getElementById('text-college').value
    let college=document.getElementById('text-college').value
    all.cells[6].innerText = document.getElementById('text-major').value
    let major=document.getElementById('text-major').value
    all.cells[7].innerText = document.getElementById('text-grade').value
    let grade=document.getElementById('text-grade').value
    all.cells[8].innerText = document.getElementById('text-class').value
    let class1=document.getElementById('text-class').value
    all.cells[9].innerText = document.getElementById('text-age').value
    let age=document.getElementById('text-age').value

    let rowIndex=all.cells[1].innerText

    let personnew={
        number:number,
        name:name,
        gender:gender,
        college:college,
        major:major,
        grade:grade,
        class:class1,
        age:age
    }
    console.log(personnew)

    let xml = new XMLHttpRequest();
    xml.open("get", "/update?id=" + number + "&name=" + name + "&gender=" + gender + "&college=" + college +
        "&major=" + major + "&grade=" + grade + "&class=" + class1 + "&age=" + age );
    xml.send();
    xml.onreadystatechange = function () {
        if (xml.readyState === 4) {
            console.log("修改成功")
        }
    }

    students.splice(rowIndex-1, 1, personnew)

}

//查看人员信息
function look(input1) {
    let modal = document.querySelector(".model-box")
    modal.classList.toggle('show-modal')
    disabled(true)
    inputshow(input1)

    let back=document.getElementById('back-box')
    back.onclick=function (){
        modal.classList.toggle('show-modal')
    }
    back.style.display='block'
    let returnbox2 = document.getElementById('return-box')
    let surebox2 = document.getElementById('sure-box')
    returnbox2.style.display='none'
    surebox2.style.display='none'

    let setbox = document.getElementById('set-box')
    setbox.style.display='none'

}

//修改人员信息
function settext(input1) {
    let modal = document.querySelector(".model-box")
    modal.classList.toggle('show-modal')

    inputshow(input1)
    disabled(false)

    let back=document.getElementById('back-box')
    let change=document.getElementById('set-box')
    change.onclick=function (){
        outputshow(input1)
        modal.classList.toggle('show-modal')
    }
    change.style.display='block'
    back.style.display='block'
    let returnbox2 = document.getElementById('return-box')
    let surebox2 = document.getElementById('sure-box')
    returnbox2.className = 'hidden'
    surebox2.className = 'hidden'




}

//删除人员信息
function deleterow() {


    let check = document.getElementsByName('check-all')
    for (let m = 0; m < check.length; m++) {
        if (check[m].checked) {
            for (let i = page*10; i <(page+1)*10 &&i<students.length ; i++){
                window.location.href="delete?v="+students[i].id
            }

            tbody.deleteRow(m);
            students.splice(m, 1)
            m--;
        }

    }
    let tr = document.querySelector('tbody').querySelectorAll('tr')
    for (let i = 0; i < tr.length; i++) {
        tr[i].remove()
    }
    create()

}
//复选框
function checkall(obj) {
    let check = document.getElementsByName('check-all')
    let titlecheck = obj.checked
    for (let i = 0; i < 10; i++) {
        check[i].checked=titlecheck
    }
}

//更新文本框
function deinput() {
    document.getElementById('text-number').value = ''
    document.getElementById('text-name').value = ''
    document.getElementById('text-gender').value = ''
    document.getElementById('text-college').value = ''
    document.getElementById('text-major').value = ''
    document.getElementById('text-grade').value = ''
    document.getElementById('text-class').value = ''
    document.getElementById('text-age').value = ''
    let modal=document.querySelector('.model-box')
    let returnbox1 = document.getElementById("return-box")
    returnbox1.onclick=function (){
        modal.classList.toggle('show-modal')
    }
}

//搜索
function searchfor() {
 let person=[]
    let v = document.getElementById('search').value;
    let xml = new XMLHttpRequest();
    xml.open("get", "/search?v=" + v);
    xml.send();
    xml.onreadystatechange = function () {

        if (xml.readyState === 4) {
            let text=xml.response;
            let a = JSON.parse(text)
            person = a.data
            let tr = document.querySelector('tbody').querySelectorAll('tr')

            for (let i = 0; i < tr.length; i++) {
                tr[i].remove()
            }
            create1(person)
            console.log(person)
        }

    }
}

//搜索渲染方法
function create1(person){
    for (let i = page*10; i <(page+1)*10 &&i<person.length ; i++){

        let tr = document.createElement('tr')
        tbody.appendChild(tr);

        //创建复选框
        let checktd = document.createElement('td')
        let check = document.createElement('input')
        check.type = "checkbox"
        check.name = 'check-all'
        checktd.appendChild(check)
        tr.appendChild(checktd)

        //创建序号列

        let num = document.createElement('td')
        num.innerHTML=(i+1).toString()
        tr.appendChild(num);

        //创建内容单元格
        let td1 = document.createElement('td')
        td1.className='tl1'
        td1.innerHTML=person[i].id
        tr.appendChild(td1)


        let td2=document.createElement('td')
        td2.className='tl2'
        td2.innerHTML = person[i].name
        tr.appendChild(td2)

        let td3 = document.createElement('td')
        td3.className='tl3'
        td3.innerHTML = person[i].gender
        tr.appendChild(td3)

        let td4 = document.createElement('td')
        td4.className='tl4'
        td4.innerHTML = person[i].college
        tr.appendChild(td4)

        let td5 = document.createElement('td')
        td5.className='tl5'
        td5.innerHTML = person[i].major
        tr.appendChild(td5)

        let td6 = document.createElement('td')
        td6.className='tl6'
        td6.innerHTML = person[i].grade
        tr.appendChild(td6)

        let td7 = document.createElement('td')
        td7.className='tl7'
        td7.innerHTML = person[i].class1
        tr.appendChild(td7)

        let td8 = document.createElement('td')
        td8.className='tl8'
        td8.innerHTML = person[i].age
        tr.appendChild(td8)

        //创建操作列
        let td = document.createElement('td')
        td.innerHTML = '<img src="imgs/查看.png"   onclick="look(this)">' +
            " " + '<img src="imgs/修改.png" onclick="settext(this)">'
        tr.appendChild(td);


    }
//鼠标事件

    let trs = document.querySelector('tbody').querySelectorAll('tr')
    for (let i = 0; i < trs.length; i++) {
        trs[i].onmouseover = function () {
            this.className = "mouse-color"
        }
        trs[i].onmouseout = function () {
            this.className = '';
        }
    }


    //初始展示十行
    if (trs.length !== 10) {
        for (let i = 10; i < trs.length; i++) {
            trs[i].remove()

        }

    }

}