$(document).ready(function(){
	var bodyId="addDeviceItemBody";
	var dg = $("#deviceItemPropertyTable").datagrid({
	        data: []
	    });
		dg.datagrid('enableCellEditing').datagrid('gotoCell', {
	        index: 0,
	        field: 'deviceItemPropertyValue'
	    });
	$("#orderNo").searchbox("setValue","");
	$("#orderid").val("");
	$('#para_order_no').searchbox({
	    searcher:function(value,name){
	    	$("#order_window").window("open");
	    	searchOrder();
	    },
	    prompt:'订单编号'
	});
	$('#orderNo').searchbox({
	    searcher:function(value,name){
	    	searchOrder();
	    },
	    prompt:'订单编号'
	});
	
	function searchOrder(){
		var para = {};
		var options = $('#orderDg').datagrid('getPager').data("pagination").options;  
		var pageNumber = options.pageNumber;
		var pageSize =options.pageSize;
		para["pageNum"] = pageNumber;		
		para["pageSize"] = pageSize;
		para["orderNo"] = $.trim($("#orderNo").searchbox("getValue"));
		BasePage.sendPostRequest('order_window','/orderController.do?method=ajaxFindOrderPage',para,function(data){	
			if(data== null || data.bizData.total ==0){
				BasePage.showInfoMessage(BasePage.noSearchData);
			}else{
				$("#orderDg").datagrid('loadData', data.bizData);
			}
		});
	}
	var orderPaging = $('#orderDg').datagrid('getPager');
    if (orderPaging) {
    	BasePage.pagerServerFilter(orderPaging,searchOrder);
    }
    //订单-ok
    $("#order_window_OK").click(function() {
    	var updObj = $("#orderDg").datagrid('getSelected');
		if(updObj!=null){
			$('#para_order_no').searchbox("setValue",updObj["orderNo"]);
			$("#orderid").val(updObj["orderId"]);
		}else{
			BasePage.showInfoMessage("请选择订单");
		}
		$("#order_window").dialog("close");
	});
    //订单-cancel
    $("#order_window_cancel").click(function() {
		$("#order_window").dialog("close");
	});
    //-------------------deviceType-----------------------------------
    $("#para_deviceType").searchbox("setValue","");
    $("#deviceTypeId").val("");
    
    $('#para_deviceType').searchbox({
	    searcher:function(value,name){
	    	$("#deviceType_window").window("open");
	    	$("#deviceTable").datagrid('loadData', []);
	    	searchDevice();
	    },
	    prompt:'设备类型'
	});
	$('#deviceType').searchbox({
	    searcher:function(value,name){
	    	searchDevice();
	    },
	    prompt:'设备类型'
	});
	//查询
	function searchDevice(){
		var para = {};
		var options = $('#deviceTable').datagrid('getPager').data("pagination").options;  
		var pageNumber = options.pageNumber;
		var pageSize =options.pageSize;
		para["pageNum"] = pageNumber;		
		para["pageSize"] = pageSize;
		para["deviceName"] =  $.trim($("#deviceType").searchbox("getValue"));
		BasePage.sendPostRequest('deviceTable','/deviceController.do?method=ajaxFindDevicePage',para,function(data){
			
			if(data== null || data.bizData.total ==0){
				BasePage.showInfoMessage(BasePage.noSearchData);
			}else{
				$("#deviceTable").datagrid('loadData', data.bizData);
			}
		});
	};
	var deviceTablePaging = $('#deviceTable').datagrid('getPager');
    if (deviceTablePaging) {
    	BasePage.pagerServerFilter(deviceTablePaging,searchDevice);
    }
  //订单-O
    $("#deviceType_window_OK").click(function() {
    	var updObj = $("#deviceTable").datagrid('getSelected');
		if(updObj!=null){
			$('#para_deviceType').searchbox("setValue",updObj["deviceName"]);
			$("#deviceTypeId").val(updObj["deviceId"]);
			var para={};
			para["deviceId"]=updObj["deviceId"];
			BasePage.sendPostRequest(bodyId,'/deviceController.do?method=ajaxGetDeviceById',para,function(data){	
				if(data!= null){
					var updObj=data.bizData;
					var arr=[];
					var property=updObj.properties
					for(var i=0;i<property.length;i++){
						var item={};
						item["devcieTypePropertyId"]=property[i]["propertyId"];
						item["deviceTypePropertyName"]=property[i]["propertyName"];
						item["deviceItemPropertyValue"]="";
						arr.push(item);
					}
			    	$('#deviceItemPropertyTable').datagrid('loadData',arr);
				}else{
					BasePage.showInfoMessage("获得更新数据异常");
				}
			});
		}else{
			BasePage.showInfoMessage("请选择设备");
		}
		$("#deviceType_window").dialog("close");
	});
  //订单-cancel
    $("#deviceType_window_cancel").click(function() {
		$("#deviceType_window").dialog("close");
	});
    //---------------------------------------------------------------
    $("#saveBtn").click(function(){
      var  orderid=	$("#orderid").val();
      if(orderid.length==0){
    	  BasePage.showInfoMessage("请选择订单！");
    	  return ;
      }
      var  deviceTypeId=$("#deviceTypeId").val();
      if(deviceTypeId.length==0){
    	  BasePage.showInfoMessage("请选择设备类型！");
    	  return ;
      }
      var deviceItemUid=$.trim($("#para_deviceItemUid").textbox("getValue"));
      if(deviceItemUid.length!=0&&deviceItemUid.length>100){
    	  BasePage.showInfoMessage("设备标识长度不合法！");
    	  return ;
      }
      var  rows=$('#deviceItemPropertyTable').datagrid('getRows');
      var para={};
      para["orderId"]=orderid;
      para["deviceTypeId"]=deviceTypeId;
      if(deviceItemUid!=""){
    	  para["deviceItemUid"]=deviceItemUid;
      }
      para["deviceItemName"]=$('#para_deviceType').searchbox("getValue");
      para["deviceItemPropertyStr"]=JSON.stringify(rows);;
	  	BasePage.sendPostRequest(bodyId,'/deviceItemController.do?method=ajaxInsertDeviceItem',para,function(data){
	  		if(data.message=="error"){
				BasePage.showInfoMessage(data.message);
			}else{
				alert("保存成功！");
				$("#retrnManageForm").submit();
			}
	  	});
    });
    $("#goHist").click(function(){
		$("#retrnManageForm").submit();
     });
});