let requestId = window.sessionStorage.getItem('requestId');

function getRequestById(requestId) {
    let url = "http://localhost:8080/ApiGateway/api/requests/" + requestId;

    let xhttp = requesterBuilder("GET", url);

    xhttp.onreadystatechange = function() {
        if(this.status === 200 && this.readyState === 4) {
            console.log(this.response);
        }
    }
}

(function() {
    if(requestId) {
        getRequestById(requestId);
    }
}())