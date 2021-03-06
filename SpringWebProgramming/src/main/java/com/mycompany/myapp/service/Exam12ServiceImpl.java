package com.mycompany.myapp.service;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.mycompany.myapp.dao.Exam12Dao;
import com.mycompany.myapp.dto.Exam12Board;
import com.mycompany.myapp.dto.Exam12Member;

@Component
public class Exam12ServiceImpl implements Exam12Service{
	private static final Logger LOGGER = LoggerFactory.getLogger(Exam12ServiceImpl.class);
	
	@Resource(name="exam12DaoImpl")
	private Exam12Dao dao;
	
	@Override
	public void boardWrite(Exam12Board board) {
		
		dao.boardInsert(board);
		
	}
		
	@Override
	public List<Exam12Board> boardListAll() {
		List<Exam12Board> list = dao.boardSelectAll();
		return list;
	}
	@Override
	public List<Exam12Board> boardListPage(int pageNo,int rowsPerPage) {
		List<Exam12Board> list = dao.boardSelectPage(pageNo, rowsPerPage);
		return list;
	}
	@Override
	public int boardTotalRows() {
		int totalRows = dao.boardCountAll();
		return totalRows;
	}
	@Override
	public Exam12Board getBoard(int bno) {
		Exam12Board board = dao.boardselectByBno(bno);
		board.setBhitcount(board.getBhitcount()+1);
		dao.boardUpdateBhitcount(bno, board.getBhitcount());
		return board;
	}
	@Override
	public String boardCheckBpassword(int bno, String bpassword) {
		String result = "fail";
		Exam12Board b = dao.boardselectByBno(bno);
		if(b.getBpassword().equals(bpassword)){
			result="success";
		}
		return result;
	}
	@Override
	public void boardUpdate(Exam12Board board) {
		dao.boardUpdate(board);
	}
	
	@Override
	public void boardDelete(int bno) {
		dao.boardDelete(bno);		
	}
	
	
////////////////////////////////////////////////////////////////////////
	
	@Override
	public void memberJoin(Exam12Member member) {
		dao.memberInsert(member);
		
	}

	@Override
	public List<Exam12Member> memberListPage(int pageNo, int rowsPerPage) {
		List<Exam12Member> list = dao.memberSelectPage(pageNo, rowsPerPage);
		return list;
	}
	
	@Override
	public List<Exam12Member> memberListPageDif(int pageNo, int rowsPerPage) {
		List<Exam12Member> list = dao.memberSelectPageDif(pageNo, rowsPerPage);
		return list;
	}

	@Override
	public int memberTotalRows() {
		int totalRows = dao.memberCountAll();
		return totalRows;
	}
	
	@Override
	public Exam12Member getMember(String mid) {
		Exam12Member m = dao.memberSelect(mid);
		return m;
	}
	
	@Override
	public void memberUpdate(Exam12Member m) {
		dao.memberupdate(m);		
	}
	
	@Override
	public String memberCheckMpassword(String mid, String mpassword) {
		String result = "fail";
		Exam12Member m = dao.memberSelect(mid);
		LOGGER.info(mpassword);
		LOGGER.info(m.getMpassword());
		if(m.getMpassword().equals(mpassword)){
			result="success";
		}
		return result;
	}
	
	@Override
	public void memberWidraw(String mid) {
		dao.memberWithdraw(mid);		
	}
	
}
