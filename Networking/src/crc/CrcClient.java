package crc;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class CrcClient {
	public static String data,generator,CRC;
	public static void main(String[] args) throws Exception{
		String ip="localhost";
		int port=8000;
		
		System.out.println("Client Started:\n");
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the dataword:");
		data=sc.nextLine();
		System.out.println("Enter the dataword:");
		generator=sc.nextLine();
		CRC=data+crc();
		System.out.println("The CRC:"+CRC);
		System.out.println("Dataword to be sent");
		CRC=sc.nextLine();
		Socket client=new Socket(ip,port);
		
		OutputStreamWriter os=new OutputStreamWriter(client.getOutputStream());
		PrintWriter out= new PrintWriter(os);
		out.println(generator+":"+CRC+"\\");
		out.flush();
		client.close();
		
		
	}
	
	
	
	public static String crc() {
		int pointer=generator.length();
		
		for(int i=0; i<generator.length()-1;i++)
			data+="0";
		
		String result=data.substring(0,pointer);
		String remainder="";
		for(int i=0;i<pointer;i++)
			if(result.charAt(i)==generator.charAt(i))
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
				for(int i=0;i<generator.length();i++)
					
					if(result.charAt(i)==generator.charAt(i))
						remainder+="0";
					else
						remainder+="1";
			}
		}
			
		
		return remainder.substring(1,remainder.length());
	}

}
