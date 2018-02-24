$(document).ready(function() {
	var bodyId="orderStaticsBody";
	var queryUrl="/staticsController.do?method=ajaxStaticsOrder";
	
	var dgid="orderStDg";
	//项目编号 
	ProjectUtil.BindProjectList(bodyId,"projectid",{},function(data){
		var arr = [{'projectId':'-1','projectName':'全部',selected:true}];
		$('#projectid').combobox('loadData',arr.concat(data.bizData));
        $("#projectid").combobox("setValue",-1);
	});
	//维修状态
	var arr = [{'id':'-1','text':'全部',selected:true}];
	$('#workStatus').combobox('loadData',arr.concat(BasePage.WorkStatus));
    $("#workStatus").combobox("setValue",-1);
 
    searchOrderSt();
	function searchOrderSt(){
		var para = {};
		var projectid=$('#projectid').combobox("getValue");
		var workStatus= $('#workStatus').combobox("getValue");
		 if(projectid==""){
			 projectid=-1;
		 } 
		 if(workStatus==""){
			 workStatus=-1;
		 } 
		para["projectId"]=projectid;
		para["workStatus"]=workStatus;
		BasePage.sendPostRequest(bodyId,queryUrl,para,function(data){	
			if(data== null){
				BasePage.showInfoMessage(BasePage.noSearchData);
			}else{
				$("#"+dgid).datagrid('loadData', data.bizData);
			}
		});
	};
	$('#doSearch').click(function(){
		searchOrderSt();
	});
	$("#exportOrder").click(function(){
		excelExport();
	});
	function excelExport(){
		var exprotUrl="/staticsController.do?method=ajaxExcelExport";
		exprotUrl+="&exportMethod=exportOrder";
		var projectid=$('#projectid').combobox("getValue");
		var workStatus= $('#workStatus').combobox("getValue");
		 if(projectid==""){
			 projectid=-1;
		 } 
		 if(workStatus==""){
			 workStatus=-1;
		 } 
		exprotUrl+="&projectId="+projectid+"&workStatus="+workStatus;
		BasePage.FormSubmit("exportForm",exprotUrl,null,null,'form');
	};
});