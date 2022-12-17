package SockProg;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
	
	public static void main(String args[]) throws Exception {
		String ip = "localhost";
		int port = 5000;
		String data = "Rahul Dutta"; 
		
		Socket client = new Socket(ip, port);//Client socket started
		
		System.out.println("Client: Client Started");
		
		OutputStreamWriter os =  new OutputStreamWriter(client.getOutputStream());
		PrintWriter out = new PrintWriter(os);
		out.println(data);
		out.flush();

		System.out.println("Client: Client has sent data");		
		
		InputStreamReader in = new InputStreamReader(client.getInputStream());
		BufferedReader br = new BufferedReader(in);
		String ack = br.readLine();
		System.out.println("Server: "+ack);
		os.flush();
		client.close();
	}
	 
}
