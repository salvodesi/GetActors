function newXMLHttpRequest() {
		var request = null;
		var browser = navigator.userAgent.toUpperCase();
		if(typeof(XMLHttpRequest) === "function" || typeof(XMLHttpRequest) === "object") {
			request = new XMLHttpRequest();
		} else if(window.ActiveXObject && browserUtente.indexOf("MSIE 4") < 0) {
			if(browser.indexOf("MSIE 5") < 0) {
				request = new ActiveXObject("Msxml2.XMLHTTP");
			} else {
				request = new ActiveXObject("Microsoft.XMLHTTP");
			}
		}
		return request;
}


function search_actor(){
	var name = document.getElementById("actname").value;
	if(name == "")
		document.getElementById("risultati").innerHTML = "Inserisci un valore nel campo di ricerca";
	else{
		var req = newXMLHttpRequest();
		req.onreadystatechange = function(){
			if(req.readyState == 4){
				if(req.status == 200){
					getData(req.responseXML);
				}
				else if(req.status == 204){
					delete_suggest();
					}
				}
			}
	}
	req.open("POST", "GetActors", true);
	req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
	var params = "name=" + escape(name);
	req.send(params);
}

//debug function
function xml_to_string(xml_node)
{
    if (xml_node.xml)
        return xml_node.xml;
    else if (XMLSerializer)
    {
        var xml_serializer = new XMLSerializer();
        return xml_serializer.serializeToString(xml_node);
    }
    else
    {
        alert("ERROR: Extremely old browser");
        return "";
    }
}

function getData(responseXML){
	delete_suggest();
	var prec_risultati = document.getElementById("risultati");
	// Se la risposta non è nulla
	resp = responseXML.getElementsByTagName("row").length;
	if(resp > 0){
		// Punto alla radice della risposta
		var risposta = responseXML.getElementsByTagName("row");
			for(x = 0; x < resp ; x++ ){
				var nm = risposta[x].childNodes[1].childNodes[0].nodeValue + " ";
				var cnm = risposta[x].childNodes[2].childNodes[0].nodeValue ;
				var row = document.createElement("tr");
				var td = document.createElement("td"); 
				td.appendChild(document.createTextNode(nm));
				td.setAttribute("class","names");
				td.appendChild(document.createTextNode(cnm));
				row.appendChild(td);
				prec_risultati.appendChild(row);
		}
	}
	else
		no_suggest();
}

function delete_suggest(){
	document.getElementById("risultati").innerHTML = "";
}

function init_suggest(){
	document.getElementById("risultati").innerHTML = "Scrivi un nome di un attore famoso";
}

function no_suggest(){
	document.getElementById("risultati").innerHTML = "Nessun attore trovato";
}