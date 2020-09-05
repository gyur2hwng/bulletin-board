<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View One Post</title>
</head>
<body>

<div align="center">
	<h2>View a Post</h2>
	<table border="1">
		<tr height="40">
			<td align="center" width="120"> Post Number </td>
			<td align="center" width="180"> ${bean.num} </td>
			<td align="center" width="120"> Views </td>
			<td align="center" width="180"> ${bean.readcount} </td>
		</tr>
		
		<tr>
			<td align="center" width="120"> Writer </td>
			<td align="center" width="180"> ${bean.writer} </td>
			<td align="center" width="120"> Date Posted </td>
			<td align="center" width="180"> ${bean.reg_date} </td>
		</tr>
		
		<tr>
			<td align="center" width="120"> Email </td>
			<td align="center" colspan="3"> ${bean.email} </td>
		</tr>
		
		<tr>
			<td align="center" width="120"> Subject </td>
			<td align="center" colspan="3"> ${bean.subject} </td>
		</tr>
		
		<tr>
			<td align="center" width="120"> Content </td>
			<td align="center" colspan="3"> ${bean.content} </td>
		</tr>
		
		<tr>
			<td align="center" colspan="4"> 
				<input type="button" value="Reply" onclick="location.href='ReWriteAction?num=${bean.num}'">
				<input type="button" value="Edit" onclick="location.href='UpdateAction?num=${bean.num}'">
				<input type="button" value="Delete" onclick="location.href='DeleteAction?num=${bean.num}'">
				<input type="button" value="View All Posts" onclick="location.href='BoardListAction'">
				</td>
		</tr>
	</table>

</div>


</body>
</html>