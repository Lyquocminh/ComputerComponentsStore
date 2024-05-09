package dao;
import java.util.List;

import entity.KhachHang;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class KhachHang_DAO {
	private EntityManager entityManager;
	
	public KhachHang_DAO(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public List<KhachHang> layDuLieuKhachHang() {
		return entityManager.createQuery("Select khachhang from KhachHang khachhang", KhachHang.class).getResultList();
	}
	

	public boolean themKhachHang(KhachHang khach) {
		EntityTransaction trans = entityManager.getTransaction();
		try {
			trans.begin();
			entityManager.persist(khach);
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
	
	//Tim kiem
	public KhachHang layDuLieuKhachHangTheoMa(String makh){
		return entityManager.createQuery("Select k from KhachHang k where k.maKH = :ma", KhachHang.class).setParameter("ma", makh).getSingleResult();
	}
	//Cap nhat
	public boolean capNhatKH(KhachHang kh) {
		// TODO Auto-generated method stub khang cap nhat diem thuong khi tao hd
		EntityTransaction trans = entityManager.getTransaction();
		try {
			trans.begin();
			entityManager.merge(kh);
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
