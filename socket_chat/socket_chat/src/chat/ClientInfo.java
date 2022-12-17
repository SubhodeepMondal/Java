package chat;

import java.net.InetAddress;
/**
 * Stores user information(UserId,Address,port)
 * and retrieve using given method
 * @author Subhodeep
 *
 */

public class ClientInfo {
	public  int port;
	public  InetAddress address;
	public  String name;
	public ClientInfo(String name,InetAddress address,int port) {
		this.name=name;
		this.address=address;
		this.port=port;
	}
	public  InetAddress getAddress() {
		return address;
	}
	public  String getName() {
		return name;
	}
	public  int getPort() {
		return port;
	}

}
