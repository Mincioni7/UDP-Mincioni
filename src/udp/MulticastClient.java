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
public class MulticastClient {
    
    public static void main(String args[]) throws SocketException, IOException
    {
        //1 porta
        MulticastSocket socket = new MulticastSocket(1900);
        
        //2 ip classe D
        InetAddress address = InetAddress.getByName("239.255.255.250");
        
        //3 unione
        socket.joinGroup(address);
        
        
        try {
            int portaServer = 6789;
            InetAddress IPServer = InetAddress.getByName("localhost");
            
            byte[] bufferOUT = new byte[1024];
            byte[] bufferIN = new byte[1024];
            BufferedReader input = new BufferedReader(new InputStreamReader (System.in));
            
            DatagramSocket clientSocket;
            clientSocket = new DatagramSocket();
        
            System.out.println("Client pronto - inserisci un dato da inviare:");
            
            String daSpedire = input.readLine();
            bufferOUT = daSpedire.getBytes();
            
            DatagramPacket sendPacket = new DatagramPacket(bufferOUT, bufferOUT.length, IPServer, portaServer);
            clientSocket.send(sendPacket);
            
            DatagramPacket receivePacket = new DatagramPacket(bufferIN, bufferIN.length);
            clientSocket.receive(receivePacket);
            String ricevuto = new String(receivePacket.getData());
            
            int numCaratteri = receivePacket.getLength();
            ricevuto=ricevuto.substring(0,numCaratteri);
            System.out.println("dal SERVER:" + ricevuto);
            
            clientSocket.close();
        } catch (UnknownHostException ex) {
            Logger.getLogger(MulticastClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}