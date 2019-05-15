<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>getProductListByCategory.jsp</title>
<!-- bootstrap을 사용하기 위한 CDN주소 -->
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<script>
//페이지가 완성되면 함수 실행
$(document).ready(function(){
	//버튼을 클릭하면 실행
	$('#searchButton').click(function(){
		//form을 submit
		$('#searchForm').submit();
	});
});
</script>
</head>
<body>
<div class="container">
    <h1>getProductListByCategory.jsp</h1>
    <table class="table table-striped">
        <thead>
            <tr>
                <th>productCommonNo</th>
                <th>categoryNo</th>
                <th>productCommonName</th>
                <th>productCommonPrice</th>
                <th>productCommonDescription</th>
                <th>productCommonDate</th>
                <th>주문하기</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="pcl" items="${productCommonList}">
                <tr>
                    <td>${pcl.productCommonNo}</td>
                    <td>${pcl.categoryNo}</td>
                    <td>${pcl.productCommonName}</td>
                    <td>${pcl.productCommonPrice}</td>
                    <td>${pcl.productCommonDescription}</td>
                    <td>${pcl.productCommonDate}</td>
                    <td><a href="${pageContext.request.contextPath}/product/orderProduct?productCommonNo=${pcl.productCommonNo}">주문하기</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <form id="searchForm" action="${pageContext.request.contextPath}/product/getProductListByCategory?" method="get">
    	<input type="hidden" name="categoryNo" value="${categoryNo}">
    	상품 이름: <input type="text" name="searchWord">
    	<button type="button" id="searchButton">검색</button>
    </form>
    <ul class="pager">
        <c:if test="${currentPage > 1}">
            <li class="previous"><a href="/product/getProductListByCategory?categoryNo=${categoryNo}&currentPage=${currentPage-1}">이전</a></li>
        </c:if>
        <c:if test="${currentPage < lastPage}">
            <li class="next"><a href="/product/getProductListByCategory?categoryNo=${categoryNo}&currentPage=${currentPage+1}">다음</a></li>
        </c:if>
    </ul>
    <a href="/product/addProduct">상품 등록</a>
</div>
</body>
</html>