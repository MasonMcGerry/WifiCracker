import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;
import javax.swing.text.PlainView;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.colorchooser.ColorChooserComponentFactory;
import javax.swing.Action;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

public class WifiCrackerGUI {
    WifiCracker wc = new WifiCracker();
    JFileChooser fcSSID = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
    JFileChooser fcPW = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
    JFrame f = new JFrame();
    JPanel p = new JPanel();
    JButton attack = new JButton("Attack");
    JLabel trying = new JLabel();
    JButton stop = new JButton("Stop");
    JButton selectSSID = new JButton("Select SSID List"); // maybe give option to type in one SSID or choose the file list
    JLabel ssidLabel = new JLabel("SSID List"); // shows SSID list path
    JButton selectPW = new JButton("Select Password List");
    JLabel pwLabel = new JLabel("Password List"); // shows password list path
    JTextArea consoleOutput = new JTextArea("console output");
    JScrollPane scrollpane = new JScrollPane(consoleOutput);
    Border border = BorderFactory.createLineBorder(Color.RED, 5);
    GridBagConstraints gbc = new GridBagConstraints();
   
    // method to run window
    public void window() throws Exception{
        consoleOutput.setEditable(false);
        //pwLabel.setBorder(border);
        //ssidLabel.setBorder(border);
        selectSSID.addActionListener(new ActionListener() {        
            // method to set SSID List path to ui
            public void actionPerformed(ActionEvent e){
                int rSSID = fcSSID.showOpenDialog(null); // open dialog
                String fileSSID = fcSSID.getSelectedFile().getPath();
                ssidLabel.setText(fileSSID);
            }
        });
        selectPW.addActionListener(new ActionListener() {        
            // method to set SSID List path to ui
            public void actionPerformed(ActionEvent e){
                int rPW = fcPW.showOpenDialog(null); // open dialog
                String filePW = fcPW.getSelectedFile().getPath();
                pwLabel.setText(filePW);
            }
        });
        attack.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try {attack();}
                catch(Exception ex){ex.printStackTrace();};
            }
        });
        /*
        stop.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try {stop();}
                catch(Exception ex){ex.printStackTrace();};
            }
        });
         */
        ssidLabel.setOpaque(true);
        ssidLabel.setBackground(Color.BLACK);
        ssidLabel.setForeground(Color.WHITE);
        pwLabel.setOpaque(true);
        pwLabel.setBackground(Color.BLACK);
        pwLabel.setForeground(Color.WHITE);
        consoleOutput.setBackground(Color.BLACK);
        consoleOutput.setForeground(Color.WHITE);
        //selectSSID.setOpaque(true);
        selectSSID.setBackground(Color.GRAY);
        //selectPW.setOpaque(true);
        selectPW.setBackground(Color.GRAY);

        gbc.anchor = GridBagConstraints.WEST;
        p.setLayout(new GridBagLayout());
        gbc.gridy = 0;
        gbc.gridx = 0;
        p.add(ssidLabel,gbc);
        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridy = 0;
        gbc.gridx = 1;
        p.add(selectSSID,gbc);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridy = 1;
        gbc.gridx = 0;
        p.add(pwLabel,gbc);
        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridy = 1;
        gbc.gridx = 1;
        p.add(selectPW,gbc);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridy = 2;
        gbc.gridx = 0;
        p.add(consoleOutput,gbc);
        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridy = 2;
        gbc.gridx = 1;
        p.add(attack,gbc);
        p.setBackground(Color.DARK_GRAY);
        f.add(p);
        f.setMinimumSize(new Dimension(700,200));
        //f.pack();
		f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    } // end window() 
    
    // method to get SSID List path
    public File getSSIDList(){
        File ssids = new File(ssidLabel.getText());
        return ssids;
    }
    // method to get Password List path
    public File getPasswordList(){
        File passwords = new File(pwLabel.getText());
        return passwords;
    }
    public void attack() throws Exception {
        try {
            File ssidlist = getSSIDList();
            File passwordlist = getPasswordList();
            String output = wc.runAttack(ssidlist, passwordlist);
            consoleOutput.setText(output);
            System.out.println("Attack Complete");
        }
        catch (IOException e) {e.printStackTrace();}
    }
    /*
    public void stop() throws Exception {
        try {
            wc.stop();
        }
        catch (IOException e) {e.printStackTrace();}
    }
    */
}