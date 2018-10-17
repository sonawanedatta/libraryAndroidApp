<?php
  //importing dbConnect.php script
 require_once('db_connect.php');
 if($_SERVER['REQUEST_METHOD']=='POST'){
 //POSTting values
 $username = $_POST['email'];
 $password = $_POST['password'];

 //Creating sql query
 $sql = "SELECT * FROM tbl_users WHERE email='$username' AND password='".md5($password)."'";

 //executing query
 $result = mysqli_query($con,$sql);
 $count = mysqli_num_rows($result);

 //if we got some result
 if($count>0){
 //displaying success
 echo "success";
 }else{
 //displaying failure
 echo "Invalid Credentials";
 }
 mysqli_close($con);
 }
