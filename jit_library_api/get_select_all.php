<?php
require "db_connect.php";
$userbloodgroup = $_GET['blood_group'];
$usercity = $_GET['address'];
$sql = "SELECT * FROM tbl_users WHERE blood_group='$userbloodgroup' AND address = '$usercity'" ;
 print_r($sql);
$result = mysqli_query($con, $sql);

	while($row=mysqli_fetch_assoc($result))
	{$data[] = $row;	
	}
	echo json_encode($data);
?>