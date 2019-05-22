<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<!-- jquery를 사용하기 위한 주소 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>BOARD VIEW(spring mvc 방식 + mybatis)</title>
<!-- bootstrap을 사용하기 위한 CDN주소 -->
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
<script>
$(document).ready(function(){
	console.log('화면 로드 완료 테스트...')
	var currentPage = 1;
	$.ajax({
		url:'/getBoardComment',
		data:{boardNo: ${board.boardNo}},
		type:'POST',
		dataType:'json',
		success:function(result){
			console.log(result)
			console.log(result.lastPage);
			console.log(result.currentPage);
			console.log(result.pageNoPerPage);
			// 댓글 목록
			$(result.boardCommentList).each(function(i, item){
				$('#commentListView').append('<tr>');
				$('#commentListView').append('<td>댓글내용: '+item.boardCommentContent+'&nbsp;</td>');
				$('#commentListView').append('<td>작성자: '+item.boardCommentUser+'&nbsp;</td>');
				$('#commentListView').append('<td><a href="">댓글 삭제</a></td>');
				$('#commentListView').append('</tr>');
			});
			// 댓글 페이징번호 목록
			var step;
			var p = (currentPage-1)*result.pageNoPerPage;
			for(step=1; step<result.pageNoPerPage+1;step++){
				$('#commentListPage').append('<a href="">'+(step)+'</a>&nbsp;&nbsp;');
			}
		}
	});
});
</script>
</head>
<body>
<h1>게시글+댓글</h1>
<div class="container">
     <table class="table">
         <tbody>
			<tr>
                <td>board_no :</td>
                <td>${board.boardNo}</td>
			</tr>
            <tr>
				<td>board_title :</td>
				<td>${board.boardTitle}</td>
            </tr>
            <tr>
				<td>board_content :</td>
				<td>${board.boardContent}</td>
            </tr>
            <tr>
				<td>board_user :</td>
				<td>${board.boardUser}</td>
            </tr>
            <tr>
				<td>board_date :</td>
				<td>${board.boardDate}</td>
            </tr>
        </tbody>
    </table>
    <a class="btn btn-default" href="${pageContext.request.contextPath}/board/modifyBoard?boardNo=${board.boardNo}">수정</a>
    <a class="btn btn-default" href="${pageContext.request.contextPath}/board/removeBoard?boardNo=${board.boardNo}">삭제</a>
    <a class="btn btn-default" href="${pageContext.request.contextPath}/board/getBoardList">글목록</a>
</div>
<br>


<!-- 댓글 목록 -->
<div id="commentListView" class="container">

</div>
<!-- 댓글 목록의 페이지 번호 -->
<div id="commentListPage" class="container">

</div>


<div class="container">
	<div>
		<c:if test="${boardFileList.size() != 0}">
			<c:forEach var="boardFile" items="${boardFileList}">
				boardFile : <a href="${pageContext.request.contextPath}/upload/${boardFile.getBoardFileSaveName()}.${boardFile.getBoardFileExt()}">${boardFile.getBoardFileSaveName()}.${boardFile.getBoardFileExt()}</a><br>
			</c:forEach>
		</c:if>
	</div>
</div>
<br>
<div class="container">
	<form method="post" action="${pageContext.request.contextPath}/board/addBoardComment">
		<input type="text" name="boardNo" value="${board.boardNo}">
		<div>
			<textarea name="boardCommentContent" cols="80" row="3"></textarea>
		</div>
		<div>
			boardCommentUser : 
			<input type="text" name="boardCommentUser">
		</div>
		<div>
			boardCommentPassword : 
			<input type="password" name="boardCommentPw">
		</div>
		<input class="btn btn-default" type="submit" value="댓글입력">
	</form>
</div>
</body>
</html>
