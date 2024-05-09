package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.formdev.flatlaf.FlatLightLaf;

import dao.ChiTietHoaDon_DAO;
import service.EntityManagerFactoryUtil;
import dao.HoaDon_DAO;
import dao.NhanVien_DAO;
import dao.KhachHang_DAO;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.KhachHang;
import jakarta.persistence.EntityManager;

import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

public class ChiTietHoaDon_GUI extends JFrame implements ActionListener, MouseListener{

	private static final long serialVersionUID = 1L;
	private String currentMaHD;
	private JPanel panelTong;
	private JLabel lblNgayLap;
	private JTextField txtNgayLap;
	private JTextField txtTenKH;
	private JTextField txtTenNV;
	private DefaultTableModel modelTableChiTietHoaDon;
	private JTable tblChiTietHoaDon;
	private JTextField txtGiamTru;
	private JTextField txtThue;
	private JTextField txtTongTien;
	private JTextField txtMaKH;
	private JTextField txtTenKhachHang;
	private JTextField txtDiaChi;
	private JTextField txtSDT;
	private DefaultTableModel modelTableGioHang;
	private JTable tblGioHang;
	private JRadioButton rdbtnNam;
	private JRadioButton rdbtnNu;
	private JComboBox<String> cbmaKH;
	private JComboBox<String> comboBox;
	private KhachHang_DAO khachHang_dao;
	private JButton btnThem, btnXoa, btnXuat;
	private ChiTietHoaDon_DAO cthd_dao;
	private JLabel lblMaHD;
	private ArrayList<ChiTietHoaDon> list;
	private double giamTru;
	private int thanhTien;
	private EntityManagerFactoryUtil entityManagerFactoryUtil = new EntityManagerFactoryUtil();
	private EntityManager entityManager = entityManagerFactoryUtil.getEnManager();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//FlatLightLaf.setup();
					ChiTietHoaDon_GUI frame = new ChiTietHoaDon_GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public ChiTietHoaDon_GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		panelTong = new JPanel();
		panelTong.setBackground(Color.WHITE);
		panelTong.setBorder(new EmptyBorder(5, 5, 5, 5));
		//Khoi tao ket noi den SQL

				//Khoi tao DAO
		khachHang_dao = new KhachHang_DAO(entityManager);
		cthd_dao = new ChiTietHoaDon_DAO(entityManager);
		setContentPane(panelTong);
		panelTong.setLayout(null);
		
		JPanel panelTren = new JPanel();
		panelTren.setLayout(null);
		panelTren.setBackground(new Color(0, 153, 255));
		panelTren.setBounds(0, 0, 1283, 52);
		panelTong.add(panelTren);
		
		JLabel lblTieuDe1 = new JLabel("HÓA ĐƠN");
		lblTieuDe1.setHorizontalAlignment(SwingConstants.CENTER);
		lblTieuDe1.setForeground(Color.WHITE);
		lblTieuDe1.setFont(new Font("Arial", Font.BOLD, 26));
		lblTieuDe1.setBounds(402, 6, 478, 40);
		panelTren.add(lblTieuDe1);
		
		JPanel panelTrai = new JPanel();
		panelTrai.setBackground(new Color(255, 240, 245));
		panelTrai.setBounds(10, 63, 678, 591);
		panelTong.add(panelTrai);
		panelTrai.setLayout(null);
		
		JPanel panelTieuDe2 = new JPanel();
		panelTieuDe2.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panelTieuDe2.setBackground(new Color(255, 204, 51));
		panelTieuDe2.setBounds(10, 11, 109, 21);
		panelTrai.add(panelTieuDe2);
		panelTieuDe2.setLayout(null);
		
		JLabel lblTieuDe2 = new JLabel("Chi tiết hóa đơn");
		lblTieuDe2.setHorizontalAlignment(SwingConstants.CENTER);
		lblTieuDe2.setBounds(0, 1, 109, 20);
		panelTieuDe2.add(lblTieuDe2);
		lblTieuDe2.setFont(new Font("Arial", Font.PLAIN, 14));
		
