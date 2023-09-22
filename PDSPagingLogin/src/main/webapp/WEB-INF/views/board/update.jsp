<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>게시글 수정</title>
<link rel="shortcut icon" href="/img/favicon.ico">
<link rel="stylesheet" href="/css/common.css" />

<style>
   input             { height: 32px;  }

   #table  th        { width : 20%;  }  
   #table  td        { width : 80%;  }  
   input[type=text]  { width : 100%; }
   textarea          { width : 100%; height : 400px; padding:10px;} 
      
</style>

</head>
<body>
  <div id="main">
   <!--  메뉴 목록 -->
   <%@include file="/WEB-INF/include/menus.jsp" %>
  
   <h2>${ name } 게시글 수정 </h2>
	 
  <form action="/Board/Update?idx=${ vo.idx }" method="POST" >
  <%-- <input type="hidden"  name="idx"      value="${ vo.idx }" /> --%>
  <input type="hidden"  name="menu_id"  value="${ vo.menu_id }" />
 
  <table id="table">   
   <tr>
     <th>글번호</th>
     <td>${ vo.idx }</td>
   </tr>
   <tr>
     <th>작성일</th>
     <td>${ vo.regdate }</td>
   </tr>
   <tr>
     <th>조회수</th>
     <td>${ vo.readcount }</td>
   </tr>
   <tr>
     <th>글쓴이</th>
     <td>${ vo.writer }</td>
   </tr> 
   
   <tr>
     <th>제목</th>
     <td><input type="text" name="title" value="${ vo.title }"/></td>
   </tr>
   <tr>
     <th>내용</th>
     <td><textarea name="cont" maxlength="1000">${ vo.cont }</textarea></td>
   </tr>   
   
   <tr>     
     <td colspan="2">
     <input type="submit" value="수정" />
     </td>
   </tr> 
  </table>
  </form>
  </div>
</body>
</html>








