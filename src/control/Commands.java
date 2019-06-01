package control;

import java.io.IOException;

public class Commands {

    public static void main(String[] args) {

    }

    public static void toggleDisplay(int val){
        String turnOffCmd =
                "powershell (Add-Type '[DllImport(\\\"user32.dll\\\")]^public static extern int SendMessage(int hWnd, int hMsg, int wParam, int lParam);' -Name a -Pas)::SendMessage(-1,0x0112,0xF170,2)";

        String turnOnCmd =
                "powershell (Add-Type '[DllImport(\\\"user32.dll\\\")]^public static extern int SendMessage(int hWnd, int hMsg, int wParam, int lParam);' -Name a -Pas)::SendMessage(-1,0x0112,0xF170,-1)";
        Runtime runtime = Runtime.getRuntime();
        try {
            if(val == 0){
                runtime.exec("cmd.exe /c " + turnOffCmd);
            }else{
                runtime.exec("cmd.exe /c " + turnOnCmd);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void restartHost(){
        Runtime runtime = Runtime.getRuntime();
        try {
            runtime.exec("shutdown -r -t 5");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }

    public static void shutdownHost(){
        Runtime runtime = Runtime.getRuntime();
        try {
            runtime.exec("shutdown -s -t 5");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }

}
