inform8.views.LoginFormPanel = Ext.extend(Ext.form.FormPanel, {
	autoRender : true,
	items : 
		[ {
			xtype : 'hiddenfield',
			name : 'login',
			value : '1'
		}, {
			xtype : 'textfield',
			name : 'Username',
			label : 'Username'
		}, {
			xtype : 'passwordfield',
			name : 'Password',
			label : 'Password'
		} ]
});

Ext.reg('Inform8LoginFormPanel', inform8.views.LoginFormPanel);