package bank.util;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class QQServer  {
	private DataOutputStream dos;
	private ServerSocket server;
	private Socket socket;

	
	
	
	public Socket getSocket() throws IOException {
		return new ServerSocket(8888).accept();
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public void start() throws IOException {
		
		 server = new ServerSocket(8888);
		System.out.println("服务器启动完成，监听端口：8888");

		boolean running = true;

		while (running) {
			// 当前线程进入阻塞状态
			 socket = server.accept();
			// 创建线程来处理业务
			new Thread() {
				public void run() {

					// 获取网络对象
					InetAddress addr = socket.getInetAddress();
					System.out.println("客户端的主机地址：" + addr.getHostAddress());
					System.out.println("客户端的ip地址：" + Arrays.toString(addr.getAddress()));

					try {
						InputStream in = socket.getInputStream();
						OutputStream out = socket.getOutputStream();
						dos = new DataOutputStream(out);
						boolean running = true;
						while (running) {
							DataInputStream dis = new DataInputStream(in);
							
						String msg =	dis.readUTF();
						dos.writeUTF(msg);
						}

					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}.start();

		}
	}

	public ServerSocket getServer() throws IOException {
		return new ServerSocket(8888);
	}

	public void setServer(ServerSocket server) {
		this.server = server;
	}

}
