<?php
include 'db_connect.php';

$sql = "SELECT cid, cname FROM customer";
$result = $conn->query($sql);

if ($result->num_rows > 0) {
    echo "<h2>Customer Information</h2>";
    echo "<table border='1'><tr><th>Customer ID</th><th>Customer Name</th></tr>";
    while($row = $result->fetch_assoc()) {
        echo "<tr><td>" . $row["cid"]. "</td><td>" . $row["cname"]. "</td></tr>";
    }
    echo "</table>";
} else {
    echo "No customers found.";
}
echo "<form action='menu.html'>".
     "<input type='submit' value='HOME'>".
     "</form>";
echo "<form action='insertcustomer.php'>".
     "<input type='submit' value='CREATE'>".
     "</form>";

$conn->close();
?>
