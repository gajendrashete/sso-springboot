console.log('hello from appjs'); 

Ext.onReady(function() {
	
	Ext.create('Ext.panel.Panel', {
	fullscreen: true,
    title: '<div align="center">Administration : Dashboard</div>',
    padding: '50 50 50 50',
    html: '<div align="center"> <br>' + 
		  '<a href="/admin/home">Test Admin access</a>' + 
		  '<hr width="80%">' +
		  '<a href="/app/logout">Logout</a>' +
		  '</div>',
    renderTo: Ext.getBody()
});

	
});