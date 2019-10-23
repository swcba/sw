package com.sw.paint;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DateTime;

public class Wrok {

	protected Shell shell;
	private Text text;
	private Text text_1;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Wrok window = new Wrok();
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
		shell.setSize(450, 394);
		
		Label label = new Label(shell, SWT.NONE);
		label.setBounds(67, 37, 43, 17);
		label.setText("\u7528\u6237\u540D\uFF1A");
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBounds(67, 75, 36, 17);
		lblNewLabel.setText("\u5BC6\u7801\uFF1A");
		
		text = new Text(shell, SWT.BORDER);
		text.setBounds(151, 34, 148, 20);
		
		text_1 = new Text(shell, SWT.BORDER | SWT.PASSWORD);
		text_1.setBounds(151, 72, 148, 20);
		
		Button button = new Button(shell, SWT.CHECK);
		button.setBounds(152, 109, 52, 17);
		button.setText("\u8BFB\u4E66");
		
		Button button_1 = new Button(shell, SWT.CHECK);
		button_1.setText("\u770B\u7535\u5F71");
		button_1.setBounds(210, 109, 56, 17);
		
		Button button_2 = new Button(shell, SWT.CHECK);
		button_2.setText("\u542C\u97F3\u4E50");
		button_2.setBounds(278, 109, 56, 17);
		
		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setBounds(67, 109, 43, 17);
		label_1.setText("\u7231\u597D:");
		
		Label label_2 = new Label(shell, SWT.NONE);
		label_2.setBounds(67, 147, 61, 17);
		label_2.setText("\u6027\u522B:");
		
		Button button_3 = new Button(shell, SWT.RADIO);
		button_3.setBounds(150, 147, 56, 17);
		button_3.setText("\u7537");
		
		Button button_4 = new Button(shell, SWT.RADIO);
		button_4.setText("\u5973");
		button_4.setBounds(210, 147, 56, 17);
		
		Label label_3 = new Label(shell, SWT.NONE);
		label_3.setBounds(67, 184, 56, 17);
		label_3.setText("\u7C4D\u8D2F:");
		
		Combo combo = new Combo(shell, SWT.NONE);
		combo.setToolTipText("");
		combo.setItems(new String[] {"\u5E7F\u4E1C", "\u6E56\u5357", "\u4E0A\u6D77"});
		combo.setBounds(151, 181, 88, 25);
		combo.setText("\u5E7F\u4E1C");
		
		Combo combo_1 = new Combo(shell, SWT.NONE);
		combo_1.setItems(new String[] {"\u5E7F\u5DDE", "\u97F6\u5173", "\u4E1C\u839E"});
		combo_1.setBounds(262, 181, 88, 25);
		combo_1.setText("\u5E7F\u5DDE");
		
		Label label_4 = new Label(shell, SWT.NONE);
		label_4.setBounds(67, 231, 52, 17);
		label_4.setText("\u751F\u65E5:");
		
		DateTime dateTime = new DateTime(shell, SWT.BORDER);
		dateTime.setBounds(151, 224, 135, 24);
		shell.setTabList(new Control[]{text, text_1, button, button_1, button_2, button_3, button_4, combo, combo_1});

	}
}
