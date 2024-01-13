package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import bean.SanPhamBEAN;
import bean.SanPhamHomeBEAN;
import common.DBConncet;

public class SanPhamHomeDAO {
	private PreparedStatement preP = null;
	private ResultSet rs = null;

	public String getIMGThumbnail(int id_san_pham) throws Exception {
		String link_anh = "";

		String sqlQuery = "select link_anh\r\n" + "  from Anh_San_Pham where id_san_pham = ? and thumbnail = 1";

		PreparedStatement prePNEW = DBConncet.cn.prepareStatement(sqlQuery);

		prePNEW = DBConncet.cn.prepareStatement(sqlQuery);
		prePNEW.setInt(1, id_san_pham);

		ResultSet rsNew = prePNEW.executeQuery();
		while (rsNew.next()) {
			link_anh = rsNew.getString(1);
		}

		rsNew.close();
		prePNEW.close();
		return link_anh;
	}

	public ArrayList<SanPhamHomeBEAN> getAll() throws Exception {
		ArrayList<SanPhamHomeBEAN> list = new ArrayList<SanPhamHomeBEAN>();
		String sqlQuery = "select top(12) SP.id_san_pham, SP.ten_san_pham, MIN(TTCT.gia_ban_ra) as 'price'\r\n"
				+ "  from San_Pham SP join Thong_Tin_Chi_Tiet TTCT \r\n" + "  ON sp.id_san_pham = TTCT.id_san_pham\r\n"
				+ "  Group by SP.id_san_pham, SP.ten_san_pham order by SP.id_san_pham desc";
		DBConncet.GetConnect();
		preP = DBConncet.cn.prepareStatement(sqlQuery);
		rs = preP.executeQuery();
		while (rs.next()) {
			int id_san_pham = rs.getInt(1);
			String ten_san_pham = rs.getString(2);
			long price = rs.getLong(3);
			SanPhamHomeBEAN sanPham = new SanPhamHomeBEAN(id_san_pham, ten_san_pham, price);
			// Thực hiện gán ảnh thumbail cho sản phẩm này
			sanPham.setLink_anh(getIMGThumbnail(sanPham.getId_san_pham()));
			list.add(sanPham);
		}

		DBConncet.closeAllConnection(preP, rs);
		return list;
	}
	
	public int getTotalPage(String[] Brands, ArrayList<Long> MucGia,
			String[] HeDieuHanh, String keySearch,int RowPerPage) throws Exception {
		
		int sl = 0;
		String sql = "select SP.id_san_pham\r\n"
				+ "from San_Pham SP join Thong_Tin_Chi_Tiet TTCT\r\n" + "On SP.id_San_pham = TTCT.id_san_pham\r\n";

		sql += " where 1=1 ";

		if (Brands != null) { // Lọc theo hãng sản xuất
			sql += " and ( ";
			for (String hang_san_xuat : Brands) {
				sql += "SP.id_hang = " + hang_san_xuat + " or ";
			}
			// Cập nhật lại chuỗi sql sau khi thêm điều kiện filter, bỏ đi or thừa ở cuối
			sql = sql.substring(0, (sql.length() - 1) - 3);
			sql += ")";
		}

		// Lọc theo giá sản phẩm
		if (MucGia != null) {
			sql += " and (TTCT.gia_ban_ra between " + MucGia.get(0) + " and " + MucGia.get(1) + ")";
		}

		// Lọc theo hệ điều hành
		if (HeDieuHanh != null)

		{
			sql += " and (";
			for (String hdh : HeDieuHanh) {
				sql += "he_dieu_hanh = '" + hdh + "' or ";
			}
			// Cập nhật lại chuỗi sql sau khi thêm điều kiện filter, bỏ đi or thừa ở cuối
			sql = sql.substring(0, (sql.length() - 1) - 3);
			sql += ")";
		}

		if (keySearch != null) {
			sql += " and ";
			sql += "SP.ten_san_pham like N'%" + keySearch + "%'";
		}

		sql += " group by SP.id_san_pham, SP.ten_san_pham order by SP.id_san_pham";

		DBConncet.GetConnect();
		preP = DBConncet.cn.prepareStatement(sql);
		rs = preP.executeQuery();
		
		while(rs.next()) {
			sl++;
		}
		
		DBConncet.closeAllConnection(preP, rs);
		
		if(sl % RowPerPage == 0) {
			return sl / RowPerPage;
		} else {
			return sl /RowPerPage + 1;
		}
	}

	public ArrayList<SanPhamHomeBEAN> filter(String[] Brands, ArrayList<Long> MucGia,
			String[] HeDieuHanh, String keySearch, int pageNumber, int RowPerPage) throws Exception {
		ArrayList<SanPhamHomeBEAN> list = new ArrayList<SanPhamHomeBEAN>();
		String sql = "select SP.id_san_pham, SP.ten_san_pham, MIN(TTCT.gia_ban_ra)\r\n"
				+ "from San_Pham SP join Thong_Tin_Chi_Tiet TTCT\r\n" + "On SP.id_San_pham = TTCT.id_san_pham\r\n";

		sql += " where 1=1 ";

		if (Brands != null) { // Lọc theo hãng sản xuất
			sql += " and ( ";
			for (String hang_san_xuat : Brands) {
				sql += "SP.id_hang = " + hang_san_xuat + " or ";
			}
			// Cập nhật lại chuỗi sql sau khi thêm điều kiện filter, bỏ đi or thừa ở cuối
			sql = sql.substring(0, (sql.length() - 1) - 3);
			sql += ")";
		}

		// Lọc theo giá sản phẩm
		if (MucGia != null) {
			sql += " and (TTCT.gia_ban_ra between " + MucGia.get(0) + " and " + MucGia.get(1) + ")";
		}

		// Lọc theo hệ điều hành
		if (HeDieuHanh != null)

		{
			sql += " and (";
			for (String hdh : HeDieuHanh) {
				sql += "he_dieu_hanh = '" + hdh + "' or ";
			}
			// Cập nhật lại chuỗi sql sau khi thêm điều kiện filter, bỏ đi or thừa ở cuối
			sql = sql.substring(0, (sql.length() - 1) - 3);
			sql += ")";
		}

		if (keySearch != null) {
			sql += " and ";
			sql += "SP.ten_san_pham like N'%" + keySearch + "%'";
		}

		sql += " group by SP.id_san_pham, SP.ten_san_pham order by SP.id_san_pham offset ? rows fetch next ? rows only";

		DBConncet.GetConnect();
		preP = DBConncet.cn.prepareStatement(sql);
		preP.setInt(1, (pageNumber - 1)*RowPerPage);
		preP.setInt(2, RowPerPage);
		rs = preP.executeQuery();
		while (rs.next())

		{
			int id_san_pham = rs.getInt(1);
			String ten_san_pham = rs.getString(2);
			long price = rs.getLong(3);
			SanPhamHomeBEAN sanPham = new SanPhamHomeBEAN(id_san_pham, ten_san_pham, price);
			// Thực hiện gán ảnh thumbail cho sản phẩm này
			sanPham.setLink_anh(getIMGThumbnail(sanPham.getId_san_pham()));
			list.add(sanPham);
		}

		//System.out.println(sql);
		DBConncet.closeAllConnection(preP, rs);
		return list;

	}

	public static void main(String[] args) throws Exception {
		SanPhamHomeDAO spDao = new SanPhamHomeDAO();
		System.out.println(spDao.getTotalPage(null, null,null, null, 5));
	}
}
