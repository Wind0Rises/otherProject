<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>shiro标签</title>
</head>
<body>
<div class="login">
    <h2>Shiro  JSP  标签</h2>
    
    <shiro:hasPermission name="sys:shiro">
        <div>
            <span>this is hasPremisson</span>
        </div>
    </shiro:hasPermission>
</div>
</body>
</html>

