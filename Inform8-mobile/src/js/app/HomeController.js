/**
 * @class loans
 * @extends Ext.Controller
 * 
 * The only controller in this simple application - this simply sets up the fullscreen viewport panel
 * and renders a detailed overlay whenever a Loan is tapped on.
 */
Ext.regController("home", {

    /**
     * Renders the Viewport and sets up listeners to show details when a Loan is tapped on. This
     * is only expected to be called once - at application startup. This is initially called inside
     * the app.js launch function.
     */
	launch: function() {
        homeView = this.render({xtype: 'Inform8Home', id: 'I8_HOME'}, Ext.getBody());
        
        new GetUserTransaction(function(user) {
        	Ext.getCmp('I8_LOADING_BUTTON').setVisible(false);
        	homeView.setUser(user);
        });
    }

});