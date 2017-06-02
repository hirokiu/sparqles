package sparqles.utils;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.SingleClientConnManager;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sparqles.core.CONSTANTS;
import sparqles.avro.Dataset;
import sparqles.avro.Endpoint;
import sparqles.core.EndpointFactory;
import sparqles.core.SPARQLESProperties;

//http://datahub.io/api/2/search/resource?format=api/sparql&all_fields=1&limit=1000

public class DatahubAccess {
	private static final Logger log = LoggerFactory.getLogger(DatahubAccess.class);



	/**
	 * This class fetch the SPARQL endpoint list from datahub using the datahub API 
	 * @param epm 
	 **/
	public static Collection<Endpoint> checkEndpointList(){
		Map<String, Endpoint> results = new HashMap<String, Endpoint>();
		try {
			// Do not do this in production!!!
			HostnameVerifier hostnameVerifier = org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER;

			DefaultHttpClient client = new DefaultHttpClient();

			SchemeRegistry registry = new SchemeRegistry();
			SSLSocketFactory socketFactory = SSLSocketFactory.getSocketFactory();
			socketFactory.setHostnameVerifier((X509HostnameVerifier) hostnameVerifier);
			registry.register(new Scheme("https", socketFactory, 443));
			SingleClientConnManager mgr = new SingleClientConnManager(client.getParams(), registry);
			DefaultHttpClient httpclient = new DefaultHttpClient(mgr, client.getParams());

			// Set verifier     
			HttpsURLConnection.setDefaultHostnameVerifier(hostnameVerifier);
			String apiURL="https://datahub.io/api/2/search/resource?format=api/sparql&all_fields=1&limit=1000";
			apiURL="https://datahub.io/api/3/action/resource_search?query=format:api/sparql";
			HttpGet getRequest = new HttpGet(apiURL);
			
			getRequest.addHeader("User-Agent", CONSTANTS.USER_AGENT);
			
			
			HttpResponse response = httpclient.execute(getRequest);
			
			if (response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatusLine().getStatusCode());
			}
			HttpEntity entity = response.getEntity();
			String respString = EntityUtils.toString(entity,ContentType.getOrDefault(entity).getCharset());
			
			JsonFactory factory = new JsonFactory();
			ObjectMapper mapper = new ObjectMapper(factory);
			JsonNode rootNode = mapper.readTree(respString);  

			JsonNode res = rootNode.get("result");
			res = res.get("results");
			log.info("We found {} datasets",res.size());
			Iterator<JsonNode> iter = res.getElements();
			int c=1;
			
			
			Map<String,Set<String>> map = new HashMap<String, Set<String>>();
			while(iter.hasNext()){
				JsonNode node = iter.next();
				String endpointURL = node.findPath("url").getTextValue().trim();
				String datasetId = node.findPath("package_id").getTextValue().trim();
				
				Set<String> s = map.get(endpointURL);
				if(s==null){
					s= new HashSet<String>();
					map.put(endpointURL, s);
				}
				s.add(datasetId);
			}
			for(Entry<String,Set<String>> ent: map.entrySet()){
				String endpointURL = ent.getKey(); 

				if(endpointURL.length()==0) continue;
				
				Endpoint ep = results.get(endpointURL);
				if(ep == null){
					try {
						ep = EndpointFactory.newEndpoint(new URI(endpointURL));
						List<Dataset> l = new ArrayList<Dataset>();
						ep.setDatasets(l);
						results.put(endpointURL, ep);
					} catch (URISyntaxException e) {
						log.warn("URISyntaxException:{}",e.getMessage());
					}
				}
				if(ent.getValue().size()!=0){
					for(String ds : ent.getValue()){
						ep = checkForDataset(ep,ds,httpclient );
						log.info("Found dataset information for {}", ep);
					}
				}else{
					System.err.println("This should not happend for ep"+ep);
				}
				log.info("[GET] [{}] {}",c++,ep);
			}
//			httpClient.getConnectionManager().shutdown();
		} catch (Exception e2) {
			log.warn("[EXEC] {}",e2);
			e2.printStackTrace();
		} 
		log.info("Found {} endpoints",results.size());
		
