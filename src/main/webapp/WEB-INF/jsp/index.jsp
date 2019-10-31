<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<table>
    <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Age</th>
        </tr>
    </thead>
    <tbody>
    <c:forEach var="item" items="${list}" varStatus="vs">
        <tr>
            <td><input type="checkbox" value="<c:out value='vs'/>"/></td>
            <td>${item.id}</td>
            <td>${item.name}</td>
            <td>${item.age}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<input type="button" id="regist" value="등록"/>
<input type="button" id="update" value="수정"/>
<input type="button" id="delete" value="삭제"/>