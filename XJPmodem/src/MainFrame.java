/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
import com.fazecast.jSerialComm.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyListener;
import java.io.OutputStream;
import java.io.IOException;
import java.util.*;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.event.CaretListener;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.ChannelSftp.LsEntry;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import java.io.File;
import org.apache.commons.io.FileUtils;
/**
 *
 * @author Electro
 */
public class MainFrame extends javax.swing.JFrame implements KeyListener{
      public static String dataBuffer = "";
      public static String dataB;
      private static int Fase = 1;
      static OutputStream Outputstream1;
      static byte[][] readyfile;              //????
      static int righe;
      static int colonne;
      static int index = 0;
      static int d;
      static SerialPort port = null; 
      static String testo = "";
      static String UPD_FILE = "";
      static String STD_FILE = "";
      static boolean end = false;
      static boolean carriage = false;
      static boolean connected = false;
      static boolean found = false;
      static String userprofile = System.getenv("USERPROFILE");
      
      static DownloadFile lf = new DownloadFile();
      
      private static final byte SOH  = 0x01;
      private static final byte EOT  = 0x04;
      private static final byte ACK  = 0x06;
      private static final byte NACK = 0x15;
      private static final byte ESC  = 0x1B;
      
      private static String furl = "http://192.168.62.251/home/pi/doora_upgrade";
      
    /**
     * Creates new form MainFrame
     */
    public MainFrame() {              
        initComponents();
        Status.addKeyListener(this); 
        showProgressBar();       
    }
    
    @Override
    public void keyReleased(KeyEvent arg0) {
    // TODO 
    }
    
    @Override
    public void keyPressed(KeyEvent arg0) {
    // TODO 
   //   System.out.println("S??");
      if(arg0.getKeyCode() == KeyEvent.VK_ENTER)
          carriage = true;
    }

