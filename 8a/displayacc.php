<?php
include 'db_connect.php';

$sql = "SELECT ano, atype, balance, cid FROM account";
$result = $conn->query($sql);

if ($result->num_rows > 0) {
    echo "<h2>Account Information</h2>";
    echo "<table border='1'><tr><th>Account Number</th><th>Account Type</th><th>Balance</th><th>Customer ID</th></tr>";
    while($row = $result->fetch_assoc()) {
        echo "<tr><td>" . $row["ano"]. "</td><td>" . $row["atype"]. "</td><td>" . $row["balance"]. "</td><td>" . $row["cid"]. "</td></tr>";
    }
    echo "</table>";
} else {
    echo "No accounts found.";
}
echo "<form action='menu.html'>".
     "<input type='submit' value='HOME'>".
     "</form>";
echo "<form action='insertacc.php'>".
     "<input type='submit' value='CREATE'>".
     "</form>";
$conn->close();
?>
