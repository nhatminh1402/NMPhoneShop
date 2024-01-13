package dao;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import common.DBConncet;

public class DatHang {

	
	public int TaoHoaDon(int MaKhachHang, String DiaChiNhanHang, String ghiChu) throws Exception {
		String sql = "insert into HoaDon(Ma_Khach_Hang, DiaChiNhanHang, GhiChu)"
				+ "values(?,?,?)";
		DBConncet.GetConnect();
		int id = 0;
		
		PreparedStatement pre = DBConncet.cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		pre.setInt(1, MaKhachHang);
		pre.setString(2, DiaChiNhanHang);
		pre.setString(3, ghiChu);
		
		int rowEffect = pre.executeUpdate();
		ResultSet rs = null;
		if(rowEffect > 0) {
			rs = pre.getGeneratedKeys();
			while(rs.next()) {
				id = rs.getInt(1);
			}
		}
		
		DBConncet.closeAllConnection(pre, rs);
		return id;
	}
	
	
	public void TaoChiTietHoaDon(int maHoaDon, int id_thong_tin_chi_tiet, int so_luong_mua) throws Exception {
		DBConncet.GetConnect();
		CallableStatement proC = DBConncet.cn.prepareCall("{call proc_createBillDeatil(? ,?, ?)}");
		
		proC.setInt(1, maHoaDon);
		proC.setInt(2, id_thong_tin_chi_tiet);
		proC.setInt(3, so_luong_mua);
		
		proC.executeUpdate();
		proC.close();
		DBConncet.cn.close();
	}
}
