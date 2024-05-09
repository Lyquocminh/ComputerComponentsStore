package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Container;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.FlowLayout;
import javax.swing.border.LineBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;

import service.EntityManagerFactoryUtil;
import dao.KhachHang_DAO;
import entity.KhachHang;
import jakarta.persistence.EntityManager;

import javax.swing.JTable;
import javax.swing.JRadioButton;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowFocusListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.WindowEvent;

public class KhachHang_GUI extends JFrame implements FocusListener, WindowFocusListener, ActionListener, MouseListener {

	private static final long serialVersionUID = 1L;
	private JPanel panelTong, panelBenPhai;
	private DefaultTableModel modelTable;
	private DefaultTableModel modelTableKH;
	private JTable tblKhachHang;
	private JTextField txtMa;
	private JTextField txtTen;
	private JTextField txtSDT;
	private JTextField txtDiaChi;
	private JTextField txtDiem;
	private JTextField txtNhap;
	private JRadioButton rdNam;
	private JRadioButton rdNu;
	private KhachHang_DAO khachHang_dao;
	private JButton btnTim;
	private JButton btnXoa;
	private JButton btnSua;
	private JButton btnThem;
	private JButton btnLuu;
	private int soLuongKH;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KhachHang_GUI frame = new KhachHang_GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public KhachHang_GUI() {
	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setSize(1300, 700);
		setLocationRelativeTo(null);
		panelTong = new JPanel();
		panelTong.setBackground(Color.WHITE);
		panelTong.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(panelTong);
		panelTong.setLayout(null);
		
		JPanel panelTren = new JPanel();
		panelTren.setBounds(0, 0, 1283, 52);
		panelTren.setBackground(new Color(0, 153, 255));
		panelTong.add(panelTren);
		panelTren.setLayout(null);
		
		JLabel lblTieuDe = new JLabel("KHÁCH HÀNG");
		lblTieuDe.setHorizontalAlignment(SwingConstants.CENTER);
		lblTieuDe.setBounds(538, 6, 207, 40);
		lblTieuDe.setForeground(Color.WHITE);
		lblTieuDe.setFont(new Font("Arial", Font.BOLD, 26));
		panelTren.add(lblTieuDe);
		
		JPanel panelBenTrai = new JPanel();
		panelBenTrai.setBorder(new EmptyBorder(0, 0, 0, 0));
		panelBenTrai.setBackground(new Color(255, 240, 245));
		panelBenTrai.setBounds(10, 116, 843, 538);
		panelTong.add(panelBenTrai);
		panelBenTrai.setLayout(null);
		
		JPanel panelTieuDe1 = new JPanel();
		panelTieuDe1.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panelTieuDe1.setBackground(new Color(255, 204, 51));
		panelTieuDe1.setBounds(147, 11, 548, 39);
		panelBenTrai.add(panelTieuDe1);
		panelTieuDe1.setLayout(null);
		
		JLabel lblTieuDe1 = new JLabel("Danh sách khách hàng");
		lblTieuDe1.setFont(new Font("Arial", Font.BOLD, 16));
		lblTieuDe1.setHorizontalAlignment(SwingConstants.CENTER);
		lblTieuDe1.setBounds(134, 1, 280, 37);
		panelTieuDe1.add(lblTieuDe1);

		String[] header = {"Mã KH","Họ tên","SĐT","Địa chỉ","Giới tính","Điểm tích luỹ","Bậc"};
		modelTableKH = new DefaultTableModel(header, 0);
		tblKhachHang = new JTable(modelTableKH);
		tblKhachHang.setBackground(Color.WHITE);
		tblKhachHang.setFont(new Font("Arial", Font.PLAIN, 14));
		tblKhachHang.setRowHeight(20);
		JScrollPane scrollPane = new JScrollPane(tblKhachHang);
		scrollPane.setSize(823, 450);
		scrollPane.setLocation(10, 77);
		panelBenTrai.add(scrollPane);
		
		panelBenPhai = new JPanel();
		panelBenPhai.setBorder(new EmptyBorder(0, 0, 0, 0));
		panelBenPhai.setBackground(new Color(255, 240, 245));
		panelBenPhai.setBounds(863, 116, 394, 538);
		panelTong.add(panelBenPhai);
		panelBenPhai.setLayout(null);
		
		JPanel panelTieuDe2 = new JPanel();
		panelTieuDe2.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panelTieuDe2.setBackground(new Color(255, 204, 51));
		panelTieuDe2.setBounds(38, 38, 318, 47);
		panelBenPhai.add(panelTieuDe2);
		panelTieuDe2.setLayout(null);
		
		JLabel lblTieuDe2 = new JLabel("Thông tin khách hàng");
		lblTieuDe2.setFont(new Font("Arial", Font.BOLD, 16));
		lblTieuDe2.setHorizontalAlignment(SwingConstants.CENTER);
		lblTieuDe2.setBounds(0, 0, 318, 47);
		panelTieuDe2.add(lblTieuDe2);
		
		JLabel lblMaKH = new JLabel("Mã KH:");
		lblMaKH.setFont(new Font("Arial", Font.PLAIN, 14));
		lblMaKH.setBounds(38, 120, 61, 16);
		panelBenPhai.add(lblMaKH);
		
		txtMa = new JTextField();
		txtMa.setBounds(91, 116, 265, 26);
		panelBenPhai.add(txtMa);
		txtMa.setColumns(10);
		txtMa.setEditable(false);
		
		JLabel lblTen = new JLabel("Họ tên:");
		lblTen.setFont(new Font("Arial", Font.PLAIN, 14));
		lblTen.setBounds(38, 173, 61, 16);
		panelBenPhai.add(lblTen);
		
		txtTen = new JTextField();
		txtTen.setColumns(10);
		txtTen.setBounds(91, 169, 265, 26);
		panelBenPhai.add(txtTen);
		
		JLabel lblSDT = new JLabel("SĐT:");
		lblSDT.setFont(new Font("Arial", Font.PLAIN, 14));
		lblSDT.setBounds(38, 224, 61, 16);
		panelBenPhai.add(lblSDT);
		
		txtSDT = new JTextField();
		txtSDT.setColumns(10);
		txtSDT.setBounds(91, 220, 265, 26);
		panelBenPhai.add(txtSDT);
		
		JLabel lblDiaChi = new JLabel("Địa chỉ:");
		lblDiaChi.setFont(new Font("Arial", Font.PLAIN, 14));
		lblDiaChi.setBounds(38, 279, 61, 16);
		panelBenPhai.add(lblDiaChi);
		
		txtDiaChi = new JTextField();
		txtDiaChi.setColumns(10);
		txtDiaChi.setBounds(91, 275, 265, 26);
		panelBenPhai.add(txtDiaChi);
		
		JLabel lblGioiTinh = new JLabel("Giới tính:");
		lblGioiTinh.setFont(new Font("Arial", Font.PLAIN, 14));
		lblGioiTinh.setBounds(38, 329, 61, 16);
		panelBenPhai.add(lblGioiTinh);
		
		rdNam = new JRadioButton("Nam");
		rdNam.setBackground(new Color(255, 240, 245));
		rdNam.setBounds(105, 327, 69, 23);
		panelBenPhai.add(rdNam);
		
		rdNu = new JRadioButton("Nữ");
		rdNu.setBackground(new Color(255, 240, 245));
		rdNu.setBounds(176, 327, 69, 23);
		panelBenPhai.add(rdNu);
		
		JLabel lblDiem = new JLabel("Điểm:");
		lblDiem.setFont(new Font("Arial", Font.PLAIN, 14));
		lblDiem.setBounds(38, 377, 49, 16);
		panelBenPhai.add(lblDiem);
		
		txtDiem = new JTextField();
		txtDiem.setBackground(Color.WHITE);
		txtDiem.setEditable(true);
		txtDiem.setBounds(91, 373, 265, 26);
		panelBenPhai.add(txtDiem);
		txtDiem.setColumns(10);
		
		btnThem = new JButton("Thêm ");
		btnThem.setBackground(new Color(0, 191, 255));
		btnThem.setFont(new Font("Arial", Font.PLAIN, 14));
		btnThem.setBounds(38, 439, 149, 25);
		panelBenPhai.add(btnThem);
		
		btnSua = new JButton("Cập nhật");
		btnSua.setFont(new Font("Arial", Font.PLAIN, 14));
		btnSua.setBackground(new Color(0, 191, 255));
		btnSua.setBounds(207, 439, 149, 25);
		panelBenPhai.add(btnSua);
		
		btnLuu = new JButton("Lưu");
		btnLuu.setFont(new Font("Arial", Font.PLAIN, 14));
		btnLuu.setBackground(new Color(0, 191, 255));
		btnLuu.setBounds(207, 439, 149, 25);
		
		btnXoa = new JButton("Xoá trắng");
		btnXoa.setFont(new Font("Arial", Font.PLAIN, 14));
		btnXoa.setBackground(new Color(0, 191, 255));
		btnXoa.setBounds(38, 483, 317, 30);
		panelBenPhai.add(btnXoa);
		
		txtNhap = new JTextField();
		txtNhap.setFont(new Font("Arial", Font.PLAIN, 14));
		txtNhap.setText("Nhập mã...");
		txtNhap.setBounds(10, 74, 288, 30);
		themPlaceholderStyle(txtNhap);
		panelTong.add(txtNhap);
		txtNhap.setColumns(10);
		addWindowFocusListener(this);
		txtNhap.addFocusListener(this);
		
		
		btnTim = new JButton("Tìm");
		btnTim.setFont(new Font("Arial", Font.PLAIN, 14));
		btnTim.setBackground(new Color(0, 191, 255));
		btnTim.setBounds(308, 76, 90, 25);
		panelTong.add(btnTim);
		
		//Dang ky su kien
		btnTim.addActionListener(this);
		btnThem.addActionListener(this);
		btnSua.addActionListener(this);
		btnXoa.addActionListener(this);
		rdNam.addActionListener(this);
		rdNu.addActionListener(this);
		tblKhachHang.addMouseListener(this);
		btnLuu.addActionListener(this);
		
		
		//Khoi tao DAO
		EntityManagerFactoryUtil entityManagerFactoryUtil = new EntityManagerFactoryUtil();
		EntityManager entityManager = entityManagerFactoryUtil.getEnManager();
		khachHang_dao = new KhachHang_DAO(entityManager);
		
		//Doc du lieu SQL -> TABLE
		docDuLieuTuDatabaseVaoTable();
		
		
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
	//Doc du lieu tu DATABASE SQL VAO TABLE JAVA
	public void docDuLieuTuDatabaseVaoTable() {
		List<KhachHang> list = khachHang_dao.layDuLieuKhachHang();
		soLuongKH = list.size();
		for (KhachHang khachHang : list) {
			modelTableKH.addRow(new Object[] {
					khachHang.getMaKH(),
					khachHang.getTenKH(),
					khachHang.getSdt(),
					khachHang.getDiaChi(),
					khachHang.isGioiTinh()==true?"Nam":"Nữ",
					khachHang.getDiemTichLuy(),
					tinhBac(khachHang.getDiemTichLuy()),
			});
		}
	}
	//Them placeholder
	public void themPlaceholderStyle(JTextField textField) {
		Font font = textField.getFont();
		font = font.deriveFont(Font.ITALIC);
		textField.setFont(font);
		textField.setForeground(Color.gray);
	}
	//Xoa placeholder
	public void xoaPlaceholder(JTextField textField) {
		Font font = textField.getFont();
		font = font.deriveFont(Font.PLAIN);
		textField.setFont(font);
		textField.setForeground(Color.black);
	}
	//Xu ly su kien placeholder
	@Override
	public void focusGained(FocusEvent e) {
		if(txtNhap.getText().equals("Nhập mã...")) {
			txtNhap.setText(null);
			txtNhap.requestFocus();
			xoaPlaceholder(txtNhap);
		}
		
	}
	@Override
	public void focusLost(FocusEvent e) {
		if (txtNhap.getText().length()==0) {
			themPlaceholderStyle(txtNhap);
			txtNhap.setText("");
		}
	}

	@Override
	public void windowGainedFocus(WindowEvent e) {
		this.requestFocusInWindow();
		
	}

	@Override
	public void windowLostFocus(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	public boolean kiemTraRangBuoc() {
		String tenKH = txtTen.getText();
		String sdt = txtSDT.getText();
		String diaChi = txtDiaChi.getText();
		String diemTL = txtDiem.getText();
		
		if(!tenKH.matches("[a-zA-Z' ]+")) {
			JOptionPane.showMessageDialog(this,	"Tên khách hàng phải là ký tự");
			return false;
		}
		if(!diaChi.matches("[a-zA-Z' ]+")) {
			JOptionPane.showMessageDialog(this,	"Địa chỉ phải là ký tự");
			return false;
		}
		try {
			int soDT = Integer.parseInt(sdt);
			if(soDT < 0) {
				JOptionPane.showMessageDialog(this,	"Số điện thoại không âm");
				return false;
			}
			else if(!sdt.matches("[0-9]{8}")) {
				JOptionPane.showMessageDialog(this,	"Số điện thoại tối đa 8 chữ số");
				return false;
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this,	"Số điện thoại phải là số");
			return false;
		}
		try {
			int diem = Integer.parseInt(diemTL);
			if(diem < 0) {
				JOptionPane.showMessageDialog(this,	"Điểm tích lũy không âm");
				return false;
			}			
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this,	"Điểm tích lũy là số");
			return false;
		}
		return true;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o.equals(rdNam)) {
			rdNu.setSelected(false);
		}
		if(o.equals(rdNu)) {
			rdNam.setSelected(false);
		}
		if (o.equals(btnTim)) {
			String txtTim = txtNhap.getText();	
			KhachHang khachHang = khachHang_dao.layDuLieuKhachHangTheoMa(txtTim);
			modelTableKH.getDataVector().removeAllElements();
			modelTableKH.addRow(new Object[] {
						khachHang.getMaKH(),
						khachHang.getTenKH(),
						khachHang.getSdt(),
						khachHang.getDiaChi(),
						khachHang.isGioiTinh()==true?"Nam":"Nữ",
						khachHang.getDiemTichLuy(),
						tinhBac(khachHang.getDiemTichLuy()),
				});
			
	}
		if (o.equals(btnThem)) {
			if(kiemTraRangBuoc()) {
				String tenKH = txtTen.getText();
				int sdt = Integer.parseInt(txtSDT.getText());
				String diaChi = txtDiaChi.getText();
				boolean gioiTinh = rdNam.isSelected();
				int diem = Integer.parseInt(txtDiem.getText());
				String maKH = String.format("%s%02d","KH",++soLuongKH);
				KhachHang khach = new KhachHang(maKH, tenKH, sdt, diaChi, gioiTinh, diem);
				try {
					khachHang_dao.themKhachHang(khach);
					modelTableKH.addRow(new Object[] {
							khach.getMaKH(),
							khach.getTenKH(),
							khach.getSdt(),
							khach.getDiaChi(),
							khach.isGioiTinh()==true?"Nam":"Nữ",
							khach.getDiemTichLuy(),
							tinhBac(khach.getDiemTichLuy())
					});
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Trùng mã khách hàng!");
				}
			}
		}
		if (o.equals(btnSua)) {
			txtTen.setText("");
			txtDiem.setText("");
			txtSDT.setText("");
			txtDiaChi.setText("");
			//Đổi nút cập nhật thành lưu
	        int index = panelBenPhai.getComponentZOrder(btnSua);
	        panelBenPhai.remove(btnSua);
	        panelBenPhai.add(btnLuu, index);
	        panelBenPhai.revalidate();
	        panelBenPhai.repaint();
		}
		if(o.equals(btnLuu)) {
			String maKH = txtMa.getText();
			String tenKH = txtTen.getText();
			int sdt = Integer.parseInt(txtSDT.getText());
			String diaChi = txtDiaChi.getText();
			int diemTL = Integer.parseInt(txtDiem.getText());
			KhachHang kh = new KhachHang(maKH, tenKH, sdt, diaChi, rdNam.isSelected()?true:false, diemTL);
			khachHang_dao.capNhatKH(kh);
			modelTableKH.getDataVector().removeAllElements();
			docDuLieuTuDatabaseVaoTable();
			//đổi lại về cập nhật
			int index = panelBenPhai.getComponentZOrder(btnLuu);
	        panelBenPhai.remove(btnLuu);
	        panelBenPhai.add(btnSua, index);
	        panelBenPhai.revalidate();
	        panelBenPhai.repaint();
		}
		if (o.equals(btnXoa)) {
			txtMa.setText("");
			txtDiaChi.setText("");
			txtDiem.setText("");
			txtSDT.setText("");
			txtTen.setText("");
			rdNam.setSelected(false);
			rdNu.setSelected(false);
			modelTableKH.getDataVector().removeAllElements();
			docDuLieuTuDatabaseVaoTable();
			
		}
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		int row = tblKhachHang.getSelectedRow();
		txtMa.setText(modelTableKH.getValueAt(row, 0).toString());
		txtTen.setText(modelTableKH.getValueAt(row, 1).toString());
		txtSDT.setText(modelTableKH.getValueAt(row, 2).toString());
		txtDiaChi.setText(modelTableKH.getValueAt(row, 3).toString());
		if(modelTableKH.getValueAt(row, 4).equals("Nam")) {
			rdNam.setSelected(true);
			rdNu.setSelected(false);
		}
		else {
			rdNu.setSelected(true);
			rdNam.setSelected(false);
		}
		txtDiem.setText(modelTableKH.getValueAt(row, 5).toString());
		
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


