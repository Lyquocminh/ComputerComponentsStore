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


import entity.TaiKhoan;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class TaiKhoan_DAO{
	private EntityManager entityManager;
	public TaiKhoan_DAO(EntityManager entityManager){
		this.entityManager = entityManager;
	}
	
	public List<TaiKhoan> getalltbTaiKhoan(){
		return entityManager.createNativeQuery("SELECT * FROM TaiKhoan", TaiKhoan.class)
				.getResultList();
	}

	
	public boolean taoTK(TaiKhoan tk){
		EntityTransaction trans = entityManager.getTransaction();
		try {
			trans.begin();
			entityManager.persist(tk);
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

	
	public boolean doiMatKhau(TaiKhoan tk, String mkMoi){
		EntityTransaction trans = entityManager.getTransaction();
		try {
			trans.begin();
			TaiKhoan taiKhoan = entityManager.find(TaiKhoan.class, tk.getTenTK());
			taiKhoan.setMatKhau(mkMoi);
			entityManager.merge(taiKhoan);
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


	
	public TaiKhoan getTKTheoTen(String tenTK){
		return entityManager.find(TaiKhoan.class, tenTK);
				
	}

	
	public TaiKhoan getTKTheoMK(String mk){
		return entityManager.createQuery("SELECT tk FROM TaiKhoan tk WHERE tk.matKhau = :matKhau", TaiKhoan.class)
				.setParameter("matKhau", mk)
				.getSingleResult();
	}
}
