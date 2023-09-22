<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>회원 등록</title>
<link rel="shortcut icon" href="/img/favicon.ico">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<link rel="stylesheet" href="/css/common.css" />

<style>
   th      { width : 20%;  }  
   td      { width : 80%;  }  
   input[type=text] ,  input[type=password], input[type=email]    { width:100%; } 
      
</style>


</head>
<body>
  <div id="main">
  <h2>회원 수정</h2>
  <form action="/User/Update" method="POST" >
  <table>
   <tr>
     <th>아이디</th>
     <td><input type="text" name="userid" value="${ user.userid }" readonly /></td>
   </tr> 
   <tr>
     <th>비밀번호</th>
     <td><input type="password" name="passwd" id="pwd1"  /></td>
   </tr> 
   <tr>
     <th>비밀번호 확인</th>
     <td><input type="password" id="pwd2" /></td>
   </tr> 
   <tr>
     <th>이름</th>
     <td><input type="text" name="username" value="${ user.username }" /></td>
   </tr> 
   <tr>
     <th>이메일</th>
     <td><input type="email" name="email"  value="${ user.email }" /></td>
   </tr> 
   <tr>
     <th>가입일</th>
     <td><input type="indate"  value="${ user.indate }" readonly /></td>
   </tr> 
   <tr>     
     <td colspan="2">
     <input class="btn btn-primary btn-sm" type="submit" value="수정" />
     <a id="abtn" class="btn btn-primary btn-sm" href="http://www.naver.com">수정</a>
     <div id="divbtn" class="btn btn-primary btn-sm" >수정</div>
     <hth id="hthbtn" class="btn btn-primary btn-sm" >수정</hth>
     </td>
   </tr> 
  </table>
  </form>
  </div>
  
  <script>
     let  form = document.querySelector('form');
      
     let  abtnEl = document.getElementById('abtn');
     abtnEl.addEventListener('click', function(e) {
    	 e.preventDefault();
    	 e.stopPropagation();
     	 alert('a click');
     	 let passwd1El = document.getElementById('pwd1'); 
    	 let passwd2El = document.getElementById('pwd2');
    	 
    	 if(passwd1El.value != passwd2El.value) {    		 
    		 e.preventDefault();
        	 e.stopPropagation();	 
    		 alert('비밀번호가 일치하지 않습니다');
    	 }
     	 // sform.action = '';
     	 form.submit();    // js 이 함수는 onsubmit 이벤트를 발생시키지 않는다 	 
     });
    
     form.onsubmit = function( e ) {
    	    	 
    	 let passwd1El = document.getElementById('pwd1'); 
    	 let passwd2El = document.getElementById('pwd2');
    	 
    	 if(passwd1El.value != passwd2El.value) {    		 
    		 e.preventDefault();
        	 e.stopPropagation();	 
    		 alert('비밀번호가 일치하지 않습니다');
    	 }
     }
  </script>
  
</body>
</html>








