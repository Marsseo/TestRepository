package com.mycompany.myapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import com.mycompany.myapp.dto.Exam12Board;
import com.mycompany.myapp.dto.Exam12Member;

@Component
public class Exam12DaoImpl2 implements Exam12Dao{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Exam12DaoImpl2.class);
			
			String mid = null;
			Connection conn = null;
			
						
			@Autowired
			private JdbcTemplate jdbcTemplate;
			
			
	@Override
	public int boardInsert(Exam12Board board) {
		
			
		
		LOGGER.info("연결성공");
		//매개 변수화된 SQL 작성
		final String sql = "insert into board "
		+"(BNO,BTITLE, bcontent, bwriter, bdate, bpassword, bhitcount, boriginalfilename, bsavedfilename, bfilecontent) "
		+"values "
		+"(board_bno_seq.nextval, ?, ?, ?, sysdate, ?, 0, ?, ?, ?) ";
		//SQL 문을 전송해서 실행
		
	
		PreparedStatementCreator psc = new PreparedStatementCreator(){

			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
				PreparedStatement pstmt = conn.prepareStatement(sql, new String[] {"bno"});
				pstmt.setString(1, board.getBtitle());
				pstmt.setString(2, board.getBcontent());
				pstmt.setString(3, board.getBwriter());
				pstmt.setString(4, board.getBpassword());
				pstmt.setString(5, board.getBoriginalfilename());
				pstmt.setString(6, board.getBsavedfilename());
				pstmt.setString(7, board.getBfilecontent());
				return pstmt;
			}
			
		};
		LOGGER.info("삽입성공");
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		jdbcTemplate.update(psc, keyHolder);
		int bno = keyHolder.getKey().intValue();
		
		LOGGER.info("bno: "+bno);
		return bno;		
	}

	@Override
	public List<Exam12Board> boardSelectAll() {
		List<Exam12Board> list = null;
		
			
			
			//매개 변수화된 SQL 작성
			String sql = "select bno, btitle, bwriter, bdate, bhitcount "
			+"from board "
			+"order by bno asc";
			//SQL 문을 전송해서 실행
			
			RowMapper <Exam12Board> rowMapper = new RowMapper<Exam12Board>(){

				@Override
				public Exam12Board mapRow(ResultSet rs, int rowNum) throws SQLException {
					Exam12Board b = new Exam12Board();
					b.setBno(rs.getInt("bno"));
					b.setBtitle(rs.getString("btitle"));
					b.setBwriter(rs.getString("bwriter"));
					b.setBdate(rs.getDate("bdate"));
					b.setBhitcount(rs.getInt("bhitcount"));
					return b;
				}
				
			};
			LOGGER.info("가져오기 성공");
			list = jdbcTemplate.query(sql, rowMapper);
			
			LOGGER.info("연결끊기");
		
		return list;
	}



	@Override
	public List<Exam12Board> boardSelectPage(int pageNo, int rowsPerPage) {
		List<Exam12Board> list = new ArrayList<>();
		
			LOGGER.info("연결성공");
			//매개 변수화된 SQL 작성
			String sql = "select * from("
			+"select rownum as r, bno, btitle, bwriter, bdate, bhitcount from ("
			+"select bno, btitle, bwriter, bdate, bhitcount from board order by bno desc) "
			+"where rownum <= ?"
			+")where r>= ? ";
			//SQL 문을 전송해서 실행
						
			Object[] args = {(pageNo*rowsPerPage), ((pageNo-1)*rowsPerPage+1)};
			
			RowMapper <Exam12Board> rowMapper = new RowMapper<Exam12Board>(){

				@Override
				public Exam12Board mapRow(ResultSet rs, int rowNum) throws SQLException {
					Exam12Board b = new Exam12Board();
					b.setBno(rs.getInt("bno"));
					b.setBtitle(rs.getString("btitle"));
					b.setBwriter(rs.getString("bwriter"));
					b.setBdate(rs.getDate("bdate"));
					b.setBhitcount(rs.getInt("bhitcount"));
					return b;
				}
				
			};
			LOGGER.info("가져오기 성공");
			
			list = jdbcTemplate.query(sql, args, rowMapper);
		
			LOGGER.info("연결끊기");
			
		
		return list;
	}
	@Override
	public int boardCountAll() {
		int count = 0;
		List<Exam12Board> list = new ArrayList<>();
		
			LOGGER.info("연결성공");
			//매개 변수화된 SQL 작성
			String sql = "select count(*) from board";
			
			//SQL 문을 전송해서 실행
			
			count = jdbcTemplate.queryForObject(sql, Integer.class);
			
			LOGGER.info("전체 갯수 가져오기 성공");
			
		
		return count;
	}
	@Override
	public Exam12Board boardselectByBno(int bno) {
		
		
			LOGGER.info("연결성공");
			//매개 변수화된 SQL 작성
			String sql = "select * from board where bno=?";
						
			//SQL 문을 전송해서 실행
			RowMapper <Exam12Board> rowMapper = new RowMapper<Exam12Board>(){

				@Override
				public Exam12Board mapRow(ResultSet rs, int rowNum) throws SQLException {
					Exam12Board board = new Exam12Board();
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
					return board;
				}
				
			};
			
			Exam12Board board = jdbcTemplate.queryForObject(sql, rowMapper, bno);
			
			LOGGER.info("가져오기 성공");
					
		return board;
	}
	@Override
	public void boardUpdateBhitcount(int bno, int bhitcount) {
		
		
			LOGGER.info("연결성공");
			//매개 변수화된 SQL 작성
			String sql = "update board set bhitcount=? where bno=?";
			jdbcTemplate.update(sql, bhitcount, bno);
			LOGGER.info("가져오기 성공");
						
	}
	@Override
	public void boardUpdate(Exam12Board board) {
		
			LOGGER.info("연결성공");
			//매개 변수화된 SQL 작성
			String sql;
			if(board.getBoriginalfilename()!=null){
				sql = "update board set btitle=?, bcontent=?, bpassword=?, "
				+"bdate=sysdate, boriginalfilename=?, bsavedfilename=?, bfilecontent=? "
				+"where bno=?";
				jdbcTemplate.update(sql, board.getBtitle(), board.getBcontent(), board.getBpassword(),board.getBoriginalfilename(), board.getBsavedfilename(), board.getBfilecontent(), board.getBno());
			}else{
				sql = "update board set btitle=?, bcontent=?, bpassword=?, "
				+"bdate=sysdate "
				+"where bno=?";
				jdbcTemplate.update(sql, board.getBtitle(), board.getBcontent(), board.getBpassword(), board.getBno());
			}
			
			LOGGER.info("가져오기 성공");
			
		
	}
	
	@Override
	public void boardDelete(int bno) {

		List<Exam12Board> list = new ArrayList<>();
		
			LOGGER.info("진입 성공");
			//매개 변수화된 SQL 작성
			String sql = "delete from board where bno=?";
			
			//SQL 문을 전송해서 실행
			jdbcTemplate.update(sql, bno);
			LOGGER.info("삭제 성공");
			
		
		
	}
	
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public String memberInsert(Exam12Member m) {
		
			LOGGER.info("연결성공");
			//매개 변수화된 SQL 작성
			final String sql = "insert into member "
			+"(mid, mname, mpassword, mdate, mtel, memail, mage, maddress, moriginalfilename, msavedfilename, mfilecontent) "
			+"values "
			+"(?, ?, ?, sysdate, ?, ?, ?, ?, ?, ?, ?) ";
			//SQL 문을 전송해서 실행
			PreparedStatementCreator psc = new PreparedStatementCreator(){

				@Override
				public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
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
					return pstmt;
				}
				
			};
			LOGGER.info("삽입성공");
			
			KeyHolder keyHolder = new GeneratedKeyHolder();
			
			jdbcTemplate.update(psc, keyHolder);
			mid = keyHolder.getKey().toString();
			
			LOGGER.info("mid: "+mid);
			
			LOGGER.info("행 추가 성공");			
		
		return mid;
	}
	
	@Override
	public List<Exam12Member> memberSelectPage(int pageNo, int rowsPerPage) {
		List<Exam12Member> list = new ArrayList<>();
		
			LOGGER.info("연결성공");
			//매개 변수화된 SQL 작성
			String sql = "select * from( ";
			sql+="select rownum as r, mid, mname, mtel, memail, mage, maddress from ( ";
			sql+="select mid, mname, mtel, memail, mage, maddress from member order by mid desc) ";
			sql+="where rownum <= ? ";
			sql+=")where r>= ? ";
			//SQL 문을 전송해서 실행
			
			Object[] args = {(pageNo*rowsPerPage), ((pageNo-1)*rowsPerPage+1)};
			
			RowMapper <Exam12Member> rowMapper = new RowMapper<Exam12Member>(){

				@Override
				public Exam12Member mapRow(ResultSet rs, int rowNum) throws SQLException {
					Exam12Member m = new Exam12Member();
					m.setMid(rs.getString("mid"));
					m.setMname(rs.getString("mname"));
					m.setMtel(rs.getString("mtel"));
					m.setMemail(rs.getString("memail"));
					m.setMage(rs.getInt("mage"));
					m.setMaddress(rs.getString("maddress"));
					return m;
				}
				
			};
			
			list = jdbcTemplate.query(sql, args, rowMapper);
			
			LOGGER.info("가져오기 성공");
		
		return list;
	}
	
	@Override
	public List<Exam12Member> memberSelectPageDif(int pageNo, int rowsPerPage) {
		List<Exam12Member> list = new ArrayList<>();
		
			LOGGER.info("연결성공");
			//매개 변수화된 SQL 작성
			String sql = "select * from( ";
			sql+="select rownum as r, mid, mname, mtel, memail, mage, maddress, msavedfilename from ( ";
			sql+="select mid, mname, mtel, memail, mage, maddress, msavedfilename from member order by mid desc) ";
			sql+="where rownum <= ? ";
			sql+=")where r>= ? ";
			//SQL 문을 전송해서 실행
			Object[] args = {(pageNo*rowsPerPage), ((pageNo-1)*rowsPerPage+1)};
			
			RowMapper <Exam12Member> rowMapper = new RowMapper<Exam12Member>(){

				@Override
				public Exam12Member mapRow(ResultSet rs, int rowNum) throws SQLException {
					Exam12Member m = new Exam12Member();
					m.setMid(rs.getString("mid"));
					m.setMname(rs.getString("mname"));
					m.setMtel(rs.getString("mtel"));
					m.setMemail(rs.getString("memail"));
					m.setMage(rs.getInt("mage"));
					m.setMaddress(rs.getString("maddress"));
					m.setMsavedfilename(rs.getString("msavedfilename"));
					return m;
				}
				
			};
			
			list = jdbcTemplate.query(sql, args, rowMapper);
			
			LOGGER.info("가져오기 성공");
			
				
		return list;
	}

	@Override
	public int memberCountAll() {
		int count = 0;
		List<Exam12Member> list = new ArrayList<>();
		
			LOGGER.info("연결성공");
			//매개 변수화된 SQL 작성
			String sql = "select count(*) from member";
			
			//SQL 문을 전송해서 실행
			count = jdbcTemplate.queryForObject(sql, Integer.class);
			LOGGER.info("갯수 가져오기 성공");
			
		
		
		return count;
	}
	@Override
	public Exam12Member memberSelect(String mid) {
		Exam12Member m = null;
		
			LOGGER.info("연결성공");
			//매개 변수화된 SQL 작성
			String sql = "select * from member where mid=?";
			
			//SQL 문을 전송해서 실행
			
			RowMapper <Exam12Member> rowMapper = new RowMapper<Exam12Member>(){

				@Override
				public Exam12Member mapRow(ResultSet rs, int rowNum) throws SQLException {
					Exam12Member m = new Exam12Member();
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
					return m;
				}
				
			};
			m = jdbcTemplate.queryForObject(sql, rowMapper, mid);
			LOGGER.info("멤버 선택 성공");
			
		
		return m;
	}
	
	@Override
	public void memberupdate(Exam12Member m) {
		
			LOGGER.info("연결성공");
			//매개 변수화된 SQL 작성
			String sql;
			if(m.getMoriginalfilename()!=null){
				sql = "update member set mname=?, mpassword=?, mdate=sysdate, mtel=?, memail=?, mage=?, ";
				sql +="maddress=?, ";
				sql +="moriginalfilename=?, msavedfilename=?, mfilecontent=? ";
				sql +="where mid=?";
				jdbcTemplate.update(sql, m.getMname(), m.getMpassword(), m.getMtel(), m.getMemail(), m.getMaddress(), m.getMoriginalfilename(), m.getMsavedfilename(), m.getMfilecontent(), m.getMid());
			}else{
				sql = "update member set mname=?, mpassword=?, mdate=sysdate, mtel=?, memail=?, mage=?, ";
				sql +="maddress=? ";
				sql +="where mid=?";
				jdbcTemplate.update(sql, m.getMname(), m.getMpassword(), m.getMtel(), m.getMemail(), m.getMaddress(), m.getMid());
			}
			
			LOGGER.info("update 성공");
			
		
	}
	@Override
	public void memberWithdraw(String mid) {
		
			LOGGER.info("연결성공");
			//매개 변수화된 SQL 작성
			String sql = "delete from member where mid=?";
			
			//SQL 문을 전송해서 실행
			jdbcTemplate.update(sql, mid);
			LOGGER.info("삭제 성공");
			
		
	}
	
//	public static void main(String[] args){
//		Exam12DaoImpl2 test = new Exam12DaoImpl2();
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
//		for(int j=1 ; j<=100; j++){
//			Exam12Board b = new Exam12Board();
//			b.setBno(j);
//			b.setBtitle("제목"+j);
//			b.setBcontent("내용"+j);
//			b.setBwriter("홍길동");
//			b.setBpassword("1234");
//			b.setBhitcount(0);
//			b.setBoriginalfilename("a.png");
//			b.setBsavedfilename("00-a.png");
//			b.setBfilecontent("image/png");
//			test.boardInsert(b);
//		}
//		LOGGER.info("성공");		
//	}
	
}
