<?php
include 'db_connect.php';

if ($_SERVER["REQUEST_METHOD"]=="POST") {
    $cname = $_POST["cname"];

    if (!empty($cname)) {
        $stmt = $conn->prepare("INSERT INTO customer (cname) VALUES (?)");
        $stmt->bind_param("s", $cname);

        if ($stmt->execute()) {
            echo "New customer created successfully.";
        } else {
            echo "Error: " . $stmt->error;
        }
        $stmt->close();
    } else {
        echo "Customer name is required.";
    }
    echo "<form action='menu.html'>".
    "<input type='submit' value='HOME'>".
    "</form>";
echo "<form action='displaycustomer.php'>".
    "<input type='submit' value='DISPLAY'>".
    "</form>";
}

$conn->close();
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create Customer</title>
</head>
<body>
    <h2>Create Customer</h2>
    <form action="insertcustomer.php" method="POST">
        Customer Name: <input type="text" name="cname" required><br><br>
        <input type="submit" value="Create Customer">
    </form>
</body>
</html>
