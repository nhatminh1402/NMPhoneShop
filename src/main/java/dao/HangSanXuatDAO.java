package dao;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import bean.HangSanXuatBEAN;
import common.DBConncet;

public class HangSanXuatDAO {
	private PreparedStatement preP = null;
	private ResultSet rs = null;

	public ArrayList<HangSanXuatBEAN> getAll() throws Exception {
		ArrayList<HangSanXuatBEAN> list = new ArrayList<HangSanXuatBEAN>();
		DBConncet.GetConnect();

		String sqlQuery = "select * from Hang_San_Xuat";

		preP = DBConncet.cn.prepareStatement(sqlQuery);

		rs = preP.executeQuery();
		while (rs.next()) {
			int id_hang = rs.getInt("id_hang");
			String hang_san_xuat = rs.getString("hang_san_xuat");
			list.add(new HangSanXuatBEAN(id_hang, hang_san_xuat));
		}

		preP.close();
		rs.close();
		DBConncet.cn.close();
		return list;
	}

	// Hàm nhằm giúp lấy ra tổng số page mà hệ thống có được
	public int getTotalPage(int rowPerPage) throws Exception {
		// Kết nối đến Database
		DBConncet.GetConnect();
		int total = 0;

		CallableStatement cs = DBConncet.cn.prepareCall("{call proc_Hang_San_Xuat_CaculatePageNumber(?)}");
		cs.setInt(1, rowPerPage);

		rs = cs.executeQuery();

		while (rs.next()) {
			total = rs.getInt(1);
		}

		cs.close();
		rs.close();
		DBConncet.cn.close();
		return total;
	}

	// Hàm nhằm giúp lấy ra dữ liệu của trang cần xem
	public ArrayList<HangSanXuatBEAN> getListPaginate(int pageSize, int pageNumber) throws Exception {
		// Kết nối đến Database
		DBConncet.GetConnect();
		ArrayList<HangSanXuatBEAN> ds = new ArrayList<HangSanXuatBEAN>();

		CallableStatement cs = DBConncet.cn.prepareCall("{call proc_Hang_San_Xuat_Paginate( ?,? ) }");
		cs.setInt(1, pageSize);
		cs.setInt(2, pageNumber);

		rs = cs.executeQuery();

		while (rs.next()) {
			int id_hang = rs.getInt("id_hang");
			String hang_san_xuat = rs.getString("hang_san_xuat");
			ds.add(new HangSanXuatBEAN(id_hang, hang_san_xuat));
		}

		cs.close();
		rs.close();
		DBConncet.cn.close();
		return ds;
	}

	public boolean isBrandExist(String hangSanXuat) throws Exception {
		// Kết nối đến Database
		DBConncet.GetConnect();

		String sqlQuery = "select * from Hang_San_Xuat where LOWER(hang_san_xuat) = LOWER(?)";
		preP = DBConncet.cn.prepareStatement(sqlQuery);
		preP.setString(1, hangSanXuat);
		rs = preP.executeQuery();

		while (rs.next()) {
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

	public void insert(String HangSanXuat) throws Exception {
		String sqlQuery = "insert into Hang_San_Xuat (hang_san_xuat) values(?)";

		DBConncet.GetConnect();

		preP = DBConncet.cn.prepareStatement(sqlQuery);
		preP.setString(1, HangSanXuat);

		preP.executeUpdate();
		
		preP.close();
		DBConncet.cn.close();
	}

	public void deleteById(int id) throws Exception {
		String sqlQuery = "delete from Hang_San_Xuat where id_hang = ?";

		DBConncet.GetConnect();

		preP = DBConncet.cn.prepareStatement(sqlQuery);
		preP.setInt(1, id);

		preP.executeUpdate();
		preP.close();
		DBConncet.cn.close();
	}
	
	public void updateById(int id, String ten_hang) throws Exception {
		String sqlQuery = "update Hang_San_Xuat set hang_san_xuat = ? where id_hang = ?";

		DBConncet.GetConnect();

		preP = DBConncet.cn.prepareStatement(sqlQuery);
		preP.setString(1, ten_hang);
		preP.setInt(2, id);

		preP.executeUpdate();
		preP.close();
		DBConncet.cn.close();
	}

}
