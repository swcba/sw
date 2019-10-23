package com.sw.paint;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class TabWin {

	protected Shell shell;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			TabWin window = new TabWin();
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
		shell.setSize(450, 300);
		shell.setText("SWT Application");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		final TabFolder tabFolder = new TabFolder(shell, SWT.NONE);
		tabFolder.setToolTipText("");
		
		TabItem tabItem = new TabItem(tabFolder, SWT.NONE);
		tabItem.setText("欢迎使用");
		
		TabItem tabItem_1 = new TabItem(tabFolder, SWT.NONE);
		tabItem_1.setText("员工查询");
		
		Menu menu = new Menu(shell, SWT.BAR);
		shell.setMenuBar(menu);
		
		MenuItem menuItem = new MenuItem(menu, SWT.NONE);
		menuItem.setText("系统管理");
		
		MenuItem menuItem_1 = new MenuItem(menu, SWT.CASCADE);
		menuItem_1.setText("员工管理");
		
		Menu menu_1 = new Menu(menuItem_1);
		menuItem_1.setMenu(menu_1);
		
		MenuItem menuItem_2 = new MenuItem(menu_1, SWT.NONE);
		menuItem_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TabItem tbtmNewItem = new TabItem(tabFolder,SWT.NONE);
				tbtmNewItem.setText("员工查询");
				//创建自定义组件
				EmpQueryCmp eqc = new EmpQueryCmp(tabFolder, SWT.NONE);
				//设置标签显示的自定义组件
				tbtmNewItem.setControl(eqc);
				
				//选中当前标签页
				tabFolder.setSelection(tbtmNewItem);
			}
			
		});
		menuItem_2.setText("员工查询");

	}
}
