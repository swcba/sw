package com.yc.test;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;

import com.sw.paint.DBHelper;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class BookupWin extends Dialog {

	protected Object result;
	protected Shell shell;
	private Text text;
	private Button button;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public BookupWin(Shell parent, int style) {
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
		shell = new Shell(getParent(), getStyle());
		shell.setSize(450, 300);
		shell.setText(getText());
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBounds(40, 83, 61, 17);
		lblNewLabel.setText("备份路径");
		
		text = new Text(shell, SWT.BORDER);
		text.setText("");
		text.setBounds(158, 80, 73, 23);
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileWriter fw= null;
				DBHelper db =new DBHelper();
				try {
					List<Map<String,Object>> list = db.executeQuery("select * from emp");
					
					fw=new FileWriter(text.getText());
					
					
					for(int i = 0;i<list.size();i++) {
						String row="%s\t%s\t%s\t%s\t\r\n";
						String line = String.format(row, list.get(i).get("EID"),
								 list.get(i).get("ENAME"),
								 list.get(i).get("PWD"),
								 list.get(i).get("JOB"));
								fw.append(line);
					}
					MessageBox mb= new MessageBox(shell,SWT.OK);
					mb.setText("系统提示");
					mb.setMessage("备份成功");
					mb.open();
				
					result = 1;
					shell.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					MessageBox mb= new MessageBox(shell,SWT.OK);
					mb.setText("系统提示");
					mb.setMessage("备份失败");
					mb.open();
					e1.printStackTrace();
				}finally {
					if(fw!=null) {
					try {
						fw.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					}
				}
				
				
				
				
			}
		});
		btnNewButton.setBounds(110, 161, 80, 27);
		btnNewButton.setText("备份");
		
		button = new Button(shell, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				File a = new File(text.getText());
				DBHelper db =new DBHelper();
				FileReader fr = null;
				BufferedReader br = null;
				if(a.exists()) {
					try {
					
					String deletesql="delete from emp";
					String insertsql="insert into emp values(?,?,?,?)";
					db.executeUpdate(deletesql);
					fr = new FileReader(text.getText());
					br = new BufferedReader(fr);
					String line = null;
					try {
						while((line = br.readLine())!=null) {
							String[] values = line.split("\\t");
							for(int i=0;i<values.length;i++) {
							//String value = "null".equals(values[i]) ? null:values[i]; values[i];
						}
						
						}
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					MessageBox mb =new MessageBox(shell);
					mb.setText("系统提示");
					mb.setMessage("还原成功");
					mb.open();
					result = 1;
					
					
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				}else {
					MessageBox mb =new MessageBox(shell);
				mb.setText("系统提示");
				mb.setMessage("备份文件不存在");
				mb.open();
				}
			}
		});
		button.setBounds(232, 161, 80, 27);
		button.setText("还原");

	}
}
