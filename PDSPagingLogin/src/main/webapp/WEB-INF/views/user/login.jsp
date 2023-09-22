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
  <h2>로그인</h2>
  <form action="/loginprocess" method="POST" >
  <table>
   <tr>
     <th>아이디</th>
     <td><input type="text" name="userid" /></td>
   </tr> 
   <tr>
     <th>비밀번호</th>
     <td><input type="password" name="passwd" /></td>
   </tr> 
   <tr>     
     <td colspan="2">
     <input type="submit" value="로그인" />
     </td>
   </tr> 
  </table>
  </form>
  </div>
</body>
</html>








