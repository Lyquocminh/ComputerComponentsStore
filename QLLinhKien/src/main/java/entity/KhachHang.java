package entity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
@Entity
@Table(name = "KhachHang")
public class KhachHang implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3714686914005603546L;
	@Id
	private String maKH;
	private String tenKH;
	private int sdt;
	private String diaChi;
	private boolean gioiTinh;
	private int diemTichLuy;
	@OneToMany(mappedBy = "khachHang")
	private List<HoaDon> hoaDon;
	
	public KhachHang() {
	}
	
	public KhachHang(String maKH) {
		this.maKH = maKH;
	}

	public KhachHang(String maKH, String tenKH, int sdt, String diaChi, boolean gioiTinh, int diemTichLuy) {
		this.maKH = maKH;
		this.tenKH = tenKH;
		this.sdt = sdt;
		this.diaChi = diaChi;
		this.gioiTinh = gioiTinh;
		this.diemTichLuy = diemTichLuy;
	}



	public String getMaKH() {
		return maKH;
	}
	public void setMaKH(String maKH) {
		this.maKH = maKH;
	}
	public String getTenKH() {
		return tenKH;
	}
	public void setTenKH(String tenKH) {
		this.tenKH = tenKH;
	}
	public int getSdt() {
		return sdt;
	}
	public void setSdt(int sdt) {
		this.sdt = sdt;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	public boolean isGioiTinh() {
		return gioiTinh;
	}
	public void setGioiTinh(boolean gioiTinh) {
		this.gioiTinh = gioiTinh;
	}
	public int getDiemTichLuy() {
		return diemTichLuy;
	}
	public void setDiemTichLuy(int diemTichLuy) {
		this.diemTichLuy = diemTichLuy;
	}

	//Hashcode kiem loi trung ma 

	@Override
	public int hashCode() {
		return Objects.hash(maKH);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		KhachHang other = (KhachHang) obj;
		return Objects.equals(maKH, other.maKH);
	}
	
}
