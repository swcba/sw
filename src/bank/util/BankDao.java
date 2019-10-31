package bank.util;

import java.util.List;
import java.util.Map;

public class BankDao {
	public List<Map<String,Object>> query(String cardno){
		String sql= "select * from account where accountid = ?";
	return	DBHelper.executeQuery(sql, cardno);
		
	}
	
	public void update1(String cardno,float money) {
		String sql= "update account set balance = balance + ? where accountid = ?";
		DBHelper.executeUpdate(sql, money,cardno);
		
	}

	public void insert(String cardno, float money) {
		String sql = "insert into account values(?,?)";
		DBHelper.executeUpdate(sql, cardno,money);
		
	}
	public String update2(String cardno,float money) {
		List<Map<String,Object>> user=query(cardno);
		System.out.println("取款卡号："+user.get(0).get("accountid"));
		String s= user.get(0).get("balance").toString();
		float balance= Float.parseFloat(s );
		if(balance-money<0) {
			return "余额不足";
		}else {

		String sql= "update account set balance = balance - ? where accountid = ?";
		DBHelper.executeUpdate(sql, money,cardno);
		return "取款成功";
		}
	}

	public String transfer(String cardno1, String cardno2, float money) {
		update2(cardno1,money);
		update1(cardno2, money);
		return "转账成功";
	}

}
