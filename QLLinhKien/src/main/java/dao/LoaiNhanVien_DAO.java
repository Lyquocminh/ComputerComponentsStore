package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.LoaiNhanVien;
import entity.NhanVien;
import entity.TaiKhoan;
import jakarta.persistence.EntityManager;

public class LoaiNhanVien_DAO{
	private EntityManager entityManager;
	public LoaiNhanVien_DAO(EntityManager entityManager){
		this.entityManager = entityManager;
	}
	
	public List<LoaiNhanVien> layDuLieuLoaiNhanVienTheoMa(String manv){
		return entityManager.createNativeQuery("LoaiNhanVien.findByMaNV", LoaiNhanVien.class)
				.setParameter("maNV", "%" + manv + "%")
				.getResultList();
	}
}
