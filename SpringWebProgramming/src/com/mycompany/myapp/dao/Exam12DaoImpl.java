package com.mycompany.myapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
	public int boardInsert(Exam12Board board) {
		try {
			//JDBC Driver 클래스 로딩
			Class.forName("oracle.jdbc.OracleDriver");
			//연결 문자열 작성
			String url = "jdbc:oracle:thin:@106.253.56.126:1521:orcl";
			//연결 객체 얻기
			conn = DriverManager.getConnection(url, "user17", "java12345");
			//dataSource.getConnection();
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

	@Override
	public List<Exam12Board> boardSelectAll() {
		List<Exam12Board> list = new ArrayList<>();
		try {
			//JDBC Driver 클래스 로딩
			Class.forName("oracle.jdbc.OracleDriver");
			//연결 문자열 작성
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			//연결 객체 얻기
			conn = DriverManager.getConnection(url, "iotuser", "iot12345");
			LOGGER.info("연결성공");
			//매개 변수화된 SQL 작성
			String sql = "select bno, btitle, bwriter, bdate, bhitcount ";
			sql+= "from board ";
			sql+= "order by bno asc";
			//SQL 문을 전송해서 실행
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				Exam12Board b = new Exam12Board();
				b.setBno(rs.getInt("bno"));
				b.setBtitle(rs.getString("btitle"));
				b.setBwriter(rs.getString("bwriter"));
				b.setBdate(rs.getDate("bdate"));
				list.add(b);
			}
			rs.close();
			pstmt.close();
			LOGGER.info("가져오기 성공");
			
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
		
		return list;
	}



	@Override
	public List<Exam12Board> boardSelectPage(int pageNo, int rowsPerPage) {
		List<Exam12Board> list = new ArrayList<>();
		try {
			//JDBC Driver 클래스 로딩
			Class.forName("oracle.jdbc.OracleDriver");
			//연결 문자열 작성
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			//연결 객체 얻기
			conn = DriverManager.getConnection(url, "iotuser", "iot12345");
			LOGGER.info("연결성공");
			//매개 변수화된 SQL 작성
			String sql = "select * from(";
			sql+="select rownum as r, bno, btitle, bwriter, bdate, bhitcount from (";
			sql+="select bno, btitle, bwriter, bdate, bhitcount from board order by bno desc) ";
			sql+="where rownum <= ?";
			sql+=")where r>= ? ";
			//SQL 문을 전송해서 실행
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pageNo*rowsPerPage);
			pstmt.setInt(2, (pageNo-1)*rowsPerPage+1);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				Exam12Board b = new Exam12Board();
				b.setBno(rs.getInt("bno"));
				b.setBtitle(rs.getString("btitle"));
				b.setBwriter(rs.getString("bwriter"));
				b.setBdate(rs.getDate("bdate"));
				b.setBhitcount(rs.getInt("bhitcount"));
				list.add(b);
			}
			rs.close();
			pstmt.close();
			LOGGER.info("가져오기 성공");
			
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
		
		return list;
	}
	@Override
	public int boardCountAll() {
		int count = 0;
		List<Exam12Board> list = new ArrayList<>();
		try {
			//JDBC Driver 클래스 로딩
			Class.forName("oracle.jdbc.OracleDriver");
			//연결 문자열 작성
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			//연결 객체 얻기
			conn = DriverManager.getConnection(url, "iotuser", "iot12345");
			LOGGER.info("연결성공");
			//매개 변수화된 SQL 작성
			String sql = "select count(*) from board";
			
			//SQL 문을 전송해서 실행
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			count = rs.getInt(1);
			rs.close();
			pstmt.close();
			LOGGER.info("가져오기 성공");
			
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
		
		return count;
	}
	@Override
	public Exam12Board boardselectByBno(int bno) {
		Exam12Board board = null;
		try {
			//JDBC Driver 클래스 로딩
			Class.forName("oracle.jdbc.OracleDriver");
			//연결 문자열 작성
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			//연결 객체 얻기
			conn = DriverManager.getConnection(url, "iotuser", "iot12345");
			LOGGER.info("연결성공");
			//매개 변수화된 SQL 작성
			String sql = "select * from board where bno=?";
			
			//SQL 문을 전송해서 실행
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()){
				board = new Exam12Board();
				board.setBno(rs.getInt("bno"));
				board.setBtitle(rs.getString("btitle"));
				board.setBwriter(rs.getString("bwriter"));
				board.setBdate(rs.getDate("bdate"));
				board.setBhitcount(rs.getInt("bhitcount"));
				board.setBcontent(rs.getString("bcontent"));
				board.setBpassword(rs.getString("bpassword"));
				board.setBoriginalfilename(rs.getString("boriginalfilename"));
				board.setBsavedfilename(rs.getString("bsavedfilename"));
				board.setBfilecontent(rs.getString("bfilecontent"));
			}
			rs.close();
			pstmt.close();
			LOGGER.info("가져오기 성공");
			
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
		
		return board;
	}
	@Override
	public void boardUpdateBhitcount(int bno, int bhitcount) {
		int count = 0;
		List<Exam12Board> list = new ArrayList<>();
		try {
			//JDBC Driver 클래스 로딩
			Class.forName("oracle.jdbc.OracleDriver");
			//연결 문자열 작성
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			//연결 객체 얻기
			conn = DriverManager.getConnection(url, "iotuser", "iot12345");
			LOGGER.info("연결성공");
			//매개 변수화된 SQL 작성
			String sql = "update board set bhitcount=? where bno=?";
			
			//SQL 문을 전송해서 실행
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bhitcount);
			pstmt.setInt(2, bno);
			pstmt.executeUpdate();
			pstmt.close();
			LOGGER.info("가져오기 성공");
			
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
		
	}
	@Override
	public void boardUpdate(Exam12Board board) {
		try {
			//JDBC Driver 클래스 로딩
			Class.forName("oracle.jdbc.OracleDriver");
			//연결 문자열 작성
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			//연결 객체 얻기
			conn = DriverManager.getConnection(url, "iotuser", "iot12345");
			LOGGER.info("연결성공");
			//매개 변수화된 SQL 작성
			String sql = "update board set btitle=?, bcontent=?, bpassword=?, ";
			if(board.getBoriginalfilename()!=null){
				sql +="bdate=sysdate, boriginalfilename=?, bsavedfilename=?, bfilecontent=? ";
				sql +="where bno=?";
			}else{
				sql +="bdate=sysdate ";
				sql +="where bno=?";
			}
			//SQL 문을 전송해서 실행
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getBtitle());
			pstmt.setString(2, board.getBcontent());
			pstmt.setString(3, board.getBpassword());
			if(board.getBoriginalfilename()!=null){
				pstmt.setString(4, board.getBoriginalfilename());
				pstmt.setString(5, board.getBsavedfilename());
				pstmt.setString(6, board.getBfilecontent());
				pstmt.setInt(7, board.getBno());
			}else{
				pstmt.setInt(4, board.getBno());
			}
			pstmt.executeUpdate();
			pstmt.close();
			LOGGER.info("가져오기 성공");
			
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
	}
	
	@Override
	public void boardDelete(int bno) {

		List<Exam12Board> list = new ArrayList<>();
		try {
			//JDBC Driver 클래스 로딩
			Class.forName("oracle.jdbc.OracleDriver");
			//연결 문자열 작성
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			//연결 객체 얻기
			conn = DriverManager.getConnection(url, "iotuser", "iot12345");
			LOGGER.info("연결성공");
			//매개 변수화된 SQL 작성
			String sql = "delete from board where bno=?";
			
			//SQL 문을 전송해서 실행
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
			pstmt.executeUpdate();
			pstmt.close();
			LOGGER.info("가져오기 성공");
			
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
		
	}
	
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public String memberInsert(Exam12Member m) {
		try {
			//JDBC Driver 클래스 로딩
			Class.forName("oracle.jdbc.OracleDriver");
			//연결 문자열 작성
			String url = "jdbc:oracle:thin:@106.253.56.126:1521:orcl";
			//연결 객체 얻기
			conn = DriverManager.getConnection(url, "user17", "java12345");
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
			PreparedStatement pstmt = conn.prepareStatement(sql);
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
			
			mid = m.getMid();
			pstmt.close();
			LOGGER.info("행 추가 성공");
			//LOGGER.info("추가된 행의 mid: " + mid);
			
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
	
	@Override
	public List<Exam12Member> memberSelectPage(int pageNo, int rowsPerPage) {
		List<Exam12Member> list = new ArrayList<>();
		try {
			//JDBC Driver 클래스 로딩
			Class.forName("oracle.jdbc.OracleDriver");
			//연결 문자열 작성
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			//연결 객체 얻기
			conn = DriverManager.getConnection(url, "iotuser", "iot12345");
			LOGGER.info("연결성공");
			//매개 변수화된 SQL 작성
			String sql = "select * from( ";
			sql+="select rownum as r, mid, mname, mtel, memail, mage, maddress from ( ";
			sql+="select mid, mname, mtel, memail, mage, maddress from member order by mid desc) ";
			sql+="where rownum <= ? ";
			sql+=")where r>= ? ";
			//SQL 문을 전송해서 실행
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pageNo*rowsPerPage);
			pstmt.setInt(2, (pageNo-1)*rowsPerPage+1);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				Exam12Member m = new Exam12Member();
				m.setMid(rs.getString("mid"));
				m.setMname(rs.getString("mname"));
				m.setMtel(rs.getString("mtel"));
				m.setMemail(rs.getString("memail"));
				m.setMage(rs.getInt("mage"));
				m.setMaddress(rs.getString("maddress"));
				list.add(m);
			}
			rs.close();
			pstmt.close();
			LOGGER.info("가져오기 성공");
			
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
		
		return list;
	}
	
	@Override
	public List<Exam12Member> memberSelectPageDif(int pageNo, int rowsPerPage) {
		List<Exam12Member> list = new ArrayList<>();
		try {
			//JDBC Driver 클래스 로딩
			Class.forName("oracle.jdbc.OracleDriver");
			//연결 문자열 작성
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			//연결 객체 얻기
			conn = DriverManager.getConnection(url, "iotuser", "iot12345");
			LOGGER.info("연결성공");
			//매개 변수화된 SQL 작성
			String sql = "select * from( ";
			sql+="select rownum as r, mid, mname, mtel, memail, mage, maddress, msavedfilename from ( ";
			sql+="select mid, mname, mtel, memail, mage, maddress, msavedfilename from member order by mid desc) ";
			sql+="where rownum <= ? ";
			sql+=")where r>= ? ";
			//SQL 문을 전송해서 실행
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pageNo*rowsPerPage);
			pstmt.setInt(2, (pageNo-1)*rowsPerPage+1);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				Exam12Member m = new Exam12Member();
				m.setMid(rs.getString("mid"));
				m.setMname(rs.getString("mname"));
				m.setMtel(rs.getString("mtel"));
				m.setMemail(rs.getString("memail"));
				m.setMage(rs.getInt("mage"));
				m.setMaddress(rs.getString("maddress"));
				m.setMsavedfilename(rs.getString("msavedfilename"));
				list.add(m);
			}
			rs.close();
			pstmt.close();
			LOGGER.info("가져오기 성공");
			
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
		
		return list;
	}

	@Override
	public int memberCountAll() {
		int count = 0;
		List<Exam12Member> list = new ArrayList<>();
		try {
			//JDBC Driver 클래스 로딩
			Class.forName("oracle.jdbc.OracleDriver");
			//연결 문자열 작성
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			//연결 객체 얻기
			conn = DriverManager.getConnection(url, "iotuser", "iot12345");
			LOGGER.info("연결성공");
			//매개 변수화된 SQL 작성
			String sql = "select count(*) from member";
			
			//SQL 문을 전송해서 실행
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			count = rs.getInt(1);
			rs.close();
			pstmt.close();
			LOGGER.info("갯수 가져오기 성공");
			
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
		
		return count;
	}
	@Override
	public Exam12Member memberSelect(String mid) {
		Exam12Member m = null;
		try {
			//JDBC Driver 클래스 로딩
			Class.forName("oracle.jdbc.OracleDriver");
			//연결 문자열 작성
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			//연결 객체 얻기
			conn = DriverManager.getConnection(url, "iotuser", "iot12345");
			LOGGER.info("연결성공");
			//매개 변수화된 SQL 작성
			String sql = "select * from member where mid=?";
			
			//SQL 문을 전송해서 실행
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mid);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()){
				m = new Exam12Member();
				m.setMid(rs.getString("mid"));
				m.setMname(rs.getString("mname"));
				m.setMdate(rs.getDate("mdate"));
				m.setMtel(rs.getString("mtel"));
				m.setMemail(rs.getString("memail"));
				m.setMage(rs.getInt("mage"));
				m.setMaddress(rs.getString("maddress"));
				m.setMpassword(rs.getString("mpassword"));
				m.setMoriginalfilename(rs.getString("moriginalfilename"));
				m.setMsavedfilename(rs.getString("msavedfilename"));
				m.setMfilecontent(rs.getString("mfilecontent"));
			}
			rs.close();
			pstmt.close();
			LOGGER.info("멤버 선택 성공");
			
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
		return m;
	}
	
	@Override
	public void memberupdate(Exam12Member m) {
		try {
			//JDBC Driver 클래스 로딩
			Class.forName("oracle.jdbc.OracleDriver");
			//연결 문자열 작성
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			//연결 객체 얻기
			conn = DriverManager.getConnection(url, "iotuser", "iot12345");
			LOGGER.info("연결성공");
			//매개 변수화된 SQL 작성
			String sql = "update member set mname=?, mpassword=?, mdate=sysdate, mtel=?, memail=?, mage=?, ";
			if(m.getMoriginalfilename()!=null){
				sql +="maddress=?, ";
				sql +="moriginalfilename=?, msavedfilename=?, mfilecontent=? ";
				sql +="where mid=?";
			}else{
				sql +="maddress=? ";
				sql +="where mid=?";
			}
			//SQL 문을 전송해서 실행
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m.getMname());
			pstmt.setString(2, m.getMpassword());
			pstmt.setString(3, m.getMtel());
			pstmt.setString(4, m.getMemail());
			pstmt.setInt(5, m.getMage());
			pstmt.setString(6, m.getMaddress());
			if(m.getMoriginalfilename()!=null){
				pstmt.setString(7, m.getMoriginalfilename());
				pstmt.setString(8, m.getMsavedfilename());
				pstmt.setString(9, m.getMfilecontent());
				pstmt.setString(10, m.getMid());
			}else{
				pstmt.setString(7, m.getMid());
			}
			pstmt.executeUpdate();
			pstmt.close();
			LOGGER.info("update 성공");
			
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
	}
	@Override
	public void memberWithdraw(String mid) {
		try {
			//JDBC Driver 클래스 로딩
			Class.forName("oracle.jdbc.OracleDriver");
			//연결 문자열 작성
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			//연결 객체 얻기
			conn = DriverManager.getConnection(url, "iotuser", "iot12345");
			LOGGER.info("연결성공");
			//매개 변수화된 SQL 작성
			String sql = "delete from member where mid=?";
			
			//SQL 문을 전송해서 실행
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mid);
			pstmt.executeUpdate();
			pstmt.close();
			LOGGER.info("삭제 성공");
			
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
	}
	
	public static void main(String[] args){
		Exam12DaoImpl test = new Exam12DaoImpl();
//		for(int i = 1 ;i<=100; i++){
//			Exam12Member m = new Exam12Member();
//			m.setMid("iot"+i);
//			m.setMname("학생"+i);
//			m.setMpassword("00"+i);
//			m.setMemail("kim"+i+(int)(Math.random()*10)+""+(int)(Math.random()*10)+"@naver.com");
//			m.setMtel("02-"+(int)(Math.random()*10)+""+(int)(Math.random()*10)+""+(int)(Math.random()*10)+"-"+(int)(Math.random()*10)+""+(int)(Math.random()*10)+""+(int)(Math.random()*10)+""+(int)(Math.random()*10));
//			m.setMage((6/i)+(int)(Math.random()*50+1));
//			m.setMaddress("서울특별시 송파구 가락"+(int)(Math.random()*10+1)+"동 "+(int)(Math.random()*300));
//			m.setMoriginalfilename(i+".jpg");
//			m.setMsavedfilename("00-"+i+".jpg");
//			m.setMfilecontent("image/jpeg");
//			test.memberInsert(m);
//		}
		for(int j=201 ; j<=300; j++){
			Exam12Board b = new Exam12Board();
			b.setBno(j);
			b.setBtitle("제목"+j);
			b.setBcontent("내용"+j);
			b.setBwriter("홍길동");
			b.setBpassword("1234");
			b.setBhitcount(0);
			b.setBoriginalfilename("a.png");
			b.setBsavedfilename("00-a.png");
			b.setBfilecontent("image/png");
			test.boardInsert(b);
		}
		LOGGER.info("성공");		
	}
	
}
