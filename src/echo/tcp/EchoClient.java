package echo.tcp;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class EchoClient
{
    public static void main(String[] args)
    {
        String hostname = "localhost";
        int port = 6868;

        Scanner console = new Scanner(System.in);

        try (Socket socket = new Socket(hostname, port))
        {

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);

            while (true)
            {
                System.out.println("Enter message to send to the server (or 'quit' to stop):");
                String userInput = console.nextLine();
                writer.println(userInput); // Send message to server

                if ("quit".equalsIgnoreCase(userInput))
                {
                    break;
                }

                System.out.println("Server response: " + reader.readLine());
            }

        } catch (UnknownHostException ex)
        {
            System.out.println("Server not found: " + ex.getMessage());
        } catch (IOException ex)
        {
            System.out.println("I/O error: " + ex.getMessage());
        }
    }
}
