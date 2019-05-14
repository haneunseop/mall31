<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- bootstrap을 사용하기 위한 CDN주소 -->
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
 
<!-- jquery를 사용하기위한 CDN주소 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
 

<title>BOARD MODIFY FORM(spring mvc + mybatis 방식)</title>
</head>
<body>
<div class="container">
    <h1>BOARD MODIFY FORM(spring mvc + mybatis 방식)</h1> 
    <form id="modifyForm" action="${pageContext.request.contextPath}/board/modifyBoard" enctype="multipart/form-data" method="post">
            <input class="form-control" name="boardNo" value="${board.boardNo}" type="hidden">
            <input class="form-control" name="boardNo" value="${board.boardUser}" type="hidden">
        <div class="form-group">
            <label for="boardPw">비밀번호 확인 :</label>
            <input class="form-control" name="boardPw" id="boardPw" type="password"/>
        </div>    
        <div class="form-group">
            <label for="boardPw">boardTitle :</label>
            <input class="form-control" value="${board.boardTitle}" name="boardTitle" id="boardTitle" type="text"/>
        </div>
        <div class="form-group">boardContent :
            <textarea class="form-control" id="boardContent" name="boardContent" rows="5" cols="50">${board.boardContent}</textarea>
        </div>
        <!-- 이 자리에 파일 추가 -->
        <input class="form-control" name="boardFile" multiple="multiple" type="file"/>
        <div>
            <input class="btn btn-default" type="submit" value="글수정"/>
            <input class="btn btn-default" type="reset" value="초기화"/>
        </div>
    </form>
    <!-- 이 자리에 파일 목록과 삭제 -->
	<c:if test="${boardFileList.size() != 0}">
		<c:forEach var="boardFile1" items="${boardFileList}">
			<c:if test="${boardFile1.boardFileDelete == 0}">
				boardFile : ${boardFile1.getBoardFileSaveName()}.${boardFile1.getBoardFileExt()}
				<a href="${pageContext.request.contextPath}/board/removeBoardFileInModifyBoard?boardNo=${board.boardNo}&boardFileNo=${boardFile1.boardFileNo}">삭제</a><br>
			</c:if>
		</c:forEach>
	</c:if>
</div>
</body>
</html>

