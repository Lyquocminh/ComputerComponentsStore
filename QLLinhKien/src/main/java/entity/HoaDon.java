package entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DialectOverride.ColumnDefaults;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
@Entity
@Table(name = "HoaDon")
public class HoaDon implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -775320372205846897L;
	@Id
	private String maHD;
	private LocalDate ngayTao;
	@OneToMany(mappedBy = "maHD")
	private List<ChiTietHoaDon> chiTietHoaDon;
	
	@ManyToOne
	@JoinColumn(name = "maKH")
	private KhachHang khachHang;
	
	@ManyToOne
	@JoinColumn(name = "maNV")
	private NhanVien nhanVien;
	
	@Column(nullable = true)
	private double soLuongGiam;
	
	@Column(nullable = true)
	private double thue;
	public HoaDon() {
		
	}
	
	public List<ChiTietHoaDon> getChiTietHoaDon() {
		return chiTietHoaDon;
	}

	public void setChiTietHoaDon(List<ChiTietHoaDon> chiTietHoaDon) {
		this.chiTietHoaDon = chiTietHoaDon;
	}

	public HoaDon(String maHD, LocalDate ngayTao, KhachHang khachHang, NhanVien nhanVien, double soLuongGiam,
			double thue) {
		this.maHD = maHD;
		this.ngayTao = ngayTao;
		this.khachHang = khachHang;
		this.nhanVien = nhanVien;
		this.soLuongGiam = soLuongGiam;
		this.thue = thue;
	}
	public String getMaHD() {
		return maHD;
	}
	public void setMaHD(String maHD) {
		this.maHD = maHD;
	}
	public LocalDate getNgayTao() {
		return ngayTao;
	}
	public void setNgayTao(LocalDate ngayTao) {
		this.ngayTao = ngayTao;
	}
	public KhachHang getKhachHang() {
		return khachHang;
	}
	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}
	public NhanVien getNhanVien() {
		return nhanVien;
	}
	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}
	public double getSoLuongGiam() {
		return soLuongGiam;
	}
	public void setSoLuongGiam(double soLuongGiam) {
		this.soLuongGiam = soLuongGiam;
	}
	public double getThue() {
		return thue;
	}
	public void setThue(double thue) {
		this.thue = thue;
	}
	@Override
	public String toString() {
		return "HoaDon [maHD=" + maHD + ", ngayTao=" + ngayTao + ", khachHang=" + khachHang + ", nhanVien=" + nhanVien
				+ ", soLuongGiam=" + soLuongGiam + ", thue=" + thue + "]";
	}
	
}
