var express = require('express');
var path = require('path');
var favicon = require('serve-favicon');
var logger = require('morgan');
var cookieParser = require('cookie-parser');
var bodyParser = require('body-parser');
var methodOverride = require('method-override');
var errorHandler = require('errorhandler');

//var index = require('./routes/index');
//var users = require('./routes/users');

var http = require('http');
var fs = require('fs');

// 既存モジュール
var ConfigProvider = require('./configprovider').ConfigProvider;
var MongoDBProvider = require('./mongodbprovider').MongoDBProvider;
var mongoDBProvider = new MongoDBProvider('localhost', 27017);
var configApp = new ConfigProvider('../config.json');

var app = express();

/*
 * mongodb
// 参考：http://qiita.com/itagakishintaro/items/a1519998a91061cbfb1e
var MongoClient = require('mongodb').MongoClient
  , assert = require('assert');

// Connection URL
var url = 'mongodb://localhost:27017/sparqles';

// Use connect method to connect to the server
MongoClient.connect(url, function(err, db) {
  assert.equal(null, err);
  console.log("Connected successfully to server");

findDocuments(db, function() {
      db.close();
    });
});

var findDocuments = function(db, callback) {
  // Get the documents collection
  var collection = db.collection('endpoints');
  // Find some documents
  //collection.find({}).toArray(function(err, docs) {
  collection.find({},{"_id":0}).sort({"uri":1}).toArray(function(err, docs) {
    assert.equal(err, null);
    console.log("Found the following records");
    //console.log(docs)
    callback(docs);
  });
}
 * mongodb ここまで
 */

// all environments
app.set('port', process.env.PORT || 3001);

// set default timezon in Tokyo
process.env.TZ = 'Asia/Tokyo';

// development only
if ('development' == app.get('env')) {
  app.use(errorHandler())
}

// view engine setup
app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'jade');

// uncomment after placing your favicon in /public
//app.use(favicon(path.join(__dirname, 'public', 'favicon.ico')));
app.use(logger('dev'));
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: false }));
app.use(cookieParser());
app.use(express.static(path.join(__dirname, 'public')));

//app.use('/', index);
//app.use('/users', users);

// error handler
app.use(function(err, req, res, next) {
  // set locals, only providing error in development
  res.locals.message = err.message;
  res.locals.error = req.app.get('env') === 'development' ? err : {};

  // render the error page
  res.status(err.status || 500);
  res.render('error');
});

/*
 * Router
 */
