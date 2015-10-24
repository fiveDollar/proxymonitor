package com.windoor.mailUtil;

import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MailHtml {

 /**
   * @param args
  */
 public static void mail(String tcontent,String email_address,String title) {
   String tto=email_address;
   String ttitle=title;
//   String tcontent="<table border='1' bgColor='red'><tr><td>发送一个表格</td></tr></table>";//HTML代码
   Properties props=new Properties();
   props.put("mail.smtp.host","smtp.163.com");
   props.put("mail.smtp.auth","true");
   Session s=Session.getInstance(props);
   s.setDebug(true);

   MimeMessage message=new MimeMessage(s);

//   给消息对象设置发件人/收件人/主题/发信时间
  try{
   InternetAddress from=new InternetAddress("zhangxian887@163.com");
   message.setFrom(from);
   InternetAddress to=new InternetAddress(tto);
   message.setRecipient(Message.RecipientType.TO,to);
   message.setSubject(ttitle);
   message.setSentDate(new Date());

//   给消息对象设置内容
   BodyPart mdp=new MimeBodyPart();//新建一个存放信件内容的BodyPart对象
   mdp.setContent(tcontent,"text/html;charset=gb2312");//给BodyPart对象设置内容和格式/编码方式
   Multipart mm=new MimeMultipart();//新建一个MimeMultipart对象用来存放BodyPart对
//   象(事实上可以存放多个)
   mm.addBodyPart(mdp);//将BodyPart加入到MimeMultipart对象中(可以加入多个BodyPart)
   message.setContent(mm);//把mm作为消息对象的内容

   message.saveChanges();
   Transport transport=s.getTransport("smtp");
   transport.connect("smtp.163.com","zhangxian887@163.com","519754821");
   transport.sendMessage(message,message.getAllRecipients());
   transport.close();
   System.out.println("发送成功!");
   }catch (Exception e) {
    e.printStackTrace();
   // TODO: handle exception
   }

 }
 
 public static void mailf(String fileName,String email_address,String title) {
	   String tto=email_address;
	   String ttitle=title;
	//   String tcontent="<table border='1' bgColor='red'><tr><td>发送一个表格</td></tr></table>";//HTML代码
	   Properties props=new Properties();
	   props.put("mail.smtp.host","smtp.163.com");
	   props.put("mail.smtp.auth","true");
	   Session s=Session.getInstance(props);
	   s.setDebug(true);

	   MimeMessage message=new MimeMessage(s);

	//   给消息对象设置发件人/收件人/主题/发信时间
	  try{
	   InternetAddress from=new InternetAddress("zhangxian887@163.com");
	   message.setFrom(from);
	   InternetAddress to=new InternetAddress(tto);
	   message.setRecipient(Message.RecipientType.TO,to);
	   message.setSubject(ttitle);
	   message.setSentDate(new Date());

	//   给消息对象设置内容
	   
	   Multipart mm=new MimeMultipart();//新建一个MimeMultipart对象用来存放BodyPart对
	//   象(事实上可以存放多个)
		   BodyPart mdp=new MimeBodyPart();//新建一个存放信件内容的BodyPart对象
		   mdp.setFileName(fileName);
		   mm.addBodyPart(mdp);//将BodyPart加入到MimeMultipart对象中(可以加入多个BodyPart)
	   message.setContent(mm);//把mm作为消息对象的内容

	   message.saveChanges();
	   Transport transport=s.getTransport("smtp");
	   transport.connect("smtp.163.com","zhangxian887@163.com","519754821");
	   transport.sendMessage(message,message.getAllRecipients());
	   transport.close();
	   System.out.println("发送成功!");
	   }catch (Exception e) {
	    e.printStackTrace();
	   // TODO: handle exception
	   }

	 }

}