Ext.onReady(function(){
	Ext.QuickTips.init();
	Ext.Loader.setConfig({
		enabled:true
	});
	//Ext.override(Ext.view.Table, { enableTextSelection: true }); // Treepanels
	//Ext.override(Ext.grid.View,  { enableTextSelection: true }); // Grids
	Ext.application({
		name : 'AM',//应用的名字
		appFolder : "app",//应用的目录
		launch:function(){//当前页面加载完成执行的函数
	        Ext.create('Ext.container.Viewport', { //简单创建一个试图
	        	layout:'fit',//自动填充布局
	            items: {
	            	xtype: 'ddView',
	                title: '数据字典',
	                html : 'List of datadic will go here'
	            }
	        });
		},
		controllers:[
			'DataDic'
		]
	});
});