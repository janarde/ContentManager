package com.mobile.grazie.csv;

import java.io.File;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


import org.apache.http.entity.FileEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.util.EntityUtils;


import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;



public class Poster {

	private FileEntity reqEntity;
	private HttpClient httpclient;
	private HttpPost post;
	
	
	public Poster(String url, File file, String username, String password) {
	
		String encoding = new String(Base64.encodeBase64(StringUtils.getBytesUtf8(username+":"+password)));
		httpclient = new DefaultHttpClient();
		httpclient.getParams().setBooleanParameter(CoreProtocolPNames.USE_EXPECT_CONTINUE, false);
		post = new HttpPost(url);
		post.setHeader("Authorization", "Basic " + encoding);
		
		reqEntity = new FileEntity(file);
		reqEntity.setContentType("application/json");
		post.setEntity(reqEntity);
		
		try {
			printEntity(post.getEntity().getContent());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	private void printEntity(InputStream is) {
		// read it with BufferedReader
		BufferedReader br = new BufferedReader(new InputStreamReader(is));

		StringBuilder sb = new StringBuilder();

		String line;
		try {
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}

			System.out.println(sb.toString());

			br.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	public void doRequest() {
		HttpResponse response;
		try {
			response = httpclient.execute(post);
			if (response.getEntity() != null) {
				System.out.println("Got here");
				System.out.println(response.getStatusLine().getStatusCode());
			} else {
				System.out.println("Damnit");
			}
			
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		httpclient.getConnectionManager().shutdown();
	}
}
