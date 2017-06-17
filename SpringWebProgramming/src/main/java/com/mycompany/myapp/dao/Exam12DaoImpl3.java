package com.mycompany.myapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class Exam12DaoImpl3 implements Exam12Dao{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Exam12DaoImpl3.class);
			
			String mid = null;
			Connection conn = null;
			
						
			@Autowired
			private JdbcTemplate jdbcTemplate;
			
			@Autowired
			private SqlSessionTemplate sqlSessionTemplate;	
			
	@Override
	public int boardInsert(Exam12Board board) {
		
		//sql문 mybatis에 있음
		sqlSessionTemplate.insert("board.insert", board);
		LOGGER.info("삽입성공");
		
		return board.getBno();		
	}

	@Override
	public List<Exam12Board> boardSelectAll() {
		List<Exam12Board> list = null;
		
			
			list = sqlSessionTemplate.selectList("board.selectAll");
			
			LOGGER.info("가져오기 성공");
		
		return list;
	}



	@Override
	public List<Exam12Board> boardSelectPage(int pageNo, int rowsPerPage) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("startNum", (pageNo-1)*rowsPerPage+1);
		map.put("endNum", pageNo*rowsPerPage);
		
		List<Exam12Board> list = sqlSessionTemplate.selectList("board.selectByPage", map);			
		
		return list;
	}
	@Override
	public int boardCountAll() {
		
		int count = sqlSessionTemplate.selectOne("board.countAll");
				
		return count;
	}
	@Override
	public Exam12Board boardselectByBno(int bno) {
		
		
		Exam12Board board = sqlSessionTemplate.selectOne("board.selectByBno", bno);
			
		LOGGER.info("가져오기 성공");
					
		return board;
	}
	@Override
	public void boardUpdateBhitcount(int bno, int bhitcount) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bno", bno);
		map.put("bhitcount", bhitcount);
		
		int a = sqlSessionTemplate.update("board.updateBhitcount", map);
		LOGGER.info("조회수 성공 ");
						
	}
	@Override
	public void boardUpdate(Exam12Board board) {
		
		sqlSessionTemplate.update("board.update", board);
			
		LOGGER.info("업데이트 성공");
				
	}
	
	@Override
	public void boardDelete(int bno) {

		sqlSessionTemplate.delete("board.delete", bno);
		LOGGER.info("삭제 성공");
				
	}
	
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public String memberInsert(Exam12Member m) {
		
			
			sqlSessionTemplate.insert("member.insert", m);
			LOGGER.info("행 추가 성공");			
		
		return m.getMid();
	}
	
	@Override
	public List<Exam12Member> memberSelectPage(int pageNo, int rowsPerPage) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("startNum", (pageNo-1)*rowsPerPage+1);
		map.put("endNum", pageNo*rowsPerPage);
				
		List<Exam12Member> list = sqlSessionTemplate.selectList("member.selectByPage", map);
							
		LOGGER.info("가져오기 성공");
		
		return list;
	}
	
	@Override
	public List<Exam12Member> memberSelectPageDif(int pageNo, int rowsPerPage) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("startNum", (pageNo-1)*rowsPerPage+1);
		map.put("endNum", pageNo*rowsPerPage);
				
		List<Exam12Member> list = sqlSessionTemplate.selectList("member.selectByPage", map);
							
		LOGGER.info("가져오기 성공");
			
				
		return list;
	}

	@Override
	public int memberCountAll() {
		
		
		int count = sqlSessionTemplate.selectOne("member.countAll");
		LOGGER.info("갯수 가져오기 성공");				
		
		return count;
	}
	@Override
	public Exam12Member memberSelect(String mid) {
		
		Exam12Member m = sqlSessionTemplate.selectOne("member.selectByMid", mid);
		LOGGER.info("멤버 선택 성공");
					
		return m;
	}
	
	@Override
	public void memberupdate(Exam12Member m) {
		
		sqlSessionTemplate.update("member.update", m);			
			LOGGER.info("update 성공");
			
		
	}
	@Override
	public void memberWithdraw(String mid) {
		
		sqlSessionTemplate.delete("member.delete", mid);
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
