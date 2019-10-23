package com.sw.paint;

import com.sw.paint.DBHelper;

public class Test {
public static void main(String[] args) {
	DBHelper db = new DBHelper();
	long i=	db.executeQueryNo("select * from EMP_1");
	System.out.println(i);
}
}
