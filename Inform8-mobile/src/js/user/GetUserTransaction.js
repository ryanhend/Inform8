function GetUserTransaction(callback) {
	Ext.Ajax.request({
		url : 'ajax.php',
		params : {
			call : 'userdetails'
		},
		method : 'POST',
		success : function(result, request) {
			if (Ext.decode(result.responseText).authenticated) {
				var user = Ext.decode(result.responseText).user;
				callback(user);
				Ext.StoreMgr.add('user', user);
			} else {
				callback(null);
				Ext.StoreMgr.removeByKey('user');
			}
		},
		failure : function(result, request) {
			callback(null);
			Ext.StoreMgr.removeByKey('user');
		}
	});
}