package com.mycompany.myapp.controller;


import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mycompany.myapp.dto.Exam12Board;
import com.mycompany.myapp.dto.Exam12Member;
import com.mycompany.myapp.service.Exam12Service;

@Controller
public class Exam12jdbcController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Exam12jdbcController.class);
	
	@Autowired
	private Exam12Service service;
	
	@Autowired
	private ServletContext servletContext;
	
	@RequestMapping("/jdbc/exam01")
	public String exam01(){
		Exam12Board b = new Exam12Board();
		b.setBtitle("이번에는");
		b.setBcontent("서비스로다!");
		b.setBwriter("홍길동");
		b.setBpassword("1234");
		b.setBoriginalfilename("a.png");
		b.setBsavedfilename("a.png");
		b.setBfilecontent("image/png");
		service.write(b);
		return "redirect:/";
	}
	@RequestMapping(value="/jdbc/exam02", method = RequestMethod.GET)
	public String exam02Get(){
		return "jdbc/exam02";
	}
	@RequestMapping(value="/jdbc/exam02", method = RequestMethod.POST)
	public String exam02Post(Exam12Board b) throws IllegalStateException, IOException{
		
		//첨부 파일에 대한 정보를 컬럼값으로 설정 (필드에 값을 세팅한다)
		b.setBoriginalfilename(b.getBattach().getOriginalFilename());
		b.setBfilecontent(b.getBattach().getContentType());
		String fileName = new Date().getTime()+"-"+b.getBoriginalfilename();
		b.setBsavedfilename(fileName);
		//서비스 객체에 요청처리 요청
		service.write(b);
		// 첨부 파일을 서버 로컬 시스템에 저장
		String realPath = servletContext.getRealPath("/WEB-INF/upload/");	
		File file = new File(realPath+fileName);
		b.getBattach().transferTo(file);		
		
		return "redirect:/";
	}
	
	
	@RequestMapping(value="/jdbc/exam03", method = RequestMethod.GET)
	public String exam03Get(){
		return "jdbc/exam03";
	}
	@RequestMapping(value="/jdbc/exam03", method = RequestMethod.POST)
	public String exam03Post(Exam12Member m) throws IllegalStateException, IOException{
		
		//첨부 파일에 대한 정보를 컬럼값으로 설정 (필드에 값을 세팅한다)
		m.setMoriginalfilename(m.getMattach().getOriginalFilename());
		m.setMfilecontent(m.getMattach().getContentType());
		String fileName = new Date().getTime()+"-"+m.getMoriginalfilename();
		m.setMsavedfilename(fileName);
		//서비스 객체에 요청처리 요청
		service.register(m);
		// 첨부 파일을 서버 로컬 시스템에 저장
		String realPath = servletContext.getRealPath("/WEB-INF/upload/");	
		File file = new File(realPath+fileName);
		m.getMattach().transferTo(file);		
		
		return "redirect:/";
	}
}
