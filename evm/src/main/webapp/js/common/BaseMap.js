var QmMap = {} || QmMap;
(function() {
	var _Map;
	QmMap.Map;
	QmMap.MGeocoder;
	QmMap.Cluster;
	QmMap.placeSearch;
	QmMap.init = function(containerId) {
		_Map = new AMap.Map(containerId);
		//增加地图级别
		_Map.setZoomAndCenter(11, [ 125.3245,43.886841]);
		_Map.plugin([ "AMap.ToolBar" ], function() {
			toolBar = new AMap.ToolBar();
			_Map.addControl(toolBar);
		});
		_Map.plugin([ "AMap.OverView" ], function() {
			overView = new AMap.OverView();
			_Map.addControl(overView);
		});
		AMap.service([ "AMap.Geocoder" ], function() {
			QmMap.MGeocoder = new AMap.Geocoder({
			radius : 1000,
			extensions : "all"
			});
		});
		_Map.plugin([ "AMap.MarkerClusterer" ], function() {
			QmMap.Cluster = new AMap.MarkerClusterer(_Map, []);
			//设置地图中点标记的最大聚合级别
			QmMap.Cluster.setMaxZoom(11);
			//设置单个聚合的最小数量 聚合的最小数量。默认值为2，即小于2个点则不能成为一个聚合
//			QmMap.Cluster.setMinClusterSize(2);
		});
		AMap.service('AMap.PlaceSearch',function(){//回调函数
	        //实例化PlaceSearch
			QmMap.placeSearch= new AMap.PlaceSearch();
	        //TODO: 使用placeSearch对象调用关键字搜索的功能
	    })
		QmMap.Map=_Map;
		return _Map;
	};
	QmMap.clearMap = function() {
		Map.clearMap();
	};
    QmMap.search=function(address){
    	if(address=="")
    		return;
    	QmMap.placeSearch.search(address, function(status, result) {
    	       //TODO:开发者使用result自己创建交互面板和地图展示
    		for(var i=0;i<result.poiList.pois.length;i++){
    			var  marker = new AMap.Marker({
    				    position:result.poiList.pois[i].location,
    				    title: result.poiList.pois[i].name
    				});
    		    marker.setMap(_Map);
    		}
    	 });
    };
})();
 