app.get('/', function(req, res){
		/*var eps = JSON.parse(fs.readFileSync('./examples/index.json'));*/
		mongoDBProvider.endpointsCount(function(error,nbEndpointsSearch){
		//console.log(docs);
			mongoDBProvider.getLastUpdate( function(error,lastUpdate){
			//console.log(lastUpdate);
				mongoDBProvider.getIndex( function(error,index){
				//console.log(index);
          mongoDBProvider.getAMonths( function(error,amonths){
            //console.log(JSON.stringify(amonths));
            var indexInterop = JSON.parse(JSON.stringify(index.interoperability.data), function(k, v) {
              if (k === "data") 
                this.values = v;
              else
                return v;
            });
			// rename availability colors range
			for(var i = 0; i < amonths.length; i++) {
				if(amonths[i]['key']=="0-5")amonths[i]['key']="[0-5[";
				if(amonths[i]['key']=="5-75")amonths[i]['key']="[5-75[";
				if(amonths[i]['key']=="75-95")amonths[i]['key']="[75-95[";
				if(amonths[i]['key']=="95-99")amonths[i]['key']="[95-99[";
				if(amonths[i]['key']=="99-100")amonths[i]['key']="[99-100]";
			}
			//amonths = JSON.parse(JSON.stringify(amonths).replace("\"0\-5\":", "\"[0-5[\":"));
			
            //PERFORMANCE
            mongoDBProvider.getCollection('performance_widget', function(error, coll) {
              coll.find({}).sort({"date_calculated": -1}).limit(1).toArray(function(err, docs) {
                //var avgASKCold = (docs[0].median_ASK_cold / 1000) % 60;
                //var avgJOINCold = (docs[0].median_JOIN_cold / 1000) % 60;
                //var avgASKWarm = (docs[0].median_ASK_warm /1000) % 60;
                //var avgJOINWarm = (docs[0].median_JOIN_warm / 1000) % 60;
                var avgASKCold = (docs[0] / 1000) % 60;
                var avgJOINCold = (docs[0] / 1000) % 60;
                var avgASKWarm = (docs[0] /1000) % 60;
                var avgJOINWarm = (docs[0] / 1000) % 60;

                // get the discoverability stats
                mongoDBProvider.getDiscoView( function(error,docs){
                  //var lastUpdate=0;
                  var nbEndpointsVoID=0;
                  var nbEndpointsSD=0;
                  var nbEndpointsServerName=0;
                  var nbEndpointsTotal=0;
                  var nbEndpointsNoDesc=0;
                  for (i in docs){
                    nbEndpointsTotal++;
                    //if(docs[i].lastUpdate>lastUpdate) lastUpdate=docs[i].lastUpdate;
                    if(docs[i].VoID==true)nbEndpointsVoID++;
                    if(docs[i].SD==true)nbEndpointsSD++;
                    if(docs[i].VoID!=true && docs[i].SD!=true)nbEndpointsNoDesc++;;
                    if(docs[i].serverName.length>0&&docs[i].serverName!="missing") nbEndpointsServerName++;
                  }
                  res.render('content/index.jade',{
                    configInstanceTitle: configApp.get('configInstanceTitle'),
                    amonths: amonths,
                    index:index,
                    indexInterop:indexInterop,
                    nbEndpointsSearch: nbEndpointsSearch,
					nbEndpointsVoID: nbEndpointsVoID,
					nbEndpointsSD: nbEndpointsSD,
					nbEndpointsServerName: nbEndpointsServerName,
					nbEndpointsTotal: nbEndpointsTotal,
					nbEndpointsNoDesc: nbEndpointsNoDesc,
                    lastUpdate: lastUpdate[0].lastUpdate,
                    perf: {"threshold":10000 /*mostCommonThreshold[0]*/,"data":[{"key": "Cold Tests","color": "#1f77b4","values": [{"label" : "Median ASK" ,"value" : avgASKCold },{"label" : "Median JOIN" ,"value" : avgJOINCold}]},{"key": "Warm Tests","color": "#2ca02c","values": [{"label" : "Median ASK" ,"value" : avgASKWarm} ,{"label" : "Median JOIN" ,"value" : avgJOINWarm}]}]},
                    configInterop: JSON.parse(fs.readFileSync('./texts/interoperability.json')),
                    configPerformance: JSON.parse(fs.readFileSync('./texts/performance.json')),
                    configDisco: JSON.parse(fs.readFileSync('./texts/discoverability.json')),
					configAvailability: JSON.parse(fs.readFileSync('./texts/availability.json'))
                  });
/*
                res.render('content/index.jade',{
                  configInstanceTitle: configApp.get('configInstanceTitle'),
                  amonths: amonths,
                  index:index,
                  indexInterop:indexInterop,
                  nbEndpointsSearch: nbEndpointsSearch,
                  lastUpdate: lastUpdate[0].lastUpdate,
                  perf: {"threshold":10000 ,"data":[{"key": "Cold Tests","color": "#1f77b4","values": [{"label" : "Median ASK" ,"value" : avgASKCold },{"label" : "Median JOIN" ,"value" : avgJOINCold}]},{"key": "Warm Tests","color": "#2ca02c","values": [{"label" : "Median ASK" ,"value" : avgASKWarm} ,{"label" : "Median JOIN" ,"value" : avgJOINWarm}]}]},
                  configInterop: JSON.parse(fs.readFileSync('./texts/interoperability.json')),
                  configPerformance: JSON.parse(fs.readFileSync('./texts/performance.json')),
                  configDisco: JSON.parse(fs.readFileSync('./texts/discoverability.json')),
				  configAvailability: JSON.parse(fs.readFileSync('./texts/availability.json'))
*/
                });
              });
            });
          });
				});
			});
		});
});

app.get('/api/endpointsAutoComplete', function(req, res){
		mongoDBProvider.autocomplete(req.param('q'), function(error,docs){
			//for(i in docs)console.log(docs[i].uri);
			if(docs){
				res.json(docs);
			}
			else res.end();
		});
});

app.get('/api/endpoint/list', function(req, res){
                mongoDBProvider.endpointsList(function(error,docs){
                        //for(i in docs)console.log(docs[i].uri);
                        if(docs){
                                res.header("Content-Type", "application/json; charset=utf-8");
                                res.json(docs);
                        }
                        else res.end();
                });
});

app.get('/api/endpoint/autocomplete', function(req, res){
                mongoDBProvider.autocomplete(req.param('q'), function(error,docs){
                        //for(i in docs)console.log(docs[i].uri);
                        if(docs){
                                res.json(docs);
                        }
                        else res.end();
                });
});

