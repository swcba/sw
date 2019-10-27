package bank.ui;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class withdraw extends Dialog {

	protected Object result;
	protected Shell shell;
	private Text text;
	private Text text_1;
	private Socket socket;
	private String msg;
	
	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public withdraw(Shell parent, int style,Socket socket) {
		super(parent, style);
		setText("SWT Dialog");
		this.socket=socket;
		
	
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Socket open() {
		createContents();
		shell.open();
		shell.layout();
		Display display = getParent().getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return socket;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shell = new Shell(getParent(), getStyle());
		shell.setSize(450, 387);
		shell.setText(getText());
		
		text = new Text(shell, SWT.BORDER);
		text.setBounds(181, 40, 172, 23);
		
		text_1 = new Text(shell, SWT.BORDER);
		text_1.setBounds(181, 138, 172, 23);
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBounds(82, 43, 61, 17);
		lblNewLabel.setText("卡号：");
		
		Label lblNewLabel_1 = new Label(shell, SWT.NONE);
		lblNewLabel_1.setBounds(82, 138, 61, 17);
		lblNewLabel_1.setText("取款金额");
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				String cardid = text.getText();
				
				float money = Float.parseFloat(text_1.getText());
				
				
				
				
				try {
					InputStream in = socket.getInputStream();
					OutputStream out = socket.getOutputStream();
					
					DataInputStream dis = new DataInputStream(in);
					DataOutputStream dos = new DataOutputStream(out);
				
					dos.writeUTF("withdraw");
					dos.writeUTF(cardid);
					dos.writeFloat(money);
					dos.flush();
					
					msg=dis.readUTF();
						
					
					
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}finally {
					MessageBox mb = new MessageBox(shell);
					mb.setMessage(msg);
					mb.open();
					
					
				}
				
				
				
				
			}
		});
		btnNewButton.setBounds(194, 207, 80, 27);
		btnNewButton.setText("确定");
		
		Button btnNewButton_1 = new Button(shell, SWT.NONE);
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.close();
			}
		});
		btnNewButton_1.setBounds(194, 264, 80, 27);
		btnNewButton_1.setText("退出");

	}

}
