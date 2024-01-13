package bean;

public class AnhSanPhamBEAN {
	private int id_anh;
	private String link_anh;
	private int id_san_pham;
	private boolean thumbnail;
	
	public AnhSanPhamBEAN(int id_anh, String link_anh, int id_san_pham) {
		super();
		this.id_anh = id_anh;
		this.link_anh = link_anh;
		this.id_san_pham = id_san_pham;
	}
	
	public AnhSanPhamBEAN(int id_anh, String link_anh, int id_san_pham, boolean thumbnail) {
		super();
		this.id_anh = id_anh;
		this.link_anh = link_anh;
		this.id_san_pham = id_san_pham;
		this.thumbnail = thumbnail;
	}

	public int getId_anh() {
		return id_anh;
	}

	public void setId_anh(int id_anh) {
		this.id_anh = id_anh;
	}

	public String getLink_anh() {
		return link_anh;
	}

	public void setLink_anh(String link_anh) {
		this.link_anh = link_anh;
	}

	public int getId_san_pham() {
		return id_san_pham;
	}

	public void setId_san_pham(int id_san_pham) {
		this.id_san_pham = id_san_pham;
	}

	public boolean isThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(boolean thumbnail) {
		this.thumbnail = thumbnail;
	}
	
	
}