    @Override
    public void keyTyped(KeyEvent arg0) {
    // TODO 
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Connect = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        Status = new javax.swing.JTextArea();
        Progress = new javax.swing.JProgressBar();
        com = new javax.swing.JLabel();
        ComPort = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Doora upgrade");

        Connect.setText("Connect");
        Connect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConnectActionPerformed(evt);
            }
        });

        Status.setColumns(20);
        Status.setRows(5);
        jScrollPane1.setViewportView(Status);

        com.setBackground(new java.awt.Color(255, 0, 0));
        com.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        com.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        ComPort.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
                ComPortPopupMenuWillBecomeVisible(evt);
            }
        });
        ComPort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComPortActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 588, Short.MAX_VALUE)
                    .addComponent(Progress, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Connect, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(com, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(ComPort, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(Connect))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ComPort, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(com, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Progress, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
   
     
    
    private void ConnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConnectActionPerformed
        Font f = com.getFont();
        com.setFont(f.deriveFont(f.getStyle()|Font.BOLD));      
        
        
   //     ComPort.removeAllItems();
        SerialPort [] AvailablePorts = SerialPort.getCommPorts();   
       
       if(ComPort.getSelectedIndex() != -1) port = AvailablePorts[ComPort.getSelectedIndex()]; 
       // use the for loop to print the available serial ports
  /*       for(SerialPort S : AvailablePorts){
              System.out.println("\n  " + S.getSystemPortName()+ "-" + S.getDescriptivePortName());
              if("STM32".equals(S.getPortDescription().substring(0,5))){
                  System.out.println(S.getPortDescription().substring(0,5));
                  port = AvailablePorts[index];  
                  ComPort.addItem(port.getSystemPortName() + "-" + port.getDescriptivePortName());
       
                  found = true;
                }    
              
            }   */
        if(!connected){
         if(!found){
             System.out.println("Dispositivo non trovato");
             Status.setText(" Dispositivo non trovato\r\n");
             com.setForeground(Color.red);             
             com.setText("N/A");
         }
         else{     
            try{ 
             port.setBaudRate(1177);
             port.setNumDataBits(8);
             port.setNumStopBits(1);
             port.setParity(SerialPort.NO_PARITY);
             port.openPort();
             
             if(port.isOpen()){
               connected = true;               
               Connect.setText("DISCONNECT");
   //            System.out.println(port.getDescriptivePortName() + " -- Porta aperta con successo");
               Status.setText(port.getSystemPortName() + "....Aperta con successo");
               com.setForeground(new Color(0,153,0));              
               com.setText(port.getSystemPortName());
               //  System.out.println("4".getBytes());                
                 Serial_EventBasedReading(port, this, lf);                
                 
             }
             else{
   //              System.out.println(port.getDescriptivePortName() + " -- Fallita apertura porta");    
                 Status.setText(port.getSystemPortName() + "....Fallita apertura");
                 com.setForeground(Color.red);
                 com.setText(port.getSystemPortName());
             }
             //     if(port.isOpen()) port.closePort();
            }
            catch(ArrayIndexOutOfBoundsException a){
   //             System.out.println("Nessuna porta seriale rilevata");
                Status.setText(port.getSystemPortName() + "....Nessuna porta seriale rilevata");
                com.setForeground(Color.red);
                com.setText(port.getSystemPortName());
            }
            catch(Exception b){
                System.out.println("ERROR");
            }
            
         }
       }
         else{
             connected = false;
             Connect.setText("CONNECT");             
             com.setForeground(Color.red);
             com.setText(port.getSystemPortName());
             Status.setText(port.getSystemPortName() + "....Chiusa");
             port.closePort();
            
         }
    }//GEN-LAST:event_ConnectActionPerformed

    private void ComPortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComPortActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComPortActionPerformed

    private void ComPortPopupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_ComPortPopupMenuWillBecomeVisible
        // TOComPort.removeAllItems();
        ComPort.removeAllItems();
        SerialPort [] AvailablePorts = SerialPort.getCommPorts();       
       // use the for loop to print the available serial ports
         for(SerialPort S : AvailablePorts){
   //           System.out.println("\n  " + S.getSystemPortName()+ "-" + S.getDescriptivePortName());
            //  if("STM32".equals(S.getPortDescription().substring(0,5))){
   //               System.out.println(S.getPortDescription().substring(0,5));
                //  port = AvailablePorts[index];  
                  ComPort.addItem(S.getSystemPortName() + "-" + S.getDescriptivePortName());
                  found = true;
            //    }    
              index++;
            }
    }//GEN-LAST:event_ComPortPopupMenuWillBecomeVisible

    /**
     * @param args the command line arguments
     */
    
    private void showProgressBar(){
      //progressBar = new JProgressBar(0, 100);
      Progress.setValue(0);
      Progress.setStringPainted(true);
    }
    
    private void setProgressValue(int value){
   //     System.out.println(value);
        Progress.setValue(value);
    }
    public void setJtext(String testo){
        Status.append(testo);
    }
    public void settText(String testo){
        Status.setText(testo);
    }
    public String getJtext(){
        return Status.getText();
    }
    
    public void addList(){
      Status.addKeyListener(this);  
    }
    
    private void clearAll(){
      Status.selectAll();
      Status.replaceSelection("");      
    }
    
    private void setCursor(){
        Status.setCaretPosition(Status.getDocument().getLength());
    }
    
    private void reqFocus(){
        Status.requestFocus();
    }
    
    public void preparaFile(){
          MainFrame mf = new MainFrame();
       // mn.addList();
        String path = userprofile + "\\Desktop\\doora.bin";
        boolean found = false;
        int portIndex = 0;
        int index = 0;      
        OpenFileBin open = new OpenFileBin();
        //byte[][]ofile = open.OpenFile("C:\\Users\\Electro\\Desktop\\DOORA1_UmtsMono_v00.03.04_868MHz.bin");
        byte[][]ofile = open.OpenFile(path);
        righe = ofile.length;
        colonne = ofile[0].length;
        int pp =0;  
        int r = 0;
       
        
        String[] ff = lf.Load();       
         
        UPD_FILE = ff[1].substring(0,25);
        UPD_FILE = UPD_FILE.substring(17,25);
        
        furl = furl + "/" + ff[1].substring(0,25)+"_868MHz.bin";
        
        Packet p = new Packet(righe,colonne,ofile);
        readyfile = p.createPacket(); 
    }

         
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
    
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });                  
        
    }
 private static void Serial_EventBasedReading(SerialPort activeport, MainFrame mf, DownloadFile lf){             
      activeport.addDataListener(new SerialPortDataListener(){
         @Override
         public int getListeningEvents(){return SerialPort.LISTENING_EVENT_DATA_RECEIVED;}
         @Override
         public void serialEvent(SerialPortEvent event){               
             Timer timer = new Timer();
             Timer timer1 = new Timer();
       //      Scanner scanner = new Scanner(System.in);             
             TimerTask task = new Helper();   
             TimerTask task1 = new Helper1();
             int s=0;
             int p =0;
             boolean dwnEnd = false;   
             
             if(activeport.isOpen() && connected == false) activeport.closePort();
             
             Outputstream1 = activeport.getOutputStream();                
             byte []newdata = event.getReceivedData();             
               for(int i=0; i<newdata.length; i++){               
                 dataBuffer += (char)newdata[i];                      
               }             
    //           System.out.print(dataBuffer);              
             try{        
                 switch(Fase){                  
                     case 1:
                          if(dataBuffer.indexOf(">") != -1){   
                              STD_FILE = dataBuffer.substring(dataBuffer.indexOf("DOORA"), dataBuffer.indexOf("DOORA")+27);
                              STD_FILE = STD_FILE.substring(19,27);
                                                    
                        //  if(STD_FILE.trim().equals(UPD_FILE.trim())){
                        //      System.out.println("BAU");
                        //      mf.setJtext("\r\nIl dispositivo ?? gi?? aggiornato");
                        //  }
                        //  else{
                              mf.setJtext("\r\nInizio a scaricare il file");
                              if(lf.DownLoad()){
                                  mf.setJtext("\r\nFile scaricato correttamente");
                                  
                              }
                              else{
                                  mf.setJtext("\r\nFile non scaicato");
                              }
                              dataBuffer = "";
                        //  }              
                        
                            mf.preparaFile();
                        
                            //System.out.print("Inserire la password : ");                            
                            mf.setJtext("\r\nInserire la password: ");   
                            mf.reqFocus();
                            mf.setCursor();
                            while(!carriage){
                                System.out.print("");                           
                            }   
                            carriage = false;
                            String pwd = mf.getJtext().substring(mf.getJtext().indexOf(":")+2,(mf.getJtext().length()));
                         //   System.out.println(pwd);                      
                            pwd = pwd+"\r\n";
                            Outputstream1.write(pwd.getBytes());                               
                            dataBuffer = "";
                            Fase = 2;
                          } 
                          else{
                   //         System.out.println("BAU");
                            Outputstream1.write(ESC);                               
                            dataBuffer = "";
                          }
                                                                               
                      break;
                      case 2:       
                          if(dataBuffer.indexOf("OK") != -1){                            
                            Outputstream1.write("upgrade main\r\n".getBytes());                            
                            Fase = 3;         
                            dataBuffer = "";   
                          }
                          else if (dataBuffer.indexOf("ERROR") != -1){
                              mf.clearAll();
                              mf.setJtext("Password errata\r\n");
                      //        System.out.println("Password errata!");                                
                              mf.setJtext("Inserire la password: ");  
                              mf.setCursor();
                              while(!carriage){
                       //         System.out.println(mf.getJtext());
                              }     
                              carriage = false;
                              String pwd = mf.getJtext().substring(mf.getJtext().indexOf(":")+2,(mf.getJtext().length()));
                     //         System.out.println(pwd);                      
                              pwd = pwd+"\r\n";
                              Outputstream1.write(pwd.getBytes());                                
                              dataBuffer = "";
                              Fase = 2;
                          //  System.exit(0);
                          }                          
                      break;
                      case 3:
                          if(dataBuffer.indexOf("C") != -1){   
                    //        System.out.println("...Received C");                            
                            if(d < righe-1){ 
                   //           System.out.print(d + " ");
                              for (int y = 0; y < 133; y++){
                                 Outputstream1.write(readyfile[d][y]);                                                                               
                              }                                                            
                              dataBuffer = "";        
                              mf.setJtext("Caricamento ....");
                              testo = mf.getJtext();  
                              timer.schedule(task, 60000L, 60000L); 
                              Fase = 5;                          
                            }
                            else{
                              Outputstream1.write(EOT);             //EOT                                                 
                              Fase = 1000;
                            }
                                                   
                          }
                          dataBuffer = "";
                      break;
                      case 4:  
                                               
                              Fase = 5;
                      break;    
                      case 5:                                                               
                          if(dataBuffer.indexOf(ACK) != -1){         //ACK                            
                              if(d < (righe-1)){    
                                 d++;                  
                                 double val = 100.0 * d/righe;                                     
                                 String tt = testo + String.format("%2.0f", val) + "%";  
                                 mf.settText(tt);
                                 mf.setCursor();
                                 mf.setProgressValue((int)val+1);
                 //                System.out.println(String.format("%2.0f", val));                                 
                                 for (int y = 0; y < 133; y++){
                                   Outputstream1.write(readyfile[d][y]);                                                                                                   
                                 }   
                             //    timer.cancel();                                    
                                 dataBuffer = "";                                                             
                              }
                              else{
                                 if(!end){ 
                                   Outputstream1.write(EOT);  
                                   dataBuffer = "";
                                   end = true;
                   //                System.out.println("Sent EOT");
                                 }
                                 else{
                                     if(dataBuffer.indexOf(">") != -1){
                                         Outputstream1.write("reset\r".getBytes());
                                    //     mf.setJtext("\r\nreset");
                                    //     System.out.println("reset");
                                         dataBuffer = "";
                                   //      timer.cancel();
                                         activeport.closePort();
                                         connected = false;
                                         Outputstream1.close();                                        
                                         activeport.closePort();
                                         String userprofile = System.getProperty("user.home");
                                         String[] cuserprofile = userprofile.split("\\\\");
                                         userprofile = cuserprofile[0] + "\\" + cuserprofile[1] + "\\" + cuserprofile[2];
                                         System.out.println(userprofile);
                                         File file = new File(userprofile + "\\Desktop\\doora.bin");
                                     //    File fileexe = new File(userprofile +"\\Downloads\\XJPmodem.exe");
                                     //    File filebat = new File(userprofile +"\\Downloads\\upgrade.bat");
                                     //    File dist = new File(userprofile +"\\Downloads\\dist");
                                     //    FileUtils.deleteDirectory(dist);
                                         if (!file.delete()){ // || !fileexe.delete() || filebat.delete()) {
                                             mf.setJtext("\r\nFile non cancellato");
                                             //                       System.out.println("Failed to delete the file.");
                                         } else { 
                                             mf.setJtext("\r\nFile cancellato");
                                             //                       System.out.println("Deleted the file: " + file.getName());
                                         }
                                         timer1.schedule(task1, 1000L, 1000L); 
                                         mf.setJtext("\r\nCaricamento completato");
                                         //System.exit(0);
                                         //Fase = 1000;
                                     }
                                 }
                              }      
                          }
                          else if(dataBuffer.indexOf(NACK) != -1){            //NACK    
             //                   System.out.println("...NACK received");
                                 p++;
                                 if(p >= 10){
             //                      System.out.println("ERRORE DI COMUNICAZIONE");
                                   mf.setJtext("Errore di comunicazione");                                   
                                   p=0;
                                   Fase = 1000;
                                 }
                                 else{
                                     for (int y = 0; y < 133; y++){
                                       Outputstream1.write((byte)readyfile[d][y]);                                                        
                                     }                                                   
                                     dataBuffer = "";                                
                                 }
                          }                          
                          else if (dataBuffer.indexOf("ERROR") != -1){
           //                     System.out.println("...Received ERROR");                                                              
           //                     System.out.println("Errore di comunicazione");
                                mf.setJtext("Errore di comunicazione");
                                Outputstream1.close();
                                activeport.closePort();
                                timer1.schedule(task1, 1000L, 1000L); 
                                //System.exit(0);
                                
                          
                          }
           //               else System.out.println("....Received  "+ dataBuffer+" for ACK");
                                                                       
                      break;
                      case 1000:                              
                          Outputstream1.close();
                          activeport.closePort();
                          timer.cancel();
                          timer1.schedule(task1, 1000L, 1000L);                                                     
                         // System.exit(0);
                      break;
                      
                }
             }
                 catch(IOException e){
                   System.out.println(e.getMessage());
             } 
             
          }

          private CaretListener CaretListener() {
              throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
          }
        
      });
  }    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ComPort;
    private javax.swing.JButton Connect;
    private javax.swing.JProgressBar Progress;
    private javax.swing.JTextArea Status;
    private javax.swing.JLabel com;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
class Helper extends TimerTask{
    public static int i = 1;  
    MainFrame mm = new MainFrame();
            
   public void run() {
    //    System.out.println("Problemi di comunicazione");     
        JOptionPane.showMessageDialog(mm,"Problemi di comunicazione");
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException ex) {
            Logger.getLogger(Helper.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.exit(0);
    }  
}

class Helper1 extends TimerTask {
    public static int i = 1;  
  
            
   public void run() {                  
        System.exit(0);
    }  
}