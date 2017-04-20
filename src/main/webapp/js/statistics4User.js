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
                ['类型名称',   45.0],
                ['类型名称2',       26.8],
                {
                    name: '类型名称3',
                    y: 12.8,
                    sliced: true,
                    selected: true
                },
                ['类型名称4',    8.5],
                ['类型名称5',     6.2],
                ['类型名称6',   0.7]
            ]
        }]
    });
});
