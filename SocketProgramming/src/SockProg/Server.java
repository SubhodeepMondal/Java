package SockProg;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) throws Exception {
		
		System.out.println("Server: Server Started");
		ServerSocket server = new ServerSocket(5000);
		System.out.println("Server: Server is waiting for client request");
		Socket s = server.accept();
		
		System.out.println("Server: Client Connected");
		
		InputStreamReader in = new InputStreamReader(s.getInputStream());
		BufferedReader br = new BufferedReader(in);
		
		String data = br.readLine();
		
		System.out.println("Data : " + data);
		
		
		String ack = "Gotcha";
		
		OutputStreamWriter out = new OutputStreamWriter(s.getOutputStream());
		PrintWriter pw = new PrintWriter(out);
		pw.println("Server: " + ack); 
		pw.flush();
		s.close();
	}
}
