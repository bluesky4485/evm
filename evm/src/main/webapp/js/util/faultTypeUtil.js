var FaultTypeUtil={}||FaultTypeUtil;
(function(){
	FaultTypeUtil.BindFaultTypeComoboxControl=function(formId,ctrId,callback){
		var faultTypeArr = [];
		BasePage.sendPostRequest(formId,'/faultTypeController.do?method=ajaxFindFaultType',{},function(data){
			$('#'+ctrId).combobox('loadData',faultTypeArr.concat(data.bizData));
			if(data.bizData.length>0){
				$('#'+ctrId).combobox('setValue',data.bizData[0].faultTypeId);
			}
			if(callback!=undefined){
				callback(data);
			}
		});
	};
	FaultTypeUtil.submitValidate=function(text){
		var reg =/^[\u4E00-\u9FA5A-Za-z0-9]+$/;
        var value =$.trim(text.val());
        if(!value.match(reg)){
        	 BasePage.showInfoMessage("请您输入中文，数字，英文！");
        	return false;	
        }else{
        	return true;
        }       
	}
})();