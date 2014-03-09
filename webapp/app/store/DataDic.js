//User数据集合
Ext.define('AM.store.DataDic', {
	extend: 'Ext.data.Store',
	model: 'AM.model.DataDic',
	storeId: 's_user',
	proxy:{
	    type:'ajax',
	    url:'/core/dd/list/0',
	    reader: {
	        type: 'json',
	        root: /*'topics'*/'content',
	        totalProperty: 'totalElements'
	    },writer:{
			type:'json'
		}
	},
	autoLoad: true //很关键
});