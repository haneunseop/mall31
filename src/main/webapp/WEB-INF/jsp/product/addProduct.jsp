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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
 
<!-- bootstrap javascript소스를 사용하기 위한 CDN주소 -->
<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
<script>
//페이지가 완성되면 함수 실행
$(document).ready(function(){
	//버튼을 클릭하면 실행
	$('#addBtn').click(function(){
		console.log('addBtn click test...');
		//form을 submit
		$('#addProductForm').submit();
	});
});
</script> 

<title>addProduct.jsp</title>
</head>
<body>
<div class="container">
    <h1>addProduct.jsp</h1>
    <form id="addProductForm" action="${pageContext.request.contextPath}/product/addProduct" method="post">
        <div class="form-group">
            <label for="boardPw">categoryNo : int :</label>
            <input class="form-control" name="categoryNo" id="categoryNo" type="text"/>
        </div>
        <div class="form-group">
            <label for="boardPw">productCommonName : String :</label>
            <input class="form-control" name="productCommonName" id="productCommonName" type="text"/>
        </div>
        <div class="form-group">
            <label for="boardContent">productCommonPrice : int :</label>
            <input class="form-control" name="productCommonPrice" id="productCommonPrice" type="text"/>
        </div>
        <div class="form-group">
            <label for="boardName">productCommonDescription : String :</label>
            <input class="form-control" name="productCommonDescription" id="productCommonDescription" type="text"/>
        </div>
		<div>
			<button id="addBtn" type="button">등록</button>
		</div>
    </form>
</div>
</body>
</html>

