$(document).ready(function() {
	BizPageSt.BindQueryCustResult('updCustomBody',"todayStopServiceCnt","threeDayStopServiceCnt","fifthDayStopServiceCnt","monthStopServiceCnt");
	
	BasePage.BindSexControl("csex");
	BasePage.BindUserTypeControl("ctype");
	QmMap.init("mapContainer");
	init();
	function init() {
		// 用户类别
		var cno=$("#cno").textbox("getValue");
		var para={};
		para["cno"]=cno;
		BasePage.sendPostRequest('updCustomBody','/customController.do?method=ajaxFindCustomById',para,function(data){	
			if(data== null || data.bizData!=null){
				init_callback(data.bizData);
			}else{
				BasePage.showInfoMessage("获得更新数据异常");
			}
		});
	}
	function init_callback(updObj){
		$("#hidden_updDate").val(updObj["updDate"]);
		$("#cname").textbox("setValue", updObj["cname"]);
		$("#ctel1").textbox("setValue", updObj["ctel1"]);
		$("#ctel2").textbox("setValue", updObj["ctel2"]);
		$("#csex").combobox("setValue", updObj["csex"]);
		$("#cidCard").textbox("setValue", updObj["cidCard"]);
		$("#caddress").textbox("setValue", updObj["caddress"]);
		 $("#lat").val(updObj["lat"]);
         $("#lng").val(updObj["lng"]);
		$("#substation").textbox("setValue", updObj["substation"]);
		$("#policestation").textbox("setValue", updObj["policestation"]);
		$("#ctype").combobox("setValue", updObj["ctype"]);
		$("#remark").textbox("setValue", updObj["remark"]);
		$("#projects").textbox("setValue", updObj["projects"]);
	}
	$("#updCusMap").click(function(){
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
	        });
	        QmMap.Map.on('click',function(e){
	            marker.setPosition(e.lnglat);
	            $("#lat").val(e.lnglat.lat);
	            $("#lng").val(e.lnglat.lng);
	            geocoder.getAddress(e.lnglat,function(status,result){
	              if(status=='complete'){
	            	  $("#caddress").textbox("setValue", result.regeocode.formattedAddress);
	              }
	            })
	        })
	    });
		$("#map_window").window("open");
	});
	$("#updCustom").click(function(){
		if(CustomerUtil.submitValidate()){
			var para = {};
			var cno=$.trim($("#cno").textbox("getValue"));
			para["cno"] = cno;
			var cname = $.trim($("#cname").textbox("getValue"));
			if (cname != "") {
				para["cname"] = cname;
			}
			var ctel1 = $.trim($("#ctel1").textbox("getValue"));
			if (ctel1 != "") {
				para["ctel1"] = ctel1;
			}
			var ctel2 = $.trim($("#ctel2").textbox("getValue"));
			if (ctel2 != "") {
				para["ctel2"] = ctel2;
			}
			var csex = $("#csex").combobox("getValue");
			para["csex"] = csex;
			var cidCard = $.trim($("#cidCard").textbox("getValue"));
			if (cidCard != "") {
				para["cidCard"] = cidCard;
			}
			var ctype = $("#ctype").combobox("getValue");
			para["ctype"] = ctype;
			var caddress = $.trim($("#caddress").textbox("getValue"));
			if (caddress != "") {
				para["caddress"] = caddress;
				para["lng"]=$("#lng").val();
				para["lat"]=$("#lat").val();
			}
			var policestation = $.trim($("#policestation").textbox("getValue"));
			if (policestation != "") {
				para["policestation"] = policestation;
			}
			var substation = $.trim($("#substation").textbox("getValue"));
			if (substation != "") {
				para["substation"] = substation;
			}
			var remark = $.trim($("#remark").textbox("getValue"));
			if (remark != "") {
				para["remark"] = remark;
			}
			para["updDate"]=$("#hidden_updDate").val();
			BasePage.sendPostRequest('updCustomBody','/customController.do?method=ajaxUpdateCustom',para,function(data){
				if(data.messageType=="error"){
					BasePage.showInfoMessage(data.message);
					return;
				}else{
					alert("更新成功！");
					$("#returnManagePage").submit();
				}
			});
		}
	});
	 $("#goHist").click(function(){
			$("#returnManagePage").submit();
	});
});