<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@taglib  prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
  
  <c:set  var="menu_id"            value="${ pdsPagingVo.menu_id         }" /> 
  <c:set  var="startnum"           value="${ pdsPagingVo.pagestartnum    }" /> 
  <c:set  var="endnum"             value="${ pdsPagingVo.pageendnum      }" /> 
  <c:set  var="totalpagecount"     value="${ pdsPagingVo.totalpagecount  }" /> 
  
  <div>
    <table width="500" height="25"  align="center"  >
     <tr>
       <td width="500" height="25"  align="center" >
     
     <!-- 처음/ 이전 -->     
     <c:if test="${ startnum gt 1 }">
       <a href="/Pds/List?menu_id=${ menu_id }&nowpage=1">⏮</a>
       <a href="/Pds/List?menu_id=${ menu_id }&nowpage=${ startnum - 1 }">
       ⏪
       </a>
     </c:if>
     
     <!-- 1 2 3 4 5 6 [7] 8 9 10  -->
     <c:forEach  var="pagenum"  begin="${startnum}"  end="${endnum}"  step="1">
        <a href="/Pds/List?menu_id=${ menu_id }&nowpage=${ pagenum }">
        ${ pagenum }
        </a>&nbsp;&nbsp;     
     </c:forEach>    
     
     <!-- 다음 / 마지막 -->
     <c:if test="${ totalpagecount ne endnum }">
       <a href="/Pds/List?menu_id=${ menu_id }&nowpage=${ endnum + 1 }">
       ⏩
       </a>
       <a href="/Pds/List?menu_id=${ menu_id }&nowpage=${ totalpagecount }">⏭</a>
     </c:if>    
     
      </td>
     </tr>    
    </table>   
  </div>
  
  
  
  
  
  
  
  
  