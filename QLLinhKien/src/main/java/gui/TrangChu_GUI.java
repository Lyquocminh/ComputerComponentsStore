package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Panel;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import com.formdev.flatlaf.FlatLightLaf;

import service.SocketService;

import javax.swing.JButton;
import java.awt.Component;
import java.awt.Container;

import javax.swing.Box;

public class TrangChu_GUI extends JFrame implements ActionListener {

	String trangChu = "Trang chủ";
	String taiKhoan = "Tài khoản";
	String doiMatKhau = "Đổi mật khẩu";
	String dangXuat = "Đăng xuất";
	String sanPham = "Sản phẩm";
	String hoaDon = "Hóa đơn";
	String thongKe = "Thống kê";
	String quanLyKhachHang = "Quản lý khách hàng";

	private JMenuBar menuBar;

	private JMenu menuTrangChu;
	private JMenu menuTaiKhoan;
	private JMenu menuQuanLy;
	private JMenu menuSanPham;
	private JMenu menuThongKe;

	private JMenuItem menuitemTrangChu;
	private JMenuItem menuitemNhanVien;
	private JMenuItem menuitemDanhMucSP;
	private JMenuItem menuitemDoiMatKhau;
	private JMenuItem menuitemKhachHang;
	private JMenuItem menuitemThongKe;
	private JPanel contentPane;
	private Component horizontalStrut;
	private Container trangChu_gui;
	private Container danhMucSanPham_gui;
	private Container quanLyNhanVien_gui;
	private Container khachHang_gui;
	private Container doiMK_gui;
	private JButton btnDangXuat;
	private JMenu menuHoaDon;
	private JMenuItem menuItemHoaDon;
	private JMenuItem menuitemChat;
	private JMenu menuChat;

