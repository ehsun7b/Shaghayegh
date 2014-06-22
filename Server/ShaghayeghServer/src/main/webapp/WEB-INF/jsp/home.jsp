<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
    </head>
    <body>
        <h1>List of All Contact Groups</h1>
        <a href="/logout">Sign Out</a>
        <br/><br/>        
        
        <c:forEach items="${groups}" var="group">
            <a href="group?time=${group.getTime()}">${group}</a><br/>
        </c:forEach>
    </body>
</html>
