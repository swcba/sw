package com.yc.qq.ui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;



import java.io.IOException;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class User1 {

	protected Shell shell;
	private Text text;
	private Text text_1;
	private Socket socket;
	private ServerSocket server;
	private Talker talker;
	
	List<String> messages =new ArrayList<>();
	Users user = new Users("张三");
	private Text text_2;
	
	private Label lblNewLabel;
	private Button button;
	private Label label;
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			User1 window = new User1();
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
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
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
		
		
		
		shell = new Shell();
		shell.setSize(437, 409);
		shell.setText("QQ聊天室\r\n");
		
		text =  new Text(shell, SWT.BORDER | SWT.MULTI);
		text.setEnabled(false);
		text.setBounds(0, 52, 424, 187);
		
		
		
		
		
		text_2 = new Text(shell, SWT.BORDER);
		text_2.setText("cba");
		text_2.setBounds(61, 7, 91, 23);
		
		lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBounds(158, 10, 303, 20);
		lblNewLabel.setText("好友IP：请再启动一次程序作为客户端");

		label = new Label(shell, SWT.NONE);
		label.setText("昵称：");
		label.setBounds(10, 13, 51, 20);

		
		
		text_1 = new Text(shell, SWT.BORDER);
		text_1.setEnabled(false);
		text_1.setBounds(10, 259, 292, 26);

		button = new Button(shell, SWT.CENTER);
		button.setText("发送");
		button.setBounds(321, 257, 80, 27);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
			
				try {
					if (text_1.getText().trim().isEmpty() == false) {
						String msg = talker.send(text_2.getText(), text_1.getText());
						text.setText(text.getText() + msg + "\r\n");
						text_1.setText("");
						// 设置焦点
						text_1.setFocus();
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
					}
					
			
		});
		begin();
		
	}

	public void begin() {
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
					shell.getDisplay().asyncExec(new Runnable() {
						@Override
						public void run() {
							MessageBox mb = new MessageBox(shell,SWT.OK);
							mb.setText("系统提示");
							mb.setMessage("连接成功！现在你可以开始聊天了！");
							mb.open();
						}
					});

					talker = new Talker(socket, new MsgListener() {
						@Override
						public void onMessage(String msg) {
							// 收到消息时，将消息更新到多行文本框
							shell.getDisplay().asyncExec(new Runnable() {
								@Override
								public void run() {
									text.setText(text.getText() + msg + "\r\n");
								}
							});
						}

						@Override
						public void onConnect(InetAddress addr) {
							// 连接成功时，显示对方IP
							shell.getDisplay().asyncExec(new Runnable() {
								@Override
								public void run() {
									lblNewLabel.setText("好友IP：" + addr.getHostAddress());
									text_1.setEnabled(true);
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
}
