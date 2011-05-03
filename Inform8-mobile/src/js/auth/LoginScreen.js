inform8.views.Login = Ext.extend(Ext.Panel, {
	floating : true,
	scroll : 'both',
	centered : true,
	modal : true,
	width : 400,
	height : 200,
	layout : 'fit',
	items : [ {
		xtype : 'Inform8LoginFormPanel',
		id: 'I8_LOGIN_FRMPANEL'
	} ],
	dockedItems : [ {
		dock : 'bottom',
		xtype : 'toolbar',
		align : 'end',
		ui : 'light',
		items : [ {
			xtype : 'spacer'
		}, {
			ui : 'confirm',
			text : 'Login',
			scope : this,
			handler : function() {
				var values = Ext.getCmp('I8_LOGIN_FRMPANEL').getValues();
				new LoginTransaction(values.Username, values.Password, function(user) { 
					Ext.getCmp('I8_HOME').setUser(user);
					
				});
				Ext.getCmp('I8_LOGIN_POPUP').hide();
			}
		} ]
	} ]
});

Ext.reg('Inform8Login', inform8.views.Login);