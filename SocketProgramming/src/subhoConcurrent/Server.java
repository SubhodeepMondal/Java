package subhoConcurrent;

import java.net.ServerSocket;
import java.net.Socket;


public class Server {
	public static void main(String args[]) {
		int nreq=1;
		try {
			ServerSocket sock=new ServerSocket(2000);
			System.out.println("Server is listening.....");
			while(true) {
				Socket newsock=sock.accept();
				System.out.println("Connected! Creating thread to handle this Client:");
				ThreadHandler t=new ThreadHandler(newsock,nreq);
				t.start();
				System.out.println("Running thread for client:"+nreq);
				nreq++;
			}
		}catch(Exception e) {
			System.out.println("IO error"+e);
		}
	}

}
