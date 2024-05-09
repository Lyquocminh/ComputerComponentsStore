package entity;

public class TongThuNhap {
	private String ngay, tongThuNhap;
	public TongThuNhap() {
		// TODO Auto-generated constructor stub
	}

	public TongThuNhap(String ngay, String tongThuNhap) {
		super();
		this.ngay = ngay;
		this.tongThuNhap = tongThuNhap;
	}

	public String getNgay() {
		return ngay;
	}

	public void setNgay(String ngay) {
		this.ngay = ngay;
	}

	public String getTongThuNhap() {
		return tongThuNhap;
	}

	public void setTongThuNhap(String tongThuNhap) {
		this.tongThuNhap = tongThuNhap;
	}
	
	
}
