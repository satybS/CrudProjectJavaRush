<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
<html>
<head>
    <title>Users page</title>
    <LINK REL=StyleSheet HREF="style.css" TYPE="text/css" MEDIA=screen>
    <style type="text/css">
     body {
            font-family: arial, helvetica, sans-serif;
            font-size: 80%;
            color: black;
            background-color: #f5f5f0;
            margin: 1em;
            padding: 0;
            }

            h1 {
                color:#1a1a1a;
                margin-top: 10px;
                margin-bottom: 0;
            }

            .tg {
                border-collapse: collapse;
                border-spacing: 0;
                border-color: #ccc;
                background-color: #f9f9f9
            }

            .tg td {
                font-family: Arial, sans-serif;
                font-size: 14px;
                padding: 10px 5px;
                border-style: solid;
                border-width: 1px;
                overflow: hidden;
                word-break: normal;
                border-color: #ccc;
                color: #333;
                background-color: #fff;
            }

            .tg th {
                font-family: Arial, sans-serif;
                font-size: 14px;
                font-weight: normal;
                padding: 10px 5px;
                border-style: solid;
                border-width: 1px;
                overflow: hidden;
                word-break: normal;
                border-color: #ccc;
                color: #333;
                background-color: #f0f0f0;
            }

            ul.pagination {
                display: inline-block;
                margin-bottom: 10px;
                padding: 0;
            }

            ul.pagination li {
                display: inline;
                border: 1px solid #ddd;
                color: black;
                float: left;
                font-size: 18px;
                padding: 8px 16px;
                text-decoration: none;
                transition: background-color 0.3s ease 0s;
            }

    </style>
</head>
<body>
    <a href="../../index.jsp">Back to main menu</a>
    <br/>
    <br/>
    <table class="tg">
        <tr>
            <th>
                <c:if test="${!empty user.name}">Edit existing user</c:if>
                <c:if test="${empty user.name}">Add a new user</c:if>
            </th>
        </tr>
        <tr>
            <td>
                <c:url var="addAction" value="/users/add"/>
                <form:form action="${addAction}" commandName="user">
                    <table>
                        <c:if test='${!empty user.name}'>
                            <tr>
                                <td><form:label path="id"><spring:message text="ID"/></form:label></td>
                                <td><form:input path="id" readonly="true" size="8" disabled="true"/><form:hidden path="id"/></td>
                            </tr>
                        </c:if>

                            <td><form:label path="name"><spring:message text="Name"/></form:label></td>
                            <td><form:input path="name"/></td>


                            <td><form:label path="age"><spring:message text="Age"/></form:label></td>
                            <td><form:input path="age"/></td>


                            <td><form:label path="admin"><spring:message text="Admin"/></form:label></td>
                            <td>
                                <form:radiobutton path="admin" value="true"/>Yes
                                <form:radiobutton path="admin" value="false"/>No
                            </td>


                            <td colspan="2">
                                <c:if test="${!empty user.name}"><input type="submit" value="<spring:message text="Edit User"/>"/></c:if>
                                <c:if test="${empty user.name}"><input type="submit" value="<spring:message text="Add User"/>"/></c:if>
                            </td>

                    </table>
                </form:form>
            </td>

        </tr>
    </table>

    <table class ="tg">
    <th>Search User in Database</th>
    <tr>
    <td>
                <c:url var="searchuser" value="/searchresults"/>
                <form:form action="${searchuser}" commandName="searcheduser">
                    <table>
                        <tr>
                            <td><form:label path="name"><spring:message text="Name"/></form:label></td>
                            <td><form:input path="name"/></td>
                            <td><input type="submit" name="action" value="<spring:message text="Search"/>"/></td>
                        </tr>
                    </table>
                </form:form>
            </td>
            </tr>
    </table>

    <div>
        <h1>Users List</h1>
        <%--<c:url var="refreshUserList" value="/users"/>--%>
        <%--<form:form action="${refreshUserList}" method="GET">--%>
            <%--<input type="submit" value="<spring:message text="Show All Users"/>"/>--%>
        <%--</form:form>--%>
        <ul class="pagination">
            <c:if test="${page > 1}">
                <li><a href="<c:url value="/users"/>">&#60;&#60;</a></li>
                <li><a href="<c:url value="/users"><c:param name="page" value="${page - 1}"/>${page - 1}</c:url>">&#60;</a></li>
            </c:if>
            <li><spring:message text="${page}"/></li>
            <c:if test="${!empty listUsers}">
                <li><a href="<c:url value="/users"><c:param name="page" value="${page + 1}"/>${page + 1}</c:url>">&#62;</a></li>
            </c:if>
        </ul>
    </div>
    <c:if test="${!empty listUsers}">
        <table class="tg">
            <tr>
                <th width="60">ID</th>
                <th width="200">Name</th>
                <th width="50">Age</th>
                <th width="50">Admin</th>
                <th width="220">Creation date</th>
                <th width="50">Edit</th>
                <th width="50">Delete</th>
            </tr>
            <c:forEach items="${listUsers}" var="user">
                <tr>
                    <td>${user.id}</td>
                    <td><a href="/userdata/${user.id}" target="_blank">${user.name}</a></td>
                    <td>${user.age}</td>
                    <td>${user.admin}</td>
                    <td>${user.createdDate}</td>
                    <td><a href="<c:url value='/edit/${user.id}'/>">Edit</a></td>
                    <td><a href="<c:url value='/remove/${user.id}'/>">Delete</a> </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
    <br/>
    <br/>


</body>
</html>
