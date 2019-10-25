package bank.ui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import bank.Client;


import org.eclipse.swt.widgets.Button;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class ClientUI {

	protected Shell shell;
	private ClientThread CT;
	private Socket socket;
	private Map<String, Object> msg;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ClientUI window = new ClientUI();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
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
	 */
	protected void createContents() {

		shell = new Shell();
		shell.setSize(324, 355);
		shell.setText("YC银行客户端");
		shell.setLayout(new GridLayout(4, false));
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);

		Button button = new Button(shell, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				 new register(shell, SWT.NONE,socket).open();
			}
		});
		GridData gd_button = new GridData(SWT.LEFT, SWT.FILL, false, false, 1, 1);
		gd_button.widthHint = 100;
		button.setLayoutData(gd_button);
		button.setText("开户");
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);

		Button btnNewButton = new Button(shell, SWT.NONE);
		GridData gd_btnNewButton = new GridData(SWT.LEFT, SWT.FILL, false, false, 1, 1);
		gd_btnNewButton.widthHint = 99;
		btnNewButton.setLayoutData(gd_btnNewButton);
		btnNewButton.setText("存款");
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);

		Button btnNewButton_1 = new Button(shell, SWT.NONE);
		GridData gd_btnNewButton_1 = new GridData(SWT.CENTER, SWT.FILL, false, false, 1, 1);
		gd_btnNewButton_1.widthHint = 97;
		btnNewButton_1.setLayoutData(gd_btnNewButton_1);
		btnNewButton_1.setText("取款");
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);

		Button btnNewButton_2 = new Button(shell, SWT.NONE);
		GridData gd_btnNewButton_2 = new GridData(SWT.LEFT, SWT.FILL, false, false, 1, 1);
		gd_btnNewButton_2.widthHint = 100;
		btnNewButton_2.setLayoutData(gd_btnNewButton_2);
		btnNewButton_2.setText("转账");
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);

		Button btnNewButton_3 = new Button(shell, SWT.NONE);
		GridData gd_btnNewButton_3 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnNewButton_3.widthHint = 101;
		btnNewButton_3.setLayoutData(gd_btnNewButton_3);
		btnNewButton_3.setText("退出");

	}

	public void beagn() {

		new Thread() {
			public void run() {

				try {
					socket = new Socket("127.0.0.1", 8888);
					System.out.println("服务器连接成功");
					InetAddress addr = socket.getInetAddress();
					System.out.println("服务器端的主机地址："+ addr.getHostAddress());
					System.out.println("服务器的ip地址："+Arrays.toString(addr.getAddress()));
					
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {

					shell.getDisplay().asyncExec(new Runnable() {
						@Override
						public void run() {
							MessageBox mb = new MessageBox(shell, SWT.OK);
							mb.setText("系统提示");
							mb.setMessage("连接成功！现在你可以开始聊天了！");
							mb.open();
						}
					});
				}
				try {
					CT = new ClientThread(socket);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		};

	}
}
