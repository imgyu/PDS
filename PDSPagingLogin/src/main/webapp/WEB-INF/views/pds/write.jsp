<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>자료실 등록</title>
<link rel="shortcut icon" href="/img/favicon.ico">
<link rel="stylesheet" href="/css/common.css" />

<style>
   input             { height: 32px;  }

   #table  th        { width : 20%;  }  
   #table  td        { width : 80%;  }  
   input[type=text]  { width : 100%; }
   textarea          { width : 100%; height : 400px; padding:10px;} 
      
</style>

<script src="https://code.jquery.com/jquery.min.js"></script>

<script>
  $( function() {
	  let num = 1;
	  $('#btnAddFile').on('click', function(e) {
		  let tag = '<input type="file"  name="upfile' + num + '" class="upfile"/><br>';
		  $('#tdfile').append( tag );		  
		  num++;
	  })
  });
</script>

<script>
 /*
   window.onload = function() {
	   let  num = 1;
	   const   btnAddFileEl  = document.querySelector('#btnAddFile');
	   const   divfilesEl    = document.querySelector('#tdfile #divfiles');
	   btnAddFileEl.onclick = function(e) {
		   let oldtag = divfilesEl.innerHTML;
		   let tag = '<input type="file"  name="upfile' + num + '" class="upfile"/><br>';
		   let newtag = oldtag + tag;		  
		   divfilesEl.innerHTML = newtag;
		   num++;
	   };
   }
  */
</script>

</head>
<body>
  <div id="main">
   <!--  메뉴 목록 -->
   <%@include file="/WEB-INF/include/pdsmenus.jsp" %>
  
  <c:choose>
    <c:when test="${  map.bnum eq 0 }">    
	  <h2>${ map.menuname } 자료실 새글 등록</h2>
	</c:when>
	<c:otherwise>    
	  <h2>${ map.menuname } 자료실 답글 등록</h2>
	</c:otherwise>  
  </c:choose>
 
  <!-- enctype="multipart/form-data" : 파일전송(binary)시 필수
     <input file="" name="" /> 사용가능
  -->
  <form action="/Pds/Write" method="POST" 
        enctype="multipart/form-data"   >   
  <input type="hidden"  name="menu_id" value="${ map.menu_id }" />
  <input type="hidden"  name="nowpage" value="${ map.nowpage }" />
  <input type="hidden"  name="bnum"    value="${ map.bnum    }" />
  <input type="hidden"  name="lvl"     value="${ map.lvl     }" />
  <input type="hidden"  name="step"    value="${ map.step    }" />
  <input type="hidden"  name="nref"    value="${ map.nref    }" /> 
  <input type="hidden"  name="parent"  value="${ map.idx     }" /> 
 
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
     <th>파일</th>
     <!-- 
     <td id="tdfile">
        <input type="button"  id="btnAddFile" value="파일 추가" />
        <div id="divfiles">
        <input type="file"  name="upfile"  class="upfile"/><br>
        </div>
     </td>
     -->
     <td id="tdfile">
        <input type="button"  id="btnAddFile" value="파일 추가(최대 100M byte)" /><br>
        <input type="file"  name="upfile"  class="upfile"/><br>
     </td>
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








