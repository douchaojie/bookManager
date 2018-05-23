<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.domain.*"%>
<%  
String basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();

%>
<!DOCTYPE html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<!-- 提取的公共头部代码-->
<%
out.flush();  /* 先进行发送缓存----避免代码位置不对  */
request.getRequestDispatcher("top.jsp").include(request, response); %>
<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
<script type="text/javascript"
	src="bower_components/jquery/dist/jquery.min.js"></script>

<!-- 定义主题css -->
<link rel="stylesheet" id="themLink" type="text/css">

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script type="text/javascript"
	src="bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<!--导入session  -->
<script type="text/javascript"
	src="bower_components/bootstrap/dist/js/jquery.cookie.js"></script>

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
							<li><a href="#">主题&nbsp;&nbsp; <select id="selectTheme"
									style="color: black;" >
										<option>default</option>
										<option>flatly</option>
										<option>paper</option>
										<option>darkly</option>

								</select>
							</a></li>
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
						<!--搜索框  -->
						<tr>
							<td colspan="9">
								<form class="form-inline" action="bookList" method="post"
									id="searchFrm">
									<div class="form-group" class="col-md-3">
										<label for="exampleInputName2">书名</label> <input type="text"
											class="form-control" id="exampleInputName2" name="name"
											value="<%=request.getAttribute("name")==null?"":request.getAttribute("name") %>">
									</div>
									<div class="form-group">

										<label for="selectTid">类型</label> <select name="tid"
											id="selectTid" class="form-control">

											<option value="-1">-------请选择----</option>


											<% List<bookType> typeLists=(List<bookType>)request.getAttribute("typeList");
                         			  int tid=(Integer)request.getAttribute("tid");				
								for(bookType type:typeLists)
									{
										
										if(tid==type.getId())
										{%>
											<option value="<%=type.getId()%>" selected="selected"><%=type.getBookType() %></option>

											<%}else{%>

											<option value="<%=type.getId()%>"><%=type.getBookType() %></option>



											<%}
									
									}								
									%>
										</select>



									</div>
									<button type="submit" class="btn btn-default">搜索</button>
								</form>



							</td>
						</tr>

						<tr>
							<th>标号</th>
							<th>书名</th>
							<th>描述</th>
							<th>类型</th>
							<th>图片</th>
							<th>价格</th>
							<th>作者</th>
							<th>出版日期</th>
							<th>操作</th>
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
						<td><%=list.getBookType()%></td>
						<!--由tid更换为类型名字  -->
						<td id="bookImg"><img src="upload/<%=list.getT_photo()%>"
							style="width: 150px; height: 150px;"></td>
						<td><%=list.getT_price()%></td>
						<td><%=list.getT_author()%></td>
						<td><%=list.getT_date()%></td>
						<td><a href="editBook?id=<%=list.getId()%>"
							class="glyphicon glyphicon-pencil">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;
							<a href="deleteBook?id=<%=list.getId()%>"
							class="glyphicon glyphicon-remove" onclick="delBook(event);">删除</a></td>

					</tr>
					<%
						}
					%>
					<!-- 进行翻页  -->
					<!-- 向前翻页 -->

					<tr>
						<td colspan="9" style="padding-top: 0px; padding-bottom: 0px;"
							class="text-center">

							<ul class="pagination" style="margin: 0px;">
								<!-- 向前翻页 -->

								<%
									int pageNo = (Integer) request.getAttribute("pageNo"); /*括号里面必须是包装类   */

									if (pageNo == 1) {
								%>
								<li class="disabled"><a href="#">&lt;&lt;</a></li>

								<%
									} else {
								%>
								<li><a href="bookList?pageNo=<%=pageNo - 1%>">&lt;&lt;</a></li>


								<%
									}
								%>


								<!--显示总页数  -->
								<!-- 对于我们这里最多显示五个超级链接，实现算法如下：
					     1. 如果totalPage<=5,我们显⽰所有超级
                         2. 否则如果pageNum<=3 ,显⽰1 2 3 4 ...totalPage这五个超级链接
                         3. 否则如果pageNum>=totalPage-3,显⽰1... totalPage-3 totalPage-2 totalPage-1 totalPage这五个链接
                         4. 否则显示：1... pageNum-1 pageNum pageNum+1 ...totalPage这五个 -->


								<%
									int totalpage = (Integer) request.getAttribute("totalPage");
									/*   1. 如果totalPage<=5,我们显⽰所有超级*/
									if (totalpage <= 5) {
										for (int i = 1; i <= totalpage; i++) {
								%>

								<li><a href="bookList?pageNo=<%=i%>"><%=i%></a></li>

								<%
									}
									} else {
										/* 2. 否则如果pageNum<=3 ,显⽰1 2 3 4 ...totalPage这五个超级链接 */
										if (pageNo <= 3) {
								%>
								<li><a href="bookList?pageNo=1">1</a></li>
								<li><a href="bookList?pageNo=2">2</a></li>
								<li><a href="bookList?pageNo=3">3</a></li>
								<li><a href="bookList?pageNo=4">4</a></li>
								<li><a href="bookList?pageNo=<%=totalpage%>">...<%=totalpage%></a></li>
								<%
									}
										/*  3. 否则如果pageNum>=totalPage-3,显⽰1... totalPage-3 totalPage-2 totalPage-1 totalPage这五个链接 */
										else if (pageNo >= totalpage - 3) {
								%>

								<li><a href="bookList?pageNo=1">1...</a></li>
								<li><a href="bookList?pageNo=<%=totalpage - 3%>"><%=totalpage - 3%></a></li>
								<li><a href="bookList?pageNo=<%=totalpage - 2%>"><%=totalpage - 2%></a></li>
								<li><a href="bookList?pageNo=<%=totalpage - 1%>"><%=totalpage - 1%></a></li>
								<li><a href="bookList?pageNo=<%=totalpage%>"><%=totalpage%></a></li>


								<%
									} else {
											/* 4. 否则显示：1... pageNum-1 pageNum pageNum+1 ...totalPage这五个   */
								%>

								<li><a href="bookList?pageNo=1">1...</a></li>
								<li><a href="bookList?pageNo=<%=pageNo - 1%>"><%=pageNo - 1%></a></li>
								<li><a href="bookList?pageNo=<%=pageNo%>"><%=pageNo%></a></li>
								<li><a href="bookList?pageNo=<%=totalpage + 1%>"><%=totalpage + 1%></a></li>
								<li><a href="bookList?pageNo=<%=totalpage%>">...<%=totalpage%></a></li>




								<%
									}

									}
								%>



								<!-- 向后翻页 -->
								<%
									if (pageNo == totalpage) {
								%>
								<li class="disabled"><a href="#">&gt;&gt;</a></li>

								<%
									} else {
								%>
								<li><a href="bookList?pageNo=<%=pageNo + 1%>">&gt;&gt;</a></li>


								<%
									}
								%>
							</ul>
						</td>
					</tr>
					<tbody>
				</table>

			</div>
		</div>
		<div class="row">
			<!-- 尾部提取代码 -->
			<%
		out.flush();    /* 先进行发送缓存----避免代码位置不对  */
		request.getRequestDispatcher("bottom.jsp").include(request, response); %>
		</div>
	</div>

	<script type="text/javascript">   /* 点到哪页哪页下面的 样式改变 */

    $(function(){
           /* 改变 当前页样式  */
            $("a[ href^='bookList?pageNo=<%=pageNo%>'] ").parent("li").addClass("active");

			/* 序列化表单  */
            $(".pagination a[ href^='bookList?pageNo=']").click(function(e){

                this.href+="&"+$("#searchFrm").serialize();


                });

         
			
		});
	</script>
	<!--解决图片丢失问题，如果丢失添加一个默认图片  -->
	<%-- <script type="text/javascript">
	 $(function(){

		   $("#bookImg img").error(function(){

		    this.src="<%=basePath%>/upload/lost.jpg";
			
		  });

		});


	</script> --%>

	<!-- 更换主题 -->

	<script type="text/javascript">

