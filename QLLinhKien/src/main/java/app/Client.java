package app;

import java.io.*;
import java.net.*;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class Client {
    public ObjectOutputStream out;
    public ObjectInputStream in;
    
    public Client (){
		try (Socket socket = new Socket("192.168.1.125", 8888)) {
			ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}