
package concurrent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ThreadHandler extends Thread {
	  Socket newsock;
	  int n;
	  public ThreadHandler(Socket s,int v)
	  {
	    newsock=s;
	    n=v;
	  }
	  public void run()
	  {
	    try{
	        BufferedReader br=new BufferedReader(new InputStreamReader(newsock.getInputStream()));
	        PrintWriter pw=new PrintWriter(newsock.getOutputStream());
	        pw.println("Hello client:: enter quit to exit");
	        pw.flush();
	        String msg1=br.readLine();
	        while(!msg1.equalsIgnoreCase("quit")){
	        	System.out.println("client " + n + " says: " + msg1);
	        	msg1=br.readLine();
	        }
	    } catch(IOException e){
	    	System.out.println("IO error"+e);
	    }
	}

}
