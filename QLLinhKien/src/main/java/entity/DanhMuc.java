package entity;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class DanhMuc {
	private String kind;
	private JLabel lbl;
	private JPanel pnl;
	
	public DanhMuc() {
		// TODO Auto-generated constructor stub
	}

	public DanhMuc(String kind, JLabel lbl, JPanel pnl) {
		super();
		this.kind = kind;
		this.lbl = lbl;
		this.pnl = pnl;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public JLabel getLbl() {
		return lbl;
	}

	public void setLbl(JLabel lbl) {
		this.lbl = lbl;
	}

	public JPanel getPnl() {
		return pnl;
	}

	public void setPnl(JPanel pnl) {
		this.pnl = pnl;
	}
	
	
}
