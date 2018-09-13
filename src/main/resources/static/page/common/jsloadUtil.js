function loadJsAndCss(container,files) {
	var jsVer = localStorage.getItem("jsVer");
	var head
	for (var i = 0; i < files.length; i++) {
		var path = files[i];
		var extendName = path.substring(path.length - 3).toLowerCase();
		if (path.indexOf("?") > 0) {
			path = path + "&v=" + jsVer;
		} else {
			path = path + "?v=" + jsVer;
		}

		if (extendName == ".js") {
			$(container).append("<script type='text/javascript' src='" + path + "'><\/script>");
		} else if (extendName == "css") {
			$(container).append("<link type='text/css' rel='stylesheet' href='" + path + "' />");
		} else {
			console.log("不支持的文件类型：" + path);
		}
	}
}