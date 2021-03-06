<%@ include file="../../include/header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<input type="button" id="regist" value="등록"/>
<input type="button" id="devare" value="삭제"/>
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
                <td><input type="checkbox" name="seqs[]" class="seq" value="${item.seq}"/></td>
                <td><a href="/users/view/${item.seq}">${item.id}</a></td>
                <td>${item.name}</td>
                <td>${item.age}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</form>
<br/>
검색어<input type="text" name="keyword" id="keyword" value=<c:out value="${keyword}"/>>
<input type="button" name="search" id="search" value="조회"/>
<br/>
<c:if test="${pagenation.curPage > 0}">
    <a href="#" onclick="UserList.callPage('0')">[처음]</a>
</c:if>

<!-- **이전페이지 블록으로 이동 : 현재 페이지 블럭이 1보다 크면 [이전]하이퍼링크를 화면에 출력 -->
<c:if test="${pagenation.curPage > 0}">
    <a href="#" onclick="UserList.callPage('${pagenation.prevPage}')">[이전]</a>
</c:if>
<c:forEach var="num" begin="${pagenation.blockBegin}" end="${pagenation.blockEnd}">
    <!-- **현재페이지이면 하이퍼링크 제거 -->
    <c:choose>
        <c:when test="${num-1 == pagenation.curPage}">
            <span style="color: red">${num}</span>
        </c:when>
        <c:otherwise>
            <a href="#" onclick="UserList.callPage('${num-1}')">${num}</a>
        </c:otherwise>
    </c:choose>
</c:forEach>
<c:if test="${pagenation.curPage+1 < pagenation.totPage}">
    <a href="#" onclick="UserList.callPage('${pagenation.nextPage}')">[다음]</a>
</c:if>

<!-- **끝페이지로 이동 : 현재 페이지가 전체 페이지보다 작거나 같으면 [끝]하이퍼링크를 화면에 출력 -->
<c:if test="${pagenation.curPage+1 < pagenation.totPage}">
    <a href="#" onclick="UserList.callPage('${pagenation.totPage-1}')">[끝]</a>
</c:if>

<script>

    $(document).ready(() => {
        UserList.init();
    });

    let UserList = {
        init : () => {

        },

        callPage : (pageNum) => {
            let keyword = $('#keyword').val();
            location.href='/users/list?page='+pageNum+'&keyword='+keyword;
        }
    }

    $('#regist').click(() => {
        location.href='/users/create';
    });

    $('#devare').click(() => {
        let seqs = $('input[name="seqs[]"]:checked');

        let seq = [];
        let $checked = $('.seq').is(':checked');
        if($checked.length===0) {
            alert('선택된게 없어요');
            return;
        } else if($checked.length > 2) {
            alert('1개만 선택하세요.');
        }

        seqs.each((idx, item) => {
            seq.push($(item).val());
        });

        $.ajax({
            url:'/users/devare',
            type:'POST',
            contentType : "application/json; charset=UTF-8",
            data: JSON.stringify({
                "seq":seq
            }),
            dataType:'json'
        }).done(function (result) {
            if(result.code) {
                alert('삭제완료');
                location.href='/users/list';
            }
        });
    });

</script>
<%@ include file="../../include/footer.jsp" %>