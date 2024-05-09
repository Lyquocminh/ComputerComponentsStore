package entity;

public class TongLoiNhuan {
	private String ngay, thuNhap, loiNhuan;
	public TongLoiNhuan() {
		super();
	}
	public TongLoiNhuan(String ngay, String thuNhap, String loiNhuan) {
		super();
		this.ngay = ngay;
		this.thuNhap = thuNhap;
		this.loiNhuan = loiNhuan;
	}
	public String getNgay() {
		return ngay;
	}
	public void setNgay(String ngay) {
		this.ngay = ngay;
	}
	public String getThuNhap() {
		return thuNhap;
	}
	public void setThuNhap(String thuNhap) {
		this.thuNhap = thuNhap;
	}
	public String getLoiNhuan() {
		return loiNhuan;
	}
	public void setLoiNhuan(String loiNhuan) {
		this.loiNhuan = loiNhuan;
	}

	
}
