/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package udp;

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author M1K
 */
public class MulticastServerThread {

    public static void main(String[] args){
        try {
            InetAddress group = InetAddress.getByName("239.255.255.250");
            DatagramSocket serverSocket = new DatagramSocket(6789);
            boolean attivo = true;
            byte[] bufferIN = new byte[1024];
            byte[] bufferOUT = new byte[1024];
            
            System.out.println("SERVER avviato...");
            while(attivo)
            {
                DatagramPacket receivePacket = new DatagramPacket(bufferIN,bufferIN.length);
                try {
                    serverSocket.receive(receivePacket);
                } catch (IOException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }
                String ricevuto = new String(receivePacket.getData());
                int numCaratteri = receivePacket.getLength();
                ricevuto=ricevuto.substring(0,numCaratteri);
                System.out.println("RICEVUTO: "+ricevuto);
                
                InetAddress IPClient = receivePacket.getAddress();
                int portaClient = receivePacket.getPort();
                
                String daSpedire = ricevuto.toUpperCase();
                bufferOUT = daSpedire.getBytes();
                
                DatagramPacket sendPacket = new DatagramPacket(bufferOUT, bufferOUT.length, group, 1900);
                try {
                    serverSocket.send(sendPacket);
                } catch (IOException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                if(ricevuto.equals("fine"))
                {
                    System.out.println("SERVER IN CHIUSURA. Buona serata.");
                    attivo=false;
                }
            }
            serverSocket.close();
        } catch (SocketException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(MulticastServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}