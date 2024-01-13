package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.GioHangBean;
import common.DBConncet;

public class GioHangDAO {

	PreparedStatement pre = null;
	ResultSet rs = null;

	public GioHangBean getProductByIDDetail(int id_chi_tiet) throws Exception {
		
		GioHangBean gh = new GioHangBean();
		String sql = "select *\r\n" + "from Thong_Tin_Chi_Tiet TTCT join San_Pham SP\r\n"
				+ "	on TTCT.id_san_pham = SP.id_san_pham\r\n" + "	where TTCT.id_thong_tin_chi_tiet = ?";

		DBConncet.GetConnect();
		pre = DBConncet.cn.prepareStatement(sql);
		pre.setInt(1, id_chi_tiet);
		rs = pre.executeQuery();
		while (rs.next()) {
			SanPhamHomeDAO spDAO = new SanPhamHomeDAO();
			int ma_chi_tiet_San_pham = rs.getInt("id_thong_tin_Chi_Tiet");
			String ten_san_pham = rs.getString("ten_San_pham");
			Long gia = rs.getLong("gia_ban_ra");
			String ram = rs.getString("ram");
			String dung_luong_luu_tru = rs.getString("dung_luong_luu_tru");
			String mau_sac = rs.getString("mau_sac");
			String link_Anh = spDAO.getIMGThumbnail(rs.getInt("id_san_pham"));
			gh = new GioHangBean(ma_chi_tiet_San_pham, ten_san_pham, gia, ram, dung_luong_luu_tru, mau_sac, link_Anh);
		}

		DBConncet.closeAllConnection(pre, rs);
		return gh;
	}
}
