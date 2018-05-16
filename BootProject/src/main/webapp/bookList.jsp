<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.domain.*"%>
<!DOCTYPE html>
<html>
<head>
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
<title>Insert title here</title>
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
				<nav class="navbar navbar-default" role="navigation">
					<div class="navbar-header">

						<button type="button" class="navbar-toggle" data-toggle="collapse"
							data-target="#bs-example-navbar-collapse-1">
							<span class="sr-only">Toggle navigation</span><span
								class="icon-bar"></span><span class="icon-bar"></span><span
								class="icon-bar"></span>
						</button>
						<a class="navbar-brand" href="#">豫南小清华图书管理系统</a>
					</div>

					<div class="collapse navbar-collapse"
						id="bs-example-navbar-collapse-1">
						<ul class="nav navbar-nav">

							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown">书籍管理<strong class="caret"></strong></a>
								<ul class="dropdown-menu">
									<li><a href="#">删除</a></li>
									<li><a href="addBook.jsp">添加</a></li>
								</ul></li>
						</ul>
						<ul class="nav navbar-nav navbar-right">
							<li><a href="updateUser.jsp">修改密码</a></li>
							<li><a href="logout">注销</a></li>
						</ul>
					</div>

				</nav>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">

				<table class="table table-hover table-condensed   table-bordered">
					<thead>
						<tr>
							<th>标号</th>
							<th>书名</th>
							<th>描述</th>
							<th>类型</th>
							<th>图片</th>
							<th>价格</th>
							<th>作者</th>
							<th>出版日期</th>
						</tr>
					</thead>

					<%
						List<BookList> lists = (List<BookList>) request.getAttribute("bookList");
						for (BookList list : lists) {
					%>
					<tr>
						<td><%=list.getId()%></td>
						<td><%=list.getT_name()%></td>
						<td><%=list.getDescri()%></td>
						<td ><%=list.getBookType()%></td>   <!--由tid更换为类型名字  -->
						<td><img src="upload/<%=list.getT_photo()%>"
							style="width: 150px; height: 150px;"></td>
						<td><%=list.getT_price()%></td>
						<td><%=list.getT_author()%></td>
						<td><%=list.getT_date()%></td>
					</tr>
					<%
						}
					%>
					<!-- 进行翻页  -->
					<!-- 向前翻页 -->

					<tr>
						<td colspan="8" style="padding-top: 0px; padding-bottom: 0px;"
							class="text-center">

							<ul class="pagination" style="margin: 0px;">
								<!-- 向前翻页 -->

								<%
							int pageNo=(Integer)request.getAttribute("pageNo");   /*括号里面必须是包装类   */
							
							if(pageNo==1){
							%>
								<li class="disabled"><a href="#">&lt;&lt;</a></li>

								<%}else{
							%>
								<li><a href="bookList?pageNo=<%=pageNo-1%>">&lt;&lt;</a></li>


								<%}
							%>


								<!--显示总页数  -->
								<%
									int totalpage = (Integer) request.getAttribute("totalPage");
									for (int i = 1; i <= totalpage; i++) {
								%>

								<li><a href="bookList?pageNo=<%=i%>"><%=i%></a></li>

								<%
									}
								%> 
								
							<%-- 	<%
									 
									   int totalPage=(Integer)request.getAttribute("totalPage");
									    if(totalPage<=5) {
									    	for(int i=1;i<=totalPage;i++)
									    	{
									    		%>
									<li><a href="bookList?pageNo=<%=i%>"><%=i%></a></li>
									<%
									    	}
									    }else if(pageNo<=3){
									    	
									    	%>

									<li><a href="bookList?pageNo=1">1</a></li>
									<li><a href="bookList?pageNo=2">2</a></li>
									<li><a href="bookList?pageNo=3">3</a></li>
									<li><a href="bookList?pageNo=4">4</a></li>
									<li><a href="bookList?pageNo=<%=totalPage%>">..<%=totalPage%></a></li>
									<% 
									    } else if(pageNo>=totalPage-2){
									    	
									    	%>
									    	<li><a href="bookList?pageNo=1">1..</a></li>
									<li><a href="bookList?pageNo=<%=totalPage-3%>"><%=totalPage-3%></a></li>
									<li><a href="bookList?pageNo=<%=totalPage-2%>"><%=totalPage-2%></a></li>
									<li><a href="bookList?pageNo=<%=totalPage-1%>"><%=totalPage-1%></a></li>
									<li><a href="bookList?pageNo=<%=totalPage%>"><%=totalPage%></a></li>
									    	
									    	<% 
									    }else{
									    	
									    	%>
									   	    	<li><a href="bookList?pageNo=1">1..</a></li>
									<li><a href="bookList?pageNo=<%=pageNo-1%>"><%=pageNo-1%></a></li>
									<li><a href="bookList?pageNo=<%=pageNo%>"><%=pageNo%></a></li>
									<li><a href="bookList?pageNo=<%=pageNo+1%>"><%=pageNo+1%></a></li>
									<li><a href="bookList?pageNo=<%=totalPage%>">..<%=totalPage%></a></li> 	
									    	
									    	<% 
									    }
									  %>
								
								 --%>
								
								
								<!-- 向后翻页 -->
								<%
						
							if(pageNo==totalpage){
							%>
								<li class="disabled"><a href="#">&gt;&gt;</a></li>

								<%}else{
							%>
								<li><a href="bookList?pageNo=<%=pageNo+1%>">&gt;&gt;</a></li>


								<%}
							%>
							</ul>
						</td>
					</tr>
					<tbody>
				</table>

			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<h2></h2>
				<p>&copy;南阳德刚版权所有</p>

			</div>
		</div>
	</div>

<script type="text/javascript">   /* 点到哪页哪页下面的 样式改变 */
      $(function(){
              $("a[ href='bookList?pageNo=<%=pageNo%>']").parent("li").addClass("active");
          });
      
</script>


</body>
</html>