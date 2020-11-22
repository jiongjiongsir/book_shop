<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>图书管理</title>
	<%@include file="/pages/common/header.jsp"%>
</head>
<script type="text/javascript">
	$(function () {
		$(".deleteclass").click(function () {
			return confirm("您确定要删除"+$(this).parent().parent().find("td:first").text()+"吗？");
		//	return false表示组织元素的默认行为
		});
		$("#searchPagebtn").click(function () {
			var pageNo=$("#pn_input").val();
			//js中提供一個location地址栏对象，该对象有一个属性href可以获取当前地址栏的地址并且该属性可读可写
			location.href="http://localhost:8080/book_shop/${requestScope.page.url}&pageNo="+pageNo;
		})
	})
</script>
<body>

<%@include file="/pages/common/manager_menu.jsp"%>
	
	<div id="main">
		<table>
			<tr>
				<td>名称</td>
				<td>价格</td>
				<td>作者</td>
				<td>销量</td>
				<td>库存</td>
				<td colspan="2">操作</td>
			</tr>
			<c:forEach items="${requestScope.page.items}" var="book">
				<tr>
					<td>${book.name}</td>
					<td>${book.price}</td>
					<td>${book.author}</td>
					<td>${book.sales}</td>
					<td>${book.stock}</td>
					<td><a href="manager/bookServlet?action=getBook&id=${book.id}&method=update&pageNo=${requestScope.page.pageNo}">修改</a></td>
					<td><a class="deleteclass" href="manager/bookServlet?action=delete&id=${book.id}&pageNo=${requestScope.page.pageNo}">删除</a></td>
				</tr>
			</c:forEach>


			
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td><a href="pages/manager/book_edit.jsp?method=add&pageNo=${requestScope.page.pageTotal}">添加图书</a></td>
			</tr>	
		</table>
		<div id="page_nav">
			<c:if test="${requestScope.page.pageNo>1}">
				<a href="${requestScope.page.url}&pageNo=1">首页</a>
				<a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo-1}">上一页</a>
			</c:if>
			<%--	页码输出开始		--%>
			<c:choose>
				<c:when test="${requestScope.page.pageTotal<5}">
					<c:forEach begin="1" end="${requestScope.page.pageTotal}" var="i">
						<c:if test="${i==requestScope.page.pageNo}">
							【${requestScope.page.pageNo}】

						</c:if>
						<c:if test="${i!=requestScope.page.pageNo}">
							<a href="${requestScope.page.url}&pageNo=${i}">${i}</a>
						</c:if>

					</c:forEach>
				</c:when>
				<c:when test="${requestScope.page.pageTotal>5}">
					<c:choose>
						<c:when test="${requestScope.page.pageNo<3}">
							<c:forEach begin="1" end="5" var="i">
								<c:if test="${i==requestScope.page.pageNo}">
									【${requestScope.page.pageNo}】

								</c:if>
								<c:if test="${i!=requestScope.page.pageNo}">
									<a href="${requestScope.page.url}&pageNo=${i}">${i}</a>
								</c:if>

							</c:forEach>
						</c:when>
						<c:when test="${requestScope.page.pageNo>requestScope.page.pageTotal-3}">
							<c:forEach begin="${requestScope.page.pageTotal-4}" end="${requestScope.page.pageTotal}" var="i">
								<c:if test="${i==requestScope.page.pageNo}">
									【${requestScope.page.pageNo}】

								</c:if>
								<c:if test="${i!=requestScope.page.pageNo}">
									<a href="${requestScope.page.url}&pageNo=${i}">${i}</a>
								</c:if>

							</c:forEach>
						</c:when>
						<c:otherwise>
							<c:forEach begin="${requestScope.page.pageNo-2}" end="${requestScope.page.pageNo+2}" var="i">
								<c:if test="${i==requestScope.page.pageNo}">
									【${requestScope.page.pageNo}】

								</c:if>
								<c:if test="${i!=requestScope.page.pageNo}">
									<a href="${requestScope.page.url}&pageNo=${i}">${i}</a>
								</c:if>

							</c:forEach>
						</c:otherwise>
					</c:choose>
				</c:when>


			</c:choose>


			<%--	页码输出结束		--%>

			<c:if test="${requestScope.page.pageNo<requestScope.page.pageTotal }">
				<a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo+1}">下一页</a>
				<a href="${requestScope.page.url}&pageNo=${requestScope.page.pageTotal}">末页</a>
			</c:if>

			共${requestScope.page.pageTotal}页，${requestScope.page.pageTotalCount}条记录 到第<input value="${param.pageNo}" name="pn" id="pn_input"/>页
			<input id="searchPagebtn" type="button" value="确定">
		</div>
	</div>

<%@include file="/pages/common/footer.jsp"%>
</body>
</html>