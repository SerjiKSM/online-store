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
                        |
                        <a class="btn btn-primary" href="/admin/">Page admin</a>
                    </div>
                    <br>
                    <c:set var="start" value="true" scope="page" />
                    <c:set var="productImages" value="${productData}" scope="page" />
                    <c:forEach items="${productData}" var="product">
                        <c:if test="${start}">
                            <form role="form" enctype="multipart/form-data" class="form-horizontal" action="/admin/update-product/<c:out value="${product.product_id}"/>" method="post">
                                <div class="form-group"><h1>Edit product</h1></div>

                                <div class="form-group">
                                    <label for="title">Title:</label>
                                    <input id="title" type="text" class="form-control" name="name" placeholder="Title" required value="<c:out value="${product.name}"/>">
                                </div>
                                <div class="form-group">
                                    <label for="description">Description:</label>
                                    <input id="description" type="text" class="form-control" name="description" placeholder="Description" required value="<c:out value="${product.description}"/>">
                                </div>
                                <div class="form-group">
                                    <label for="price">Price:</label>
                                    <input id="price" type="text" class="form-control" name="price" placeholder="Price" required value="<c:out value="${product.price}"/>">
                                </div>
                                <div class="form-group">
                                    <label for="quantity">Quantity:</label>
                                    <input id="quantity" type="text" class="form-control" name="quantity" placeholder="Quantity" required value="<c:out value="${product.quantity}"/>">
                                </div>


                                <div class="form-group">
                                    <div class="add-temp-photo-project">
                                        <c:forEach items="${productImages}" var="productPhoto">
                                            <c:if test="${not empty productPhoto.link}">
                                                <div class="wrapper-img-project" data-id="">
                                                    <label>
                                                        <img width="100" height="100" class="addImage" src="/image?link=<c:out value="${productPhoto.link}"/>" data-id="">
                                                        <span class="imageTitle"><c:out value="${productPhoto.link}"/></span>
                                                    </label>
                                                </div>
                                                <br>
                                            </c:if>
                                        </c:forEach>
                                    </div>
                                </div>


                                <div class="form-group"><input type="submit" class="btn btn-primary" value="Save"></div>

                            </form>
                            <c:set var="start" value="false" scope="page" />
                        </c:if>

                    </c:forEach>
                </div>
            </div><!-- /.col-md-12 -->
        </div><!-- /.row -->
    </div><!-- /.container -->
</div>
</body>
</html>