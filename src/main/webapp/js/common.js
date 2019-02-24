function createHttpRequest()
{
	var xmlhttp = null;
	if (window.XMLHttpRequest) {
		xmlhttp=new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	} else {
		alert("Your browser does not support XMLHTTP!");
	}
	return xmlhttp;
}

function syncronJsonGetRequest(url, token){
	var xmlhttp = createHttpRequest();
	xmlhttp.open("GET", url, false);
	xmlhttp.setRequestHeader("Authorization", token)
	xmlhttp.send(null);
	return xmlhttp;
}

function syncronJsonPostRequest(url, data){
	var xmlhttp = createHttpRequest();
	xmlhttp.open("POST", url, false);
	//xmlhttp.setRequestHeader('Content-type','application/json')
	xmlhttp.send(data);
	return xmlhttp;
}

function asyncronJsonGetRequest(url, callback){
    var xmlhttp = createHttpRequest();
    xmlhttp.open("GET", url, true);
	xmlhttp.onreadystatechange = callback;
	xmlhttp.send(null);
	return xmlhttp;
}

function asyncronJsonGetRequest(url, callback, data){
    var xmlhttp = createHttpRequest();
    xmlhttp.open("POST", url, true);
	xmlhttp.onreadystatechange = callback;
	xmlhttp.send(data);
	return xmlhttp;
}

/*  Syncron GET example:
    var URL="/admin/getSubLocationByLocType";
	URL+="?deck="+deck;
    var json=JSON.parse(syncronJsonGetRequest(URL).response);
*/

/*  Asyncron GET example:
    
	var URL="/admin/setAppForLocation";
	URL+="?poiid="+poiid+"&poitype="+poitype+"&operation="+operation+"&value="+value+"&label="+label+"&appname="+appName;
	var json = asyncronJsonGetRequest(URL,function(){
		if(xmlHttp == null ||xmlHttp.readyState!=4 || xmlHttp.status != 200){
			return;
		}
		var json = JSON.parse(xmlHttp.responseText);
		if(operation === "add"){
			
			showActiveLocationRules(appName);
			
		}
	});
*/

/*  Syncron POST example:

	var URL="/jsonservice?operation=addtype";
	var data = {"typeName" : typeName.trim(), "typeURI" : typeURI.trim()};	
	var json = JSON.parse(syncronJsonPostRequest(URL, JSON.stringify(data)).response);

*/