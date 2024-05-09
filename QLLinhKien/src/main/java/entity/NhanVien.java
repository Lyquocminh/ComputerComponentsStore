package entity;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedNativeQueries;
import jakarta.persistence.NamedNativeQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "NhanVien")
public class NhanVien implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4414216585579309057L;
	@Id
	private String maNV;
	private String tenNV;
	private int sdt;
	private String diaChi;
	private boolean gioiTinh;
	@OneToOne
	@JoinColumn(name = "tenTK")
	private TaiKhoan tk;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "loai")
	private LoaiNhanVien loainv;
	public NhanVien(String maNV, String tenNV, int sdt, String diaChi, boolean gioiTinh, TaiKhoan tk,
			LoaiNhanVien loainv) {
		
		this.maNV = maNV;
		this.tenNV = tenNV;
		this.sdt = sdt;
		this.diaChi = diaChi;
		this.gioiTinh = gioiTinh;
		this.tk = tk;
		this.loainv = loainv;
	}
	
	public NhanVien(String maNV, String tenNV, int sdt, String diaChi, boolean gioiTinh) {
		this.maNV = maNV;
		this.tenNV = tenNV;
		this.sdt = sdt;
		this.diaChi = diaChi;
		this.gioiTinh = gioiTinh;
	}

	public NhanVien(String maNV, String tenNV, int sdt, String diaChi, boolean gioiTinh, TaiKhoan tk) {
		
		this.maNV = maNV;
		this.tenNV = tenNV;
		this.sdt = sdt;
		this.diaChi = diaChi;
		this.gioiTinh = gioiTinh;
		this.tk = tk;
	}

	public NhanVien() {
		
	}
	public String getMaNV() {
		return maNV;
	}
	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}
	public String getTenNV() {
		return tenNV;
	}
	public void setTenNV(String tenNV) {
		this.tenNV = tenNV;
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
	public TaiKhoan getTk() {
		return tk;
	}
	public void setTk(TaiKhoan tk) {
		this.tk = tk;
	}
	public LoaiNhanVien getLoainv() {
		return loainv;
	}
	public void setLoainv(LoaiNhanVien loainv) {
		this.loainv = loainv;
	}
	@Override
	public String toString() {
		return "NhanVien [maNV=" + maNV + ", tenNV=" + tenNV + ", sdt=" + sdt + ", diaChi=" + diaChi + ", gioiTinh="
				+ gioiTinh + ", tk=" + tk + ", loainv=" + loainv + "]";
	}
	
}
