<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
  <%@taglib  prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>  
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>자료실 수정</title>
<link rel="shortcut icon" href="/img/favicon.ico">
<link rel="stylesheet" href="/css/common.css" />

<style>
   input                       { height: 32px;  }

   #table  td:nth-of-type(1)   { width : 150px;  text-align:center;  }  
   #table  td:nth-of-type(2)   { width : 400px;  text-align:center;  }  
   #table  td:nth-of-type(3)   { width : 150px;  text-align:center;  }  
   #table  td:nth-of-type(4)   { width : 400px;  text-align:center;  }  
   
   #table  td[colspan]         { text-align: left;  }  
   
   input[type=text]  { width : 100%; }
   textarea          { width : 100%; height : 400px; padding:10px;} 
      
</style>

<script src="https://code.jquery.com/jquery.min.js"></script>

<script>
  $( function() {
	  let num = 1;
	  // 파일추가 버튼 클릭
	  $('#btnAddFile').on('click', function(e) {
		  let tag = '<input type="file"  name="upfile' + num + '" class="upfile"/><br>';
		  $('#tdfile').append( tag );		  
		  num++;
	  })
	  
	  // X 클릭 -  파일 삭제
	  $('.aDelete').on('click', function(e) {
		  e.preventDefault();    // 페이지 이동 막음
		  e.stopPropagation();		  
		   
		  let aDelete = this;
		  
		  $.ajax({
			  url     : this.href,
			  method  : 'GET'			  
		  })
		  .done(function( result  ) {
		//	  alert('삭제 완료');
			  $(aDelete).parent().remove();   // 화면에서 항목 삭제
		  })
		  .fail( function( error ) {
			  console.log(error);
			  alert('오류발생:' + error );
		  } ) ;
		 
	  })
  });  
</script>


</head>
<body>
  <div id="main">
   <!--  메뉴 목록 -->
   <%@include file="/WEB-INF/include/pdsmenus.jsp" %>
    
  <form action="/Pds/Update" method="POST" 
        enctype="multipart/form-data"   >   
  <input type="hidden"  name="idx"     value="${ map.idx     }" />
  <input type="hidden"  name="menu_id" value="${ map.menu_id }" />
  <input type="hidden"  name="nowpage" value="${ map.nowpage }" />
  <input type="hidden"  name="bnum"    value="${ map.bnum    }" />
  <input type="hidden"  name="lvl"     value="${ map.lvl     }" />
  <input type="hidden"  name="step"    value="${ map.step    }" />
  <input type="hidden"  name="nref"    value="${ map.nref    }" /> 
  <input type="hidden"  name="parent"  value="${ map.idx     }" /> 
 
  <table id="table">   
   <caption><h2>${ menuname } 자료실 정보 수정</h2></caption>
   <tr>
     <td>글번호</td>
     <td>${ vo.idx }</td>
     <td>작성일</td>
     <td>${ vo.regdate }</td>
   </tr>
   <tr>
     <td>작성자</td>
     <td>${ vo.writer }</td>
     <td>조회수</td>
     <td>${ vo.readcount }</td>
   </tr>
   <tr>
     <td>제목</td>
     <td colspan="3">
     <input type="text" name="title" value="${ vo.title }"/>
     </td>
   </tr> 
   <tr>
     <td>내용</td>
     <td colspan="3">
     <textarea name="cont" maxlength="1000">${ vo.cont }</textarea>
     </td>
   </tr> 
   <tr>
     <td>파일</td>   
     <td id="tdfile" colspan="3">
        <!-- 기존 파일 목록, 삭제버튼 -->
        <c:forEach  var="file"   items="${ fileList }">
           <div>
             <a  class  = "aDelete"
                 href="/deleteFile?file_num=${ file.file_num }&sfile=${file.sfilename}">
                             ❌
             </a>
             <a  href="/Pds/download/external/${ file.sfilename }">
             ${ file.filename }
             </a>
           </div>
        </c:forEach>  
        <br>     
        <!-- 새 파일 추가 -->
        <input type="button"  id="btnAddFile" value="파일 추가(최대 100M byte)" /><br>
        <input type="file"  name="upfile"  class="upfile"/><br>
     </td>
   </tr> 
     
   <tr>     
     <td colspan="4">
     <input type="submit" value="작성" />
     </td>
   </tr> 
  </table>
  </form>
  </div>
</body>
</html>








