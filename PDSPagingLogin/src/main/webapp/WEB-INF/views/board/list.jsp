<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
 <%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>   
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>List</title>
<link rel="shortcut icon" href="/img/favicon.ico">
<link rel="stylesheet" href="/css/common.css" />

<style>
  #table td:not([colspan]) { text-align: center;  } 
  #table th:nth-of-type(1) { width:100px;  }
  #table th:nth-of-type(2) { width:500px;  }
  #table th:nth-of-type(3) { width:100px;  }
  #table th:nth-of-type(4) { width:120px;  }
  #table th:nth-of-type(5) { width:100px;  }
  
  #table td:nth-of-type(2) { text-align: left;  }
</style>

</head>
<body>
   <div id="main">
   
   <!--  메뉴 목록 -->
   <%@include file="/WEB-INF/include/menus.jsp" %>
   
   <!--  게시물 목록 -->
   <table  id="table">
   <caption>
   	  <h2>${ name } 게시판</h2>   
   </caption>
   <tr>
      <td colspan="5"  class="right">
        <a href="/Board/WriteForm?menu_id=${ param.menu_id }&bnum=0&lvl=0&step=0&nref=0">새 글쓰기</a>
      </td>
   </tr>
   <tr>
      <th>번호</th>
      <th>제목</th>  
      <th>글쓴이</th> 
      <th>날짜</th>      
      <th>조회수</th>
   </tr>
   
   <c:forEach  var="board"  items="${ bList }" >
       
     <tr>
       <td>
          <%-- <c:if test="${ board.lvl == 0 }"> --%> 
            ${ board.idx }
          <%-- </c:if>  --%>
       </td>
       <td>
          <b style="display:inline-block;width:${board.lvl*20}px"></b>
          
          <c:choose>          
            <c:when test="${ board.delnum eq 0 }">
	          <a href="/Board/View?idx=${ board.idx }">
	            ${ board.title }
	          </a>
	        </c:when>
	        <c:otherwise>
	          <s>게시글이 삭제되었습니다</s>
	        </c:otherwise>          
          </c:choose>
          
       </td>
       <td>${ board.writer    }</td>
       <td>${ board.regdate   }</td>
       <td>${ board.readcount }</td>
     </tr>   
   </c:forEach>
   <tr>   
   </tr>    
   
   </table>
   
   </div>
</body>
</html>















