package bean;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GioHangBean {
	private int ma_chi_tiet_San_pham;
	private String ten_san_pham;
	private Long gia;
	private String ram;
	private String dung_luong_luu_tru;
	private String mau_sac;
	private int so_luong_mua;
	private Long thanh_tien;
	private String img;
	
	public GioHangBean() {
		super();
		// TODO Auto-generated constructor stub
	}



	public GioHangBean(int ma_chi_tiet_San_pham, String ten_san_pham, Long gia, String ram,
			String dung_luong_luu_tru, String mau_sac, String img) {
		super();
		this.ma_chi_tiet_San_pham = ma_chi_tiet_San_pham;
		this.ten_san_pham = ten_san_pham;
		this.gia = gia;
		this.ram = ram;
		this.dung_luong_luu_tru = dung_luong_luu_tru;
		this.mau_sac = mau_sac;
		this.img = img;
	}

	

}
