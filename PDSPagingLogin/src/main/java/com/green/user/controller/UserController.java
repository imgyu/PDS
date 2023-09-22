package com.green.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.green.user.service.UserService;
import com.green.user.vo.UserVo;

@Controller
public class UserController {

	@Autowired
	private UserService  userService;
	
	// 회원등록창(user/write.jsp)으로 이동
	// http://localhost:9090/User/WriteForm
	@RequestMapping("/User/WriteForm") 
	public  String  writeForm() {
		return "user/write";   // user/write.jsp
	}
	
	// DB 회원등록 -> 회원목록 패이지로 이동
	// http://localhost:9090/User/Write -- <form action="" />  
	@RequestMapping("/User/Write")
	public  String write( UserVo user, Model model ) {   // UserVo user <form> 넘겨준 정보(name 속성이 있는)  
		// System.out.println( user );
		
		// db 에 저장
		userService.insertUser( user );		
		
		// 회원목록조회로 이동 (  http://localhost:9090/User/List)		
		return "redirect:/User/List"; 
		/*
		List userList = userService.getUserList();
		model.addAttribute("userList", userList);
		
		return "user/list";  // 조회하러간다
		*/
	}
	
	// 회원목록조회 -> 회원목록을 조회해서 회원목록패이지(user/list.jsp)로 이동
	// http://localhost:9090/User/List
	@RequestMapping("/User/List")
	public  String  list( Model model ) {
		
		// db 조회
		List userList = userService.getUserList(); 
		
		// System.out.println("조회된 시용자 목록:" + userList );
		
		model.addAttribute("uList", userList);
		
		return "user/list";    // /WEB-INF/views/  user/list   .jsp
	}
	
	// 회원정보보기
	// http://localhost:9090/User/View?userid=dddd
	@RequestMapping("/User/View")
	public   String    view( String userid,  Model model ) {
		
		// db 조회
		UserVo  vo = userService.getUser( userid );  
		
		// model 에 담는다
		model.addAttribute("user", vo);
				
		return  "user/view";   //  user/view.jsp
	}
	
	// 회원삭제
	@RequestMapping("/User/Delete")
	public  String  delete( String userid ) {
		
		// 삭제
		userService.deleteUser( userid );
		
		return  "redirect:/User/List"; 
	}   
	
	// 회원 정보 수정 화면
	// http://localhost:9090/User/UpdateForm?userid=dddd
	@RequestMapping("/User/UpdateForm")
	public  String  updateForm( String  userid, Model model ) {
		
		// update.jsp 에 보여줄 회원정보를 조회한다
		UserVo vo  =  userService.getUser( userid ); 
		
		// model 에 담아서 전달한다
		model.addAttribute("user", vo );
		
		return "user/update";  // user/update.jsp
	}
	
	// 회원정보 수정
	// http://localhost:9090/User/Update
	// userid, passwd, username, email
	@RequestMapping("/User/Update")
	public  String   update( UserVo vo ) {
		
		// System.out.println("contr1:" + vo);
		
		// 수정된 정보를 저장한다
		userService.updateUser( vo );
		
		return "redirect:/User/List";
		
	}
	
	
	
}


















