package bo;

import java.util.ArrayList;

import bean.GioHangBean;
import dao.GioHangDAO;

public class GioHangBo {
	ArrayList<GioHangBean> ds = new ArrayList<GioHangBean>();

	GioHangDAO ghDAO = new GioHangDAO();

	public GioHangBean getProductByIDDetail(int id_chi_tiet) throws Exception {
		return ghDAO.getProductByIDDetail(id_chi_tiet);
	}

	public ArrayList<GioHangBean> getAll() {
		return ds;
	}
	

	public void add(int id_chi_tiet_San_pham, int so_luong_mua) throws Exception {
		// truy vấn ra thông tin của sản phẩm này
		GioHangBean gh = ghDAO.getProductByIDDetail(id_chi_tiet_San_pham);

		if (ds.size() == 0) {
			// nếu giỏ hàng rỗng thì new ra một giohangbean và add vào danh sách

			gh.setSo_luong_mua(so_luong_mua);
			gh.setThanh_tien(so_luong_mua * gh.getGia());
			ds.add(gh);
		} else {
			for (GioHangBean item : ds) {
				if (item.getMa_chi_tiet_San_pham() == id_chi_tiet_San_pham) {
					int sl = item.getSo_luong_mua() + so_luong_mua;
					item.setSo_luong_mua(sl);
					item.setThanh_tien(item.getSo_luong_mua() * item.getGia());
					return;
				}
			}
			gh.setSo_luong_mua(so_luong_mua);
			gh.setThanh_tien(so_luong_mua * gh.getGia());
			ds.add(gh);
		}
	}

	public long Tongtien() {
		int n = ds.size();
		long s = 0;
		for (int i = 0; i < n; i++) {
			s = s + ds.get(i).getThanh_tien();
		}
		return s;
	}

	public void xoa(int id_chi_tiet) {
		for (GioHangBean gh : ds) {
			if (gh.getMa_chi_tiet_San_pham() == id_chi_tiet) {
				ds.remove(gh);
				return;
			}
		}

	}

	public long IncreseOneProduct(int id_chi_tiet_San_pham) throws Exception {
		// truy vấn ra thông tin của sản phẩm này
		for (GioHangBean item : ds) {
			if (item.getMa_chi_tiet_San_pham() == id_chi_tiet_San_pham) {
				int sl = item.getSo_luong_mua() + 1;
				item.setSo_luong_mua(sl);
				item.setThanh_tien(item.getSo_luong_mua() * item.getGia());
				return item.getThanh_tien();
			}
		}
		return 0;
	}
	
	public long reduce(int id_chi_tiet_San_pham) throws Exception {
		// truy vấn ra thông tin của sản phẩm này
		for (GioHangBean item : ds) {
			if (item.getMa_chi_tiet_San_pham() == id_chi_tiet_San_pham) {
				int sl = item.getSo_luong_mua() - 1;
				item.setSo_luong_mua(sl);
				item.setThanh_tien(item.getSo_luong_mua() * item.getGia());
				return item.getThanh_tien();
			}
		}
		return 0;
	}

}
