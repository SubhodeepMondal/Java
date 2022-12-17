package Iterative;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
	public static void main(String args[]) throws Exception {
		//Wait for new connection
		ServerSocket welcomeSocket = new ServerSocket(2000);
		System.out.println("Server is listening...");
		Socket connectionSocket = welcomeSocket.accept();	
		System.out.println("Connected... :)");
		
		//Exchange hello message
		BufferedReader br = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
		PrintWriter pw = new   	PrintWriter(connectionSocket.getOutputStream());
		pw.println("Hello Client...");
		pw.flush();		
		String helloMsg = br.readLine();
		System.out.println("client says: " + helloMsg);
		
		//Exchange echo message
		String msg=br.readLine();
		System.out.println("client says: " + msg);
		pw.println(msg.toUpperCase());
		pw.flush();	

	}
}
