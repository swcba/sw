package bank;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Scanner;

public class Client {
		public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException {
	
	
	
		
	new Client().start();
	
	
	
		}
	private DataOutputStream dos;
	public void start() throws UnknownHostException, IOException {
		Scanner sc = new Scanner(System.in);
		
		
		Socket server = new Socket("127.0.0.1",8888);
		System.out.println("成功连接服务器");
		InetAddress addr = server.getInetAddress();
		System.out.println("服务器端的主机地址："+ addr.getHostAddress());
		System.out.println("服务器的ip地址："+Arrays.toString(addr.getAddress()));
		
		
		InputStream in = server.getInputStream();
		OutputStream out = server.getOutputStream();
		
		DataInputStream dis = new DataInputStream(in);
		dos = new DataOutputStream(out);
		
		boolean running = true;
		while(running) {
			System.out.println("*****************");
			System.out.println("*   1.开户                  *");
			System.out.println("*   2.存款                  *");
			System.out.println("*   3.取款                  *");
			System.out.println("*   4.转账                  *");
			System.out.println("*****************");
			System.out.println("请输入：");
			int num = sc.nextInt();
			switch (num) {
			case 1:
				register();
				System.out.println(dis.readUTF());
				break;
			case 2:
				diposit();
				System.out.println(dis.readUTF());
				break;
			case 3:
				withdraw();
				System.out.println(dis.readUTF());
				break;
			case 4:
				transfer();
				System.out.println(dis.readUTF());
				break;

			default:
				break;
			}
		}
		
	}
	//开户
		public void register() throws IOException {
			dos.writeUTF("register");
			
			dos.writeUTF("0123456789");
			
			dos.writeFloat(20000);
			
			dos.flush();
			
		}
		//存款
		public void diposit() throws IOException {
			dos.writeUTF("diposit");
			
			dos.writeUTF("1234567890");
			
			dos.writeFloat(1000);
			
			dos.flush();

		}
		//取款
		public void withdraw() throws IOException {
			dos.writeUTF("withdraw");
			
			dos.writeUTF("1234567890");
			
			dos.writeFloat(1000);
			
			dos.flush();
		}
		//转账
		public void transfer() throws IOException {
			dos.writeUTF("transfer");
			dos.writeUTF("123456789");
			dos.writeUTF("1234567890");
			
			dos.writeFloat(1000);
			
			dos.flush();
		}
		
}
