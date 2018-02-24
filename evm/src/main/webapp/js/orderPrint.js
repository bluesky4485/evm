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
    //施工类型
    $('#workType').combobox('loadData',arr.concat(BasePage.WorkType));
    $("#workType").combobox("setValue",-1);
    searchOrderSt();
	function searchOrderSt(){
		var para = {};
		var projectid=$('#projectid').combobox("getValue");
		var workStatus= $('#workStatus').combobox("getValue");
		var workType=$('#workType').combobox("getValue");
		var orderNo=$("#oNo").textbox("getValue");
		var convergeBoxNo=$("#convergeBoxNo").textbox("getValue");
		 if(projectid==""){
			 projectid=-1;
		 } 
		 if(workStatus==""){
			 workStatus=-1;
		 } 
		 if(workType==""){
			 workType=-1;
		 }
		para["projectId"]=projectid;
		para["workStatus"]=workStatus;
		para["workType"]=workType;
		para["orderNo"]=orderNo;
		para["convergeBoxNo"]=convergeBoxNo;
		BasePage.sendPostRequest(bodyId,queryUrl,para,function(data){	
			if(data== null){
				BasePage.showInfoMessage(BasePage.noSearchData);
			}else{
				$("#"+dgid).datagrid('loadData', data.bizData);
				//createEVMImg();
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
		var workType=$('#workType').combobox("getValue");
		var orderNo=$("#oNo").textbox("getValue");
		var convergeBoxNo=$("#convergeBoxNo").textbox("getValue");
		 if(projectid==""){
			 projectid=-1;
		 } 
		 if(workStatus==""){
			 workStatus=-1;
		 } 
		exprotUrl+="&projectId="+projectid+"&workStatus="+workStatus+"&workType"+workType;
		if(convergeBoxNo!=""){
			exprotUrl+="&convergeBoxNo"+convergeBoxNo;
		}
		if(orderNo!=""){
			exprotUrl+="&orderNo"+orderNo;
		}
		BasePage.FormSubmit("exportForm",exprotUrl,null,null,'form');
	};
	function createEVMImg(){
		$("#batch_print_hidden").empty();
		var data =$("#orderStDg").datagrid("getRows");
		for(var i=0;i<data.length;i++){
		   var orderNo=data[i].orderNo;
		   var convergeBoxNo=data[i].convergeBoxNo;
		   if(convergeBoxNo==undefined){
			   convergeBoxNo="";
		   }
		   var content="<div style='width:70mm;height:50mm;' id='div_"+orderNo+"'>";
			   content+="<div  id='"+orderNo+"'></div>";
			   content+="</div>";
		   $("#batch_print_hidden").append(content);
//		   createEvm(orderNo,orderNo,120,120);
		   var qrcode = $('#'+orderNo).qrcode({  
			    text: orderNo,  
			    width: 150,  
			    height: 150  
			}).hide(); 
		   var canvas = $("#"+orderNo+" canvas");
		   var img = canvas[0].toDataURL("image/png");
		   $('#div_'+orderNo).html("<img style='width:150px;height:150px' src='" + img + "'/><div ><span>订单编号:"+orderNo+"</span></div><div ><span>箱号:"+convergeBoxNo+"</span></div>");
	   }
	}
	//打印
	$("#printOrderBtn").click(function(){
		createEVMImg();
		$("#batch_print_hidden").jqprint({debug:false,operaSupport:true});
     });
});