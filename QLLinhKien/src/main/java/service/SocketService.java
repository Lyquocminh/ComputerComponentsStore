package service;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextArea;

public class SocketService {
    private ServerSocket serverSocket;
    private List<ClientHandler> clients = new ArrayList<>();
    private JTextArea logArea;

    public SocketService(JTextArea logArea) {
        this.logArea = logArea;
    }

    public void startServer(int port) {
        try {
            serverSocket = new ServerSocket(port);
            logArea.append("Server started. Waiting for clients...\n");
            while (true) {
                Socket clientSocket = serverSocket.accept();
                logArea.append("Client connected: " + clientSocket + "\n");
                ClientHandler clientHandler = new ClientHandler(clientSocket);
                clients.add(clientHandler);
                new Thread(clientHandler).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void broadcastMessage(String message) {
        for (ClientHandler client : clients) {
            client.sendMessage(message);
        }
    }

    private class ClientHandler implements Runnable {
        private Socket clientSocket;
        private PrintWriter out;

        public ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
            try {
                out = new PrintWriter(clientSocket.getOutputStream(), true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
                String message;
                while ((message = in.readLine()) != null) {
                    logArea.append("Client: " + message + "\n");
                    broadcastMessage("Client: " + message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void sendMessage(String message) {
            out.println(message);
        }
    }
}
