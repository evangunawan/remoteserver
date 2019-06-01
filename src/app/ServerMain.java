package app;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {

    private final int LISTEN_PORT = 8025;
    private ServerSocket serverSocket;
    private Socket clientSocket;

    public ServerMain(){
        try{
            serverSocket = new ServerSocket(LISTEN_PORT);
            System.out.println("Server is running, listening on " + LISTEN_PORT);
            System.out.println("Waiting for connection...");

        }catch(IOException ex){
            System.out.println("IO Error when starting server.");
            ex.printStackTrace();
        }

        while(true){

            try{
                clientSocket = serverSocket.accept();
            }catch(IOException ex){
                System.out.println("IO Error when accepting client.");
                ex.printStackTrace();
                return;
            }
            new control.ServerThread(clientSocket).start();
        }

    }

    public static void main(String[] args) {
        System.out.println("Host: " + System.getProperty("os.name"));
        new ServerMain();
    }

}
