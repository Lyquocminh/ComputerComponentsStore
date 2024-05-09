package entity;

import java.util.ArrayList;
import java.util.Date;

public class TongSoHoaDon {
	private String ngayXuat;
	private String maHD;
	private String tenKH;
	private String tenNV;
	private String tongTien;
	
	public TongSoHoaDon() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public TongSoHoaDon(String ngayXuat, String maHD, String tenKH, String tenNV, String tongTien) {
		super();
		this.ngayXuat = ngayXuat;
		this.maHD = maHD;
		this.tenKH = tenKH;
		this.tenNV = tenNV;
		this.tongTien = tongTien;
	}



	public String getTenNV() {
		return tenNV;
	}

	public void setTenNV(String tenNV) {
		this.tenNV = tenNV;
	}

	public String getNgayXuat() {
		return ngayXuat;
	}
	public void setNgayXuat(String ngayXuat) {
		this.ngayXuat = ngayXuat;
	}
	public String getMaHD() {
		return maHD;
	}
	public void setMaHD(String maHD) {
		this.maHD = maHD;
	}
	public String getTenKH() {
		return tenKH;
	}
	public void setTenKH(String tenKH) {
		this.tenKH = tenKH;
	}
	public String getTongTien() {
		return tongTien;
	}
	public void setTongTien(String tongTien) {
		this.tongTien = tongTien;
	}

}
