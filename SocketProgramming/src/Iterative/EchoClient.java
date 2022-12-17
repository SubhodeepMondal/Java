package Iterative;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class EchoClient {

	public static void main(String[] args) throws Exception {
		//Connect to Server
		Socket socket = new Socket("localhost",2000);
		System.out.println("Got connected...");
		
		//Exchange hello message
		BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String helloMsg=br.readLine();
        System.out.println("server says: " + helloMsg);      
		PrintWriter pw = new PrintWriter(socket.getOutputStream());		
		pw.println("Hello Server");
		pw.flush();
		
		//Exchange echo message
		BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Enter the message: ");
		String msg=br1.readLine();
		pw.println(msg);
        pw.flush();
        String msg1 = br.readLine();
        System.out.println("From Server: "+msg1);
	}

}