app.get('/api/endpoint/info', function(req, res){
  var uri = req.query.uri;
               mongoDBProvider.getEndpointView(uri, function(error,docs){
                        if(docs){
                                res.json(docs);
                        }
                        else res.end();
                });
});

app.get('/api/availability', function(req, res){
               mongoDBProvider.getAvailView(function(error,docs){
                        if(docs){
                                res.json(docs);
                        }
                        else res.end();
                });
});
app.get('/api/discoverability', function(req, res){
               mongoDBProvider.getDiscoView(function(error,docs){
                        if(docs){
                                res.json(docs);
                        }
                        else res.end();
                });
});
app.get('/api/performance', function(req, res){
               mongoDBProvider.getPerfView(function(error,docs){
                        if(docs){
                                res.json(docs);
                        }
                        else res.end();
                });
});
app.get('/api/interoperability', function(req, res){
               mongoDBProvider.getInteropView(function(error,docs){
                        if(docs){
                                res.json(docs);
                        }
                        else res.end();
                });
});


app.get('/endpoint', function(req, res){
		var uri = req.query.uri;
                var ep = JSON.parse(fs.readFileSync('./examples/endpoint.json'));
                //console.log(req.param('uri'))
                mongoDBProvider.endpointsCount(function(error,nbEndpointsSearch){
                //TODO deal with no URI
                        console.log(uri);
                        mongoDBProvider.getEndpointView(uri, function(error,docs){
				mongoDBProvider.getCollection('endpoints', function(error, collection) {
					collection.find({ "uri": uri })
					.toArray(function(err, results) {
            // get last 10 performance median runs
            mongoDBProvider.getLastTenPerformanceMedian(uri, function(error, lastTenObj) {
              var perfParsed = JSON.parse(JSON.stringify(docs[0].performance), function(k, v) {
                if (k === "data")
                  this.values = v;
                else
                  return v;
              });
              // do ASK COLD
              for(var y=0; y<perfParsed.ask[0].values.length; y++) {
                var o = perfParsed.ask[0].values[y];
                o.value = lastTenObj[ 'ASK' + o.label.toUpperCase() + '_cold' ];
              }
              // do ASK WARM
              for(var y=0; y<perfParsed.ask[1].values.length; y++) {
                var o = perfParsed.ask[1].values[y];
                o.value = lastTenObj[ 'ASK' + o.label.toUpperCase() + '_warm' ];
              }
              // do JOIN COLD
              for(var y=0; y<perfParsed.join[0].values.length; y++) {
                var o = perfParsed.join[0].values[y];
                o.value = lastTenObj[ 'JOIN' + o.label.toUpperCase() + '_cold' ];
              }
              // do JOIN WARM
              for(var y=0; y<perfParsed.join[1].values.length; y++) {
                var o = perfParsed.join[1].values[y];
                o.value = lastTenObj[ 'JOIN' + o.label.toUpperCase() + '_warm' ];
              }
              for(var i in docs[0].availability.data.values){
                if(docs[0].availability.data.values[i].x == 1421625600000){
                  docs[0].availability.data.values.splice(i,1);
                  break;
                }
              }

              mongoDBProvider.getLatestDisco(uri, function(error, latestDisco) {
                var SDDescription = [{
                  label: 'foo',
                  value: true
                }];
                var SDDescription = [];
                var descriptionFiles = latestDisco[0].descriptionFiles;
                for(var i=0; i<descriptionFiles.length; i++) {
                  var d = descriptionFiles[i];
                  var name = d.Operation;
                  if(name == 'EPURL') name = 'HTTP Get';
                  if(name == 'wellknown') name = '/.well-known/void';
                  var preds = false;
                  // check if SPARQLDESCpreds object is empty or not
                  if(Object.keys(d.SPARQLDESCpreds).length) {
                    preds = true;
                  }
                  SDDescription.push({
                    label: name,
                    value: preds
                  })
                }


                docs[0].discoverability.SDDescription = SDDescription;

                res.render('content/endpoint.jade',{
                  ep: ep,
                  nbEndpointsSearch:nbEndpointsSearch,
                  lastUpdate: uri,
                  configInterop: JSON.parse(fs.readFileSync('./texts/interoperability.json')),
                  configPerf: JSON.parse(fs.readFileSync('./texts/performance.json')),
                  configDisco: JSON.parse(fs.readFileSync('./texts/discoverability.json')),
                  epUri: uri,
                  epDetails: /*docs[0].endpoint*/ results[0],
                  epPerf: perfParsed,
                  epAvail: docs[0].availability,
                  epInterop: docs[0].interoperability,
                  epDisco: docs[0].discoverability
                });

              });

            });
					})
				})

                        });
                });
});



