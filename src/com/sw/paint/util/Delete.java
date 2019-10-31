package com.sw.paint.util;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;

import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Text;

import com.sw.paint.DBHelper;

import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;

public class Delete extends Dialog {

	protected Object result;
	protected Shell shell;
	private Table table;
	private Text text;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public Delete(Shell parent, int style) {
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
		shell.setSize(630, 516);
		shell.setText(getText());
		
		table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(10, 115, 604, 67);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(62);
		tblclmnNewColumn.setText("姓名");
		
		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_1.setWidth(61);
		tblclmnNewColumn_1.setText("职位");
		
		TableColumn tblclmnNewColumn_2 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_2.setWidth(53);
		tblclmnNewColumn_2.setText("领导");
		
		TableColumn tblclmnNewColumn_3 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_3.setWidth(100);
		tblclmnNewColumn_3.setText("部门");
		
		TableColumn tblclmnNewColumn_4 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_4.setWidth(100);
		tblclmnNewColumn_4.setText("入职时间");
		
		TableColumn tblclmnNewColumn_5 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_5.setWidth(100);
		tblclmnNewColumn_5.setText("薪水");
		
		TableColumn tblclmnNewColumn_6 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_6.setWidth(100);
		tblclmnNewColumn_6.setText("性别");
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		
		btnNewButton.setBounds(88, 228, 80, 27);
		btnNewButton.setText("确认");
		
		Button btnNewButton_1 = new Button(shell, SWT.NONE);
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.dispose();
			}
		});
		btnNewButton_1.setBounds(329, 228, 80, 27);
		btnNewButton_1.setText("取消");
		
		Label label = new Label(shell, SWT.NONE);
		label.setBounds(10, 21, 157, 27);
		label.setText("请输入您要删除的学员姓名：");
		
		text = new Text(shell, SWT.BORDER);
		text.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
				if(e.keyCode==SWT.CR||e.keyCode==SWT.KEYPAD_CR) {
					String sql = "select * from EMP_1 where ename=?";
					List<Map<String, Object>> list = DBHelper.executeQuery(sql, text.getText());

					table.removeAll();

					for (Map<String, Object> row : list) {
						TableItem tableitem = new TableItem(table, SWT.NONE);
						tableitem.setText(new String[] { "" + row.get("ENAME"), "" + row.get("JOB"), "" + row.get("LIEDER"),
								"" + row.get("PARTMENT"), "" + row.get("JOINTIME"), "" + row.get("SALARY"),
								"" + row.get("SEX") });

					}
				}
			}
		});
		text.setBounds(178, 18, 73, 23);
		
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				DBHelper.delete("EMP_1", "ENAME", text.getText());
				MessageBox ms = new MessageBox(shell);
				ms.setMessage("删除成功");
			}
		});
	}

}
