package com.yc.test;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;

import com.sw.paint.DBHelper;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class AddWin extends Dialog {

	protected Object result;
	protected Shell shell;
	private Text text;
	private Text text_1;
	private Text text_2;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public AddWin(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
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
		shell = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.PRIMARY_MODAL);
		shell.setSize(450, 300);
		shell.setText(getText());
		shell.setLayout(null);
		
		Label label = new Label(shell, SWT.NONE);
		label.setBounds(49, 39, 61, 17);
		label.setText("姓名");
		
		text = new Text(shell, SWT.BORDER);
		text.setBounds(147, 36, 73, 23);
		
		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setBounds(49, 70, 61, 17);
		label_1.setText("密码");
		
		text_1 = new Text(shell, SWT.BORDER);
		text_1.setBounds(147, 67, 73, 23);
		
		Label label_2 = new Label(shell, SWT.NONE);
		label_2.setBounds(49, 102, 61, 17);
		label_2.setText("工作");
		
		text_2 = new Text(shell, SWT.BORDER);
		text_2.setBounds(147, 99, 73, 23);
		
		Button button = new Button(shell, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				DBHelper db = new DBHelper();
				String countsql="select count(*)+1 from emp";
				String sql="insert into emp values(("+countsql+"),?,?,?)";
				db.executeUpdate(sql, text.getText(),text_1.getText(),text_2.getText());
				MessageBox mb= new MessageBox(shell,SWT.OK);
				mb.setText("系统提示");
				mb.setMessage("保存成功");
				mb.open();
			
				result = 1;
			}
		});
		button.setBounds(140, 207, 80, 27);
		button.setText("添加");

	}
}
