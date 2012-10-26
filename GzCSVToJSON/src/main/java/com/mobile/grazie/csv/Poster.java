package com.mobile.grazie.csv;

import java.io.File;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.List;

import org.apache.http.entity.FileEntity;
import org.apache.http.HttpResponse;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.ClientProtocolException;

import org.apache.http.impl.client.DefaultHttpClient;

import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.util.EntityUtils;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mobile.grazie.csv.JSONWriter;

/**
 * This class takes care of the HTTP stuff needed to get the JSON to the server
 * @author Jesse_Anarde
 *
 */
public class Poster {

	final Logger logger = LoggerFactory.getLogger(Poster.class);
	
	private FileEntity reqEntity;
	private HttpClient httpclient;
	private HttpPost post;
	private String json;
		
	public Poster(String url, String json, String username, String password) {
	
		this.json = json;
		String encoding = new String(Base64.encodeBase64(StringUtils.getBytesUtf8(username+":"+password)));
		httpclient = new DefaultHttpClient();
		httpclient.getParams().setBooleanParameter(CoreProtocolPNames.USE_EXPECT_CONTINUE, false);
		post = new HttpPost(url);
		post.setHeader("Authorization", "Basic " + encoding);
			
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

			logger.debug(sb.toString());

			br.close();

		} catch (IOException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}

	}
	public void doRequest() {
		HttpResponse response;
		List<File> fileList = new JSONWriter(json).convert();
		
		for (File file : fileList) {
			logger.debug("file = " + file.getAbsolutePath());
			reqEntity = new FileEntity(file);
			reqEntity.setContentType("application/json");
			post.setEntity(reqEntity);
			
			try {
				response = httpclient.execute(post);
				if (response.getEntity() != null) {
					logger.info("received http status : " + (response.getStatusLine().getStatusCode()));
					EntityUtils.consume(response.getEntity());
				} else {
					logger.debug("response entity was null for some reason");
					EntityUtils.consume(response.getEntity());
				}
				
			} catch (ClientProtocolException e) {
				logger.error(e.getMessage());
				e.printStackTrace();
			} catch (IOException e) {
				logger.error(e.getMessage());
				e.printStackTrace();
			}
			
			try {
				printEntity(post.getEntity().getContent());
			} catch (IOException e) {
				logger.error(e.getMessage());
				e.printStackTrace();
			}
			
			file.deleteOnExit();
			
		}
		
		
		
		httpclient.getConnectionManager().shutdown();
	}
}
