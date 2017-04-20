$(document).ready(function() {
	var bodyId="projectStaticsBody";
	var queryUrl="/staticsController.do?method=ajaxStaticsProject";
	
	searchProjectSt();
	function searchProjectSt(){
		var para = {};
		var name=$('#projectSearch').searchbox("getName");
		var value=$.trim($('#projectSearch').searchbox("getValue"));
		if(value!=""){
			para["condName"]=name;
			para["condValue"]=value;
		}
		BasePage.sendPostRequest(bodyId,queryUrl,para,function(data){	
			if(data== null || data.bizData.total ==0){
				BasePage.showInfoMessage(BasePage.noSearchData);
			}else{
				$("#projectStDg").datagrid('loadData', data.bizData);
			}
		});
	};
	//搜索出库联系人
	$('#projectSearch').searchbox({
	    searcher:function(value,name){
	    	searchProjectSt();
	    } 
	});
	$("#exportProject").click(function(){
		excelExport();
	});
	function excelExport(){
		var exprotUrl="/staticsController.do?method=ajaxExcelExport";
		var name=$('#projectSearch').searchbox("getName");
		var value=$.trim($('#projectSearch').searchbox("getValue"));
		
		exprotUrl+="&exportMethod=exportProject";
		if(name!=""){
			exprotUrl+="&condName="+name;
		}
		if(value!=""){
			exprotUrl+="&condValue="+value;
		}
		BasePage.FormSubmit("exportForm",exprotUrl,null,null,'form');
	};
});