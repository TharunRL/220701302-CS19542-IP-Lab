<?php
include 'db_connect.php';

$sql = "SELECT empid, ename, desig, dept, doj, salary FROM empdetails";
$result = $conn->query($sql);

if ($result->num_rows > 0) {
    echo "<h2>Employee Details</h2>";
    echo "<table border='1'>
        <tr>
            <th>Employee ID</th>
            <th>Employee Name</th>
            <th>Designation</th>
            <th>Department</th>
            <th>Date of Joining</th>
            <th>Salary</th>
        </tr>";
    
    while ($row = $result->fetch_assoc()) {
        echo "<tr>
                <td>" . $row['empid'] . "</td>
                <td>" . $row['ename'] . "</td>
                <td>" . $row['desig'] . "</td>
                <td>" . $row['dept'] . "</td>
                <td>" . $row['doj'] . "</td>
                <td>" . $row['salary'] . "</td>
            </tr>";
    }
    echo "</table>";
} else {
    echo "No employee records found.";
}

$conn->close();
?>
<html>
    <body><form action="menu.html">
        <input type="submit" value="HOME">
    </form></body>
</html>
