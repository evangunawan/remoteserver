package control;

import java.io.IOException;
import java.io.ObjectOutputStream;

public class ControlHandler {
    private static boolean busy = false;


    public static boolean isBusy() {
        return busy;
    }

    public static void setBusy(boolean b) {
        ControlHandler.busy = b;
    }


    public static void execCommand(int val, ObjectOutputStream oos){
        String server_response = null;

        switch(val){
            case 10:
                server_response = "finish_activity";
                sendResponse(server_response, oos);
                Commands.shutdownHost();
                break;
            case 11:
                server_response = "finish_activity";
                sendResponse(server_response,oos);
                Commands.restartHost();
                break;
            case 20:
                Commands.toggleDisplay(0);
                break;
            case 21:
                Commands.toggleDisplay(1);
                break;
        }
    }

    private static void sendResponse(String response, ObjectOutputStream oos){
        try {
            oos.writeObject(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
