$(document).ready(function() {
	//项目编号 
	$('#projectid').combobox('loadData',projectData);
	BasePage.BindMonthControl("monthCnt");
	BasePage.BindYearControl("yearCnt",2016,20,20);
	QmMap.init("mapContainer");
	$("#addCusMap").click(function(){
		$("#map_window").window("open");
	});
});
