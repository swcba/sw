package com.sw.paint;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Text;

import com.sw.paint.DBHelper;
import com.sw.paint.util.Alter;
import com.sw.paint.util.Delete;
import com.sw.paint.util.inserWin;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.events.SelectionListener;
import java.util.function.Consumer;

public class EmpQueryWin {

	protected Shell shell;
	private Text text;
	private Text text_1;
	private Text text_2;
	private Table table;
	private Text text_3;
	private int[] linearray = { 3, 4, 5, 10 };
	private int lines;// 规定每页最多有几行
	private long cunt;// 每页有几行
	private int page;// 页数
	private long pages;// 总页数
	

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			EmpQueryWin window = new EmpQueryWin();
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
		shell.setSize(682, 573);
		shell.setText("SWT Application");

		List<Map<String, Object>> list = DBHelper.executeQuery("select * from EMP_1");
		shell.setLayout(null);

		Label label = new Label(shell, SWT.NONE);
		label.setBounds(25, 23, 40, 17);
		label.setText("姓名：");

		text = new Text(shell, SWT.BORDER);
		text.setBounds(86, 20, 156, 23);

		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBounds(248, 23, 30, 17);
		lblNewLabel.setText("职位：");

		text_1 = new Text(shell, SWT.BORDER);
		text_1.setBounds(284, 20, 156, 23);

		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.setBounds(464, 18, 169, 27);

		btnNewButton.setText("查询");

		Button button = new Button(shell, SWT.NONE);
		button.setBounds(553, 69, 80, 27);
		button.setText("新增");

		Button button_1 = new Button(shell, SWT.NONE);
		
		button_1.setBounds(464, 69, 80, 27);
		button_1.setText("重置");

		Button btnNewButton_1 = new Button(shell, SWT.NONE);
		btnNewButton_1.setBounds(464, 120, 80, 27);
		btnNewButton_1.setText("修改");

