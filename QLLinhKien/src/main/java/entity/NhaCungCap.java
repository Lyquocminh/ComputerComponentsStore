package entity;

import java.io.Serializable;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "NhaCungCap")
@NamedQueries({
		// get all NhaCungCap
		@NamedQuery(name = "NhaCungCap.findAll", query = "SELECT n FROM NhaCungCap n"),
		@NamedQuery(name = "NhaCungCap.findByMaNCC", query = "SELECT n FROM NhaCungCap n WHERE n.maNCC = :maNCC")
		
})
public class NhaCungCap implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6038209105897075304L;
	@Id
	private String maNCC;
	private String tenNCC;
	private String diaChi;
	private String sdt;
	@OneToMany(mappedBy = "nhaCungCap", fetch = FetchType.LAZY)
	private Set<SanPham> sanPham;
	
	public NhaCungCap() {
	}
	public NhaCungCap(String maNCC, String tenNCC, String diaChi, String sdt) {
		this.maNCC = maNCC;
		this.tenNCC = tenNCC;
		this.diaChi = diaChi;
		this.sdt = sdt;
	}
	public String getMaNCC() {
		return maNCC;
	}
	public void setMaNCC(String maNCC) {
		this.maNCC = maNCC;
	}
	public String getTenNCC() {
		return tenNCC;
	}
	public void setTenNCC(String tenNCC) {
		this.tenNCC = tenNCC;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	public String getSdt() {
		return sdt;
	}
	public void setSdt(String sdt) {
		this.sdt = sdt;
	}
	@Override
	public String toString() {
		return "NhaCungCap [maNCC=" + maNCC + ", tenNCC=" + tenNCC + ", diaChi=" + diaChi + ", sdt=" + sdt + "]";
	}
	
}
