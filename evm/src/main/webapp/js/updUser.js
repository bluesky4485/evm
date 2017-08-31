$(document).ready(function() {
	var bodyId="updUserBody";
	UserUtil.BindRoleList(bodyId);
	BasePage.BindSexControl("usex");
	QmMap.init("mapContainer");
	$("#addCusMap").click(function(){
		var lng=$("#lng").val();
		var lat =$("#lat").val();
		AMap.plugin('AMap.Geocoder',function(){
	        var geocoder = new AMap.Geocoder({
	            city: "0431"//城市，默认：“全国”
	        });
	        var _position=[125.3245,43.886841]
	        if((lng!=0||lng!="")&&(lat!=0||lat!="")){
	        	_position=[lng,lat];
	        }
	        var marker = new AMap.Marker({
	            map:QmMap.Map,
	            bubble:true,
	            position: _position
	        })
	        QmMap.Map.on('click',function(e){
	            marker.setPosition(e.lnglat);
	            $("#lat").val(e.lnglat.lat);
	            $("#lng").val(e.lnglat.lng);
	            geocoder.getAddress(e.lnglat,function(status,result){
	              if(status=='complete'){
	            	  $("#uaddress").textbox("setValue", result.regeocode.formattedAddress);
	              }
	            })
	        })
	    });
		$("#map_window").window("open");
	});
	init();
	function init() {
		// 用户类别
		var uid=$("#uid").val();
		var para={};
		para["uid"]=uid;
		BasePage.sendPostRequest(bodyId,'/userController.do?method=ajaxFindUserById',para,function(data){	
			if(data== null || data.bizData!=null){
				init_callback(data.bizData);
			}else{
				BasePage.showInfoMessage("获得更新数据异常");
			}
		});
	}
	function init_callback(updObj){
		$("#uname").textbox("setValue",updObj["uname"]);
		$("#usex").combobox("setValue",updObj["usex"]);
		$("#upass").passwordbox("setValue",updObj["upwd"]);
		$("#rupass").passwordbox("setValue",updObj["upwd"]);
		$("#utel1").textbox("setValue",updObj["utel1"]);
		$("#utel2").textbox("setValue",updObj["utel2"]);
		$("#ufullName").textbox("setValue",updObj["ufullName"]);
		$("#roleName").textbox("setValue",updObj["roleName"]);
		$("#uidCard").textbox("setValue",updObj["uidCard"]);
		$("#uaddress").textbox("setValue",updObj["uaddress"]);
		 $("#lat").val(updObj["lat"]);
	     $("#lng").val(updObj["lng"]);
		$("#uremark").textbox("setValue",updObj["uremark"]);
		$("#updDate").val(updObj["updDate"]);
		var roleList=updObj.roleList;
		var role=[];
		for(var i=0;i<roleList.length;i++){
			role.push(roleList[i].roleId);
		}
		$("#roleName").combobox("setValues",role);
	}
	$("#saveUser").click(function(){
		if(!UserUtil.SubmitValidate()){
			return;
		}
		var uname = $.trim($("#uname").textbox("getValue"));
		var ufullName = $.trim($("#ufullName").textbox("getValue"));
		var pwd=$.trim($("#upass").passwordbox("getValue"));
		var rpwd=$.trim($("#rupass").passwordbox("getValue"));
		var ctel1 = $.trim($("#utel1").textbox("getValue"));
		var ctel2 = $.trim($("#utel2").textbox("getValue"));
		var csex = $("#usex").combobox("getValue");
		var idCard = $.trim($("#uidCard").textbox("getValue"));
		var uaddress = $.trim($("#uaddress").textbox("getValue"));
		var remark = $.trim($("#uremark").textbox("getValue"));
		var updDate=$("#updDate").val();
		var roleName=$("#roleName").combobox("getValues");
		var para = {};
			para["uid"] = $("#uid").val();
			para["uname"] = uname;
			para["upwd"]=pwd;//$.md5(pwd).toUpperCase();
			para["ufullName"] = ufullName;
			para["utel1"] = ctel1;
			para["utel2"] = ctel2;
			para["usex"] = csex;
			para["uidCard"] = idCard;
			para["uaddress"] = uaddress;
			para["lng"]=$("#lng").val();
			para["lat"]=$("#lat").val();
			para["uremark"] = remark;
			para["updDate"] = updDate;
			para["roleName"] = JSON.stringify(roleName);
			
			BasePage.sendPostRequest('updUserBody','/userController.do?method=ajaxUpdateUser',para,function(data){	
				if(data.messageType=="error"){
					BasePage.showInfoMessage(data.message);
					return;
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