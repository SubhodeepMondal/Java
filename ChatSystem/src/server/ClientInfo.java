package server;

import java.net.InetAddress;

public class ClientInfo {
	
	private InetAddress address;
	private int port;
	private int id;
	private String name;
	public ClientInfo(String name,int id,InetAddress address,int port) {
		this.name=name;
		this.port=port;
		this.id=id;
		this.address=address;
	}
	public String getName() {
		return name;
	}
	public int getId() {
		return id;
	}
	public InetAddress getAddress() {
		return address;
	}
	public int getPort() {
		return port;
	}

}
