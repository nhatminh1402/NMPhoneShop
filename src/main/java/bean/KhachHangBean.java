package bean;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KhachHangBean {
	private int ma_Khach_hang;
	private String ten_Khach_Hang;
	private String email;
	private String matKhau;
	private String soDienThoai;
	public KhachHangBean(int ma_Khach_hang, String ten_Khach_Hang, String email, String matKhau, String soDienThoai) {
		super();
		this.ma_Khach_hang = ma_Khach_hang;
		this.ten_Khach_Hang = ten_Khach_Hang;
		this.email = email;
		this.matKhau = matKhau;
		this.soDienThoai = soDienThoai;
	}
	public KhachHangBean(String ten_Khach_Hang, String email, String matKhau, String soDienThoai) {
		super();
		this.ten_Khach_Hang = ten_Khach_Hang;
		this.email = email;
		this.matKhau = matKhau;
		this.soDienThoai = soDienThoai;
	}
	public KhachHangBean(int ma_Khach_hang, String ten_Khach_Hang, String email, String soDienThoai) {
		super();
		this.ma_Khach_hang = ma_Khach_hang;
		this.ten_Khach_Hang = ten_Khach_Hang;
		this.email = email;
		this.soDienThoai = soDienThoai;
	}
	
	
	
}
