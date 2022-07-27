let people= {} ;
// [{
//     number: "11921380131",
//     name: "蒋锡培",
//     gender: "男",
//     college: "人工智能学院",
//     major: "软件工程",
//     grade: "2019",
//     class: "1",
//     age: "22"
// }, {
//     number: "11921380132",
//     name: "向文波",
//     gender: "女",
//     college: "人工智能学院",
//     major: "软件工程",
//     grade: "2019",
//     class: "1",
//     age: "22"
// }, {
//     number: "11921380133",
//     name: "牛根生",
//     gender: "男",
//     college: "人工智能学院",
//     major: "软件工程",
//     grade: "2019",
//     class: "1",
//     age: "22"
// }, {
//     number: "11921380134",
//     name: "于清教",
//     gender: "女",
//     college: "人工智能学院",
//     major: "软件工程",
//     grade: "2019",
//     class: "1",
//     age: "22"
// }, {
//     number: "11921380135",
//     name: "谢清海",
//     gender: "男",
//     college: "人工智能学院",
//     major: "软件工程",
//     grade: "2019",
//     class: "1",
//     age: "22"
// }, {
//     number: "11921380136",
//     name: "刘旗辉",
//     gender: "女",
//     college: "人工智能学院",
//     major: "软件工程",
//     grade: "2019",
//     class: "1",
//     age: "22"
// }, {
//     number: "11921380137",
//     name: "陈达夫",
//     gender: "男",
//     college: "人工智能学院",
//     major: "软件工程",
//     grade: "2019",
//     class: "1",
//     age: "22"
// }, {
//     number: "11921380138",
//     name: "叶茂中",
//     gender: "女",
//     college: "人工智能学院",
//     major: "软件工程",
//     grade: "2019",
//     class: "1",
//     age: "22"
// }, {
//     number: "11921380139",
//     name: "李士福",
//     gender: "男",
//     college: "人工智能学院",
//     major: "软件工程",
//     grade: "2019",
//     class: "1",
//     age: "22"
// }, {
//     number: "11921380110",
//     name: "王进生",
//     gender: "女",
//     college: "人工智能学院",
//     major: "软件工程",
//     grade: "2019",
//     class: "1",
//     age: "22"
// }, {
//     number: "11921380111",
//     name: "任志强",
//     gender: "男",
//     college: "人工智能学院",
//     major: "软件工程",
//     grade: "2019",
//     class: "1",
//     age: "22"
// }, {
//     number: "11921380112",
//     name: "孙虹钢",
//     gender: "女",
//     college: "人工智能学院",
//     major: "软件工程",
//     grade: "2019",
//     class: "1",
//     age: "22"
// }, {
//     number: "11921380113",
//     name: " 宋新宇",
//     gender: "男",
//     college: "人工智能学院",
//     major: "软件工程",
//     grade: "2019",
//     class: "1",
//     age: "22"
// }, {
//     number: "11921380114",
//     name: "翁向东",
//     gender: "女",
//     college: "人工智能学院",
//     major: "软件工程",
//     grade: "2019",
//     class: "1",
//     age: "22"
// }];
let peoo=[];
let findPeople={};
let change3;
//---------------初始数据
window.onload=function (){
    let passage=document.getElementById('passage');
    let p1=parseInt(passage.innerHTML);
    $.ajax({
        type:"post",
        url:"/findAll",
        data:"null",
        dataType:"json",
        success(data){
            people=data;
            setPeople(people,0,1);
        },
        error(){

        }
    })
}
//----------------添加一行
function creatPeople(name, gender, college, major, grade, Class, age, number,x) {
    let tbody = document.getElementById('menuBody');
    let dom = document.createElement('tr');
    let td1 = document.createElement('td');
    let td2 = document.createElement('td');
    let td3 = document.createElement('td');
    let td4 = document.createElement('td');
    let td5 = document.createElement('td');
    let td6 = document.createElement('td');
    let td7 = document.createElement('td');
    let td8 = document.createElement('td');
    let td9 = document.createElement('td');
    let td10 = document.createElement('td');
    td1.innerHTML = "<div><input type='checkbox' name='checkInput' onclick='opposite(this)'>"+x+"</div>";
    td2.innerHTML = "<div>" + name + "</div>";
    td3.innerHTML = "<div>" + gender + "</div>";
    td4.innerHTML = "<div>" + college + "</div>";
    td5.innerHTML = "<div>" + major + "</div>";
    td6.innerHTML = "<div>" + grade + "</div>";
    td7.innerHTML = "<div>" + Class + "</div>";
    td8.innerHTML = "<div>" + age + "</div>";
    td9.innerHTML = "<div>" + number + "</div>";
    td10.innerHTML=" <div><button class='add1' onclick='change(this)'></button>"+"<button class='add3' onclick='look(this)'></button>"+
        "<button class=\"delete1\" onclick='del(this)'> - </button>\</div>";
    dom.appendChild(td1);
    dom.appendChild(td2);
    dom.appendChild(td3);
    dom.appendChild(td4);
    dom.appendChild(td5);
    dom.appendChild(td6);
    dom.appendChild(td7);
    dom.appendChild(td8);
    dom.appendChild(td9);
    dom.appendChild(td10);
    tbody.appendChild(dom);
}