		lblMaHD = new JLabel("Mã hóa đơn:");
		lblMaHD.setHorizontalAlignment(SwingConstants.LEFT);
		lblMaHD.setFont(new Font("Arial", Font.PLAIN, 14));
		lblMaHD.setBounds(483, 11, 185, 21);
		panelTrai.add(lblMaHD);
		
		JLabel lblTieuDe3 = new JLabel("Hóa đơn bán hàng");
		lblTieuDe3.setHorizontalAlignment(SwingConstants.CENTER);
		lblTieuDe3.setFont(new Font("Arial", Font.BOLD, 18));
		lblTieuDe3.setBounds(227, 11, 224, 42);
		panelTrai.add(lblTieuDe3);
		
		lblNgayLap = new JLabel("Ngày lập:");
		lblNgayLap.setHorizontalAlignment(SwingConstants.LEFT);
		lblNgayLap.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNgayLap.setBounds(10, 64, 84, 21);
		panelTrai.add(lblNgayLap);
		
		txtNgayLap = new JTextField();
		txtNgayLap.setHorizontalAlignment(SwingConstants.LEFT);
		txtNgayLap.setFont(new Font("Arial", Font.PLAIN, 14));
		txtNgayLap.setBounds(104, 64, 172, 20);
		panelTrai.add(txtNgayLap);
		txtNgayLap.setColumns(10);
		
		Date ngayHT = new Date();
        SimpleDateFormat ngay = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDate = ngay.format(ngayHT); 
        txtNgayLap.setText(formattedDate);
        txtNgayLap.setEditable(false);
		
		JLabel lblTenKH = new JLabel("Khách hàng:");
		lblTenKH.setHorizontalAlignment(SwingConstants.LEFT);
		lblTenKH.setFont(new Font("Arial", Font.PLAIN, 14));
		lblTenKH.setBounds(10, 96, 84, 21);
		panelTrai.add(lblTenKH);
		
		txtTenKH = new JTextField();
		txtTenKH.setHorizontalAlignment(SwingConstants.LEFT);
		txtTenKH.setFont(new Font("Arial", Font.PLAIN, 14));
		txtTenKH.setColumns(10);
		txtTenKH.setBounds(104, 96, 172, 20);
		panelTrai.add(txtTenKH);
		
		JLabel lblTenNV = new JLabel("Nhân viên:");
		lblTenNV.setHorizontalAlignment(SwingConstants.LEFT);
		lblTenNV.setFont(new Font("Arial", Font.PLAIN, 14));
		lblTenNV.setBounds(10, 127, 84, 21);
		panelTrai.add(lblTenNV);
		
		txtTenNV = new JTextField();
		txtTenNV.setHorizontalAlignment(SwingConstants.LEFT);
		txtTenNV.setFont(new Font("Arial", Font.PLAIN, 14));
		txtTenNV.setColumns(10);
		txtTenNV.setEditable(false);
		txtTenNV.setBounds(104, 127, 172, 20);
		panelTrai.add(txtTenNV);
		
		JPanel panelPhaiTren = new JPanel();
		panelPhaiTren.setLayout(null);
		panelPhaiTren.setBackground(new Color(255, 240, 245));
		panelPhaiTren.setBounds(698, 63, 575, 254);
		panelTong.add(panelPhaiTren);
		
		JPanel panelTieuDe4 = new JPanel();
		panelTieuDe4.setLayout(null);
		panelTieuDe4.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panelTieuDe4.setBackground(new Color(255, 204, 51));
		panelTieuDe4.setBounds(10, 11, 109, 21);
		panelPhaiTren.add(panelTieuDe4);
		
		JLabel lblKhchHng = new JLabel("Khách hàng");
		lblKhchHng.setHorizontalAlignment(SwingConstants.CENTER);
		lblKhchHng.setFont(new Font("Arial", Font.PLAIN, 14));
		lblKhchHng.setBounds(0, 1, 109, 20);
		panelTieuDe4.add(lblKhchHng);
		
