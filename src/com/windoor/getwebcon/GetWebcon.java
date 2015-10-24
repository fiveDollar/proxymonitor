package com.windoor.getwebcon;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class GetWebcon {
	int timeout = 1000*10;
	
	public String getwebcon(String url) {
		HttpURLConnection	connection=null;
		ByteArrayOutputStream	bos=null;
		InputStream in = null;
		try {
			connection = (HttpURLConnection)new URL(url).openConnection();
			connection.setReadTimeout(timeout);
			connection.setConnectTimeout(timeout);
			in = connection.getInputStream();
			byte[] temp = new byte[1024 * 1024];
			bos = new ByteArrayOutputStream();
			int size = in.read(temp);
			while (size > 0) {
				bos.write(temp, 0, size);
				try {
					size = in.read(temp);
				} catch (Exception e) {
					size = 0;
				}
			}
			String webcon = bos.toString("utf-8");
			
			return webcon;
		} catch (MalformedURLException e1) {
			return "chaoshi";
		} catch (IOException e) {
			e.printStackTrace();
			return "chaoshi";
		} finally {
			try {
				if(in!=null){
					in.close();
				}
				if(bos!=null){
					bos.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(connection!=null){
				connection.disconnect();
			}
		}
	
	}
	
	

}
