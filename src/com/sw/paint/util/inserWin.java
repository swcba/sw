package com.sw.paint.util;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;

import java.sql.Date;
import java.time.LocalDate;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Text;
import com.sw.paint.DBHelper;


import org.eclipse.swt.widgets.Button;

import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Spinner;

public class inserWin {

	protected Shell shell;
	private Text text;
	private Text text_1;
	private Text text_2;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			inserWin window = new inserWin();
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
		shell.setSize(450, 439);
		shell.setText("SWT Application");
		
	
		Label label = new Label(shell, SWT.NONE);
		label.setBounds(10, 20, 43, 17);
		label.setText("姓名");
		
		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setBounds(10, 69, 43, 17);
		label_1.setText("职位");
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBounds(10, 120, 41, 17);
		lblNewLabel.setText("领导");
		
		Label label_2 = new Label(shell, SWT.NONE);
		label_2.setBounds(10, 169, 38, 17);
		label_2.setText("部门");
		
		text = new Text(shell, SWT.BORDER);
		text.setBounds(77, 17, 210, 23);
		
		
		text_1 = new Text(shell, SWT.BORDER);
		text_1.setBounds(77, 66, 210, 23);
	
		
		text_2 = new Text(shell, SWT.BORDER);
		text_2.setBounds(77, 117, 210, 23);
	
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.setBounds(37, 326, 80, 27);
		btnNewButton.setText("保存");
		
		
		Button btnNewButton_1 = new Button(shell, SWT.NONE);
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.close();
			}
		});
		btnNewButton_1.setBounds(194, 326, 80, 27);
		btnNewButton_1.setText("退出");
		
		Label lblNewLabel_1 = new Label(shell, SWT.NONE);
		lblNewLabel_1.setBounds(10, 228, 61, 17);
		lblNewLabel_1.setText("薪水");
		
		Label lblNewLabel_2 = new Label(shell, SWT.NONE);
		lblNewLabel_2.setBounds(10, 280, 61, 17);
		lblNewLabel_2.setText("性别");
		
		Button btnRadioButton = new Button(shell, SWT.RADIO);
		btnRadioButton.setBounds(77, 280, 55, 17);
		btnRadioButton.setText("男");
		
		Button btnRadioButton_1 = new Button(shell, SWT.RADIO);
		btnRadioButton_1.setBounds(138, 280, 97, 17);
		btnRadioButton_1.setText("女");
		
		Combo combo = new Combo(shell, SWT.NONE);
		combo.setItems(new String[] {"装备部", "学生会", "新闻部", "狮心会", "密党", "招生办"});
		combo.setBounds(77, 166, 210, 25);
		
		Spinner spinner = new Spinner(shell, SWT.BORDER);
		spinner.setMaximum(10000);
		spinner.setBounds(77, 225, 210, 23);
		
		
		
		
		btnNewButton.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				//判断数据是否合理
				if ( text.getText() == null &&  text.getText().trim().length() == 0) {
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
				if (spinner.getText() == null &&spinner.getText().trim().length() == 0) {
					throw new RuntimeException("薪水不能为空");

				}
				if(!(btnRadioButton.getSelection()||btnRadioButton_1.getSelection())) {
					throw new RuntimeException("性别不能为空");
				}
				
				
				
				//添加数据
				DBHelper.insert("EMP_1", text.getText(),text_1.getText(),
						text_2.getText(),combo.getText(),Date.valueOf(LocalDate.now()),
						Float.parseFloat(spinner.getText()),
						btnRadioButton.getSelection()?"男":"女");
				MessageBox mb = new MessageBox(shell);
				mb.setMessage("保存成功！");
				mb.open();
			}
		});
	}
}
