
function expandAll(){
	var allTags=document.getElementsByTagName('div');
	for(var i=0; i<allTags.length;i++){
		allTags[i].style.display = 'block';
	}
	var allImgs=document.getElementsByTagName('img');
	for(var i=0; i<allImgs.length;i++){
		if(allImgs[i].src=="reportData/expand.gif"){
  			document.write(allImgs[i].alt);
			allImgs[i].src="reportData/collapse.gif";
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
		if(allImgs[i].src=="reportData/collapse.gif")
			allImgs[i].src="reportData/expand.gif";
	}
}

function clip(id) {
	if (document.getElementById("div_" + id).style.display == 'none') {
		document.getElementById("img_" + id).src = "reportData/collapse.gif";
		document.getElementById("img_" + id).alt = "-";
		document.getElementById("div_" + id).style.display = "block";
	} 
	else if (document.getElementById("div_" + id).style.display == 'block') {
		document.getElementById("img_" + id).src = "reportData/expand.gif";
		document.getElementById("img_" + id).alt = "+";
		document.getElementById("div_" + id).style.display = "none";
	}
	else {
		document.getElementById("img_" + id).src = "reportData/collapse.gif";
		document.getElementById("img_" + id).alt = "+";
		document.getElementById("div_" + id).style.display = "block";
	}
}