app.get('/fix-encoding', function(req, res){
	mongoDBProvider.getCollection('endpoints', function(error, coll) {
		coll.find({}).toArray(function(err, endpoints) {
			mongoDBProvider.getCollection('atasks_agg', function(error, taskColl) {
				for(var i in endpoints) {
					var endpoint = endpoints[i];
					taskColl.update({ "endpoint.uri": endpoint.uri }, { $set :  { "endpoint.datasets" : endpoint.datasets } }, function(err, result) {
					})
				}
			})

			mongoDBProvider.getCollection('dtasks_agg', function(error, taskColl) {
				for(var i in endpoints) {
					var endpoint = endpoints[i];
					taskColl.update({ "endpoint.uri": endpoint.uri }, { $set :  { "endpoint.datasets" : endpoint.datasets } }, function(err, result) {
					})
				}
			})

			mongoDBProvider.getCollection('ptasks_agg', function(error, taskColl) {
				for(var i in endpoints) {
					var endpoint = endpoints[i];
					taskColl.update({ "endpoint.uri": endpoint.uri }, { $set :  { "endpoint.datasets" : endpoint.datasets } }, function(err, result) {
					})
				}
			})
		})
	})
})
app.get('/availability', function(req, res){
		mongoDBProvider.endpointsCount(function(error,nbEndpointsSearch){
			mongoDBProvider.getAvailView( function(error,docs){
				var lastUpdate=0;
				var nbEndpointsUp=0;
console.log(error);
				for (i in docs){
					if(docs[i].upNow==true) nbEndpointsUp++;
					if(docs[i].lastUpdate>lastUpdate)lastUpdate=docs[i].lastUpdate;
				}
				res.render('content/availability.jade',{
					lastUpdate: new Date(lastUpdate).toUTCString(),
					nbEndpointsSearch:nbEndpointsSearch,
					atasks_agg: docs,
					nbEndpointsUp:nbEndpointsUp,
					nbEndpointsTotal:docs.length
					});
			});
		});
});

app.get('/discoverability', function(req, res){
		mongoDBProvider.endpointsCount(function(error,nbEndpointsSearch){
			mongoDBProvider.getDiscoView( function(error,docs){
				var lastUpdate=0;
				var nbEndpointsVoID=0;
				var nbEndpointsSD=0;
				var nbEndpointsServerName=0;
				var nbEndpointsTotal=0;
				for (i in docs){
					nbEndpointsTotal++;
					if(docs[i].lastUpdate>lastUpdate) lastUpdate=docs[i].lastUpdate;
					if(docs[i].VoID==true)nbEndpointsVoID++;
					if(docs[i].SD==true)nbEndpointsSD++;
					if(docs[i].serverName.length>0&&docs[i].serverName!="missing") nbEndpointsServerName++;
				}
				res.render('content/discoverability.jade',{
					lastUpdate: new Date(lastUpdate).toUTCString(),
					nbEndpointsSearch:nbEndpointsSearch,
					nbEndpointsVoID: nbEndpointsVoID,
					nbEndpointsSD: nbEndpointsSD,
					nbEndpointsServerName: nbEndpointsServerName,
					nbEndpointsTotal: nbEndpointsTotal,
					dtasks_agg: docs,
					configDisco: JSON.parse(fs.readFileSync('./texts/discoverability.json'))
					});
			});
		});
});

app.get('/performance', function(req, res){
		mongoDBProvider.endpointsCount(function(error,nbEndpointsSearch){
			mongoDBProvider.getPerfView( function(error,docs){
				var lastUpdate=0;
				var nbEndpointsWithThreshold=0;
				var nbEndpointsTotal=0;
				var thresholds=[];
				for (i in docs){
					if(docs[i].lastUpdate>lastUpdate) lastUpdate=docs[i].lastUpdate;
					if(docs[i].threshold>0 && docs[i].threshold%100==0){
						nbEndpointsWithThreshold++;
						if(thresholds[docs[i].threshold])thresholds[docs[i].threshold]++;
						else thresholds[docs[i].threshold]=1;
					}
					if(docs[i].askMeanCold+docs[i].joinMeanCold>0) nbEndpointsTotal++;
				}
				var mostCommonThreshold = [0,0];
				for (i in thresholds){
					if(thresholds[i]>mostCommonThreshold[1]){
						mostCommonThreshold[0]=i;
						mostCommonThreshold[1]=thresholds[i];
					}
				}
				//console.log(mostCommonThreshold);
				res.render('content/performance.jade',{
					lastUpdate: new Date(lastUpdate).toUTCString(),
					nbEndpointsSearch:nbEndpointsSearch,
					configPerformance: JSON.parse(fs.readFileSync('./texts/performance.json')),
					ptasks_agg: docs,
					nbEndpointsWithThreshold: nbEndpointsWithThreshold,
					nbEndpointsTotal: nbEndpointsTotal,
					mostCommonThreshold: mostCommonThreshold[0]
					});
			});
		});
});

