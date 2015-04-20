<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<?xml version="1.0" encoding="UTF-8" ?>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>账户信息</title>
</head>
<body>
<h1>Account Info</h1>

<p>${message}</p>
<table border="1px" cellpadding="0" cellspacing="0">
    <thead>
    <tr>
        <th width="10%">id</th>
        <th width="15%">name</th>
        <th width="30%">address</th>
        <th width="10%">phone</th>
        <th width="10%">操作</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td>${account.id}</td>
        <td>${account.user_name}</td>
        <td>${account.address}</td>
        <td>${account.phone}</td>
        <td><a
                href="${pageContext.request.contextPath}/account.do?method=edit&id=${account.id}">Edit</a><br/>
        </td>
    </tr>

    </tbody>
</table>

<p>
    <a href="${pageContext.request.contextPath}/index.html">Home page</a>
</p>

</body>
</html>