		JLabel lblMaKH = new JLabel("Mã khách hàng:");
		lblMaKH.setHorizontalAlignment(SwingConstants.LEFT);
		lblMaKH.setFont(new Font("Arial", Font.PLAIN, 14));
		lblMaKH.setBounds(55, 43, 109, 21);
		panelPhaiTren.add(lblMaKH);
		
		cbmaKH = new JComboBox<String>();
		cbmaKH.setFont(new Font("Arial", Font.PLAIN, 14));
		cbmaKH.setBackground(Color.WHITE);
		cbmaKH.setBounds(174, 43, 100, 20);
			
		List<KhachHang> listKH = khachHang_dao.layDuLieuKhachHang();
		for (KhachHang kh : listKH) {
			cbmaKH.addItem(kh.getMaKH());
		}
		panelPhaiTren.add(cbmaKH);
		
		JLabel lblTenKhachHang = new JLabel("Tên khách hàng:");
		lblTenKhachHang.setHorizontalAlignment(SwingConstants.LEFT);
		lblTenKhachHang.setFont(new Font("Arial", Font.PLAIN, 14));
		lblTenKhachHang.setBounds(55, 75, 109, 21);
		panelPhaiTren.add(lblTenKhachHang);
		
		txtTenKhachHang = new JTextField();
		txtTenKhachHang.setHorizontalAlignment(SwingConstants.LEFT);
		txtTenKhachHang.setFont(new Font("Arial", Font.PLAIN, 14));
		txtTenKhachHang.setColumns(10);
		txtTenKhachHang.setBounds(174, 75, 346, 20);
		panelPhaiTren.add(txtTenKhachHang);
		
		JLabel lblDiaChi = new JLabel("Địa chỉ:");
		lblDiaChi.setHorizontalAlignment(SwingConstants.LEFT);
		lblDiaChi.setFont(new Font("Arial", Font.PLAIN, 14));
		lblDiaChi.setBounds(55, 107, 109, 21);
		panelPhaiTren.add(lblDiaChi);
		
		txtDiaChi = new JTextField();
		txtDiaChi.setHorizontalAlignment(SwingConstants.LEFT);
		txtDiaChi.setFont(new Font("Arial", Font.PLAIN, 14));
		txtDiaChi.setColumns(10);
		txtDiaChi.setBounds(174, 107, 346, 20);
		panelPhaiTren.add(txtDiaChi);
		
		JLabel lblSDT = new JLabel("Số điện thoại:");
		lblSDT.setHorizontalAlignment(SwingConstants.LEFT);
		lblSDT.setFont(new Font("Arial", Font.PLAIN, 14));
		lblSDT.setBounds(55, 139, 109, 21);
		panelPhaiTren.add(lblSDT);
		
		JLabel lblGioiTinh = new JLabel("Giới tính:");
		lblGioiTinh.setHorizontalAlignment(SwingConstants.LEFT);
		lblGioiTinh.setFont(new Font("Arial", Font.PLAIN, 14));
		lblGioiTinh.setBounds(55, 171, 109, 21);
		panelPhaiTren.add(lblGioiTinh);
		
		JLabel lblBac = new JLabel("Bậc:");
		lblBac.setHorizontalAlignment(SwingConstants.LEFT);
		lblBac.setFont(new Font("Arial", Font.PLAIN, 14));
		lblBac.setBounds(55, 203, 55, 21);
		panelPhaiTren.add(lblBac);
		
		txtSDT = new JTextField();
		txtSDT.setHorizontalAlignment(SwingConstants.LEFT);
		txtSDT.setFont(new Font("Arial", Font.PLAIN, 14));
		txtSDT.setColumns(10);
		txtSDT.setBounds(174, 138, 346, 20);
		panelPhaiTren.add(txtSDT);
		
		rdbtnNam = new JRadioButton("Nam");
		rdbtnNam.setFont(new Font("Arial", Font.PLAIN, 14));
		rdbtnNam.setBackground(new Color(255, 240, 245));
		rdbtnNam.setBounds(174, 170, 55, 23);
		rdbtnNam.setEnabled(false);
		panelPhaiTren.add(rdbtnNam);
		
