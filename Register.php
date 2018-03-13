<?php
  //  require("password.php");

    $servername = "localhost";
    $database = "id5043778_profiledata";
    $username = "id5043778_belaytionshipsrib1356";
    $password = "macbrown12";
    //$connect = mysqli_connect("localhost", "id5043778_belaytionshipsrib1356", "macbrown12", "id5043778_profiledata");
    $connect = mysqli_connect($servername, $username, $password, $database);
    //$username = '';
    //$password = '';//"" When you want to append stuff later
                   //0  When you want to add numbers later

    //$username = isset($_POST['$username']) ? $_POST['$username'] : '';
    //$password = isset($_POST['$password']) ? $_POST['$password'] : '';


    //$conn = new mysqli($servername, $username, $password, $database);
    //Check connection
    // if ($connect->connect_error) {
    //    die("Connection failed: " . $connect->connect_error);
    // }else{
    //   echo "Connected successfully";
    // };


    $username = $_POST["username"];
    $password = $_POST["password"];
      //
      // //$sql = "INSERT INTO UserInformation (username, password) VALUES ('Rob', 'Doe')";
    $statement = mysqli_prepare($connect, "INSERT INTO UserInformation (username, password) VALUES (?, ?)");
    mysqli_stmt_bind_param($statement, "ss", $username, $password);
    mysqli_stmt_execute($statement);
    mysqli_stmt_close($statement);

      //
      // if (mysqli_query($connect, $statement)) {
      //   echo "New record created successfully";
      // } else {
      //   echo "Error: " . $sql . "<br>" . mysqli_error($conn);
      // }


    $response = array();
    $response["success"] = true;
    print_r(json_encode($response));

      //
      //
    //mysqli_close($connect);

    // if (isset($username)) {
    // echo "Username has been set to:   ", $username;
    // }
    //
    // if (isset($password)) {
    // echo "Password has been set to:    ", $password;
    // }
  //   function registerUser() {
      //  global $connect, $username, $password;
      //  $passwordHash = password_hash($password, PASSWORD_DEFAULT);

  //  }

    // function usernameAvailable() {
    //     global $connect, $username;
    //     $statement = mysqli_prepare($connect, "SELECT * FROM user WHERE username = ?");
    //     mysqli_stmt_bind_param($statement, "s", $username);
    //     mysqli_stmt_execute($statement);
    //     mysqli_stmt_store_result($statement);
    //     $count = mysqli_stmt_num_rows($statement);
    //     mysqli_stmt_close($statement);
    //     if ($count < 1){
    //         return true;
    //     }else {
    //         return false;
    //     }
    // }print_r(json_encode($response));





    //if (usernameAvailable()){
    //    registerUser();
  //      $response["success"] = true;
  //  }


?>
