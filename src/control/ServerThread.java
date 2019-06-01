package control;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerThread extends Thread {

    private Socket clientSocket;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;


    public ServerThread(Socket s){
        this.clientSocket = s;
    }

    public void run() {
        int command = 0;
        String clientDeviceModel = null;
        try{
            //Initialize ObjectIOStreams
            ois = new ObjectInputStream(clientSocket.getInputStream());
            oos = new ObjectOutputStream(clientSocket.getOutputStream());

            //Receive client device details on connect.
            try {
                clientDeviceModel = (String)ois.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            System.out.println("Client Connected: " + clientDeviceModel);

        } catch (IOException ex){
            System.out.println("Error when client connects.");
            ex.printStackTrace();
        }



        //Receiving the command from the other side.
        while(true){
            try{
                command = (Integer)ois.readObject();
                if(command!=1){
                    System.out.println("Received command code: " + command);
                    ControlHandler.execCommand(command, oos);

                }else{
                    System.out.println("Client closed the connection");
                    clientSocket.close();
                    ois.close();
                    oos.close();
                    return;
                }

            }catch(IOException | ClassNotFoundException ex){
                ex.printStackTrace();
                System.out.println("Client unexpectedly closed the connection");
                return;
            }
        }
    }
}
