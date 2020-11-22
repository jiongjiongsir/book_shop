<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>
	<%@include file="/pages/common/header.jsp"%>
</head>
<script type="text/javascript">
	$(function () {
		$(".deleteItem").click(function () {
			return confirm("您确定要删除【"+$(this).parent().parent().find("td:first").text()+"】吗？")
		});
		$("#clear").click(function () {
			return confirm("您确定要清空购物车吗？")
		});
		//给输入框绑定失去焦点事件
		$(".count").change(function () {
			var id=$(this).attr("bookId");
			if(confirm("你确定要修改【"+$(this).parent().parent().find("td:first").text()+"】的数量吗？"))
			{
				var new_count=this.value;
				alert(new_count)
				location.href="http://localhost:8080/book_shop/cartServlet?action=updateItem&id="+id+"&new_count="+new_count;
			}
			else
			{
				this.value=this.defaultValue;
			}

		});
	})
</script>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">购物车</span>
		<%@include file="/pages/common/login_sucess_menu.jsp"%>
	</div>
	
	<div id="main">
	
		<table>
			<tr>
				<td>商品名称</td>
				<td>数量</td>
				<td>单价</td>
				<td>金额</td>
				<td>操作</td>
			</tr>
			<c:if test="${empty sessionScope.cart.cartItems}">
				<tr>
					<td colspan="5"><a href="index.jsp">亲，当前购物车为空! 快去浏览商品吧！</a></td>

				</tr>
			</c:if>
			<c:if test="${not empty sessionScope.cart.cartItems}">
				<c:forEach items="${sessionScope.cart.cartItems}" var="item">
					<tr>
						<td>${item.value.name}</td>
						<td><input type="text" class="count" value="${item.value.count} "bookId="${item.value.id}" style="width: 80px"></td>
						<td>${item.value.price}</td>
						<td>${item.value.totalPrice}</td>
						<td><a href="cartServlet?action=deleteItem&id=${item.value.id}" class="deleteItem">删除</a></td>
					</tr>
				</c:forEach>

			</c:if>



			
		</table>
		<c:if test="${not empty sessionScope.cart.cartItems}">
			<div class="cart_info">
				<span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cart.totalCount}</span>件商品</span>
				<span class="cart_span">总金额<span class="b_price">${sessionScope.cart.totalPrice}</span>元</span>
				<span class="cart_span"><a href="cartServlet?action=clear" id="clear">清空购物车</a></span>
				<span class="cart_span"><a href="orderServlet?action=createOrder">去结账</a></span>
			</div>
		</c:if>

	
	</div>

	<%@include file="/pages/common/footer.jsp"%>
</body>
</html>