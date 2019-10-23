package bank.ui;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;





public class ClientThread extends Thread{

	private InputStream in;
	private OutputStream out;
	
	private DataOutputStream dos;

	public ClientThread(Socket socket) throws IOException {
		
		in = socket.getInputStream();
		out = socket.getOutputStream();
		dos = new DataOutputStream(out);
		// 启动线程，开始接受消息
		start();
	}
	public void run() {
		DataInputStream dis = new DataInputStream(in);
		while (true) {
		try {
			switch (dis.readUTF()) {
			case "register":
				register(dis.readUTF(),dis.readFloat());
				System.out.println(dis.readUTF());
				break;
			case "diposit":
				diposit();
				System.out.println(dis.readUTF());
				break;
			case "withdraw":
				withdraw();
				System.out.println(dis.readUTF());
				break;
			case "transfer":
				transfer();
				System.out.println(dis.readUTF());
				break;

			default:
				break;
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		}
	}
	//开户
			public void register(String cradID , float money) throws IOException {
				dos.writeUTF("register");
				
				dos.writeUTF("cradID");
				
				dos.writeFloat(money);
				
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
