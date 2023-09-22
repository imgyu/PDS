<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Home</title>
<link rel="shortcut icon" href="/img/favicon.ico">
<link rel="stylesheet" href="/css/common.css" />
</head>
<body>
   <div id="main">
   <h2>Home</h2> 
   <ul> 
   <li><a href="/User/List">사용자 목록</a></li>
   <li><a href="/User/WriteForm">사용자 등록</a></li>
   <li></li>
   <li><a href="/Menus/List">메뉴 목록</a></li>
   <li><a href="/Menus/WriteForm2">메뉴 등록</a></li>
   <li></li>
   <li><a href="/Board/List?menu_id=MENU01">게시물 목록</a></li>
   <li><a href="/Board/WriteForm?menu_id=MENU01&bnum=0&lvl=0&step=0&nref=0">게시물 등록</a></li>
   <li></li>
   <li><a href="/Pds/List?menu_id=MENU01&nowpage=1">자료실 목록</a></li>
   <li><a href="/Pds/WriteForm?menu_id=MENU01&bnum=0&lvl=0&step=0&nref=0">자료실 등록</a></li>
   <li></li>
   <li><a href="/Pds/List?menu_id=MENU01&nowpage=1">페이징 자료실 목록</a></li>
   <li><a href="/Pds/WriteForm?menu_id=MENU01&bnum=0&lvl=0&step=0&nref=0&nowpage=1">자료실 등록</a></li>
   <li>
   ${ sessionScope.login.username } 님 환영합니다<br>
   당신의 가입일은 ${ login.indate } 입니다<br>
   <a href="/logout">로그아웃</a>   
   </li>
   </ul>
   </div>
</body>
</html>








