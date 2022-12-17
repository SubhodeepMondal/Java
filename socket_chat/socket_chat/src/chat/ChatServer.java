package chat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;



public class ChatServer {
	
	private static Map logedinUsers = new HashMap();//format is [userId,ip:port]
	private static int port=5281;
	private static DatagramSocket socket;
	private static boolean running;
	private static int id;
	private static ArrayList<ClientInfo> clients=new ArrayList<ClientInfo>();
	
	public static void main(String args[]){
		try {
			socket=new DatagramSocket(port);
			running=true;
			listen();
			System.out.println("Server started on port: "+port);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	
	public static void broadcast(String message) {
		/**
		 * Broadcast message to every logged user
		 */
		for(ClientInfo info : clients) {
			send(message,info.getAddress(),info.getPort());
		}
		
		
	}
	
	public static void send(String message,InetAddress address,int port) {
		/** 
		 * Send message to the a particular user
		 */
		try {
			message+="\\e";
			byte[] data=message.getBytes();
			DatagramPacket packet=new DatagramPacket(data,data.length,address,port);
			socket.send(packet);	
			System.out.println("Send message to:"+address.getHostAddress()+":"+port);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void listen() {
		Thread listenThread=new Thread("Chat Listner") {
			public void run() {
				
				try {
					while(running) {
						byte[] data=new byte[1024];
						DatagramPacket packet=new DatagramPacket(data,data.length);
						socket.receive(packet);
						String message=new String(data);
						System.out.println(message);
						handleCommand(message,packet);
					}
				}catch(Exception e) {
					e.printStackTrace();
				}
				
			}
		};
		listenThread.start();
	}
	
	private static void handleCommand(String message, DatagramPacket packet) {
        
        if(message.startsWith("[login:")){
        	//cmd structure is [login:User_id//e
        	/*handle login command
        	 * 1. send logedinUsers object to the client
        	 * 2. forward the command to all the  loggedinUsers, 
        	 * 3. add the new user to the logedinUsers map
        	 */
        	String name=message.substring(message.indexOf(":")+1,message.indexOf("\\e"));
        	
        	clients.add(new ClientInfo(name,packet.getAddress(),packet.getPort()));
        	broadcast(name+" is connected.");
        	connectedUsers(name,packet);
        	
        }else if(message.startsWith("[logout:")){
        	//cmd structure is [logout:User_id//e
        	/*handle logout command
        	 * 1. Remove the user from logedinUsers
        	 * 2. forward the command to all the  loggedinUsers, 
        	 */
        	int count=0;
        	String userDel=message.substring(message.indexOf(":")+1,message.indexOf("\\e"));
        	for(ClientInfo info: clients) {
        		if(info.getName().equals(userDel)){
        			clients.remove(count);
        			broadcast(userDel+" logged out.");
        		}
        		count++;
        	}
        	
        	
        }else if(!message.startsWith("[")) {
        	InetAddress address;
        	int port;
        	String sender=message.substring(0,message.indexOf("|"));
        	String reciver=message.substring(message.indexOf("|")+1,message.indexOf(":"));
        	message=message.substring(message.indexOf(":")+1,message.indexOf("\\e"));
        	message="["+sender+"]: "+message;
        	for(ClientInfo info: clients) {
        		if(info.getName().equals(reciver)) {
        			send(message,info.getAddress(),info.getPort());
        		}
        	}
        }
	}
	public static void connectedUsers(String name,DatagramPacket packet) {
		/**
		 * Send connected users info to recently connected user;
		 */
		String message;
		for(ClientInfo info: clients) {
			if(!info.getName().equals(name)) {
				message=info.getName()+" is Connected";
				send(message,packet.getAddress(),packet.getPort());
			}
		}
	}
}
