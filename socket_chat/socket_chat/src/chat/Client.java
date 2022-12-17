package chat;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client {
	private static DatagramSocket socket;
	private static InetAddress address;
	private static int port;
	private static boolean running;
	private static String name;
	public Client(String name,String address,int port) {
		try {
			this.name=name;
			this.address=InetAddress.getByName(address);
			this.port=port;
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void login() {
		/**
		 * Generate logout message by putting [login:UserId] in front of 
		 * the message
		 */
		String message;
		try {
			socket=new DatagramSocket();
			running=true;
			message="[login:"+name;
			send(message);
			listen();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	
	public static void logOut() {
		/**
		 * Generate logout message by putting [logout:UserId] in front of 
		 * the message
		 */
		String message;
		try {
			socket=new DatagramSocket();
			running=false;
			message="[logout:"+name+"\\e";
			send(message);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void listen() {
		/**
		 * listen for incoming data to the client 
		 */
		Thread listenThread=new Thread("Chat Listner") {
			public void run() {
				
				try {
					while(running) {
						byte[] data=new byte[1024];
						DatagramPacket packet=new DatagramPacket(data,data.length);
						socket.receive(packet);
						String message=new String(data);
						message=message.substring(0,message.indexOf("\\e"));
						ChatWindow.printToConsole(message);
					}
				}catch(Exception e) {
					e.printStackTrace();
				}
				
			}
		};
		listenThread.start();
	}
	public static void send(String message) {
		/**
		 * Send the data to server
		 */
		try {
			message=message+"\\e";
			byte[] data=message.getBytes();
			DatagramPacket packet=new DatagramPacket(data,data.length,address,port);
			socket.send(packet);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
