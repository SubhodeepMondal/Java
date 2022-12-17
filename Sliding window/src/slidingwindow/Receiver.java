package slidingwindow;

import java.net.*;
import java.io.*;
class Receiver
{
	private static int port,portClient;
	private static DatagramSocket socket;
	private static boolean running,ack;
	private static int i=1;
	private static InetAddress address;
	private static String data,generator,datacode;

	public static void main(String[] args){
		// TODO Auto-generated method stub
				try {
					address=InetAddress.getByName("localhost");
					port=1024;
					ack=true;

					socket=new DatagramSocket(6523);
					running=true;
					send(" ");
					listen();
					
					
				}catch(Exception e) {
					e.printStackTrace();
				}
		
	}
	public static void send(String message) {
		try {
			if(!message.equals(" "))
				message=message+"\\";
			byte[] data= message.getBytes();
			DatagramPacket packet=new DatagramPacket(data,data.length,address,port);
			Thread.sleep(500);
			socket.send(packet);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	private static void listen() {
		Thread listenThread=new Thread("ChatProgram Listner"){
			public void run() {
				try {
					while(running) {
						byte[] data = new byte[1024];
						DatagramPacket packet=new DatagramPacket(data,data.length);
						socket.receive(packet);
						String message=new String(data);
						message=message.substring(0,message.indexOf("\\"));
						System.out.println("Packet received:"+message);
						acknowledge(message);
					}
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		}; listenThread.start();
	}
	/*
	 * This acknowledge function responsible to generate 0.5 acknowledgement delivery
	 */
	public static void acknowledge(String message){
		if(ack) {
			ack=false;
			send(Integer.toString(i));
		}
		else {
			ack=true;
			send(Integer.toString(i));

			i++;
			i=i%2;
		}		
		
	}
}