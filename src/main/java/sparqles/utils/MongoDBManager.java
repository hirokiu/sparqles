package sparqles.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.apache.avro.Schema;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.io.JsonDecoder;
import org.apache.avro.io.JsonEncoder;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;
import org.apache.avro.specific.SpecificRecordBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sparqles.analytics.avro.AvailabilityView;
import sparqles.analytics.avro.EPView;
import sparqles.core.Endpoint;
import sparqles.core.SPARQLESProperties;
import sparqles.core.availability.AResult;
import sparqles.core.discovery.DResult;
import sparqles.core.features.FResult;
import sparqles.core.performance.PResult;
import sparqles.schedule.Schedule;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.MongoException.DuplicateKey;
import com.mongodb.QueryBuilder;
import com.mongodb.WriteConcern;
import com.mongodb.WriteResult;
import com.mongodb.util.JSON;



public class MongoDBManager {
	private static final Logger log = LoggerFactory.getLogger(MongoDBManager.class);

	private MongoClient client;
	private DB db;

	
	private final static String RESULT_KEY="endpointResult.endpoint.uri";
	private final static String VIEW_KEY="endpoint.uri";
	private final static String COLL_SCHED="schedule";

	private final static String COLL_AVAIL="atasks";
	private final static String COLL_PERF="ptasks";
	private final static String COLL_DISC="dtasks";
	private final static String COLL_FEAT="ftasks";
	private final static String COLL_ENDS="endpoints";
	private final static String COLL_INDEX="index";

	private final static String COLL_AVAIL_AGG="atasks_agg";
	private final static String COLL_PERF_AGG="ptasks_agg";
	private final static String COLL_DISC_AGG="dtasks_agg";
	private final static String COLL_FEAT_AGG="ftasks_agg";
	private final static String COLL_EP_VIEW="epview";

	public MongoDBManager()  {
		setup();
	}

	public boolean isRunning() {
		if(client == null || db == null) return false;
		return true;
	}

