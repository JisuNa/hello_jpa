<%@ include file="../../include/header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<table>
    <tr>
        <th>ID</th>
        <th><c:out value="${user.id}"/></th>
    </tr>
    <tr>
        <th>Name</th>
        <th><c:out value="${user.name}"/></th>
    </tr>
    <tr>
        <th>Age</th>
        <th><c:out value="${user.age}"/></th>
    </tr>
</table>
<input type="hidden" id="seq" value="${user.seq}"/>
<input type="button" id="update" value="수정"/>
<script>
    $('#update').click(function() {
        location.href='/users/update/'+$('#seq').val();
    });
</script>
<%@ include file="../../include/footer.jsp" %>