package bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class ChiTietSanPhamBEAN {
	private int id_thong_tin_chi_tiet;
	private String ram;
	private String dung_luong_luu_tru;
	private float camera_truoc;
	private float camera_sau;
	private String mau_sac;
	private float man_hinh;
	private String chip;
	private int id_tinh_nang_dac_biet;
	private long gia_nhap_vao;
	private long gia_ban_ra;
	private int so_luong;
	private int id_san_pham;
	
	public ChiTietSanPhamBEAN(int id_thong_tin_chi_tiet, String ram, String dung_luong_luu_tru, float camera_truoc,
			float camera_sau, String mau_sac, float man_hinh, String chip, int id_tinh_nang_dac_biet, long gia_nhap_vao,
			long gia_ban_ra, int so_luong, int id_san_pham) {
		super();
		this.id_thong_tin_chi_tiet = id_thong_tin_chi_tiet;
		this.ram = ram;
		this.dung_luong_luu_tru = dung_luong_luu_tru;
		this.camera_truoc = camera_truoc;
		this.camera_sau = camera_sau;
		this.mau_sac = mau_sac;
		this.man_hinh = man_hinh;
		this.chip = chip;
		this.id_tinh_nang_dac_biet = id_tinh_nang_dac_biet;
		this.gia_nhap_vao = gia_nhap_vao;
		this.gia_ban_ra = gia_ban_ra;
		this.so_luong = so_luong;
		this.id_san_pham = id_san_pham;
	}
	
	public ChiTietSanPhamBEAN(String ram, String dung_luong_luu_tru, float camera_truoc, float camera_sau,
			String mau_sac, float man_hinh, String chip, long gia_nhap_vao, long gia_ban_ra, int so_luong,
			int id_san_pham) {
		super();
		this.ram = ram;
		this.dung_luong_luu_tru = dung_luong_luu_tru;
		this.camera_truoc = camera_truoc;
		this.camera_sau = camera_sau;
		this.mau_sac = mau_sac;
		this.man_hinh = man_hinh;
		this.chip = chip;
		this.gia_nhap_vao = gia_nhap_vao;
		this.gia_ban_ra = gia_ban_ra;
		this.so_luong = so_luong;
		this.id_san_pham = id_san_pham;
	}
	
	public ChiTietSanPhamBEAN(int id_thong_tin_chi_tiet, String ram, String dung_luong_luu_tru, float camera_truoc,
			float camera_sau, String mau_sac, float man_hinh, String chip, long gia_nhap_vao, long gia_ban_ra,
			int so_luong) {
		super();
		this.id_thong_tin_chi_tiet = id_thong_tin_chi_tiet;
		this.ram = ram;
		this.dung_luong_luu_tru = dung_luong_luu_tru;
		this.camera_truoc = camera_truoc;
		this.camera_sau = camera_sau;
		this.mau_sac = mau_sac;
		this.man_hinh = man_hinh;
		this.chip = chip;
		this.gia_nhap_vao = gia_nhap_vao;
		this.gia_ban_ra = gia_ban_ra;
		this.so_luong = so_luong;
	}

	public ChiTietSanPhamBEAN(int id_thong_tin_chi_tiet, long gia_ban_ra) {
		super();
		this.id_thong_tin_chi_tiet = id_thong_tin_chi_tiet;
		this.gia_ban_ra = gia_ban_ra;
	}
	
	
	
}
