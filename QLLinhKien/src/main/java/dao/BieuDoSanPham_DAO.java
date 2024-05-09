package dao;

import java.util.ArrayList;

import entity.BieuDoSanPham;

public interface BieuDoSanPham_DAO {
	public ArrayList<BieuDoSanPham> layDanhSachSanPham(String date1, String date2);
}
