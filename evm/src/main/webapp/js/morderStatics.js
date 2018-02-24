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
    //验收状态	
    var _arr = [{'id':'-1','text':'全部',selected:true}];
    var _a=_arr.concat(BasePage.AcceptStatus);
    $('#acceptStatus').combobox('loadData',_a);
    $("#acceptStatus").combobox("setValue",-1);
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
		var maintainStartDate=$("#maintainStartDate").datebox("getValue");
		var maintainEndDate=$("#maintainEndDate").datebox("getValue");
		
		if((maintainStartDate!=""&&maintainEndDate!="")||(maintainStartDate==""&&maintainEndDate=="")){
			para["maintainStartDate"]=maintainStartDate;
			para["maintainEndDate"]=maintainEndDate;
		}else{
			alert("派修时间需要输入完整的时间范围！");
			return;
		}
		
		var completeStartDate=$("#completeStartDate").datebox("getValue");
		var completeEndDate=$("#completeEndDate").datebox("getValue");
		
		if((completeStartDate!=""&&completeEndDate!="")||(completeStartDate==""&&completeEndDate=="")){
			para["completeStartDate"]=completeStartDate;
			para["completeEndDate"]=completeEndDate;
		}else{
			alert("完成时间需要输入完整的时间范围！");
		}
		var acceptStatus=$("#acceptStatus").combobox("getValue");
		if(acceptStatus==""){
			acceptStatus=-1;
		} 
		para["acceptStatus"]=acceptStatus;
		var maintainPmName=$("#maintainPmName").textbox("getValue");
		para["maintainPmName"]=maintainPmName;
		var cusName=$("#cusName").textbox("getValue");
		para["cusName"]=cusName;
		var opt=$("#opt").combobox("getValue");
		para["optType"]=opt;
		var maintianDuration=$("#maintianDuration").numberbox("getValue");
		para["maintianDuration"]=maintianDuration;
		
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
		var acceptStatus=$("#acceptStatus").combobox("getValue");
		exprotUrl+="&acceptStatus="+acceptStatus;
		var maintainStartDate=$("#maintainStartDate").datebox("getValue");
		var maintainEndDate=$("#maintainEndDate").datebox("getValue");
		if((maintainStartDate!=""&&maintainEndDate!="")||(maintainStartDate==""&&maintainEndDate=="")){
			exprotUrl+="&maintainStartDate="+maintainStartDate;
			exprotUrl+="&maintainEndDate="+maintainEndDate;
		}else{
			alert("派修时间需要输入完整的时间范围！");
			return;
		}
		var maintainPmName=$("#maintainPmName").textbox("getValue");
		exprotUrl+="&maintainPmName="+maintainPmName;
		var cusName=$("#cusName").textbox("getValue");
		exprotUrl+="&cusName="+cusName;
		var opt=$("#opt").combobox("getValue");
		exprotUrl+="&optType="+opt;
		var maintianDuration=$("#maintianDuration").numberbox("getValue");
		exprotUrl+="&maintianDuration="+maintianDuration;
		BasePage.FormSubmit("exportForm",exprotUrl,null,null,'form');
	};
	//绑定操作符
	BasePage.BindOptTypeControl("opt");
	 
	
});