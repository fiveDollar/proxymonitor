package com.windoor.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

//import org.traffic.rank4.Rank;

public class RemoteDatabaseforall {
	// public static String jdbcDriver = "com.mysql.jdbc.Driver";
	// public static String jdbcurl = "jdbc:mysql://42.121.194.66/floor2";
	// public static String userName = "remote";
	// public static String password = "remote2013";
//	public static String jdbcDriver = "com.mysql.jdbc.Driver";
////	public static String jdbcurl = "jdbc:mysql://222.92.117.87:3306/_monitor?user=root&password=Iknowthat&useUnicode=true&&characterEncoding=utf-8&autoReconnect = true";
//	public static String jdbcurl = "jdbc:mysql://222.92.117.87/_monitor";
//	public static String userName = "root";
//	//jdbc:mysql://222.92.117.87/_monitor
//	public static String password = "Iknowthat";
//	 public static String jdbcDriver = "com.mysql.jdbc.Driver";
//	 public static String jdbcurl = "jdbc:mysql://61.129.51.118/Tmall";
//	 public static String userName = "Remote221";
//	 public static String password = "Iknowthat";
	 public String jdbcDriver = "com.mysql.jdbc.Driver";
		 public  String jdbcurl = "";
		 public  String userName = "remote";
		 public  String password = "Iknowthat";
	public RemoteDatabaseforall(String host,String database,String userName,String password) {
		this.jdbcurl = "jdbc:mysql://"+host+"/"+database;
		this.userName = userName;
		this.password = password;
	}
	
	public  Connection createConnection() {
		Connection conn = null;
		try {
			Class.forName(jdbcDriver);
			conn = DriverManager.getConnection(jdbcurl, userName, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static void close(Statement st, Connection conn) {
		try {
			if (st != null)
				st.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void close(ResultSet rs, Statement st, Connection conn) {
		try {
			if (rs != null)
				rs.close();
			if (st != null)
				st.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * return the article ID.
	 * 
	 * @TODO 
	 *       锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷诨锟矫伙拷锟斤拷锟斤拷锟斤拷锟侥拷锟斤拷锟紺rawlerParameter
	 *       .ARTICLE_CATEGORY_ID
	 * @param title
	 * @param fulltext
	 * @param introText
	 * @return
	 */

	private String getDate() {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");// 锟斤拷锟斤拷锟斤拷锟节革拷式
		return df.format(new Date());
	}
	public ArrayList<Object[]> selectall(String sql) {
		ArrayList<Object[]> data = new ArrayList<>();
		Connection conn = createConnection();
		Statement statment = null;
		ResultSet rst =null;
		try {
			statment = conn.createStatement();
			statment.setQueryTimeout(300);
			rst = statment.executeQuery(sql);
			ResultSetMetaData rstmd = rst.getMetaData();
			
			// 列数
			int count = rstmd.getColumnCount();
			// System.out.println(count);
			while (rst.next()) {
				Object[] row = new Object[count];
				for (int i = 0; i < count; i++) {
					row[i] = rst.getObject(i + 1);
				}
				data.add(row);
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
			return null;
		}catch(Exception e1){
			return null;
		}
		finally {
			close(rst,statment, conn);
		}
		return data;
	}
	
	public void select(String sql) {
		ArrayList<Object[]> data = new ArrayList<>();
		Connection conn = createConnection();
		Statement statment = null;
		try {
			
			statment = conn.createStatement();
			statment.setQueryTimeout(300);
			ResultSet rst = statment.executeQuery(sql);
		}catch (SQLException e) {
			e.printStackTrace();
		}catch(Exception e1){
		}
		finally {
		}
	}
	public void inserAll(String SQLtemp) {
		// keywords.get(0);
		Connection conn = createConnection();
		Statement statment = null;
		try {
			statment = conn.createStatement();
			statment.executeUpdate(SQLtemp);
		} catch (SQLException e) {
			System.out.println(SQLtemp);
			e.printStackTrace();
		} finally {
			close(statment, conn);
		}
	}


	public void executeBatch(List<String> sqlList) throws SQLException {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			con = createConnection();
			st = con.createStatement();
			for (String s : sqlList) {
				st.addBatch(s);
			}
			st.executeBatch();
		} catch (SQLException e) {
			throw e;
		} finally {
			close(rs, st, con);
		}
	}
}
