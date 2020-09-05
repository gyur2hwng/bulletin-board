<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit</title>
</head>
<body>

<div align="center">
	<h2>Edit</h2>
	
	<form action="UpdateActionPro" method="post">
		<table border="1">
			<tr height="40">
				<td width="120" align="center"> Writer </td>
				<td width="180" align="center"> ${ bean.writer}</td>
				<td width="120" align="center"> Date Posted </td>
				<td width="180" align="center">  ${bean.reg_date}</td>
			</tr>
				
			<tr height="40">
				<td width="120" align="center"> Subject </td>
				<td width="480" colspan="3"> &nbsp; 
					<input type="text" name="subject" value=" ${ bean.subject}" size="60" />
					</td>				
			</tr>
			
			<tr height="40">
				<td width="120" align="center"> Password </td>
				<td width="480" colspan="3"> &nbsp;
					<input type="password" name="password" size="60" />
					</td>
			</tr>
				
			<tr height="40">
				<td width="120" align="center"> Content </td>
				<td width="480">
					<textarea rows="10" cols="60" name="content">${ bean.content}</textarea>
					</td>
			</tr>
				
			<tr height="40">
				<td colspan="4" align="center">
					<input type="hidden" name="num" value="${ bean.num}" />
					<input type="submit" value="Edit" /> &nbsp;&nbsp;
					<input type="button" onclick="location.href='BoardListAction'" value="View All Posts" />
					</td>
				</tr>
		</table>
	</form>
	
</div>
</body>
</html>