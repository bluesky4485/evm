$(document).ready(function() {
	BizPageSt.BindQueryCustResult('addProjectBody',"todayStopServiceCnt","threeDayStopServiceCnt","fifthDayStopServiceCnt","monthStopServiceCnt");
	BasePage.BindSexControl("csex");
	BasePage.BindUserTypeControl("ctype");
	QmMap.init("mapContainer");
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
            	  $("#caddress").textbox("setValue", result.regeocode.formattedAddress);
              }
            })
        })
    });
	$("#addCusMap").click(function(){
		QmMap.search($("#caddress").textbox("getValue"));
		$("#map_window").window("open");
	});
	$("#saveCustom").click(function(){
		if(CustomerUtil.submitValidate()){
			var para = {};
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
			BasePage.sendPostRequest('addProjectBody','/customController.do?method=ajaxInsertCustom',para,function(data){	
				if(data.messageType=="error"){
					BasePage.showInfoMessage(data.message);
					return;
				}else{
					alert("保存成功！");
					$("#retrnManageForm").submit();
				}
			});
		} 
	});
	$("#goHist").click(function(){
		$("#retrnManageForm").submit();
	});
});