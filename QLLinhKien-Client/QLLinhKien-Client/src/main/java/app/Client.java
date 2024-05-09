package app;

import java.io.*;
import java.net.*;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class Client {
    public static ObjectOutputStream out;
    public static ObjectInputStream in;
    
	public static void sendMessage(String message) {
        try {
            out.writeUTF(message);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 9999);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
            
            SwingUtilities.invokeLater(() -> {
                new DanhMucSanPham_GUI().setVisible(true);
            });
            
            System.out.println("Client started on port 9999");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}