package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import bean.SanPhamBEAN;
import common.DBConncet;
import common.PageManager;

public class SanPhamDAO {
	private PreparedStatement preP = null;
	private ResultSet rs = null;

	public void update(int maSP, String ten_San_pham, String he_dieu_hanh, int tong_thang_bao_hanh, int id_hang)
			throws Exception {
		String updateQuery = "UPDATE San_Pham SET " + "ten_san_pham = ?, " + "he_dieu_hanh = ?, "
				+ "tong_thang_bao_hanh = ?, id_hang = ? WHERE id_san_pham = ?";
		DBConncet.GetConnect();

		preP = DBConncet.cn.prepareStatement(updateQuery);
		preP.setString(1, ten_San_pham);
		preP.setString(2, he_dieu_hanh);
		preP.setInt(3, tong_thang_bao_hanh);
		preP.setInt(4, id_hang);
		preP.setInt(5, maSP);

		preP.executeUpdate();

		preP.close();
		DBConncet.cn.close();
	}

	public void updateDescribe(String describe, int productId) throws Exception {
		String sql = "update San_Pham set mo_ta = ? where id_san_pham = ?";

		DBConncet.GetConnect();
		preP = DBConncet.cn.prepareStatement(sql);

		preP.setString(1, describe);
		preP.setInt(2, productId);

		preP.executeUpdate();

		preP.close();
		DBConncet.cn.close();
	}

	public void insert(String ten_san_pham, String he_dieu_hanh, int id_hang, int bao_hanh) throws Exception {
		String updateQuery = "insert into San_Pham (ten_san_pham, he_dieu_hanh, tong_thang_bao_hanh, id_hang)"
				+ "values(?,?,?,?)";
		DBConncet.GetConnect();

		preP = DBConncet.cn.prepareStatement(updateQuery);
		preP.setString(1, ten_san_pham);
		preP.setString(2, he_dieu_hanh);
		preP.setInt(3, bao_hanh);
		preP.setInt(4, id_hang);

		preP.executeUpdate();

		DBConncet.closeAllConnection(rs);
	}

	public void delate(int id) throws Exception {
		String updateQuery = "delete from san_pham WHERE id_san_pham = ?";
		DBConncet.GetConnect();

		preP = DBConncet.cn.prepareStatement(updateQuery);
		preP.setInt(1, id);

		preP.executeUpdate();

		preP.close();
		DBConncet.cn.close();
	}

	public ArrayList<SanPhamBEAN> pagination(int pageNumber, int pageSize) throws Exception {
		ArrayList<SanPhamBEAN> listSP = new ArrayList<SanPhamBEAN>();
		String sqlQuery = "select HSX.hang_san_xuat, SP.ngay_nhap_hang ,SP.id_san_pham, SP.ten_san_pham, SP.he_dieu_hanh, SP.tong_thang_bao_hanh, SP.mo_ta, SP.id_hang\r\n"
				+ "from Hang_San_Xuat HSX right join San_Pham SP\r\n" + "on SP.id_hang = HSX.id_hang\r\n"
				+ "order by sp.id_san_pham desc OFFSET (?-1) * ?  rows fetch next ? rows only";
		DBConncet.GetConnect();
		preP = DBConncet.cn.prepareStatement(sqlQuery);
		preP.setInt(1, pageNumber);
		preP.setInt(2, pageSize);
		preP.setInt(3, pageSize);
		rs = preP.executeQuery();
		while (rs.next()) {
			int id_san_pham = rs.getInt("id_san_pham");
			String ten_san_pham = rs.getString("ten_san_pham");
			String he_dieu_hanh = rs.getString("he_dieu_hanh");
			int tong_thang_bao_hanh = rs.getInt("tong_thang_bao_hanh");
			String mo_ta = rs.getString("mo_ta");
			int id_hang = rs.getInt("id_hang");
			String ten_hang = rs.getString("hang_San_xuat");
			java.sql.Date d = rs.getDate("ngay_nhap_hang");
			Date ngay_nhap = new Date(d.getTime());
			listSP.add(new SanPhamBEAN(id_san_pham, ten_san_pham, he_dieu_hanh, tong_thang_bao_hanh, ngay_nhap, mo_ta,
					id_hang, ten_hang));
		}

		DBConncet.closeAllConnection(preP, rs);
		return listSP;
	}

	public SanPhamBEAN selectById(int id) throws Exception {
		SanPhamBEAN sp = null;
		String sqlQuery = "SELECT TOP (1000) [id_san_pham]\r\n" + "      ,[ten_san_pham]\r\n"
				+ "      ,[he_dieu_hanh]\r\n" + "      ,[tong_thang_bao_hanh]\r\n" + "      ,[id_hang]\r\n"
				+ "  FROM [NMShop].[dbo].[San_Pham]\r\n" + "  where id_san_pham = ? ";
		DBConncet.GetConnect();
		preP = DBConncet.cn.prepareStatement(sqlQuery);
		preP.setInt(1, id);
		rs = preP.executeQuery();
		while (rs.next()) {
			int id_san_pham = rs.getInt("id_san_pham");
			String ten_san_pham = rs.getString("ten_san_pham");
			String he_dieu_hanh = rs.getString("he_dieu_hanh");
			int tong_thang_bao_hanh = rs.getInt("tong_thang_bao_hanh");
			int id_hang = rs.getInt("id_hang");

			sp = new SanPhamBEAN(id_san_pham, ten_san_pham, he_dieu_hanh, tong_thang_bao_hanh, id_hang);
		}

		DBConncet.closeAllConnection(preP, rs);
		return sp;
	}

	public void updateDescribeProduct(int maSP, String mo_ta) throws Exception {
		String updateQuery = "UPDATE San_Pham SET mo_ta = ? WHERE id_san_pham = ?";
		DBConncet.GetConnect();

		preP = DBConncet.cn.prepareStatement(updateQuery);
		preP.setString(1, mo_ta);
		preP.setInt(2, maSP);

		preP.executeUpdate();

		preP.close();
		DBConncet.cn.close();
	}

	public String selectDescribeProduct(int id) throws Exception {
		String moTa = null;
		String sqlQuery = "select mo_ta from San_Pham where id_san_pham = ? ";
		DBConncet.GetConnect();
		preP = DBConncet.cn.prepareStatement(sqlQuery);
		preP.setInt(1, id);
		rs = preP.executeQuery();
		while (rs.next()) {
			moTa = rs.getString(1);
		}

		DBConncet.closeAllConnection(preP, rs);
		return moTa;
	}


	

}
