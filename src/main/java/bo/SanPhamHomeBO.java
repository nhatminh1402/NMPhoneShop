package bo;

import java.util.ArrayList;

import bean.SanPhamHomeBEAN;
import dao.SanPhamDAO;
import dao.SanPhamHomeDAO;

public class SanPhamHomeBO {
	SanPhamHomeDAO spDAO = new SanPhamHomeDAO();
	
	public ArrayList<SanPhamHomeBEAN> getAll() throws Exception {
		return spDAO.getAll();
	}
	
	public ArrayList<SanPhamHomeBEAN> filter(String[] Brands, ArrayList<Long> MucGia,
			String[] HeDieuHanh, String keySearch, int pageNumber, int RowPerPage) throws Exception {
		return spDAO.filter(Brands, MucGia, HeDieuHanh, keySearch, pageNumber, RowPerPage);
	}
	
	public int getTotalPage(String[] Brands, ArrayList<Long> MucGia,
			String[] HeDieuHanh, String keySearch,int RowPerPage) throws Exception {
		return spDAO.getTotalPage(Brands, MucGia, HeDieuHanh, keySearch, RowPerPage);
	}
}
