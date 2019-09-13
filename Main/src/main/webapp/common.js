function isObject(val) {
    return (typeof val === 'object');
}

/** Open a request and return the object to be used at the caller's context
 * 
 * @param {String} httpMethod => the http method that gonna be used to open the request
 * @param {String} url => the url the will be used
 * @param {String (JSON)} requestBody 
 */
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

/** Go to another destination (page) being possible to pass input parameters  
 * 
 * @param {String} url => the destination page
 * @param {Object Array} params  => the page input parameters
 */
function goTo(url, params) {
    if(params.isArray) { // check is not undefined, null, ...
        array.forEach(param => {
            window.sessionStorage.setItem(param.key, param.value); 
        });
    } else if(isObject(params)) {
        window.sessionStorage.setItem(params.key, params.value); 
    }  else {
        alert("The url '" + url + "' was caught without params");
    }

    window.location.href = url;
}

// ------------------------ Old personal util functions ----------------------------

/**
 * Retorna um objeto com chaves e valores associados
 * @param map = mapa (keys + values) que é transformado em objeto
 */

function mapToObject(map) {
    let obj = Object.create(null);
    for (let [k,v] of map) {
        obj[k] = v;
    }
    return obj;
}

/**
 * Retorna um mapa com chaves e valores associados
 * @param obj = objeto com atributos e valores preenchidos que é transformado em mapa (keys + values)
 */

function objectToMap(obj) {
    let map = new Map();
    for (let k of Object.keys(obj)) {
        map.set(k, obj[k]);
    }
    return map;
}

/**
 * Função sem retorno; Resultado: troca o textNode de um array de elementos
 * @param elementID = ids dos elementos html que serão modificados
 * @param values = novos valores que serão setados nos elementos
 */

function changeElementsText(elementsID, values) {
    for (let i = 0; i < elementsID.length; i++) 
	document.getElementById(elementsID[i]).innerHTML = values[i];
}

function changeElementText(elementID, value) {
	document.getElementById(elementID).innerHTML = value;
}

function changeElementAttributes(elementID, attributes, values) {
    for (let i = 0; i < attributes.length; i++) {
        //document.getElementById(elementID).setAttribute(attributes[i], values[i]);
        $('#'+elementID).attr(attributes[i], values[i]);
    }
}

/**
 * Retona um objeto a partir de um array de inputs
 * @param inputIDS = array de inputs (ids) que será usado para recuperar os elementos inputs
 * e passar o attributo 'name' como key
 */

function getInputsValue(inputIDs) {
	let map = new Map();

	for (let i = 0; i < inputIDs.length; i++) {
        let currentInput = document.getElementById(inputIDs[i]);
		map.set(currentInput.getAttribute('name'), currentInput.value)
	}

	return mapToObject(map);
}

/**
 * Função sem retorno; Resultado: mostra um alert (tipo danger ou success) de retorno ao usuário por 6 segundos
 * @param alertID = id do alert que será exibido
 */

function showAlert(alertID) {
	document.getElementById(alertID).style.display = 'inline-block';
	setTimeout(function(){ document.getElementById(alertID).style.display = 'none'; }, 6000);
}

/**
 * Função sem retorno; Resultado: fecha um modal pelo seu id
 * @param alertID = id do modal a ser fechado
 */

function closeModal(modalID) {
    $('#' + modalID).modal('hide');
}


/**
 * Função que adiciona eventos a todos elementos de uma classe
 * @param classe classe que será usada para adicionar eventos 
 * @param event evento que será usado como parametro no eventListenr
 * @param funcao funcao que será chamada quando o evento for acionado
 * @param params parametros da função em forma de objeto (que seram usados na função acima)
 */

function eventListenerByClass(classe, event, funcao, params) {
    classe = document.getElementsByClassName(classe);

    for (let i = 0; i < classe.length; i++) { 
        classe[i].addEventListener(event, function() { 
            eval(funcao)(params, classe[i]);
        });
    }
}