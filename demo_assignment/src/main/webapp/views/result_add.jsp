<html>
<style>
    table {
        border-collapse: collapse;
        width: 50%;
    }

    th, td {
        text-align: left;
        padding: 8px;
    }

    tr:nth-child(even) {background-color: #f2f2f2;}
</style>
<head>
    <title>MyPatients | Success </title>
</head>
<h3>Record added successfully.</h3>
<body>
<table>
    <tr>
        <td>Patient Name:</td>
        <td>${patient.getName()}</td>
    </tr>
    <tr>
        <td>Patient Address:</td>
        <td>${patient.getAddress()}</td>
    </tr>
    <tr>
        <td>Patient Email:</td>
        <td>${patient.getEmail()}</td>
    </tr>
    <tr>
        <td>Patient Phone Number:</td>
        <td>${patient.getPhone_number()}</td>
    </tr>
    <tr>
        <td>Patient Password:</td>
        <td>${patient.getPassword()}</td>
    </tr>
</table>
<h3><a href="home">Go to home</a></h3>


</body>
</html>