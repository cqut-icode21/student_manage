let students = []

function getData(page) {
    // console.log(username);
    let xml = new XMLHttpRequest();
    xml.open("get", "/operation?currentPage=" + page)
    xml.send()
    xml.onreadystatechange = function () {
        if (xml.readyState === 4) {
            let text = xml.response
            let a = JSON.parse(text)
            students = a.data
            renderData()
            console.log(students)
        }
    }
}

getData(1)


function renderData() {
    let tb = document.getElementById("tb");
    console.log(students.length)
    for (let i = 0; i < students.length; i++) {
        let tr = document.createElement("tr");
        tr.innerHTML =
            '<td><label><input type="checkbox"/></label> </td>' +
            '<td>' + students[i].sno + '</td>' +
            '<td>' + students[i].sAge + '</td>' +
            '<td>' + students[i].sDept + '</td>' +
            '<td>' + students[i].sGender + '</td>' +
            '<td><button>编辑</button><button>新增</button> </td>';
        console.log(i)
        tb.appendChild(tr)
    }
}

