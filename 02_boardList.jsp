<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View All Posts</title>
</head>
<body>

	<div align="center">
	<h2>See All Posts</h2>
	
	<table widht="700" border="1">
		<tr height="40">
			<td colspan="5" align="right">
				<button onclick="location.href='01_write.jsp'">Write a Post</button>
				</td>
		</tr>
		<tr height="40">
			<th width="50" align="center">Number</th>
			<th width="320" align="center">Subject</th>
			<th width="100" align="center">Writer</th>
			<th width="150" align="center">Date Posted</th>
			<th width="80" align="center">Views</th>
		</tr>
		<c:set var="number" value="${number }"/>
			
		<c:forEach var="bean" items="${v }">
		<tr height="40">
			<td width="50" align="center"> ${number}</td>
			<td width="320" align="left"> 
				<%-- adding tabs for replies --%>
				<c:if test="${bean.re_step > 1 }">
					<c:forEach var="i" bean="1" end="${bean.re_step-1 } * 5">
						&nbsp;
					</c:forEach>
				</c:if>
				<a href="InfoAction?num=${bean.num }">${bean.subject }</a>
				</td>
			<td width="100" align="center">${bean.writer }</td>
			<td width="150" align="center">${bean.reg_date }</td>
			<td width="80" align="center">${bean.readcount }</td>
		</tr>
		<c:set var="number" value="${number - 1 }" />
		</c:forEach>
			
		</table>
		<br>
		
		<%-- Code for Page Count --%>
		<c:if test="${count > 0 }">   <%-- If there are more than 0 posts --%>
		
			<%-- used for startPage --%>
			<fmt:parseNumber var="result" value="${currentPage/10 }" 
				integerOnly="true" />
			<%-- used for endPage --%>
			<c:set var="pageCount" value=
			"${count / pageSize + (count % pageSize == 0 ? 0 : 1) }" />
			<fmt:parseNumber var="pageCount value="${pageCount }" 
				integerOnly="true" />
			
			<%-- startPage --%>
			<c:if test="${currentPage % 10 ne 0 }"> <%-- If current Page# is not 10, 20, 30... --%>
				<c:set var="startPage" value="${(result) * 10 + 1 }" />
				<%-- starts the page count with 1, 11, 21, 31... --%>
			</c:if>
			
			<c:if test="${currentPage % 10 eq 0 }"> <%-- If current Page# is 10, 20, 30... --%>
				<c:set var="startPage" value="${(result-1) * 10 + 1}" />
				<%-- starts the page count with 1, 11, 21, 31... --%> 
			</c:if>
			
			<%-- endPage (shows 10 at a time) --%>
			<c:set var="endPage" value="${startPage + 10 - 1 }" />
			<c:if test="${endPage > pageCount }">
				<c:set var="endPage value="${pageCount }" />
			</c:if>
			
			
			<%-- displays [Prev] [1] [2] [Next] --%>
			<c:if test="${startPage > 10 }">
				<a href="BoardListAction?pageNum=${startPage-10 }">[Prev]</a>
			</c:if>
			
			<c:forEach var="i" begin="${startPage }" end="${endPage }">
				<a href="BoardListAction?pageNum=${i }">[${i}]</a>
			</c:forEach>
			
			<c:if test="${endPage < pageCount }">
				<a href="BoardListAction?pageNum=${startPage+10 }">[Next]</a>
			</c:if>

		</c:if>
		

	</div>

</body>
</html>