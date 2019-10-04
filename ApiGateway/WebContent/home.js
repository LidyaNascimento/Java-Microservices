function getRequests() {
    let url = "https://api.themoviedb.org/3/movie/550?api_key=f3868890cfd42037d89815f91e7f94ec";
    let xhttp = requesterBuilder('GET', url);
    
    xhttp.onreadystatechange = function() {
        if(this.status === 200 && this.readyState === 4) {
            console.log(this.response);
        }
    }
}

function loginAsRicardo() {
    let url = "http://localhost:8080/ApiGateway/api/users/login";

    let usuario = {
        nome: "ricardo",
        senha: "123"
    }

    let xhttp = requesterBuilder("POST", url, JSON.stringify(usuario));

    xhttp.onreadystatechange = function() {
        if(this.status === 200 && this.readyState === 4) {
            console.log(this.response);
        }
    }
}

function goTo_RequestDetail() {
    let goTo_RequestDetail = document.getElementById('goTo_RequestDetail');
    goTo_RequestDetail.addEventListener('click', function() {
        let param = {};
        param.key = 'requestId';
        param.value = goTo_RequestDetail.getAttribute('requestId');
        goTo('requestDetail.html', param);
    })
}

function eventListeners() {
    goTo_RequestDetail();
}


(function() {
    eventListeners();
    loginAsRicardo();
    getRequests();
}())