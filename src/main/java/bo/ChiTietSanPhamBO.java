package bo;

import java.util.ArrayList;

import bean.ChiTietSanPhamBEAN;
import dao.ChiTietSanPhamDAO;

public class ChiTietSanPhamBO {
	ChiTietSanPhamDAO ctDAO = new ChiTietSanPhamDAO();
	
	public ArrayList<ChiTietSanPhamBEAN> getAll(int id_sp) throws Exception {
		return ctDAO.getAll(id_sp);
	}
	
	public void insert(ChiTietSanPhamBEAN detail) throws Exception {
		ctDAO.insert(detail);
	}
	
	public void delete(int id_thong_tin_chi_tiet) throws Exception {
		ctDAO.delete(id_thong_tin_chi_tiet);
	}
	
	public void Update(ChiTietSanPhamBEAN detail) throws Exception {
		ctDAO.Update(detail);
	}
	
	public ChiTietSanPhamBEAN showDescribe(int id_describe) throws Exception {
		return ctDAO.showDescribe(id_describe);
	}
	
	public ArrayList<String> getAllColor(int id_sp) throws Exception {
		return ctDAO.getAllColor(id_sp);
	}
	
	public ArrayList<String> getBoNhoBoiMauSac(String mauSac, int idSP) throws Exception {
		return ctDAO.getBoNhoBoiMauSac(mauSac, idSP);
	}
	
	public ArrayList<String> getRamByMauVaDungLuong(String dungLuong,String mauSac, int idSP) throws Exception {
		return ctDAO.getRamByMauVaDungLuong(dungLuong,mauSac, idSP);
	}
	
	public int getSoLuongSanPham(int id_sp) throws Exception {
		return ctDAO.getSoLuongSanPham(id_sp);
	}
	
	
	public ChiTietSanPhamBEAN getGia(String ram,String dungLuong,String mauSac, int idSP) throws Exception {
		return ctDAO.getGia(ram, dungLuong, mauSac, idSP);
	}
}