	private void setup()  {
		try {
			client = new MongoClient( SPARQLESProperties.getDB_HOST() , SPARQLESProperties.getDB_PORT() );
			log.info("[INIT] MongoDB {} ", client);
			db = client.getDB( SPARQLESProperties.getDB_NAME() );
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
	
	public void initEndpointCollection() {
		DBCollection c = db.getCollection(COLL_ENDS);
		c.drop();
		DBObject d = new BasicDBObject("uri", 1);
		c.ensureIndex(d, new BasicDBObject("unique", true));
	}

	public void initScheduleCollection() {
		DBCollection c = db.getCollection(COLL_SCHED);
		c.drop();
		c.ensureIndex(new BasicDBObject("endpoint.uri", 1), new BasicDBObject("unique", true));
	}
	
	public void initAggregateCollections() {
		String []cols = {COLL_AVAIL_AGG, COLL_DISC_AGG, COLL_FEAT_AGG, COLL_FEAT_AGG, COLL_EP_VIEW};
		for(String col: cols){
			DBCollection c = db.getCollection(col);
			c.drop();
//			c.ensureIndex(new BasicDBObject("endpoint.uri", 1), new BasicDBObject("unique", true));	
		}
	}


	

	public <V extends SpecificRecordBase> boolean insert(V res) {
		if(res instanceof DResult) return insert(COLL_DISC, res, res.getSchema() );
		if(res instanceof AResult) return insert(COLL_AVAIL, res, res.getSchema() );
		if(res instanceof PResult) return insert(COLL_PERF, res, res.getSchema() );
		if(res instanceof FResult) return insert(COLL_FEAT, res, res.getSchema() );
		if(res instanceof Endpoint) return insert(COLL_ENDS, res, res.getSchema() );
		if(res instanceof Schedule) return insert(COLL_SCHED, res, res.getSchema() );
		if(res instanceof AvailabilityView) return insert(COLL_AVAIL_AGG, res, res.getSchema() );
		if(res instanceof EPView) return insert(COLL_EP_VIEW, res, res.getSchema() );
		return false;
	}
	
	
	
	private boolean insert(String collName, Object e, Schema schema){
		DBCollection c = db.getCollection(collName);
		try{

			DBObject dbObject = getObject(e, schema);
			WriteResult wr = c.insert(dbObject,WriteConcern.FSYNC_SAFE);
			if(wr.getError()!=null){
				System.out.println("error");
			}else{
				log.info("[INSERT] [SUCC] {}:{}",collName,e.toString());
			}

			return true;
		}catch(DuplicateKey ex){
			log.info("[INSERT] [DUPLICATE] uri key");
			return true;
		}catch(MongoException ex){
			log.warn("[INSERT] [EXEC] {}",ex);
		}catch(Exception exx){
			log.warn("[INSERT] [EXEC] {}",exx);
		}
		return false;
	}

	
	public <V extends SpecificRecordBase> boolean update(V res){
		if(res instanceof AvailabilityView) return update(COLL_AVAIL_AGG, ((AvailabilityView) res).getEndpoint(),res, res.getSchema(),VIEW_KEY );
		if(res instanceof EPView) return update(COLL_EP_VIEW, ((EPView) res).getEndpoint(), res, res.getSchema(),VIEW_KEY );
		return false;
	}
	
	private boolean update(String collName, Endpoint ep, Object e, Schema schema, String key){
		DBCollection c = db.getCollection(collName);
		try{
			DBObject dbObject = getObject(e, schema);
			BasicDBObject q = new BasicDBObject();
			q.append(key, ep.getUri().toString());
			
			WriteResult wr = c.update(q, dbObject);
			System.out.println(wr.toString());
			if(wr.getError()!=null){
				System.out.println("error");
			}else{
				log.info("[UPDATE] [SUCC] {}:{}",collName,e.toString());
			}

			return true;
		}catch(DuplicateKey ex){
			log.info("[UPDATE] [DUPLICATE] uri key");
			return true;
		}catch(MongoException ex){
			log.warn("[EXEC] {}",ex);
		}catch(Exception exx){
			log.warn("[EXEC] {}",exx);
		}
		return false;
	}
		
	
	
	public <V extends SpecificRecordBase> List<V> get(Class<V> cls, Schema schema) {
		return getResults(null, cls, schema);
	}

	
	public <T> List<T> getResults(Endpoint ep, Class<T> cls, Schema schema) {
		if(cls.getName().equals(DResult.class.getName())) return scan(ep,COLL_DISC, cls,schema, RESULT_KEY);
		if(cls.getName().equals(AResult.class.getName())) return scan(ep,COLL_AVAIL,cls, schema, RESULT_KEY);
		if(cls.getName().equals(PResult.class.getName())) return scan(ep,COLL_PERF, cls,schema, RESULT_KEY);
		if(cls.getName().equals(FResult.class.getName())) return scan(ep,COLL_FEAT, cls,schema, RESULT_KEY);
		if(cls.getName().equals(Endpoint.class.getName())) return scan(ep,COLL_ENDS, cls,schema, VIEW_KEY);
		if(cls.getName().equals(Schedule.class.getName())) return scan(ep,COLL_SCHED, cls,schema, VIEW_KEY);
		
		if(cls.getName().equals(AvailabilityView.class.getName())) return scan(ep,COLL_AVAIL_AGG, cls,schema, VIEW_KEY);
		if(cls.getName().equals(EPView.class.getName())) return scan(ep,COLL_EP_VIEW, cls,schema, VIEW_KEY); 
		return null;
	}
	
	public boolean close(){
		client.close();
		return true;
	}

	private DBObject getObject(Object o, Schema s){
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		JsonEncoder e;
		try {
			e = EncoderFactory.get().jsonEncoder(s, baos);
			SpecificDatumWriter w = new SpecificDatumWriter(o.getClass());
			w.write(o, e);
			e.flush();
			DBObject dbObject = (DBObject)JSON.parse(baos.toString());
			return dbObject;

		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return null;
	}

	private <T> List<T> scan(Endpoint ep,String colName, Class<T> cls, Schema schema, String key) {
		ArrayList<T> reslist = new ArrayList<T>();	

		DBCollection c  = db.getCollection(colName);
		DBCursor curs = null;
		if(ep==null){
			curs = c.find();
		}else{
			BasicDBObject q = new BasicDBObject();
			q.append(key, ep.getUri().toString());
			curs = c.find(q);
		}
		
		while(curs.hasNext()){
			DBObject o = curs.next();
			SpecificDatumReader r = new SpecificDatumReader<T>(cls);
			JsonDecoder d;
			try {
				d = DecoderFactory.get().jsonDecoder(schema, o.toString());
				T t =(T) r.read(null, d);
				reslist.add(t);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return reslist;
	}

	public <T extends SpecificRecordBase> List<T> getResultsSince(Endpoint ep, Class<T> cls,
			Schema schema, long since) {
		
		ArrayList<T> reslist = new ArrayList<T>();	

		DBCollection c  = db.getCollection(COLL_AVAIL);
		DBCursor curs = null;
		if(ep==null){
			curs = c.find();
		}else{
			
			DBObject q =
			QueryBuilder.start().and(
					QueryBuilder.start("endpointResult.endpoint.uri").is(ep.getUri().toString()).get(),
					QueryBuilder.start("endpointResult.start").greaterThan(since).get()).get();
			log.info("[EXEC] {}",q);
			curs = c.find(q);
		}
		
		while(curs.hasNext()){
			DBObject o = curs.next();
			SpecificDatumReader r = new SpecificDatumReader<T>(cls);
			JsonDecoder d;
			try {
				d = DecoderFactory.get().jsonDecoder(schema, o.toString());
				T t =(T) r.read(null, d);
				reslist.add(t);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return reslist;
		
	}

	public <T extends SpecificRecordBase> List<T> getResultsSince(Endpoint ep, Class<T> cls,
			Schema schema, long from, long to) {
		ArrayList<T> reslist = new ArrayList<T>();	

		DBCollection c  = db.getCollection(COLL_AVAIL);
		DBCursor curs = null;
		if(ep==null){
			curs = c.find();
		}else{
			
			DBObject q =
			QueryBuilder.start().and(
					QueryBuilder.start("endpointResult.endpoint.uri").is(ep.getUri().toString()).get(),
					QueryBuilder.start("endpointResult.start").greaterThan(from).get(),
					QueryBuilder.start("endpointResult.start").lessThanEquals(to).get()).get();
			log.info("[EXEC] {}",q);
			curs = c.find(q);
		}
		
		while(curs.hasNext()){
			DBObject o = curs.next();
			SpecificDatumReader r = new SpecificDatumReader<T>(cls);
			JsonDecoder d;
			try {
				d = DecoderFactory.get().jsonDecoder(schema, o.toString());
				T t =(T) r.read(null, d);
				reslist.add(t);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return reslist;
	}

	
	

//	public <T> List<T> getResultsByDESCDate(Endpoint ep, Class<T> cls) {
//		ArrayList<T> reslist = new ArrayList<T>();
//		try {
//
//			Statement st = con.createStatement();
//			String query ="SELECT * FROM results WHERE Endpoint='"+ep.getUri().toString()+"' AND Task='"+cls.getSimpleName()+"' ORDER BY Date DESC;";
//			log.info("[QUERY] {}", query);
//
//			ResultSet res = st.executeQuery(query);
//			while(res.next()){
//				Decoder decoder = DecoderFactory.get().binaryDecoder(res.getBinaryStream(3), null);
//				DatumReader<T> dr = new SpecificDatumReader<T>(cls);
//
//				T t = dr.read(null, decoder);
//				reslist.add(t);
//			}
//		} catch (SQLException e) {
//
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return reslist;
//	}
//
//	public <T> List<T> getResultsSince(Endpoint ep, Class<T> cls,
//			Long timestamp) {
//		ArrayList<T> reslist = new ArrayList<T>();
//		try {
//			Statement st = con.createStatement();
//			String query ="SELECT * FROM results WHERE Endpoint='"+ep.getUri().toString()+"' AND Task='"+cls.getSimpleName()+"' AND Date > '"+new java.sql.Timestamp(timestamp)+"' ORDER BY Date DESC;";
//			log.info("[QUERY] {}", query);
//
//			ResultSet res = st.executeQuery(query);
//			while(res.next()){
//				Decoder decoder = DecoderFactory.get().binaryDecoder(res.getBinaryStream(3), null);
//				DatumReader<T> dr = new SpecificDatumReader<T>(cls);
//
//				T t = dr.read(null, decoder);
//				reslist.add(t);
//			}
//		} catch (SQLException e) {
//
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return reslist;
//	}
//
//	
//
//



}