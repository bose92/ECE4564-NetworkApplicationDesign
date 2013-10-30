package edu.vt.ece4564.project2client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.lang.Object;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;

import android.os.AsyncTask;
import android.util.Log;

public class NetworkTask extends AsyncTask<String, Void, String> {
	String userReview;
	MainActivity mainact_;
	static String setIP = "172.31.175.100";
	static String setPort = "8080";

	public NetworkTask(MainActivity main) {
		mainact_ = main;
	}

	@Override
	protected String doInBackground(String... params) {
		if (!(params[0].equals("")) && !(params[1].equals(""))) {
			setIP = params[0];
			setPort = params[1];
			String takeData = null;
			return takeData;
		}
		if (params[2].equals("getreview")) {
			try {
				HttpClient httpclient_ = new DefaultHttpClient();
				HttpGet httpget_ = new HttpGet("http://" + setIP + ":"
						+ setPort + "/review");
				HttpResponse response_ = httpclient_.execute(httpget_);
				HttpEntity entity_ = response_.getEntity();
				userReview = EntityUtils.toString(entity_);

			} catch (IOException e) {
			}
			String takeData = userReview;
			return takeData;
		}

		if (params[2].equals("postreview")) {
			HttpClient httpclient_ = new DefaultHttpClient();
			HttpGet httpget_ = new HttpGet("http://" + setIP + ":" + setPort
					+ "/review" + "?" + params[3]);
			try {
				HttpResponse response = httpclient_.execute(httpget_);

			} catch (ClientProtocolException e) {
			} catch (IOException e) {
			}

			String takeData = null;
			return takeData;
		} else {
			String takeData = "Parameter Not Valid";
			return takeData;
		}
	}

	@Override
	protected void onPostExecute(String para_result) {
		mainact_.setReview(para_result);
	}
}