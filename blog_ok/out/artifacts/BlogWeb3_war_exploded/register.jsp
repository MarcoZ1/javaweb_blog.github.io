<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"  %>
<html lang="zh-CN">

<head>
  <title>博客-注册</title>

  <%@ include file="common/meta.jsp"%>
  <%@ include file="common/css.jsp"%>
</head>

<body background="img/Dabab.jpg"
      style=" background-repeat:no-repeat ;  <%--背景图片不重复--%>
              background-size:100% 100%;             <%--达到窗口的百分百比例--%>
              background-attachment: fixed;"         <%--图片固定，随着页面滚动而移动--%>
>
<body>


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
          <h2>注册</h2>
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
      <p>请填写如下信息</p>
      <!-- Contact Form - Enter your email address on line 19 of the mail/contact_me.php file to make this form work. -->
      <!-- WARNING: Some web hosts do not allow emails to be sent through forms to common mail hosts like Gmail or Yahoo. It's recommended that you use a private domain email address! -->
      <!-- To use the contact form, your site must be on a live web host with PHP! The form will not work locally! -->

      <c:if test="${error!=null}">
        <div id="error"  class='alert alert-danger'>
            ${error}
        </div>
      </c:if>
      
      <form action="register" method="post" name="sentMessage" id="contactForm" novalidate>
        <div class="control-group">
          <div class="form-group floating-label-form-group controls">
            <label>用户名</label>
            <input type="text" class="form-control" placeholder="用户名" id="name" name="name"
                   pattern="^[a-zA-Z0-9_-]{4,16}$"
                   data-validation-pattern-message="用户名必须4到16位（字母，数字，下划线，减号）"
                   data-validation-
                   data-validation-required-message="用户名不能为空"
                   required>
            <p class="help-block text-danger"></p>
          </div>
        </div>
        <div class="control-group">
          <div class="form-group floating-label-form-group controls">
            <label>电子邮件</label>
            <input type="email" class="form-control" placeholder="电子邮件" id="email" name="email"
                   data-validation-required-message="电子邮件不能为"
                   data-validation-email-message="电子邮件格式错误"
                   required >
            <p class="help-block text-danger"></p>
          </div>
        </div>
        <div class="control-group">
          <div class="form-group col-xs-12 floating-label-form-group controls">
            <label>密码</label>
            <input type="password" class="form-control" placeholder="密码" id="password" name="password" required
                   pattern="^[a-zA-Z0-9_-]{4,16}$"
                   data-validation-pattern-message="密码必须4到16位（字母，数字，下划线，减号）"
                   data-validation-required-message="密码不能为空">
            <p class="help-block text-danger"></p>
          </div>
        </div>
        <div class="control-group">
          <div class="form-group col-xs-12 floating-label-form-group controls">
            <label>确认密码</label>
            <input type="password" class="form-control" placeholder="确认密码" id="password_confirm"
                   data-validation-required-message="密码不能为空"
                   data-validation-matches-match="password"
                   data-validation-matches-message="密码不一致"
                   required>
            <p class="help-block text-danger"></p>
          </div>
        </div>
        <div class="control-group">
          <div class="form-group floating-label-form-group controls">
            <label>自我简介</label>
            <textarea rows="5" class="form-control" placeholder="自我简介" id="intro" required
                      data-validation-required-message="请填写自我简介"></textarea>
            <p class="help-block text-danger"></p>
          </div>
        </div>
        <div>
          <label>验证码：</label>
          <input placeholder="请输入验证码" name="code" >
          <img src="kaptcha.jpg"/>
        </div>
        <div id="success"></div>
        <div class="form-group">
          <button type="submit" class="btn btn-primary btn-block" id="sendMessageButton">注册</button>
        </div>
      </form>
    </div>
  </div>
</div>

<hr>

<!-- Footer -->
<%@ include file="common/footer.jsp"%>

<%-- JavaScript File--%>
<%@ include file="common/js.jsp"%>

<!-- Contact Form JavaScript -->
<script src="js/jqBootstrapValidation.js"></script>
<script src="js/register.js"></script>

<!-- Custom scripts for this template -->
<script src="js/clean-blog.min.js"></script>

</body>

</html>
