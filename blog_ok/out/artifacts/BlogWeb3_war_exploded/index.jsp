<%@ page import="model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="zh-CN">

<head>

    <title>Web后端应用开发——博客项目</title>
    <%@ include file="common/meta.jsp" %>
    <%@ include file="common/css.jsp" %>

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

    <style type="text/css"></style>


</head>

<%--背景--%>

<body background="img/post-bg.jpg"
      style=" background-repeat:no-repeat ;  <%--背景图片不重复--%>
              background-size:100% 100%;             <%--达到窗口的百分百比例--%>
              background-attachment: fixed;"         <%--图片固定，随着页面滚动而移动--%>
>


<body>
<!-- Navigation -->
<%@ include file="common/navigation.jsp" %>

<!-- Page Header -->
<header class="masthead" style="background-image: url('img/abab.png')">
    <div class="overlay"></div>
    <div class="container">
        <div class="row">
            <div class="col-lg-8 col-md-10 mx-auto">
                <div class="site-heading" style="padding-top: 200px; padding-bottom: 300px;">
                    <h1>拾  枝  杂  谈</h1>
                    <hr>
                    <%--          <%  //取共享域的值，共享域的值是字符串类型，进行强制转换
                                User loginUser=(User)request.getAttribute("loginUser");
                                <%=loginUser.getUsername()%>
                                %>--%>
                    <span class="subheading">欢迎用户：${sessionScope.loginUser.username} 进入博客</span>
                    <hr>
                    <hr>
                    <h3>Welcome to My Blog！</h3>
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
                    <p class="post-meta">作者： <a href="#">${article.author}</a>
                            ${article.create_at}</p>
                </div>
            </c:forEach>
            <hr>
            <!-- Pager -->
            <div class="clearfix">
                <a class="btn btn-primary float-right" href="list">更多文章 &rarr;</a>
            </div>
        </div>
    </div>
</div>

<hr>

<%--花瓣飘落--%>

<script type="text/javascript" class="autoinsert" src="js/jquery-1.2.6.min.js"></script>

<script src="js/snowfall.jquery.js"></script>

<script>

    $(document).snowfall('clear');

    $(document).snowfall({

        image: "img/huaban.png",

        flakeCount: 30,

        minSize: 5,

        maxSize: 22

    });

</script>


<!-- Footer -->
<%@ include file="common/footer.jsp" %>

<%-- JavaScript File--%>
<%@ include file="common/js.jsp" %>

<!-- Custom scripts for this template -->
<script src="js/clean-blog.min.js"></script>
</body>

</html>
