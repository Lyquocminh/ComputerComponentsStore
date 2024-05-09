package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.FlatLightLaf;

public class ManHinhChinh_GUI extends JFrame {

	private JMenuBar menuBar;
	
	private JMenu menuTrangChu;
	private JMenu menuCuaHang;
	private JMenu menuNhanVien;
	private JMenu menuHoaDon;
	private JMenu menuThongKe;
	
	private JMenuItem menuitemTrangChu;
	private JMenuItem menuitemNhanVienHanhChinh;
	private JMenuItem menuitemHoaDon;
	private JMenuItem menuitemCuaHang;
	private JMenuItem menuitemNhanVienKyThuat;
	private JMenuItem menuitemThongKe;
	private JPanel contentPane;	
	private Component horizontalStrut;
	
	public ManHinhChinh_GUI() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(1300, 700);
		setLocationRelativeTo(null);


		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		//setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBackground(Color.GRAY);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(TrangChu_GUI.class.getResource("/image/main.png")));

		lblNewLabel.setBounds(0, 62, 1284, 599);
//		lblNewLabel.setBounds(54, 123, 830, 476);
		contentPane.add(lblNewLabel);
						
								JLabel lblNewLabel_1 = new JLabel("PHẦN MỀM QUẢN LÝ LINH KIỆN MÁY TÍNH", SwingConstants.CENTER);
								lblNewLabel_1.setBounds(0, 0, 1194, 84);
								contentPane.add(lblNewLabel_1);
								lblNewLabel_1.setBackground(new Color(255, 255, 255));
								lblNewLabel_1.setForeground(Color.BLACK);
								lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 24));
	
								add(contentPane);
	}
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				//FlatLightLaf.setup();
				new ManHinhChinh_GUI().setVisible(true);
			}
		});
	}

}
