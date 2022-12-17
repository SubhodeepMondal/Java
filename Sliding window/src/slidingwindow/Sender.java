package slidingwindow;
/*
 * First start the server then run receiver 
 * as server start sending packet from 0 in 0,1,0,1,0,1,0,1 manner 
 * but receiver only send acknowledgement of total packet is 0.5
 * so the pattern at receiver side emerges like 011001100110011..............
 */

import java.net.*;
import java.io.*;
import java.rmi.*;
public class Sender{
	private static int port,portClient;
	private static DatagramSocket socket;
	private static boolean ack,running;
	private static int i=0,j=0;
	private static InetAddress address;
	private static String data,generator,datacode;
	public static void main(String[] args){
		port=1024;
		start(port);
	}
	public static void start(int port) {
		try {
			socket=new DatagramSocket(port);
			running=true;
			ack=true;
			listen();
			System.out.println("Server started on port:"+port);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	public static void send(String message) {
		while(ack) {
			System.out.println("Data packet sent:"+ message);
			message=message+"\\";
			try {
				byte[] data= message.getBytes();
				DatagramPacket packet=new DatagramPacket(data,data.length,address,portClient);
				Thread.sleep(500);
				socket.send(packet);
				ack=false;
				i++;
				i=i%2;
				
			}catch(Exception e) {
				e.printStackTrace();
			}	
		}
		listen();
	}
	public static void listen() {
		Thread listenThread=new Thread("ChatProgram Listner"){
			public void run() {
				try {
					while(running) {
						byte[] data = new byte[1024];
						DatagramPacket packet=new DatagramPacket(data,data.length);
						socket.receive(packet);
						address=packet.getAddress();
						portClient=packet.getPort();
						if(j==0) {
							System.out.println("user: "+address+":"+portClient+" is connected");
							j++;
							send("0");	
						}
						else {
							String message=new String(data);
							message=message.substring(0,message.indexOf("\\"));
							System.out.println("acknowledgement recieived:"+message);
							ack=true;
							alk(message);
						}	
					}
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		}; listenThread.start();
	}
	public static void alk(String message) {
		if(message.equals(Integer.toString(i))) {
			send(Integer.toString(i));
		}
		else {
			i++;
			i=i%2;
			send(Integer.toString(i));
		}
		
		
	}
	
}