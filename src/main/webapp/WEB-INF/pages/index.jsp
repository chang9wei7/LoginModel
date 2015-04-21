<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<?xml version="1.0" encoding="UTF-8" ?>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>Note4j &#8250; Login</title>
</head>
<body>
<h1>Login</h1>

<p>${message}</p>
<form:form method="POST" commandName="account"
           action="${pageContext.request.contextPath}/account.do?method=login">
    <table>
        <tbody>
        <tr>
            <td>Name:</td>
            <td><form:input path="user_name"/></td>
        </tr>
        <tr>
            <td>Passwd:</td>
            <td><form:input type="password" path="password"/></td>
        </tr>
        <tr>
            <td><input type="submit" value="Login"/></td>
            <td></td>
        </tr>
        </tbody>
    </table>
</form:form>

<p>
    <a href="${pageContext.request.contextPath}/index.html">Home page</a>|<a
        href="${pageContext.request.contextPath}/account.do?method=register">Register</a>
</p>
</body>
</html>