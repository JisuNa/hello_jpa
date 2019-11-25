<%@ include file="../../include/header.jsp" %>
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
                <td><input type="checkbox" name="seqs[]" class="seq" value="${vs.index}"/></td>
                <td><a href="/users/view/${item.seq}">${item.id}</a></td>
                <td>${item.name}</td>
                <td>${item.age}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</form>
<input type="button" id="regist" value="등록"/>
<input type="button" id="delete" value="삭제"/>

<script>
    $('#regist').click(function () {
        location.href='/users/create';
    });

    $('#delete').click(function () {
        var seqs = $('input[name="seqs[]"]:checked');

        var seq = [];
        var $checked = $('.seq').is(':checked');
        if($checked.length===0) {
            alert('선택된게 없어요');
            return;
        } else if($checked.length > 2) {
            alert('1개만 선택하세요.');
        }

        seqs.each(function(idx, item) {
            seq.push($(item).val());
        });



        $.ajax({
            url:'/users/delete',
            type:'POST',
            contentType : "application/json; charset=UTF-8",
            data: JSON.stringify({
                "seq":seq
            }),
            dataType:'json'
        }).done(function (result) {
            if(result.result_code) {
                alert('삭제완료');
                location.href='/users/list';
            }
        });
    });

</script>
<%@ include file="../../include/footer.jsp" %>