<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
 
<!-- bootstrap javascript소스를 사용하기 위한 CDN주소 -->
<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
<script>
//페이지가 완성되면 함수 실행
$(document).ready(function(){
	//버튼을 클릭하면 실행
	$('#productColor').change(function(){
		console.log('orderBtn click test...');
		//form을 submit
		$('#orderProductForm').submit();
	});
});
</script> 

<title>orderProduct.jsp</title>
</head>
<body>
<div class="container">
    <h1>orderProduct.jsp</h1>
    <form id="orderProductForm" action="${pageContext.request.contextPath}/product/orderProduct" method="post">
        <div class="form-group">
            <input class="form-control" name="productCommonNo" type="hidden" value="${productCommon.productCommonNo}"/>
        </div>
        
       	<!-- 색상을 보여준다.-->
        <c:set var="color" value="color"></c:set>
       	<select name='productColor' id='productColor'>
       		<c:if test="${productList == null}">
       			<option value='' selected>-- Color --</option>
       		</c:if>
	        <c:forEach var="product" items="${productCommon.products}">
	        	<!-- 내가 선택한 색상을 보여준다. -->
	        	<c:if test="${product.productColor ne color}">
	        		<option value="${product.productColor}"
	        		<c:if test="${productList[0].productColor eq product.productColor}">
	        			selected
	        		</c:if>
	        		>${product.productColor}</option>
	        		<c:set var="color" value="${product.productColor}"></c:set>
	        	</c:if>
	        </c:forEach>
		</select>	
		<!-- 색상을 선택하면 그 색상의 사이즈가 보인다. -->
		<select>
			<c:if test="${productList eq null}">
				<option value='' selected>-- Size & Stock --</option>
			</c:if>
			<c:if test="${productList ne null}">
				<c:forEach items="${productList}" var="product">
					<option value='${product.productSize}'>${product.productSize} & ${product.productStock}</option>
				</c:forEach>
			</c:if>
		</select>
		<!-- 몇개를 주문할 것인지 입력받는데
		+1 +10 -1 -10 버튼을 통해서 숫자를 입력하게 만들자 -->
		<c:if test="${productList eq null}">
			<input type="text" value="-- Quantity --" readonly="readonly">
		</c:if>
		<c:if test="${productList ne null}">

		</c:if>
		<!-- 색상을 골라야지 주문하기 버튼이 보인다. -->
		<c:set var="order" value="주문하기"></c:set>
		<c:if test="${productList ne null}">
			<div>
				<button id="orderBtn" type="button">주문하기</button>
			</div>
		</c:if>
    </form>
</div>
</body>
</html>

