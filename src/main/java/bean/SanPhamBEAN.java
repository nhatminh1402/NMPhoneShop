package bean;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SanPhamBEAN {
	private int id_san_pham;
	private String ten_san_pham;
	private String he_dieu_hanh;
	private int tong_thang_bao_hanh;
	private Date ngay_nhap_hang;
	private String mo_ta;
	private int id_hang;
	private String ten_hang;
	
	
	
	public SanPhamBEAN(int id_san_pham, String ten_san_pham, String he_dieu_hanh, int tong_thang_bao_hanh,
			Date ngay_nhap_hang, String mo_ta, int id_hang, String ten_hang) {
		super();
		this.id_san_pham = id_san_pham;
		this.ten_san_pham = ten_san_pham;
		this.he_dieu_hanh = he_dieu_hanh;
		this.tong_thang_bao_hanh = tong_thang_bao_hanh;
		this.ngay_nhap_hang = ngay_nhap_hang;
		this.mo_ta = mo_ta;
		this.id_hang = id_hang;
		this.ten_hang = ten_hang;
	}

	public SanPhamBEAN(String ten_san_pham, String he_dieu_hanh, int tong_thang_bao_hanh, int id_hang) {
		super();
		this.ten_san_pham = ten_san_pham;
		this.he_dieu_hanh = he_dieu_hanh;
		this.tong_thang_bao_hanh = tong_thang_bao_hanh;
		this.id_hang = id_hang;
	}

	
	public SanPhamBEAN() {
		super();
	}

	public SanPhamBEAN(int id_san_pham, String ten_san_pham, String he_dieu_hanh, int tong_thang_bao_hanh,
			int id_hang) {
		super();
		this.id_san_pham = id_san_pham;
		this.ten_san_pham = ten_san_pham;
		this.he_dieu_hanh = he_dieu_hanh;
		this.tong_thang_bao_hanh = tong_thang_bao_hanh;
		this.id_hang = id_hang;
	}	
	
	
	
	
	
}
