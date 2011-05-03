Ext.regApplication({
    name: "inform8",
    
    icon: '/images/icon.png',
    glossOnIcon: false,
    
    launch: function() {
        Ext.dispatch({
            controller: 'home',
            action    : 'launch'
        });
    }

	


});