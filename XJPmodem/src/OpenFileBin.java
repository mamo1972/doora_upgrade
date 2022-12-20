


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 *
 * @author Electro
 */
import java.io.File;
import java.io.FileReader;  
import java.io.*; 
/**
 *
 * @author Electro
 */
public class OpenFileBin {

private static char SUB = 0x1A; 


  static final int BLOCK = 128;
  public OpenFileBin(){      
  }   

  public byte[][] OpenFile(String path){
     //   char[] in;
        byte[][] block = null;
        boolean keep = true;
        int n = 0;
        int r = 0;   
        int x = 0;
        int row = 0;
        try{
            File file = new File(path);
            long bytes_ = file.length();           
            InputStream in = new FileInputStream(file);
            byte[] bytes = new byte[(int) file.length()];
            in.read(bytes);
 
     //       String content = new String(bytes, charset);
     //       for(int z = 0; z < bytes.length; z++){
     //         System.out.println(String.format("%02x",bytes[z]));
     //       }
       
      /*      in = new char[(int)bytes];
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            br.read(in);
            System.out.println(bytes);    */
            while(keep){
              bytes_ -= BLOCK;          
              if(bytes_ < BLOCK){
                r = (int)bytes_;
                keep = false;                
              }
              n++;
            }
          //  if(r != 0)
              block = new byte[n][BLOCK];
    //        else
   //           block = new char[n+1][BLOCK];  
   //         row = block.length;       
            for(int i = 0; i < n; i++){
               for(int j = 0; j < BLOCK; j++){                
                   block[i][j] = bytes[(BLOCK*i)+j];
                  // System.out.print(block[i][j]);
               } 
            }     
           // System.out.print(r);
    /*        x = BLOCK - r;
            System.out.println(x);
            for(int j = 0; j < x; j++){    
                System.out.print("-"); 
               block[BLOCK][j] = bytes[BLOCK*(n-1)+j];
            }
            if(x != BLOCK){
              for(int i = x; i < BLOCK; i++){      
                   System.out.print(".");
                    block[row-2][i] = (byte)SUB;               
              }
            }         */
       /*     else{
              for(int i = 0; i < BLOCK + 5; i++){      
                   System.out.print(".");
                    block[row-1][i] = SUB;               
              }                
            }          */
     //       for(int i = (int)bytes - r; i <= (int)bytes; i++){
     //          block[n-1][i] = in[i];             
     //       }
 /*           for(int q = 0; q < n; q++){
              for(int z = 0; z < BLOCK; z++){
                System.out.print(block[q][z]);
              }
            
            }*/
        }catch(Exception e){
            e.printStackTrace();
        }
        
      
        return block;
        
 }
}
