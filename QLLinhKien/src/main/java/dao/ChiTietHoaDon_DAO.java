package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.SanPham;
import jakarta.persistence.EntityManager;

public class ChiTietHoaDon_DAO {
	
	private EntityManager entityManager;

	public ChiTietHoaDon_DAO(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public ArrayList<ChiTietHoaDon> layDuLieuHoaDonTheoMa(String maHoaDon) {
	    HoaDon hoaDon = entityManager.find(HoaDon.class, maHoaDon);
	    if (hoaDon != null) {
	        return (ArrayList<ChiTietHoaDon>) entityManager.createNamedQuery("ChiTietHoaDon.findByMaHD", ChiTietHoaDon.class)
	                .setParameter("maHD", hoaDon)
	                .getResultList();
	    } else {
	        return new ArrayList<>(); // Return empty list if hoaDon is null
	    }
	}
	public boolean themVaoHoaDon(String maHD, int soLuong, String maSP) {
	    try {
	        entityManager.getTransaction().begin();
	        
	        // Insert into HoaDon
	        Random rand = new Random();
	        int randomNumber = rand.nextInt(100); // Adjust 100 according to your range
	        String maCTHD = "CTHD" + String.format("%02d", randomNumber);
	        HoaDon hoaDon = new HoaDon();
	        hoaDon.setMaHD(maHD);
	        hoaDon.setSoLuongGiam(0); // Set your default values here
	        hoaDon.setThue(0.02); // Set your default values here

	        // Insert into ChiTietHoaDon
	        ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon();
	        chiTietHoaDon.setMaCTHD(maCTHD);
	        chiTietHoaDon.setMaHD(hoaDon);
	        chiTietHoaDon.setSoLuong(soLuong);
	        // Fetch the SanPham object based on maSP and set it to chiTietHoaDon
	        SanPham sanPham = entityManager.find(SanPham.class, maSP);
	        chiTietHoaDon.setSp(sanPham);
	        entityManager.persist(chiTietHoaDon);

	        entityManager.getTransaction().commit();

	        return true;
	    } catch (Exception e) {
	        e.printStackTrace(); // Log or handle the exception as needed
	        return false;
	    }
	}

}
