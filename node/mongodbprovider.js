var Db = require('mongodb').Db;
var Connection = require('mongodb').Connection;
var Server = require('mongodb').Server;
var BSON = require('mongodb').BSON;
var ObjectID = require('mongodb').ObjectID;

MongoDBProvider = function(host, port) {
  this.db= new Db('sparqles', new Server(host, port, {auto_reconnect: true}, {}));
  this.db.open(function(){});
};

//getCollection

MongoDBProvider.prototype.getCollection= function(collectionName, callback) {
  this.db.collection(collectionName, function(error, collection) {
    if( error ) callback(error);
    else callback(null, collection);
  });
};

//Availability
MongoDBProvider.prototype.getAvailView = function(callback) {
    this.getCollection('atasks_agg',function(error, collection) {
      if( error ) callback(error)
      else {
        collection.find().sort({"endpoint.datasets.0.label":1,"endpoint.uri":1}).toArray(function(error, results) {
          if( error ) callback(error)
          else callback(null, results)
        });
      }
    });
};

//Interoperability
MongoDBProvider.prototype.getInteropView = function(callback) {
    this.getCollection('ftasks_agg',function(error, collection) {
      if( error ) callback(error)
      else {
        collection.find().sort({"endpoint.datasets.0.label":1,"endpoint.uri":1}).toArray(function(error, results) {
          if( error ) callback(error)
          else callback(null, results)
        });
      }
    });
};

//Performance
MongoDBProvider.prototype.getPerfView = function(callback) {
    this.getCollection('ptasks_agg',function(error, collection) {
      if( error ) callback(error)
      else {
        collection.find().sort({"endpoint.datasets.0.label":1,"endpoint.uri":1}).toArray(function(error, results) {
          if( error ) callback(error)
          else callback(null, results)
        });
      }
    });
};

//Discoverability
MongoDBProvider.prototype.getDiscoView = function(callback) {
    this.getCollection('dtasks_agg',function(error, collection) {
      if( error ) callback(error)
      else {
        collection.find().sort({"endpoint.datasets.0.label":1,"endpoint.uri":1}).toArray(function(error, results) {
          if( error ) callback(error)
          else callback(null, results)
        });
      }
    });
};

//Endpoint view
MongoDBProvider.prototype.getEndpointView = function(epUri, callback) {
    this.getCollection('epview',function(error, collection) {
      if( error ) callback(error)
      else {
        collection.find({"endpoint.uri":epUri}).toArray(function(error, results) {
          if( error ) callback(error)
          else callback(null, results)
        });
      }
    });
};

//autocomplete
MongoDBProvider.prototype.autocomplete = function(query, callback) {
    this.getCollection('endpoints',function(error, collection) {
      if( error ) callback(error)
      else {
		collection.find({ 'datasets.label': {$regex: '.*'+query+'.*', $options: 'i'}, 'datasets.uri': {$regex: '.*'+query+'.*', $options: 'i'}}).limit(10).toArray(function(error, results) {
          if( error ) callback(error)
          else callback(null, results)
        });
      }
    });
};

exports.MongoDBProvider = MongoDBProvider;