<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="zh-CN">
<head>
    <title>博客-发表文章</title>
    <%@ include file="common/meta.jsp"%>
    <%@ include file="common/css.jsp"%>
    <link rel="stylesheet" type="text/css" href="vendor/wang-editor2/css/wangEditor.min.css">

</head>



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
                    <h2>文章编辑</h2>
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
            <form action="editor.do" method="post" name="sentMessage" id="editorForm" novalidate>
                <div class="control-group">
                    <div class="form-group floating-label-form-group controls">
                        <label>请输入标题</label>
                        <input type="text" class="form-control" placeholder="标题" id="title" name="title" required
                               data-validation-required-message="标题不能为空">
                        <p class="help-block text-danger"></p>
                    </div>
                </div>

                <div class="control-group">
                    <div class="form-group floating-label-form-group controls">
                        <label>内容</label>
                        <textarea rows="6" class="form-control" placeholder="内容"
                                  id="editor" name="contents"
                                  style="height:400px;max-height:500px;" required data-validation-required-message="内容不能为空"></textarea>
                        <p class="help-block text-danger"></p>
                    </div>
                </div>
                <div id="success"></div>
                <div class="form-group">
                    <button type="submit" class="btn btn-primary btn-block" id="sendMessageButton">发表</button>
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
<script src="js/editor.js"></script>
<!-- Custom scripts for this template -->
<script src="js/clean-blog.min.js"></script>
<script type="text/javascript" src="vendor/wang-editor2/js/wangEditor.min.js"></script>
<script type="text/javascript">
    var editor = new wangEditor('editor');
    editor.create();
</script>


</body>

</html>