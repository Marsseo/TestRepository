package com.mycompany.myapp.dao;

import org.springframework.stereotype.Component;

import com.mycompany.myapp.dto.Exam15Account;

@Component
public interface Exam15Dao {
	
	
	public Exam15Account selectByAno(String ano);
	public int update(Exam15Account account);
}