//-----------------写入数组
function setPeople(people ,i,j) {
    for ( i; i < 10+10*j; i++) {
        if(people[i]==null){
            break;
        }
        else {
            creatPeople(people[i].name, people[i].gender, people[i].college, people[i].major, people[i].grade, people[i].classes,
                people[i].age, people[i].number, i + 1);
        }
    }
}
setPeople(people,0,1);
//---------------------下一页
let che=[];
let num=0;
let che2=[];
let passage = document.getElementById('passage');
function turnNext(){
    let p=parseInt(passage.innerHTML)+1;
    if(people[(p-1)*10]!==undefined) {
        document.getElementById('menuBody').innerHTML = '';
        passage.innerHTML = p;
        setPeople(people, (p - 1) * 10, p - 1);
        rev();
        let selectAll = document.getElementById('selectAll');
        let checks2 = document.getElementsByName('checkInput');
        for (let i = 0; i < checks2.length; i++) {
            if (checks2[i].checked === false) {
                selectAll.checked = false;
            }
        }
    }
}
//------------------上一页
function turnUp(){
    let p=parseInt(passage.innerHTML)-1;
    if (p>0) {
        passage.innerHTML = p;
        document.getElementById('menuBody').innerHTML = '';
        setPeople(people, (p - 1) * 10, p - 1);
        rev();
        let selectAll=document.getElementById('selectAll');
        let checks2=document.getElementsByName('checkInput');
        for (let i=0;i<checks2.length;i++){
            if(checks2[i].checked===false){
                selectAll.checked=false;
            }

        }
    }
}
//------------------全选
function selectAll(){
       let  select=document.getElementById('selectAll');
       let  checks=document.getElementsByName('checkInput');
    for (let i=0;i<checks.length;i++){
          if(!checks[i].checked===true){
              checks[i].checked=true;
          }
          checks[i].checked = select.checked === true;
    }

}
//------------保存选择
function rev(){
    let checks=document.getElementsByName('checkInput');
        for (let i = 0; i < checks.length; i++) {
               for (let j = 0; j < che.length; j++) {
                if (checks[i].parentNode.textContent === che[j]) {
                    checks[i].checked = true;
                }
             }
        }
}
// ----------反选
function  opposite(obj){
    let  checks=document.getElementsByName('checkInput');
    let select=document.getElementById('selectAll');
    let turn=true;
    for(let i=0;i<checks.length;i++){
        if(checks[i].checked!==true){
            select.checked=false;
            turn=false;
            break;
        }
      }
    if (turn===true){
        select.checked=true;
    }
    let c=obj.parentNode.textContent;
    if(obj.checked){
        che[num]=c;
        num++;
    }
    else {
        for(let i=0;i<che.length;i++){
            if(che[i]===c){
                che.splice(i,1);
            }
        }
    }
}
//------------btn添加add
function  add(){
   let smallMenu=document.getElementById('smallMenu');
    smallMenu.hidden=false;
    let add2=document.getElementById('add2');
    add2.hidden=false;
    let change=document.getElementById('change');
    change.hidden=true;
    let cancel=document.getElementById('cancel');
    cancel.style.marginLeft='50px';
}

