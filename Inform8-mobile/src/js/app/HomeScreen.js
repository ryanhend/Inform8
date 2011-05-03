var displayLoginHandler = function(button, event) {	
	var loginPanel = Ext.create({xtype: 'Inform8Login', id: 'I8_LOGIN_POPUP'});
    loginPanel.show();    
};

var logoutHandler = function(button, event) {
	new LogoutTransaction(function(loggedout) { 
		if(loggedout) {
			Ext.getCmp('I8_HOME').setUser(null);
		}else {
			alert('not logged out');
		}
	});
};


var appToolbar = new Ext.Toolbar({
    dock : 'top',
    title: 'Inform8 Mobile',
    items: [
		{
			id: 'I8_LOADING_BUTTON',
		    text: 'Loading...',
		}, 
        {
            text: 'Login',
            id: 'I8_LOGIN_BUTTON',
            hidden: true,
            handler: displayLoginHandler
        },
        {
            text: 'Logout',
            id: 'I8_LOGOUT_BUTTON',
            hidden: true,
            handler: logoutHandler
        }        
    ]
});


var homePanel = new Ext.Panel({
	
	html: 'Congratulations. Your now running Inform8 Mobile.',
	cls: 'inform8-home'
	
});

inform8.views.Home = Ext.extend(Ext.Panel, {
    layout: 'fit',
    fullscreen: true,
    
    dockedItems: [appToolbar],
    items: [homePanel],
    
    setUser: function(user) {
    	
    	if (user != null && user != '') {
    		Ext.getCmp('I8_LOGIN_BUTTON').setVisible(false);
    		Ext.getCmp('I8_LOGOUT_BUTTON').setVisible(true);
    	}else {
    		Ext.getCmp('I8_LOGIN_BUTTON').setVisible(true);
    		Ext.getCmp('I8_LOGOUT_BUTTON').setVisible(false);
    	}    		
    }
    
});

Ext.reg('Inform8Home', inform8.views.Home);