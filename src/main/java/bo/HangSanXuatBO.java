package bo;

import java.util.ArrayList;

import bean.HangSanXuatBEAN;
import dao.HangSanXuatDAO;

public class HangSanXuatBO {

	HangSanXuatDAO hDAO = new HangSanXuatDAO();
	
	public ArrayList<HangSanXuatBEAN> getAll() throws Exception {
		return hDAO.getAll();
	}
	
	public int getTotalPage(int rowPerPage) throws Exception {
		return hDAO.getTotalPage(rowPerPage);
	}
	
	public ArrayList<HangSanXuatBEAN> getListPaginate(int pageSize, int pageNumber) throws Exception {
		return hDAO.getListPaginate(pageSize, pageNumber);
	}
	
	public boolean isBrandExist(String hangSanXuat) throws Exception {
		return hDAO.isBrandExist(hangSanXuat);
	}
	
	public void insert(String HangSanXuat) throws Exception {
		 hDAO.insert(HangSanXuat);
	}
	
	public void deleteById(int id) throws Exception {
		hDAO.deleteById(id);
	}
	
	public void updateById(int id, String ten_hang) throws Exception {
		hDAO.updateById(id, ten_hang);
	}
}
