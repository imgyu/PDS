<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        
  <%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
  <%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>          
        
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Pds List</title>
<link rel="shortcut icon" href="/img/favicon.ico">
<link rel="stylesheet" href="/css/common.css" />

<style>

 #PdsList  td:nth-of-type(1) { width:70px; text-align: center;  }
 #PdsList  td:nth-of-type(2) { width:300px; text-align: left  }
 #PdsList  td:nth-of-type(3) { width:80px; text-align: center;  }
 #PdsList  td:nth-of-type(4) { width:80px; text-align: center;  }
 #PdsList  td:nth-of-type(5) { width:100px; text-align: center;  }
 #PdsList  td:nth-of-type(6) { width:100px; text-align: center;  }
  
</style>

</head>
<body>
  <div id="main">
  <!-- 메뉴 목록 -->
  <%@include file="/WEB-INF/include/pdsmenus.jsp" %>
  
  <!-- 자료실 목록 -->
  <table id="PdsList">
  <caption><h2>${ map.menuname  } 자료실 ( ${ map.nowpage } page ) </h2></caption>
  <tr>
    <td class="right" colspan="6">
     <a href="/Pds/WriteForm?menu_id=${ map.menu_id }&bnum=0&lvl=0&step=0&nref=0&nowpage=1">새 글 쓰기</a>
    </td>
  </tr>
  <tr>
    <td>번호</td>
    <td>제목</td>
    <td>작성자</td>
    <td>조회수</td>
    <td>첨부파일수</td>
    <td>작성일</td>
  </tr>
  
  <c:forEach var="pdsPagingVo" items="${ pdsPagingList }">
  <tr>
     <td>
     <!-- 번호 -->
      <c:if test="${ pdsPagingVo.lvl eq 0 }">
          ${ pdsPagingVo.idx }
      </c:if> 
     </td>
     <td>
       <!-- 제목(새글/답글) -->
       <c:choose>
         <c:when test="${ pdsPagingVo.lvl eq 0 }">
         
           <c:choose>
            <c:when test="${ pdsPagingVo.delnum eq 0 }">
              <a href="/Pds/View?menu_id=${pdsPagingVo.menu_id}&idx=${pdsPagingVo.idx}&nowpage=${map.nowpage}">
       		   ${ pdsPagingVo.title }
       		  </a>
       		</c:when>
       		<c:otherwise>
       		   <s>삭제된 게시물 입니다</s>
       		</c:otherwise>
       	   </c:choose>	 
       		
         </c:when>
         <c:otherwise>
         
            <b style="display:inline-block; width:${pdsPagingVo.lvl*20}px"></b> 
            
            <c:choose>
              <c:when test="${ pdsPagingVo.delnum eq 0 }">
                <a href="/Pds/View?menu_id=${pdsPagingVo.menu_id}&idx=${pdsPagingVo.idx}&nowpage=${map.nowpage}">
              	  [답글] ${ pdsPagingVo.title }
         	    </a>
         	  </c:when>
         	  <c:otherwise>
         	      [답글] <s>삭제된 글입니다</s>
         	  </c:otherwise>
         	</c:choose>
         	
         </c:otherwise>
       </c:choose>
     </td>
     <td>
     <!-- 작성자 -->
     ${ pdsPagingVo.writer }
     </td>
     <td>
     <!-- 조회수 -->
     ${ pdsPagingVo.readcount }
     </td>
     <td>
     <!-- 첨부파일수 -->
     <c:choose>
       <c:when test="${ pdsPagingVo.filescount eq 0 }">
         &nbsp;
       </c:when>
       <c:otherwise>
         ${ pdsPagingVo.filescount }         
       </c:otherwise>
     </c:choose>
     </td>
     <td>
     <!-- 날짜 -->
     ${fn:substring( pdsPagingVo.regdate, 0, 10) }
     </td>  
  </tr>  
  </c:forEach>
    
  </table>
  
  <!-- 페이징 리스트 -->  
  <%@include file="/WEB-INF/include/paging.jsp" %>
  
  
  
  </div>
</body>
</html>





