package entity;

import java.util.Date;

public class BieuDoThuNhap {
	private double tongThuNhap;
	private Date ngay;
	public BieuDoThuNhap() {
		super();
	}
	public BieuDoThuNhap(double tongThuNhap, Date ngay) {
		super();
		this.tongThuNhap = tongThuNhap;
		this.ngay = ngay;
	}
	public double getTongThuNhap() {
		return tongThuNhap;
	}
	public void setTongThuNhap(double tongThuNhap) {
		this.tongThuNhap = tongThuNhap;
	}
	public Date getNgay() {
		return ngay;
	}
	public void setNgay(Date ngay) {
		this.ngay = ngay;
	}
	
	
	
}
