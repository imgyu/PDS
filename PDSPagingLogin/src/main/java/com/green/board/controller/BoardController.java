package com.green.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.green.board.service.BoardService;
import com.green.board.vo.BoardVo;
import com.green.menus.service.MenuService;
import com.green.menus.vo.MenuVo;

@Controller
@RequestMapping("/Board")
public class BoardController {
	
	@Autowired
	private  MenuService   menuService;
	
	@Autowired
	private  BoardService  boardService;
	
	// 게시물 목록 보기
	// http://localhost:9090/Board/List?menu_id=MENU01	
	@RequestMapping("/List")
	public  ModelAndView   list( String  menu_id ) {
		
		// MenuList  - 메뉴 전체 목록
		List<MenuVo>   menuList   =  menuService.getMenuList();

		// BoardList - 게시글 목록
		List<BoardVo>  boardList  =  boardService.getBoardList( menu_id  );
		//System.out.println("Contr BoardList:" + boardList);
		
		// 게시판 이름 : menu_name
		String         name       =  menuService.getMenuName( menu_id );
				
		// 이동할 페이지 정보 : .setViewName()
		// 넘겨줄 정보        : .addObject()
		ModelAndView  mv  =  new ModelAndView(); 
		mv.setViewName("board/list");   // /WEB-INF/views/  board/list  .jsp
		mv.addObject("menuList",   menuList);
		mv.addObject("bList",      boardList);
		mv.addObject("name",       name);
		
		return  mv;
	}
	
	// 글쓰기	
	// http://localhost:9090/Board/WriteForm?menu_id=MENU01&bnum=0&lvl=0&step=0&nref=0       - 새글쓰기
    // http://localhost:9090/Board/WriteForm?menu_id=MENU01&idx=6&bnum=1&lvl=0&step=0&nref=1 - 답글쓰기
	@RequestMapping("/WriteForm")
	public   ModelAndView   writeForm( BoardVo  vo ) {
		
		//System.out.println( "contr wirteform:" + vo );
		
		// MenuList - 전체 메뉴 목록
		List<MenuVo>   menuList   =  menuService.getMenuList();
		
		// 답글이 경우 : 내용을 조회하여 출력
		if( vo.getBnum() > 0 ) {
			BoardVo  boardVo = boardService.getBoard( vo.getIdx() );
			vo               = boardVo;
			vo.setCont( ">> " + vo.getCont().replace("\n", "\n>> ") 
					+ "\n===================\n"   );
		}
		
		// 메뉴이름 조회
		String  name  =  menuService.getMenuName( vo.getMenu_id() );
		
		ModelAndView  mv  = new ModelAndView();
		mv.setViewName("board/write");
		mv.addObject("menuList", menuList);
		mv.addObject("vo"      , vo );
		mv.addObject("name"    , name );
		return  mv;
		
	}
		
	@RequestMapping("/Write")
	public   ModelAndView   write( BoardVo vo  ) {
		// System.out.println( "write vo:" + vo);
		
		// 게시글 등록
		boardService.insertBoard( vo );
		
		ModelAndView  mv  = new ModelAndView();
		mv.setViewName("redirect:/Board/List?menu_id=" + vo.getMenu_id() );		
		return  mv;
		
	}
	
	//-------------------------------------
	// 게시물 내용보기
	
	@RequestMapping("/View")
	public  ModelAndView  view( int idx  ) {
		
		// 메뉴목록 조회
		List<MenuVo>  menuList = menuService.getMenuList();
		
		// db 조회수(readcount) 증가 
		boardService.incrementReadCount( idx );
		
		// idx 로 게시물 조회	
		BoardVo       boardVo  = boardService.getBoard( idx );
		String        cont     = boardVo.getCont().replace("\n", "<br />");
		boardVo.setCont(cont);
		//System.out.println("contr view : " + boardVo);
		
		// 현재메뉴이름
		String  menuname  = menuService.getMenuName( boardVo.getMenu_id() );
						
		ModelAndView  mv = new ModelAndView();
		mv.setViewName("board/view");
		mv.addObject("vo",       boardVo );
		mv.addObject("menuList", menuList );
		mv.addObject("name",     menuname );   // 메뉴이름
		
		return        mv;
		
	}
	
	//----------------------------------------
	// 게시글 삭제
	// http://localhost:9090/Board/Delete?menu_id=MENU01&idx=12
	@RequestMapping("/Delete")
	public  ModelAndView  delete( BoardVo  vo ) {
		
		// data 삭제
		boardService.deleteBoard( vo );   
		
		String  menu_id  = vo.getMenu_id();		
		// 삭제 후 이동할 주소
		ModelAndView  mv  = new ModelAndView();
		mv.setViewName("redirect:/Board/List?menu_id=" + menu_id);		
		return  mv;
		
	}
	
	//-----------------------------------------
	// 게시물 수정
	// http://localhost:9090/Board/UpdateForm?menu_id=MENU01&idx=1
	@RequestMapping("/UpdateForm")
	public   ModelAndView   updateForm( BoardVo vo ) {
		
		// 메뉴 전체 목록
		List<MenuVo>  menuList  =  menuService.getMenuList();
		
		// 수정할 게시물 조회
		BoardVo       boardVo   =  boardService.getBoard( vo.getIdx() );
		
		// 해당 메뉴이름
		String        menuname  =  menuService.getMenuName( vo.getMenu_id() );
		
		ModelAndView  mv  = new ModelAndView();
		mv.setViewName("board/update");
		mv.addObject("menuList", menuList );
		mv.addObject("vo",       boardVo );
		mv.addObject("name",     menuname );
				
		return  mv;
	}
	
	@RequestMapping("/Update")
	public   ModelAndView   update(BoardVo vo) {
		
		// /Update : BoardVo [idx=7, menu_id=MENU01, title=aaa, cont=aaa, regdate=null, ... ]
		
		// System.out.println("/Update : " + vo);

		// db 수정
		boardService.updateBoard( vo );		
		
		ModelAndView   mv   = new ModelAndView();
		mv.setViewName("redirect:/Board/List?menu_id=" + vo.getMenu_id() );		
		
		return  mv;
	}
	
}