		return results.values();
	}

	private static Endpoint checkForDataset(Endpoint ep, String datasetId, HttpClient httpClient){
		log.debug("[GET] dataset info for {} and {}", datasetId,ep);
		HttpGet getRequest = null;
		try {
			getRequest = new HttpGet("https://datahub.io/api/3/action/package_show?id="+datasetId);
			getRequest.addHeader("User-Agent", CONSTANTS.USER_AGENT);
			System.out.println(getRequest);
			HttpResponse response = httpClient.execute(getRequest);
			if (response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatusLine().getStatusCode());
			}
			String respString = EntityUtils.toString(response.getEntity());
//			response.close();
			
			JsonFactory factory = new JsonFactory();
			ObjectMapper mapper = new ObjectMapper(factory);
			JsonNode rootNode = mapper.readTree(respString);
			JsonNode res = rootNode.get("result");

//			System.out.println(rootNode);
			String ckan_url = res.findPath("url").getTextValue();
			List<JsonNode> titles  = res.findValues("title");
			String title = null;
			for(JsonNode s : titles){
//				System.out.println(s);
				if(!s.toString().contains("Linking Open"))
					title = s.asText();
				if (title !=null && title.length() > 200){
					title = title.substring(0,200);
				}
			}
					
			Dataset d = new Dataset();
			d.setLabel(title);
			d.setUri(ckan_url);
			List<Dataset> l =  ep.getDatasets();
			l.add(d);

			return ep;

		} catch (Exception e) {
			log.warn("[EXEC] "+ep,e);
		} 
		return ep;
	}


	/*
	 * Local Endpoint List
	 */
	public static Collection<Endpoint> checkEndpointListFile(){
		Map<String, Endpoint> results = new HashMap<String, Endpoint>();
		try {
			String endpointListFile = SPARQLESProperties.getENDPOINT_LIST();
			FileInputStream fs = new FileInputStream(endpointListFile);
			BufferedReader br = new BufferedReader(new InputStreamReader(fs));
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			br.close();
			
			JsonFactory factory = new JsonFactory();
			ObjectMapper mapper = new ObjectMapper(factory);
			JsonNode rootNode = mapper.readTree(sb.toString());  

			JsonNode res = rootNode.get("results");
			//res = res.get("results");
			log.info("We found {} datasets",res.size());
			Iterator<JsonNode> iter = res.getElements();
			int c=1;
			
			
			Map<String,Set<String>> map = new HashMap<String, Set<String>>();
			while(iter.hasNext()){
				JsonNode node = iter.next();
				String endpointURL = node.findPath("url").getTextValue().trim();
				String datasetId = node.findPath("package_id").getTextValue().trim();
				
				Set<String> s = map.get(endpointURL);
				if(s==null){
					s= new HashSet<String>();
					map.put(endpointURL, s);
				}
				s.add(datasetId);
			}
			for(Entry<String,Set<String>> ent: map.entrySet()){
				String endpointURL = ent.getKey(); 

				if(endpointURL.length()==0) continue;
				
				Endpoint ep = results.get(endpointURL);
				if(ep == null){
					try {
						ep = EndpointFactory.newEndpoint(new URI(endpointURL));
						List<Dataset> l = new ArrayList<Dataset>();
						ep.setDatasets(l);
						results.put(endpointURL, ep);
					} catch (URISyntaxException e) {
						log.warn("URISyntaxException:{}",e.getMessage());
					}
				}
				log.info("[GET] [{}] {}",c++,ep);
			}
		} catch (Exception e2) {
			log.warn("[EXEC] {}",e2);
			e2.printStackTrace();
		} 
		log.info("Found {} endpoints",results.size());
		
		return results.values();
	}
}
