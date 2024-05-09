package entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "SanPham")
@NamedQueries({
    @NamedQuery(name = "SanPham.findAll", query = "SELECT sp FROM SanPham sp"),
    @NamedQuery(name = "SanPham.findByMaSP", query = "SELECT sp FROM SanPham sp WHERE sp.maSP = :maSP")
})
public class SanPham implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7248331413616021821L;
	@Id
	private String maSP;
	private String tenSP;
	private int soLuongTon;
	private double giaBan;
	private double giaMua;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "maNCC") 
	private NhaCungCap nhaCungCap;
	@OneToMany(mappedBy = "sp", fetch = FetchType.EAGER)
	private List<ChiTietHoaDon> chiTietHoaDon;
	public SanPham() {
		
	}
	public SanPham(String maSP, String tenSP, int soLuongTon, double giaBan, double giaMua, NhaCungCap nhaCungCap) {
		this.maSP = maSP;
		this.tenSP = tenSP;
		this.soLuongTon = soLuongTon;
		this.giaBan = giaBan;
		this.giaMua = giaMua;
		this.nhaCungCap = nhaCungCap;
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
	public int getSoLuongTon() {
		return soLuongTon;
	}
	public void setSoLuongTon(int soLuongTon) {
		this.soLuongTon = soLuongTon;
	}
	public double getGiaBan() {
		return giaBan;
	}
	public void setGiaBan(double giaBan) {
		this.giaBan = giaBan;
	}
	public double getGiaMua() {
		return giaMua;
	}
	public void setGiaMua(double giaMua) {
		this.giaMua = giaMua;
	}
	public NhaCungCap getNhaCungCap() {
		return nhaCungCap;
	}
	public void setNhaCungCap(NhaCungCap nhaCungCap) {
		this.nhaCungCap = nhaCungCap;
	}
	@Override
	public String toString() {
		return "SanPham [maSP=" + maSP + ", tenSP=" + tenSP + ", soLuongTon=" + soLuongTon + ", giaBan=" + giaBan
				+ ", giaMua=" + giaMua + ", nhaCungCap=" + nhaCungCap + "]";
	}
	 
	
}
