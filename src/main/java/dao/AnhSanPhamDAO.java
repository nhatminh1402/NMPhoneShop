package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import bean.AnhSanPhamBEAN;
import common.DBConncet;

public class AnhSanPhamDAO {
	private PreparedStatement preP = null;
	private ResultSet rs = null;

	public ArrayList<AnhSanPhamBEAN> getAll(int id_sp) throws Exception {
		ArrayList<AnhSanPhamBEAN> list = new ArrayList<AnhSanPhamBEAN>();
		DBConncet.GetConnect();

		String sqlQuery = "select * from anh_San_pham where id_san_pham = ? and thumbnail = 0";

		preP = DBConncet.cn.prepareStatement(sqlQuery);
		preP.setInt(1, id_sp);

		rs = preP.executeQuery();
		while (rs.next()) {
			 int id_anh = rs.getInt(1);
			 String link_anh = rs.getString(2);
			 int id_san_pham = rs.getInt(3);

			 list.add(new AnhSanPhamBEAN(id_anh, link_anh, id_san_pham));
		}

		preP.close();
		rs.close();
		DBConncet.cn.close();
		return list;
	}
	

	public ArrayList<AnhSanPhamBEAN> listAllIMGProduct(int id_sp) throws Exception {
		ArrayList<AnhSanPhamBEAN> list = new ArrayList<AnhSanPhamBEAN>();
		DBConncet.GetConnect();

		String sqlQuery = "select * from anh_San_pham where id_san_pham = ?";

		preP = DBConncet.cn.prepareStatement(sqlQuery);
		preP.setInt(1, id_sp);

		rs = preP.executeQuery();
		while (rs.next()) {
			 int id_anh = rs.getInt(1);
			 String link_anh = rs.getString(2);
			 int id_san_pham = rs.getInt(3);
			 Boolean thumbnail = rs.getBoolean(4);

			 list.add(new AnhSanPhamBEAN(id_anh, link_anh, id_san_pham, thumbnail));
		}

		preP.close();
		rs.close();
		DBConncet.cn.close();
		return list;
	}
	
	public void insert(String link_anh, int id_san_pham) throws Exception {
		String sqlQuery = "insert into Anh_San_Pham (link_anh, id_san_pham) values(?,?)";

		DBConncet.GetConnect();

		preP = DBConncet.cn.prepareStatement(sqlQuery);
		preP.setString(1,"ProductFile\\" + link_anh);
		preP.setInt(2, id_san_pham);

		preP.executeUpdate();
		
		preP.close();
		DBConncet.cn.close();
	}
	
	public void deleteIMG(int id_anh) throws Exception {
		String sqlQuery = "delete from Anh_San_Pham where id_anh = ?";

		DBConncet.GetConnect();

		preP = DBConncet.cn.prepareStatement(sqlQuery);
		preP.setInt(1, id_anh);

		preP.executeUpdate();
		
		preP.close();
		DBConncet.cn.close();
	}
	

	public void ResetThumbnail(int id_san_pham) throws Exception {
		String sqlQuery = "update Anh_San_Pham\r\n"
				+ "set thumbnail = 0\r\n"
				+ "where id_san_pham = ?";

		DBConncet.GetConnect();

		preP = DBConncet.cn.prepareStatement(sqlQuery);
		preP.setInt(1, id_san_pham);

		preP.executeUpdate();
		
		preP.close();
		DBConncet.cn.close();
	}
	
	
	public void updateThumbnail(int id_anh) throws Exception {
		String sqlQuery = "update Anh_San_Pham\r\n"
				+ "set thumbnail = 1\r\n"
				+ "where id_anh = ?";

		DBConncet.GetConnect();

		preP = DBConncet.cn.prepareStatement(sqlQuery);
		preP.setInt(1, id_anh);

		preP.executeUpdate();
		
		preP.close();
		DBConncet.cn.close();
	}
	
	
	
	
}
