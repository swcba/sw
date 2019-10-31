package com.sw.paint;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

public class EmpQueryWin1 {

	protected Shell shell;
	private Text text;
	private Text text_1;
	private Table table;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			EmpQueryWin1 window = new EmpQueryWin1();
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
		shell.setSize(654, 605);
		shell.setLayout(null);
		
		Label label = new Label(shell, SWT.NONE);
		label.setBounds(34, 10, 24, 17);
		label.setText("姓名");
		
		text = new Text(shell, SWT.BORDER);
		text.setBounds(63, 7, 238, 23);
		
		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setBounds(306, 10, 24, 17);
		label_1.setText("岗位");
		
		text_1 = new Text(shell, SWT.BORDER);
		text_1.setBounds(335, 7, 224, 23);
		
		Button button = new Button(shell, SWT.NONE);
		button.setBounds(564, 5, 69, 27);
		button.setText("查询");
		
		table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(5, 142, 628, 359);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(100);
		tableColumn.setText("编号");
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setWidth(100);
		tableColumn_1.setText("名称");
		
		TableColumn tableColumn_2 = new TableColumn(table, SWT.NONE);
		tableColumn_2.setWidth(100);
		tableColumn_2.setText("性别");
		
		TableColumn tableColumn_3 = new TableColumn(table, SWT.NONE);
		tableColumn_3.setWidth(100);
		tableColumn_3.setText("年龄");
		
		TableColumn tableColumn_4 = new TableColumn(table, SWT.NONE);
		tableColumn_4.setWidth(100);
		tableColumn_4.setText("地址");
		
		Label label_2 = new Label(shell, SWT.NONE);
		label_2.setText("性别：");
		label_2.setBounds(34, 75, 136, 17);
		
		Button button_1 = new Button(shell, SWT.RADIO);
		button_1.setText("男");
		button_1.setBounds(78, 75, 49, 17);
		
		Button button_2 = new Button(shell, SWT.RADIO);
		button_2.setText("女");
		button_2.setBounds(133, 75, 49, 17);

	}
}
