package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConncet {
	
	public static Connection cn;
	
	public static void GetConnect() throws Exception{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String url="jdbc:sqlserver://LAPTOP-4MV5DK6J\\SQLEXPRESS:1433;databaseName=NMShop;user=sa; password=1";
		cn= DriverManager.getConnection(url);
		System.out.println("DA KET NOI");
	}
	
	public static void closeAllConnection(PreparedStatement pre, ResultSet rs) throws SQLException {
		pre.close();
		rs.close();
		DBConncet.cn.close();
	}
	
	public static void closeAllConnection(ResultSet rs) throws SQLException {
		rs.close();
		DBConncet.cn.close();
	}
	
}
