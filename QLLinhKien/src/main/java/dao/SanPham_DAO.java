package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.NhaCungCap;
import entity.SanPham;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class SanPham_DAO {
	
	private EntityManager entityManager;

	public SanPham_DAO(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public List<SanPham> getDSSanPham(){
		return entityManager.createNamedQuery("SanPham.findAll", SanPham.class).getResultList();
	}
	public SanPham getSanPhamTheoMa(String ma) {
		return entityManager.find(SanPham.class, ma);
	}
	public boolean themSanPham(SanPham sp) throws SQLException {
		EntityTransaction trans = entityManager.getTransaction();
		try {
			trans.begin();
			entityManager.persist(sp);
			trans.commit();
			return true;
		} catch (Exception e) {
			if(trans.isActive())
				trans.rollback();
			e.printStackTrace();
		} finally {
			//entityManager.close();
		}
		return false;
	}	
	public boolean suaSanPham(SanPham sp) throws SQLException {
		EntityTransaction trans = entityManager.getTransaction();
		try {
			trans.begin();
			entityManager.merge(sp);
			trans.commit();
			return true;
		} catch (Exception e) {
			if(trans.isActive())
				trans.rollback();
			e.printStackTrace();
		} finally {
			//entityManager.close();
		}
		return false;
	}

	public boolean suaSoLuongSPTheoMa(String maSP, int soLuongNew) throws SQLException {
		EntityTransaction trans = entityManager.getTransaction();
		SanPham sp = entityManager.find(SanPham.class,maSP);
		sp.setSoLuongTon(soLuongNew);
		try {
			trans.begin();
			entityManager.merge(sp);
			trans.commit();
			return true;
		} catch (Exception e) {
			if(trans.isActive())
				trans.rollback();
			e.printStackTrace();
		} finally {
			//entityManager.close();
		}
		return false;
	}	
	public boolean xoaSanPhamTheoMa(String maSP) throws SQLException {
		EntityTransaction transaction = entityManager.getTransaction();
		SanPham sp = entityManager.find(SanPham.class, maSP);
		try {
			transaction.begin();
			entityManager.remove(sp);
			transaction.commit();
			return true;
		} catch (Exception e) {
			if (transaction.isActive())
				transaction.rollback();
			e.printStackTrace();
		} finally {
			//entityManager.close();
		}
		return false;
	}
	
	public boolean themVaoHoaDon2(String maHD, int soLuong, String maSP) {
	    // Generate a random number for maCTHD
	    Random rand = new Random();
	    int randomNumber = rand.nextInt(100); // Adjust 100 according to your range
	    String maCTHD = "CTHD" + String.format("%02d", randomNumber);

	    try {
	        entityManager.getTransaction().begin();

	        int rowsAffected = entityManager.createQuery("INSERT INTO ChiTietHoaDon (maCTHD, soLuong, maHD, maSP) VALUES (:maCTHD, :soLuong, :maHD, :maSP)")
	                .setParameter("maCTHD", maCTHD)
	                .setParameter("soLuong", soLuong)
	                .setParameter("maHD", maHD)
	                .setParameter("maSP", maSP)
	                .executeUpdate();

	        entityManager.getTransaction().commit();

	        return rowsAffected > 0;
	    } catch (Exception e) {
	        if (entityManager.getTransaction().isActive()) {
	            entityManager.getTransaction().rollback();
	        }
	        e.printStackTrace();
	        return false;
	    }
	}




	

}
