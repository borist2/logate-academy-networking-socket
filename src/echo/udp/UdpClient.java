package echo.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;

public class UdpClient
{
    public static void main(String[] args)
    {
        String hostname = "localhost";
        int port = 4445;
        try (DatagramSocket socket = new DatagramSocket())
        {
            socket.setSoTimeout(10000); // Set timeout in milliseconds

            InetAddress address = InetAddress.getByName(hostname);
            byte[] sendData = "Hello from UDP client".getBytes();

            DatagramPacket packet = new DatagramPacket(sendData, sendData.length, address, port);
            socket.send(packet); // Send packet to server

            byte[] buffer = new byte[1024];
            DatagramPacket responsePacket = new DatagramPacket(buffer, buffer.length);
            socket.receive(responsePacket); // Receive response from server
            String received = new String(responsePacket.getData(), 0, responsePacket.getLength());
            System.out.println("Server says: " + received);

        } catch (SocketTimeoutException e)
        {
            System.out.println("Socket timed out!");
        } catch (IOException e)
        {
            System.out.println("Client error: " + e.getMessage());
        }
    }
}
