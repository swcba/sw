package bank;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

import bank.util.BankDao;

public class Server {
	
	public static void main(String[] args) throws IOException {
		new Server().start();
	}
	private DataOutputStream dos;
	private BankDao dao = new BankDao();
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
					dos  = new DataOutputStream(out);
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
							switch (command) {
							case "register":
								register(dis.readUTF(),dis.readFloat());
								break;
							case "diposit":
								diposit(dis.readUTF(), dis.readFloat());					
								break;
							case "withdraw":
								withdraw(dis.readUTF(), dis.readFloat());		
								break;
							case "transfer":
								transfer(dis.readUTF(),dis.readUTF(), dis.readFloat());
								break;

							default:
								break;
							}
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
	//开户
	public void register(String cardno,float money) throws IOException {
		System.out.println(cardno+"||"+money);
		dao.insert(cardno,money);
		
		dos.writeUTF("开户成功");
		
		dos.flush();
	}
	//存款
	public void diposit(String cardno,float money) throws IOException {
		System.out.println(cardno + " || "+money);
		dao.update1(cardno, money);
		dos.writeUTF("存款成功");
		dos.flush();
	}
	//取款
	public void withdraw(String cardno,float money) throws IOException {
		System.out.println(cardno + " || "+money);
		String msg=dao.update2(cardno, money);
		dos.writeUTF(msg);
		dos.flush();
	}
	//转账
	public void transfer(String cardno1,String cardno2,float money) {
		
	}
}
