package dao;

import java.util.ArrayList;

import entity.BieuDoLoiNhuan;

public interface BieuDoLoiNhuan_DAO {
	public ArrayList<BieuDoLoiNhuan> layDanhSach(String date1, String date2);
}
