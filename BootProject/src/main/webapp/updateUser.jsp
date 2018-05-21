<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.domain.*" %>	
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- 1、告诉浏览器表缩放 -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<!--普通 css -->
<link href="bower_components/bootstrap/dist/css/bootstrap.min.css"
	rel="stylesheet" type="text/css" />
<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
<script type="text/javascript"
	src="bower_components/jquery/dist/jquery.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script type="text/javascript"
	src="bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

<style type="text/css">
.dv1 {
	border: 2px solid black;
	position: relative;
	height: 800px;
	width: 100%;
	background-color: pink;
}

.dv2 {
	border: 3px solid black;
	position: fixed;
	top: 50%;
	left: 50%;
	height: 320px;
	width: 400px;
	margin-left: -150px
}
</style>

</head>
<body>

	<div class="dv1">
		<h1 align="center" style="color: blue">欢迎登陆学生教务管理系统</h1>
		<div class="dv2">
		<!--如果验证码错误，用于打印错误信息  -->
			<%if(request.getAttribute("vcodeResult")!=null) {%>
			<div class="alert alert-warning" role="alert"><%=request.getAttribute("vcodeResult") %></div>
			<%} %>


			<form action="updateUser" method="post">

				<div class="form-group">
					<div class="col-sm-3">
						<label for="exampleInputEmail1">用户：</label>
					</div>
					<div class="col-sm-9">





						<input type="text" class="form-control" id="exampleInputEmail1"
							name="name" value="<%=((admin)session.getAttribute("loginUser")).getName()%>" readonly="readonly">
					</div>
				</div>

                <br/><br/><br/>
				<div class="form-group">
					<div class="col-sm-3">
						<label for="exampleInputPassword1">密码：</label>
					</div>
					<div class="col-sm-9">
						<input type="password" class="form-control" name="password"
							id="exampleInputPassword1">
					</div>

				</div>
				  <br/><br/>
				<div class="form-group">
					<div class="col-sm-3">
						<label for="exampleInputPassword2">确认密码：</label>
					</div>
					<div class="col-sm-9">
						<input type="password" class="form-control" name="password2"
							id="exampleInputPassword2">
					</div>

				</div>
               <br/><br/>
				<div class="form-group">
					<div class="col-sm-3">
						<label for="yanzhengma">验证码：</label>
					</div>
					<div class="col-sm-5">
						<input type="password" class="form-control" name="vcode"
							id="yanzhengma">
					</div>
					<div class="col-sm-4">
						<img src="vcode.png" id="VcodeImage" title="单击切换图片" width="100%">
					</div>


				</div>



				<div class="form-group">

					<div class="col-sm-offset-2 col-sm-5">
						<br /> <br /> <input type="submit" class="form-control"
							value="确定">
					</div>
				</div>

			</form>


			<script type="text/javascript">
				/*点击更换验证码   */
				$(function() {

					$("#VcodeImage").click(function(evt) {

						this.src = "vcode.png?t=" + Math.random(); /* 随机产生，保证每次的地址不一样，这样可以达到切换的效果了  */

					});

				});
			</script>





		</div>

	</div>










</body>
</html>