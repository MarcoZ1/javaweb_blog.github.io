<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="zh-CN">
<head>
  <title>文章标题</title>
  <%@ include file="common/meta.jsp"%>
  <%@ include file="common/css.jsp"%>
  <style>
    #contents img {
      max-width: 100%;
    }
  </style>
</head>

<body>
<!-- Navigation -->
<%@ include file="common/navigation.jsp"%>

<!-- Page Header -->
<header class="masthead" style="background-image: url('img/abab.png.png');">
  <div class="overlay"></div>
  <div class="container">
    <div class="row">
      <div class="col-lg-8 col-md-10 mx-auto">
        <div class="post-heading" style="padding-bottom: 20px;">
          <h2>${article.title}</h2>
          <span style="color:#34ce57;" class="meta"><a href="#">作者: ${article.author}</a> 发表于 ${article.create_at}
              时间</span>
        </div>
      </div>
    </div>
  </div>
</header>

<!-- Post Content -->
<article>
  <div class="container">
    <div class="row">
      <div id="contents" class="col-lg-8 col-md-10 mx-auto img-fluid">
        ${article.contents}
        <hr>

        <!-- Comments Form -->
        <div class="card my-4">
          <h5 class="card-header">评论</h5>
          <div class="card-body">
            <form action="" method="post">
              <div class="form-group">
                <textarea class="form-control" rows="3"></textarea>
              </div>
              <button type="submit" class="btn btn-primary">提交</button>
            </form>
          </div>
        </div>

        <!-- Single Comment -->

          <div class="media mb-4">
            <img class="d-flex mr-3 rounded-circle" src="img/heat/xzb.png" alt="">
            <div class="media-body">
              <h5 class="mt-0">墨桓丶</h5>
              MAD一定做一定做，不会咕不会咕
            </div>
          </div>

        <div class="media mb-4">
          <img class="d-flex mr-3 rounded-circle" src="img/heat/sxq1.jpg" alt="">
          <div class="media-body">
            <h5 class="mt-0">公薰i</h5>
            月色真美！
          </div>
        </div>

          <div class="media mb-4">
            <img class="d-flex mr-3 rounded-circle" src="img/heat/ysy.jpg" alt="">
            <div class="media-body">
              <h5 class="mt-0">不朽之蓝恶魔</h5>
              碧蓝航线是幼儿园游戏
            </div>
          </div>

          <div class="media mb-4">
            <img class="d-flex mr-3 rounded-circle" src="img/heat/xzj.jpg" alt="">
            <div class="media-body">
              <h5 class="mt-0">紫陌凛风</h5>
              微信转账300，我向你敬礼，solute！
            </div>
          </div>

            <div class="media mt-4">
              <img class="d-flex mr-3 rounded-circle" src="img/heat/mhf1.png" alt="">
              <div class="media-body">
                <h5 class="mt-0">生命甜包</h5>
                二次元哈哈哈
              </div>
            </div>

          <div class="media mt-4">
            <img class="d-flex mr-3 rounded-circle" src="img/heat/hjs.jpg" alt="">
            <div class="media-body">
              <h5 class="mt-0">叶湘伦</h5>
              最美不是下雨天
            </div>
          </div>


          </div>
        </div>
      </div>
    </div>
  </div>
</article>

<hr>
<!-- Footer -->
<%@ include file="common/footer.jsp"%>

<%-- JavaScript File--%>
<%@ include file="common/js.jsp"%>

<!-- Custom scripts for this template -->
<script src="js/clean-blog.min.js"></script>

</body>

</html>
