<?php
include 'db_connect.php';

$empid = $ename = $desig = $dept = $doj = $salary = "";

if (isset($_POST['find'])) {
    $empid = $_POST['empid'];
    $sql = "SELECT ename, desig, dept, doj, salary FROM empdetails WHERE empid = ?";
    $stmt = $conn->prepare($sql);
    $stmt->bind_param("i", $empid);
    $stmt->execute();
    $result = $stmt->get_result();

    if ($result->num_rows > 0) {
        $row = $result->fetch_assoc();
        $ename = $row['ename'];
        $desig = $row['desig'];
        $dept = $row['dept'];
        $doj = $row['doj'];
        $salary = $row['salary'];
    } else {
        echo "No employee found with ID $empid";
    }

    $stmt->close();
}

if (isset($_POST['update'])) {
    $empid = $_POST['empid'];
    $ename = $_POST['ename'];
    $desig = $_POST['desig'];
    $dept = $_POST['dept'];
    $doj = $_POST['doj'];
    $salary = $_POST['salary'];

    $sql = "UPDATE empdetails SET ename=?, desig=?, dept=?, doj=?, salary=? WHERE empid=?";
    $stmt = $conn->prepare($sql);
    $stmt->bind_param("ssssdi", $ename, $desig, $dept, $doj, $salary, $empid);

    if ($stmt->execute()) {
        echo "Employee details updated successfully.";
    } else {
        echo "Error updating record: " . $stmt->error;
    }

    $stmt->close();
}

$conn->close();
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Update Employee Details</title>
</head>
<body>

    <h2>Find Employee</h2>
    <form action="update_employee.php" method="POST">
        Employee ID: <input type="number" name="empid" value="<?php echo $empid; ?>" required><br><br>
        <input type="submit" name="find" value="Find Employee">
    </form>
    <?php if (!empty($ename)): ?>
    <h2>Update Employee</h2>
    <form action="update_employee.php" method="POST">
        <input type="hidden" name="empid" value="<?php echo $empid; ?>"><br><br>
        Employee Name: <input type="text" name="ename" value="<?php echo $ename; ?>" required><br><br>
        Designation: <input type="text" name="desig" value="<?php echo $desig; ?>" required><br><br>
        Department: <input type="text" name="dept" value="<?php echo $dept; ?>" required><br><br>
        Date of Joining: <input type="date" name="doj" value="<?php echo $doj; ?>" required><br><br>
        Salary: <input type="text" name="salary" value="<?php echo $salary; ?>" required><br><br>
        <input type="submit" name="update" value="Update Employee">
    </form>
    <?php endif; ?>
    <form action="menu.html">
        <input type="submit" value="HOME">
    </form>
    <form action="retrieve_employee.php">
        <input type="submit" value="DISPLAY">
    </form>

</body>
</html>
