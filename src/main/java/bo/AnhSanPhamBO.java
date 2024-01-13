package bo;

import java.util.ArrayList;

import bean.AnhSanPhamBEAN;
import dao.AnhSanPhamDAO;

public class AnhSanPhamBO {
	AnhSanPhamDAO aDAO = new AnhSanPhamDAO();
	
	public ArrayList<AnhSanPhamBEAN> getAll(int id_sp) throws Exception {
		return aDAO.getAll(id_sp);
	}
	public void insert(String link_anh, int id_san_pham) throws Exception {
		aDAO.insert(link_anh, id_san_pham);
	}
	
	public void deleteIMG(int id_anh) throws Exception {
		aDAO.deleteIMG(id_anh);
	}
	
	public ArrayList<AnhSanPhamBEAN> listAllIMGProduct(int id_sp) throws Exception {
		return aDAO.listAllIMGProduct(id_sp);
	}
	
	public void ResetThumbnail(int id_san_pham) throws Exception {
		aDAO.ResetThumbnail(id_san_pham);
	}
	
	public void updateThumbnail(int id_anh) throws Exception {
		aDAO.updateThumbnail(id_anh);
	}
}
