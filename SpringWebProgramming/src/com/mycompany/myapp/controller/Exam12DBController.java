package com.mycompany.myapp.controller;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mycompany.myapp.dto.Exam12Board;
import com.mycompany.myapp.dto.Exam12Member;
import com.mycompany.myapp.service.Exam12Service;

@Controller
public class Exam12DBController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Exam12DBController.class);
	
	@Resource(name="exam12ServiceImpl3")
	private Exam12Service service;
	
/*	@Resource(name="exam12ServiceImpl")
	private Exam12Service service2;
	
	@Resource(name="exam12ServiceImpl2")
	private Exam12Service service3;*/
	
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
		service.boardWrite(b);
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

		// 첨부 파일을 서버 로컬 시스템에 저장
		String realPath = servletContext.getRealPath("/WEB-INF/upload/");	
		File file = new File(realPath+fileName);
		b.getBattach().transferTo(file);
		
		//서비스 객체에 요청처리 요청
		service.boardWrite(b);
		
		return "redirect:/jdbc/exam05";
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
		
		// 첨부 파일을 서버 로컬 시스템에 저장
		String realPath = servletContext.getRealPath("/WEB-INF/upload/");	
		File file = new File(realPath+fileName);
		m.getMattach().transferTo(file);
		
		//서비스 객체에 요청처리 요청
		service.memberJoin(m);
		
		return "redirect:/jdbc/exam06";
	}
	
	@RequestMapping("/jdbc/exam04")
	public String exam04(Model model){
		List<Exam12Board> list = service.boardListAll();
		model.addAttribute("list", list);
		return "/jdbc/exam04";
	}
	
	@RequestMapping("/jdbc/exam05")
	public String exam05(@RequestParam(defaultValue = "1") int pageNo, Model model){
		//한 페이지를 구성하는
		int rowsPerPage = 10;
		//한 그룹을 구성하는 페이지 수
		int pagesPerGroup = 7;
		//총 행수
		int totalRows = service.boardTotalRows();
		//전체 페이지 수
		int totalPageNo = totalRows/rowsPerPage + ((totalRows%rowsPerPage!=0)? 1: 0);
		//전체 그룹 수
		int totalGroupNo = totalPageNo/pagesPerGroup + ((totalPageNo%pagesPerGroup!=0)? 1: 0);
		//현재 그룹 번호
		int groupNo = (pageNo-1)/pagesPerGroup + 1;
		//현재 그룹의 시작 페이지 번호
		int startPageNo = (groupNo-1)*pagesPerGroup + 1;
		//현재 그룹의 마지막 페이지 번호
		int endPageNo = startPageNo+pagesPerGroup - 1;
		if(groupNo == totalGroupNo) endPageNo = totalPageNo;
		
		//현재 페이지의 행의 데이터 가져오기
		List<Exam12Board> list = service.boardListPage(pageNo, rowsPerPage);
		
		//View로 넘겨줄 데이터
		model.addAttribute("list", list);
		model.addAttribute("totalPageNo", totalPageNo);
		model.addAttribute("totalGroupNo", totalGroupNo);
		model.addAttribute("groupNo", groupNo);
		model.addAttribute("startPageNo", startPageNo);
		model.addAttribute("endPageNo", endPageNo);
		model.addAttribute("pagesPerGroup", pagesPerGroup);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("rowsPerPage", rowsPerPage);
		
		return "/jdbc/exam05";
	}
	@RequestMapping("/jdbc/exam05Detail")
	public String exam05Detail(int bno, Model model){
		Exam12Board b = service.getBoard(bno);
		model.addAttribute("b", b);
		return "/jdbc/exam05Detail";
	}
	
	@RequestMapping("/jdbc/exam05CheckBpassword")
	public String exam05CheckBpassword(int bno, String bpassword, Model model){
		String result = service.boardCheckBpassword(bno, bpassword);
		model.addAttribute("result", result);
		return "/jdbc/exam05CheckBpassword";
	}
	
	@RequestMapping(value="/jdbc/exam05Update", method=RequestMethod.GET)
	public String exam05UpdateGet(int bno, Model model){
		Exam12Board b = service.getBoard(bno);
		model.addAttribute("b", b);
		return "/jdbc/exam05Update";
	}
	
	@RequestMapping(value="/jdbc/exam05Update", method=RequestMethod.POST)
	public String exam05UpdatePost(Exam12Board b) throws IllegalStateException, IOException{
		if(!b.getBattach().isEmpty()){
			//첨부 파일에 대한 정보를 컬럼값으로 설정 (필드에 값을 세팅한다)
			b.setBoriginalfilename(b.getBattach().getOriginalFilename());
			b.setBfilecontent(b.getBattach().getContentType());
			String fileName = new Date().getTime()+"-"+b.getBoriginalfilename();
			b.setBsavedfilename(fileName);
			//서비스 객체에 요청처리 요청
			// 첨부 파일을 서버 로컬 시스템에 저장
			String realPath = servletContext.getRealPath("/WEB-INF/upload/");
			File file = new File(realPath+fileName);
			b.getBattach().transferTo(file);
		}
		service.boardUpdate(b);
		return "redirect:/jdbc/exam05Detail?bno="+b.getBno();
	}
	
	@RequestMapping("/jdbc/exam05Delete")
	public String exam05Delte(int bno){
		service.boardDelete(bno);
		return "redirect:/jdbc/exam05";
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@RequestMapping("/jdbc/exam06")
	public String exam06(@RequestParam(defaultValue = "1") int pageNo, Model model){
		//한 페이지를 구성하는
		int rowsPerPage = 9;
		//한 그룹을 구성하는 페이지 수
		int pagesPerGroup = 5;
		//총 행수
		int totalRows = service.memberTotalRows();
		//전체 페이지 수
		int totalPageNo = totalRows/rowsPerPage + ((totalRows%rowsPerPage!=0)? 1: 0);
		//전체 그룹 수
		int totalGroupNo = totalPageNo/pagesPerGroup + ((totalPageNo%pagesPerGroup!=0)? 1: 0);
		//현재 그룹 번호
		int groupNo = (pageNo-1)/pagesPerGroup + 1;
		//현재 그룹의 시작 페이지 번호
		int startPageNo = (groupNo-1)*pagesPerGroup + 1;
		//현재 그룹의 마지막 페이지 번호
		int endPageNo = startPageNo+pagesPerGroup - 1;
		if(groupNo == totalGroupNo) endPageNo = totalPageNo;
		
		//현재 페이지의 행의 데이터 가져오기
		List<Exam12Member> list = service.memberListPage(pageNo, rowsPerPage);
		
		//View로 넘겨줄 데이터
		model.addAttribute("list", list);
		model.addAttribute("totalPageNo", totalPageNo);
		model.addAttribute("totalGroupNo", totalGroupNo);
		model.addAttribute("groupNo", groupNo);
		model.addAttribute("startPageNo", startPageNo);
		model.addAttribute("endPageNo", endPageNo);
		model.addAttribute("pagesPerGroup", pagesPerGroup);
		model.addAttribute("pageNo", pageNo);
		
		return "/jdbc/exam06";
	}
	@RequestMapping("/jdbc/exam06Different")
	public String exam06Different(@RequestParam(defaultValue = "1") int pageNo, Model model){
		
		int line = 3;
		//한 페이지를 구성하는
		int rowsPerPage = 7;
		//한 그룹을 구성하는 페이지 수
		int pagesPerGroup = 5;
		//총 행수
		int totalRows = service.memberTotalRows();
		//전체 페이지 수
		int totalPageNo = totalRows/(rowsPerPage) + ((totalRows%(rowsPerPage)!=0)? 1: 0);
		//전체 그룹 수
		int totalGroupNo = totalPageNo/pagesPerGroup + ((totalPageNo%pagesPerGroup!=0)? 1: 0);
		//현재 그룹 번호
		int groupNo = (pageNo-1)/pagesPerGroup + 1;
		//현재 그룹의 시작 페이지 번호
		int startPageNo = (groupNo-1)*pagesPerGroup + 1;
		//현재 그룹의 마지막 페이지 번호
		int endPageNo = startPageNo+pagesPerGroup - 1;
		if(groupNo == totalGroupNo) endPageNo = totalPageNo;
		
		//현재 페이지의 행의 데이터 가져오기
		List<Exam12Member> list = service.memberListPageDif(pageNo, rowsPerPage);
		
		//View로 넘겨줄 데이터
		model.addAttribute("list", list);
		model.addAttribute("totalPageNo", totalPageNo);
		model.addAttribute("totalGroupNo", totalGroupNo);
		model.addAttribute("groupNo", groupNo);
		model.addAttribute("startPageNo", startPageNo);
		model.addAttribute("endPageNo", endPageNo);
		model.addAttribute("pagesPerGroup", pagesPerGroup);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("line", line);
		
		return "/jdbc/exam06Different";
	}
	
	
	@RequestMapping("/jdbc/exam06Detail")
	public String exam06Detail(String mid, int pageNo, Model model){
		Exam12Member m = service.getMember(mid);
		model.addAttribute("m", m);
		model.addAttribute("pageNo", pageNo);
		return "/jdbc/exam06Detail";
	}
	
	@RequestMapping("/jdbc/exam06CheckMpassword")
	public String exam05CheckBpassword(String mid, String mpassword, Model model){
		String result = service.memberCheckMpassword(mid, mpassword);
		model.addAttribute("result", result);
		return "/jdbc/exam06CheckMpassword";
	}
	
	
	@RequestMapping(value="/jdbc/exam06Update", method=RequestMethod.GET)
	public String exam06UpdateGet(String mid, int pageNo, Model model){
		Exam12Member m = service.getMember(mid);
		model.addAttribute("m", m);
		model.addAttribute("pageNo", pageNo);
		return "/jdbc/exam06Update";
	}
	
	@RequestMapping(value="/jdbc/exam06Update", method=RequestMethod.POST)
	public String exam06UpdatePost(Exam12Member m, int pageNo) throws IllegalStateException, IOException{
		if(!m.getMattach().isEmpty()){
			//첨부 파일에 대한 정보를 컬럼값으로 설정 (필드에 값을 세팅한다)
			m.setMoriginalfilename(m.getMattach().getOriginalFilename());
			m.setMfilecontent(m.getMattach().getContentType());
			String fileName = new Date().getTime()+"-"+m.getMoriginalfilename();
			m.setMsavedfilename(fileName);
			//서비스 객체에 요청처리 요청
			service.memberJoin(m);
			// 첨부 파일을 서버 로컬 시스템에 저장
			String realPath = servletContext.getRealPath("/WEB-INF/upload/");	
			File file = new File(realPath+fileName);
			m.getMattach().transferTo(file);
		}
		service.memberUpdate(m);
		return "redirect:/jdbc/exam06Detail?mid="+m.getMid()+"&pageNo="+pageNo;
	}
	
	@RequestMapping("/jdbc/exam06Delete")
	public String exam06Delte(String mid){
		service.memberWidraw(mid);
		return "redirect:/jdbc/exam06";
	}
	
	@RequestMapping("/jdbc/exam06Download")
    public void download(HttpServletResponse response, @RequestHeader("User-Agent") String userAgent, String savedfilename) throws IOException {
        // 응답 HTTP 헤더행을 추가(3가지는 다 넣어주는게 좋음)
        // 1 파일 이름(옵션)
        String fileName = savedfilename;
        String encodingFileName;
        if (userAgent.contains("MSIE") || userAgent.contains("Trident") || userAgent.contains("Edge")) {
            encodingFileName = URLEncoder.encode(fileName, "UTF-8");
            // fileName을 UTF-8로 인코딩한 바이트 배열을 16진수로 출력함         
        } else {
//            encodingFileName = new String(fileName.getBytes(), "UTF-8");
            encodingFileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
        }
        System.out.println(encodingFileName);
        
        response.addHeader("Content-Disposition", "attachment; filename=\"" + encodingFileName + "\"");
        // Disposition: 위치
        // attachment: 첨부파일 이므로 브라우저는 파일로 저장하는 행동(다이얼로그 창)을 취해라
        // "attachment; filename=\" \" " > "xxx xxx.png" 를 저장하기 위해
        
        // 2 파일 종류(필수)
        response.addHeader("Content-type", "image/jpeg");
        // "image/jpeg" >  MIME
        
        // 3 파일 크기(옵션)m
        File file = new File(servletContext.getRealPath("/WEB-INF/upload/" + fileName));
        long fileSize = file.length();
        response.addHeader("Content-Length", String.valueOf(fileSize));
        
        // 응답 HTTP 본문에 파일 데이터 추가
        OutputStream os = response.getOutputStream();
        FileInputStream fis = new FileInputStream(file);
        FileCopyUtils.copy(fis, os);
        // spring 에서는 자바에서 했던 복잡한 방법으로 파일을 카피하는 방식이 아닌 FileCopyUtils 클래스 제공
        // fis 에서 읽고, os로 출력
        os.flush();
        fis.close();
        os.close();
    }
	
	
}
