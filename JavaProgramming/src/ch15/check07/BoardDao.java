package ch15.check07;

import java.util.*;

public class BoardDao {
	
	
	public static List getBoardList(){
		List<Board> list = new ArrayList<>();
		
		list.add(new Board("����1", "����1"));
		list.add(new Board("����2", "����2"));
		list.add(new Board("����3", "����3"));
		
		return list;
	}
}
