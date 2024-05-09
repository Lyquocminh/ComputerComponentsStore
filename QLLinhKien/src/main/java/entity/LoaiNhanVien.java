package entity;

import java.io.Serializable;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedNativeQueries;
import jakarta.persistence.NamedNativeQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "LoaiNhanVien")
@NamedNativeQueries({
	@NamedNativeQuery(name = "LoaiNhanVien.findByMaNV", query = "SELECT * " +
            "FROM LoaiNhanVien lnv " +
            "INNER JOIN NhanVien nv ON lnv.Loai = nv.Loai " +
            "WHERE nv.maNV = :maNV"),
})
public class LoaiNhanVien implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5960706493231402663L;
	@Id
	private String loai;
	@OneToMany(mappedBy = "loainv", fetch = FetchType.LAZY)
	private Set<NhanVien> nhanVien;
	public LoaiNhanVien(String loai, Set<NhanVien> nhanVien) {
		this.loai = loai;
		this.nhanVien = nhanVien;
	}
	
	public LoaiNhanVien(String loai) {
		
		this.loai = loai;
	}

	public LoaiNhanVien() {	
	}
	public String getLoai() {
		return loai;
	}
	public void setLoai(String loai) {
		this.loai = loai;
	}
	public Set<NhanVien> getNhanVien() {
		return nhanVien;
	}
	public void setNhanVien(Set<NhanVien> nhanVien) {
		this.nhanVien = nhanVien;
	}
	@Override
	public String toString() {
		return "LoaiNhanVien [loai=" + loai + ", nhanVien=" + nhanVien + "]";
	}
	
	
}
