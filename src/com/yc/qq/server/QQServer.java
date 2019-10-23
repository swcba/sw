package com.yc.qq.server;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.yc.qq.client.QQClient;
import com.yc.qq.ui.Users;

import bank.Server;

public class QQServer {
	public static void main(String[] args) throws IOException {
		new Server().start();
	}
	
	public  void start() throws IOException {
		
		ServerSocket server=new ServerSocket(8888);
		System.out.println("服务器启动完成，监听端口：8888");
		
		boolean running= true;
		
		while(running ) {
			//当前线程进入阻塞状态
		Socket client = server.accept();
		//创建线程来处理业务
		new Thread () {
			public void run() {

				//获取网络对象
				InetAddress addr = client.getInetAddress();
				System.out.println("客户端的主机地址："+addr.getHostAddress());
				System.out.println("客户端的ip地址："+Arrays.toString(addr.getAddress()));
				
				try {
					InputStream in = client.getInputStream();
					OutputStream out = client.getOutputStream();
					boolean running=true;
					while(running) {
						/**
						 * 业务约定==》协议
						 * 如果客户端发送一个命令：disposit，接受命令所需要的参数
						 * 
						 */
						DataInputStream dis = new DataInputStream(in);
						
						try {
							String command= dis.readUTF();
							System.out.println(command);
							String cardno = dis.readUTF();
							System.out.println(cardno);
							float money = dis.readFloat();
							System.out.println(money);
						} catch (EOFException e) {
							// TODO Auto-generated catch block
							break;
						}
					}
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
		}.start();
		}
		
	}

}
