function getRequests() {
    let url = "https://api.themoviedb.org/3/movie/550?api_key=f3868890cfd42037d89815f91e7f94ec";
    let xhttp = requesterBuilder('GET', url);
    
    xhttp.onreadystatechange = function() {
        if(this.status === 200 && this.readyState === 4) {
            console.log(this.response);
        }
    }
}

function requesterBuilder(httpMethod, url, requestBody) {
    let xhttp = new XMLHttpRequest();
    xhttp.open(httpMethod, url);
    xhttp.setRequestHeader("Content-Type", "application/json");

    if (requestBody) {
        xhttp.send(requestBody);
    } else {
        xhttp.send();
    }

    return xhttp;
}

function loginAsRicardo() {
    let usuario = {
        nome: "ricardo",
        senha: "123"
    }
    let xhttp = requesterBuilder("POST", "http://localhost:8080/appweb4/api/users/login", JSON.stringify(usuario));

    xhttp.onreadystatechange = function() {
        if(this.status === 200 && this.readyState === 4) {
            console.log(this.response);
        }
    }
}


(function() {
    loginAsRicardo();
    getRequests();
}())