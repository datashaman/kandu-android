package com.inomma.kandu.server;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import org.apache.http.Header;

import android.os.Environment;

import com.inomma.SharedPreferencesHelper;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.builder.Builders.Any.B;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

/**
 * Android AsyncTask implementation for sending asynchronous requests
 * 
 * @author Narek Gevorgyan
 * 
 */
@SuppressWarnings("rawtypes")
public class MainSender {

	public static MainSender instance = new MainSender();
	public static String BASE_URL;

	public String token;

	private MainSender() {
	}

	public void execute(final Request<? extends Response> request) {
		if (BASE_URL == null) {
			BASE_URL = SharedPreferencesHelper
					.getStringData("server_url", null);
		}
		Map<String, Object> params = request.getParams();
		if (token == null) {
			token = SharedPreferencesHelper.getStringData("token", null);
		}
		boolean hasFile;
		B ion;
		switch (request.getMethod()) {
		case POST:
			hasFile = hasFile(params);
			ion = Ion
					.with(request.getContext(), BASE_URL + request.getAction());

			if (token != null) {
				ion.setHeader("Authorization", "Token " + token);
			}

			for (String key : params.keySet()) {
				Object value = params.get(key);
				if (value instanceof String) {
					if (hasFile) {
						ion.setMultipartParameter(key, (String) value);
					} else {
						ion.setBodyParameter(key, (String) value);

					}
				} else if (value instanceof File) {
					ion.setMultipartFile(key, (File) value);
				} else if (value instanceof Integer) {
					if (hasFile) {
						ion.setMultipartParameter(key, (Integer) value + "");
					} else {
						ion.setBodyParameter(key, (Integer) value + "");

					}
				}

			}
			ion.asString().setCallback(new FutureCallback<String>() {

				@Override
				public void onCompleted(Exception exc, String response) {
					if (exc == null)
						request.handle(response);

				}
			});
			break;
		case PUT:
			hasFile = hasFile(params);

			ion = Ion.with(request.getContext()).load("PUT",
					BASE_URL + request.getAction());
			if (token != null) {
				ion.setHeader("Authorization", "Token " + token);
			}

			for (String key : params.keySet()) {
				Object value = params.get(key);
				if (value instanceof String) {
					if (hasFile) {
						ion.setMultipartParameter(key, (String) value);
					} else {
						ion.setBodyParameter(key, (String) value);

					}
				} else if (value instanceof File) {
					ion.setMultipartFile(key, (File) value);
				} else if (value instanceof Integer) {
					if (hasFile) {
						ion.setMultipartParameter(key, (Integer) value + "");
					} else {
						ion.setBodyParameter(key, (Integer) value + "");

					}
				}

			}
			ion.asString().setCallback(new FutureCallback<String>() {

				@Override
				public void onCompleted(Exception exc, String response) {
					if (exc == null)
						request.handle(response);

				}
			});
			break;
		case GET:
			B b = Ion.with(request.getContext()).load(
					BASE_URL + request.getAction());
			if (token != null) {
				b.setHeader("Authorization", "Token " + token);
			}
			b.asString().setCallback(new FutureCallback<String>() {

				@Override
				public void onCompleted(Exception exc, String response) {
					if (exc == null)
						request.handle(response);

				}
			});
			break;
		default:
			break;
		}

	}

	public void execute1(final Request<? extends Response> request) {
		if (BASE_URL == null) {
			BASE_URL = SharedPreferencesHelper
					.getStringData("server_url", null);
		}
		Map<String, Object> params = request.getParams();
		if (token == null) {
			token = SharedPreferencesHelper.getStringData("token", null);
		}
		String fullPath = BASE_URL + request.getAction();
		AsyncHttpClient client = new AsyncHttpClient();
		if (token != null)
			client.addHeader("Authorization", "Token " + token);
           		RequestParams requestParams = new RequestParams();
		for (String key : params.keySet()) {
			if (params.get(key) instanceof File) {
				File file = (File) params.get(key);
				try {
					requestParams.put(key, file);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				
				file.exists();
				continue;
			}
			requestParams.put(key, params.get(key));
		}
           		switch (request.getMethod()) { 
		case POST:

			client.post(request.getContext(), fullPath, requestParams, 
					new TextHttpResponseHandler() {

						@Override
						public void onSuccess(int arg0, Header[] arg1,
								String response) {
 							request.handle(response);

						}
 
						@Override
						public void onFailure(int arg0, Header[] arg1,
								String response, Throwable arg3) {
							request.handle(response);

						}
					});
			break;
		case PUT:

			client.put(request.getContext(), fullPath, requestParams,
					new TextHttpResponseHandler() {

						@Override
						public void onSuccess(int arg0, Header[] arg1,
								String response) {
							request.handle(response);

						}

						@Override
						public void onFailure(int arg0, Header[] arg1,
								String response, Throwable arg3) {
							request.handle(response);

						}
					});
			break;
		case GET:
			B b = Ion.with(request.getContext()).load(fullPath);
			if (token != null) {
				b.setHeader("Authorization", "Token " + token);
			}
			b.asString().setCallback(new FutureCallback<String>() {

				@Override
				public void onCompleted(Exception exc, String response) {
					if (exc == null)
						request.handle(response);

				}
			});
			break;
		default:
			break;
		}

	}

	private boolean hasFile(Map<String, Object> map) {
		for (Object value : map.values()) {
			if (value instanceof File) {
				return true;
			}
		}
		return false;
	}
}