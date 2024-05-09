package entity;

import java.util.Date;

public class TongSoSanPham {
	private String maSP;
	private String tenSP;
	private String ngayBan;
	private int soLuong;
	private String tongTien;
	
	public TongSoSanPham() {
		// TODO Auto-generated constructor stub
	}

	public TongSoSanPham(String maSP, String tenSP, String ngayBan, int soLuong, String tongTien) {
		super();
		this.maSP = maSP;
		this.tenSP = tenSP;
		this.ngayBan = ngayBan;
		this.soLuong = soLuong;
		this.tongTien = tongTien;
	}

	public String getMaSP() {
		return maSP;
	}

	public void setMaSP(String maSP) {
		this.maSP = maSP;
	}

	public String getTenSP() {
		return tenSP;
	}

	public void setTenSP(String tenSP) {
		this.tenSP = tenSP;
	}

	public String getNgayBan() {
		return ngayBan;
	}

	public void setNgayBan(String ngayBan) {
		this.ngayBan = ngayBan;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public String getTongTien() {
		return tongTien;
	}

	public void setTongTien(String tongTien) {
		this.tongTien = tongTien;
	}
	
	
}
