var OrderUtil={}||OrderUtil;
(function(){
	//
	OrderUtil.BindOrderIdComoboxControl=function(formId,ctrId,callback){
		var orderArr = [];
		BasePage.sendPostRequest(formId,'/orderController.do?method=ajaxFindOrderIdNo',{},function(data){
			$('#'+ctrId).combobox('loadData',orderArr.concat(data.bizData));
			 
			callback;
		});
	};
	OrderUtil.submitValidate=function(){
		//施工地点
		var workAddress =$("#workAddress").textbox("getValue");
		 if(workAddress!=""&&workAddress.length>100){
			  BasePage.showInfoMessage("您输入的施工地点信息超长！");
			  return false;
		  }
		//施工单位
		var workCompany=$("#workCompany").textbox("getValue");
		 if(workCompany!=""&&workCompany.length>100){
			  BasePage.showInfoMessage("您输入的施工单位信息超长！");
			  return false;
		  }
		//单位资质情况
		var workCompanyQualified =$("#workCompanyQualified").textbox("getValue");
        if(workCompanyQualified!=""&&workCompanyQualified.length>100){
			  BasePage.showInfoMessage("您输入的单位资质情况信息超长！");
			  return false;
		  }
		//用户评价
		var userAppraise =$("#userAppraise").textbox("getValue");
		  if(userAppraise!=""&&userAppraise.length>200){
			  BasePage.showInfoMessage("您输入的用户评价情况信息超长！");
			  return false;
		  }
	  //备注
		  var workRemark=$("#workRemark").textbox("getValue");
		  if(workRemark.length>200){
			  BasePage.showInfoMessage("您输入的信息超长！");
			  return false;
		  }
		  return true;
	};
})();