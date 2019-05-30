<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>List products</title>
    <meta name="description" content="">
    <meta name="keywords" content="">
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>

    <%--<link rel="stylesheet" type="text/css" href="/resources/css/styles.css">--%>

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
                            <a class="btn btn-primary" href="/admin/">Page admin</a>
                        </div>
                        <br>
                        <h1>List products</h1>
                        <br>

                        <div class="block-filter">
                            <div class="filter-price">
                                <form role="form" class="form-horizontal" action="/sort-by-price" method="post">
                                    <div class="form-group">
                                        <label for="priceFrom">Price from:</label>
                                        <input id="priceFrom" type="text" name="priceFrom" required>
                                    </div>
                                    <div class="form-group">
                                        <label for="priceTo">Price to:</label>
                                        <input id="priceTo" type="text" name="priceTo" required>
                                    </div>

                                    <div class="form-group">
                                        <input type="submit" class="btn btn-primary" value="Price filter">
                                    </div>
                                </form>
                            </div>

                            <div class="filter-all-products">
                                <form role="form" class="form-horizontal" action="/" method="post">
                                    <div class="form-group">
                                        <input type="submit" class="btn btn-primary" value="Show all products">
                                    </div>
                                </form>
                            </div>
                        </div>

                        <br>
                        <div class="table-responsive">
                            <table class="table table-bordered">
                                <thead>
                                <tr>
                                    <th><b>Image</b></th>
                                    <th><b>Id</b></th>
                                    <th><b>Name</b></th>
                                    <th><b>Description</b></th>
                                    <th><b>Price</b></th>
                                    <th><b>Quantity</b></th>
                                    <th><b>Action</b></th>
                                </tr>
                                </thead>
                                <c:set var="productIdBefore" value="${0}" scope="page"/>
                                <c:set var="start" value="true" scope="page" />

                                <c:forEach items="${products}" var="product">

                                    <c:if test="${start}">
                                        <c:set var="productIdBefore" value="${product}" scope="page"/>
                                    </c:if>

                                    <c:choose>
                                        <c:when test="${not empty product.link}">

                                            <c:if test="${start}">
                                                <tr>
                                                    <td>
                                                        <img height="100" width="100" src="/image?link=${product.link}" alt=""/>
                                                        <span>${product.link}</span>
                                                    </td>
                                                    <td><c:out value="${product.product_id}"/></td>
                                                    <td><c:out value="${product.name}"/></td>
                                                    <td><c:out value="${product.description}"/></td>
                                                    <td><c:out value="${product.price}"/></td>
                                                    <td><c:out value="${product.quantity}"/></td>
                                                    <td>
                                                        <div class="list-purchase-button-action">
                                                            <a class="btn btn-primary list-project-button-edit" href="/page-edit-quantity?id=<c:out value="${product.product_id}"/>"
                                                            <c:if test="${product.quantity == 0}">style="display: none"</c:if>
                                                               >
                                                                Purchase
                                                            </a>
                                                        </div>
                                                    </td>
                                                </tr>
                                            </c:if>

                                            <c:if test="${!start && productIdBefore.product_id != product.product_id}">
                                                <tr>
                                                    <td>
                                                        <img height="100" width="100" src="/image?link=${product.link}" alt=""/>
                                                        <span>${product.link}</span>
                                                    </td>
                                                    <td><c:out value="${product.product_id}"/></td>
                                                    <td><c:out value="${product.name}"/></td>
                                                    <td><c:out value="${product.description}"/></td>
                                                    <td><c:out value="${product.price}"/></td>
                                                    <td><c:out value="${product.quantity}"/></td>
                                                    <td>
                                                        <div class="list-purchase-button-action">
                                                            <a class="btn btn-primary list-project-button-edit" href="/page-edit-quantity?id=<c:out value="${product.product_id}"/>"
                                                               <c:if test="${product.quantity == 0}">style="display: none"</c:if>
                                                               >
                                                                Purchase
                                                            </a>
                                                        </div>
                                                    </td>
                                                </tr>

                                                <c:set var="productIdBefore" value="${product}" scope="page"/>
                                                <c:set var="start" value="true" scope="page" />
                                            </c:if>

                                            <c:if test="${!start && productIdBefore.product_id == product.product_id}">
                                                <td>
                                                    <img height="100" width="100" src="/image?link=${product.link}" alt=""/>
                                                    <span>${product.link}</span>
                                                </td>

                                                <c:set var="productIdBefore" value="${product}" scope="page"/>

                                            </c:if>

                                            <c:set var="start" value="false" scope="page" />

                                        </c:when>
                                        <c:otherwise>
                                            <tr>
                                                <td>
                                                    <img height="100" width="100" src="/image?link=temp.png" alt="without picture"/>
                                                    <span>${product.link}</span>
                                                </td>
                                                <td><c:out value="${product.product_id}"/></td>
                                                <td><c:out value="${product.name}"/></td>
                                                <td><c:out value="${product.description}"/></td>
                                                <td><c:out value="${product.price}"/></td>
                                                <td><c:out value="${product.quantity}"/></td>
                                                <td>
                                                    <div class="list-purchase-button-action">
                                                        <a class="btn btn-primary list-project-button-edit" href="/page-edit-quantity?id=<c:out value="${product.product_id}"/>"
                                                           <c:if test="${product.quantity == 0}">style="display: none"</c:if>
                                                           >
                                                            Purchase
                                                        </a>
                                                    </div>
                                                </td>
                                            </tr>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </table>
                        </div>
                    </div>
                </div><!-- /.col-md-12 -->
            </div><!-- /.row -->
        </div><!-- /.container -->
    </div>

    <c:if test="${not empty showMessage}">
        <div class="modal" tabindex="-1" role="dialog">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Message</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <p><c:out value="${message}"/></p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>
    </c:if>
</body>
</html>
