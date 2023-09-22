package com.green.pds.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.green.menus.service.MenuService;
import com.green.menus.vo.MenuVo;
import com.green.pds.service.PdsService;
import com.green.pds.vo.FilesVo;
import com.green.pds.vo.PdsPagingVo;
import com.green.pds.vo.PdsVo;

@Controller
@RequestMapping("/Pds")
public class PdsController {
	
	@Autowired
	private  MenuService  menuService;
	
	@Autowired
	private  PdsService   pdsService;
	
	// /Pds/List?menu_id=MENU01&nowpage=1	
	@RequestMapping("/List")
	public   ModelAndView   list( 
		@RequestParam  HashMap<String, Object>  map	
			) {

		//System.out.println("Contr List MAP:" + map);
		 //  Contr List MAP:{menu_id=MENU01, nowpage=1}
		
		//  메뉴 목록
		List<MenuVo>  menuList  = menuService.getMenuList();
	
		//--------------------------------------
		// 페이징 정보 준비
		// 현재 페이지 	번호
		int           nowpage   =  Integer.parseInt( (String) map.get("nowpage") );
		// 한페이지에 보여줄 자료수(ROWS)
		int           pagecount =  5;    // 한페이지 당 출력할 줄(row)수 - 2

		// sql 사용할 변수 : 조회할 레코드 번호
		int           startnum  =  ( nowpage - 1 ) * pagecount + 1;
		int           endnum    =  nowpage  *  pagecount;

		map.put("nowpage",   nowpage );
		map.put("pagecount", pagecount );
		map.put("startnum",  startnum ); // 조회할 자료의 시작번호
		map.put("endnum",    endnum );	 // 조회할 자료의 끝번호	
		//----------------------------------------
					
		// 자료실 글 목록
		String              menu_id        =  (String) map.get("menu_id");
		// 조회할 자료 검색
		List<PdsPagingVo>   pdsPagingList  =  pdsService.getPdsPagingList( map );
		
		// 조회후 pdsService.getPagingList(map)를 실행한 후 변경된 map 정보를 이용 
		// paging.jsp 가 사용할 변수담고 있다
		PdsPagingVo         pdsPagingVo    =  (PdsPagingVo) map.get("pdsPagingVo");
		//System.out.println("125:" +  pdsPagingVo);
	
		
		// 메뉴이름을 가져온다		
		String        menuname  = menuService.getMenuName(menu_id);
		map.put("menuname", menuname);				
				
		ModelAndView   mv  =  new ModelAndView();
		mv.setViewName("pds/list");
		mv.addObject("menuList",       menuList);       // 메뉴 목록
		mv.addObject("pdsPagingList",  pdsPagingList);	// 페이징된 자료 리스트	 
		mv.addObject("pdsPagingVo",    pdsPagingVo);	// paging,jsp 
		mv.addObject("map",            map);
		
		return mv;
	}
	
	// 자료실 새글 쓰기
	// /Pds/WriteForm?menu_id=MENU01&bnum=0&lvl=0&step=0&nref=0&nowpage=2           // 새글
	// /Pds/WriteForm?menu_id=MENU03&idx=19&bnum=19&lvl=0&step=0&nref=19&nowpage=2  // 답글
	@RequestMapping("/WriteForm")
	public  ModelAndView   writeForm(  
		@RequestParam  HashMap<String, Object> map	) {

		// System.out.println( "contr writeForm map:" + map );
		// map:{menu_id=MENU01, bnum=0, lvl=0, step=0, nref=0, nowpage=2} : 새글
		// map:{menu_id=MENU01, bnum=7, lvl=1, step=1, nref=7, idx=7, nowpage=2} : 답글
		
		// 메뉴 목록
		List<MenuVo>  menuList  =  menuService.getMenuList();
		
		String        menu_id   =  (String) map.get("menu_id");
		String        menuname  =  menuService.getMenuName(menu_id);
		map.put("menuname", menuname);
		
		// idx 없으면 null      : 새글
		//     있으면 숫자 27   : 답글
		int      idx    = 0;
		PdsVo    pdsVo  = null;
		if( map.get("idx") != null  ) {
			idx    =  Integer.parseInt( String.valueOf( map.get("idx") ) );
			pdsVo  =  pdsService.getPds( map );
			String title  =  ">> " + pdsVo.getTitle();
			String cont   =  pdsVo.getCont();
			if(cont == null) 
				cont  = "";
			else
				cont  = ">> " + cont.replace("\n", "\n >> ");
			cont         +=  "\n==============================\n"; 
			pdsVo.setTitle( title );
			pdsVo.setCont(  cont );			
		}
		map.put("idx", idx);
				
		//System.out.println("idx=" +  map.get("idx") );
		
		ModelAndView  mv  = new ModelAndView();
		mv.setViewName("pds/write");              // /WEB-INF/views/pds/write.jsp
		mv.addObject("menuList", menuList);
		mv.addObject("vo",       pdsVo);          // 답글처리를 위해 기존글 조회
		mv.addObject("map",      map);
				
		return mv;
	}
	
