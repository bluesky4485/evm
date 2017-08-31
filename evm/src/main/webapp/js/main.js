$(document).ready(function() {
	          $("#center_iframe").width(document.body.offsetWidth-222);
			var isIE = navigator.userAgent.toUpperCase().indexOf("MSIE") != -1 ? true : false;
			var isFirefox = navigator.userAgent.toUpperCase().indexOf("Firefox") != -1 ? true : false;
			var isChrome = window.navigator.userAgent.indexOf("Chrome") != -1 ? true : false;
			// 定义iframe的ID
			var iframeids = [ "center_iframe" ];// "qm_map_trackPlay",s
			function dyniframesize() {
				var iframehide = "yes";
				if (document.getElementById) {
					var frameDiv=document.getElementById("center_iframe");
					if(frameDiv){
						frameDiv.style.display = "block";
						frameDiv.height = document.body.offsetHeight - 10;// 110
					}
					// 根据设定的参数来处理不支持iframe的浏览器的显示问题
					if ((document.all || document.getElementById) && iframehide == "no") {
						frameDiv.style.display = "block";
					}
				}
			};

			if (window.addEventListener) {
				window.addEventListener("load", dyniframesize, false);
			} else if (window.attachEvent) {
				window.attachEvent("onload", dyniframesize);
			} else {
				window.onload = dyniframesize;
			}
			 
			//window.onerror = killerrors;
//			window.onresize = function(){
//					BasePage.confirmMessage("您的操作导致页面刷新，系统需要回到首页，是否继续?",function(r){
//				    if (r){
//				    	location.reload(true);
//				    }else{
//				    	return;
//				    }
//				});
//				
//			}
});