function  cancel(){
    let smallMenu=document.getElementById('smallMenu');
    smallMenu.hidden=true;
}
//----------------------读取表单元素
function read(){
    let  inputs=document.getElementsByName('input');
    for (let k=0;k<inputs.length;k++){
        inputs[k].removeAttribute('readonly');
    }
    let inputName=document.getElementById('name').value;
    let inputGender=document.getElementById('gander').value;
    let inputCollege=document.getElementById('college').value;
    let inputMajor=document.getElementById('major').value;
    let inputGrade=document.getElementById('grade').value;
    let inputClass=document.getElementById('Class').value;
    let inputAge=document.getElementById('age').value;
    let inputNumber=document.getElementById('number').value;
    return {
        number: inputNumber,
        name: inputName,
        gender: inputGender,
        college: inputCollege,
        major: inputMajor,
        grade: inputGrade,
        class: inputClass,
        age: inputAge
    };
}
//---------------------btn add表单添加数据
function  submit() {
    let p = parseInt(passage.innerHTML);
    let peo=read();
    $.ajax({
        type: "post",
        url: "/add",
        data:peo,
        dataType: "json",
        success(data){
            document.getElementById('menuBody').innerHTML = '';
            people=data;
                setPeople(people, (p - 1) * 10, p - 1);
            selectAll();
        },
        error(){
           alert("添加失败");
        }
    })

}

