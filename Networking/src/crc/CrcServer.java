package crc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class CrcServer {
	public static String data,generator;
	public static void main(String[] args) throws Exception{
		ServerSocket server=new ServerSocket(8000);
		
		System.out.println("Server is waiting for connection\n");
		Socket s=server.accept();
		System.out.println("Client connected:\n");
		InputStreamReader in=new InputStreamReader(s.getInputStream());
		BufferedReader br=new BufferedReader(in);
		String Data=br.readLine();
		if(crc(Data)) {
			System.out.println("Message received correctly");
		}else {
			System.out.println("Message received incorrectly");
		}
		
	}
	public static boolean crc(String message) {
		generator=message.substring(0,message.indexOf(":"));
		data=message.substring(message.indexOf(":")+1,message.indexOf("\\"));
		int pointer=generator.length();
		System.out.println(data+":"+generator);
		String result=data.substring(0,pointer);
		String remainder="";
		for(int i=0;i<pointer-1;i++)
			if(generator.charAt(i)==result.charAt(i))
				remainder+="0";
			else
				remainder+="1";
		while(pointer<data.length()) {
			remainder=remainder.substring(1,remainder.length());
			remainder=remainder+String.valueOf(data.charAt(pointer));
			pointer++;
			if(remainder.charAt(0)=='1') {
				result=remainder;
				remainder="";
				for(int i=0; i<generator.length();i++) 
					
					if(result.charAt(i)==generator.charAt(i))
						remainder+="0";
					else
						remainder+="1";
			}
		}
		
			
					
		if(remainder.substring(1,remainder.length()).contentEquals("000"))
			return true;
		else 
			return false;
	}

}
