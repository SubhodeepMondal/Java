package crc;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class CrcClient {

	private static int port,portClient;
	private static DatagramSocket socket;
	private static boolean running;
	private static int id;
	private static InetAddress address;
	private static String data,generator,datacode;

	public static void main(String[] args) throws UnknownHostException {
		// TODO Auto-generated method stub
		try {
			address=InetAddress.getByName("localhost");
			port=1234;

			socket=new DatagramSocket(6523);
			running=true;
			send("");
			listen();
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		

	}
	/*
	 * Sending connection request feed.........
	 */
	public static void send(String message) {
		try {
			byte[] data= message.getBytes();
			DatagramPacket packet=new DatagramPacket(data,data.length,address,port);
			socket.send(packet);;
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * listening for incoming feed.....
	 */
	
	private static void listen() {
		Thread listenThread=new Thread("ChatProgram Listner"){
			public void run() {
				try {
					while(running) {
						byte[] data = new byte[1024];
						DatagramPacket packet=new DatagramPacket(data,data.length);
						socket.receive(packet);
						String message=new String(data);
						if(crc(message))
							System.out.println("Message received correctly");
						else {
							System.out.println("Message contains error");
						}
						
					}
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		}; listenThread.start();
	}
	/*
	 * client side crc function to check incoming data code
	 * Returns true if data code is correctly received
	 * Returns false if data code is not correctly received
	 */
	public static boolean crc(String message) {
		generator=message.substring(0,message.indexOf(":"));
		data=message.substring(message.indexOf(":")+1,message.indexOf("\\"));
		
		
		int pointer=generator.length();
		
		String result=data.substring(0,pointer);
		String remainder="";
		System.out.println(pointer+":"+data.length());
		for(int i=0;i<pointer;i++)
			if(result.charAt(i)==generator.charAt(i))
				remainder+="0";
			else
				remainder+="1";
		
		while(pointer<data.length()) {
			
			remainder=remainder.substring(1,remainder.length());
			remainder=remainder+String.valueOf(data.charAt(pointer));
			pointer++;
			if(remainder.charAt(0)=='1') {
				result=remainder;
				remainder="";
				for(int i=0; i<generator.length(); i++)
						
					if(result.charAt(i)==generator.charAt(i))
						remainder+="0";
					else
						remainder+="1";
				}
		}
					
		if(remainder.substring(1,remainder.length()).contentEquals("000"))
			return true;
		else 
			return false;
	}

}
