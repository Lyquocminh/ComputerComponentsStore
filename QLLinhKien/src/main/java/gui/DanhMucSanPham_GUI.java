
package gui;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import entity.HoaDon;
import entity.NhaCungCap;
import entity.SanPham;
import jakarta.persistence.EntityManager;
import app.Client;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Date;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class DanhMucSanPham_GUI extends JFrame implements ActionListener, MouseListener {
	private String currentNCC;
	private String currentSanPham;
	private String currentMaHD;
	private JTextField txtSearchBox;
	private JButton btnTim;
	private JTable tableSP;
	private JTextField txtSupplierID, txtTenNhaCungCap, txtDiaChi, txtSoDienThoai;
	private TitledBorder tableBorder;
	private DefaultTableModel modelSP;
	private JLabel lblTenSP;
	private JTextField txtTenSP;
	private JLabel lblThuongHieu;
	private JTextField txtThuongHieu;
	private JLabel lblSoLuongTon;
	private JTextField txtSoLuongTon;
	private JLabel lblGiaNhap;
	private JTextField txtGiaNhap;
	private JLabel lblGiaBan;
	private JTextField txtGiaBan;
	private boolean daNhap = true;
	private JLabel lblNhaCungCap;
	private JLabel lblDiaChi;
	private JComboBox<String> cboNhaCungCap;
	private JLabel lblSoDienThoai;
	private JButton btnGioHang;
	private Component horizontalStrut;
	private Component horizontalStrut_1;
	private Box horizontalBox_3;
	private Component horizontalStrut_2;
	private Box horizontalBox_6;
	private JButton btnSave;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JButton btnDeleteAll;
	private Component verticalStrut_1;
	private Component verticalStrut_2;
	private Component horizontalStrut_4;
	private Component horizontalStrut_5;
	private Component horizontalStrut_6;
	private Component horizontalStrut_7;
	private Box horizontalBox_7;
	private JButton btnAddToHoaDon;
	private Component horizontalStrut_3;
	private JLabel lblTT;
	private Component verticalStrut;
	private Object dao_cthd;
	private Client client = new Client();
	private List<NhaCungCap> listNCC = new ArrayList<>();
	private List<SanPham> listSP = new ArrayList<>();
	
	public DanhMucSanPham_GUI() {
		setSize(1300, 650);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		setResizable(false);

		// Top panel
		JPanel topPanel = new JPanel(new BorderLayout());
		topPanel.setBackground(new Color(255, 240, 245));
		txtSearchBox = new JTextField(20);
	
		
		Box titleBar = Box.createVerticalBox();
		Box searchBar = Box.createHorizontalBox();
		Box titleBox =Box.createHorizontalBox();

		JPanel pTop = new JPanel();
		pTop.add(lblTT = new JLabel("DANH MỤC SẢN PHẨM"));
		lblTT.setFont(new Font("Arial", Font.BOLD, 20));
		lblTT.setForeground(Color.WHITE);
		pTop.setBackground(new Color(0, 153, 255));
		
		
		btnTim = new JButton("Tìm");
		btnTim.setBackground(new Color(0, 191, 255));
		horizontalStrut_2 = Box.createHorizontalStrut(800);
		searchBar.add(horizontalStrut_2);

		//searchBar.add(horizontalStrut_1);
		searchBar.add(txtSearchBox);
		searchBar.add(btnTim);
		titleBar.add(pTop);
		
		verticalStrut = Box.createVerticalStrut(5);
		titleBar.add(verticalStrut);
		titleBar.add(searchBar);
		//topPanel.add(pTop, BorderLayout.NORTH);
		topPanel.add(titleBar, BorderLayout.CENTER);

		horizontalStrut = Box.createHorizontalStrut(20);
		searchBar.add(horizontalStrut);

		// Middle panel
		JPanel middlePanel = new JPanel(new GridLayout(1, 3));

		// Left side with JTable
		JPanel tablePanel = new JPanel(new BorderLayout());
		tablePanel.setBackground(new Color(255, 240, 245));
		tableBorder = BorderFactory.createTitledBorder("Danh sách sản phẩm");
		tablePanel.setBorder(tableBorder);

		String cols[] = { "Mã", "Tên sản phẩm", "Thương hiệu", "Giá nhập", "Giá bán", "Số lượng tồn" };
		modelSP = new DefaultTableModel(cols, 0);
		tableSP = new JTable(modelSP);
		tableSP.setRowHeight(20);
		JScrollPane scrollPane = new JScrollPane(tableSP);
		tablePanel.add(scrollPane);
		middlePanel.add(tablePanel);

		loadData();
		// Right side with product and supplier info
		JPanel rightPanel = new JPanel(new GridLayout(2, 1));

		// Product info panel
		JPanel productInfoPanel = new JPanel();
		productInfoPanel.setBackground(new Color(255, 240, 245));
		productInfoPanel.setBorder(BorderFactory.createTitledBorder("Thông tin sản phẩm"));
		productInfoPanel.setLayout(new BoxLayout(productInfoPanel, BoxLayout.Y_AXIS));

		Box horizontalBox = Box.createHorizontalBox();
		productInfoPanel.add(horizontalBox);
		lblTenSP = new JLabel("Tên sản phẩm:");
		horizontalBox.add(lblTenSP);
		horizontalBox.add(Box.createHorizontalStrut(20));
		txtTenSP = new JTextField();
		horizontalBox.add(txtTenSP);
		horizontalBox.add(Box.createHorizontalStrut(20));
		productInfoPanel.add(Box.createVerticalStrut(20));

		Box horizontalBox_1 = Box.createHorizontalBox();
		productInfoPanel.add(horizontalBox_1);
		lblThuongHieu = new JLabel("Thương hiệu:");
		horizontalBox_1.add(lblThuongHieu);
		horizontalBox_1.add(Box.createHorizontalStrut(20));
		txtThuongHieu = new JTextField();
		horizontalBox_1.add(txtThuongHieu);
		horizontalBox_1.add(Box.createHorizontalStrut(20));
		productInfoPanel.add(Box.createVerticalStrut(20));

		Box horizontalBox_2 = Box.createHorizontalBox();
		productInfoPanel.add(horizontalBox_2);
		lblSoLuongTon = new JLabel("Số lượng tồn:");
		horizontalBox_2.add(lblSoLuongTon);
		horizontalBox_2.add(Box.createHorizontalStrut(20));
		txtSoLuongTon = new JTextField();
		horizontalBox_2.add(txtSoLuongTon);
		horizontalBox_2.add(Box.createHorizontalStrut(20));
		productInfoPanel.add(Box.createVerticalStrut(20));
		Box horizontalBox_4 = Box.createHorizontalBox();

		productInfoPanel.add(horizontalBox_4);
		lblGiaNhap = new JLabel("Giá nhập:");
		horizontalBox_4.add(lblGiaNhap);
		horizontalBox_4.add(Box.createHorizontalStrut(20));
		txtGiaNhap = new JTextField();
		horizontalBox_4.add(txtGiaNhap);
		horizontalBox_4.add(Box.createHorizontalStrut(20));
		productInfoPanel.add(Box.createVerticalStrut(20));
		Box horizontalBox_5 = Box.createHorizontalBox();

		productInfoPanel.add(horizontalBox_5);
		lblGiaBan = new JLabel("Giá bán:");
		horizontalBox_5.add(lblGiaBan);
		horizontalBox_5.add(Box.createHorizontalStrut(20));
		txtGiaBan = new JTextField();
		horizontalBox_5.add(txtGiaBan);
		horizontalBox_5.add(Box.createHorizontalStrut(20));
		productInfoPanel.add(Box.createVerticalStrut(20));

		lblThuongHieu.setPreferredSize(lblTenSP.getPreferredSize());
		lblSoLuongTon.setPreferredSize(lblTenSP.getPreferredSize());
		lblGiaBan.setPreferredSize(lblTenSP.getPreferredSize());
		lblGiaNhap.setPreferredSize(lblTenSP.getPreferredSize());

		rightPanel.add(productInfoPanel);

		// Supplier info panel
		JPanel supplierInfoPanel = new JPanel(new GridLayout(8, 1));
		supplierInfoPanel.setBackground(new Color(255, 240, 245));
		supplierInfoPanel.setBorder(BorderFactory.createTitledBorder("Thông tin nhà cung cấp"));

		Box bPickNCC = Box.createHorizontalBox();
		lblNhaCungCap = new JLabel("Mã nhà cung cấp:");
		bPickNCC.add(lblNhaCungCap);
		bPickNCC.add(Box.createHorizontalStrut(20));
		cboNhaCungCap = new JComboBox();
		loadNhaCungCap();
		loadHoaDon();
		bPickNCC.add(cboNhaCungCap);
		bPickNCC.add(Box.createHorizontalStrut(400));
		supplierInfoPanel.add(bPickNCC);

		Box b1 = Box.createHorizontalBox();
		lblNhaCungCap = new JLabel("Tên nhà cung cấp:");
		b1.add(lblNhaCungCap);
		b1.add(Box.createHorizontalStrut(20));
		txtTenNhaCungCap = new JTextField();
		b1.add(txtTenNhaCungCap);
		b1.add(Box.createHorizontalStrut(20));
		supplierInfoPanel.add(Box.createVerticalStrut(20));
		supplierInfoPanel.add(b1);

		Box b2 = Box.createHorizontalBox();
		lblDiaChi = new JLabel("Địa chỉ:");
		b2.add(lblDiaChi);
		b2.add(Box.createHorizontalStrut(20));
		txtDiaChi = new JTextField();
		b2.add(txtDiaChi);
		b2.add(Box.createHorizontalStrut(20));
		supplierInfoPanel.add(Box.createVerticalStrut(20));
		supplierInfoPanel.add(b2);

		Box b3 = Box.createHorizontalBox();
		lblSoDienThoai = new JLabel("Số điện thoại:");
		b3.add(lblSoDienThoai);
		b3.add(Box.createHorizontalStrut(20));
		txtSoDienThoai = new JTextField();
		b3.add(txtSoDienThoai);
		b3.add(Box.createHorizontalStrut(20));
		supplierInfoPanel.add(Box.createVerticalStrut(20));
		supplierInfoPanel.add(b3);

		lblDiaChi.setPreferredSize(lblNhaCungCap.getPreferredSize());
		lblSoDienThoai.setPreferredSize(lblNhaCungCap.getPreferredSize());
		rightPanel.add(supplierInfoPanel);
		middlePanel.add(rightPanel);

		// Bottom panel (Buttons)
		JPanel bottomPanel = new JPanel(new BorderLayout());
		bottomPanel.setBackground(new Color(255, 240, 245));
		bottomPanel.setPreferredSize(new Dimension(10, 50));

		getContentPane().add(topPanel, BorderLayout.NORTH);

		horizontalBox_3 = Box.createHorizontalBox();
		topPanel.add(horizontalBox_3, BorderLayout.NORTH);
		getContentPane().add(middlePanel, BorderLayout.CENTER);
		getContentPane().add(bottomPanel, BorderLayout.SOUTH);

		horizontalBox_6 = Box.createHorizontalBox();
		horizontalBox_6.setPreferredSize(new Dimension(671, 70));
		bottomPanel.add(horizontalBox_6, BorderLayout.CENTER);

		btnSave = new JButton("Thêm mới");
		btnSave.setBackground(new Color(0, 191, 255));
		btnSave.setMaximumSize(new Dimension(120, 45));
		horizontalBox_6.add(btnSave);
		
		horizontalStrut_5 = Box.createHorizontalStrut(20);
		horizontalBox_6.add(horizontalStrut_5);

		btnUpdate = new JButton();
		btnUpdate.setText("Sửa");
		btnUpdate.setBackground(new Color(0, 191, 255));
		btnUpdate.setMaximumSize(new Dimension(90, 45));
		horizontalBox_6.add(btnUpdate);
		
		horizontalStrut_6 = Box.createHorizontalStrut(20);
		horizontalBox_6.add(horizontalStrut_6);

		btnDelete = new JButton("Xóa");
		btnDelete.setBackground(new Color(0, 191, 255));
		btnDelete.setMaximumSize(new Dimension(90, 45));
		horizontalBox_6.add(btnDelete);
		
		horizontalStrut_7 = Box.createHorizontalStrut(20);
		horizontalBox_6.add(horizontalStrut_7);

		btnDeleteAll = new JButton("Xóa trắng");
		btnDeleteAll.setBackground(new Color(0, 191, 255));
		btnDeleteAll.setMaximumSize(new Dimension(90, 45));
		horizontalBox_6.add(btnDeleteAll);
		
		verticalStrut_1 = Box.createVerticalStrut(2);
		bottomPanel.add(verticalStrut_1, BorderLayout.SOUTH);
		
		verticalStrut_2 = Box.createVerticalStrut(2);
		bottomPanel.add(verticalStrut_2, BorderLayout.NORTH);
		
		horizontalStrut_4 = Box.createHorizontalStrut(470);
		bottomPanel.add(horizontalStrut_4, BorderLayout.WEST);
		
		horizontalBox_7 = Box.createHorizontalBox();
		bottomPanel.add(horizontalBox_7, BorderLayout.EAST);
		
		btnAddToHoaDon = new JButton("Thêm vào hóa đơn");
		btnAddToHoaDon.setBackground(new Color(0, 191, 255));
		btnAddToHoaDon.setMaximumSize(new Dimension(131, 45));
		horizontalBox_7.add(btnAddToHoaDon);
		
		horizontalStrut_3 = Box.createHorizontalStrut(2);
		horizontalBox_7.add(horizontalStrut_3);

		txtTenSP.setEditable(false);
		txtThuongHieu.setEditable(false);
		txtSoLuongTon.setEditable(false);
		txtGiaNhap.setEditable(false);
		txtGiaBan.setEditable(false);
		txtTenNhaCungCap.setEditable(false);
		txtDiaChi.setEditable(false);
		txtSoDienThoai.setEditable(false);
		cboNhaCungCap.setEnabled(false);

		tableSP.addMouseListener(this);
		btnTim.addActionListener(this);
		btnUpdate.addActionListener(this);
		btnDelete.addActionListener(this);
		btnDeleteAll.addActionListener(this);
		cboNhaCungCap.addActionListener(this);
		btnSave.addActionListener(this);
		btnAddToHoaDon.addActionListener(this);
	}

	public void loadNhaCungCap() {
		try {
			client.out.writeUTF("getAllNCC");
			client.out.flush();
			listNCC = (List<NhaCungCap>) client.in.readObject();
			for (NhaCungCap ncc : listNCC) {
				cboNhaCungCap.addItem(ncc.getMaNCC());
				currentNCC = ncc.getMaNCC();

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String convertMoney(double gia) {
		return NumberFormat.getCurrencyInstance(new Locale("vi", "VN")).format(gia);
	}

	public void loadData() {
		try {
			client.out.writeUTF("getAllSP");
			client.out.flush();
			listSP = (List<SanPham>) client.in.readObject();
			modelSP.getDataVector().removeAllElements();
			for (SanPham sp : listSP) {
				Object[] row = { sp.getMaSP(), sp.getTenSP(), sp.getNhaCungCap().getTenNCC(),
						convertMoney(sp.getGiaMua()), convertMoney(sp.getGiaBan()), sp.getSoLuongTon() };
				currentSanPham = sp.getMaSP();
				modelSP.addRow(row);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void loadHoaDon() {
		try {
			client.out.writeUTF("getAllHD");
			client.out.flush();
			List<HoaDon> dsHD = (List<HoaDon>) client.in.readObject();
			currentMaHD = String.format("HD%02d", dsHD.size()+1);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				//FlatLightLaf.setup();
				
				new DanhMucSanPham_GUI().setVisible(true);
			}
		});
	}

	public void xoaTrangSP() {
		txtTenSP.setText("");
		txtSoLuongTon.setText("");
		txtGiaNhap.setText("");
		txtGiaBan.setText("");
		txtTenSP.setEditable(true);
		txtSoLuongTon.setEditable(true);
		txtGiaNhap.setEditable(true);
		txtGiaBan.setEditable(true);
		cboNhaCungCap.setEnabled(true);
	}

	public void lockSP() {
		txtTenSP.setEditable(false);
		txtSoLuongTon.setEditable(false);
		txtGiaNhap.setEditable(false);
		txtGiaBan.setEditable(false);
	}

	public void unlockSP() {
		txtTenSP.setEditable(true);
		txtSoLuongTon.setEditable(true);
		txtGiaBan.setEditable(true);
		txtGiaNhap.setEditable(true);
	}

	public void lockNCC() {
		cboNhaCungCap.setEnabled(false);
		txtTenNhaCungCap.setEditable(false);
		txtDiaChi.setEditable(false);
		txtSoDienThoai.setEditable(false);
	}

	public void unlockNCC(String oldNhaCungCap) {
		String newNCC = String.format("%s%02d", "NCC", listNCC.size() + 1);
		cboNhaCungCap.removeItem("Thêm nhà cung cấp mới");
		cboNhaCungCap.addItem(newNCC);
		cboNhaCungCap.setSelectedItem(newNCC);
		daNhap = false;
		cboNhaCungCap.addItem("Thêm nhà cung cấp mới");
		txtTenNhaCungCap.setText("");
		txtDiaChi.setText("");
		txtSoDienThoai.setText("");
		txtThuongHieu.setText("");
		cboNhaCungCap.setEnabled(false);
		txtTenNhaCungCap.setEditable(true);
		txtDiaChi.setEditable(true);
		txtSoDienThoai.setEditable(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnDeleteAll)) {
			if (!daNhap) {
				cboNhaCungCap.removeItemAt(cboNhaCungCap.getItemCount() - 2);
				daNhap = true;
			}
			xoaTrangSP();
			tableSP.clearSelection();
			// System.out.println(tableSP.getSelectedRow());
		}
		if (o.equals(cboNhaCungCap)) {
			String maNCC = cboNhaCungCap.getSelectedItem().toString();
			try {
				if (maNCC.equals("Thêm nhà cung cấp mới")) {
					if (daNhap) {
						JOptionPane.showMessageDialog(null, "Không thể thêm nhà cung cấp mới trong lúc chỉnh sửa!");
						
						return;
					}
					unlockNCC(currentNCC);
				} else {
					client.out.writeUTF("getNCCTheoMa");
					client.out.flush();
		            client.out.writeUTF(maNCC);
		            client.out.flush();
					NhaCungCap nhaCungCap = (NhaCungCap) client.in.readObject();
					txtTenNhaCungCap.setText(nhaCungCap.getTenNCC());
					txtDiaChi.setText(nhaCungCap.getDiaChi());
					txtSoDienThoai.setText(nhaCungCap.getSdt());
					txtThuongHieu.setText(nhaCungCap.getTenNCC());
				}
			} catch (Exception e2) {
				// TODO: handle exception
			}

		}
		if (o.equals(btnSave)) {
			if (checkRangBuoc()) {
				int curentSP = Integer.parseInt(currentSanPham.split("SP")[1]);
				String maSP = String.format("%s%02d", "SP", curentSP + 1);
				String tenSP = txtTenSP.getText();
				int soLuongTon = Integer.parseInt(txtSoLuongTon.getText());
				Double giaNhap = Double.parseDouble(txtGiaNhap.getText());
				Double giaBan = Double.parseDouble(txtGiaBan.getText());
				String maNCC = cboNhaCungCap.getSelectedItem().toString();
				String tenNhaCungCap = txtTenNhaCungCap.getText();
				String diaChi = txtDiaChi.getText();
				String soDienThoai = txtSoDienThoai.getText();
				try {
					client.out.writeUTF("getNCCTheoMa");
					client.out.flush();
					client.out.writeUTF(maNCC);
					client.out.flush();
					NhaCungCap nc = (NhaCungCap) client.in.readObject();
					if (nc.getMaNCC() == null) {
						nc.setMaNCC(maNCC);
						nc.setTenNCC(tenNhaCungCap);
						nc.setDiaChi(diaChi);
						nc.setSdt(soDienThoai);
						client.out.writeUTF("themNCC");
						client.out.flush();
						client.out.writeObject(nc);
						client.out.flush();
					}
					SanPham sp = new SanPham(maSP, tenSP, soLuongTon, giaBan, giaNhap, nc);
					try {
						client.out.writeUTF("themSP");
						client.out.flush();
						client.out.writeObject(sp);
						client.out.flush();
						Object[] row = { sp.getMaSP(), sp.getTenSP(), sp.getNhaCungCap().getTenNCC(),
								convertMoney(sp.getGiaMua()), convertMoney(sp.getGiaBan()), sp.getSoLuongTon() };
						currentSanPham = sp.getMaSP();
						currentNCC = nc.getMaNCC();
						modelSP.addRow(row);
						daNhap = true;
						tableSP.changeSelection(tableSP.getRowCount() - 1, 0, false, false);
						lockSP();
						lockNCC();
					} catch (Exception e1) {
						e1.printStackTrace();
					}

				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		}
		if (o.equals(btnUpdate)) {
			try {
				if (btnUpdate.getText().equals("Sửa")) {
					if (tableSP.getSelectedRow() == -1) {
						JOptionPane.showMessageDialog(null, "Chưa chọn sản phẩm để chỉnh sửa!");
						return;
					}
					int row = tableSP.getSelectedRow();
					cboNhaCungCap.setEnabled(true);
					unlockSP();
					btnUpdate.setText("Cập nhật");
					client.out.writeUTF("getSPTheoMa");
					client.out.flush();
					client.out.writeUTF(modelSP.getValueAt(row, 0).toString());
					client.out.flush();
					SanPham sp = (SanPham) client.in.readObject();
					txtGiaNhap.setText(String.format("%d", (long) sp.getGiaMua()));
					txtGiaBan.setText(String.format("%d", (long) sp.getGiaBan()));
				} else {
					if (checkRangBuoc()) {
						int row = tableSP.getSelectedRow();
						String maSP = modelSP.getValueAt(row, 0).toString();
						String tenSP = txtTenSP.getText();
						int soLuongTon = Integer.parseInt(txtSoLuongTon.getText());
						Double giaNhap = Double.parseDouble(txtGiaNhap.getText());
						Double giaBan = Double.parseDouble(txtGiaBan.getText());
						String maNCC = cboNhaCungCap.getSelectedItem().toString();
						String tenNCC = txtTenNhaCungCap.getText();
						client.out.writeUTF("getNCCTheoMa");
						client.out.flush();
						client.out.writeUTF(maNCC);
						client.out.flush();
						NhaCungCap ncc = (NhaCungCap) client.in.readObject();
						SanPham sp = new SanPham(maSP, tenSP, soLuongTon, giaBan, giaNhap,ncc);
						try {
							client.out.writeUTF("suaSP");
							client.out.flush();
							client.out.writeObject(sp);
							client.out.flush();
							btnUpdate.setText("Sửa");
							JOptionPane.showMessageDialog(null, "Sửa sản phẩm thành công!");
							modelSP.setValueAt(tenSP, row, 1);
							modelSP.setValueAt(sp.getNhaCungCap().getTenNCC(), row, 2);
							modelSP.setValueAt(convertMoney(giaNhap), row, 3);
							modelSP.setValueAt(convertMoney(giaBan), row, 4);
							modelSP.setValueAt(soLuongTon, row, 5);

						} catch (Exception e1) {
							e1.printStackTrace();
						}

					}
				}
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		if (o.equals(btnDelete)) {
			int row = tableSP.getSelectedRow();
			if (row == -1) {
				JOptionPane.showMessageDialog(null, "Chưa chọn sản phẩm để xóa!");
				return;
			} else {
				try {
					String maSP = modelSP.getValueAt(row, 0).toString();
					client.out.writeUTF("xoaSP");
					client.out.flush();
					client.out.writeUTF(maSP);
					client.out.flush();
					
					JOptionPane.showMessageDialog(null, "Xóa thành công!");
					modelSP.removeRow(row);
					loadData();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}
		if (o.equals(btnAddToHoaDon)) {
			SanPham sp = listSP.get(tableSP.getSelectedRow());
			String soLuong = JOptionPane.showInputDialog("Nhập số lượng cần mua: ");
			try {
				if(soLuong == null || soLuong.trim().equals("")) {
					soLuong = "1";
				} else {
					int sLuong = Integer.parseInt(soLuong);
					if(sp.getSoLuongTon() < sLuong) {
						JOptionPane.showMessageDialog(null, "Số lượng cần mua vượt quá số lượng tồn.");
						return;
					} else {
						client.out.writeUTF("getHDTheoMa");
						client.out.flush();
						client.out.writeUTF(currentMaHD);
						client.out.flush();
						HoaDon hd = (HoaDon) client.in.readObject();
						if(hd == null) {
							Date today = new Date(System.currentTimeMillis());
							client.out.writeUTF("taoHDMoi");
							client.out.flush();
							client.out.writeUTF(currentMaHD);
							client.out.flush();
							client.out.writeObject(today);
							client.out.flush();
							
						}
						client.out.writeUTF("themCTHD");
						client.out.flush();
						client.out.writeUTF(currentMaHD);
						client.out.flush();
						client.out.writeUTF(sp.getMaSP());
						client.out.flush();
						client.out.writeInt(sLuong);
						client.out.flush();
						client.out.writeUTF("suaSoLuongSPTheoMa");
						client.out.flush();
						client.out.writeUTF(sp.getMaSP());
						client.out.flush();
						client.out.writeInt(sp.getSoLuongTon()-sLuong);
						client.out.flush();
						JOptionPane.showMessageDialog(null, "Thêm vào hóa đơn thành công!");
						loadData();
						
					}

				}	
			} catch (Exception e2) {
				// TODO: handle exception
			}	

		}
		if(o.equals(btnTim)) {
            try {
            	String ma = txtSearchBox.getText();
                if(ma.trim().equals("")) {
                	loadData();
                	return;
                }
                client.out.writeUTF("getSPTheoMa");
                client.out.flush();
                client.out.writeUTF(ma);
                client.out.flush();
                SanPham sp = (SanPham) client.in.readObject();
                if(sp.getMaSP() != null) {
                	client.out.writeUTF("getNCCTheoMa");
                	client.out.flush();
                	client.out.writeUTF(sp.getNhaCungCap().getMaNCC());
                	client.out.flush();
                    NhaCungCap ncc = (NhaCungCap) client.in.readObject();
                    Object[] row = { sp.getMaSP(), sp.getTenSP(), ncc.getTenNCC(),
                            convertMoney(sp.getGiaMua()), convertMoney(sp.getGiaBan()), sp.getSoLuongTon() };
                    currentSanPham = sp.getMaSP();
                    modelSP.getDataVector().removeAllElements();
                    modelSP.addRow(row);	
                } else {
                	JOptionPane.showMessageDialog(null, "Không tìm thấy sản phẩm nào phù hợp.");
                	return;
                }
			} catch (Exception e2) {
				// TODO: handle exception
			}

        }

	}

	public boolean checkRangBuoc() {
		int curentSP = Integer.parseInt(currentSanPham.split("SP")[1]);
		String maSP = String.format("%s%02d", "SP", curentSP + 1);
		String tenSP = txtTenSP.getText();
		String soLuongTon = txtSoLuongTon.getText();
		String giaNhap = txtGiaNhap.getText();
		String giaBan = txtGiaBan.getText();
		String maNCC = cboNhaCungCap.getSelectedItem().toString();
		String tenNhaCungCap = txtTenNhaCungCap.getText();
		String diaChi = txtDiaChi.getText();
		String soDienThoai = txtSoDienThoai.getText();
		if (tenSP.trim().equals("")) {
			JOptionPane.showMessageDialog(null, "Tên sản phẩm không được để trống!");
			return false;
		}
		if (soLuongTon.trim().equals("")) {
			JOptionPane.showMessageDialog(null, "Số lượng không được để trống!");
			return false;
		} else {
			try {
				int slTon = Integer.parseInt(soLuongTon);
				if (slTon <= 0) {
					JOptionPane.showMessageDialog(null, "Số lượng không được bé hơn hoặc bằng 0!");
					return false;
				}
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Số lượng không hợp lệ!");
				return false;
			}

		}
		if (giaNhap.trim().equals("")) {
			JOptionPane.showMessageDialog(null, "Giá nhập không được để trống!");
			return false;
		} else {
			try {
				Double gNhap = Double.parseDouble(giaNhap);
				if (gNhap <= 0) {
					JOptionPane.showMessageDialog(null, "Giá nhập được bé hơn hoặc bằng 0!");
					return false;
				}
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Giá nhập không hợp lệ!");
				return false;
			}

		}
		if (giaBan.trim().equals("")) {
			JOptionPane.showMessageDialog(null, "Giá bán không được để trống!");
			return false;
		} else {
			try {
				Double gBan = Double.parseDouble(giaBan);
				if (gBan <= 0) {
					JOptionPane.showMessageDialog(null, "Giá bán được bé hơn hoặc bằng 0!");
					return false;
				}
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Giá bán không hợp lệ!");
				return false;
			}

		}
		if (tenNhaCungCap.trim().equals("")) {
			JOptionPane.showMessageDialog(null, "Tên nhà cung cấp không được để trống!");
			return false;
		}
		if (diaChi.trim().equals("")) {
			JOptionPane.showMessageDialog(null, "Địa chỉ không được để trống!");
			return false;
		}
		if (soDienThoai.trim().equals("")) {
			JOptionPane.showMessageDialog(null, "Số điện thoại không được để trống!");
			return false;
		}
		return true;

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (!daNhap) {
			cboNhaCungCap.removeItemAt(cboNhaCungCap.getItemCount() - 2);
			daNhap = true;
		}
		lockSP();
		lockNCC();
		int row = tableSP.getSelectedRow();
		txtTenSP.setText((String) modelSP.getValueAt(row, 1));
		txtThuongHieu.setText((String) modelSP.getValueAt(row, 2));
		txtSoLuongTon.setText(modelSP.getValueAt(row, 5).toString());
		txtGiaNhap.setText((String) modelSP.getValueAt(row, 3));
		txtGiaBan.setText((String) modelSP.getValueAt(row, 4));
		try {
			client.out.writeUTF("getSPTheoMa");
			client.out.flush();
			client.out.writeUTF(modelSP.getValueAt(row, 0).toString());
			client.out.flush();
			NhaCungCap nc = (NhaCungCap) client.in.readObject();
			cboNhaCungCap.setSelectedItem(nc.getMaNCC());
			txtTenNhaCungCap.setText(nc.getTenNCC());
			txtDiaChi.setText(nc.getDiaChi());
			txtSoDienThoai.setText(nc.getSdt());
		} catch (Exception e2) {
			// TODO: handle exception
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

}