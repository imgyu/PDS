<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>회원정보</title>
<link rel="shortcut icon" href="/img/favicon.ico">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<link rel="stylesheet" href="/css/common.css" />

<style>
   a:hover { text-decoration: none;  }
   td:nth-of-type(1)  { width : 20%;  }  
   td:nth-of-type(2)  { width : 80%;  }  
   tr:last-child > td { text-align:center;  }
</style>

</head>
<body>
  <div id="main">
     <h2>회원 정보 보기</h2>
     <table>
       <tr>
        <td>아이디</td> 
        <td>${ user.userid }</td> 
       </tr>
       <tr>
        <td>암호</td> 
        <td>${ user.passwd }</td> 
       </tr>
       <tr>
        <td>이름</td> 
        <td>${ user.username }</td> 
       </tr>
       <tr>
        <td>이메일</td> 
        <td>${ user.email }</td> 
       </tr>
       <tr>
        <td>가입일</td> 
        <td>${ user.indate }</td> 
       </tr>
       <tr>
        <td  colspan="2">
          <a class="btn btn-primary btn-sm" href="/User/WriteForm">회원가입</a>&nbsp;&nbsp;
          <a class="btn btn-primary btn-sm" href="/User/UpdateForm?userid=${ user.userid }">회원수정</a>&nbsp;&nbsp;
          <a class="btn btn-primary btn-sm" href="/User/Delete?userid=${ user.userid }">회원삭제</a>&nbsp;&nbsp;
          <a class="btn btn-primary btn-sm" href="/User/List">회원목록</a>&nbsp;&nbsp;
          <a class="btn btn-primary btn-sm" href="/">Home</a>
        </td>
       </tr>
     
     </table>
  </div>
</body>
</html>







