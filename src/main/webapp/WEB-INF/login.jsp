<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
</head>
<body>
	<form action="login" method="post">
		<div>
			<div>
				<div style="display: inline-block">用户名</div>
				<div style="display: inline-block">
					<input type="text" id="userName" name="userName"/>
				</div>
			</div>
			<div>
				<div style="display: inline-block" >密码</div>
				<div style="display: inline-block">
					<input type="text" id="passWord"  name="passWord"/>
				</div>
			</div>
			 <input type="submit" value="Submit" />
		</div>
	</form>
</body>
</html>