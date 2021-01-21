<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="zh-CN">

<head>

  <title>Web后端应用开发——博客项目</title>

  <%@ include file="common/meta.jsp"%>
  <%@ include file="common/css.jsp"%>


</head>

<body background="img/post-bg.jpg"
      style=" background-repeat:no-repeat ;  <%--背景图片不重复--%>
              background-size:100% 100%;             <%--达到窗口的百分百比例--%>
              background-attachment: fixed;"         <%--图片固定，随着页面滚动而移动--%>
>

<body>
<!-- Navigation -->
<%@ include file="common/navigation.jsp"%>

<!-- Page Header -->
<header class="masthead" style="background-image: url('img/abab.png')">
  <div class="overlay"></div>
  <div class="container">
    <div class="row">
      <div class="col-lg-8 col-md-10 mx-auto">
        <div class="site-heading" style="padding-top: 100px; padding-bottom: 80px;">
          <h2>文章列表</h2>
          <!-- <span class="subheading">文章列表</span> -->
        </div>
      </div>
    </div>
  </div>
</header>

<!-- Main Content -->
<div class="container">
  <div class="row">
    <div class="col-lg-8 col-md-10 mx-auto">

     <c:forEach var="article" items="${articles}">

        <div class="post-preview">
          <a href="article?id=${article.id}">
            <h2 class="post-title">
                ${article.title}
            </h2>
            <h3 class="post-subtitle">
            </h3>
          </a>
          <p style="color:red" class="post-meta" ><a href="#">作者:${article.author}</a>&nbsp; 发表于
              ${article.create_at}</p>

        </div>

      </c:forEach>

        <hr>

      <!-- Pager -->
        <div class="clearfix">
            <%--        <%Fenye sPage = (Fenye) request.getAttribute("articles");%>--%>
            <%--          AricleListServlet--%>
            <a href=" ">首页</a>
            <a href="list?currentPage=${currentPage-1}">上一页</a>
            <a href="list?currentPage=${currentPage+1}">下一页</a>
                <a href="list?currentPage=3">尾页</a>
            <a href="list?currentPage=3">文章总数${count}</a>

        </div>
    </div>
  </div>
</div>

<hr>
<!-- Footer -->
<%@ include file="common/footer.jsp"%>

<%-- JavaScript File--%>
<%@ include file="common/js.jsp"%>
<!-- Custom scripts for this template -->
<script src="js/clean-blog.min.js"></script>

</body>

</html>
