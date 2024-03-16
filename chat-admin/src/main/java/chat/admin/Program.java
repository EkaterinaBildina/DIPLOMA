package chat.admin;

import java.io.IOException;
import java.net.ServerSocket;
import java.rmi.UnknownHostException;

public class Program {
    public static void main(String[] args) {
        System.out.println("Start Admin");

        try
        {
            ServerSocket serverSocket = new ServerSocket(1400);
            Admin server = new Admin(serverSocket);
            server.runServer();
        }
        catch (UnknownHostException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    }

