 <?php  
 
 
 
	if (isset($_POST['username']) && isset($_POST['password']) && isset($_POST['firstname']) && isset($_POST['lastname']) && isset($_POST['email'])) 
		
	{
 
		$firstname= $_POST['firstname'];
		$lastname = $_POST['lastname'];
		$email = $_POST['email'];
		$username = $_POST['username'];
		$password = $_POST['password'];
		
		$password = md5($password);
		
		static $db;

			if(!isset($db)) 
			{
				$db = mysqli_connect("localhost", "webalex", "vr8aTp573L", "webalex_SwoleAF");
			}

			if($db === false) 
			{
				return mysqli_connect_error();
			}
			
			
			$response = array("error" => FALSE);
			
		
			$sql = "CALL CreateUser('" . $username . "','" . $email . "',
			'" . $firstname . "','" . $lastname . "','" . $password . "')";
			
			$result = mysqli_query($db, $sql);
			
				

			if($result->num_rows > 0) 
			{	
				$response["error"] = FALSE; 
				//$row = $result->fetch_assoc();
				$id = $row["GeneratedID"];
				//echo $id;
				session_start();
				$_SESSION['user'] = $id;
				echo json_encode($response);
			} 
			
			
			else 
			{
				
				 $response["error"] = TRUE; 
				 $response["error_msg"] = "Error Signing Up. Please Try Again.";
				 echo json_encode($response);
			}
	}
 ?>  