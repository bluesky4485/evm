$(document).ready(function() {
	    var bodyId="deviceManageBody";
	    //EditTable
		var dg = $("#deviceItemPropertyTable").datagrid({
	        data: []
	    });
		dg.datagrid('enableCellEditing').datagrid('gotoCell', {
	        index: 0,
	        field: 'deviceItemPropertyValue'
	    });
		 //EditTable
		var upddg = $("#deviceItemPropertyTableUpd").datagrid({
	        data: []
	    });
		upddg.datagrid('enableCellEditing').datagrid('gotoCell', {
	        index: 0,
	        field: 'deviceItemPropertyValue'
	    });
		//订单查询
		$("#doSearchOrderBtn").click(function(){
			searchOrder();
		});
		//设备查询
		$("#doSearchDeviceItemBtn").click(function(){
			searchDeviceItemByOrder();
		});
		$("#orderDg").datagrid({
	        onClickRow : function(index, row){ searchDeviceItemByOrder()}
         });
		$('#deviceTypeClassType').combobox('loadData',BasePage.AllDeviceType);
		//打开地图
		QmMap.init("mapContainer");
		$("#addMap").click(function(){
			$("#openType").val("addMap");
			$("#map_window").window("open");
			loadGeocoder();
		});
		function loadGeocoder(){
			QmMap.Map.clearMap();
			AMap.plugin('AMap.Geocoder',function(){
		        var geocoder = new AMap.Geocoder({
		            city: "0431"//城市，默认：“全国”
		        });
		        var openType=$("#openType").val();
	            if(openType=="addMap"){
	            	var marker = new AMap.Marker({
	    	            map:QmMap.Map,
	    	            bubble:true,
	    	            position:[125.3245,43.886841]
	    	        });
	            	QmMap.Map.panTo([125.3245,43.886841])
	            }else{
	            	var lat=$("#hidden_upd_lat").val();
		            var lng=$("#hidden_upd_lng").val();
		            if(lat==0||lat==undefined){
		            	lat=43.886841;
		            }
		            if(lng==0||lng==undefined){
		            	lng=125.3245;
		            }
	            	var marker = new AMap.Marker({
	    	            map:QmMap.Map,
	    	            bubble:true,
	    	            position:[lng,lat]
	    	        });
	            	QmMap.Map.panTo([lng,lat])
	            }
		        
		        QmMap.Map.on('click',function(e){
		            marker.setPosition(e.lnglat);
		            var openType=$("#openType").val();
		            if(openType=="addMap"){
		            	$("#hidden_add_lat").val(e.lnglat.lat);
			            $("#hidden_add_lng").val(e.lnglat.lng);
			            geocoder.getAddress(e.lnglat,function(status,result){
			              if(status=='complete'){
			            	  $("#add_deviceItemAddr").textbox("setValue", result.regeocode.formattedAddress);
			              }
			            });
		            }else{
		            	$("#hidden_upd_lat").val(e.lnglat.lat);
			            $("#hidden_upd_lng").val(e.lnglat.lng);
			            geocoder.getAddress(e.lnglat,function(status,result){
			              if(status=='complete'){
			            	  $("#upd_deviceItemAddr").textbox("setValue", result.regeocode.formattedAddress);
			              }
			            });
		            }
		            
		        })
		    });
		}
		
		$("#updMap").click(function(){
			$("#openType").val("updMap");
			$("#map_window").window("open");
			loadGeocoder();
		});
		function searchDeviceItemByOrder(){
			var para = {};
			var options = $('#orderDg').datagrid('getPager').data("pagination").options;  
			var pageNumber = options.pageNumber;
			var pageSize =options.pageSize;
//			para["pageNum"] = pageNumber;		
//			para["pageSize"] = pageSize;
			var row = $("#orderDg").datagrid('getSelected');
			if(row!=null){
				para["orderNo"] = row['orderNo'];
			}else{
				BasePage.showInfoMessage("尚未选择订单！");
				return;
			}
			var deviceTypeName=$("#deviceTypeName").textbox("getValue");
			if(deviceTypeName!=""){
				para["deviceTypeName"]=deviceTypeName;
			}
			var classType=$("#deviceTypeClassType").combobox("getValue");
			if(classType!=""){
				para["deviceTypeClassType"]=classType;
			}
			BasePage.sendPostRequest('deviceManageBody','/deviceItemController.do?method=ajaxFindDeviceItemPage',para,function(data){
				if(data.messageType=="error"){
					BasePage.showInfoMessage(data.message);
				}else{
					if(data== null || data.bizData.total ==0)
					{
						$("#deviceItemTable").datagrid('loadData', []);
					}else{
						$("#deviceItemTable").datagrid('loadData', data.bizData);
					}
				}
			});  
		}
		init();
		function init(){
			searchOrder();
		}
	    function searchOrder(){
			var para = {};
			var options = $('#orderDg').datagrid('getPager').data("pagination").options;  
			var pageNumber = options.pageNumber;
			var pageSize =options.pageSize;
			para["pageNum"] = pageNumber;		
			para["pageSize"] = pageSize;
			var pName=$.trim($("#pName").textbox("getValue"));
			if(pName!=""){
				para["projectName"] = pName;
			}
			var orderNo=$.trim($("#oNo").textbox("getValue"));
			if(orderNo!=""){
				para["orderNo"] = orderNo;
			}
			//过滤条件
			BasePage.sendPostRequest(bodyId,'/orderController.do?method=ajaxFindOrderPage',para,function(data){	
				if(data.messageType=="error"){
					BasePage.showInfoMessage(data.message);
					return;
				}else{
					if(data== null || data.bizData.total ==0){
						BasePage.showInfoMessage("未查询到订单信息！");
						$("#orderDg").datagrid('loadData', []);
					}else{
						$("#orderDg").datagrid('loadData', data.bizData);
					}
				}
			});
		}
		var orderPaging = $('#orderDg').datagrid('getPager');
	    if (orderPaging) {
	    	BasePage.pagerServerFilter(orderPaging,searchOrder);
	    }
	    //TODO:创建window
	    $("#add_window").window("close");
	    $("#add_window").window({onClose:function(){
			$("#hidden_add_orderId").val("");
			$("#hidden_add_deviceTypeId").val("");
			$("#add_orderNo").text("");
			$("#add_deviceType").searchbox("setValue","");
			$("#add_deviceItemUid").searchbox("setValue","");
			$("#deviceItemPropertyTable").datagrid('loadData', []);
	    }});
	 
	    //创建按钮
	    $("#addDeviceBtn").click(function(){
	    	var row = $("#orderDg").datagrid('getSelected');
			if(row!=null){
				$("#add_orderNo").text(row.orderNo);
				$("#hidden_add_orderId").val(row.orderId);
				
			}else{
				BasePage.showInfoMessage("选择订单信息!");
				return;
			}
	    	$("#add_window").window("open");
	    });
	    //TODO:取消按钮
	    $("#addItem_window_cancel").click(function() {
	    	 $("#add_window").window("close");
	    });
	    //TODO:保存新的设备
	    $("#addItem_window_OK").click(function(){
	        var  orderid=$("#hidden_add_orderId").val();
	        if(orderid.length==0){
	      	  BasePage.showInfoMessage("请选择订单！");
	      	  return ;
	        }
	        var  deviceTypeId=$("#hidden_add_deviceTypeId").val();
	        if(deviceTypeId.length==0){
	      	  BasePage.showInfoMessage("请选择设备类型！");
	      	  return ;
	        }
	        var deviceItemUid=$.trim($("#add_deviceItemUid").textbox("getValue"));
	        if(deviceItemUid.length!=0&&deviceItemUid.length>100){
	      	  BasePage.showInfoMessage("设备标识长度不合法！");
	      	  return ;
	        }
	        var deviceItemAddr=$.trim($("#add_deviceItemAddr").textbox("getValue"));
	        if(deviceItemAddr.length!=0&&deviceItemAddr.length>100){
	      	  BasePage.showInfoMessage("设备地址长度不合法！");
	      	  return ;
	        }
	        var lat=$("#hidden_add_lat").val();
            var lng=$("#hidden_add_lng").val();
	        var  rows=$('#deviceItemPropertyTable').datagrid('getRows');
	        var para={};
	        para["orderId"]=orderid;
	        para["deviceTypeId"]=deviceTypeId;
	        if(deviceItemUid!=""){
	      	  para["deviceItemUid"]=deviceItemUid;
	        }
	        if(deviceItemAddr!=""){
		      	  para["deviceItemAddr"]=deviceItemAddr;
		    }
	        para["lat"]=lat;
	        para["lng"]=lng;
//	        para["deviceItemName"]=$('#para_deviceType').searchbox("getValue");
	        para["deviceItemPropertyStr"]=JSON.stringify(rows);
	  	  	BasePage.sendPostRequest(bodyId,'/deviceItemController.do?method=ajaxInsertDeviceItem',para,function(data){
	  	  		if(data.messageType=="error"){
	  				BasePage.showInfoMessage(data.message);
	  			}else{
	  				alert("保存成功！");
	  				 $("#add_window").window("close");
	  				searchDeviceItemByOrder();
	  			}
	  	  	});
	      });
	    
	    //-------------------deviceType-----------------------------------
	    $("#add_deviceType").searchbox("setValue","");
	    $('#add_deviceType').searchbox({
		    searcher:function(value,name){
		    	$("#deviceType_window").window("open");
		    	$("#deviceTable").datagrid('loadData', []);
		    	searchDevice();
		    },
		    prompt:'设备类型'
		});
	    //设备搜索
		$('#para_deviceType').searchbox({
		    searcher:function(value,name){
		    	searchDevice();
		    },
		    prompt:'设备类型'
		});
		//查询
		function searchDevice(){
			var para = {};
			var deviceName=$.trim($("#para_deviceType").searchbox("getValue"));
			if(deviceName!=""){
				para["deviceName"] =deviceName;
			}
			para["orderId"]=$("#hidden_add_orderId").val();
			BasePage.sendPostRequest('deviceTable','/deviceController.do?method=ajaxFindDevicePage',para,function(data){
				if(data== null || data.bizData.total ==0){
					BasePage.showInfoMessage(BasePage.noSearchData);
				}else{
					$("#deviceTable").datagrid('loadData', data.bizData);
				}
			});
		};
	 
	  //设备类型查询=OK
	    $("#deviceType_window_OK").click(function() {
	    	var updObj = $("#deviceTable").datagrid('getSelected');
			if(updObj!=null){
				$('#add_deviceType').searchbox("setValue",updObj["deviceName"]);
				$("#hidden_add_deviceTypeId").val(updObj["deviceId"]);
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
	    //设备类型查询=cancel
	    $("#deviceType_window_cancel").click(function() {
			$("#deviceType_window").dialog("close");
		});
       //删除设备
		$("#delDeviceBtn").click(function(){
			var row = $("#deviceItemTable").datagrid('getSelected');
			if(row!=null){
				BasePage.confirmMessage("确认删除该设备？",function(flag){
					if(flag){
						var para={};
						para["deviceItemId"]=row["deviceItemId"];
						para["updDate"]=row["updDate"];
						BasePage.sendPostRequest(bodyId,'/deviceItemController.do?method=ajaxDeleteDeviceItemById',para, function(data) {
							if(data.messageType=="error"){
								BasePage.showInfoMessage(data.message);
							}else{
								BasePage.showInfoMessage("删除成功!");
								searchDeviceItemByOrder();
							}
						});
					}
				});
			}else{
				BasePage.showInfoMessage("选择需要删除的设备!");
			}
		});
//修改设备
		//TODO:创建window
	    $("#update_window").window("close");
		$("#updDeviceItemBtn").click(function(){
			var row = $("#deviceItemTable").datagrid('getSelected');
			if(row==null){
				BasePage.showInfoMessage("请选择修改的设备！");
				return;
			}else{
				$("#update_window").window("open");
				var deviceItemId=row["deviceItemId"];
				var para={};
				para["deviceItemId"]=deviceItemId;
				BasePage.sendPostRequest(bodyId,'/deviceItemController.do?method=ajaxFindDeviceItemById',para,function(data){	
					if(data== null || data.bizData!=null){
						init_callback(data.bizData);
					}else{
						BasePage.showInfoMessage("获得更新数据异常");
					}
				});
			}
		});
		function init_callback(updObj){
			var deviceItemId=updObj.deviceItemId;
			$("#upd_deviceItemID").text(deviceItemId);
			var deviceItemName=updObj.deviceTypeName+updObj.deviceItemId;;
			$("#upd_deviceItemName").text(deviceItemName);
			var deviceTypeName=updObj.deviceTypeName;
			$("#upd_deviceType").text(deviceTypeName);
			var propertyList=updObj.deviceItemPropertyList;
			$("#deviceItemPropertyTableUpd").datagrid('loadData', propertyList);
			$("#hidden_updDate").val(updObj["updDate"]);
			var deviceItemUid=updObj.deviceItemUid;
			$("#upd_deviceItemUid").textbox("setValue",deviceItemUid);
			var deviceItemAddr=updObj.deviceItemAddr;
			$("#upd_deviceItemAddr").textbox("setValue",deviceItemAddr);
			var row = $("#orderDg").datagrid('getSelected');
			$("#upd_orderNo").text(row['orderNo']);
			$("#hidden_upd_lat").val(updObj["lat"]);
			$("#hidden_upd_lng").val(updObj["lng"]);
		}
		//upd-window-cancel
		$("#updItem_window_cancel").click(function(){
			$("#update_window").window("close");
		});
		//upd-window-ok
		$("#updItem_window_OK").click(function(){
			var rows=$("#deviceItemPropertyTableUpd").datagrid("getRows");
			var deviceItemId=$("#upd_deviceItemID").text();
			var para={};
			var deviceItemUid=$.trim($("#upd_deviceItemUid").textbox("getValue"));
		    if(deviceItemUid.length!=0&&deviceItemUid.length>100){
		    	  BasePage.showInfoMessage("设备标识长度不合法！");
		    	  return ;
		     }
		    var deviceItemAddr=$.trim($("#upd_deviceItemAddr").textbox("getValue"));
	        if(deviceItemAddr.length!=0&&deviceItemAddr.length>100){
	      	  BasePage.showInfoMessage("设备地址长度不合法！");
	      	  return ;
	        }
	        var lat=$("#hidden_upd_lat").val();
            var lng=$("#hidden_upd_lng").val();
		    para["deviceItemUid"]=deviceItemUid;
		    para["deviceItemAddr"]=deviceItemAddr;
		    para["lat"]=lat;
	        para["lng"]=lng;
			para["deviceItemId"]=deviceItemId;
			para["updDate"]=$("#hidden_updDate").val();
			para["deviceItemPropertyStr"]=JSON.stringify(rows);
			BasePage.sendPostRequest(bodyId,'/deviceItemController.do?method=ajaxInsertDeviceItemProperty',para,function(data){	
				if(data.messageType=="error"){
					BasePage.showInfoMessage(data.message);
				}else{
					alert("保存成功！");
					$("#update_window").window("close");
					searchDeviceItemByOrder();
				}
			});
		});
});