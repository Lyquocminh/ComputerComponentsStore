package dao;

import java.util.ArrayList;

import entity.BieuDoThuNhap;

public interface BieuDoThuNhap_DAO {
	public ArrayList<BieuDoThuNhap> layDanhSachThuNhap(String date1, String date2);
}
