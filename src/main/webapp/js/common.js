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
	xmlhttp.setRequestHeader("Authorization", token);
	xmlhttp.send(null);
	return xmlhttp;
}

function syncronJsonPostRequest(url, data, token){
	var xmlhttp = createHttpRequest();
	xmlhttp.open("POST", url, false);
	xmlhttp.setRequestHeader("Authorization", token);
	xmlhttp.send(data);
	return xmlhttp;
}

function asyncronJsonGetRequest(xmlHttp, url, token, callback){
	document.getElementById('loadingScreen').style.display = 'inline-block';

	xmlHttp.open("GET", url, true);
	xmlHttp.setRequestHeader("Authorization", token);
	xmlHttp.onreadystatechange = callback;
	xmlHttp.send(null);
	return xmlHttp;
}

function asyncronJsonPostRequest(xmlHttp, url, token, data, callback){
	document.getElementById('loadingScreen').style.display = 'inline-block';
	
	xmlHttp.open("POST", url, true);
	xmlHttp.setRequestHeader("Authorization", token);
	xmlHttp.onreadystatechange = callback;
	xmlHttp.send(JSON.stringify(data));
	return xmlHttp;
}

function guidGenerator() {
    var S4 = function() {
       return (((1+Math.random())*0x10000)|0).toString(16).substring(1);
    };
    return (S4()+S4()+"-"+S4()+"-"+S4()+"-"+S4()+"-"+S4()+S4()+S4());
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