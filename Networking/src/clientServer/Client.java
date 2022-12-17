package clientServer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
	public static void main(String[] args) throws Exception{
		String ip="localhost";
		int port=8000;
		Socket s=new Socket(ip,port);
		
		System.out.println("Client:Client Started");
		
		PrintWriter pw=new PrintWriter(s.getOutputStream());
		pw.println("Hallow Server");
		pw.flush();
		BufferedReader br1=new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			System.out.print("Enter a message.(\"quite\"to terminate): ");
			String msg=br1.readLine();
			pw.println(msg);
			pw.flush();
			if(msg.contentEquals("quit"))
				break;
		}
		
		
/*		InputStreamReader in=new InputStreamReader(s.getInputStream());
		BufferedReader br=new BufferedReader(in);
		String input=br.readLine();
		System.out.println("Server Says:"+input); */
		s.close();
		
	}

}
