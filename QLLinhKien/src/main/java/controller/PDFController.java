package controller;

import com.itextpdf.text.*;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;


import dao.ChiTietHoaDon_DAO;
import dao.HoaDon_DAO;
import dao.KhachHang_DAO;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.KhachHang;
import jakarta.persistence.EntityManager;
import service.EntityManagerFactoryUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class PDFController {

    private static final Font GLOBAL_FONT = getFont();
	private static ArrayList<ChiTietHoaDon> dsSp;
	private static double thanhTien = 0;
	static EntityManagerFactoryUtil entityManager = new EntityManagerFactoryUtil();
	static EntityManager entity = entityManager.getEnManager();
 
	private static void addTopPanel(Document document, HoaDon hd) throws DocumentException {
	    PdfPTable topTable = new PdfPTable(2);
	    topTable.setWidthPercentage(100);


	    PdfPCell leftCell = new PdfPCell();
	    leftCell.addElement(new Paragraph("Cửa hàng linh kiện Bảo Giang\n", GLOBAL_FONT));
	    leftCell.addElement(new Paragraph("Số 1 Nguyễn Văn Bảo, Quận Gò Vấp, Thành phố Hồ Chí Minh", GLOBAL_FONT));
	    leftCell.setBorder(Rectangle.NO_BORDER);
	    topTable.addCell(leftCell);


	    PdfPCell rightCell = new PdfPCell();

	    rightCell.addElement(new Paragraph("Ngày tạo hóa đơn\n", GLOBAL_FONT));
	    rightCell.addElement(new Paragraph(hd.getNgayTao().toString()));
	    rightCell.setBorder(Rectangle.NO_BORDER);
	    topTable.addCell(rightCell);

	    document.add(topTable);


	    document.add(new Paragraph("\n"));
	}
    private static Font getFont() {
        try {
            BaseFont baseFont = BaseFont.createFont("image/bvn.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            return new Font(baseFont, 12, Font.NORMAL, BaseColor.BLACK);
        } catch (Exception e) {
            e.printStackTrace();
            return FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL, BaseColor.BLACK);
        }
    }

	public static String convertMoney(double gia) {
		return NumberFormat.getCurrencyInstance(new Locale("vi", "VN")).format(gia);
	}

    private static void addMiddlePanel(Document document, HoaDon hd, ArrayList<ChiTietHoaDon> dsSp) throws DocumentException {
    	KhachHang_DAO kh_dao = new KhachHang_DAO(entity);

        Paragraph title = new Paragraph("Thông tin hóa đơn", GLOBAL_FONT);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);

        Paragraph customerName = new Paragraph("Tên khách hàng: "+hd.getKhachHang().getTenKH(), GLOBAL_FONT);
        customerName.setAlignment(Element.ALIGN_LEFT);
        document.add(customerName);

        Paragraph cashierName = new Paragraph("Tên nhân viên: "+hd.getNhanVien().getTenNV(), GLOBAL_FONT);
        cashierName.setAlignment(Element.ALIGN_LEFT);
        document.add(cashierName);
        document.add(new Paragraph("\n"));

        PdfPTable productTable = new PdfPTable(4); 
        productTable.setWidthPercentage(100);

        addTableHeader(productTable);
        int i=1;
        for (ChiTietHoaDon sp : dsSp) {
            addTableRow(productTable, ""+i++, sp.getSp().getTenSP(), ""+sp.getSoLuong(), convertMoney(sp.getSoLuong()*sp.getSp().getGiaBan()), GLOBAL_FONT);
            
            thanhTien+= (sp.getSp().getGiaBan()*sp.getSoLuong());
		}



        document.add(productTable);


        document.add(new Paragraph("\n"));
    }

    private static void addBottomPanel(Document document, HoaDon hd) throws DocumentException {

        Paragraph discount = new Paragraph("Giảm trừ: "+convertMoney(hd.getSoLuongGiam()), GLOBAL_FONT);
        discount.setAlignment(Element.ALIGN_LEFT);
        document.add(discount);


        Paragraph tax = new Paragraph("Thuế: 2%", GLOBAL_FONT);
        tax.setAlignment(Element.ALIGN_LEFT);
        document.add(tax);


        Paragraph grandTotal = new Paragraph("Thành tiền: "+convertMoney(thanhTien-thanhTien*0.02), GLOBAL_FONT);
        grandTotal.setAlignment(Element.ALIGN_LEFT);
        document.add(grandTotal);


        Paragraph thankYou = new Paragraph("Cảm ơn quý khách đã mua hàng!", GLOBAL_FONT);
        thankYou.setAlignment(Element.ALIGN_CENTER);
        document.add(thankYou);
    }

    private static void addTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setPhrase(new Paragraph("Mã sản phẩm", GLOBAL_FONT));
        table.addCell(cell);

        cell.setPhrase(new Paragraph("Tên sản phẩm", GLOBAL_FONT));
        table.addCell(cell);

        cell.setPhrase(new Paragraph("Số lượng", GLOBAL_FONT));
        table.addCell(cell);

        cell.setPhrase(new Paragraph("Giá", GLOBAL_FONT));
        table.addCell(cell);
    }
    private static PdfPCell createCell(String content, Font font) {
        PdfPCell cell = new PdfPCell(new Phrase(content, font));

        return cell;
    }

    private static void addTableRow(PdfPTable table, String productId, String productName, String quantity, String price, Font globalFont) {
        table.addCell(createCell(productId, globalFont));
        table.addCell(createCell(productName, globalFont));
        table.addCell(createCell(quantity, globalFont));
        table.addCell(createCell(price, globalFont));
    }
    
    public static void InHoaDon(String maHD) {
    	
        String outputPdfPath = "target/pdf/"+maHD+".pdf";
        HoaDon_DAO hd_dao = new HoaDon_DAO(entity);
        ChiTietHoaDon_DAO cthd_dao = new ChiTietHoaDon_DAO(entity);
        dsSp = cthd_dao.layDuLieuHoaDonTheoMa(maHD);
        HoaDon hd = hd_dao.getHoaDonTheoMa(maHD);

        try {
        	Document document = new Document(PageSize.A4.rotate());
            PdfWriter.getInstance(document, new FileOutputStream(new File(outputPdfPath)));

            document.open();
            addTopPanel(document, hd);
            addMiddlePanel(document, hd, dsSp);
            addBottomPanel(document, hd);
            document.close();

            System.out.println("In hóa đơn thành công: " + outputPdfPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    	
    }
    
    public static void main(String[] args) {
	}
  
}
