package bo;

import java.util.ArrayList;

import bean.SanPhamBEAN;
import dao.SanPhamDAO;

public class SanPhamBO {

	SanPhamDAO spDAO = new SanPhamDAO();
	
	public void update(int maSP,String ten_San_pham, String he_dieu_hanh, int tong_thang_bao_hanh, int id_hang) throws Exception {
		spDAO.update(maSP,ten_San_pham, he_dieu_hanh, tong_thang_bao_hanh, id_hang);
	}
	
	public void delate(int id) throws Exception {
		spDAO.delate(id);
	}
	
	public ArrayList<SanPhamBEAN> pagination(int pageNumber, int pageSize) throws Exception {
		return spDAO.pagination(pageNumber, pageSize);
	}
	
	public void insert(String ten_san_pham, String he_dieu_hanh, int id_hang, int bao_hanh) throws Exception {
		spDAO.insert(ten_san_pham, he_dieu_hanh, bao_hanh, id_hang);
	}
	
	public SanPhamBEAN selectById(int id) throws Exception {
		return spDAO.selectById(id);
	}
	
	public void updateDescribe(String describe, int productId) throws Exception {
		spDAO.updateDescribe(describe, productId);
	}
	
	public String selectDescribeProduct(int id) throws Exception {
		return spDAO.selectDescribeProduct(id);
	}
}
