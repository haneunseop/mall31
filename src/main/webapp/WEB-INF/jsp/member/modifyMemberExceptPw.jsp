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
 
<!-- bootstrap javascript소스를 사용하기 위한 CDN주소 -->
<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
 

<title>modifyMemberPw.jsp</title>
</head>
<body>
<div class="container">
    <h1>modifyMemberPw.jsp</h1>
    <form id="addForm" action="${pageContext.request.contextPath}/member/modifyMemberExceptPw" method="post">
        <div class="form-group">
            <input class="form-control" name="memberPw" type="hidden" value="${member.memberPw}"/>
        </div>        
        <div class="form-group">
            <label for="memberPw">memberId :</label>
            <input class="form-control" name="memberId" type="text" value="${member.memberId}" readonly="readonly"/>
        </div>
        <div class="form-group">
            <label for="memberPw">memberName :</label>
            <input class="form-control" name="memberName" type="text" value="${member.memberName}" readonly="readonly"/>
        </div>
        <div class="form-group">
            <label for="memberPw">memberGender :</label>
            <input class="form-control" name="memberGender" type="text" value="${member.memberGender}" readonly="readonly"/>
        </div>
        <div class="form-group">
            <label for="memberPw">memberLevel :</label>
            <input class="form-control" name="memberLevel" type="text" value="${member.memberLevel}" readonly="readonly"/>
        </div>            
        <div class="form-group">
            <label for="memberPw">memberPhone :</label>
            <input class="form-control" name="memberPhone" type="text" value="${member.memberPhone}"/>
        </div>
        <div class="form-group">
            <label for="memberPw">memberAddress :</label>
            <input class="form-control" name="memberAddress" type="text" value="${member.memberAddress}"/>
        </div>                          
        <div>
            <input class="btn btn-default" type="submit" value="회원수정"/>
            <input class="btn btn-default" type="reset" value="초기화"/>
        </div>
    </form>
</div>
</body>
</html>

