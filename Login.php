<?php
    //require("password.php");

    //$con = mysqli_connect("localhost", "id5043778_belaytionshipsrib1356", "macbrown12", "id5043778_profiledata");
    $servername = "localhost";
    $database = "id5043778_profiledata";
    $username = "id5043778_belaytionshipsrib1356";
    $password = "macbrown12";

    $connect = mysqli_connect($servername, $username, $password, $database);

    // if ($connect->connect_error) {
    //    die("Connection failed: " . $connect->connect_error);
    // }else{
    //   echo "Connected successfully";
    // };
    $username = $_POST["username"];
    $password = $_POST["password"];

    $statement = mysqli_prepare($connect, "SELECT * FROM UserInformation WHERE username = ? AND password = ?");
    mysqli_stmt_bind_param($statement, "ss", $username, $password);
    mysqli_stmt_execute($statement);

    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $user_id, $username, $password);
    //mysqli_stmt_bind_result($statement, $colUserID, $colUsername, $colPassword);

    $response = array();
    $response["success"] = false;

    while(mysqli_stmt_fetch($statement)){
       $response["success"] = true;
       $response["username"] = $username;
       $response["password"] = $password;
   }
    // while(mysqli_stmt_fetch($statement)){
    //     if (password_verify($password, $colPassword)) {
    //         $response["success"] = true;
    //         $response["username"] = $colUsername;
    //     }
    // }

    print_r(json_encode($response));
?>
