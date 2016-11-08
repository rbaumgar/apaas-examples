var express = require("express");
var logfmt = require("logfmt");
var app = express();

// Read Environment Parameters
var port = Number(process.env.PORT || 4999);
var greeting = process.env.GREETING || 'Hello World!';

app.use(logfmt.requestLogger());

app.get('/', function(req, res) {
  res.send(greeting);
});

app.listen(port, function() {
  console.log("Listening on " + port);
});
