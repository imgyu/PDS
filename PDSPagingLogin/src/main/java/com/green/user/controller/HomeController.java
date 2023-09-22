package com.green.user.controller;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.green.user.service.UserService;
import com.green.user.vo.UserVo;

@Controller
public class HomeController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/")   // root : http://localhost:9090/   
	public  String  home( ) {			
		return  "home";      // /WEB-INF/views/ home .jsp
	}
	
	// 로구안 
	@RequestMapping("/login")
	public String login() {
		return "user/login";
	}
	
	// 로그인체크 
	@RequestMapping("/loginprocess")
	public String loginprocess(@RequestParam HashMap<String, Object> map, HttpSession session) {
		
		String returnURL  =  "";
		
		//  기존의 로그인정보가 존재한다면 기본정보를 제거
		if(session.getAttribute("login") != null ) {
			session.removeAttribute("login");
		}
		
		//  로그인 체크
		UserVo  vo  =  userService.getLogin(map);
		if( vo != null ) {
			session.setAttribute("login", vo);
			returnURL  =  "redirect:/"; // HOME 으로 보낸다
		} else {
			returnURL  =  "redirect:/login"; // login.jsp 으로 보낸다
		}
		
		return returnURL;
	}
	
	// 로그아웃 
	@RequestMapping("/Logout")
	public String logout(HttpSession session) {
		
		session.invalidate();
		return "redirect:/login";
	}
	
}