app.get('/interoperability', function(req, res){
		mongoDBProvider.endpointsCount(function(error,nbEndpointsSearch){
			var nbSPARQL1Features=24;
			var nbSPARQL11Features=18;
			mongoDBProvider.getInteropView( function(error,docs){
				var lastUpdate=0;
				var nbCompliantSPARQL1Features=0;
				var nbFullCompliantSPARQL1Features=0;
				var nbCompliantSPARQL11Features=0;
				var nbEndpointsTotal=0;
				var nbFullCompliantSPARQL11Features=0;
				for (i in docs){
					if(docs[i].nbCompliantSPARQL1Features+docs[i].nbCompliantSPARQL11Features>0)nbEndpointsTotal++;
					if(docs[i].nbCompliantSPARQL1Features>0){
						nbCompliantSPARQL1Features++;
						if(docs[i].nbCompliantSPARQL1Features==nbSPARQL1Features)nbFullCompliantSPARQL1Features++;
					}
					if(docs[i].nbCompliantSPARQL11Features>0){
						nbCompliantSPARQL11Features++;
						if(docs[i].nbCompliantSPARQL11Features==nbSPARQL11Features)nbFullCompliantSPARQL11Features++;
					}
					if(docs[i].lastUpdate>lastUpdate)lastUpdate=docs[i].lastUpdate;
				}
				//console.log(nbCompliantSPARQL1Features+' - '+nbFullCompliantSPARQL1Features+' - '+nbCompliantSPARQL11Features+' - '+nbFullCompliantSPARQL11Features);
				res.render('content/interoperability.jade',{
				lastUpdate: new Date(lastUpdate).toUTCString(),
				nbEndpointsSearch: nbEndpointsSearch,
				configInterop: JSON.parse(fs.readFileSync('./texts/interoperability.json')),
				nbSPARQL1Features: nbSPARQL1Features,
				nbSPARQL11Features: nbSPARQL11Features,
				nbCompliantSPARQL1Features: nbCompliantSPARQL1Features,
				nbFullCompliantSPARQL1Features: nbFullCompliantSPARQL1Features,
				nbCompliantSPARQL11Features: nbCompliantSPARQL11Features,
				nbFullCompliantSPARQL11Features: nbFullCompliantSPARQL11Features,
				ftasks_agg: docs,
				nbEndpointsTotal: nbEndpointsTotal
				});
			});
		});
});

app.get('/data', function(req, res){
  mongoDBProvider.endpointsCount(function(error,nbEndpointsSearch){
    var dir = '../dumps/'; // data dir
    function bytesToSize(bytes) {
       if(bytes == 0) return '0 Byte';
       var k = 1000;
       var sizes = ['Bytes', 'KB', 'MB', 'GB', 'TB', 'PB', 'EB', 'ZB', 'YB'];
       var i = Math.floor(Math.log(bytes) / Math.log(k));
       return (bytes / Math.pow(k, i)).toPrecision(3) + ' ' + sizes[i];
    }
    var files = fs.readdirSync(dir)
                .map(function(v) { 
                    return { name:v,
                             time:fs.statSync(dir + v).mtime.getTime(),
                             size:bytesToSize(fs.statSync(dir + v).size)
                           }; 
                 });
    res.render('content/data.jade',{files:files, nbEndpointsSearch:nbEndpointsSearch});
  });
});

app.get('/iswc2013', function(req, res){
		mongoDBProvider.endpointsCount(function(error,nbEndpointsSearch){
			mongoDBProvider.autocomplete(req.param('q'), function(error,docs){
				res.render('content/iswc2013.jade',{nbEndpointsSearch:nbEndpointsSearch});
			});
		});
});

app.get('/api', function(req,res){
                mongoDBProvider.endpointsCount(function(error,nbEndpointsSearch){
                        mongoDBProvider.autocomplete(req.param('q'), function(error,docs){
                                res.render('content/api.jade',{nbEndpointsSearch:nbEndpointsSearch});
                        });
                });

});


http.createServer(app).listen(app.get('port'), function(){
  console.log('Express server listening on port ' + app.get('port'));
});