	// ?menu_id=MENU03&bnum=0&lvl=0&step=0&nref=0  &title=eee&writer=eee&cont=eee -> map 
	// 파일전송  -> request 에 전송된 파일들 (파일명, 파일내용) 
	@RequestMapping("/Write")
	public  ModelAndView   write(
		 @RequestParam  HashMap<String, Object> map,
		 HttpServletRequest request ) {
		
		//contr /Write map:{parent=0, lvl=0, nref=0, bnum=0, step=0, writer=aa, title=aa, 
		//    cont=aa, menu_id=MENU01}
		// request : 보내진 파일 정보
		// System.out.println("contr /Write map:" + map );
		
		System.out.println("234:" + map);
		
		String  menu_id  =  String.valueOf( map.get("menu_id") );
		int     nowpage  =  Integer.parseInt( String.valueOf( map.get("nowpage") ) );
		
		// 새글(답글)저장 -> Board table  저장
		// 파일 정보 저장 -> Files table  저장
		// 실제 파일 저장 -> d:\\upload   저장
		
		// 글쓰기 및 파일저장
		pdsService.setWrite(map, request);
		
		String  fmt = "redirect:/Pds/List?menu_id=%s&nowpage=%d";
		String  loc = String.format(fmt, menu_id, nowpage);
		
		ModelAndView  mv  = new ModelAndView();
		mv.addObject("map", map);
		mv.setViewName(loc);
		
		return mv;
		
	}
	
	// 내용보기
	// /Pds/View?menu_id=MENU03&idx=15&nowpage=1
	@RequestMapping("/View")
	public   ModelAndView   view( 
		@RequestParam HashMap< String, Object >  map	
			) {
		
		// 메뉴 리스트
		List<MenuVo>   menulist  =  menuService.getMenuList();
			
		// 보여줄 게시글 내용
		PdsVo          pdsVo     =  pdsService.getPds(map);      // idx		
		// System.out.println("/VIEW pdsVo:" + pdsVo);
		// 게시글의 내용중 <textarea> 안의 엔터키는 -> \n
		// 화면에 출력 <td><div> 줄바꿈 <br>
		String         cont      =  pdsVo.getCont();
		if( cont == null )     cont = "";
		cont                     =   cont.replace("\n", "<br>");
		pdsVo.setCont(cont);
		
		// 파일정보목록 
		List<FilesVo>  fileList  =  pdsService.getFileList( map );    // idx
		// System.out.println( fileList  );
		
		// 메뉴이름
		String  menu_id   =  (String) map.get("menu_id");
		String  menuname  =  menuService.getMenuName(menu_id);
		map.put("menuname",  menuname);
		
		ModelAndView  mv  =  new ModelAndView();
		mv.setViewName( "pds/view" );     //  pds/view.jsp
		mv.addObject("menuList",  menulist);
		mv.addObject("vo",        pdsVo);
		mv.addObject("fileList",  fileList);
		mv.addObject("map",       map);
		return        mv;
		
	}
	
	// 자료실 글 삭제
	// /Pds/Delete?menu_id=MENU03&idx=17&nowpage=1
	@RequestMapping("/Delete")
	public  ModelAndView   delete(
		@RequestParam   HashMap<String,  Object>  map	
			) {
		
		// 자료실 자료 삭제
		// 1. Files  idx 가진 파일정보 삭제   : child
		// 2. Board  idx 번글 삭제            : parent
		// 3. D:\\upload\\에 있는 파일 삭제
		
		pdsService.setDelete( map );   // idx 
		
	//	String  menu_id  =  map.get("menu_id").toString();
	//	String  nowpage  =  map.get("nowpage").toString();
		String  fmt      =  "redirect:/Pds/List?menu_id=%s&nowpage=%s"; 
	//	String  loc      =  String.format(fmt, menu_id, nowpage);
		String  loc      =  String.format(fmt, map.get("menu_id"), map.get("nowpage"));
		
		ModelAndView   mv  = new ModelAndView();
		mv.setViewName( loc );
		mv.addObject("map", map );
		return     mv;
		
	}
	
