$(document).ready(function() {
	UserUtil.BindRoleList('addUserBody');
	BasePage.BindSexControl("usex");
	QmMap.init("mapContainer");
	$("#addCusMap").click(function(){
		AMap.plugin('AMap.Geocoder',function(){
	        var geocoder = new AMap.Geocoder({
	            city: "0431"//城市，默认：“全国”
	        });
	        var _position=[125.3245,43.886841]
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
		var roleName=$("#roleName").combobox("getValues");
		var para = {};
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
		para["roleName"] = JSON.stringify(roleName);
		BasePage.sendPostRequest('addUserBody','/userController.do?method=ajaxInsertUser',para,function(data){	
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