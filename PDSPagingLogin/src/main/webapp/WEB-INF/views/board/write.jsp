<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>게시글 등록</title>
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
  
  <c:choose>
    <c:when test="${  vo.bnum eq 0 }">    
	  <h2>${ name } 새글 등록</h2>
	</c:when>
	<c:otherwise>    
	  <h2>${ name } 답글 등록</h2>
	</c:otherwise>  
  </c:choose>
 
  <form action="/Board/Write" method="POST" >
  <input type="hidden"  name="menu_id" value="${ vo.menu_id }" />
  <input type="hidden"  name="bnum"    value="${ vo.bnum    }" />
  <input type="hidden"  name="lvl"     value="${ vo.lvl     }" />
  <input type="hidden"  name="step"    value="${ vo.step    }" />
  <input type="hidden"  name="nref"    value="${ vo.nref    }" /> 
  <input type="hidden"  name="parent"  value="${ vo.idx     }" /> 
 
  <table id="table">   
   <tr>
     <th>제목</th>
     <td><input type="text" name="title" value="${ vo.title }"/></td>
   </tr> 
   <tr>
     <th>글쓴이</th>
     <td><input type="text" name="writer" value="${ vo.writer }" /></td>
   </tr> 
   <tr>
     <th>내용</th>
     <td><textarea name="cont" maxlength="1000">${ vo.cont }</textarea></td>
   </tr>   
   <tr>     
     <td colspan="2">
     <input type="submit" value="작성" />
     </td>
   </tr> 
  </table>
  </form>
  </div>
</body>
</html>








