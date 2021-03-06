<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
 

<title>addMember.jsp</title>
</head>
<body>
<div class="container">
    <h1>addMember</h1>
    <form id="addForm" action="${pageContext.request.contextPath}/member/addMember" method="post">
        <div class="form-group">
            <label for="memberId">memberId :</label>
            <input class="form-control" name="memberId" type="text"/>
        </div>
        <div class="form-group">
            <label for="memberPw">memberPw :</label>
            <input class="form-control" name="memberPw" type="password"/>
        </div>
        <div class="form-group">
            <label for="memberName">memberName :</label>
            <input class="form-control" name="memberName" type="text"/>
        </div>
        <div class="form-group">
            <label for="memberPhone">memberPhone :</label>
            <input class="form-control" name="memberPhone" type="text">
        </div>
		<div class="form-group">
            <label for="memberAddress">memberAddress :</label>
            <input class="form-control" name="memberAddress" type="text"/>
        </div>       
        <div class="form-group">
            <label for="memberGender">memberGender :</label>
            <input class="form-control" name="memberGender" type="text"/>
        </div>
        <div class="form-group">
            <label for="memberEmail">memberEmail :</label>
            <input class="form-control" name="memberEmail" type="text"/>
        </div> 
        <div class="form-group">
            <input class="form-control" name="memberLevel" type="hidden" value="consumer" />
        </div>             
        <div>
            <input class="btn btn-default" id="addButton" type="submit" value="회원가입"/>
            <input class="btn btn-default" type="reset" value="초기화"/>
        </div>
    </form>
</div>
</body>
</html>

