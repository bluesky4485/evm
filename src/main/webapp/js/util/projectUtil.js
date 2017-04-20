var ProjectUtil={}||ProjectUtil;
(function(){
	ProjectUtil.BindProjectList=function(formId,ctrId,para,callback){
		var projectArr = [];
		BasePage.sendPostRequest(formId,'/projectController.do?method=ajaxFindProjectIDNO',para,function(data){	
			$('#'+ctrId).combobox('loadData',projectArr.concat(data.bizData));
			callback(data);
		});
	};
})();