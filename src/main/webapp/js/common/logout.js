$(document).ready(function() {
	//退出
	$("#sysLogoutBtn").click(function signOut() {
		window.parent.signout();
	});
	//未维修订单
	var mOrderSturl="/pageDatastController.do?method=ajaxMOrderST";
	BasePage.sendPostRequest("newMorder",mOrderSturl,{},function(data){			
		$('#newMorder').text(data.bizData.allNoDoMorderCnt);			
	});
});