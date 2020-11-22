<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>尚硅谷会员注册页面</title>
<%@include file="/pages/common/header.jsp"%>
<style type="text/css">
	.login_form{
		height:420px;
		margin-top: 25px;
	}
	
</style>
	<script>
		$(function () {
			$("#sub_btn").click(function () {
				var userNameValue=$("#username").val();

				var username_patt=/^\w{5,12}$/;

				if(!username_patt.test(userNameValue))
				{
					$("span.errorMsg").text("用户名不合法！");
					return false;
				}
				else
				{
					$("span.errorMsg").text("");
				}
				var userPawValue=$("#password").val();

				var paw_patt=/^\w{5,12}$/;

				if(!paw_patt.test(userPawValue))
				{
					$("span.errorMsg").text("密码不合法！");
					return false;
				}
				else
				{
					$("span.errorMsg").text("");
				}
				if($("#password").val()==$("#repwd").val())
				{
					$("span.errorMsg").text("");
				}
				else
				{
					$("span.errorMsg").text("密码不一致！");
					return false;
				}
				var eamilValue=$("#email").val();
				var eamil_patt=/^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$/;
				if(!eamil_patt.test(eamilValue))
				{
					$("span.errorMsg").text("邮箱不合法！");
					return false;
				}
				else
				{
					$("span.errorMsg").text("");
				}
				var codeValue=$("#code").val();
				codeValue=$.trim(codeValue);
				if(codeValue==null||codeValue=="")
				{
					$("span.errorMsg").text("验证码不正确！");
					return false;
				}
				else
				{
					$("span.errorMsg").text("");
				}
			});
			$("#img_code").click(function () {
				this.src="${basePath}kaptcha.jpg?d="+new Date();
			});

			$(function () {
				$("#username").blur(function () {
					$.ajax({
						url:"http://localhost:8080/book_shop/pages/user/regist.jsp",
						data: "action=ajaxServlet&username=" + $("#username").value(),
						type:"POST",
						success:function (data) {
							if(data.existUsername==true)
							{
								$("span.errorMsg").text("用户名已重复!");
							}
						},
						dataType:"json"
					});
				});

			});
		})

	</script>
</head>
<body>
		<div id="login_header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
		</div>
		
			<div class="login_banner">
			
				<div id="l_content">
					<span class="login_word">欢迎注册</span>
				</div>
				
				<div id="content">
					<div class="login_form">
						<div class="login_box">
							<div class="tit">
								<h1>注册尚硅谷会员</h1>
<%--								<span class="errorMsg"><%= request.getAttribute("msg")==null?"":request.getAttribute("msg")%></span>--%>
								<span class="errorMsg">${requestScope.msg}</span>
							</div>
							<div class="form">
								<form action="userServlet" method="post">
									<input type="hidden" name="action" value="regist" />
									<label>用户名称：</label>
									<input class="itxt" type="text" placeholder="请输入用户名" autocomplete="off" tabindex="1" name="username" id="username"
									value="${requestScope.username}"/>
<%--									"<%= request.getAttribute("username")==null?"":request.getAttribute("username")%>"--%>
									<br />
									<br />
									<label>用户密码：</label>
									<input class="itxt" type="password" placeholder="请输入密码" autocomplete="off" tabindex="1" name="password" id="password" />
									<br />
									<br />
									<label>确认密码：</label>
									<input class="itxt" type="password" placeholder="确认密码" autocomplete="off" tabindex="1" name="repwd" id="repwd" />
									<br />
									<br />
									<label>电子邮件：</label>
									<input class="itxt" type="text" placeholder="请输入邮箱地址" autocomplete="off" tabindex="1" name="email" id="email"
											value="${requestScope.email}"/>
<%--									"<%= request.getAttribute("email")==null?"":request.getAttribute("email")%>"--%>
									<br />
									<br />
									<label>验证码：</label>
									<input class="itxt" type="text" style="width: 120px;" name="code" id="code"/>
									<img id="img_code" alt="" src="kaptcha.jpg" style="float: right; margin-right: 40px; margin-top: 10px;width: 100px">
									<br />
									<br />

									<input type="submit" value="注册" id="sub_btn" />
									
								</form>
							</div>
							
						</div>
					</div>
				</div>
			</div>
		<%@include file="/pages/common/footer.jsp"%>
</body>
</html>