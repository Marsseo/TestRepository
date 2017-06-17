package com.mycompany.myapp.service;

import org.springframework.stereotype.Component;

import com.mycompany.myapp.dto.Exam15Account;

@Component
public interface Exam15Service {
	
	public Exam15Account getAccount(String ano);
	public void deposit(String ano, int amount);
	public void withdraw(String ano, int amount);
	public void transfer(String fromAno, String toAno, int amount);
}
