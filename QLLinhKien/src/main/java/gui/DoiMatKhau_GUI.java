package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
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

import dao.TaiKhoan_DAO;
import entity.TaiKhoan;
import jakarta.persistence.EntityManager;
import service.EntityManagerFactoryUtil;

public class DoiMatKhau_GUI extends JFrame implements ActionListener{
	private JLabel lblTT;
	private JLabel lblMK;
	private JPasswordField txtMK;
	private JLabel lblMKM;
	private JPasswordField txtMKM;
	private JLabel lblXN;
	private JPasswordField txtXN;
	private JButton btnXN;
	private JButton btnHuy;
	private TaiKhoan_DAO tk_dao;
	private JCheckBox chkShowPwd;

	public DoiMatKhau_GUI() {
		setTitle("Phần mềm quản lý linh kiện");
		setSize(420, 320);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Color bg = new Color(0, 191, 255);
		EntityManagerFactoryUtil entityManager = new EntityManagerFactoryUtil();
		EntityManager entity = entityManager.getEnManager();
		tk_dao = new TaiKhoan_DAO(entity);
		//panel
		JPanel pContent = new JPanel();
		pContent.setLayout(new BorderLayout());
		add(pContent);
		
		JPanel pTop = new JPanel();
		pTop.add(lblTT = new JLabel("THAY ĐỔI MẬT KHẨU"));
		lblTT.setFont(new Font("Arial", Font.BOLD, 20));
		lblTT.setForeground(Color.WHITE);
		pTop.setBackground(new Color(0, 153, 255));
		pContent.add(pTop, BorderLayout.NORTH);
		
		JPanel pCen = new JPanel();
		pCen.setBackground(new Color(255, 240, 245));
		pContent.add(pCen, BorderLayout.CENTER);
		
		JPanel pBot = new JPanel();
		pBot.setBackground(new Color(255, 240, 245));
		pContent.add(pBot, BorderLayout.SOUTH);
		//box
		Box b, b1, b2, b3, b4;
		b = Box.createVerticalBox();
		b1 = Box.createHorizontalBox();
		b2 = Box.createHorizontalBox();
		b3 = Box.createHorizontalBox();
		b4 = Box.createHorizontalBox();
		//b1
		b1.add(lblMK = new JLabel("Mật khẩu cũ:"));
		b1.add(Box.createHorizontalStrut(40));
		b1.add(txtMK = new JPasswordField(15));
		lblMK.setForeground(Color.BLACK);
		lblMK.setFont(new Font("Arial", Font.PLAIN, 15));
		//b2
		b2.add(lblMKM = new JLabel("Mật khẩu mới:"));
		b2.add(Box.createHorizontalStrut(40));
		b2.add(txtMKM = new JPasswordField(15));
		lblMKM.setForeground(Color.BLACK);
		lblMKM.setFont(new Font("Arial", Font.PLAIN, 15));
		//b3
		b3.add(lblXN = new JLabel("Xác nhận mật khẩu:"));
		b3.add(Box.createHorizontalStrut(40));
		b3.add(txtXN = new JPasswordField(15));
		lblXN.setForeground(Color.BLACK);
		lblXN.setFont(new Font("Arial", Font.PLAIN, 15));
		//b4
		b4.add(Box.createHorizontalStrut(140));
		chkShowPwd = new JCheckBox("Hiển thị mật khẩu");
		chkShowPwd.setBackground(new Color(255, 240, 245));
		b4.add(chkShowPwd);
		//preference
		lblMK.setPreferredSize(lblXN.getPreferredSize());
		lblMKM.setPreferredSize(lblXN.getPreferredSize());
		//add box
		b.add(Box.createRigidArea(new Dimension(0, 20)));
		b.add(b1);
		b.add(Box.createRigidArea(new Dimension(0, 20)));
		b.add(b2);
		b.add(Box.createRigidArea(new Dimension(0, 20)));
		b.add(b3);
		b.add(Box.createRigidArea(new Dimension(0, 20)));
		b.add(b4);
		pCen.add(b);
		//btn
		pBot.add(btnXN = new JButton("Xác nhận"));
		btnXN.setForeground(Color.WHITE);
		btnXN.setBackground(bg);
		pBot.add(btnHuy = new JButton("Hủy bỏ"));
		btnHuy.setForeground(Color.WHITE);
		btnHuy.setBackground(bg);
		//action
		btnXN.addActionListener(this);
		btnHuy.addActionListener(this);
		chkShowPwd.addActionListener(this);
		txtMK.setEchoChar('*');
		txtMKM.setEchoChar('*');
		txtXN.setEchoChar('*');
	}
	public static void main(String[] args) {
		new DoiMatKhau_GUI().setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o.equals(btnHuy)) {
			int option = JOptionPane.showConfirmDialog(null, "Bạn có thực sự muốn thoát?", "Thoát?", JOptionPane.YES_NO_OPTION);
			if (option == JOptionPane.YES_OPTION) {
				try {
					new TrangChu_GUI().setVisible(true);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				this.dispose();
			}
		}
		if(o.equals(btnXN)) {
			kiemTra();
		}
		else if (o.equals(chkShowPwd)) {
			if (chkShowPwd.isSelected()) {
				txtMKM.setEchoChar((char) 0);
				txtXN.setEchoChar((char) 0);
				txtMK.setEchoChar((char) 0);
            } else {
            	txtMKM.setEchoChar('*');
            	txtXN.setEchoChar('*');
            	txtMK.setEchoChar('*');
            }
		}
		
	}
	public boolean kiemTra() {
		String matKhauCu = txtMK.getText();
		String matKhauMoi = txtMKM.getText();
		String xacNhan = txtXN.getText();
		TaiKhoan tk = tk_dao.getTKTheoMK(matKhauCu);
		if(tk.getMatKhau() == null) {
			JOptionPane.showMessageDialog(null, "Mật khẩu không đúng!");
			return false;
		}
		if(matKhauMoi.equals("")) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập mật khẩu mới");
			return false;
		}
		if(!tk.getMatKhau().equals(matKhauCu)) {
			JOptionPane.showConfirmDialog(this, "Sai mật khẩu!");
			return false;
		}
		if(!xacNhan.equals(matKhauMoi)) {
			JOptionPane.showMessageDialog(this, "Xác nhận không trùng với mật khẩu mới");
			return false;
		}
		else {
			tk_dao.doiMatKhau(tk, matKhauMoi);
			JOptionPane.showMessageDialog(this, "Đổi thành công");
			new DangNhap_GUI().setVisible(true);
			//
			this.dispose();
		}
		return true;
	}
}
