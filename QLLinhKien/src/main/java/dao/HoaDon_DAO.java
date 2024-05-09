package dao;

import java.util.Date;
import java.util.List;

import entity.HoaDon;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class HoaDon_DAO {
	private EntityManager entityManager;
	
	
	public HoaDon_DAO(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	public List<HoaDon> getDSHoaDon() {
		return entityManager.createQuery("Select hoadon from HoaDon hoadon", HoaDon.class).getResultList();
	}
	public HoaDon getHoaDonTheoMa(String maHD) {
		return entityManager.find(HoaDon.class, maHD);
	}

	public boolean taoHDMoi(String maHD, Date ngay) {
		try {
		    entityManager.getTransaction().begin();

		    int rowsAffected = entityManager.createQuery("INSERT INTO HoaDon (ngayTao, soLuongGiam, thue, maHD) VALUES (:ngayTao, :soLuongGiam, :thue, :maHD)")
		        .setParameter("maHD", maHD)
		        .setParameter("ngayTao", ngay)
		        .setParameter("soLuongGiam", 0)
		        .setParameter("thue", 0.02)
		        .executeUpdate();

		    entityManager.getTransaction().commit();

		    return rowsAffected > 0;
		} catch (Exception e) {
		    if (entityManager.getTransaction().isActive()) {
		        entityManager.getTransaction().rollback();
		    }
		    throw e;
		}

	}
	
	public boolean capNhatHoaDon(HoaDon hd) {
		EntityTransaction trans = entityManager.getTransaction();
		try {
			trans.begin();
			entityManager.merge(hd);
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
