package entity;

import java.util.Date;

public class BieuDoHoaDon {
	private int soLuongHoaDon;
	private Date ngay;
	public BieuDoHoaDon() {
		super();
	}
	public BieuDoHoaDon(int soLuongHoaDon, Date ngay) {
		super();
		this.soLuongHoaDon = soLuongHoaDon;
		this.ngay = ngay;
	}
	public int getSoLuongHoaDon() {
		return soLuongHoaDon;
	}
	public void setSoLuongHoaDon(int soLuongHoaDon) {
		this.soLuongHoaDon = soLuongHoaDon;
	}
	public Date getNgay() {
		return ngay;
	}
	public void setNgay(Date ngay) {
		this.ngay = ngay;
	}
	
	
}
