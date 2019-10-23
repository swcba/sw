package com.sw.paint;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;



/**
 * 属于JDBC操作的工具类
 * 
 * @author sw
 * 
 */

public class DBHelper {
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			// 静�?�块中编译期异常无法抛出，可以抛出运行期异常
			throw new RuntimeException(e);
		}

	}

	// 获取连接
	public static Connection openConnection() {
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		String user = "scott";
		String password = "sw";
		try {
			return DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			
			throw new RuntimeException(e);
		}
	}

	// 变更数据
	/**
	 * 
	 * @param sql
	 * @param param
	 *            Object...param 可变更参数数�??
	 * @return
	 */
	public static int executeUpdate(String sql, Object... param) {
		Connection conn = openConnection();

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			// 设置参数
			for (int i = 0; i < param.length; i++) {
				ps.setObject(i + 1, param[i]);
			}
			
			//打印sql语句
			System.out.println(sql);
			//arrays.toString 数组转字�??
			System.out.println("参数:"+Arrays.toString(param));
			
			return ps.executeUpdate();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}

	}
	//新增数据
	public static void insert(String table,Object...param){
		String sql="insert into "+table+" values(";
		for(int i=0;i<param.length;i++){
			if(i<param.length-1){
			sql+="?,";
			}else{
				sql+="?";
			}
		}
		sql+=")";

		//打印sql语句
		System.out.println(sql);
		//arrays.toString 数组转字�??
		System.out.println("参数:"+Arrays.toString(param));
		
		
		System.out.println(executeUpdate(sql, param));
		
	}
	//删除数据delete from dept where deptno = ?
	public static void delete(String table,String columnname ,Object...param){
		String sql="delete from "+table+" where "+columnname+" = ?";

		//打印sql语句
		System.out.println(sql);
		//arrays.toString 数组转字�??
		System.out.println("参数:"+Arrays.toString(param));
		
		
		executeUpdate(sql,param);
		
	}
	//更新数据update  dept set dname=? , loc=? where deptno=?
	public static void update(String table,Object...param){
		String sql="update  "+table+" set ";
		int size=param.length/2;
		for(int i=1;i<size;i++){
			if(i<size-1){
				sql+=(String)param[i]+" = ?,";
				}else{
					sql+=(String)param[i]+" = ? ";
				}
		}
		sql+="where "+param[0]+" = ?";

		//打印sql语句
		System.out.println(sql);
		//arrays.toString 数组转字�??
		System.out.println("参数:"+Arrays.toString(param));
		
		
		Object[] newparam=new Object[size];
		System.arraycopy(param, size, newparam, 0, size);
	
		executeUpdate(sql, newparam);
	}
	
	public static List<Map<String, Object>> executeQuery(String sql,
			Object... param) {
		Connection conn = openConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			// 设置参数
			for (int i = 0; i < param.length; i++) {
				ps.setObject(i + 1, param[i]);
			}
			

			//打印sql语句
			System.out.println(sql);
			//arrays.toString 数组转字�??
			System.out.println("参数:"+Arrays.toString(param));
			
			
			// 封装结果�??
			ResultSet res = ps.executeQuery();
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			// 结果集的元数据对象，封装了结果集的所有信息，包括列的数量，列的名字
			ResultSetMetaData resm = res.getMetaData();
			while (res.next()) {
				// 把每一行的值写入map
				// res.getstring
				Map<String, Object> row = new LinkedHashMap<String, Object>();
				// getColumnCount()获取结果集中列的数量
				for (int i = 0; i < resm.getColumnCount(); i++) {
					// getcolumnName 根据列编号获取列名
					String columnName = resm.getColumnName(i + 1);
					Object value = res.getObject(columnName);
					row.put(columnName, value);
				}
				list.add(row);
			}

			return list;

		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				conn.close();

			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}

	}

	/**
	 * 分页查询
	 * 
	 * @param sql
	 * @param page
	 *            第几页
	 * @param rows
	 *            每页有多少行
	 * @param param
	 * @return
	 */
	public static List<Map<String, Object>> executeQueryPage(String sql,
			int page, int rows, Object... param) {
		sql = "select * from (select t.*,rownum rn from(" + sql
				+ ") t where rownum <= ?) where rn >?";

		//打印sql语句
		System.out.println(sql);
		//arrays.toString 数组转字符
		System.out.println("参数:"+Arrays.toString(param));
		
		
		int endRow = page * rows;
		int startRow = (page - 1) * rows;

		Object[] newObject = new Object[param.length + 2];
		System.arraycopy(param, 0, newObject, 0, param.length);
		
		newObject[newObject.length-2]=endRow;
		newObject[newObject.length-1]=startRow;
		
		return executeQuery(sql, newObject);

		
	}

	/**
	 * 作业
	 * 查询�??段数据，返回结果集个�??
	 * @param args
	 */
	public static Long executeQueryNo(String sql,Object... param){
		sql = "select count(*) cnt from ("+sql+") a";

		//打印sql语句
		System.out.println(sql);
		//arrays.toString 数组转字�??
		System.out.println("参数:"+Arrays.toString(param));
		
		
		List<Map<String,Object>> ret = executeQuery(sql, param);
		return Long.parseLong(""+ret.get(0).get("cnt"));
		
	}
	

}

