$(document).ready(function() {
	var funcDg="funcDg";
	var bodyId="addRoleBody";
	var updUrl="/roleController.do?method=ajaxUpdateRole";
	var getByIdUrl="/roleController.do?method=ajaxFindRoleById";
	var funcQueryUrl="/funcController.do?method=ajaxFindFuncPage";
	$("#saveRoleBtn").click(function(){
		var roleRemark=$.trim($("#roleRemark").textbox("getValue"));
		if(!submitValidate()){
			return;
		}
		var para={};
		para["roleId"]=$("#roleId").val();
		para["updDate"]=$("#hidden_updDate").val();
		para["roleRemark"]=roleRemark;
		var rows=$("#"+funcDg).datagrid("getSelections");
		para["funcStr"]=JSON.stringify(rows);
		BasePage.sendPostRequest(bodyId,updUrl,para,function(data){	
			  if(data.bizData>0){
					alert("保存成功！");
					$("#returnManageForm").submit();
				}else{
					BasePage.showInfoMessage(data.message);
				}
		});
	});
	 $("#goHist").click(function(){
			$("#returnManageForm").submit();
	     });
	function submitValidate(){
		var roleRemark=$.trim($("#roleRemark").textbox("getValue"));
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
			$("#"+funcDg).datagrid('loadData', data.bizData);
			searchRoleByID();
		});
	}
	function searchRoleByID(){
		var roleId=$("#roleId").val();
		var para={};
		para["roleId"]=roleId;
		BasePage.sendPostRequest(bodyId,getByIdUrl,para,function(data){	
			if(data== null || data.bizData!=null){
				init_callback(data.bizData);
			}else{
				BasePage.showInfoMessage("获得更新数据异常");
			}
		});
	}
	function init_callback(updObj){
		$("#roleName").textbox("setValue",updObj["roleName"]);
		$("#roleRemark").textbox("setValue",updObj["roleRemark"]);
		$("#hidden_updDate").val(updObj["updDate"]);
		var funcList=updObj.funcList;
		var tableRows=$("#"+funcDg).datagrid("getRows");
        for(var i=0;i<funcList.length;i++){
        	for(var j=0;j<tableRows.length;j++){
        		if(funcList[i].funcId==tableRows[j].funcId){
        			var rowIndex=$("#"+funcDg).datagrid("getRowIndex",tableRows[j]); 
        			$("#"+funcDg).datagrid("selectRow",rowIndex);
        		}
        	}
        }
	}
});