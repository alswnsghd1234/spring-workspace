<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
</head>
<body>
	<h1>Spring에서 ajax 사용하는 법</h1>
	<h3>1. 요청 시 값 전달, 응답 결과 받아보기</h3>
	이름 : <input type="text" id="name"> <br>
	나이 : <input type="number" id="age"> <br>
	<button onclick="test1();">전송</button>
	<div id="result1"></div>
	<script>
		function test1(){
			
			$.ajax({
				url : "ajax1.do",
				data : {
						name : $("#name").val(),
						age : $("#age").val()
				},
				success : function(result){
					console.log(result);
					$("#result1").html(result);
//					응답 데이터가 array일 경우
// 					var resultStr = "이름 : " + result[0] + "<br>" + "나이 : " + result[1];
// 					$("#result1").html(resultStr);

//					응답 데이터가 object일 경우 속성에 접근 : 객체명.속성명
					var resultStr = "이름 : " + result.name + "<br>" + "나이 : " + result.age; 
					$("#result1").html(resultStr);
				},
				error : function(){
					console.log("ajax 통신 실패");
				}
			})
		}
	</script>
	
	<h3>2. 조회요청 후 조회된 회원 객체를 응답받아서 출력하기</h3>
	조회할 회원번호 : <input type="number" id="userNo">
	<button id="btn">조회</button>
	<div id="result2"></div>
	<script>
		$(function(){
			$("#btn").click(function(){
				$.ajax({
					url : "ajax2.do",
					data :{userNo : $("#userNo").val()},
					success : function(result){
						console.log(result);
						
						var resultStr = "<ul>"
										+ "<li>아이디 : " + result.userId + "</li>" 
										+ "<li>비밀번호 : " + result.userPwd + "</li>"
										+ "<li>나이 : " + result.age + "</li>"
										+ "<li>핸드폰번호 : " + result.phone + "</li>"
										+ "</ul>";
						$("#result2").html(resultStr);
					},
					error : function(){
						console.log("통신 실패");
					}
				})
			})
		})
	</script>
	<h3>3. 조회 요청 후 조회된 회원 리트스를 응답받아서 출력해보기</h3>
	<button onclick="test3();">회원전체조회</button>
	<br><br>
	
	<table border="1" id="result3">
		<thead>
			<tr>
				<th>아이디</th>
				<th>나이</th>
				<th>핸드폰번호</th>
			</tr>
		</thead>
		<tbody>
		</tbody>
	</table>
	
	<script>
		function test3(){
			$.ajax({
				url : "ajax3.do",
				success : function(result){
					
					var str = "";
					
					for(var i = 0; i < result.length; i++){
						str += "<tr>"
							     + "<td>" + result[i].userId + "</td>"
							     + "<td>" + result[i].age + "</td>"
							     + "<td>" + result[i].phone + "</td>"
						     + "</tr>"
					};
					$("#result3 tbody").html(str);
				},
				error : function(){
					console.log("통신 실패");
				}
			})
		}
	</script>
</body>
</html>