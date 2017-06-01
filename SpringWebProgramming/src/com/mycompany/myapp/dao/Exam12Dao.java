package com.mycompany.myapp.dao;

import java.util.List;

import com.mycompany.myapp.dto.Exam12Board;
import com.mycompany.myapp.dto.Exam12Member;

public interface Exam12Dao {
	public int boardInsert(Exam12Board board);
	public List<Exam12Board> boardSelectAll();
	public List<Exam12Board> boardSelectPage(int pageNo, int rowsPerPage);
	public int boardCountAll();
	public Exam12Board boardselectByBno(int bno);
	public void boardUpdateBhitcount(int bno, int bhitcount);
	public void boardUpdate(Exam12Board board);
	public void boardDelete(int bno);
	
	////////////////////////////////////////////////////////////////////////////
	public String memberInsert(Exam12Member member);
	public List<Exam12Member> memberSelectPage(int pageNo, int rowsPerPage);
	public int memberCountAll();
	public Exam12Member memberSelect(String mid);
	public void memberupdate(Exam12Member m);
	public void memberWithdraw(String mid);
	
}
