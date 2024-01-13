package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import bean.AnhSanPhamBEAN;
import bean.ChiTietSanPhamBEAN;
import common.DBConncet;

public class ChiTietSanPhamDAO {
	private PreparedStatement preP = null;
	private ResultSet rs = null;

	public ArrayList<ChiTietSanPhamBEAN> getAll(int id_sp) throws Exception {
		ArrayList<ChiTietSanPhamBEAN> list = new ArrayList<ChiTietSanPhamBEAN>();
		DBConncet.GetConnect();

		String sqlQuery = "select * from Thong_Tin_Chi_Tiet where id_san_pham = ?";

		preP = DBConncet.cn.prepareStatement(sqlQuery);
		preP.setInt(1, id_sp);

		rs = preP.executeQuery();
		while (rs.next()) {
			int id_thong_tin_chi_tiet = rs.getInt("id_thong_tin_chi_tiet");
			String ram = rs.getString("ram");
			String dung_luong_luu_tru = rs.getString("dung_luong_luu_tru");
			float camera_truoc = rs.getFloat("camera_truoc");
			float camera_sau = rs.getFloat("camera_sau");
			String mau_sac = rs.getString("mau_sac");
			float man_hinh = rs.getFloat("man_hinh");
			String chip = rs.getString("chip");
			int id_tinh_nang_dac_biet = rs.getInt("id_tinh_nang_dac_biet");
			long gia_nhap_vao = rs.getLong("gia_nhap_vao");
			long gia_ban_ra = rs.getLong("gia_ban_ra");
			int so_luong = rs.getInt("so_luong");
			int id_san_pham = rs.getInt("id_san_pham");
			list.add(new ChiTietSanPhamBEAN(id_thong_tin_chi_tiet, ram, dung_luong_luu_tru, camera_truoc, camera_sau,
					mau_sac, man_hinh, chip, id_tinh_nang_dac_biet, gia_nhap_vao, gia_ban_ra, so_luong, id_san_pham));
		}

		preP.close();
		rs.close();
		DBConncet.cn.close();
		return list;
	}

	public ChiTietSanPhamBEAN showDescribe(int id_describe) throws Exception {
		ChiTietSanPhamBEAN ct = null;
		DBConncet.GetConnect();

		String sqlQuery = "select * from Thong_Tin_Chi_Tiet where id_thong_tin_chi_tiet = ?";

		preP = DBConncet.cn.prepareStatement(sqlQuery);
		preP.setInt(1, id_describe);

		rs = preP.executeQuery();
		while (rs.next()) {
			int id_thong_tin_chi_tiet = rs.getInt("id_thong_tin_chi_tiet");
			String ram = rs.getString("ram");
			String dung_luong_luu_tru = rs.getString("dung_luong_luu_tru");
			float camera_truoc = rs.getFloat("camera_truoc");
			float camera_sau = rs.getFloat("camera_sau");
			String mau_sac = rs.getString("mau_sac");
			float man_hinh = rs.getFloat("man_hinh");
			String chip = rs.getString("chip");
			int id_tinh_nang_dac_biet = rs.getInt("id_tinh_nang_dac_biet");
			long gia_nhap_vao = rs.getLong("gia_nhap_vao");
			long gia_ban_ra = rs.getLong("gia_ban_ra");
			int so_luong = rs.getInt("so_luong");
			int id_san_pham = rs.getInt("id_san_pham");
			ct = new ChiTietSanPhamBEAN(id_thong_tin_chi_tiet, ram, dung_luong_luu_tru, camera_truoc, camera_sau,
					mau_sac, man_hinh, chip, gia_nhap_vao, gia_ban_ra, so_luong);

		}

		preP.close();
		rs.close();
		DBConncet.cn.close();
		return ct;
	}

	public void insert(ChiTietSanPhamBEAN detail) throws Exception {
		DBConncet.GetConnect();

		String sqlQuery = "INSERT INTO [dbo].[Thong_Tin_Chi_Tiet]\r\n" + "           ([ram]\r\n"
				+ "           ,[dung_luong_luu_tru]\r\n" + "           ,[camera_truoc]\r\n"
				+ "           ,[camera_sau]\r\n" + "           ,[mau_sac]\r\n" + "           ,[man_hinh]\r\n"
				+ "           ,[chip]\r\n" + "           ,[id_tinh_nang_dac_biet]\r\n"
				+ "           ,[gia_nhap_vao]\r\n" + "           ,[gia_ban_ra]\r\n" + "           ,[so_luong]\r\n"
				+ "           ,[id_san_pham]) values(?,?,?,?,?,?,?,?,?,?,?,?)";

		preP = DBConncet.cn.prepareStatement(sqlQuery);
		preP.setString(1, detail.getRam());
		preP.setString(2, detail.getDung_luong_luu_tru());
		preP.setFloat(3, detail.getCamera_truoc());
		preP.setFloat(4, detail.getCamera_sau());
		preP.setString(5, detail.getMau_sac());
		preP.setFloat(6, detail.getMan_hinh());
		preP.setString(7, detail.getChip());
		preP.setInt(8, detail.getId_tinh_nang_dac_biet());
		preP.setLong(9, detail.getGia_nhap_vao());
		System.out.println(detail.getGia_nhap_vao());
		preP.setLong(10, detail.getGia_ban_ra());
		System.out.println(detail.getGia_ban_ra());
		preP.setInt(11, detail.getSo_luong());
		preP.setInt(12, detail.getId_san_pham());

		preP.executeUpdate();

		preP.close();
		DBConncet.cn.close();
	}