//--------------btn删除delete
function delete2(){
    let return2 = document.getElementById('return');
    return2.hidden = true;
    let p=parseInt(passage.innerHTML);
    let d=document.getElementsByName('checkInput');
    let id=[];
        if (d.length > 10) {
            for (let j = 9; j >= 0; j--) {
                if (d[j].checked === true) {
                 let tr= d[j].parentNode.parentNode.parentNode;
                 let tds=tr.childNodes;
                    console.log(tds[8].firstChild.textContent);
                    id.push(tds[8].firstChild.textContent);
                }
            }
        } else {
            for (let j = d.length - 1; j >= 0; j--) {
                if (d[j].checked === true) {
                    let tr= d[j].parentNode.parentNode.parentNode;
                    let tds=tr.childNodes;
                    console.log(tds[8].firstChild.textContent);
                    id.push(tds[8].firstChild.textContent);
                }
            }
        }
      $.ajax({
          type:"post",
          url:"/deleteAll",
          data:{
             "array": id,
          },
          dataType:"json",
          success(data){
              people=data;
              document.getElementById('menuBody').innerHTML = '';
                  people=data;
                  setPeople(people, (p - 1) * 10, p - 1);
              let select=document.getElementById('selectAll');
              select.checked=false;
              id=[];
          },
          error(){
              alert("删除错误");
          }
      })
}
//----------删除功能
function  del(obj) {
    let return2 = document.getElementById('return');
    return2.hidden = true;
    let p = parseInt(passage.innerHTML);
    let trd = obj.parentNode.parentNode.parentNode;
    let tds = trd.childNodes;
     let id=tds[8].firstChild.textContent;
    console.log(change3);
    $.ajax({
        type: "post",
        url: "/delete",
        data:{
          number: id
        },
        dataType: "json",
        success(data){
            document.getElementById('menuBody').innerHTML = '';
            people=data;
            setPeople(people, (p - 1) * 10, p - 1);
            selectAll();
        },
        error(){
            alert("删除失败");
        }
    })
}
//改变功能
let id;
function change(obj) {
    let  inputs=document.getElementsByName('input');
    for (let k=0;k<inputs.length;k++){
        inputs[k].removeAttribute('readonly');
    }
    console.log("sb");
    let smallMenu=document.getElementById('smallMenu');
    smallMenu.hidden=false;
    let add2=document.getElementById('add2');
    add2.hidden=true;
    let change=document.getElementById('change');
    change.hidden=false;
    let trd = obj.parentNode.parentNode.parentNode;
    let tds = trd.childNodes;
    id=tds[8].firstChild.textContent;
    let cancel=document.getElementById('cancel');
    cancel.style.marginLeft='50px';
    document.getElementById('name').value=tds[1].firstChild.textContent;
    document.getElementById('gander').value=tds[2].firstChild.textContent;
    document.getElementById('college').value=tds[3].firstChild.textContent;
    document.getElementById('major').value=tds[4].firstChild.textContent;
    document.getElementById('grade').value=tds[5].firstChild.textContent;
    document.getElementById('Class').value=tds[6].firstChild.textContent;
    document.getElementById('age').value=tds[7].firstChild.textContent;
    document.getElementById('number').value=tds[8].firstChild.textContent;
}
//--------------改变提交
function change2(qualifiedName){
    let p = parseInt(passage.innerHTML);
    let peo=read();
    let smallMenu=document.getElementById('smallMenu');
    smallMenu.hidden=true;
    let return2 = document.getElementById('return');
    return2.hidden = true;
    peo.id=id;
    $.ajax({
        type:"post",
        url:"/change",
        data:peo,
        dataType:"json",
        success(data){
            if(data.length>0) {
                document.getElementById('menuBody').innerHTML = '';
                people = data;
                setPeople(people, (p - 1) * 10, p - 1);
                selectAll();
            }else{
                alert("修改错误");
            }
        },
        error(){
            alert("修改错误");
        }
    })
}
//查看
function look(obj){
    let smallMenu=document.getElementById('smallMenu');
    smallMenu.hidden=false;
    let add2=document.getElementById('add2');
    add2.hidden=true;
    let change=document.getElementById('change');
    change.hidden=true;
    let cancel=document.getElementById('cancel');
    cancel.style.marginLeft='120px';
    let trd = obj.parentNode.parentNode.parentNode;
    let tds = trd.childNodes;
    document.getElementById('name').placeholder=tds[1].firstChild.textContent;
    document.getElementById('gander').placeholder=tds[2].firstChild.textContent;
    document.getElementById('college').placeholder=tds[3].firstChild.textContent;
    document.getElementById('major').placeholder=tds[4].firstChild.textContent;
    document.getElementById('grade').placeholder=tds[5].firstChild.textContent;
    document.getElementById('Class').placeholder=tds[6].firstChild.textContent;
    document.getElementById('age').placeholder=tds[7].firstChild.textContent;
    document.getElementById('number').placeholder=tds[8].firstChild.textContent;
     let  inputs=document.getElementsByName('input');
     for (let k=0;k<inputs.length;k++){
         inputs[k].setAttribute('readonly','readonly');
     }
}
//-----------搜索框
function find(){
    return document.getElementById('research').value;

}
function start1() {
    let p = parseInt(passage.innerHTML);
    let value=find();
    $.ajax({
        type:"post",
        url:"/search",
        data: {
            "Value":value,
        },
        dataType:"json",
        success(data){
            if(data.length>0) {
                document.getElementById('menuBody').innerHTML = '';
                findPeople = data;
                let return2 = document.getElementById('return');
                return2.hidden = false;
                setPeople(findPeople, (p - 1) * 10, p - 1);
            }else {
                alert("查询错误");
            }
        } ,
        error(){
            alert("查询错误");
        }
    })
}
//-----------------------返回
function return2(){
    let p = parseInt(passage.innerHTML);
    document.getElementById('menuBody').innerHTML = '';
    setPeople(people, (p - 1) * 10, p - 1);
    let return2=document.getElementById('return');
    change3=0;
    findPeople={};
    return2.hidden=true;
}



