<?php
require "db_connect.php";

$sql = "SELECT * FROM tbl_books WHERE branch='".$_GET['branch']."' AND year='".$_GET['year']."'";

$result = mysqli_query($con, $sql);

if($result)
{
	while($row=mysqli_fetch_assoc($result))
	{
	$data[] = $row;
	}
	echo json_encode($data);
}else
{
	echo "Not Found";
}

?>
