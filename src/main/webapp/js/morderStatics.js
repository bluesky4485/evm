$(document).ready(function() {
	var bodyId="morderStaticsBody";
	var queryUrl="/staticsController.do?method=ajaxStaticsMOrder";
	
	var dgid="orderStDg";
	//项目编号 
	ProjectUtil.BindProjectList(bodyId,"projectid",{},function(data){
		var arr = [{'projectId':'-1','projectName':'全部',selected:true}];
		$('#projectid').combobox('loadData',arr.concat(data.bizData));
        $("#projectid").combobox("setValue",-1);
	});
	//维修状态
	var arr = [{'id':'-1','text':'全部',selected:true}];
	$('#maintainStatus').combobox('loadData',arr.concat(BasePage.MaintainStatus));
    $("#maintainStatus").combobox("setValue",-1);
	//故障类别
	FaultTypeUtil.BindFaultTypeComoboxControl(bodyId,"faultType",function(data){
		var arr = [{'faultTypeId':'-1','faultTypeName':'全部',selected:true}];
		$('#faultType').combobox('loadData',arr.concat(data.bizData));
        $("#faultType").combobox("setValue",-1);
	});
	searchMorderSt();
	function searchMorderSt(){
		var para = {};
		var projectid=$('#projectid').combobox("getValue");
		var maintainStatus= $('#maintainStatus').combobox("getValue");
		var faultType=$('#faultType').combobox("getValue");
		 if(projectid==""){
			 projectid=-1;
		 } 
		 if(faultType==""){
			 faultType=-1;
		 } 
		 if(maintainStatus==""){
			 maintainStatus=-1;
		 } 
		para["projectId"]=projectid;
		para["maintainStatus"]=maintainStatus;
		para["faultType"]=faultType;
		BasePage.sendPostRequest(bodyId,queryUrl,para,function(data){	
			if(data== null){
				BasePage.showInfoMessage(BasePage.noSearchData);
			}else{
				$("#"+dgid).datagrid('loadData', data.bizData);
			}
		});
	};
	$('#doSearch').click(function(){
		searchMorderSt();
	});
	$("#exportMorder").click(function(){
		excelExport();
	});
	function excelExport(){
		var exprotUrl="/staticsController.do?method=ajaxExcelExport";
		exprotUrl+="&exportMethod=exportMorder";
		var projectid=$('#projectid').combobox("getValue");
		var maintainStatus= $('#maintainStatus').combobox("getValue");
		var faultType=$('#faultType').combobox("getValue");
		 if(projectid==""){
			 projectid=-1;
		 } 
		 if(faultType==""){
			 faultType=-1;
		 } 
		 if(maintainStatus==""){
			 maintainStatus=-1;
		 } 
		exprotUrl+="&projectId="+projectid+"&maintainStatus="+maintainStatus+"&faultType="+faultType;
		BasePage.FormSubmit("exportForm",exprotUrl,null,null,'form');
	};
});