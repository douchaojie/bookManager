<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" isErrorPage="true"%>  <!-- 注意：需要添加isErrorPage="true" -->  
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>数组越界</title>
</head>
<body>
碉堡了！！
<%  
out.write(exception.getMessage()); 
%>  

</body>
</html>