		rdbtnNu = new JRadioButton("Nữ");
		rdbtnNu.setFont(new Font("Arial", Font.PLAIN, 14));
		rdbtnNu.setBackground(new Color(255, 240, 245));
		rdbtnNu.setBounds(299, 170, 55, 23);
		rdbtnNu.setEnabled(false);
		panelPhaiTren.add(rdbtnNu);
		
		comboBox = new JComboBox<String>();
		comboBox.setFont(new Font("Arial", Font.PLAIN, 14));
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Đồng", "Bạc", "Vàng", "Bạch kim"}));
		comboBox.setBackground(Color.WHITE);
		comboBox.setBounds(174, 202, 157, 22);
		comboBox.setEnabled(false);
		panelPhaiTren.add(comboBox);
		setSize(1300, 700);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//Table hoa don
		String[] header = {"STT","Tên sản phẩm","Số lượng","Giá thành"};
		modelTableChiTietHoaDon = new DefaultTableModel(header, 0);
		tblChiTietHoaDon = new JTable(modelTableChiTietHoaDon);
		tblChiTietHoaDon.setBackground(Color.WHITE);
		tblChiTietHoaDon.setFont(new Font("Arial", Font.PLAIN, 14));
		tblChiTietHoaDon.setRowHeight(20);
		JScrollPane scrollPane = new JScrollPane(tblChiTietHoaDon);
		scrollPane.setSize(658, 333);
		scrollPane.setLocation(10, 159);
		panelTrai.add(scrollPane);
		
		JLabel lblGiaTru = new JLabel("Giảm trừ:");
		lblGiaTru.setHorizontalAlignment(SwingConstants.LEFT);
		lblGiaTru.setFont(new Font("Arial", Font.PLAIN, 14));
		lblGiaTru.setBounds(442, 503, 84, 21);
		panelTrai.add(lblGiaTru);
		
		txtGiamTru = new JTextField();
		txtGiamTru.setHorizontalAlignment(SwingConstants.LEFT);
		txtGiamTru.setFont(new Font("Arial", Font.PLAIN, 14));
		txtGiamTru.setColumns(10);
		txtGiamTru.setBounds(526, 503, 142, 20);
		txtGiamTru.setEditable(false);
		panelTrai.add(txtGiamTru);
		
		JLabel lblThue = new JLabel("Thuế:");
		lblThue.setHorizontalAlignment(SwingConstants.LEFT);
		lblThue.setFont(new Font("Arial", Font.PLAIN, 14));
		lblThue.setBounds(442, 535, 83, 21);
		panelTrai.add(lblThue);
		
		txtThue = new JTextField();
		txtThue.setHorizontalAlignment(SwingConstants.LEFT);
		txtThue.setFont(new Font("Arial", Font.PLAIN, 14));
		txtThue.setColumns(10);
		txtThue.setBounds(526, 535, 142, 20);
		txtThue.setEditable(false);
		panelTrai.add(txtThue);
		
		JLabel lblTongTien = new JLabel("Tổng tiền:");
		lblTongTien.setHorizontalAlignment(SwingConstants.LEFT);
		lblTongTien.setFont(new Font("Arial", Font.PLAIN, 14));
		lblTongTien.setBounds(442, 567, 84, 21);
		panelTrai.add(lblTongTien);
		
		txtTongTien = new JTextField();
		txtTongTien.setHorizontalAlignment(SwingConstants.LEFT);
		txtTongTien.setFont(new Font("Arial", Font.PLAIN, 14));
		txtTongTien.setColumns(10);
		txtTongTien.setBounds(526, 566, 142, 20);
		txtTongTien.setEditable(false);
		panelTrai.add(txtTongTien);
		
		JPanel panelPhaiDuoi = new JPanel();
		panelPhaiDuoi.setLayout(null);
		panelPhaiDuoi.setBackground(new Color(255, 240, 245));
		panelPhaiDuoi.setBounds(698, 328, 575, 279);
		panelTong.add(panelPhaiDuoi);
		
		JPanel panelTieuDe5 = new JPanel();
		panelTieuDe5.setLayout(null);
		panelTieuDe5.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panelTieuDe5.setBackground(new Color(255, 204, 51));
		panelTieuDe5.setBounds(10, 11, 109, 21);
		panelPhaiDuoi.add(panelTieuDe5);
		
