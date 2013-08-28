package ru.kr1soft.listennteachenglish;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import android.os.AsyncTask;
import android.util.Log;

public class Translater {

	private final String apiKeyValue = "trnsl.1.1.20130827T113845Z.bb12d61e4ebe9193.c6f4919aa06706053786c67bec2b6d71bd165c92";
	private final String apiKeyFieldName = "key";
	
	private final String langValue = "en-ru";
	private final String langFieldName = "lang";
	
	private final String textFieldName = "text"; 
	
	private final String urlRequest = "https://translate.yandex.net/api/v1.5/tr/translate";
	
	
	public String GetTranslate(String word){
		try{
						
			HttpResponse resp = new SendPostRequestTask().execute(word).get();
			HttpEntity r_entity = resp.getEntity();
			String responseBody = EntityUtils.toString(r_entity); 
			return responseBody;
		}
		catch (ExecutionException ex) {
			Log.w("ExecutionException", ex.getMessage());
			return "Ошибко";
		}
		catch (InterruptedException iex){
			Log.w("InterruptedException", iex.getMessage());
			return "Ошибко";
		}
		catch (IOException ioex){
			Log.w("IOException", ioex.getMessage());
			return "Ошибко";
		}
	}
	
	class SendPostRequestTask extends AsyncTask<String, Void, HttpResponse>{

		@Override
		protected HttpResponse doInBackground(String... params) {
			
			
			try {
				HttpClient httpclient = new DefaultHttpClient();
				HttpPost post = new HttpPost(urlRequest);

				List<NameValuePair> p = new ArrayList<NameValuePair>();
				p.add(new BasicNameValuePair(apiKeyFieldName, apiKeyValue));
				p.add(new BasicNameValuePair(langFieldName, langValue));
				p.add(new BasicNameValuePair(textFieldName, params[0]));
				post.setEntity(new UrlEncodedFormEntity(p));
				return httpclient.execute(post);
			} catch (ClientProtocolException e) {

				e.printStackTrace();
			} catch (IOException e) {

				e.printStackTrace();
			}
			
			return null;

		}
		
	}
	
}


