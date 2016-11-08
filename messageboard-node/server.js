var http = require('http');
var express = require('express');
var bodyParser = require('body-parser');

var app = express();
app.use(bodyParser());

var PORT = process.env.PORT || 8089;
var topicList = [];
var topicDetail = {};
var currentId = 123;

function addTopic(tTitle, tText) {
  console.log("addTopic(" + tTitle + "," + tText + ")");
  var topicId = ++currentId;
  topicList.push({title: tTitle, id: topicId});
  topicDetail[topicId] = {title: tTitle, description: tText, comments: []};
  return topicId;
}

function addComment(topicId, text) {
  console.log("addComment(" + topicId + "," + text + ")");
  topicDetail[topicId].comments.push(text);
}

// Seed Data
var id1 = addTopic("Best Star Wars Movie","What's the best Star Wars movie?");
var id2 = addTopic("Your favourite ice cream?","What is your favourite ice cream and why?");
addComment(id1, "Empire!");
addComment(id1, "The first was the best. They should have stopped there!");
addComment(id1, "All films with Ewoks!");
addComment(id2, "Vanilla--simple, pure.");


app.get('/', function (req,res) {
  res.sendfile('index.html');
});

app.get('/todo', function (req,res) {
  res.json(topicList);
});

app.get('/todo/:itemId', function (req, res) {
  res.json(topicDetail[req.params.itemId]);
});

app.post('/todo', function (req,res) {
  addTopic(req.body.title, req.body.description);
  res.end();
});

app.post('/todo/:itemId', function (req,res) {
    var topicId = req.params.itemId;
    addComment(topicId, req.body.text);
    res.end();
});

app.listen(PORT, function() {
    console.log('server listening on port ' + PORT)

});
