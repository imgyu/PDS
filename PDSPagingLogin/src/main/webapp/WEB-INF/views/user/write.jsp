<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>회원 등록</title>
<link rel="shortcut icon" href="/img/favicon.ico">
<link rel="stylesheet" href="/css/common.css" />

<style>
   th      { width : 20%;  }  
   td      { width : 80%;  }  
   input[type=text] ,  input[type=password], input[type=email]    { width:100%; } 
      
</style>

</head>
<body>
  <div id="main">
  <h2>회원 등록</h2>
  <form action="/User/Write" method="POST" >
  <table>
   <tr>
     <th>아이디</th>
     <td><input type="text" name="userid" /></td>
   </tr> 
   <tr>
     <th>비밀번호</th>
     <td><input type="password" name="passwd" id="pwd1" /></td>
   </tr> 
   <tr>
     <th>비밀번호 확인</th>
     <td><input type="password" id="pwd2" /></td>
   </tr> 
   <tr>
     <th>이름</th>
     <td><input type="text" name="username" /></td>
   </tr> 
   <tr>
     <th>이메일</th>
     <td><input type="email" name="email" /></td>
   </tr> 
   <tr>     
     <td colspan="2">
     <input type="submit" value="가입" />
     </td>
   </tr> 
  </table>
  </form>
  </div>
</body>
</html>








