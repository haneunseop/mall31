<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>INDEX</h1>
	<h3>쇼핑몰 메인 페이지</h3>
	<div>
		<ul>
			<c:if test="${memberId == null}">
				<li><a href="/member/login">로그인</a></li>
				<li><a href="/member/findId">아이디찾기</a></li>
				<li><a href="/member/findPass">비밀번호찾기</a></li>
			</c:if>
			<c:if test="${memberId != null}">
				<li><a href="/member/login">로그아웃</a></li>
				<li><a href="/member/getMember">개인정보확인</a></li>
				<li><a href="/member/modifyMemberPw">비밀번호만수정</a></li>
				<li><a href="/member/modifyMemberExceptPw">비밀번호제외회원정보수정</a></li>
				<li><a href="/member/removeMember">회원탈퇴</a></li>
				<li><a href="/mail">이메일 보내기</a></li>
			</c:if>
		</ul>
	</div>
	<!-- 쇼핑몰 메뉴 -->
	<div>
		<a href="/product/addProduct">상품 등록</a>
		<ul>
			<c:forEach var="category" items="${categoryList}">
				<li>
					<a href="/product/getProductListByCategory?categoryNo=${category.categoryNo}">${category.categoryName}</a><br>
				</li>
			</c:forEach>
		</ul>
	</div>
</body>
</html>