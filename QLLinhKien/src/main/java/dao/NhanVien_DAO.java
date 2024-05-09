package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import entity.LoaiNhanVien;
import entity.NhanVien;
import entity.TaiKhoan;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class NhanVien_DAO{
	private EntityManager entityManager;
	public NhanVien_DAO(EntityManager entityManager){
		this.entityManager = entityManager;
	}

	public List<NhanVien> layDuLieuNhanVien(){
		return entityManager.createNativeQuery("SELECT * FROM NhanVien", NhanVien.class).getResultList();
	}
	
	public boolean themNhanVien(NhanVien nv, TaiKhoan tk){
		EntityTransaction trans = entityManager.getTransaction();
		try {
			trans.begin();
			entityManager.persist(tk);
			entityManager.persist(nv);
			trans.commit();
			return true;
		} catch (Exception e) {
			if (trans.isActive()) {
				trans.rollback();
			}
			e.printStackTrace();
		}
		return false;
	}

	
	public List<NhanVien> layDuLieuNhanVienTheoMa(String manv){
		return entityManager.createNativeQuery("SELECT * FROM NhanVien WHERE maNV = :maNV", NhanVien.class)
				.setParameter("maNV", manv)
				.getResultList();
	}

	public boolean capNhatNV(NhanVien nv){
		EntityTransaction trans = entityManager.getTransaction();
		try {
			trans.begin();
			//NhanVien s1 = entityManager.find(NhanVien.class, id);
			//s1.setName(newName);
			entityManager.merge(nv);
			trans.commit();
			return true;
		} catch (Exception e) {
			if (trans.isActive()) {
				trans.rollback();
			}
			e.printStackTrace();
		}
		return false;
	}
}
