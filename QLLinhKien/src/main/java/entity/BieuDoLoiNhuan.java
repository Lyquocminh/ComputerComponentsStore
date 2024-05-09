package entity;

import java.util.Date;

public class BieuDoLoiNhuan {
	private double loiNhuan;
	private Date ngay;
	public BieuDoLoiNhuan() {
		super();
	}
	public BieuDoLoiNhuan(double loiNhuan, Date ngay) {
		super();
		this.loiNhuan = loiNhuan;
		this.ngay = ngay;
	}
	public double getLoiNhuan() {
		return loiNhuan;
	}
	public void setLoiNhuan(double loiNhuan) {
		this.loiNhuan = loiNhuan;
	}
	public Date getNgay() {
		return ngay;
	}
	public void setNgay(Date ngay) {
		this.ngay = ngay;
	}
	
	
}