		Button button_2 = new Button(shell, SWT.NONE);
		button_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				new Delete(shell, SWT.NONE).open();
			}
		});
		button_2.setBounds(553, 120, 80, 27);
		button_2.setText("删除");

		DateTime dateTime = new DateTime(shell, SWT.BORDER);
		dateTime.setBounds(86, 123, 105, 23);

		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setBounds(25, 125, 61, 17);
		label_1.setText("入职时间：");

		text_2 = new Text(shell, SWT.BORDER);
		text_2.setBounds(86, 71, 156, 23);

		Label label_2 = new Label(shell, SWT.NONE);
		label_2.setBounds(248, 74, 30, 17);
		label_2.setText("部门：");

		Combo combo = new Combo(shell, SWT.NONE);
		combo.setItems(new String[] { "", "装备部", "学生会", "招生办", "新闻部", "狮心会", "密党" });
		combo.setBounds(284, 71, 156, 25);

		Label lblNewLabel_1 = new Label(shell, SWT.NONE);
		lblNewLabel_1.setBounds(25, 74, 61, 17);
		lblNewLabel_1.setText("领导：");

		Label lblNewLabel_2 = new Label(shell, SWT.NONE);
		lblNewLabel_2.setBounds(248, 125, 30, 17);
		lblNewLabel_2.setText("薪水：");

		Label lblNewLabel_3 = new Label(shell, SWT.NONE);
		lblNewLabel_3.setBounds(25, 175, 40, 17);
		lblNewLabel_3.setText("性别：");

		Button btnRadioButton = new Button(shell, SWT.RADIO);
		// 男性按钮
		btnRadioButton.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {

			}
		});
		btnRadioButton.setBounds(86, 175, 61, 17);
		btnRadioButton.setText("男");
		// 女性按钮
		Button btnRadioButton_1 = new Button(shell, SWT.RADIO);
		btnRadioButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

			}
		});
		btnRadioButton_1.setBounds(153, 175, 89, 17);
		btnRadioButton_1.setText("女");

		table = new Table(shell, SWT.NONE);
		table.setBounds(25, 224, 631, 167);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(58);
		tableColumn.setText("姓名");

		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setWidth(85);
		tableColumn_1.setText("职位");

		TableColumn tableColumn_2 = new TableColumn(table, SWT.NONE);
		tableColumn_2.setWidth(87);
		tableColumn_2.setText("领导");

		TableColumn tableColumn_3 = new TableColumn(table, SWT.NONE);
		tableColumn_3.setWidth(75);
		tableColumn_3.setText("部门");

		TableColumn tableColumn_4 = new TableColumn(table, SWT.NONE);
		tableColumn_4.setWidth(93);
		tableColumn_4.setText("入职时间");

		TableColumn tableColumn_5 = new TableColumn(table, SWT.NONE);
		tableColumn_5.setWidth(88);
		tableColumn_5.setText("薪水");

		TableColumn tableColumn_6 = new TableColumn(table, SWT.NONE);
		tableColumn_6.setWidth(41);
		tableColumn_6.setText("性别");
		// 首页按钮
		Button btnNewButton_2 = new Button(shell, SWT.NONE);

		btnNewButton_2.setBounds(71, 407, 43, 27);
		btnNewButton_2.setText("首页");
		// 上一页
		Button btnNewButton_3 = new Button(shell, SWT.NONE);

		btnNewButton_3.setBounds(120, 407, 43, 27);
		btnNewButton_3.setText("上一页");
		// 页数选择按钮
		Spinner spinner = new Spinner(shell, SWT.BORDER);
		spinner.setMinimum(1);
		page = spinner.getSelection();
		spinner.setBounds(169, 409, 47, 23);
		// 尾页
		Button button_3 = new Button(shell, SWT.NONE);

		button_3.setText("尾页");
		button_3.setBounds(268, 407, 43, 27);
		// GO
		Button btnGo = new Button(shell, SWT.NONE);

		btnGo.setText("Go");
		btnGo.setBounds(317, 407, 43, 27);
		// 下一页
		Button btnNewButton_4 = new Button(shell, SWT.NONE);

		btnNewButton_4.setBounds(222, 407, 40, 27);
		btnNewButton_4.setText("下一页");

		text_3 = new Text(shell, SWT.BORDER);
		text_3.setBounds(284, 122, 156, 23);
		//行数选择按钮事件监听器
		Combo combo_1 = new Combo(shell, SWT.NONE);
		combo_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				lines = combo_1.getSelectionIndex();
				
			}
		});
		combo_1.setItems(new String[] { "3行/页", "4行/页", "5行/页", "10行/页" });
		combo_1.setBounds(501, 409, 88, 25);

		Button btnCheckButton = new Button(shell, SWT.CHECK);
		btnCheckButton.setBounds(197, 125, 19, 17);
		btnCheckButton.setText(" ");
		
		Label lblNewLabel_4 = new Label(shell, SWT.NONE);
		lblNewLabel_4.setText("共" + cunt + "条记录");
		lblNewLabel_4.setBounds(366, 412, 52, 17);

		Label lblNewLabel_5 = new Label(shell, SWT.NONE);
		lblNewLabel_5.setBounds(424, 412, 47, 17);
		lblNewLabel_5.setText("共" + pages + "页");

		// 查询

		btnNewButton.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {

				cunt = select(1, linearray[lines], text.getText(), text_1.getText(), text_2.getText(), combo.getText(),
						dateTime, Float.parseFloat(spinner.getText()), btnRadioButton.getSelection(),
						btnRadioButton_1.getSelection(), btnCheckButton.getSelection());
				if((cunt % linearray[lines])==0) {
					pages = cunt / linearray[lines];
				}else {
					pages = (cunt / linearray[lines])+1;
				}
				spinner.setMaximum((int)pages);
				System.out.println(cunt + " 条和页" + pages);
				
				lblNewLabel_4.setText("共" + cunt + "条记录");
				lblNewLabel_5.setText("共" + pages + "页");
			}
		});

		// 新增

		button.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.close();
				new inserWin().open();
				
				
			}
		});
		// 修改
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				new Alter(shell, SWT.NONE).open();
			}
		});

		for (Map<String, Object> row : list) {
			TableItem tableitem = new TableItem(table, SWT.NONE);
			tableitem.setText(new String[] { "" + row.get("ENAME"), "" + row.get("JOB"), "" + row.get("LIEDER"),
					"" + row.get("PARTMENT"), "" + row.get("JOINTIME"), "" + row.get("SALARY"), "" + row.get("SEX") });
		}

		// 首页按钮监听器
		btnNewButton_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				spinner.setSelection(1);
				select(1, linearray[lines], text.getText(), text_1.getText(), text_2.getText(), combo.getText(),
						dateTime, Float.parseFloat(spinner.getText()), btnRadioButton.getSelection(),
						btnRadioButton_1.getSelection(), btnCheckButton.getSelection());

			}
		});
		// 上一页按钮事件监听器
		btnNewButton_3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				spinner.setSelection(spinner.getSelection() - 1);
				select(spinner.getSelection(), linearray[lines], text.getText(), text_1.getText(), text_2.getText(),
						combo.getText(), dateTime, Float.parseFloat(spinner.getText()), btnRadioButton.getSelection(),
						btnRadioButton_1.getSelection(), btnCheckButton.getSelection());

			}

		});

		// 下一页按钮事件监听器
		btnNewButton_4.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				spinner.setSelection(spinner.getSelection() + 1);
				select(spinner.getSelection(), linearray[lines], text.getText(), text_1.getText(), text_2.getText(),
						combo.getText(), dateTime, Float.parseFloat(spinner.getText()), btnRadioButton.getSelection(),
						btnRadioButton_1.getSelection(), btnCheckButton.getSelection());

			}

		});

		// go按钮事件监听器
		btnGo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				select(spinner.getSelection(), linearray[lines], text.getText(), text_1.getText(), text_2.getText(),
						combo.getText(), dateTime, Float.parseFloat(spinner.getText()), btnRadioButton.getSelection(),
						btnRadioButton_1.getSelection(), btnCheckButton.getSelection());
			}

		});
		// 尾页按钮事件监听器
		button_3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				spinner.setSelection(spinner.getMaximum());
				select(spinner.getSelection(), linearray[lines], text.getText(), text_1.getText(), text_2.getText(),
						combo.getText(), dateTime, Float.parseFloat(spinner.getText()), btnRadioButton.getSelection(),
						btnRadioButton_1.getSelection(), btnCheckButton.getSelection());
			}
			
		});
		
		//重置按钮
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
			}
		});

	}

	// 查询
	public long select(int page, int lines, String name, String job, String lieder, String partment, DateTime dateTime,
			float salary, boolean sex, boolean sex_1, boolean btnCheckButton) {

		// 获取查询条件

		int year = dateTime.getYear();
		int month = dateTime.getMonth() + 1;
		int day = dateTime.getDay();
		String jointime = (year + month + day) + "";

		// 执行查询
		String sql = "select * from EMP_1 where 1=1";
		// 参数集合
		List<Object> paramList = new ArrayList<Object>();

		if (name != null && name.trim().isEmpty() == false) {
			sql += " and  ename like ?";
			paramList.add("%" + name + "%");
		}
		if (job != null && job.trim().isEmpty() == false) {
			sql += " and  job like ?";
			paramList.add("%" + job + "%");
		}

		if (lieder != null && lieder.trim().isEmpty() == false) {
			sql += " and  lieder like ?";
			paramList.add("%" + lieder + "%");
		}
		if (partment != null && partment.trim().isEmpty() == false) {
			sql += " and  partment like ?";
			paramList.add("%" + partment + "%");
		}

		if (jointime != null && btnCheckButton) {
			sql += " and extract (year from jointime)=? and extract (month from jointime)=? and extract (day from jointime)=?";
			paramList.add(year);
			paramList.add(month);
			paramList.add(day);
		}
		if (sex) {
			sql += " and  sex = 男";
			
		}else if(sex_1) {
			sql += " and  sex = 女";
		}

		List<Map<String, Object>> list = DBHelper.executeQueryPage(sql, page, lines, paramList.toArray());

		table.removeAll();

		for (Map<String, Object> row : list) {
			TableItem tableitem = new TableItem(table, SWT.NONE);
			tableitem.setText(new String[] { "" + row.get("ENAME"), "" + row.get("JOB"), "" + row.get("LIEDER"),
					"" + row.get("PARTMENT"), "" + row.get("JOINTIME"), "" + row.get("SALARY"), "" + row.get("SEX") });

		}
		return DBHelper.executeQueryNo(sql, paramList.toArray());
	}

}
