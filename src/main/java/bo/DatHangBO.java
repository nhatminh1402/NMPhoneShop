package bo;

import common.DBConncet;
import dao.DatHang;

public class DatHangBO {
	DatHang dhDAO = new DatHang();

	public int TaoHoaDon(int MaKhachHang, String DiaChiNhanHang, String ghiChu) throws Exception {
		return dhDAO.TaoHoaDon(MaKhachHang, DiaChiNhanHang, ghiChu);
	}

	public void TaoChiTietHoaDon(int maHoaDon, int id_thong_tin_chi_tiet, int so_luong_mua) throws Exception {
		dhDAO.TaoChiTietHoaDon(maHoaDon, id_thong_tin_chi_tiet, so_luong_mua);
	}

}
