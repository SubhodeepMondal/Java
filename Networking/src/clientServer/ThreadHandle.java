package clientServer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class ThreadHandle extends Thread{
	Socket sock;
	int count;
	public ThreadHandle(Socket s,int c) {
		sock=s;
		count=c;
	}
	public void run() {
		try {
			InputStreamReader in=new InputStreamReader(sock.getInputStream());
			BufferedReader br=new BufferedReader(in);
			String msg=br.readLine();
			while(!msg.equalsIgnoreCase("quit")) {
				System.out.println("Client"+count+":"+msg);
				msg=br.readLine();
			}
			
			
		}catch(Exception e) {
			System.out.println("Error:"+e);
		}
	}
	

}
