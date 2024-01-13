package bean;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SanPhamHomeBEAN {
	private int id_san_pham;
	private String ten_san_pham;
	private long price;
	private String link_anh;
	
	public SanPhamHomeBEAN(int id_san_pham, String ten_san_pham, long price) {
		super();
		this.id_san_pham = id_san_pham;
		this.ten_san_pham = ten_san_pham;
		this.price = price;
	}
}
