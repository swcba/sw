package bank.ui;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import java.net.Socket;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class register extends Dialog {

	protected Object result;
	protected Shell shell;
	private Text text;
	private Text text_1;
	private Socket socket;
	
	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public register(Shell parent, int style,Socket socket) {
		super(parent, style);
		setText("SWT Dialog");
		this.socket=socket;
		
	
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shell.open();
		shell.layout();
		Display display = getParent().getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
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
		lblNewLabel_1.setText("开户金额");
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				String cardid = text.getText();
				
				float money = Float.parseFloat(text_1.getText());
				
				
				
			}
		});
		btnNewButton.setBounds(194, 207, 80, 27);
		btnNewButton.setText("开户");
		
		Button btnNewButton_1 = new Button(shell, SWT.NONE);
		btnNewButton_1.setBounds(194, 264, 80, 27);
		btnNewButton_1.setText("退出");

	}

}