	public void delete(int id_thong_tin_chi_tiet) throws Exception {
		DBConncet.GetConnect();

		String sqlQuery = "DELETE From Thong_Tin_Chi_Tiet where id_thong_tin_chi_tiet = ?";

		preP = DBConncet.cn.prepareStatement(sqlQuery);

		preP.setInt(1, id_thong_tin_chi_tiet);

		preP.executeUpdate();

		preP.close();
		DBConncet.cn.close();
	}

	public void Update(ChiTietSanPhamBEAN detail) throws Exception {
		DBConncet.GetConnect();

		String sqlQuery = "update Thong_Tin_Chi_Tiet " + "set ram = ?, " + "dung_luong_luu_tru = ?,"
				+ "camera_truoc = ?," + "camera_sau = ?," + "mau_sac = ?," + "man_hinh = ?," + "chip = ?,"
				+ "gia_nhap_vao = ?," + "gia_ban_ra = ?," + "so_luong = ? where id_thong_tin_chi_tiet = ?";

		preP = DBConncet.cn.prepareStatement(sqlQuery);
		preP.setString(1, detail.getRam());
		preP.setString(2, detail.getDung_luong_luu_tru());
		preP.setFloat(3, detail.getCamera_truoc());
		preP.setFloat(4, detail.getCamera_sau());
		preP.setString(5, detail.getMau_sac());
		preP.setFloat(6, detail.getMan_hinh());
		preP.setString(7, detail.getChip());
		preP.setLong(8, detail.getGia_nhap_vao());
		preP.setLong(9, detail.getGia_ban_ra());
		preP.setInt(10, detail.getSo_luong());
		preP.setInt(11, detail.getId_thong_tin_chi_tiet());

		preP.executeUpdate();

		preP.close();
		DBConncet.cn.close();
	}

	public ArrayList<String> getAllColor(int id_sp) throws Exception {
		ArrayList<String> list = new ArrayList<String>();
		DBConncet.GetConnect();

		String sqlQuery = "select distinct mau_sac from Thong_Tin_Chi_Tiet where id_san_pham = ? and so_luong > 0";

		preP = DBConncet.cn.prepareStatement(sqlQuery);
		preP.setInt(1, id_sp);

		rs = preP.executeQuery();
		while (rs.next()) {
			String color = rs.getString(1);
			list.add(color);
		}

		preP.close();
		rs.close();
		DBConncet.cn.close();
		return list;
	}
	
	public ArrayList<String> getBoNhoBoiMauSac(String mauSac, int idSP) throws Exception {
		ArrayList<String> ds = new ArrayList<String>();
		String sqlQuery ="select distinct dung_luong_luu_tru\r\n"
				+ "from Thong_Tin_Chi_Tiet\r\n"
				+ "where id_san_pham = ? and mau_sac = ? and so_luong > 0";
		
		DBConncet.GetConnect();
		preP = DBConncet.cn.prepareStatement(sqlQuery);
		preP.setInt(1 ,idSP);
		preP.setString(2, mauSac);
		
		
		rs = preP.executeQuery();
		while(rs.next()) {
			 ds.add(rs.getString(1));
		}
		
		DBConncet.closeAllConnection(preP, rs);
		return ds;
	}
	
	public ArrayList<String> getRamByMauVaDungLuong(String dungLuong,String mauSac, int idSP) throws Exception {
		ArrayList<String> ds = new ArrayList<String>();
		String sqlQuery ="select distinct ram\r\n"
				+ "from Thong_Tin_Chi_Tiet\r\n"
				+ "where id_san_pham = ? and mau_sac = ? and dung_luong_luu_tru = ? and so_luong > 0";
		
		DBConncet.GetConnect();
		preP = DBConncet.cn.prepareStatement(sqlQuery);
		preP.setInt(1 ,idSP);
		preP.setString(2, mauSac);
		preP.setString(3, dungLuong);
		
		rs = preP.executeQuery();
		while(rs.next()) {
			 ds.add(rs.getString(1));
		}
		
		DBConncet.closeAllConnection(preP, rs);
		return ds;
	}
	
	public ChiTietSanPhamBEAN getGia(String ram,String dungLuong,String mauSac, int idSP) throws Exception {
		ChiTietSanPhamBEAN ct = null;
		String sqlQuery ="select id_thong_tin_chi_tiet, gia_ban_ra\r\n"
				+ "from Thong_Tin_Chi_Tiet\r\n"
				+ "where id_san_pham = ? and mau_sac = ? and dung_luong_luu_tru = ? and ram = ? and so_luong > 0";
		
		DBConncet.GetConnect();
		preP = DBConncet.cn.prepareStatement(sqlQuery);
		preP.setInt(1 ,idSP);
		preP.setString(2, mauSac);
		preP.setString(3, dungLuong);
		preP.setString(4, ram);
		
		rs = preP.executeQuery();
		while(rs.next()) {
			int id_thong_tin_chi_tiet = rs.getInt(1);
			long gia = rs.getLong(2);
			ct = new ChiTietSanPhamBEAN(id_thong_tin_chi_tiet, gia);
		}
		
		DBConncet.closeAllConnection(preP, rs);
		return ct;
	}
	
	public int getSoLuongSanPham(int id_sp) throws Exception {
		ArrayList<ChiTietSanPhamBEAN> list = new ArrayList<ChiTietSanPhamBEAN>();
		DBConncet.GetConnect();
		
		int sl = 0;

		String sqlQuery = "select so_luong from Thong_Tin_Chi_Tiet where id_thong_tin_chi_tiet = ?";

		preP = DBConncet.cn.prepareStatement(sqlQuery);
		preP.setInt(1, id_sp);

		rs = preP.executeQuery();
		while (rs.next()) {
			
			 sl = rs.getInt("so_luong");
		}

		preP.close();
		rs.close();
		DBConncet.cn.close();
		return sl;
	}

}
