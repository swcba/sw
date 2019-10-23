package com.yc.qq.ui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.google.gson.Gson;
import com.yc.qq.client.QQClient;

import bank.util.QQServer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class User2 {

	protected Shell shlUser;
	private Text text;
	private Text text_1;
	private Socket socket;
	private ServerSocket server;
	private Talker talker;
	
	List<String> messages =new ArrayList<>();
	Users user = new Users("张三");
	private Text text_2;
	private Text tMsg;
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			User2 window = new User2();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 * @throws InterruptedException 
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	public void open() throws UnknownHostException, IOException, InterruptedException {
		Display display = Display.getDefault();
		createContents();
		shlUser.open();
		shlUser.layout();
		while (!shlUser.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 * @throws InterruptedException 
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	protected void createContents() throws UnknownHostException, IOException, InterruptedException {
		
		
		
		shlUser = new Shell();
		shlUser.setSize(450, 464);
		shlUser.setText("QQ聊天室\r\n");
		
		text = new Text(shlUser, SWT.BORDER);
		text.setEnabled(false);
		text.setBounds(0, 52, 424, 187);
		
		
		text_1 = new Text(shlUser, SWT.BORDER);		
		text_1.setBounds(23, 263, 274, 23);
		
		Button button = new Button(shlUser, SWT.CENTER);
		button.setText("发送");
		button.setBounds(321, 257, 80, 27);
		
		text_2 = new Text(shlUser, SWT.BORDER);
		text_2.setText("abc");
		text_2.setBounds(61, 7, 91, 23);
		
		Label lblNewLabel = new Label(shlUser, SWT.NONE);
		lblNewLabel.setBounds(0, 10, 51, 17);
		lblNewLabel.setText("昵称：");
		
		Button btnNewButton = new Button(shlUser, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				new Thread() {
					
					
					
				
					public void run() {
						try {
							try {
								// 连接服务器
								socket = new Socket("127.0.0.1", 8888);
							} catch (IOException e) {
								// 如果连不上服务器，说明服务器未启动，则启动服务器
								server = new ServerSocket(8888);
								System.out.println("服务器启动完成，监听端口：8888");
								socket = server.accept();
							}
							
							/**
							 * 注意：所有在自定义线程中修改图形控件属性，
							 * 都必须使用 shell.getDisplay().asyncExec() 方法
							 */
							shlUser.getDisplay().asyncExec(new Runnable() {
								@Override
								public void run() {
									MessageBox mb = new MessageBox(shlUser,SWT.OK);
									mb.setText("系统提示");
									mb.setMessage("连接成功！现在你可以开始聊天了！");
									mb.open();
								}
							});

							talker = new Talker(socket, new MsgListener() {
								
								@Override
								public void onMessage(String msg) {
									// 收到消息时，将消息更新到多行文本框
									shlUser.getDisplay().asyncExec(new Runnable() {
										@Override
										public void run() {
											text.setText(text.getText() + msg + "\r\n");
										}
									});
								}

								@Override
								public void onConnect(InetAddress addr) {
									// 连接成功时，显示对方IP
									shlUser.getDisplay().asyncExec(new Runnable() {
										@Override
										public void run() {
											lblNewLabel.setText("好友IP：" + addr.getHostAddress());
											text.setEnabled(true);
											button.setEnabled(true);
										}
									});
								}
							});
						
						} catch (IOException e) {
							throw new RuntimeException(e);
						}
					}
				}.start();

			}
		});
		
		tMsg = new Text(shlUser, SWT.BORDER);
		tMsg.setEnabled(false);
		tMsg.setBounds(10, 364, 414, 26);

		
		btnNewButton.setBounds(324, 7, 80, 27);
		btnNewButton.setText("连接服务器");
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
			
				try {
					if (tMsg.getText().trim().isEmpty() == false) {
						String msg = talker.send(text_2.getText(), tMsg.getText());
						text.setText(text.getText() + msg + "\r\n");
						tMsg.setText("");
						// 设置焦点
						tMsg.setFocus();
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
					}
					
			
		});
		
	}
}
