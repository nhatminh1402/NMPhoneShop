package bean;

public class AdminBEAN {
	private int id;
	private String email;
	private String matKhau;
	private String hoTen;
	private String soDienThoai;
	private boolean gioiTinh;
	private String diaChi;

	public AdminBEAN() {
		super();
	}

	public AdminBEAN(int id, String email, String matKhau, String hoTen) {
		super();
		this.id = id;
		this.email = email;
		this.matKhau = matKhau;
		this.hoTen = hoTen;
	}

	public AdminBEAN(int id, String email, String matKhau, String hoTen, String soDienThoai, boolean gioiTinh,
			String diaChi) {
		super();
		this.id = id;
		this.email = email;
		this.matKhau = matKhau;
		this.hoTen = hoTen;
		this.soDienThoai = soDienThoai;
		this.gioiTinh = gioiTinh;
		this.diaChi = diaChi;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMatKhau() {
		return matKhau;
	}

	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public String getSoDienThoai() {
		return soDienThoai;
	}

	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}

	public boolean isGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(boolean gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
}
