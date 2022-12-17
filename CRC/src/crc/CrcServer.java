package crc;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class CrcServer {
	private static int port,portClient;
	private static DatagramSocket socket;
	private static boolean running;
	private static InetAddress address;
	private static String data,generator,datacode,message;
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the data:");
		data=sc.nextLine();
		System.out.println("Enter generator:");
		generator=sc.nextLine();
		System.out.println("The generated data:"+data+crc());
		System.out.println("Enter the generator and datacode to be send:");
		datacode=sc.nextLine();

		System.out.println("Enter the port no.:");
		port=sc.nextInt();
		start(port);

	}
	/*
	 *  Starting server.........
	 */
	public static void start(int port) {
		try {
			socket=new DatagramSocket(port);
			running=true;
			listen();
			System.out.println("Server started on port:"+port);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	/*
	 * send data code and generator byte.....
	 */
	public static void send(String message) {
		try {
			
			byte[] data= message.getBytes();
			DatagramPacket packet=new DatagramPacket(data,data.length,address,portClient);
			socket.send(packet);
			System.out.println("Message send to:"+address.getHostAddress()+":"+port);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	/*
	 * listening for connection feed......
	 */
	private static void listen() {
		Thread listenThread=new Thread("ChatProgram Listner"){
			public void run() {
				try {
					while(running) {
						byte[] data = new byte[1024];
						DatagramPacket packet=new DatagramPacket(data,data.length);
						socket.receive(packet);
						address=packet.getAddress();
						portClient=packet.getPort();
						System.out.println("user: "+address+":"+portClient+" is connected");
						message=generator+":"+datacode+"\\";
						System.out.println(message);
						send(message);
					}
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		}; listenThread.start();
	}
/*
 * Method to convert input data into crc data code.........
 */
	public static String crc() {
		
		int pointer=generator.length();
		
		for(int i=0;i<generator.length()-1;i++)
			data+="0";
		String result=data.substring(0,pointer);
		
		String remainder="";
		
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
			
		
		return remainder.substring(1,remainder.length());
	}

}
