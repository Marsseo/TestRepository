package com.mycompany.myapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.mycompany.myapp.dto.Exam12Board;
import com.mycompany.myapp.dto.Exam12Member;

@Component
public class Exam12DaoImpl implements Exam12Dao{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Exam12DaoImpl.class);
			int bno = -1;
			String mid = null;
			Connection conn = null;
	@Override
	public int insert1(Exam12Board board) {
		try {
			//JDBC Driver 클래스 로딩
			Class.forName("oracle.jdbc.OracleDriver");
			//연결 문자열 작성
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			//연결 객체 얻기
			conn = DriverManager.getConnection(url, "iotuser", "iot12345");
			LOGGER.info("연결성공");
			//매개 변수화된 SQL 작성
			String sql = "insert into board ";
			sql+="(BNO,BTITLE, bcontent, bwriter, bdate, bpassword, bhitcount, boriginalfilename, bsavedfilename, bfilecontent) ";
			sql+="values ";
			sql+="(board_bno_seq.nextval, ?, ?, ?, sysdate, ?, '0', ?, ?, ?) ";
			//SQL 문을 전송해서 실행
			//테이블 저의시 칼럼의 속성으로 자동 증가를 지정할 수 있는 DB일 경우(MySQL, MS SQL)
			//PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			//오라클일 경우 Sequence 외부 객체로 자동 증가값을 얻기 때문에 다음과 같이 지정
			PreparedStatement pstmt = conn.prepareStatement(sql, new String[] {"bno"});
			pstmt.setString(1, board.getBtitle());
			pstmt.setString(2, board.getBcontent());
			pstmt.setString(3, board.getBwriter());
			pstmt.setString(4, board.getBpassword());
			pstmt.setString(5, board.getBoriginalfilename());
			pstmt.setString(6, board.getBsavedfilename());
			pstmt.setString(7, board.getBfilecontent());
			pstmt.executeUpdate();
			
			ResultSet rs = pstmt.getGeneratedKeys();
			rs.next();
			bno = rs.getInt(1);
			pstmt.close();
			LOGGER.info("행 추가 성공");
			LOGGER.info("추가된 행의 bno: " + bno);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			//연결끊기
			try {
				conn.close();
				LOGGER.info("연결끊기");
			} catch (SQLException e) {e.printStackTrace();}
			
		}
		return bno;		
	}

	
	
	public static void main(String[] args){
		Exam12DaoImpl test = new Exam12DaoImpl();
		Exam12Board b = new Exam12Board();
		b.setBtitle("신기");
		b.setBcontent("방기");
		b.setBwriter("홍길동");
		b.setBpassword("1234");
		b.setBoriginalfilename("a.png");
		b.setBsavedfilename("a.png");
		b.setBfilecontent("image/png");
		
		int bno = test.insert1(b);
	}



	@Override
	public String insert2(Exam12Member m) {
		try {
			//JDBC Driver 클래스 로딩
			Class.forName("oracle.jdbc.OracleDriver");
			//연결 문자열 작성
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			//연결 객체 얻기
			conn = DriverManager.getConnection(url, "iotuser", "iot12345");
			LOGGER.info("연결성공");
			//매개 변수화된 SQL 작성
			String sql = "insert into member ";
			sql+="(mid, mname, mpassword, mdate, mtel, memail, mage, maddress, moriginalfilename, msavedfilename, mfilecontent) ";
			sql+="values ";
			sql+="(?, ?, ?, sysdate, ?, ?, ?, ?, ?, ?, ?) ";
			//SQL 문을 전송해서 실행
			//테이블 저의시 칼럼의 속성으로 자동 증가를 지정할 수 있는 DB일 경우(MySQL, MS SQL)
			//PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			//오라클일 경우 Sequence 외부 객체로 자동 증가값을 얻기 때문에 다음과 같이 지정
			PreparedStatement pstmt = conn.prepareStatement(sql, new String[] {"mid"});
			pstmt.setString(1, m.getMid());
			pstmt.setString(2, m.getMname());
			pstmt.setString(3, m.getMpassword());
			pstmt.setString(4, m.getMtel());
			pstmt.setString(5, m.getMemail());
			pstmt.setInt(6, m.getMage());
			pstmt.setString(7, m.getMaddress());
			pstmt.setString(8, m.getMoriginalfilename());
			pstmt.setString(9, m.getMsavedfilename());
			pstmt.setString(10, m.getMfilecontent());
			pstmt.executeUpdate();
			
			ResultSet rs = pstmt.getGeneratedKeys();
			rs.next();
			mid = rs.getString(1);
			pstmt.close();
			LOGGER.info("행 추가 성공");
			LOGGER.info("추가된 행의 mid: " + mid);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			//연결끊기
			try {
				conn.close();
				LOGGER.info("연결끊기");
			} catch (SQLException e) {e.printStackTrace();}
			
		}
		return mid;
	}
}
