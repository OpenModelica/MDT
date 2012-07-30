function foo(){
	var obj="string";
	return obj;
}
function expandAll(){
	var allTags=document.getElementsByTagName('div');
	for(var i=0; i<allTags.length;i++){
		allTags[i].style.display = 'block';
	}
	var allImgs=document.getElementsByTagName('img');
	for(var i=0; i<allImgs.length;i++){
		if(allImgs[i].src=="img/plus.png"){
  			document.write(allImgs[i].alt);
			allImgs[i].src="img/minus.png";
		}
	}
}
function collapseAll(){
	var allTags=document.getElementsByTagName('div');
	for(var i=0; i<allTags.length;i++){
		allTags[i].style.display = 'none';
	}
	var allImgs=document.getElementsByTagName('img');
	for(var i=0; i<allImgs.length;i++){
		if(allImgs[i].src=="img/minus.png")
			allImgs[i].src="img/plus.png";
	}
}

function clip(id) {
	if (document.getElementById("div_" + id).style.display == 'none') {
		//document.getElementById("img_" + id).src = "img/minus.png";
		//document.getElementById("img_" + id).alt = "-";
		document.getElementById("div_" + id).style.display = "block";
	} else {
		//document.getElementById("img_" + id).src = "img/plus.png";
		//document.getElementById("img_" + id).alt = "+";
		document.getElementById("div_" + id).style.display = "none";
	}
}