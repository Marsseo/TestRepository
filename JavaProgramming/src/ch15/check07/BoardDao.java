package ch15.check07;

import java.util.*;

public class BoardDao {
	
	
	public static List getBoardList(){
		List<Board> list = new ArrayList<>();
		
		list.add(new Board("力格1", "郴侩1"));
		list.add(new Board("力格2", "郴侩2"));
		list.add(new Board("力格3", "郴侩3"));
		
		return list;
	}
}
