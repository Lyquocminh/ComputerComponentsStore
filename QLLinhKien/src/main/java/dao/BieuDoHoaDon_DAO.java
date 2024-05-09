package dao;

import java.util.ArrayList;
import java.util.List;

import entity.BieuDoHoaDon;

public interface BieuDoHoaDon_DAO {
	public ArrayList<BieuDoHoaDon> layDanhSachHoaDon(String date1, String date2);
}
