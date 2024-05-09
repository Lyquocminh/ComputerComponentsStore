package app;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.sql.Date;

import dao.ChiTietHoaDon_DAO;
import dao.HoaDon_DAO;
import dao.NhaCungCap_DAO;
import dao.SanPham_DAO;
import entity.NhaCungCap;
import entity.SanPham;
import jakarta.persistence.EntityManager;
import service.EntityManagerFactoryUtil;

public class Server {
	public static void main(String[] args) {
		try (ServerSocket server = new ServerSocket(9999)){
			
		    
			System.out.println("Server started on port 9999");
			
			while(true) {
                Socket clientSocket = server.accept();
                
                Thread t = new Thread(new ClientHandler(clientSocket));
                t.start();
                
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}	
}

class ClientHandler implements Runnable {
	private Socket clientSocket;
	private EntityManagerFactoryUtil mangerFactoryUtil;
	private EntityManager entityManager;
	private SanPham_DAO sanPham_DAO;
	private ChiTietHoaDon_DAO chiTietHoaDon_DAO;
	private NhaCungCap_DAO nhaCungCap_DAO;
	private HoaDon_DAO hoaDon_DAO;

	public ClientHandler(Socket clientSocket) {
		this.clientSocket = clientSocket;
		this.mangerFactoryUtil = new EntityManagerFactoryUtil();
		this.entityManager = mangerFactoryUtil.getEnManager();
		this.sanPham_DAO = new SanPham_DAO(this.entityManager);
		this.chiTietHoaDon_DAO = new ChiTietHoaDon_DAO(this.entityManager);
		this.nhaCungCap_DAO = new NhaCungCap_DAO(this.entityManager);
		this.hoaDon_DAO = new HoaDon_DAO(this.entityManager);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
			ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
			
			while(true) {
			    try {

			        if (in.available() > 0) {
				String request = in.readUTF();

                
                System.out.println("Request: " + request);
				if (request.equals("getAllNCC")) {
					out.writeObject(nhaCungCap_DAO.docDuLieu());
					out.flush();
				}
				if(request.equals("getAllSP")) {
					out.writeObject(sanPham_DAO.getDSSanPham());
					out.flush();
				}
				if(request.equals("getNCCTheoMa")) {
					String maNCC = in.readUTF();
					out.writeObject(nhaCungCap_DAO.getNhaCungCapTheoMa(maNCC));
					out.flush();
				}
				if(request.equals("ThemNCC")) {
					nhaCungCap_DAO.themNhaCungCap((NhaCungCap) in.readObject());
					
				}
				if(request.equals("ThemSP")) {
					sanPham_DAO.themSanPham((SanPham) in.readObject());
				}
				if(request.equals("suaSP")) {
					sanPham_DAO.suaSanPham((SanPham) in.readObject());
				}
				if(request.equals("xoaSP")) {
					sanPham_DAO.xoaSanPhamTheoMa(in.readUTF());
				}
				if(request.equals("getHDTheoMa")) {
					String maHD = in.readUTF();
					out.writeObject(hoaDon_DAO.getHoaDonTheoMa(maHD));
					out.flush();
				}
				if(request.equals("taoHDMoi")) {
					String maHD = in.readUTF();
					Date today = (Date) in.readObject();
					hoaDon_DAO.taoHDMoi(maHD, today);
				}
				if(request.equals("themCTHD")) {
					String maHD = in.readUTF();
					String maSP = in.readUTF();
					int soLuong = in.readInt();
					chiTietHoaDon_DAO.themVaoHoaDon(maHD, soLuong, maSP);
				}
				if(request.equals("suaSoLuongSPTheoMa")) {
					String maSP = in.readUTF();
					int soLuong = in.readInt();
					sanPham_DAO.suaSoLuongSPTheoMa(maSP, soLuong);
				}
				if(request.equals("getSPTheoMa")) {
					String maSP = in.readUTF();
					out.writeObject(sanPham_DAO.getSanPhamTheoMa(maSP));
					out.flush();
				}
				if(request.equals("getAllHD")) {
					out.writeObject(hoaDon_DAO.getDSHoaDon());
					out.flush();
				}				
			        } else {
			            Thread.sleep(100); 
			        }

			    } catch (IOException e) {
                    System.out.println("I/O error occurred: " + e.getMessage());
                } catch (InterruptedException e) {
                    System.out.println("Thread interrupted: " + e.getMessage());
                }
			}
		} catch (SocketException se) {
	        // Socket closed by client or due to network error
	        System.out.println("Client socket closed or network error: " + se.getMessage());
	    } catch (Exception e) {
	        // Other exceptions
	        e.printStackTrace();
	    } finally {
	        try {
	            // Close resources
	            clientSocket.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	}
}
