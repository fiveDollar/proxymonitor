package com.windoor.timing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.wd.monitor_detail.proxyof98;
import com.windoor.mailUtil.MailHtml;

public class Proxy114 {
	public static void main(String[] args) {

		int dc = 3;
		while (true) {
			int y, m, d, h;
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.HOUR_OF_DAY, -1);
			y = cal.get(Calendar.YEAR);
			m = cal.get(Calendar.MONTH)+1 ;
			d = cal.get(Calendar.DATE);
			h = cal.get(Calendar.HOUR_OF_DAY);
			
			System.out.println(dc+"   "+h);
			if (dc !=h) {
				dc =h;
				String ddate = y + "-" + m + "-" + d + " " + h;
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH");
				Date dd = null;
				try {
					dd = sdf.parse(ddate);
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				InputStream in;
				InputStreamReader ir;
				BufferedReader buf;
				
				try {
					in = new FileInputStream(new File("etc/monitor.csv"));
					ir = new InputStreamReader(in,"GBK");
					buf = new BufferedReader(ir);
					String line="";
					String s = "";
					while((line=buf.readLine())!=null){
						if(line.contains("//")){
							continue;
						}
//						System.out.println(line);
						proxyof98 p = new proxyof98(line.split(",")[0],line.split(",")[1], line.split(",")[2], "wd_proxy");
						try{
							s += p.hour();
							s += p.day();
						}catch(Exception e){
						}
					}
//					System.out.println("s"+s);
					MailHtml.mail(s, "zhangxian887@qq.com", sdf.format(dd)+"代理监控");
//					MailHtml.mail(s, "feng.zhu@szwindoor.com", sdf.format(dd)+"代理监控");
//					MailHtml.mail(s, "joseph@szwindoor.com", sdf.format(dd)+"代理监控");
//					MailHtml.mail(s, "zhuming.song@szwindoor.com", sdf.format(dd)+"代理监控");
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
			try {
				Thread.sleep(1000 * 60 * 2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
