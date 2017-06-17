package com.mycompany.myapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.mycompany.myapp.dao.Exam15Dao;
import com.mycompany.myapp.dto.Exam15Account;
import com.mycompany.myapp.exception.NoAccountException;

@Component
@Transactional
public class Exam15ServiceImpl implements Exam15Service{
	
	@Autowired
	private Exam15Dao dao;
	
	@Override
	public Exam15Account getAccount(String ano) {
				
		return dao.selectByAno(ano);
	}

	@Override
	public void deposit(String ano, int amount) {
		Exam15Account account = dao.selectByAno(ano);
		account.setAbalance(account.getAbalance()+amount);
		dao.update(account);
	}

	@Override
	public void withdraw(String ano, int amount) {
		Exam15Account account = dao.selectByAno(ano);
		account.setAbalance(account.getAbalance()-amount);
		dao.update(account);	
		
	}

	@Override
	public void transfer(String fromAno, String toAno, int amount) throws NoAccountException{
				
		Exam15Account fromAccount = dao.selectByAno(fromAno);
		Exam15Account toAccount = dao.selectByAno(toAno);
		
		if(fromAccount!=null && toAccount!=null){
			
			fromAccount.setAbalance(fromAccount.getAbalance()-amount);
			toAccount.setAbalance(toAccount.getAbalance()+amount);
			
			dao.update(fromAccount);
			dao.update(toAccount);
			
		}else{
			if(fromAccount==null) throw new NoAccountException("출금계좌가 존재하지 않습니다.");
			else throw new NoAccountException("입금계좌가 존재하지 않습니다.");
		}		
		
	}
	
}
