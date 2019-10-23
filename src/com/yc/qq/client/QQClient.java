package com.yc.qq.client;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.yc.qq.server.QQServer;
import com.yc.qq.ui.Users;

public class QQClient {
	private Socket server;
	
	public QQClient() {
		
		try {
			server = new Socket("127.0.0.1",8888);
			System.out.println("成功连接服务器");
			InetAddress addr = server.getInetAddress();
			System.out.println("服务器端的主机地址："+ addr.getHostAddress());
			System.out.println("服务器的ip地址："+Arrays.toString(addr.getAddress()));
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void connection() throws IOException, InterruptedException {
		
		//InputStream in = server.getInputStream();
		OutputStream out = server.getOutputStream();
		
		DataOutputStream dop = new DataOutputStream(out);
		
		dop.writeUTF("diposit");
		
		dop.writeUTF("1515555");
		
		dop.writeFloat(1000);
		
		dop.flush();
		
		System.out.println("客户端暂停操作");
		Thread.sleep(20000);
		
		server.close();
		
	}
	
	
	
}
