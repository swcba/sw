package com.sw.paint.util;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;

import com.sw.paint.DBHelper;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;

public class Alter extends Dialog {

	protected Object result;
	protected Shell shell;
	private Text text;
	private Table table;
	private Text text_1;
	private Text text_2;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public Alter(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * 
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
		shell.setSize(634, 522);
		shell.setText(getText());
		shell.setLayout(null);

		Label lblQingshuru = new Label(shell, SWT.NONE);
		lblQingshuru.setBounds(10, 24, 143, 17);
		lblQingshuru.setText("请输入要修改员工的姓名：");

		text = new Text(shell, SWT.BORDER);
		
		text.setBounds(175, 21, 73, 23);

		Button btnNewButton = new Button(shell, SWT.NONE);

		btnNewButton.setBounds(289, 19, 80, 27);
		btnNewButton.setText("确定");

		table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(10, 62, 578, 64);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(87);
		tableColumn.setText("姓名");

		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setWidth(86);
		tableColumn_1.setText("职位");

		TableColumn tableColumn_2 = new TableColumn(table, SWT.NONE);
		tableColumn_2.setWidth(64);
		tableColumn_2.setText("领导");

		TableColumn tableColumn_3 = new TableColumn(table, SWT.NONE);
		tableColumn_3.setWidth(100);
		tableColumn_3.setText("部门");

		TableColumn tableColumn_4 = new TableColumn(table, SWT.NONE);
		tableColumn_4.setWidth(100);
		tableColumn_4.setText("入职日期");

		TableColumn tableColumn_5 = new TableColumn(table, SWT.NONE);
		tableColumn_5.setWidth(70);
		tableColumn_5.setText("薪水");

		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(56);
		tblclmnNewColumn.setText("性别");

		Label label = new Label(shell, SWT.NONE);
		label.setBounds(10, 151, 143, 17);
		label.setText("请输入修改后的信息:");

		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBounds(138, 180, 61, 17);
		lblNewLabel.setText("职位");

		Label lblNewLabel_1 = new Label(shell, SWT.NONE);
		lblNewLabel_1.setBounds(138, 215, 61, 17);
		lblNewLabel_1.setText("领导");

		Label lblNewLabel_2 = new Label(shell, SWT.NONE);
		lblNewLabel_2.setBounds(138, 297, 61, 17);
		lblNewLabel_2.setText("入职日期");

		Label lblNewLabel_3 = new Label(shell, SWT.NONE);
		lblNewLabel_3.setBounds(138, 340, 61, 17);
		lblNewLabel_3.setText("薪水");

		Label lblNewLabel_4 = new Label(shell, SWT.NONE);
		lblNewLabel_4.setBounds(138, 385, 61, 17);
		lblNewLabel_4.setText("性别");

		Label lblNewLabel_5 = new Label(shell, SWT.NONE);
		lblNewLabel_5.setBounds(138, 258, 61, 17);
		lblNewLabel_5.setText("部门");

		text_1 = new Text(shell, SWT.BORDER);
		text_1.setBounds(226, 174, 73, 23);

		text_2 = new Text(shell, SWT.BORDER);
		text_2.setBounds(226, 212, 73, 23);

		Combo combo = new Combo(shell, SWT.NONE);
		combo.setItems(new String[] { "装备部", "学生会", "新闻部", "狮心会", "密党", "招生办" });
		combo.setBounds(226, 255, 88, 25);

		Spinner spinner = new Spinner(shell, SWT.BORDER);
		spinner.setMaximum(10000);
		spinner.setBounds(226, 334, 47, 23);

		Button btnRadioButton = new Button(shell, SWT.RADIO);
		btnRadioButton.setBounds(226, 385, 97, 17);
		btnRadioButton.setText("男");

		Button btnRadioButton_1 = new Button(shell, SWT.RADIO);
		btnRadioButton_1.setBounds(346, 385, 97, 17);
		btnRadioButton_1.setText("女");

		DateTime dateTime = new DateTime(shell, SWT.BORDER);
		dateTime.setBounds(226, 290, 88, 24);

		Button btnNewButton_1 = new Button(shell, SWT.NONE);
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// 判断数据是否合理
				if (text.getText() == null && text.getText().trim().length() == 0) {
					throw new RuntimeException("姓名不能为空");

				}

				if (text_1.getText() == null && text_1.getText().trim().length() == 0) {
					throw new RuntimeException("职位不能为空");

				}

				if (text_2.getText() == null && text_2.getText().trim().length() == 0) {
					throw new RuntimeException("领导不能为空");

				}
				if (combo.getText() == null && combo.getText().trim().length() == 0) {
					throw new RuntimeException("部门不能为空");

				}
				if (spinner.getText() == null && spinner.getText().trim().length() == 0) {
					throw new RuntimeException("薪水不能为空");

				}
				if (!(btnRadioButton.getSelection() || btnRadioButton_1.getSelection())) {
					throw new RuntimeException("性别不能为空");
				}

				// 添加数据
				DBHelper.update("EMP_1", "ENAME", "JOB", "LIEDER", "PARTMENT", "JOINTIME", "SALARY", "SEX",
						 text_1.getText(), text_2.getText(), combo.getText(),
						Date.valueOf(LocalDate.now()), Float.parseFloat(spinner.getText()),
						btnRadioButton.getSelection() ? "男" : "女",text.getText());
				MessageBox mb = new MessageBox(shell);
				mb.setMessage("修改成功！");
				mb.open();
			}
		});
		btnNewButton_1.setBounds(118, 456, 80, 27);
		btnNewButton_1.setText("确认");

		Button btnNewButton_2 = new Button(shell, SWT.NONE);
		btnNewButton_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.dispose();
			}
		});
		btnNewButton_2.setBounds(259, 456, 80, 27);
		btnNewButton_2.setText("取消");

		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				int year = dateTime.getYear();
				int month = dateTime.getMonth() + 1;
				int day = dateTime.getDay();

				String date = year + "-" + month + "-" + day;
				Date date_1 = Date.valueOf(date);

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
		});
		text.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int year = dateTime.getYear();
				int month = dateTime.getMonth() + 1;
				int day = dateTime.getDay();

				String date = year + "-" + month + "-" + day;
				Date date_1 = Date.valueOf(date);

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
		});
	}
}
