$(document).ready(function() {
	var bodyId="diStaticsBody";
	var queryUrl="/staticsController.do?method=ajaxStaticsDeviceItem";
	
	var dgid="deviceItemStDg";
	//项目编号 
	ProjectUtil.BindProjectList(bodyId,"projectid",{},function(data){
		var arr = [{'projectId':'-1','projectName':'全部',selected:true}];
		$('#projectid').combobox('loadData',arr.concat(data.bizData));
        $("#projectid").combobox("setValue",-1);
	});
	
	//设备分类
	var arr = [{'id':'-1','text':'全部',selected:true}];
    $('#deviceTypeClassType').combobox('loadData',arr.concat(BasePage.DeviceType));
    $("#deviceTypeClassType").combobox("setValue",-1);
     
    searchDeviceItemSt();
	function searchDeviceItemSt(){
		var para = {};
		var projectid=$('#projectid').combobox("getValue");
		var deviceTypeClassType= $('#deviceTypeClassType').combobox("getValue");
		var deviceTypeName=$.trim($("#deviceTypeName").textbox("getValue"));
		 if(projectid==""){
			 projectid=-1;
		 } 
		 if(deviceTypeClassType==""){
			 deviceTypeClassType=-1;
		 } 
		para["projectId"]=projectid;
		para["deviceTypeClassType"]=deviceTypeClassType;
		if(deviceTypeName!=""){
			para["deviceTypeName"]=deviceTypeName;
		}
		
		BasePage.sendPostRequest(bodyId,queryUrl,para,function(data){	
			if(data== null){
				BasePage.showInfoMessage(BasePage.noSearchData);
			}else{
				$("#"+dgid).datagrid('loadData', data.bizData);
			}
		});
	};
	$('#doSearch').click(function(){
		searchDeviceItemSt();
	});
	$("#exportMorder").click(function(){
		excelExport();
	});
	function excelExport(){
		var exprotUrl="/staticsController.do?method=ajaxExcelExport";
		exprotUrl+="&exportMethod=exportDviceItem";
		var projectid=$('#projectid').combobox("getValue");
		var deviceTypeClassType= $('#deviceTypeClassType').combobox("getValue");
		var deviceTypeName=$.trim($("#deviceTypeName").textbox("getValue"));
		 if(projectid==""){
			 projectid=-1;
		 } 
		 if(deviceTypeClassType==""){
			 deviceTypeClassType=-1;
		 } 
		exprotUrl+="&projectId="+projectid+"&deviceTypeClassType="+deviceTypeClassType;
		if(deviceTypeName!=""){
			exprotUrl+="&deviceTypeName="+deviceTypeName;
		}
		BasePage.FormSubmit("exportForm",exprotUrl,null,null,'form');
	};
});