function readCookie(name){

var cookieList=document.cookie.split(";");
for (var i = 0; i < cookieList.length; i++) {
if(name===cookieList[i].split("=")[0].replace(/(^\s*)|(\s*$)/g, ""))//---replace(/(^\s*)|(\s*$)/g, "")去掉name 前面的空格 
{

	return cookieList[i].split("=")[1];
} 

	
}


return null;	
}




$(function(){


// 加载页面后先判断是否以前保存的有cookie
if (readCookie("theme")) {  //根据有没有对象 jqery会根据使用情况转变（本例转变成true和false）

$("#themLink").attr("href","bower_components/bootstrap/dist/css/"+readCookie("theme")+"/bootstrap.min.css");
// 下拉列框内容改变 	
$("#selectTheme").val(readCookie("theme"));
	
	
} else {

$("#themLink").attr("href","bower_components/bootstrap/dist/css/default/bootstrap.min.css");

	
}

//--------改变主题时 保存cookie 

$("#selectTheme").bind("change", function(e) {

$("#themLink").attr("href","bower_components/bootstrap/dist/css/"+$("#selectTheme").val()+"/bootstrap.min.css");
// 保存cookie 
document.cookie="theme="+$("#selectTheme").val()+";max-age="+7*24*60*60;

	
});
	
});






</script>






	<script type="text/javascript">
		/* $(function() {

			$("a[href='deleteBook?id='] [class='glyphicon glyphicon-remove']")
					.click(function(e) {

						if (!confirm("你确定要删除吗？ ")) {
							//取消超级链接默认⾏为  
							e.preventDefault();

						}

					});

		}); */
		/* 是否要删除   */
		function delBook(event) {

			if (!confirm(" 你确定要删除吗？")) {
				//取消超级链接默认⾏为  

				event.preventDefault();
			}

		}
	</script>








</body>
</html>