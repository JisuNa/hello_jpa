<%@ include file="../include/header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<form action="/update/action">
    <table>
        <thead>
            <tr>
                <th>선택</th>
                <th>ID</th>
                <th>Name</th>
                <th>Age</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach var="item" items="${list}" varStatus="vs">
            <tr>
                <td><input type="checkbox" name="seq" value="${vs.index}"/></td>
                <td><a href="/users/view/${item.seq}">${item.id}</a></td>
                <td>${item.name}</td>
                <td>${item.age}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</form>
<input type="button" id="regist" value="등록"/>
<input type="button" id="update" value="수정"/>
<input type="button" id="delete" value="삭제"/>

<script>
    $('#regist').click(function () {
        location.href='/users/create';
    });

    $('#update').click(function () {
        var checked = $('input[name=seq]:checked');
        console.log($('input[name=seq]:checked').length);
        if(checked.length===0) {
            alert('선택된게 없어요');
            return;
        } else if($checked.length > 2) {
            alert('1개만 선택하세요.');
        }
        location.href='/users/update';
    });

    $('#delete').click(function () {

    });
</script>
<%@ include file="../include/footer.jsp" %>