		JLabel lblTieuDe5 = new JLabel("Giỏ hàng");
		lblTieuDe5.setHorizontalAlignment(SwingConstants.CENTER);
		lblTieuDe5.setFont(new Font("Arial", Font.PLAIN, 14));
		lblTieuDe5.setBounds(0, 1, 109, 20);
		panelTieuDe5.add(lblTieuDe5);
		
		//Table gio hang
		String[] header1 = {"STT","Tên sản phẩm","Số lượng","Giá bán","Tổng tiền"};
		modelTableGioHang = new DefaultTableModel(header1, 0);
		tblGioHang = new JTable(modelTableGioHang);
		tblGioHang.setBackground(Color.WHITE);
		tblGioHang.setFont(new Font("Arial", Font.PLAIN, 14));
		tblGioHang.setRowHeight(20);
		JScrollPane scrollPane1 = new JScrollPane(tblGioHang);
		scrollPane1.setSize(555, 225);
		scrollPane1.setLocation(10, 43);
		panelPhaiDuoi.add(scrollPane1);
		
		btnThem = new JButton("Thêm");
		btnThem.setBackground(new Color(0, 191, 255));
		btnThem.setFont(new Font("Arial", Font.BOLD, 14));
		btnThem.setBounds(698, 618, 166, 36);
		panelTong.add(btnThem);
		
		btnXoa = new JButton("Xóa");
		btnXoa.setFont(new Font("Arial", Font.BOLD, 14));
		btnXoa.setBackground(new Color(0, 191, 255));
		btnXoa.setBounds(907, 618, 166, 36);
		panelTong.add(btnXoa);
		
