<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<?xml version="1.0" encoding="UTF-8" ?>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>Note4j &#8250; Update Account</title>
</head>
<body>
<h2>Update Account</h2>

<p>${message}</p>
<form:form method="POST" commandName="account" enctype="multipart/form-data"
           action="${pageContext.request.contextPath}/account.do?method=update&id=${account.id}&image">
    <table>
        <tbody>
        <tr>
            <td><form:label path="user_name">Name:</form:label></td>
            <td><form:input path="user_name" size="30"/><br/>
                <form:errors path="user_name" cssClass="error" cssStyle="color: red;"/>
            </td>
        </tr>
        <tr>
            <td><form:label path="phone">Phone:</form:label></td>
            <td><form:input path="phone" size="30" maxlength="15"/><br/>
                <form:errors path="phone" cssClass="error" cssStyle="color: red;"/>
            </td>
        </tr>
        <tr>
            <td><form:label path="address">Address:</form:label></td>
            <td><form:input path="address" size="30" maxlength="30"/><br/>
                <form:errors path="address" cssClass="error" cssStyle="color: red;"/>
            </td>
        </tr>
        <tr>
            <td><form:label path="mail">E-mail:</form:label></td>
            <td><form:input path="mail" size="30" maxlength="30"/><br/>
                <form:errors path="mail" cssClass="error" cssStyle="color: red;"/>
            </td>
        </tr>
        <tr>
            <td><label>Profile image:</label></td>
            <td>
                <input name="image" type="file"/><br/>
                <form:errors commandName="image" path="" cssClass="error" cssStyle="color: red;"/>
            </td>
        </tr>
        <tr>
            <td><input type="submit" value="Update"/></td>
            <td></td>
        </tr>
        </tbody>
    </table>
</form:form>

<p>
    <a href="${pageContext.request.contextPath}/index.html">Home page</a>
</p>

</body>
</html>