	//--------------------------------------
	// 수정
	// /Pds/UpdateForm?menu_id=MENU01&idx=41&nowpage=1
	@RequestMapping("/UpdateForm")
	public   ModelAndView   updateForm(
		@RequestParam	HashMap<String, Object>  map
			) {
		
		// 메뉴 목록
		List<MenuVo>  menuList  =  menuService.getMenuList();
		
		// 수정을 위해 조회한 자료
		PdsVo         pdsVo     =  pdsService.getPds( map );
		
		// menuname
		String        menu_id   =  (String) map.get("menu_id");
		String        menuname  =  menuService.getMenuName(menu_id);
		map.put( "menuname",  menuname );
		
		// fileList
		List<FilesVo>  fileList =  pdsService.getFileList( map ); 
		
		ModelAndView    mv = new ModelAndView();
		mv.setViewName("pds/update");      // update.jsp
		mv.addObject("menuList", menuList );
		mv.addObject("vo",       pdsVo );
		mv.addObject("fileList", fileList );
		mv.addObject("map",      map );
		return  mv;
		
	}
	
	@RequestMapping("/Update")
	public  ModelAndView   update(
		@RequestParam  HashMap<String, Object> map,
		HttpServletRequest                     request
			) {
		
		// 수정 ( idx, title, cont, file 정보들 )
		pdsService.setUpdate( map, request  );
				
		// 수정된 내용들 다시 조회해서 출력
		int     idx      =  Integer.parseInt( String.valueOf ( map.get("idx") ) );  
		String  menu_id  =  String.valueOf( map.get( "menu_id" ) ); 		
		String  nowpage  =  String.valueOf( map.get( "nowpage" ) ); 		
		String  fmt      =  "redirect:/Pds/View?idx=%d&menu_id=%s&nowpage=%s";
		String  loc      =  String.format(fmt, idx, menu_id, nowpage);
		
		ModelAndView  mv  =  new ModelAndView();
		mv.setViewName( loc );	
		mv.addObject("map",     map);
		return  mv;
	}   
	
	
	
	//---------------------------------------------------
	// 다운로드
	// 정규식
	// {sfile}     - .jpg 와 같이 . 포함된 문자가 들어오면 문자를 무시 : .인식하지 않는다 : 사용금지
	// {sfile:.+}  - .+ : . 이 한 개 이상
	//               반드시 . 을 포함해야 한다
	@RequestMapping(value  = "/download/{type}/{sfile:.+}",
			        method = RequestMethod.GET )
	public   void   downloadFile(
			@PathVariable("type")   String type,
			@PathVariable("sfile")  String sfile,
			HttpServletResponse     response
			) throws IOException {
		
		String     INTERNAL_FILE         =  sfile;
		String     EXTERNAL_FILE_PATH    =  "d:\\upload\\" + sfile;
				
		File       file  =  null;
		if ( type.equalsIgnoreCase("internal")  ) {
			ClassLoader   classLoader = 
				Thread.currentThread().getContextClassLoader();
			file   = new File( classLoader.getResource(INTERNAL_FILE).getPath() ); 
		} else {
			file   = new File( EXTERNAL_FILE_PATH );
		}
		
		if( !file.exists()  ) {
			String errorMessage = "죄송합니다.파일이 없습니다";
			System.out.println( errorMessage );
			OutputStream  outputStream = response.getOutputStream();
			outputStream.write(  errorMessage.getBytes(Charset.forName("UTF-8")) );
			outputStream.close();
			return;
		}
		
		// 실제 다운로드
		String  mimeType = URLConnection.guessContentTypeFromName(file.getName());
		mimeType   =  "application/octet-stream";   // 무조건 다운로드
		
		// 파일명의 한글처리		
		String   fname   = new String( 
				file.getName().getBytes("UTF-8"), "ISO-8859-1" );
		
		response.setContentType( mimeType );
		response.setHeader("Content-Disposition",
			String.format("inline; filename=\"" + fname + "\"" ));
		
		response.setContentLength( (int) file.length() );
		
		InputStream   inputStream  = new BufferedInputStream(
			new FileInputStream( file )	);
		
		FileCopyUtils.copy( inputStream, response.getOutputStream() );
		
		inputStream.close();
		
		
	}
	
	
}















