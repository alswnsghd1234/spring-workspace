<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="./resources/css/boardDetailViewStyle.css"/>
</head>
<body>
        
    <jsp:include page="../common/header.jsp" />

    <div class="content">
        <br><br>
        <div class="innerOuter">
            <h2>게시글 상세보기</h2>
            <br>

            <a class="btn btn-secondary" style="float:right;" href="list.bo?cpage=1">목록으로</a>
            <br><br>

            <table id="contentArea" algin="center" class="table">
                <tr>
                    <th width="100">제목</th>
                    <td colspan="3">${b.boardTitle }</td>
                </tr>
                <tr>
                    <th>작성자</th>
                    <td>${b.boardWriter }</td>
                    <th>작성일</th>
                    <td>${b.createDate }</td>
                </tr>
                <tr>
                    <th>첨부파일</th>
                    <td colspan="3">
                        <a href="${b.changeName }" download="${b.originName }">${b.originName }</a>
                    </td>
                </tr>
                <tr>
                    <th>내용</th>
                    <td colspan="3"></td>
                </tr>
                <tr>
                    <td colspan="4"><p style="height:150px;">${b.boardContent }</p></td>
                </tr>
            </table>
            <br>

            <div align="center">
                <!-- 수정하기, 삭제하기 버튼은 이 글이 본인이 작성한 글일 경우에만 보여져야 함 -->
                <c:if test="${b.boardWriter eq loginUser.userId }">
	                <a class="btn btn-primary" onclick="postFormSubmit(1)">수정하기</a>
	                <a class="btn btn-danger" onclick="postFormSubmit(2)">삭제하기</a>
                </c:if>
            </div>
            <br><br>
            
            <form id="postForm" action="" method="post">
            	<input type="hidden" name="bno" value="${b.boardNo }">
            	<input type="hidden" name="filePath" value="${b.changeName }">
            </form>
            
            <script>
            	function postFormSubmit(num){
            		
            		//action 속성에 경로값을 넣어주는 함수 + submit
            		if(num == 1){
            			
            			$("#postForm").attr("action", "updateForm.bo").submit();
            		}
            		else{
            			
            			$("#postForm").attr("action", "delete.bo").submit();
            		}
            		
            	}
            </script>

            <!-- 댓글 기능은 나중에 ajax 배우고 나서 구현할 예정! 우선은 화면구현만 해놓음 -->
            <table id="replyArea" class="table" align="center">
                <thead>
                    <tr>
	                    <c:choose>
		                    <c:when test="${not empty loginUser }">
		                        <th colspan="2">
		                            <textarea class="form-control" name="" id="content" cols="55" rows="2" style="resize:none; width:100%;"></textarea>
		                        </th>
		                        <th style="vertical-align:middle"><button class="btn btn-secondary" onclick="insertReply();">등록하기</button></th>
		                    </c:when>
		                    <c:otherwise>
		                    	<th colspan="2">
		                            <textarea class="form-control" name="" id="content" cols="55" rows="2" style="resize:none; width:100%;" readonly>로그인한 회원만 이용 가능합니다.</textarea>
		                        </th>
		                        <th style="vertical-align:middle"><button class="btn btn-secondary" onclick="insertReply();" disabled>등록하기</button></th>
		                    </c:otherwise>
	                    </c:choose>
                    </tr>
                    <tr>
                        <td colspan="3">댓글(<span id="rcount">0</span>)</td>
                    </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
        </div>
        <br><br>

    </div>
    <script>
    	$(function(){
    		selectReplyList();
    	})
    	
    	function insertReply(){
    		
    		if($("#content").val().trim().length != 0){
	    		$.ajax({
	    			url : "insertReply.bo",
	    			data : {
	    				replyContent : $("#content").val(),
	    				refBno : '${b.boardNo}',
	    				replyWriter : '${loginUser.userId}'
	    			},
	    			success : function(result){
	    				
	    				if(result > 0){
	    					selectReplyList();
	    					alertify.alert("서비스 요청 결과", "댓글이 정상적으로 등록되었습니다.");
	    					$("#content").val("");
	    				}
	    			},
	    			error : function(){
	    				console.log("통신 실패");
	    			}
	    		})
    		}
    		else{
    			alertify.alert("서비스 요청 결과", "댓글을 입력해주세요.");
    		}
    	}
    	function selectReplyList(){
    		$.ajax({
    			url : "replyList.bo",
    			data : {bno : '${b.boardNo}'},
    			success : function(result){
    				
    				var str = "";
    				
    				for(var i = 0; i < result.length; i++){
						str += "<tr>"
							     + "<td>" + result[i].replyWriter + "</td>"
							     + "<td>" + result[i].replyContent + "</td>"
							     + "<td>" + result[i].createDate + "</td>"
						     + "</tr>"
					};
					$("#replyArea tbody").html(str);
					$("#rcount").text(result.length);
    			},
    			error : function(){
    				console.log("통신 실패");
    			}
    		})
    	}
    	
    </script>
    <jsp:include page="../common/footer.jsp" />
    
</body>
</html>