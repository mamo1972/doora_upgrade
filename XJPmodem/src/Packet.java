/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Electro
 */
public class Packet {
       private int righe;
    private int colonne;
    private byte [][]tmp; 
    private String stmp;
    private int total;
    byte crc = 0;
    private static byte SOH = 0x01;
    public static final byte CRC8ATM_POLY = 0x07;
    
    public Packet(int righe,int colonne, byte[][]tmp){  
        this.righe = righe;
        this.colonne = colonne;
        this.tmp = tmp;             
    }
    
    public byte[][] createPacket(){     
       byte[][]jtmp = new byte[righe][colonne+5];
       int crc = 0xFFFF;          // initial value       
       int polynomial = 0x1021;   // 0001 0000 0010 0001  (0, 5, 12)
       for(int i= 0; i < righe; i++){
          crc = 0; 
          for(int j = 0; j < colonne; j++){  
                crc ^=(int)tmp[i][j] << 8;
                for (int z = 0; z < 8; z++) {
                //  boolean bit = ((tmp[i][j]   >> (7-z) & 1) == 1);
                //  boolean c15 = ((crc >> 15    & 1) == 1);
                //  crc <<= 1;
                //  if (c15 ^ bit) crc ^= polynomial;
                if((crc & 0x8000)>0) {
                      crc = crc << 1 ^ polynomial;
                }
                else {
                   crc = crc << 1;  
                }
            }
            

           //  crc = (byte) (crc ^ (byte)tmp[i][j]);
           //  crc = (byte) (crc << 1 ^ ((crc & 0x80) != 0 ? CRC8ATM_POLY : 0));                                
          } 
        //  crc &= 0xffff;
     //     System.out.println("CRC16-CCITT = " + Integer.toHexString(crc));
          jtmp[i][0] = SOH;
          if(i == 0){
             jtmp[i][1] = 1; 
             jtmp[i][2] = (byte)254; 
          }  
          else{
             jtmp[i][1] = (byte)((i%256)+1);
             jtmp[i][2] = (byte)(255-((i%256)+1));
          }
          for(int p = 3; p < 131; p++){
              jtmp[i][p] = (byte)tmp[i][p-3];
          }
          int tot = crc; //crc & 0xff00;
          char b = (char)(crc >> 8);
          jtmp[i][131] = (byte)b;
          tot = crc & 0x00ff;
          b = (char)(tot);
          jtmp[i][132] =(byte) b;
         // System.out.print((char)total + "\n");     
          //  stmp = SOH+(char)(i%256)+(char)(255-(i%256))+String.copyValueOf(tmp[i])+(char)total;         
          //stmp = (char)'1'+Integer.toString(i%256)+Integer.toString(255-(i%256))+String.copyValueOf(tmp[i])+(char)total;
          //System.out.print(stmp + "\r\n");
          //jtmp[i] = stmp.toCharArray();          
       }   
        
       return jtmp;   
    }
}
