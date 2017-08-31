$(document).ready(function() {
	//项目编号 
	$('#projectid').combobox('loadData',projectData);
	BasePage.BindMonthControl("monthCnt");
	BasePage.BindYearControl("yearCnt",2016,20,20);
});
$(function () {
    $('#container').highcharts({
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false
        },
        title: {
            text: ''
        },
        tooltip: {
            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: false
                },
                showInLegend: true
            }
        },
        series: [{
            type: 'pie',
            name: 'Browser share',
            data: [
                ['市电故障',   45.0],
                ['光缆故障',       26.8],
                {
                    name: '装修',
                    y: 12.8,
                    sliced: true,
                    selected: true
                },
                ['设备故障',    8.5],
                ['施工质量',     6.2],
                ['人为破坏',   0.7]
            ]
        }]
    });
});
