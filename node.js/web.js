var express = require('express');

var app = express();
var port = 3000;

var answer = function(question) {
	return "MyName";
}

app.get('/', function(req, res) {
	var q = req.param('q');
	console.log('Q: "%s"', q);
	var a = answer(q);
	console.log('A: %s', a);
	res.send(a.toString());
});

app.listen(port);
console.log("Server running on http://127.0.0.1:%s/", port);
