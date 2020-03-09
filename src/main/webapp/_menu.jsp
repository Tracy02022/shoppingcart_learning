<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>


<div class="menu-container">


    <a href="${pageContext.request.contextPath}/index.jsp">Home</a>
    |
    <a href="${pageContext.request.contextPath}/productList.jsp">
        Product List
    </a>
    |
    <a href="${pageContext.request.contextPath}/shoppingCart.jsp">
        My Cart
    </a>
    |
    <security:authorize  access="hasAnyRole('ROLE_MANAGER','ROLE_EMPLOYEE')">
        <a href="${pageContext.request.contextPath}/orderList.jsp">
            Order List
        </a>
        |
    </security:authorize>

    <security:authorize  access="hasRole('ROLE_MANAGER')">
        <a href="${pageContext.request.contextPath}/product.jsp">
            Create Product
        </a>
        |
    </security:authorize>

</div>