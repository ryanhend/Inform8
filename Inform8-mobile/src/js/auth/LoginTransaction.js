function LoginTransaction(un, pw, callback) {
	Ext.Ajax.request({
		url : 'authenticate.php',
		params : {Username: un, Password: pw, login: 1},
		method : 'POST',
		success : function(result, request) {
			if (Ext.decode(result.responseText).result) {
				callback(Ext.decode(result.responseText).user);	
			} else {
				callback(null);
			}
		},
		failure : function(result, request) {
			callback(null);
		}
	});
}