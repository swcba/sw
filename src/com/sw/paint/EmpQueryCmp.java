package com.sw.paint;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import com.sw.paint.DBHelper;

public class EmpQueryCmp extends Composite {
	private Table table;
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private Shell shell;
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public EmpQueryCmp(Composite parent, int style) {
		super(parent, style);
		shell = parent.getShell();
		shell = new Shell();
		shell.setSize(791, 531);
		shell.setText("SWT Application");

		table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(23, 108, 728, 411);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		List<Map<String, Object>> list = DBHelper.executeQuery("select * from TBEMP");

		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(100);
		tblclmnNewColumn.setText("编号");

		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_1.setWidth(100);
		tblclmnNewColumn_1.setText("姓名");

		TableColumn tblclmnNewColumn_2 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_2.setWidth(100);
		tblclmnNewColumn_2.setText("性别");

		TableColumn tblclmnNewColumn_3 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_3.setWidth(100);
		tblclmnNewColumn_3.setText("年龄");

		TableColumn tblclmnNewColumn_4 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_4.setWidth(100);
		tblclmnNewColumn_4.setText("地址");

		Label label = new Label(shell, SWT.NONE);
		label.setBounds(23, 23, 40, 17);
		label.setText("姓名：");

		text = new Text(shell, SWT.BORDER);
		text.setBounds(69, 20, 156, 23);

		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBounds(294, 23, 52, 17);
		lblNewLabel.setText("地址：");

		text_1 = new Text(shell, SWT.BORDER);
		text_1.setBounds(351, 19, 156, 23);

		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// 获取查询条件
				String ename = text.getText();
				String eaddr = text_1.getText();

				// 执行查询
				String sql = "select * from TBEMP where 1=1";
				// 参数集合
				List<Object> paramList = new ArrayList<Object>();

				if (ename != null && ename.trim().isEmpty() == false) {
					sql += " and  ename like ?";
					paramList.add("%"+ename+"%");
				}
				if (eaddr != null && eaddr.trim().isEmpty() == false) {
					sql += " and  eaddr like ?";
					paramList.add("%"+eaddr+"%");
				}
				
				List<Map<String, Object>> list = DBHelper.executeQuery(sql, paramList.toArray());

				table.removeAll();

				for (Map<String, Object> row : list) {
					TableItem tableitem = new TableItem(table, SWT.NONE);
					tableitem.setText(new String[] { "" + row.get("EID"), "" + row.get("ENAME"), "" + row.get("ESEX"),
							"" + row.get("SAGE"), "" + row.get("EADDR") });
				}

			}
		});
		btnNewButton.setBounds(593, 16, 80, 27);
		btnNewButton.setText("查询");
		
		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setText("姓名：");
		label_1.setBounds(23, 65, 40, 17);
		
		text_2 = new Text(shell, SWT.BORDER);
		text_2.setBounds(69, 59, 156, 23);
		
		Label label_2 = new Label(shell, SWT.NONE);
		label_2.setText("地址：");
		label_2.setBounds(294, 65, 52, 17);
		
		text_3 = new Text(shell, SWT.BORDER);
		text_3.setBounds(351, 59, 156, 23);
		
		Button button = new Button(shell, SWT.NONE);
		button.setText("新增");
		button.setBounds(593, 65, 80, 27);

		for (Map<String, Object> row : list) {
			TableItem tableitem = new TableItem(table, SWT.NONE);
			tableitem.setText(new String[] { "" + row.get("EID"), "" + row.get("ENAME"), "" + row.get("ESEX"),
					"" + row.get("SAGE"), "" + row.get("EADDR") });
		}
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
