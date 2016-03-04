<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>上报频率管理</title>
</head>
<body>
<form action="SenderAction!edit.action" method="post">
原上报频率： ${value}
<br>
更新频率为：<input type="text" name="sendValue" >
<input type="submit" value="提交">
</form>
</body>
</html>