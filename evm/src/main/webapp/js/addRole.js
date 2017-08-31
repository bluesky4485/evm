$(document).ready(function() {
	var funcDg="funcDg";
	var bodyId="addRoleBody";
	var saveUrl="/roleController.do?method=ajaxInsertRole";
	var funcQueryUrl="/funcController.do?method=ajaxFindFuncPage";
	$("#saveRoleBtn").click(function(){
		var roleName=$("#roleName").textbox("getValue");
		var roleRemark=$("#roleRemark").textbox("getValue");
		if(!submitValidate()){
			return;
		}
		var para={};
		para["roleName"]=roleName;
		para["roleRemark"]=roleRemark;
		var rows=$("#funcDg").datagrid("getSelections");
		para["funcStr"]=JSON.stringify(rows);
		BasePage.sendPostRequest(bodyId,saveUrl,para,function(data){
			if(data.messageType=="error"){
				BasePage.showInfoMessage(data.message);
				return;
			}else{
				alert("保存成功！");
				$("#returnManageForm").submit();
			}
		});
	});
	 $("#goHist").click(function(){
			$("#returnManageForm").submit();
		});
	function submitValidate(){
		var roleName=$("#roleName").textbox("getValue");
		var roleRemark=$("#roleRemark").textbox("getValue");
		if(roleName.length==0){
			BasePage.showInfoMessage("角色名称不可用为空!");
			return false;
		}
		if(!BasePage.ValidateEngChineseNum(roleName)){
			BasePage.showInfoMessage("角色名称请输入数字字母或中文!");
			return false;
		}
		if(roleRemark.length>0&&roleRemark.length>200){
			BasePage.showInfoMessage("角色备注信息超长!");
			return false;
		}
		var rows=$("#funcDg").datagrid("getSelections");
		if(rows.length<=0){
			BasePage.showInfoMessage("请选择权限!");
			return false;
		}
		return true;
	}
	init();
	function init(){
		searchFuncPage();
	}
	function searchFuncPage(){
		var para = {};
		BasePage.sendPostRequest(bodyId,funcQueryUrl,para,function(data){	
			if(data== null || data.bizData.total ==0){
				BasePage.showInfoMessage(BasePage.noSearchData);
			}else{
				$("#"+funcDg).datagrid('loadData', data.bizData);
			}
		});
	}
});