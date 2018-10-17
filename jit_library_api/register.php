<?php
require "db_connect.php";
 //print_r($_POST);die;
$name = $_POST["username"];
$password = $_POST["password"];
$email = $_POST["email"];
$branch = $_POST["branch"];
$year = $_POST["year"];
$gender = $_POST["gender"];
$mobile = $_POST["mobile"];

$sql = "INSERT INTO tbl_users (username, password, email, branch, year, gender, mobile) VALUES ('".$name."', '".md5($password)."', '".$email."','".$branch."', '".$year."', '".$gender."', '".$mobile."');";
//print_r($sql);

if(!$result = mysqli_query($con, $sql)){
    echo "Unable to save the data to the database.";
}else{

	echo "Registeration Successfully done!";
}

?>
