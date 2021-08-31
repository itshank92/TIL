package com.newlecture.app.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import com.newlecture.app.entity.Notice;



public class NoticeService {
	private String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
	private String uid = "newlec";
	private String pwd = "dnfldml1";
	
	
	public List<Notice> getList(int page, String field, String query) throws ClassNotFoundException, SQLException {
		
		
		int start = (page*10)-9 ;
		int end = page*10;
		
		String sql = "SELECT * FROM NOTICE_VIEW WHERE " + field + " LIKE ? AND NUM BETWEEN ? AND ?"; 
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection(url,uid,pwd);
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, "%"+ query +"%");
		st.setInt(2, start);
		st.setInt(3, end);
		ResultSet rs = st.executeQuery();
		
		List<Notice> list = new ArrayList<Notice>();
		
		while(rs.next()) {
			int id = rs.getInt("ID");
			String title = rs.getString("TITLE");
			String writerid = rs.getString("WRITER_ID");
			Date regDate = rs.getDate("REGDATE");
			String content = rs.getString("CONTENT");
			String files = rs.getString("FILES");
			int hit = rs.getInt("HIT");
			
			Notice notice = new Notice(id,title,writerid,regDate,content,hit,files);
			
			list.add(notice);

		}
		
		
		rs.close();
		st.close();
		con.close();
		return list;
		
	}
	// 단일값(Scalar 값)을 얻는 함수
	public int getCount() throws ClassNotFoundException, SQLException {
		
		int count = 0;
		String sql = "SELECT COUNT(ID) COUNT FROM NOTICE"; 
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection(url,uid,pwd);
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		
		
		if(rs.next()) {
			count = rs.getInt("COUNT");
		}
						
		
		rs.close();
		st.close();
		con.close();
		
		return count;
	}

	public int insert(Notice notice) throws ClassNotFoundException, SQLException {
		
		String sql = "INSERT INTO NOTICE(title, writer_id, content, files) "
				+ "VALUES(?,?,?,?)"; 
		
		String title = notice.getTitle();
		String writerid = notice.getWriterid(); 
		String content = notice.getContent();
		String files = notice.getFiles();
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection(url,uid,pwd);
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, title);
		st.setString(2, writerid);
		st.setString(3, content);
		st.setString(4, files);
		
		int row_cnt = st.executeUpdate();
		
		st.close();
		con.close();
		
		return row_cnt;
	}
	
	public int update(Notice notice) throws ClassNotFoundException, SQLException {
		
		String sql = "UPDATE NOTICE SET TITLE = ?, CONTENT = ?, FILES = ? WHERE ID=?"; 
		
		String title = notice.getTitle();
		String content = notice.getContent() ;
		String files = notice.getFiles();
		int id = notice.getId();
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection(url,uid,pwd);
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, title);
		st.setString(2, content);
		st.setString(3, files);
		st.setInt(4, id);
		
		int row_cnt = st.executeUpdate();
		
		st.close();
		con.close();
		return row_cnt;
	}
	
	public int delete(Integer id) throws ClassNotFoundException, SQLException {
		String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
		String sql = "DELETE * FROM NOTICE WHERE ID=?"; 
		
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection(url,uid,pwd);
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, id);
		
		int row_cnt = st.executeUpdate();
		
		
		st.close();
		con.close();
		
		return row_cnt;
	}


}
