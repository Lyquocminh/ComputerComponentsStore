package gui;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

public class Chat_GUI extends JFrame {
    private JTextArea logArea;
    private JPanel clientInfoPanel;
    private JLabel clientNameLabel;
    private JLabel clientIdLabel;
    private JLabel clientImageLabel;
    private JTextField chatField;
    private JButton sendButton;
    private List<PrintWriter> clientWriters = new ArrayList<>();
    private Map<Socket, String> clientInfo = new HashMap<>();

    public Chat_GUI() {
        setTitle("Chat Server");
        logArea = new JTextArea(16, 50);
        logArea.setEditable(false);
        JScrollPane logScrollPane = new JScrollPane(logArea);

        clientInfoPanel = new JPanel(new BorderLayout());
        clientNameLabel = new JLabel();
        clientNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        clientIdLabel = new JLabel();
        clientIdLabel.setHorizontalAlignment(SwingConstants.CENTER);
        clientImageLabel = new JLabel();
        clientImageLabel.setIcon(null);
        clientImageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        clientInfoPanel.add(clientImageLabel, BorderLayout.NORTH);
        clientInfoPanel.add(clientNameLabel, BorderLayout.CENTER);
        clientInfoPanel.add(clientIdLabel, BorderLayout.SOUTH);

        chatField = new JTextField(50);
        sendButton = new JButton("Gửi");

        sendButton.addActionListener(e -> broadcastMessage("Nhân viên: " + chatField.getText()));
        chatField.addActionListener(e -> {
            broadcastMessage("Nhân viên: " + chatField.getText());
            chatField.setText("");
        });

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(logScrollPane, BorderLayout.CENTER);
        panel.add(chatField, BorderLayout.SOUTH);
        panel.add(sendButton, BorderLayout.EAST);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, clientInfoPanel, panel);
        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerLocation(150);

        getContentPane().add(splitPane, BorderLayout.CENTER);
        pack();

        startServer();
    }

    private void startServer() {
        new Thread(() -> {
            try {
                ServerSocket serverSocket = new ServerSocket(12345);
                logArea.append("Chat đã được mở, đang đợi người tham gia...\n");
                while (true) {
                    Socket clientSocket = serverSocket.accept();
                    logArea.append("Khách hàng đã tham gia chat! "+"\n");
                    PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
                    clientWriters.add(writer);

                    // Read JSON string from client
                    BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    String jsonString = reader.readLine();
                    JSONObject json = new JSONObject(jsonString);

                    // Get client info from JSON string
                    String id = json.getString("id");
                    String name = json.getString("name");

                    // Set client info in map
                    clientInfo.put(clientSocket, id + " - " + name);

                    // Update client info panel
                    SwingUtilities.invokeLater(() -> {
                        clientNameLabel.setText("Tên: " + name);
                        clientIdLabel.setText("ID: " + id);
                        ImageIcon imageIcon = new ImageIcon(Chat_GUI.class.getResource("/image/customer.png"));
                        Image scaledImage = imageIcon.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
                        clientImageLabel.setIcon(new ImageIcon(scaledImage));
                    });

                    // Send welcome message to client
                    writer.println("Chào mừng " + name + " đến với dịch vụ hỗ trợ khách hàng!");

                    // Broadcast new client info to all clients
                    broadcastClientInfo();

                    new Thread(new ClientHandler(clientSocket, writer)).start();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void broadcastMessage(String message) {
        SwingUtilities.invokeLater(() -> {
            logArea.append(message + "\n");

            for (PrintWriter writer : clientWriters) {
                writer.println(message);
            }
        });
    }

    private void broadcastClientInfo() {
        for (Socket clientSocket : clientInfo.keySet()) {
            try {
                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);

                // Get client info from map
                String clientInfoString = clientInfo.get(clientSocket);

                // Send client info to client
                writer.println("Danh sách khách hàng đang trực tuyến:");
                writer.println(clientInfoString);
            } catch (IOException e) {
                // Handle error
                e.printStackTrace();
            }
        }
    }

    private class ClientHandler implements Runnable {
        private Socket clientSocket;
        private PrintWriter writer;

        public ClientHandler(Socket clientSocket, PrintWriter writer) {
            this.clientSocket = clientSocket;
            this.writer = writer;
        }

        @Override
        public void run() {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
                String message;
                while ((message = in.readLine()) != null) {
                    logArea.append("Khách hàng: " + message + "\n");
                }

                // Client has disconnected
                broadcastMessage("Khách hàng đã rời khỏi đoạn Chat này.");
                clientWriters.remove(writer);
                clientInfo.remove(clientSocket);
                broadcastClientInfo();

                // Clear client info panel
                SwingUtilities.invokeLater(() -> {
                    clientNameLabel.setText("");
                    clientIdLabel.setText("");
                    clientImageLabel.setIcon(null);
                });
            } catch (IOException e) {
                // Handle connection reset as client left the chat
                if (e instanceof SocketException && e.getMessage().contains("Connection reset")) {
                    broadcastMessage("Khách hàng đã rời khỏi đoạn Chat này.");
                    clientWriters.remove(writer);
                    clientInfo.remove(clientSocket);
                    broadcastClientInfo();

                    // Clear client info panel
                    SwingUtilities.invokeLater(() -> {
                        clientNameLabel.setText("");
                        clientIdLabel.setText("");
                        clientImageLabel.setIcon(null);
                    });
                } else {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Chat_GUI server = new Chat_GUI();
            server.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            server.setVisible(true);
        });
    }
}