	public TrangChu_GUI() throws IOException {

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(1300, 770);
		setLocationRelativeTo(null);

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		menuTrangChu = new JMenu(trangChu);
		menuTrangChu.setIcon(new ImageIcon(TrangChu_GUI.class.getResource("/image/home.png")));
		menuTrangChu.setForeground(new Color(0, 163, 163));
		menuTrangChu.setFont(new Font("Segoe UI", Font.BOLD, 18));
		menuBar.add(menuTrangChu);

		menuitemTrangChu = new JMenuItem("Trang chủ");
		menuitemTrangChu.setForeground(new Color(0, 163, 163));
		menuitemTrangChu.setIcon(new ImageIcon(TrangChu_GUI.class.getResource("/image/home.png")));
		menuitemTrangChu.setPreferredSize(new Dimension(130, menuitemTrangChu.getPreferredSize().height));
		menuTrangChu.add(menuitemTrangChu);

		menuTaiKhoan = new JMenu("Tài khoản");
		menuTaiKhoan.setIcon(new ImageIcon(TrangChu_GUI.class.getResource("/image/employee.png")));
		menuTaiKhoan.setForeground(new Color(0, 163, 163));
		menuTaiKhoan.setFont(new Font("Segoe UI", Font.BOLD, 18));
		menuBar.add(menuTaiKhoan);

		menuitemDoiMatKhau = new JMenuItem("Đổi mật khẩu");
		menuitemDoiMatKhau.setForeground(new Color(0, 163, 163));
		menuitemDoiMatKhau.setIcon(new ImageIcon(TrangChu_GUI.class.getResource("/image/changepass.png")));
		menuitemDoiMatKhau.setPreferredSize(new Dimension(124, menuitemTrangChu.getPreferredSize().height));
		menuTaiKhoan.add(menuitemDoiMatKhau);

		menuSanPham = new JMenu("Sản phẩm");
		menuSanPham.setIcon(new ImageIcon(TrangChu_GUI.class.getResource("/image/store.png")));
		menuSanPham.setForeground(new Color(0, 163, 163));
		menuSanPham.setFont(new Font("Segoe UI", Font.BOLD, 18));
		menuBar.add(menuSanPham);

		menuitemDanhMucSP = new JMenuItem("Danh mục sản phẩm");
		menuitemDanhMucSP.setForeground(new Color(0, 163, 163));
		menuitemDanhMucSP.setIcon(new ImageIcon(TrangChu_GUI.class.getResource("/image/product.png")));
		menuitemDanhMucSP.setPreferredSize(new Dimension(160, 26));
		menuSanPham.add(menuitemDanhMucSP);
		menuitemDanhMucSP.addActionListener(this);
		
		menuHoaDon = new JMenu(hoaDon);
		menuHoaDon.setIcon(new ImageIcon(TrangChu_GUI.class.getResource("/image/bill.png")));
		menuHoaDon.setForeground(new Color(0, 163, 163));
		menuHoaDon.setFont(new Font("Segoe UI", Font.BOLD, 18));
		menuBar.add(menuHoaDon);

		menuItemHoaDon = new JMenuItem("Chi tiết hóa đơn");
		menuItemHoaDon.setForeground(new Color(0, 163, 163));
		menuItemHoaDon.setIcon(new ImageIcon(TrangChu_GUI.class.getResource("/image/bill.png")));
		menuItemHoaDon.setPreferredSize(new Dimension(130, menuitemTrangChu.getPreferredSize().height));
		menuHoaDon.add(menuItemHoaDon);		

		menuQuanLy = new JMenu("Quản lý");
		menuQuanLy.setIcon(new ImageIcon(TrangChu_GUI.class.getResource("/image/management.png")));
		menuQuanLy.setForeground(new Color(0, 163, 163));
		menuQuanLy.setFont(new Font("Segoe UI", Font.BOLD, 18));
		menuBar.add(menuQuanLy);

		menuitemKhachHang = new JMenuItem("Quản lý khách hàng");
		menuitemKhachHang.setForeground(new Color(0, 163, 163));
		menuitemKhachHang.setIcon(new ImageIcon(TrangChu_GUI.class.getResource("/image/customer.png")));
		menuQuanLy.add(menuitemKhachHang);

		menuitemNhanVien = new JMenuItem("Quản lý nhân viên");
		menuitemNhanVien.setForeground(new Color(0, 163, 163));
		menuitemNhanVien.setIcon(new ImageIcon(TrangChu_GUI.class.getResource("/image/employees.png")));
		menuQuanLy.add(menuitemNhanVien);

		menuThongKe = new JMenu("Thống kê");
		menuThongKe.setIcon(new ImageIcon(TrangChu_GUI.class.getResource("/image/trend.png")));
		menuThongKe.setForeground(new Color(0, 163, 163));
		menuThongKe.setFont(new Font("Segoe UI", Font.BOLD, 18));
		menuBar.add(menuThongKe);

		menuitemThongKe = new JMenuItem("Thống kê doanh thu");
		menuitemThongKe.setForeground(new Color(0, 163, 163));
		menuitemThongKe.setIcon(new ImageIcon(TrangChu_GUI.class.getResource("/image/trend.png")));
		menuitemThongKe.setPreferredSize(new Dimension(150, 26));
		menuThongKe.add(menuitemThongKe);
		
		menuChat = new JMenu("Chat");
		menuChat.setIcon(new ImageIcon(TrangChu_GUI.class.getResource("/image/chat.png")));
		menuChat.setForeground(new Color(0, 163, 163));
		menuChat.setFont(new Font("Segoe UI", Font.BOLD, 18));
		menuBar.add(menuChat);

		menuitemChat = new JMenuItem("Chat với khách hàng");
		menuitemChat.setForeground(new Color(0, 163, 163));
		menuitemChat.setIcon(new ImageIcon(TrangChu_GUI.class.getResource("/image/chat.png")));
		menuitemChat.setPreferredSize(new Dimension(150, 26));
		menuChat.add(menuitemChat);		

		horizontalStrut = Box.createHorizontalStrut(170);
		menuBar.add(horizontalStrut);

		btnDangXuat = new JButton();
		btnDangXuat.setIcon(new ImageIcon(TrangChu_GUI.class.getResource("/image/logout.png")));
		btnDangXuat.setText(dangXuat);
		btnDangXuat.setForeground(new Color(0, 163, 163));
		btnDangXuat.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 18));
		btnDangXuat.setBorderPainted(false);
		btnDangXuat.setBackground(Color.WHITE);
		menuBar.add(btnDangXuat);

		contentPane = new JPanel();
		contentPane.setLayout(new java.awt.BorderLayout());

		add(contentPane);
		// contentPane.setLayout(null);
		menuitemTrangChu.addActionListener(this);
		menuitemDoiMatKhau.addActionListener(this);
		menuitemDanhMucSP.addActionListener(this);
		menuitemNhanVien.addActionListener(this);
		menuitemKhachHang.addActionListener(this);
		menuitemThongKe.addActionListener(this);
		menuItemHoaDon.addActionListener(this);
		btnDangXuat.addActionListener(this);
		trangChu_gui = new ManHinhChinh_GUI().getContentPane();
		//danhMucSanPham_gui = new DanhMucSanPham_gui().getContentPane();
		quanLyNhanVien_gui = new NhanVien_GUI().getContentPane();
		khachHang_gui = new KhachHang_GUI().getContentPane();
		showForm(new ManHinhChinh_GUI().getContentPane());
	}

	public static void main(String[] args) throws IOException {
		FlatLightLaf.setup();
		new TrangChu_GUI().setVisible(true);


	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o.equals(menuitemTrangChu)) {
			showForm(trangChu_gui);
		}
		if(o.equals(menuitemDoiMatKhau)) {
			this.dispose();
			new DoiMatKhau_GUI().setVisible(true);
		}
		if(o.equals(menuitemDanhMucSP)) {
			showForm(new DanhMucSanPham_GUI().getContentPane());
		}
		if(o.equals(menuItemHoaDon)) {
			showForm(new ChiTietHoaDon_GUI().getContentPane());
		}
		if(o.equals(menuitemKhachHang)) {
			showForm(khachHang_gui);
		}
		if(o.equals(menuitemNhanVien)) {
			showForm(quanLyNhanVien_gui);
		}
		if(o.equals(menuitemThongKe)) {
			
		}
		if(o.equals(btnDangXuat)) {
				int option = JOptionPane.showConfirmDialog(null, "Bạn có muốn đăng xuất không?", "Đăng xuất", JOptionPane.YES_NO_OPTION);
				if (option == JOptionPane.YES_OPTION) {
					this.dispose();
					new DangNhap_GUI().setVisible(true);
				}
		}

	}

	private void showForm(Component com) {
		if (com != null) {
			contentPane.removeAll();
			contentPane.add(com);
			contentPane.repaint();
			contentPane.revalidate();
		}
	}

}
