<%@ include file="../../include/header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<form action="/users" name="createForm">
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Age</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td><input type="text" name="id"/></td>
                <td><input type="text" name="name"/></td>
                <td><input type="text" name="age"/></td>
            </tr>
        </tbody>
    </table>
    <input type="button" value="등록"/>
</form>
<%@ include file="../../include/footer.jsp" %>