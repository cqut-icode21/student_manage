var page = 1;
var amountPerPage = 10;

findAll();

function findAll(){
    axios({
        url : 'http://localhost:8080//findAll',
        method : 'GET',
        data : {
            "page" : page,
            "amountPerPage" : amountPerPage
        }
    }).then(
        response => {
            console.log(-1)
            console.log(response.data)
        },
        error => {
            alert(error.message)
        }
    )
}



