package echo.tcp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer
{
    public static void main(String[] args)
    {
        int port = 6868;
        try (ServerSocket serverSocket = new ServerSocket(port))
        {
            System.out.println("Server is listening on port " + port);

            while (true)
            {
                try (Socket socket = serverSocket.accept())
                {
                    System.out.println("New client connected");

                    InputStream input = socket.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));

                    OutputStream output = socket.getOutputStream();
                    PrintWriter writer = new PrintWriter(output, true);

                    String message;
                    // Echo messages back to the client, except for "quit"
                    while ((message = reader.readLine()) != null)
                    {
                        if ("quit".equalsIgnoreCase(message))
                        {
                            System.out.println("Client disconnected");
                            break;
                        }
                        System.out.println("Received from client: " + message);
                        writer.println("Echo: " + message); // Sending response back to client
                    }
//                    socket.close(); // No need because of try-with-resources line 18
                }
            }

        } catch (IOException ex)
        {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
