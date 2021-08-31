package ex1;


import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;







public class Program2 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
		String sql = "INSERT INTO NOTICE(title, writer_id, content, files) "
				+ "VALUES(?,?,?,?)"; 
		
		String title = "TEST2";
		String writerid = "newlec";
		String content = "hahaha";
		String files = "";
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection(url,"newlec","dnfldml1");
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, title);
		st.setString(2, writerid);
		st.setString(3, content);
		st.setString(4, files);
		
		int row_cnt = st.executeUpdate();
		
		System.out.println(row_cnt);
		
		st.close();
		con.close();

	}

}
