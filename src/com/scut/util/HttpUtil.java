package com.scut.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

public class HttpUtil {
	// 服务端的url地址
	private static final String url = "http://192.168.1.100:8080/xxfb";
	// 创建一个HttpClient连接
	private static HttpClient httpCilent;
	// 创建一个HttpResponse用于存放响应的数据
	private static HttpResponse response;
	// 创建一个HttpPost请求
	private static HttpPost httpPost;
	// 创建一个HttpEntity用于存放请求的实体数据
	private static HttpEntity entity;

	public static String eXchangeDataByHttp(JSONObject data, String urlString) {
		httpCilent = new DefaultHttpClient();
		StringBuffer sb = new StringBuffer();
		try {
			// 设置请求的路径
			httpPost = new HttpPost(url + urlString);
			System.out.println(url + urlString);
			// 创建一个用户，用于向服务端发送数据时，存放的实体
			// 设置请求体
			/* httpPost.setEntity(new StringEntity(data.toString())); */
			httpPost.setEntity(new StringEntity(data.toString(), "utf-8"));
			// 执行请求获取响应
			response = httpCilent.execute(httpPost);
			System.out.println(response);
			// 如果响应的状态码为200时，表示请求响应成功
			while (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				// 获取响应的实体数据
				entity = response.getEntity();

				// 通过reader读取实体对象包含的数据
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(entity.getContent()));
				// 循环读取实体里面的数据
				String s = null;
				while ((s = reader.readLine()) != null) {
					sb.append(s);
				}
			}
		} catch (Exception e) {
		}

		return sb.toString();
	}

}
