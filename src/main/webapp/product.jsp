
1
2
3
4
5
6
7
8
9
10
11
12
13
14
15
16
17
18
19
20
21
22
23
24
25
26
27
28
29
30
31
32
33
34
35
36
37
38
39
40
41
42
43
44
45
46
47
48
49
50
51
52
53
54
55
56
57
58
59
60
61
62
63
64
65
66
67
68
69
70
71
72
73
74
75
76
77
78
79
80
81
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Product</title>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles.css">

</head>
<body>

<jsp:include page="_header.jsp" />
<jsp:include page="_menu.jsp" />

<div class="page-title">Product</div>

<c:if test="${not empty errorMessage }">
    <div class="error-message">
            ${errorMessage}
    </div>
</c:if>

<form:form modelAttribute="productForm" method="POST" enctype="multipart/form-data">
    <table style="text-align:left;">
        <tr>
            <td>Code *</td>
            <td style="color:red;">
                <c:if test="${not empty productForm.code}">
                    <form:hidden path="code"/>
                    ${productForm.code}
                </c:if>
                <c:if test="${empty productForm.code}">
                    <form:input path="code" />
                    <form:hidden path="newProduct" />
                </c:if>
            </td>
            <td><form:errors path="code" class="error-message" /></td>
        </tr>

        <tr>
            <td>Name *</td>
            <td><form:input path="name" /></td>
            <td><form:errors path="name" class="error-message" /></td>
        </tr>

        <tr>
            <td>Price *</td>
            <td><form:input path="price" /></td>
            <td><form:errors path="price" class="error-message" /></td>
        </tr>
        <tr>
            <td>Image</td>
            <td>
                <img src="${pageContext.request.contextPath}/productImage?code=${productForm.code}" width="100"/></td>
            <td> </td>
        </tr>
        <tr>
            <td>Upload Image</td>
            <td><form:input type="file" path="fileData"/></td>
            <td> </td>
        </tr>


        <tr>
            <td>&nbsp;</td>
            <td><input type="submit" value="Submit" /> <input type="reset"
                                                              value="Reset" /></td>
        </tr>
    </table>
</form:form>




<jsp:include page="_footer.jsp" />

</body>
</html>