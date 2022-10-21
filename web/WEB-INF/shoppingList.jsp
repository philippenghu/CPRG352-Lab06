<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Shopping List</h1>
        <div>
            Hello,${user.username} 


        </div>
        <div>
            <a href="ShoppingList?action=logout">Logout</a>
        </div>
        <br>
        <h2>List</h2>
        <br>

        <form method="post">
            <div>
                <label>Add item:</label>
                <input type="text" name="itemInput" value="${itemInput}" >
                <input type="submit"  value="add" >
                <input type="hidden" name="action" value="add">
            </div>
        </form>   



        <form method="post">
            <c:if test="${items.size()>0}">
                <ul>
                    <c:forEach items="${items}" var="item">
                        <li><input type="radio" name="itemR" value=${item}>${item}</li>
                        </c:forEach>
                </ul>
                <input type="submit" value="Delete" >
                <input type="hidden" name="action" value="delete">
            </c:if>
        </form>
    </body>
</html>
