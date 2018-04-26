 <?php  
 
 
	if (isset($_POST['username']) && isset($_POST['password'])) 
	{
		
		
 
		// receiving the post params
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
			
			
		
			$sql = "CALL Login('" . $username . "','" . $password . "')";
			
			$result = mysqli_query($db, $sql);
			

			 
      		if($result->num_rows > 0) 

			{
				$response["error"] = FALSE;
				$row = $result->fetch_assoc();
				$id = $row['UserID'];
				$uname = $row['LastName'];
				$response["uname"] = $uname;
				//	echo $id;
				session_start();
				$_SESSION['user'] = $uname;
				
				echo json_encode($response);
        		
			} 
			
			else 
			{
				
				 $response["error"] = TRUE; 
				 $response["error_msg"] = "Invalid Login. Please Try Again.";
				 echo json_encode($response);
			}
	}
 ?>  
 