<?php
include 'db_connect.php';

if ($_SERVER["REQUEST_METHOD"]=="POST") {
    $atype = $_POST["atype"];
    $balance = $_POST["balance"];
    $cid = $_POST["cid"];

    if (!empty($atype) && is_numeric($balance) && !empty($cid)) {
        $stmt = $conn->prepare("INSERT INTO account (atype, balance, cid) VALUES (?, ?, ?)");
        $stmt->bind_param("sdi", $atype, $balance, $cid);

        if ($stmt->execute()) {
            echo "New account created successfully.";
        } else {
            echo "Error: " .$stmt->error;
        }

        $stmt->close();
    } else {
        echo "All fields are required, and balance must be a number.";
    }
    echo "<form action='menu.html'>".
    "<input type='submit' value='HOME'>".
    "</form>";
echo "<form action='displayacc.php'>".
    "<input type='submit' value='DISPLAY'>".
    "</form>";
}

$conn->close();
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create Account</title>
</head>
<body>
    <h2>Create Account</h2>
    <form action="insertacc.php" method="POST">
        Account Type: <input type="text" name="atype" required><br><br>
        Balance: <input type="text" name="balance" required><br><br>
        Customer ID: <input type="text" name="cid" required><br><br>
        <input type="submit" value="Create Account">
    </form>
</body>
</html>
