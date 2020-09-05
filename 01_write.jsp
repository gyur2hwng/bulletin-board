<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Write a Post</title>
</head>
<body>

	<div align="center">
		<h2>Write a Post</h2>
		<form action="BoardWriteAction" method="post">
			<table border="1">
				<tr height="40">
					<td align="center" width="150">Writer</td>
					<td width="450"><input type="text" name="writer" size="60" /></td>
				</tr>
				<tr height="40">
					<td align="center" width="150">Subject</td>
					<td width="450"><input type="text" name="subject" size="60" /></td>
				</tr>
				<tr height="40">
					<td align="center" width="150">Email</td>
					<td width="450"><input type="text" name="email" size="60" /></td>
				</tr>
				<tr height="40">
					<td align="center" width="150">Password</td>
					<td width="450"><input type="text" name="password" size="60" /></td>
				</tr>
				<tr height="40">
					<td align="center" width="150">Content</td>
					<td width="450"><textarea rows="10" cols="50" name="content"></textarea></td>
				</tr>
				<tr height="40" colspan="2"> 
					<input type="submit" value="Post" /> &nbsp; &nbsp;
					<input type="reset" value="Rewrite" />
				</tr>
			</table>
		</form>
		<button onclick="location.href='BoardListAction'">View All Posts</button>
	</div>

</body>
</html>

<%--

include this resource on server.xml 

<Resource 
auth="Container" 
driverClassName="com.mysql.cj.jdbc.Driver" 
maxWait="5000" 
name="jdbc/pool" 
password="root" (depends on the password for your local host)
type="javax.sql.DataSource"
url="jdbc:mysql://localhost:3306/boardmvcdb05?serverTimezone=UTC" 
username="root"/>

 --%>