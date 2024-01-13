package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.AdminBEAN;
import common.DBConncet;

public class AdminDAO {
	private PreparedStatement preP = null;
	private ResultSet rs = null;
	
	public boolean isAdminExist(String email) throws Exception {
		String sql = "select Email from Admin where email = ?";
		
		DBConncet.GetConnect();
		
		preP = DBConncet.cn.prepareStatement(sql);
		preP.setString(1, email);
		
		rs = preP.executeQuery();
		
		while(rs.next()) {
			preP.close();
			rs.close();
			DBConncet.cn.close();
			return true;
		}
		
		preP.close();
		rs.close();
		DBConncet.cn.close();
		return false;
	}
	
	public AdminBEAN getAdmin(String email, String password) throws Exception {
		String sql = "select * from Admin where email = ? and MatKhau = ?";
		AdminBEAN admin = null;
		
		DBConncet.GetConnect();
		
		preP = DBConncet.cn.prepareStatement(sql);
		preP.setString(1, email);
		preP.setString(2, password);
		
		rs = preP.executeQuery();
		
		while(rs.next()) {
			 int id = rs.getInt("idAdmin");
			 String em = rs.getString("Email");
			 String matKhau = rs.getString("MatKhau");
			 String hoTen = rs.getString("HoTen");
			 admin = new AdminBEAN(id, email, matKhau, hoTen);
		}
		
		preP.close();
		rs.close();
		DBConncet.cn.close();
		return admin;
	}

}
