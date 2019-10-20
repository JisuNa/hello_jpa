<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<table>
    <thead>
        <tr>
            <td>ID</td>
            <td>Name</td>
            <td>Age</td>
        </tr>
    </thead>
    <tbody>
    <c:forEach var="item" items="${list}">
        <tr>
            <td>${item.id}</td>
            <td>${item.name}</td>
            <td>${item.age}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>