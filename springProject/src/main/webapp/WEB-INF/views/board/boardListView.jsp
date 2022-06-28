<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="./resources/css/boardListViewStyle.css"/>
</head>
<body>
    
    <jsp:include page="../common/header.jsp" />

    <div class="content">
        <br><br>
        <div class="innerOuter" style="padding:5% 10%;">
            <h2>게시판</h2>
            <br>
            <!-- 로그인 후 상태일 경우만 보여지는 글쓰기 버튼 -->
            <c:if test="${not empty loginUser }">
            	<a class="btn btn-secondary" style="float:right;" href="enrollForm.bo">글쓰기</a>
            </c:if>
            <br>
            <br>
            <table id="boardList" class="table table-hover" align="center">
                <thead>
                    <tr>
                        <th>글번호</th>
                        <th>제목</th>
                        <th>작성자</th>
                        <th>조회수</th>
                        <th>작성일</th>
                        <th>첨부파일</th>
                    </tr>
                </thead>
                <tbody>
	                <c:forEach var="b" items="${list }">
	                	<tr>
		                	<td>${b.boardNo }</td>
		                	<td>${b.boardTitle }</td>
		                	<td>${b.boardWriter }</td>
		                	<td>${b.count }</td>
		                	<td>${b.createDate }</td>
		                	<td>
			                	<c:if test="${not empty b.originName }">
			                		★
			                	</c:if>
		                	</td>
	                	</tr>
	                </c:forEach>
                </tbody>
            </table>
            <br>
			<script>
				$(function(){
					$(".table>tbody>tr").click(function(){
						location.href="detail.bo?bno=" + $(this).children().eq(0).text();
					});
				});
			</script>
            <div id="pagingArea">
                <ul class="pagination">
                	<c:choose>
	                	<c:when test="${pi.currentPage ne 1 }">
		                    <li class="page-item"><a class="page-link" href="list.bo?cpage=${pi.currentPage -1}">Previous</a></li>
	                	</c:when>
	                	<c:otherwise>
	                		<li class="page-item disabled"><a class="page-link" href="#">Previous</a></li>
	                	</c:otherwise>
                	</c:choose>
                	<c:forEach var="i" begin="${pi.startPage }" end="${pi.endPage }">
	                	<c:choose>
		                	<c:when test="${i ne pi.currentPage }">
		                		<li class="page-item"><a class="page-link" href="list.bo?cpage=${i}">${i}</a></li>
		                	</c:when>
		                	<c:otherwise>
		                		<li class="page-item disabled"><a class="page-link" href="#">${i}</a></li>
	                		</c:otherwise>
	                	</c:choose>
                	</c:forEach>
                	<c:choose>
	                    <c:when test="${pi.currentPage ne pi.maxPage }">
	                    	<li class="page-item"><a class="page-link" href="list.bo?cpage=${pi.currentPage + 1}">Next</a></li>
	                    </c:when>
	                    <c:otherwise>
	                    	<li class="page-item disabled"><a class="page-link" href="#">Next</a></li>
	                    </c:otherwise>
                    </c:choose>
                </ul>
            </div>

            <br clear="both"><br>

            <form id="searchForm" action="" method="get" align="center">
                <div class="select">
                    <select class="custom-select" name="condition">
                        <option value="writer">작성자</option>
                        <option value="title">제목</option>
                        <option value="content">내용</option>
                    </select>
                </div>
                <div class="text">
                    <input type="text" class="form-control" name="keyword">
                </div>
                <button type="submit" class="searchBtn btn btn-secondary">검색</button>
            </form>
            <br><br>
        </div>
        <br><br>

    </div>

    <jsp:include page="../common/footer.jsp" />

</body>
</html>