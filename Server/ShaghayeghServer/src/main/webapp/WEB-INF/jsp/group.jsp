<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Contacts</title>
    </head>
    <body>
        <h1>Contacts</h1>
        <c:forEach items="${contacts}" var="contact">
            <table class="contact" border="0" >
                <tr>
                    <td class="caption">Server ID</td>
                    <td class="value">${contact.serverId}</td>
                </tr>
                <tr>
                    <td class="caption">Mobile ID</td>
                    <td class="value">${contact.id}</td>
                </tr>
                <tr>
                    <td class="caption">Name</td>
                    <td class="value">${contact.name}</td>
                </tr>
                <tr>
                    <td class="caption">Numbers</td>
                    <td class="value">
                        <c:forEach items="${contact.numbers}" var="number">
                            ${number}
                            <br/>
                        </c:forEach>
                    </td>
                </tr>
                
                <tr>
                    <td class="caption">Email Addresses</td>
                    <td class="value">
                        <c:forEach items="${contact.emails}" var="email">
                            ${email}
                            <br/>
                        </c:forEach>
                    </td>
                </tr>
            </table>
            <br/>
        </c:forEach>
    </body>
</html>
