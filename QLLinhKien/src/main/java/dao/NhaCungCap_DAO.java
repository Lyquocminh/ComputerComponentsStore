package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.List;


import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import entity.NhaCungCap;
import entity.SanPham;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class NhaCungCap_DAO {
	private EntityManager entityManager;
	
	public NhaCungCap_DAO(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public List<NhaCungCap> docDuLieu() {
		return entityManager.createNamedQuery("NhaCungCap.findAll", NhaCungCap.class).getResultList();
	}
	public NhaCungCap getNhaCungCapTheoMa(String maNCC) {
		return entityManager.find(NhaCungCap.class, maNCC);
	}
	public boolean themNhaCungCap(NhaCungCap ncc) throws SQLException {
		EntityTransaction trans = entityManager.getTransaction();
		try {
			trans.begin();
			entityManager.persist(ncc);
			trans.commit();
			return true;
		} catch (Exception e) {
			if(trans.isActive()) {
				trans.rollback();
			}
			e.printStackTrace();
		} finally {
			entityManager.close();
		}
		return false;
	}	
	
}
