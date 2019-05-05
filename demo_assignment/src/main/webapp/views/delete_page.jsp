<!DOCTYPE html >
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<style>
    input[type=text], select {
        width: 100%;
        padding: 12px 20px;
        margin: 8px 0;
        display: inline-block;
        border: 1px solid #ccc;
        border-radius: 4px;
        box-sizing: border-box;
    }
    input[type=password], select {
        width: 100%;
        padding: 12px 20px;
        margin: 8px 0;
        display: inline-block;
        border: 1px solid #ccc;
        border-radius: 4px;
        box-sizing: border-box;
    }

    input[type=submit] {
        width: 100%;
        background-color: #4CAF50;
        color: white;
        padding: 14px 20px;
        margin: 8px 0;
        border: none;
        border-radius: 4px;
        cursor: pointer;
    }

    input[type=submit]:hover {
        background-color: #45a049;
    }

    div {
        border-radius: 5px;
        background-color: #f2f2f2;
        padding: 20px;
    }
</style>
<head>
    <title>MyPatients | Login</title>
</head>
<body>
<h3>Enter The Patient's Name And Password</h3>
<form:form method="POST"
           action="do_delete" modelAttribute="patient"  >
    <div class="container">
        <table>
            <tr>
                <td><form:label path="Name">Name</form:label></td>
                <td><form:input path="Name"/></td>
            </tr>
            <tr>
                <td><form:label path="Password" >
                    Password</form:label></td>
                <td><form:password path="Password"/></td>
            </tr>
            <tr>
                <td><input type="submit" value="Submit"/></td>
            </tr>
        </table>
        <h4>${error}</h4>
    </div>
</form:form>
<h3>    <a href="home">Go to Home</a></h3>
</body>
</html>