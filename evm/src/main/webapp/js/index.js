 $(document).ready(function() {
	
	var bodyId="bodyId";
	QmMap.init("qmMapContainer");
	//项目编号 
	ProjectUtil.BindProjectList(bodyId,"projectid",{},function(data){
		var arr = [{'projectId':'-1','projectName':'全部',selected:true}];
		$('#projectid').combobox('loadData',arr.concat(data.bizData));
		indexPage.AllProject=data.bizData;
		
		var project=indexPage.GetProjectById(indexPage.defaultProjectId);
		if(project==null){
			BasePage.delCookie("evm_index_defaultProject");
			$("#projectid").combobox("setValue",-1);
			BasePage.showInfoMessage("您设置的默认项目已失效，请重新配置！");
			return;
		}
		if(indexPage.defaultProjectId!=0){
			$("#projectid").combobox("setValue",indexPage.defaultProjectId);
		}
	});
	//搜索订单
	$("#doSearch").click(function(){
		QmMap.Map.clearMap();
		QmMap.Cluster.clearMarkers(); // 清除聚合点
		var projectid=$("#projectid").combobox("getValue");
		var orderNo=$.trim($("#orderNo").textbox("getValue"));
		var deviceItemPara=$.trim($("#deviceItemPara").textbox("getValue"));
		var para={};
	    para["projectId"]=projectid;
		 
		if(orderNo!=""){
			para["orderNo"]=orderNo;
		}
		if(deviceItemPara!=""){
			para["deviceItemPara"]=deviceItemPara;
		}
		var url='/orderController.do?method=ajaxFindOrder4Index';
		BasePage.sendPostRequest(bodyId,url,para,function(data){	
			init_callback(data.bizData);
		});
		var stUrl="/orderController.do?method=ajaxFindOrderSt4Index";
		BasePage.sendPostRequest(bodyId,stUrl,para,function(data){	
			st_callback(data.bizData);
		});
	});
	init();
	function init(){
		
		indexPage.defaultProjectId=BasePage.getCookie("evm_index_defaultProject");
		
		 
		var url='/orderController.do?method=ajaxFindOrder4Index';
		var para={};
		para["projectId"]=indexPage.defaultProjectId;
		BasePage.sendPostRequest(bodyId,url,para,function(data){	
			init_callback(data.bizData);
		});
		var stUrl="/orderController.do?method=ajaxFindOrderSt4Index";
		BasePage.sendPostRequest(bodyId,stUrl,para,function(data){	
			st_callback(data.bizData);
		});
	}
	function init_callback(data){
		if(data.length==0){
			//alert("项目【"+ $("#projectid").combobox("getText")+"】未创建订单！");
			return;
		}
		var arr=[];
		for(var i=0;i<data.length;i++){
			var orderMarker=createOrderMarker(data[i]);
			if(orderMarker!=undefined){
				arr.push(orderMarker);
			}
		}
		QmMap.Cluster.addMarkers(arr);
	}
	function st_callback(data){
		for(var i=0;i<data.length;i++){
			if(data[i].ordeStatus==4){
				$('#successOrder').text(data[i].orderCnt);
			}else if(data[i].ordeStatus!=4&&data[i].mStatus==1 ){
				$('#maintianOrder').text(data[i].orderCnt);
			}else if(data[i].ordeStatus!=4&&data[i].mStatus==0 ){
				$('#workingOrder').text(data[i].orderCnt);
			}
		}
	}
	function createOrderMarker(data) {
		    if(data.lng==undefined||data.lat==undefined)
		    	return;
			var orderLocation = new AMap.LngLat(data.lng, data.lat);
			var iconPath =getIconByStatus(data);
			var markerContent = document.createElement("div");
			var markerImg = document.createElement("img");
			markerImg.src = iconPath;
			markerContent.appendChild(markerImg);
			var orderMarker = new AMap.Marker({
			map : QmMap.Map,
			position : orderLocation,
			content : markerContent
			});
			AMap.event.addListener(orderMarker, 'click', function(e) {
				createOrderWindow(data, e)
			});
			return orderMarker;
		}
	function createOrderWindow(data, e) {
		var content1=createOrderInfoContent(data);
		var content2=createOrderCusInfoContent(data);
		var info=createTab("订单信息","客户信息",content1,content2);
		var inforWindow = new AMap.InfoWindow({
			content : info,
			size : new AMap.Size(280, 180),
			offset : new AMap.Pixel(0, -30)
			});
		inforWindow.open(QmMap.Map, e.target.getPosition());
	}
	
	function createOrderInfoContent(data){
		var info = [];
		var workCompany=data.workCompany;
		if(workCompany==undefined){
			workCompany="";
		}
		var workPmName="";
		if(data.workPm!=undefined&&data.workPm.ufullName!=undefined){
			workPmName=data.workPm.ufullName;
		}
		info.push("订单编号："+data.orderNo);
		info.push("订单状态："+BasePage.WorkStatusformater(data.workStatus));
		info.push("施工地点："+data.workAddress);
		info.push("施工负责人："+workPmName);
		info.push("施工进度："+data.workProgress);
		info.push("施工单位："+workCompany);
		info.push("施工人数："+data.workCnt);
		return info.join("<br/>");
	}
	function createOrderCusInfoContent(data){
		var info = [];
		var custom=data.project.custom;
		if(custom==null){
			info.push("客户名称：");
			info.push("客户性别：");
			info.push("客户电话：");
			info.push("所属分局：");
			info.push("所属派出所：");
			return info.join("<br/>");
		}else{
			var cmName=data.project.custom.cname;
			if(cmName==undefined){
				cmName="";
			}
			var cmSex="";
			if(cmSex==undefined){
				cmSex=data.project.custom.csex;
			}
			var cmTel=data.project.custom.ctel1;
			if(cmTel==undefined){
				cmTel="";
			}
			var substation="";
			var policestation="";
			if(data.project.custom!=undefined){
				if(data.project.custom.substation!=undefined){
					substation=data.project.custom.substation;
				}
				if(data.project.custom.policestation!=undefined){
					policestation=data.project.custom.policestation;
				}
			}
			info.push("客户名称："+cmName);
			info.push("客户性别："+BasePage.Sexformater(cmSex));
			info.push("客户电话："+cmTel);
			info.push("所属分局："+substation);
			info.push("所属派出所："+policestation);
			return info.join("<br/>");
		}
	}
 
	function createTab(title1,tilte2,content1,content2) {
		var info = document.createElement("div");
		info.style.height = "170px";
		info.style.padding = "5px";
		var header = document.createElement("ul");
		header.className = "info_ul";
		var tab_header_item1 = document.createElement("li");
		tab_header_item1.innerHTML =title1;
		tab_header_item1.className = "fli";
		var tab_header_item2 = document.createElement("li");
		tab_header_item2.innerHTML = tilte2;
		header.appendChild(tab_header_item1);
		header.appendChild(tab_header_item2);
		info.appendChild(header);
		var tab_content = document.createElement("div");
		var tab_content1 = document.createElement("div");
		var tab_content2 = document.createElement("div");
		tab_content2.id = "tab_item_2";
		tab_content.className = "tab_con";
		tab_content1.className = "fdiv";
		tab_content.appendChild(tab_content1);
		tab_content.appendChild(tab_content2);
		//TODO:内容
		tab_content1.innerHTML =content1;
		tab_content2.innerHTML =content2;
		info.appendChild(tab_content);
		tab_header_item2.onclick = function() {
			change("1")
		};
		tab_header_item1.onclick = function() {
			change("2")
		};
		function change(item_id) {
			if ("2" == item_id) {
				tab_header_item1.className = "fli";
				tab_content1.className = "fdiv";
				tab_header_item2.className = "";
				tab_content2.className = "";
			} else {
				tab_header_item2.className = "fli";
				tab_content2.className = "fdiv";
				tab_header_item1.className = "";
				tab_content1.className = "";
			}
		}
		return info;
	}
    function getIconByStatus(data){
    	//完成的
    	var iconPath = BasePage.urlPre + '/images/order_success.png';
    	//安装中的
    	if(data.workStatus!=4&&data.morderCnt==0){
    		iconPath =BasePage.urlPre + '/images/order_working.png';
    	}
    	//故障维修中的
    	if(data.workStatus!=4&&data.morderCnt!=0){
    		iconPath =BasePage.urlPre + '/images/order_maintian.png';
    	}
    	return iconPath;
    }
    //打开配置页面
    $("#projectConfig").click(function(){
    	$("#projectName").textbox("setValue","");
    	$("#projectNo").textbox("setValue","");
    	$("#pmName").textbox("setValue","");
    	$("#signBeginDate").datebox("setValue","");
    	$("#signEndDate").datebox("setValue","");
    	$("#projectDg").datagrid('loadData', []);
    	$("#project_window").window("open");
    });
    //查询工程
    $("#doSearchProjectBtn").click(function(){
    	var para={};
    	para["projectName"]=$("#projectName").textbox("getValue");
    	para["projectNo"]=$("#projectNo").textbox("getValue");
    	para["pmName"]=$("#pmName").textbox("getValue");
    	para["beginDate"]=$("#signBeginDate").datebox("getValue");
    	para["endDate"]=$("#signEndDate").datebox("getValue");
		BasePage.sendPostRequest(bodyId,'/projectController.do?method=ajaxFindProjectPage',para,function(data){	
			if(data== null || data.bizData.total ==0){
				BasePage.showInfoMessage("未查询到项目信息！");
				$("#projectDg").datagrid('loadData', []);
			}else{
				$("#projectDg").datagrid('loadData', data.bizData);
			}
		});
    });
    //设置
    $("#project_window_OK").click(function(){
    	var arr = [{'projectId':'-1','projectName':'全部',selected:true}];
    	var selectRows=$("#projectDg").datagrid("getSelections");
    	if(selectRows==null||selectRows.length==0){
    		BasePage.confirmMessage("您未选择任何项目，项目列表将显示所有项目?",function(r){
			    if (r){
			    	$("#project_window").window("close");
			    } else{
			    	return;
			    }
    	  });
    	}else{
    		var comboData=arr.concat(selectRows);
    		comboData=indexPage.AddDaultProject(comboData,indexPage.defaultProjectId);
    		$('#projectid').combobox('loadData',comboData);
    		$("#projectid").combobox("setValue",indexPage.defaultProjectId);
    		$("#project_window").window("close");
    	}
    });
    //追加设置
    $("#project_window_cancel").click(function(){
    	var comboData=$('#projectid').combobox('getData');
    	var selectRows=$("#projectDg").datagrid("getSelections");
    	if(selectRows==null||selectRows.length==0){
    		BasePage.confirmMessage("您未选择任何项目，项目列表将显示所有项目?",function(r){
			    if (r){
			    	$("#project_window").window("close");
			    } else{
			    	return;
			    }
    	  });
    	}else{
    		var data=indexPage.addComboData(comboData,selectRows);
    		var defautProject=indexPage.GetProjectById(indexPage.defaultProjectId);
    		if(defautProject!=null){
    			data.push(defautProject)
    		}
    		$('#projectid').combobox('loadData',data);
    		$("#projectid").combobox("setValue",indexPage.defaultProjectId);
    		$("#project_window").window("close");
    	}
    });
    
 });
 var indexPage={}||indexPage;
 (function() {
	 indexPage.AllProject=[];
	 indexPage.defaultProjectId=0;
	 //操作按钮
	 indexPage.formatOper=function(val,row,index){
		 return '<a href="#" onclick="indexPage.setDefalutProject('+index+')">设置</a>'; 
	 };
	 //设置默认项目
	 indexPage.setDefalutProject=function(index){  
		    $('#projectDg').datagrid('selectRow',index);// 关键在这里  
		    var row = $('#projectDg').datagrid('getSelected');  
		    if (row){
		    	var comboData=$('#projectid').combobox('getData');
		    	var data=indexPage.addComboData(comboData,row);
	    		$('#projectid').combobox('loadData',data);
		    	$("#projectid").combobox("setValue",row["projectId"]);
		    	BasePage.setCookie("evm_index_defaultProject",row["projectId"]);
		    	indexPage.defaultProjectId=row["projectId"];
		    	BasePage.showInfoMessage("您已设置【"+row["projectName"]+"】为默认项目！");
		    }  
	 }
	 //追加数据合并
	 indexPage.addComboData=function(comboData,addData){
	    	var _comboData=[];
	    	_comboData=_comboData.concat(comboData)
	    	 for(var j=0;j<addData.length;j++){
	    	    var flag=true;
	    		for(var i=0;i<comboData.length;i++){
	    			 if(comboData[i]["projectId"]==addData[j]["projectId"]){
	    				 flag=false;
	    				 continue;
	    			 }
	    		 }
	    		if(flag==true){
	    			_comboData.push(addData[j]);
	    		}
	    	}
	    	return _comboData;
	 };
	 indexPage.GetProjectById=function(projectId){
		 for(var j=0;j<indexPage.AllProject.length;j++){
			 if(indexPage.AllProject[j]["projectId"]==projectId){
				  return indexPage.AllProject[j];
			 }
		 }
		 return null;
	 };
	 indexPage.AddDaultProject=function(comboData,projectId){
		 var flag=true;
		 var project=indexPage.GetProjectById(projectId);
		 if(project==null){
			 return comboData;
		 }
		 for(var j=0;j<comboData.length;j++){
			 if(comboData[j]["projectId"]==projectId){
				 flag=false;
				 break;
			 }
		 }
		 if(flag==true){
			 comboData.push(project);
		 }
		 return comboData;
	 }
 })();
 
 