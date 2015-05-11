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
	// ����˵�url��ַ
	private static final String url = "http://192.168.1.100:8080/xxfb";
	// ����һ��HttpClient����
	private static HttpClient httpCilent;
	// ����һ��HttpResponse���ڴ����Ӧ������
	private static HttpResponse response;
	// ����һ��HttpPost����
	private static HttpPost httpPost;
	// ����һ��HttpEntity���ڴ�������ʵ������
	private static HttpEntity entity;

	public static String eXchangeDataByHttp(JSONObject data, String urlString) {
		httpCilent = new DefaultHttpClient();
		StringBuffer sb = new StringBuffer();
		try {
			// ���������·��
			httpPost = new HttpPost(url + urlString);
			System.out.println(url + urlString);
			// ����һ���û������������˷�������ʱ����ŵ�ʵ��
			// ����������
			/* httpPost.setEntity(new StringEntity(data.toString())); */
			httpPost.setEntity(new StringEntity(data.toString(), "utf-8"));
			// ִ�������ȡ��Ӧ
			response = httpCilent.execute(httpPost);
			System.out.println(response);
			// �����Ӧ��״̬��Ϊ200ʱ����ʾ������Ӧ�ɹ�
			while (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				// ��ȡ��Ӧ��ʵ������
				entity = response.getEntity();

				// ͨ��reader��ȡʵ��������������
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(entity.getContent()));
				// ѭ����ȡʵ�����������
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
