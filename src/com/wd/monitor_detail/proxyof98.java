package com.wd.monitor_detail;

import java.util.ArrayList;

import com.windoor.database.RemoteDatabaseforall;

public class proxyof98 {
	RemoteDatabaseforall rdb;
	String host;
	public proxyof98(String host,String userName,String password,String database) {
		this.host = host;
		rdb = new RemoteDatabaseforall(host, database, userName, password);
	}
	public String hour(){
		ArrayList<Object[]> maxAndmin=null;
		int a1=0;
		while(maxAndmin==null){
			a1++;
			if(a1>1){
				return null;
			}
			maxAndmin=rdb.selectall("select max(id),min(id) from check_result where TIMESTAMPDIFF(HOUR,`import_date`,NOW()) <1;");
		}
		if(maxAndmin!=null&&maxAndmin.size()>0){
			int max = Integer.parseInt(maxAndmin.get(0)[0].toString());
 			int min = Integer.parseInt(maxAndmin.get(0)[1].toString());
			ArrayList<Object[]> maxcount =null; 
			a1 = 0;
			while(maxcount==null){
				a1++;
				if(a1>1){
					return null;
				}
				maxcount =rdb.selectall("select `available`,`check`,`all`,`is_used` from check_result where `id`="+max+";");
			}
			ArrayList<Object[]> mincount =null;
			a1=0;
			while(mincount==null){
				a1++;
				if(a1>1){
					return null;
				}
				mincount =rdb.selectall("select `available`,`check`,`all` from check_result where `id`="+min+";");
			}
			ArrayList<Object[]> list =null; 
			a1=0;
			while(list==null){
				a1++;
				if(a1>1){
					return null;
				}
				list = rdb.selectall("select `available`,`check`,`all` from check_result where `id`>="+min+" and `id`<="+max+" and `is_used`=1;");
			}
			int sum_avilable =0;
			int sum_check = 0;
			int sum_all =0;
			for(int i=0;i<list.size();i++){
				sum_avilable += Integer.parseInt(list.get(i)[0].toString());
				sum_check += Integer.parseInt(list.get(i)[1].toString());
				sum_all += Integer.parseInt(list.get(i)[2].toString());
			}
			if(maxcount!=null&&maxcount.size()>0){
				if(maxcount.get(0)[3].toString().equals("0")){
					sum_avilable += Integer.parseInt(maxcount.get(0)[0].toString());
					sum_check += Integer.parseInt(maxcount.get(0)[1].toString());
					sum_all += Integer.parseInt(maxcount.get(0)[2].toString());
				}
			}
			if(mincount!=null&&mincount.size()>0){
				sum_avilable -=Integer.parseInt(mincount.get(0)[0].toString());
				sum_check -=Integer.parseInt(mincount.get(0)[1].toString());;
				sum_all -= Integer.parseInt(mincount.get(0)[2].toString());	
			}
			
			String content = "<br/><p>"+host+"前一个小时的总量</p><table border='1'><tr><td>可用总</td><td>检查代理量</td><td>总量</td></tr>"
					+ "<tr><td>"+sum_avilable+"</td><td>"+sum_check+"</td><td>"+sum_all+"</td></tr></table>";
			return content;
		}
		return null;
	}
	public String day(){
		ArrayList<Object[]> maxAndmin=null;
		int a1= 0;
		while(maxAndmin==null){
			a1++;
			if(a1>1){
				return null;
			}
			maxAndmin=rdb.selectall("select max(id),min(id) from check_result where TIMESTAMPDIFF(DAY,DATE_FORMAT(`import_date`,'%Y-%m-%d'),DATE_FORMAT(NOW(),'%Y-%m-%d')) <1;");
		}
		if(maxAndmin!=null&&maxAndmin.size()>0){
			int max = Integer.parseInt(maxAndmin.get(0)[0].toString());
 			int min = Integer.parseInt(maxAndmin.get(0)[1].toString());
 			long distinct_all = (long) rdb.selectall("select count(distinct ip,port) from check_proxy where `import_date`>date_format(now(),'%Y-%m-%d');").get(0)[0];
 			long distinct_ip_all = (long) rdb.selectall("select count(distinct ip) from check_proxy where `import_date`>date_format(now(),'%Y-%m-%d');").get(0)[0];
 			ArrayList<Object[]> maxcount =null; 
 			a1 = 0;
 			while(maxcount==null){
 				a1++;
 				if(a1>1){
 					return null;
 				}
 				maxcount =rdb.selectall("select `available`,`check`,`all`,`is_used` from check_result where `id`="+max+";");
			}
			ArrayList<Object[]> mincount =null;
			a1 =0;
			while(mincount==null){
				a1++;
				if(a1>1){
					return null;
				}
				mincount =rdb.selectall("select `available`,`check`,`all` from check_result where `id`="+min+";");
			}
			ArrayList<Object[]> list =null;
			a1=0;
			while(list==null){
				a1++;
				if(a1>1){
					return null;
				}
				list = rdb.selectall("select `available`,`check`,`all` from check_result where `id`>="+min+" and `id`<="+max+" and `is_used`=1;");
			}
			int sum_avilable =0;
			int sum_check = 0;
			int sum_all =0;
			for(int i=0;i<list.size();i++){
				sum_avilable += Integer.parseInt(list.get(i)[0].toString());
				sum_check += Integer.parseInt(list.get(i)[1].toString());
				sum_all += Integer.parseInt(list.get(i)[2].toString());
			}
			if(maxcount!=null&&maxcount.size()>0){
				if(maxcount.get(0)[3].toString().equals("0")){
					sum_avilable += Integer.parseInt(maxcount.get(0)[0].toString());
					sum_check += Integer.parseInt(maxcount.get(0)[1].toString());
					sum_all += Integer.parseInt(maxcount.get(0)[2].toString());
				}
			}
			if(mincount!=null&&mincount.size()>0){
				sum_avilable -=Integer.parseInt(mincount.get(0)[0].toString());
				sum_check -=Integer.parseInt(mincount.get(0)[1].toString());;
				sum_all -= Integer.parseInt(mincount.get(0)[2].toString());	
			}
			
			String content = "<br/><p>"+host+"一天内目前为止的总量</p><table border='1'><tr><td>可用总</td><td>检查代理量</td><td>总量</td><td>distinct_ip&port</td><td>distinctIP</td></tr>"
					+ "<tr><td>"+sum_avilable+"</td><td>"+sum_check+"</td><td>"+sum_all+"</td><td>"+distinct_all+"</td><td>"+distinct_ip_all+"</td></tr></table>";
			return content;
		}
		return null;
	}
}
