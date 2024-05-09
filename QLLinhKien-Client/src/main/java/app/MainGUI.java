package app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;

import org.json.JSONObject;

public class MainGUI {
    private JFrame frame;
    private JPanel panel;
    private JTextField textField;
    private static JTextArea textArea;
    private Client client;
    private JButton exitButton;
    private JButton sendButton;

    public MainGUI() {
        frame = new JFrame("Chat với nhân viên");
        frame.setTitle("Chat với nhân viên");
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(new BorderLayout());

        textArea = new JTextArea();
        textArea.setEditable(false);
        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());

        textField = new JTextField();
        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = textField.getText();
                sendMessage(message);
                textField.setText("");
            }
        });
        bottomPanel.add(textField, BorderLayout.CENTER);

        sendButton = new JButton("Gửi");
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = textField.getText();
                sendMessage(message);
                textField.setText("");
            }
        });
        bottomPanel.add(sendButton, BorderLayout.EAST);

        panel.add(bottomPanel, BorderLayout.SOUTH);

        // Add exit button to sidebar
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());

        exitButton = new JButton("Thoát");
        exitButton.setEnabled(false);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        rightPanel.add(exitButton, BorderLayout.CENTER);

        panel.add(rightPanel, BorderLayout.EAST);

        frame.getContentPane().add(panel);
        frame.setVisible(true);

        client = new Client();
        try {
            appendMessage("Đang kết nối...");
            client.startConnection("127.0.0.1", 12345);
            appendMessage("Đã kết nối đến Server");
            exitButton.setEnabled(false);

            // Send JSON string to server
            JSONObject json = new JSONObject();
            json.put("id", "KH01");
            json.put("name", "Nguyen Van A");
            client.sendMessage(json.toString());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Hiện không thể kết nối đến nhân viên!");
            System.exit(0);
        }
    }

    private void sendMessage(String message) {
        client.sendMessage(message);
        appendMessage("Bạn: " + message);
    }

    public static void appendMessage(final String message) {
        SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
			    textArea.append(message + "\n");
			}
		});
    }

    public static JTextArea getTextArea() {
        return textArea;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainGUI();
            }
        });
    }

    private class Client {
        private Socket socket;
        private PrintWriter writer;
        private BufferedReader reader;

        public void startConnection(String ip, int port) throws IOException {
            socket = new Socket(ip, port);
            writer = new PrintWriter(socket.getOutputStream(), true);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            new Thread(new Runnable() {
				@Override
				public void run() {
				    try {
				        while (true) {
				            final String message = reader.readLine();
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
									textArea.append("Nhân viên đã rời khỏi cuộc trò chuyện!" + "\n");
									exitButton.setEnabled(true);
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
            writer.println(message);
        }

    }
}