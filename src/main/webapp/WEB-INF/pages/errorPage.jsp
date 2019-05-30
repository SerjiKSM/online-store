<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Admin</title>
    <meta name="description" content="">
    <meta name="keywords" content="">
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>

    <script type="text/javascript" src="/resources/js/main.js"></script>
</head>
<body>
    <div class="boxed">
        <section class="main-blog-single page-wrap">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <div style="text-align:center" class="alert alert-warning alert-dismissible show" role="alert">
                            <strong>
                                <span style="color:red; font-size: 30px;"><c:out value="${errorMsg}"/></span>
                                <c:remove var="message" scope="session" />
                            </strong>
                        </div>
                    </div><!-- /.col-md-12 -->
                </div><!-- /.row -->
            </div><!-- /.container -->
        </section>
    </div><!-- boxed -->
</body>
</html>