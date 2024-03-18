package echo.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UdpServer
{
    public static void main(String[] args) {
        int port = 4445;

        try (DatagramSocket socket = new DatagramSocket(port)) {
            System.out.println("Server is listening on port " + port);

            byte[] buffer = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

            while (true) {
                socket.receive(packet); // Receive packet from client
                String received = new String(packet.getData(), 0, packet.getLength());
                System.out.println("Received from client: " + received);

                // Optionally, send a response back to client
                String response = "Echo: " + received;
                byte[] responseData = response.getBytes();
                DatagramPacket responsePacket = new DatagramPacket(responseData, responseData.length, packet.getAddress(), packet.getPort());
                socket.send(responsePacket);

                // Break loop if server receives "quit"
                if (received.equals("quit")) {
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }
    }
}
