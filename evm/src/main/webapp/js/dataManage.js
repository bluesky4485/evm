$(document).ready(function() {
	init();
	function init(){
		ajaxListBakFile();
	};
	function ajaxListBakFile(){
		var para={};
		BasePage.sendPostRequest('dbManageBody','/databaseManagerController.do?method=ajaxListBakFile',para,function(data){	
			 $("#fileDg").datagrid("loadData",data.bizData);
		});
	}
	$("#bakBtn").click(function(){
		var para={};
		BasePage.sendPostRequest('dbManageBody','/databaseManagerController.do?method=ajaxDoMysqldump',para,function(data){	
			alert(data.message);
			ajaxListBakFile();
		});
	});
	$("#revertBtn").click(function(){
		var para={};
		var row = $("#fileDg").datagrid('getSelected');
		if(row==null){
			BasePage.showInfoMessage("请选择还原的文件!");
			return;
		}
		para["fileName"]=row["fileName"];
		BasePage.sendPostRequest('dbManageBody','/databaseManagerController.do?method=ajaxDoMysqlRestore',para,function(data){	
			alert(data.message);
		});
	});
});
var dataManage=dataManage||{};
(function(){
	dataManage.formatImage=function(value,row){
		
		var path =$("#context").val()+"\\dbbaks\\"+row["fileName"];
		var imgStr= "<a class='singleView' ref='group' title='" + row.fileName +"' href='"+path+"'>";
		    imgStr=imgStr+"<img src='../images/down.png' width='18' height='18' alt='' /></a>";
//		 imgStr=imgStr+row["fileName"]+"</a>";
	     return imgStr;
	};
})();
 