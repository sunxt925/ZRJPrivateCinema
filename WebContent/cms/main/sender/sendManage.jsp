<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>上报频率管理</title>

</head>
<body class="sender">
<form action="SenderAction!edit.action" method="post" class="sender_form"  onsubmit="return toVaild()">
<br>
<br>
<br>
<div class="sender_form">原上报频率： <span class="sender_old">${value}</span></div>
<br>
更新频率为：<input  type="number" min="1" id="sendValue" name="sendValue" class="sender_text" 
 onmouseout="this.style.backgroundColor='#ffffff'"  required style="height: 16px; "><br/>
<br>
<input type="submit" value="提  交" class="sender_submit" >
</form>


</body>
</html>