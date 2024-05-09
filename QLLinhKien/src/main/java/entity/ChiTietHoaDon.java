package entity;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "ChiTietHoaDon")
@NamedQueries({
	// get chi tiet hoa don by maHD
	@NamedQuery(name = "ChiTietHoaDon.findByMaHD", query = "SELECT c FROM ChiTietHoaDon c WHERE c.maHD = :maHD")
})
public class ChiTietHoaDon implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4842803701302839977L;
	@Id
	private String maCTHD;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "maHD")
	private HoaDon maHD;
	private int soLuong;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "maSP")
	private SanPham sp;
	public ChiTietHoaDon() {
		super();
	}
	public ChiTietHoaDon(HoaDon maHD, int soLuong, SanPham sp) {
		super();
		this.maHD = maHD;
		this.soLuong = soLuong;
		this.sp = sp;
	}
	public HoaDon getMaHD() {
		return maHD;
	}
	public void setMaHD(HoaDon maHD) {
		this.maHD = maHD;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	public SanPham getSp() {
		return sp;
	}
	public void setSp(SanPham sp) {
		this.sp = sp;
	}
	@Override
	public String toString() {
		return "ChiTietHoaDon [maHD=" + maHD + ", soLuong=" + soLuong + ", sp=" + sp + "]";
	}
	public ChiTietHoaDon(HoaDon maHD) {
		super();
		this.maHD = maHD;
	}
	
	public String getMaCTHD() {
		return maCTHD;
	}
	public void setMaCTHD(String maCTHD) {
		this.maCTHD = maCTHD;
	}
	public ChiTietHoaDon(SanPham sp) {
		super();
		this.sp = sp;
	}
	
	
}
