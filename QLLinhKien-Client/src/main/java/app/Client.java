package app;

import java.io.*;
import java.net.*;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class Client {
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    public void startConnection(String ip, int port) throws IOException {
        socket = new Socket(ip, port);
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        
        // Start a separate thread to continuously receive messages from the server
        new Thread(new Runnable() {
			@Override
			public void run() {
			    try {
			        while (true) {
			            final String message = in.readLine();
			            if (message == null) {
			                break;
			            }

			            // Display the message in the GUI
			            SwingUtilities.invokeLater(new Runnable() {
							@Override
							public void run() {
							    MainGUI.appendMessage(message);
							}
						});
			        }
			    } catch (IOException e) {
			        // Handle connection reset as server left
			        if (e instanceof SocketException && e.getMessage().contains("Connection reset")) {
			            SwingUtilities.invokeLater(new Runnable() {
							@Override
							public void run() {
							    JOptionPane.showMessageDialog(null, "Server đã rời khỏi cuộc trò chuyện!");
							    System.exit(0);
							}
						});
			        } else {
			            e.printStackTrace();
			        }
			    }
			}
		}).start();
    }

    public void sendMessage(String message) {
        out.println(message);
    }

    public void stopConnection() throws IOException {
        in.close();
        out.close();
        socket.close();
    }

    public static void main(String[] args) throws IOException {
        Client client = new Client();
        client.startConnection("127.0.0.1", 12345);

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String message;
        while ((message = reader.readLine()) != null) {
            client.sendMessage(message);
        }

        client.stopConnection();
    }
}