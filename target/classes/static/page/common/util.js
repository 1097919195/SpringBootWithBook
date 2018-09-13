var Utils = (function($) {
	function Utils() {
		this.loginUrl = "page/login.html";
	}
     //获取路径
	Utils.prototype.getPath = function() {
		var loca = window.document.location;
	    var path = loca.protocol + "//" + loca.host + "/";	
	    return path;
		}
	

	/**
	 * 取得当前登陆的用户
	 * 
	 * @returns
	 */
	Utils.prototype.getMgmtUser = function() {
		var mgmtUser = sessionStorage.getItem("mgmtUser");
		if (mgmtUser && mgmtUser != "undefined") {
			return JSON.parse(mgmtUser);
		}
		return null;
	}

	/**
	 * 检查当前是否已登陆
	 * 
	 * @returns
	 */
	Utils.prototype.checkLogin = function() {
		var mgmtUser = this.getMgmtUser();
		if (mgmtUser == null) {
			alert("您还没有登陆，请登陆！");
			window.location.href =this.getPath() +"page/login.html";
		}
		return mgmtUser;
	}


	// ==================================ajax请求=========================================
	/**
	 * 取得登录token
	 */
	Utils.prototype.getHeaders = function() {
		var mgmtUser = this.getMgmtUser();
		var headers = {
		 userToken : mgmtUser == null ? null : mgmtUser.loginToken
		};
	return headers;
	}
	
	/**
	 * 解析后台返回结果，如果code=1,表示请求失败
	 * 
	 * @param reqResult
	 * @returns {Boolean}
	 */
	Utils.prototype.parseOptResult = function(result) {
		var  tokenState= result.tokenState;
		if (tokenState != null) {
			if (tokenState == 1) {// 重新登陆
				alert("登陆超时，请重新登陆！");
				window.location.href = this.getPath() + "page/login.html";
				return false;
			}
		}
		return true;
	}
	
	return new Utils();
})(jQuery);

