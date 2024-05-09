package entity;

import java.util.Date;

public class BieuDoSanPham {
	private int soLuongSanPham;
	private Date ngay;
	
	public BieuDoSanPham() {
		super();
	}

	public BieuDoSanPham(int soLuongHoaDon, Date ngay) {
		super();
		this.soLuongSanPham = soLuongHoaDon;
		this.ngay = ngay;
	}

	public int getSoLuongHoaDon() {
		return soLuongSanPham;
	}

	public void setSoLuongHoaDon(int soLuongHoaDon) {
		this.soLuongSanPham = soLuongHoaDon;
	}

	public Date getNgay() {
		return ngay;
	}

	public void setNgay(Date ngay) {
		this.ngay = ngay;
	}
	
	
}
