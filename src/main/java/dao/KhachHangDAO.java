package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.KhachHangBean;
import common.DBConncet;

public class KhachHangDAO {
	private PreparedStatement preP = null;
	private ResultSet rs = null;
	
	public boolean checkEmailExist(String email) throws Exception {
		boolean result = false;
		
		String sql = "select * from KhachHang where email = ?";
		
		DBConncet.GetConnect();
		
		preP = DBConncet.cn.prepareStatement(sql);
		preP.setString(1, email);
		
		rs = preP.executeQuery();
		while(rs.next()) {
			DBConncet.closeAllConnection(preP, rs);
			return true;
		}
		
		DBConncet.closeAllConnection(preP, rs);
		return false;
	}
	
	public void insert(KhachHangBean kh) throws SQLException, Exception {
		String sql = "insert into KhachHang(TenKhachHang, email, matkhau, sodienthoai) values(?,?,?,?)";
		
		DBConncet.GetConnect();
		
		preP = DBConncet.cn.prepareStatement(sql);
		
		preP.setString(1, kh.getTen_Khach_Hang() );
		preP.setString(2, kh.getEmail());
		preP.setString(3, kh.getMatKhau());
		preP.setString(4, kh.getSoDienThoai());
		
		preP.executeUpdate();
		preP.close();
		DBConncet.cn.close();
	}
	
	public KhachHangBean isAccountExist(String email, String matKhau) throws SQLException, Exception {
		String sql = "select * from KhachHang where email = ? and MatKhau = ?";
		KhachHangBean kh = null;
		
		DBConncet.GetConnect();
		
		preP = DBConncet.cn.prepareStatement(sql);
		
		preP.setString(1, email );
		preP.setString(2, matKhau);
		rs = preP.executeQuery();
		
		while(rs.next()) {
			int ma_Khach_hang = rs.getInt("Ma_Khach_Hang");
			 String ten_Khach_Hang = rs.getString("TenKhachHang");
			 String e = rs.getString("email");
			 String soDienThoai = rs.getString("SoDienThoai");
			 kh = new KhachHangBean(ma_Khach_hang, ten_Khach_Hang, email, soDienThoai);
	
		}
		preP.close();
		DBConncet.cn.close();
		return kh;
	}
	
	public static void main(String[] args) throws Exception {
		KhachHangDAO khDAO = new KhachHangDAO();
		System.out.println(khDAO.checkEmailExist("20t1020461ư@husc.edu.vn"));
	}
 
}
