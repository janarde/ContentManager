package com.mobile.grazie.csv;

import java.io.File;
import java.io.IOException;

import org.apache.http.entity.FileEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.util.EntityUtils;



public class Poster {

	private String url;
	private FileEntity reqEntity;
	private HttpClient httpclient;
	private HttpPost post;
	
	
	public Poster(String url, File file) {
	
		httpclient = new DefaultHttpClient();
		httpclient.getParams().setBooleanParameter(CoreProtocolPNames.USE_EXPECT_CONTINUE, false);
		post = new HttpPost(url);
		
		reqEntity = new FileEntity(file, "text/plain");
		reqEntity.setContentType("text/plain");
		post.setEntity(reqEntity);
		
	}
	
	public void doRequest() {
		HttpResponse response;
		try {
			response = httpclient.execute(post);
			if (response.getEntity() != null) {
				System.out.println(EntityUtils.toString(response.getEntity()));
			}
			if (response.getEntity() != null) {
				response.getEntity().consumeContent();
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		httpclient.getConnectionManager().shutdown();
	}
}
