<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<style>
    table {
        border-collapse: collapse;
        width: 100%;
    }

    th, td {
        text-align: left;
        padding: 8px;
    }

    tr:nth-child(even) {background-color: #f2f2f2;}
</style>
<head>
    <title>MyPatients | Patient Records</title>
</head>
<body>
<h1> All Records In Date Ascending Order
</h1>

<c:if test="${not empty lists}">
<table>
        <tr >
            <td bgcolor="#87cefa"><b>Name</b></td>
            <td bgcolor="#87cefa"><b>Address</b></td>
            <td bgcolor="#87cefa"><b>Email</b></td>
            <td bgcolor="#87cefa"><b>Phone Number</b></td>

        </tr>
    <c:forEach var="listValue" items="${lists}">
            <tr>
            <td>${listValue.getName()}</td>
            <td>${listValue.getAddress()}</td>
            <td>${listValue.getEmail()}</td>
            <td>${listValue.getPhone_number()}</td>
            </tr>
        </c:forEach>
</table>
    <h3>    <a href="home">Go to Home</a></h3>
</c:if>
</body>
</html>