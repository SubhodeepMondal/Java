package clientServer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) throws Exception{
		int inreq=1;
		ServerSocket server=new ServerSocket(8000);
		
		while(true) {
			Socket s=server.accept();
			System.out.println("Creating thread for new client:"+inreq);
			ThreadHandle t=new ThreadHandle(s,inreq);
			t.start();
			inreq++;
		}
				
		
	}

}
