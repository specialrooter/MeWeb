Ext.define("AM.view.DataDic",{
	extend:'Ext.grid.Panel',
	title : 'Grid Demo',//标题
	alias: 'widget.ddView',
	viewConfig:{
		stripeRows: true, 
	    enableTextSelection:true
	},
	selType: 'cellmodel',
	plugins: [
        Ext.create('Ext.grid.plugin.RowEditing', {
            clicksToEdit: 1
        })
    ],
	frame : true,//面板渲染
	width : 600,
	height: 280,
	columns : [ //列模式
				{text:"字典编码",dataIndex:'keyVal',width:100},
				{text:"类型",dataIndex:'type',width:100},
				{text:"中文描述",dataIndex:'descZhCn',width:100},
				{text:"英文描述",dataIndex:'descEnUs',width:100},
				{text:"繁体描述",dataIndex:'descZhTw',width:100
					/*field:{
						xtype:'textfield',
						allowBlank:false
					}*/
				},
				{text:"父节点",dataIndex:'pid',width:100}
	],
	tbar :[
				{xtype:'button',text:'添加',iconCls:'table_add'},
				{xtype:'button',id:'table_delete',text:'删除',iconCls:'table_remove'},
				{xtype:'button',text:'修改',iconCls:'table_edit'},
				{xtype:'button',text:'查看',iconCls:'table_comm'}
	],	
	dockedItems :[{
				xtype:'pagingtoolbar',
				store:'DataDic',
				dock:'bottom',
				displayInfo:true
	}],
	plugins:[
				Ext.create("Ext.grid.plugin.CellEditing",{
					clicksToEdit : 2
				})
	],
	selType:'checkboxmodel',//设定选择模式
	multiSelect:true,//运行多选
	store : 'DataDic',
	initComponent:function(){
		this.callParent(arguments);
	}
});


