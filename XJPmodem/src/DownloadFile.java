/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.io.File;
import java.util.Vector;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.ChannelSftp.LsEntry;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import java.io.IOException;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.List;
import net.schmizz.sshj.SSHClient;
import net.schmizz.sshj.common.Base64;
import net.schmizz.sshj.common.IOUtils;
import net.schmizz.sshj.sftp.SFTPClient;
import net.schmizz.sshj.transport.verification.PromiscuousVerifier;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

/**
 *
 * @author Electro
 */
public class DownloadFile {

   
  @SuppressWarnings("unchecked")
  
private String remoteHost = "192.168.62.251";
private String username = "pi";
private String password = "raspberry";
static String userprofile;

public static final int DEFAULT_BUFFER_SIZE = 8192;

MainFrame mf = new MainFrame();

public void DownloadFile(){
    
}   

    
   
    
    public String[] Load(){
        String SFTPHOST = "192.168.62.251";
        int    SFTPPORT = 22;
        String SFTPUSER = "pi";
        String SFTPPASS = "raspberry";
        String SFTPWORKINGDIR = "/home/pi/doora_upgrade";
        String SFTPPRIVATEKEY = "/doora_upgrade";
               
        Session     session     = null;
        Channel     channel     = null;
        ChannelSftp channelSftp = null;
        
        LsEntry entry = null;
        String[] lf = new String[3];
        
        try{
            JSch jsch = new JSch();
            File privateKey = new File(SFTPPRIVATEKEY);
            if(privateKey.exists() && privateKey.isFile())
                jsch.addIdentity(SFTPPRIVATEKEY);
            session = jsch.getSession(SFTPUSER,SFTPHOST,SFTPPORT);
            session.setPassword(SFTPPASS);
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.connect();
            channel = session.openChannel("sftp");
            channel.connect();
            channelSftp = (ChannelSftp)channel;
            channelSftp.cd(SFTPWORKINGDIR);
            Vector filelist = channelSftp.ls(SFTPWORKINGDIR);
            for(int i=0; i<filelist.size();i++){
                entry = (LsEntry)filelist.get(i);
                lf[i] = entry.getFilename();
                //System.out.println(entry.getFilename());
            }
        }catch(Exception ex){
            ex.printStackTrace();
        } finally {
            if(session != null) session.disconnect();
            if(channel != null) channel.disconnect();
        }
        
        return lf;
    }
    
  public boolean DownLoad(){
        String SFTPHOST = "192.168.62.251";
        int    SFTPPORT = 22;
        String SFTPUSER = "pi";
        String SFTPPASS = "raspberry";
        String SFTPWORKINGDIR = "/home/pi/doora_upgrade";
        String SFTPPRIVATEKEY = "/doora_upgrade";
               
        Session     session     = null;
        Channel     channel     = null;
        ChannelSftp channelSftp = null;
        boolean successo = true;
        
        userprofile = System.getenv("USERPROFILE");     
        
        LsEntry entry = null;
        String[] lf = new String[3];
        System.out.println(userprofile);
        String path = userprofile + "\\Desktop\\doora.bin";
        
        try{
            JSch jsch = new JSch();
            File privateKey = new File(SFTPPRIVATEKEY);
            if(privateKey.exists() && privateKey.isFile())
                jsch.addIdentity(SFTPPRIVATEKEY);
            session = jsch.getSession(SFTPUSER,SFTPHOST,SFTPPORT);
            session.setPassword(SFTPPASS);
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.connect();
            channel = session.openChannel("sftp");
            channel.connect();
            channelSftp = (ChannelSftp)channel;
            channelSftp.cd(SFTPWORKINGDIR);
            System.out.println("Starting File download");            
            InputStream is = channelSftp.get("/home/pi/doora_upgrade/DOORA1_UmtsMono_v00.03.04_868MHz.bin");
            File file = new File(path);
            copyInputStreamToFile(is, file);
            is.close();
        }catch(Exception ex){
            successo = false;
            ex.printStackTrace();
        } finally {
            if(session != null) session.disconnect();
            if(channel != null) channel.disconnect();
        }
        
        return successo;
    }
     
    private static void copyInputStreamToFile(InputStream inputStream, File file)
            throws IOException {

        // append = false
        try (FileOutputStream outputStream = new FileOutputStream(file, false)) {
            int read;
            byte[] bytes = new byte[DEFAULT_BUFFER_SIZE];
            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }            
        }       
    }
  }

