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
		float balance= Float.parseFloat((String) user.get(0).get("balance"));
		if(balance-money<0) {
			return "余额不足";
		}else {

		String sql= "update account set balance = balance - ? where accountid = ?";
		DBHelper.executeUpdate(sql, money,cardno);
		return "取款成功";
		}
	}

}
