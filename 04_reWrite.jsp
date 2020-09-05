<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Reply</title>
</head>
<body>

<div align="center">
	<h2>Reply</h2>
	
	<form action="ReWriteActionPro method="post">
		<table border="1"> 
			<tr height="40">
				<td width="150" align="center">Writer</td>
				<td width="450"><input type="text" name="writer" size="60"></td>
			</tr>	
		
			<tr height="40">
				<td width="150" align="center">Subject</td>
				<td width="450"><input type="text" name="subject" value="[Reply]" size="60"></td>
			</tr>
		
			<tr height="40">
				<td width="150" align="center">Email</td>
				<td width="450"><input type="email" name="email" size="60"></td>
			</tr>
			
			<tr height="40">
				<td width="150" align="center">Password</td>
				<td width="450"><input type="password" name="password" size="60"></td>
			</tr>
		
			<tr height="40">
				<td width="150" align="center">Content</td>
				<td width="450"><textarea rows="10" cols="60" name="content"></</textarea></td>
			</tr>
		
			<tr height="40">
				<td align="center" colspan="2">
					<input type="submit" value="Reply"/>
					<input type="reset" value="Reset"/>
					<input type="button" value="View All Posts" onclick="location.href='BoardListAction'"/>
					
					<%-- hand on data without receiving them from the form --%>
					<input type="hidden" name="ref" value=${ref }>
					<input type="hidden" name="re_step" value=${re_step }>
					<input type="hidden" name="re_level" value=${re_level }>
					</td>
			</tr>
		</table>
	</form>
	
</div>

</body>
</html>