package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.formdev.flatlaf.FlatLightLaf;

import dao.TaiKhoan_DAO;
import entity.TaiKhoan;
import jakarta.persistence.EntityManager;
import service.EntityManagerFactoryUtil;

public class DangNhap_GUI extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtTK;
	private JButton btnDN;
	private JLabel lblTT;
	private JLabel lblTK;
	private JLabel lblMK;
	private JButton btnHB;
	private TaiKhoan_DAO tk_dao;
	private JCheckBox chkShowMK;
	private JPasswordField txtMK;
	
	
	public DangNhap_GUI() {
		setTitle("Phần mềm quản lý linh kiện");
		setSize(420, 250);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		EntityManagerFactoryUtil entityManager = new EntityManagerFactoryUtil();
		EntityManager entity = entityManager.getEnManager();
		tk_dao = new TaiKhoan_DAO(entity);
		//panel
		JPanel p = new JPanel();
		p.setLayout(new BorderLayout());
		add(p);	
		JPanel pTop = new JPanel();
		p.add(pTop, BorderLayout.NORTH);	
		JPanel pCen = new JPanel();
		p.add(pCen, BorderLayout.CENTER);
		JPanel pBot = new JPanel();
		p.add(pBot, BorderLayout.SOUTH);
		//bg
		Color bg = new Color(0, 153, 255);
		pTop.setBackground(bg);
		pCen.setBackground(new Color(255, 240, 245));
		pBot.setBackground(new Color(255, 240, 245));
		//title	
		pTop.add(lblTT = new JLabel("ĐĂNG NHẬP"));
		lblTT.setFont(new Font("Arial", Font.BOLD, 20));
		lblTT.setForeground(Color.WHITE);		
		//box
		Box b, b1, b2, b3;
		b = Box.createVerticalBox();
		b1 = Box.createHorizontalBox();
		b2 = Box.createHorizontalBox();
		b3 = Box.createHorizontalBox();
		//b1
		b1.add(lblTK = new JLabel("Tài khoản:"));
		b1.add(Box.createHorizontalStrut(40));
		b1.add(txtTK = new JTextField(15));
		lblTK.setFont(new Font("Arial", Font.PLAIN, 15));
		lblTK.setForeground(Color.BLACK);
		//b2
		b2.add(lblMK = new JLabel("Mật khẩu:"));
		b2.add(Box.createHorizontalStrut(40));
		b2.add(txtMK = new JPasswordField(15));
		lblMK.setFont(new Font("Arial", Font.PLAIN, 15));
		lblMK.setForeground(Color.BLACK);
		//b3
		b3.add(Box.createHorizontalStrut(200));
		b3.add(chkShowMK = new JCheckBox("Xem mật khẩu"));
		chkShowMK.setBackground(new Color(255, 240, 245));
		//add box
		b.add(Box.createRigidArea(new Dimension(0, 20)));
		b.add(b1);
		b.add(Box.createRigidArea(new Dimension(0, 20)));
		b.add(b2);
		b.add(Box.createRigidArea(new Dimension(0, 10)));
		b.add(b3);
		pCen.add(b);
		//Size
		lblMK.setPreferredSize(lblTK.getPreferredSize());
		//button
		pBot.add(btnDN = new JButton("Đăng nhập"));
		btnDN.setForeground(Color.WHITE);
		btnDN.setBackground(new Color(0, 191, 255));
		pBot.add(btnHB = new JButton("Hủy bỏ"));
		btnHB.setForeground(Color.WHITE);
		btnHB.setBackground(new Color(0, 191, 255));
		//action
		btnDN.addActionListener(this);
		btnHB.addActionListener(this);
		chkShowMK.addActionListener(this);
		txtTK.setText("NV01");
		txtMK.setText("1111");
		
		
	}
	public static void main(String[] args) {
		FlatLightLaf.setup();
		new DangNhap_GUI().setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o.equals(btnDN)) {
			try {
				dangNhap();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if(o.equals(btnHB)) {
			int option = JOptionPane.showConfirmDialog(null, "Bạn có thực sự muốn thoát?", "Thoát?", JOptionPane.YES_NO_OPTION);
			if (option == JOptionPane.YES_OPTION) {
				System.exit(0);
			}
		}
		if (o.equals(chkShowMK)) {
			if (chkShowMK.isSelected()) {
				txtMK.setEchoChar((char) 0);
			} else {
				txtMK.setEchoChar('*');
			}
		}
	}
	
	public void dangNhap() throws IOException {
		String tenTk = txtTK.getText();
		String matKhau = txtMK.getText();
		TaiKhoan tk = tk_dao.getTKTheoTen(tenTk);
		if(tk.getTenTK() == null) {
			JOptionPane.showMessageDialog(null, "Tài khoản không đúng!");
		}
		else if (!tk.getMatKhau().equals(matKhau)) {
			JOptionPane.showMessageDialog(null, "Mật khẩu không đúng!");
		}
		else {
			JOptionPane.showMessageDialog(this, "Đăng nhập thành công");
			new TrangChu_GUI().setVisible(true);
			this.dispose();
			
		}
	}
}