		btnXuat = new JButton("Xuất");
		btnXuat.setFont(new Font("Arial", Font.BOLD, 14));
		btnXuat.setBackground(new Color(0, 191, 255));
		btnXuat.setBounds(1107, 618, 166, 36);
		panelTong.add(btnXuat);
		loadHoaDon();
		//Action
		rdbtnNam.addActionListener(this);
		rdbtnNu.addActionListener(this);
		cbmaKH.addActionListener(this);
		btnThem.addActionListener(this);
		btnXoa.addActionListener(this);
		btnXuat.addActionListener(this);
		
		
		khoaKH();
		txtTenKH.setEditable(false);
		docDuLieuGioHang();
	}
	public void khoaKH() {
		txtTenKhachHang.setEditable(false);
		txtDiaChi.setEditable(false);
		txtSDT.setEditable(false);
	}
	public void moKH() {
		txtTenKhachHang.setEditable(true);
		txtDiaChi.setEditable(true);
		txtSDT.setEditable(true);
	}
	public String tinhBac(int diemTL) {
		String bac;
		if(diemTL < 100) {
			bac = "Đồng";
		}
		else if(diemTL < 200) {
			bac = "Bạc";
		}
		else if(diemTL < 400) {
			bac = "Vàng";
		}
		else {
			bac = "Bạch kim";
		}
		return bac;
	}
	public double tinhGiamTru(double diemTL) {
		double giam;
		if(diemTL < 100) {
			giam = thanhTien*0.01;
		}
		else if(diemTL < 200) {
			giam = thanhTien*0.02;
		}
		else if(diemTL < 400) {
			giam = thanhTien*0.03;
		}
		else {
			giam = thanhTien*0.05;
		}
		return giam;
	}
	public int tinhDiem(double tongTien) {
		double diem = tongTien*0.01;
		int diemMoi = (int) Math.round(diem);
		return diemMoi;
	}

	public String convertMoney(double gia) {
		return NumberFormat.getCurrencyInstance(new Locale("vi", "VN")).format(gia);
	}
	public void docDuLieuGioHang() {
		double tongTien = 0;
		int  i = 1;
		list = cthd_dao.layDuLieuHoaDonTheoMa(currentMaHD);
		for (ChiTietHoaDon ct : list) {
			modelTableGioHang.addRow(new Object[] {
					i++,
					ct.getSp().getTenSP(),
					ct.getSoLuong(),
					convertMoney(ct.getSp().getGiaBan()),
					convertMoney(tongTien = ct.getSp().getGiaBan()*ct.getSoLuong()),
			});
		}
		NhanVien_DAO dao_nv = new NhanVien_DAO(entityManager);
		HoaDon_DAO dao_hd = new HoaDon_DAO(entityManager);
		if(dao_hd.getHoaDonTheoMa(currentMaHD).getNhanVien() == null) {
			txtTenNV.setText("NV01");
		} else {
			txtTenNV.setText(dao_nv.layDuLieuNhanVienTheoMa(dao_hd.getHoaDonTheoMa(currentMaHD).getNhanVien().getMaNV()).get(0).getTenNV());
		}
	}
	public void loadHoaDon() {
		HoaDon_DAO hd_dao = new HoaDon_DAO(entityManager);
		List<HoaDon> dsHD = hd_dao.getDSHoaDon();
		//System.out.println(dsHD);
		currentMaHD = dsHD.get(dsHD.size()-1).getMaHD();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o.equals(cbmaKH)) {
			String maKH = (String) cbmaKH.getSelectedItem();
			KhachHang dsKhachHang = khachHang_dao.layDuLieuKhachHangTheoMa(maKH); 
			txtTenKhachHang.setText(dsKhachHang.getTenKH());
			txtTenKH.setText(dsKhachHang.getTenKH());
			txtDiaChi.setText(dsKhachHang.getDiaChi());
			System.out.println();
			txtSDT.setText("0"+dsKhachHang.getSdt());
			if(dsKhachHang.isGioiTinh()==true) {
				rdbtnNam.setSelected(true);
				rdbtnNu.setSelected(false);
			}
			else {
				rdbtnNu.setSelected(true);
				rdbtnNam.setSelected(false);
			}
			int diemTL = dsKhachHang.getDiemTichLuy();
			comboBox.setSelectedItem(tinhBac(diemTL));
		}
		if(o.equals(btnThem)) {
			if(txtTenKH.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(null, "Vui lòng chọn mã khách hàng");
				return;
			}
			thanhTien = 0;
			
			int  i = 1;
			for (ChiTietHoaDon ct : list) {
				modelTableChiTietHoaDon.addRow(new Object[] {
						i++,
						ct.getSp().getTenSP(),
						ct.getSoLuong(),
						convertMoney(ct.getSp().getGiaBan()*ct.getSoLuong()),
				});
				thanhTien+=ct.getSp().getGiaBan()*ct.getSoLuong();
			}
			giamTru = tinhGiamTru(thanhTien);
			txtGiamTru.setText(convertMoney(giamTru).toString());
			txtTongTien.setText(convertMoney(thanhTien-thanhTien*0.02));
			txtThue.setText("2%");
			lblMaHD.setText("Mã hóa đơn: "+currentMaHD);
			btnXoa.setEnabled(false);
			btnThem.setEnabled(false);
		}
		if(o.equals(btnXoa)) {
			int row = tblGioHang.getSelectedRow();
			modelTableGioHang.removeRow(row);
			list.remove(row);
			
		}
		
		if(o.equals(btnXuat)) {
			HoaDon_DAO hd_dao = new HoaDon_DAO(entityManager);
			HoaDon hoaDon = hd_dao.getHoaDonTheoMa(currentMaHD);
			String maKH = cbmaKH.getSelectedItem().toString();
			KhachHang kh = khachHang_dao.layDuLieuKhachHangTheoMa(maKH);
			
			hoaDon.setKhachHang(kh);
			hoaDon.setSoLuongGiam(giamTru);
			hoaDon.setThue(0.02);
			
			hd_dao.capNhatHoaDon(hoaDon);
			JOptionPane.showMessageDialog(null, "Thành công");
			controller.PDFController.InHoaDon(currentMaHD);
			
			
			//tinhDiem(serialVersionUID)
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		/*if(modelTableKH.getValueAt(row, 4).equals("Nam")) {
			rdNam.setSelected(true);
		}
		else {
			rdNu.setSelected(true);
		}*/
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
