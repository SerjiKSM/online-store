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
                    </div>
                    <br>
                    <h1>Page edit product quantity</h1>
                    <br>
                    <c:if test="${not empty errorQuantity}">
                        <div class="alert alert-danger" role="alert">
                            <c:out value="${errorQuantity}"/>
                        </div>
                    </c:if>
                    <form role="form" class="form-horizontal form-edit-product-quantity" data-id="<c:out value="${product.id}"/>" action="/update-product-quantity/<c:out value="${product.id}"/>" method="post">
                       <div class="form-group">
                            <label for="name">Product:</label>
                            <input id="name" type="text" class="form-control" name="product" placeholder="Product" value="<c:out value="${product.name}"/>" required>
                        </div>
                        <div project class="form-group">
                            <label for="productQuantity">Quantity:</label>
                            <input id="productQuantity" type="text" class="form-control" name="quantity" placeholder="Product quantity" value="<c:out value="${product.quantity}"/>" required>
                        </div>
                        <div class="form-group"><input type="submit" class="btn btn-primary" value="Update"></div>
                    </form>
                </div>
            </div><!-- /.col-md-12 -->
        </div><!-- /.row -->
    </div><!-- /.container -->
</div>
</body>
</html>