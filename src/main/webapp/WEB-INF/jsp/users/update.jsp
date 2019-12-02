<%@ include file="../../include/header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<form action="/users/update/action" name="createForm">
    <table>
    <tr>
        <th>ID</th>
        <th><c:out value="${user.id}"/></th>
    </tr>
    <tr>
        <th>Name</th>
        <th><input type="text" id="name" name="name" value=<c:out value="${user.name}"/>></th>
    </tr>
    <tr>
        <th>Age</th>
        <th><input type="text" id="age" name="age" value=<c:out value="${user.age}"/>></th>
    </tr>
        <input type="hidden" id="seq" value="${user.seq}"/>
        <input type="hidden" id="id" value="${user.id}"/>
</table>
</form>
<input type="button" id="update" value="수정하기"/>
<script>
    $('#update').click(function() {
        var id = $('#id').val();
        var name = $('#name').val();
        var age = $('#age').val();
        var seq = $('#seq').val();

        $.ajax({
            url:'/users/create/action',
            type:'POST',
            data: {
                "id":id,
                "seq":seq,
                "name":name,
                "age":age
            },
            dataType:'json'
        }).done(function (result) {
            if(result.code) {
                alert('등록완료');
                location.href='/users/list';
            }
        });
    });
</script>
<%@ include file="../../include/footer.jsp" %>