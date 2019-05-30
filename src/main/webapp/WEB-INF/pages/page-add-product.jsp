<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="container">
                    <br>
                    <div class="menu">
                        <a class="btn btn-primary" href="/list">Page products</a>
                        |
                        <a class="btn btn-primary" href="/admin/">Page admin</a>
                    </div>
                    <br>

                    <%--<form role="form" enctype="multipart/form-data" class="form-horizontal" action="/admin/add-product" method="post">--%>
                    <form:form method="post" action="/admin/add-product"
                               modelAttribute="uploadForm" enctype="multipart/form-data">
                        <div class="form-group"><h1>Add new product</h1></div>

                        <div class="form-group">
                            <label for="title">Title:</label>
                            <input id="title" type="text" class="form-control" name="name" placeholder="Title" required>
                        </div>
                        <div class="form-group">
                            <label for="description">Description:</label>
                            <input id="description" type="text" class="form-control" name="description" placeholder="Description" required>
                        </div>
                        <div class="form-group">
                            <label for="price">Price:</label>
                            <input id="price" type="text" class="form-control" name="price" placeholder="Price" required>
                        </div>
                        <div class="form-group">
                            <label for="quantity">Quantity:</label>
                            <input id="quantity" type="text" class="form-control" name="quantity" placeholder="Quantity" required>
                        </div>

                        <div class="form-group">
                            <input id="addFile" type="button" value="Add File" />
                            <table id="fileTable">
                                <tr>
                                    <td><input name="files[0]" type="file" /></td>
                                </tr>
                            </table>
                        </div>
                        <div class="form-group"><input type="submit" class="btn btn-primary" value="Save"></div>
                    <%--</form>--%>
                    </form:form>
                </div>
            </div><!-- /.col-md-12 -->
        </div><!-- /.row -->
    </div><!-- /.container -->
</div>
</body>
</html>