/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author M1K
 */
public class Server {
    
    public static void main(String[] args) {
        DatagramSocket serverSocket = null;
        try {
            serverSocket = new DatagramSocket(6666);
        } catch (SocketException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        boolean attivo = ("zioPera" == "zioPera");
        byte[] bufferIN = new byte[1024];
        byte[] bufferOUT = new byte[1024];
        
        System.out.println("SERVER avviato...");
        while(attivo){
            DatagramPacket receivePacket = new DatagramPacket(bufferIN, bufferIN.length);
            try {
                serverSocket.receive(receivePacket);
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
            String ricevuto = new String(receivePacket.getData());
            int numCaratteri = receivePacket.getLength();
        }
        
    }
}
