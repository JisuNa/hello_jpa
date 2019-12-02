<%@ include file="../../include/header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div style="width:100%; text-align: center">
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
                    <td><input type="text" id="id" name="id"/></td>
                    <td><input type="text" id="name" name="name"/></td>
                    <td><input type="text" id="age" name="age"/></td>
                </tr>
            </tbody>
        </table>
        <input type="button" id="regist" value="등록"/>
    </form>
</div>
<script>
    $('#regist').click(function() {

        var id = $('#id').val();
        var name = $('#name').val();
        var age = $('#age').val();

        $.ajax({
            url:'/users/create/action',
            type:'POST',
            data: {
                "id":id,
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