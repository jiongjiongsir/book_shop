<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>编辑图书</title>
	<%@include file="/pages/common/header.jsp"%>
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
	
	h1 a {
		color:red;
	}
	
	input {
		text-align: center;
	}
</style>
</head>
<body>
<%@include file="/pages/common/manager_menu.jsp"%>
		
		<div id="main">
			<form action="manager/bookServlet" method="post">
				<table>
					<tr>
						<td>名称</td>
						<td>价格</td>
						<td>作者</td>
						<td>销量</td>
						<td>库存</td>
						<td colspan="2">操作</td>
					</tr>		
					<tr>
						<input type="hidden" name="pageNo" value="${param.pageNo}">
						<td><input name="name" type="text" value="${requestScope.Book.name}"/></td>
						<td><input name="price" type="text" value="${requestScope.Book.price}"/></td>
						<td><input name="author" type="text" value="${requestScope.Book.author}"/></td>
						<td><input name="sales" type="text" value="${requestScope.Book.sales}"/></td>
						<td><input name="stock" type="text" value="${requestScope.Book.stock}"/></td>
						<td><input type="hidden" name="action" value="${param.method}"></td>
							<input type="hidden" name="id" value="${requestScope.Book.id}">
						<td><input type="submit" value="提交"/></td>
					</tr>	
				</table>


			</form>
			
	
		</div>

	<%@include file="/pages/common/footer.jsp"%>
</body>
</html>