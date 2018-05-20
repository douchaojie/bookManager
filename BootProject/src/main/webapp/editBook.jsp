<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!--别忘了导包  -->
<%@page import="java.util.*"%>
<%@page import="com.domain.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- 引入文件bootstrap 插件-->
<!-- 提取的公共头部代码-->
<%
out.flush();  /* 先进行发送缓存----避免代码位置不对  */
request.getRequestDispatcher("top.jsp").include(request, response); %>
<!-- 时间css -->
<link
	href="bower_components/bootstrap-datepicker/dist/css/bootstrap-datepicker.min.css"
	rel="stylesheet" type="text/css" />

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
	top: 20%;
	left: 50%;
	height: 600px;
	width: 500px;
	margin-left: -200px
}
</style>

</head>
<body>

	<div class="dv1">
		<h1 align="center" style="color: blue">管理员添加书籍页面</h1>
		<div class="dv2">


			<form action="updateBook" method="post" enctype="multipart/form-data">
				<% book b=(book)request.getAttribute("book");%>
				<!--  id不能让⽤户修改，⼜要传递到服务器 -->

				<input type="hidden" value="<%=b.getId() %>" name="id">
				<div class="form-group">
					<div class="col-sm-3">
						<label for="bookName">书名：</label>
					</div>
					<div class="col-sm-9">
						<input type="text" class="form-control" id="bookName"
							name="t_name" value="<%=b==null?"":b.getT_name()%>">
					</div>
				</div>

				<br /> <br /> <br />

				<div class="form-group">
					<div class="col-sm-3">
						<label for="bookAuthor">作者：</label>
					</div>
					<div class="col-sm-9">
						<input type="text" class="form-control" name="t_author"
							id="bookAuthor" value="<%=b==null?"":b.getT_author()%>">
					</div>

				</div>
				<br />

				<div class="form-group">
					<div class="col-sm-3">
						<label for="bookDescri">描述：</label>
					</div>
					<div class="col-sm-9">
						<textarea type="text" class="form-control" name="descri"
							id="bookDescri"> <%=b==null?"":b.getDescri()%></textarea>
					</div>

				</div>
				<br /> <br />
				<div class="form-group">
					<div class="col-sm-3">
						<label for="bookPrice">价格：</label>
					</div>
					<div class="col-sm-9">
						<input type="text" class="form-control" name="t_price"
							value="<%=b==null?"":b.getT_price()%>" id="bookPrice">
					</div>

				</div>
				<br /> <br />
				<div class="form-group">
					<div class="col-sm-3">
						<label for="bookType">类型：</label>
					</div>
					<!--类型不是固定不变的  -->
					<div class="col-sm-9">
						<select id="bookType" name="t_id" class="form-control">

						</select>



					</div>

				</div>
				<br /> <br />
				<div class="form-group">
					<div class="col-sm-3">
						<label for="bookPhoto">照片：</label>
					</div>
					<div class="col-sm-3">
						<input type="file" class="form-control" name="t_photo"
							id="bookPhoto">
					</div>
					<div class="col-sm-3">
					<img alt="" src="upload/<%=b==null?"":b.getT_photo()%>" height="100px">
					
					
					</div>
					
					

				</div>
				<br /> <br /><br /> <br /><br />
				<div class="form-group">
					<div class="col-sm-3">
						<label for="bookDate">出版日期：</label>
					</div>
					<div class="col-sm-9">
						<input type="text" class="form-control" name="t_date"
							id="bookDate" value="<%=b==null?"":b.getT_date()%>">
					</div>

				</div>
				<br /> <br />

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
						<input type="submit" class="form-control" value="提交">
					</div>
				</div>

			</form>
			<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
			<script type="text/javascript"
				src="bower_components/jquery/dist/jquery.min.js"></script>
			<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
			<script type="text/javascript"
				src="bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
			<!-- 日期js -->
			<script type="text/javascript"
				src="bower_components/bootstrap-datepicker/dist/js/bootstrap-datepicker.min.js"></script>
			<!-- 中文日期js -->
			<script type="text/javascript"
				src="bower_components/bootstrap-datepicker/dist/locales/bootstrap-datepicker.zh-CN.min.js"></script>

			<!--校验信息  -->
			<script type="text/javascript"
				src="bower_components/jquery-validation/dist/jquery.validate.min.js"></script>
			<script type="text/javascript"
				src="bower_components/jquery-validation-bootstrap-tooltip/jquery-validate.bootstrap-tooltip.min.js"></script>



			<script type="text/javascript">
				/*点击更换验证码   */
				$(function() {

					$("#VcodeImage").click(function(evt) {

						this.src = "vcode.png?t=" + Math.random(); /* 随机产生，保证每次的地址不一样，这样可以达到切换的效果了  */

					});

					$('#bookDate').datepicker({
						format : 'yyyy-mm-dd',
						language : 'zh-CN', //提示中文界面
						autoclose : true
					//自动关闭
					});

				});
			</script>

           
			<script type="text/javascript">
				function fillType(types) {    /* 别忘了传值  */
                 var tid=<%=b.getT_id()%>//获取之前保存的tid 
					var selectList = document.getElementById("bookType");
					for (var i = 0; i < types.length; i++) {
                       var op=new Option(types[i].bookType,types[i].id)   /* 名字和selectypeSeverlet中的types中的保证 一致  */
						selectList.appendChild(op);
						if(tid==types[i].id){
                            op.selected=true;
						} 
							

					}

				}
			</script>
			<!-- iframe处于安全考虑不允许执行JavaScript -->
			<iframe src="selectType" style="display: none"></iframe>


		</div>

	</div>










</body>
</html>