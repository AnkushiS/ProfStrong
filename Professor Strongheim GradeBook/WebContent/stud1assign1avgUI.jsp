
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>
<style type="text/css">
    .bs-example{
    	margin-top: 10%;
    
    	
      }
</style>
<body>
<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="GradeBook.html">GradeBook</a>
			</div>
				<div>
				<ul class="nav navbar-nav">
					<li class="active"><a href="highlow">one type high low</a></li>
				</ul>
			</div>
			<div>
				<ul class="nav navbar-nav">
					<li class="active"><a href="stud1assign1avg">one student average assign type</a></li>
				</ul>
			</div>
			<div>
				<ul class="nav navbar-nav">
					<li class="active"><a href="allstud1type">all student one assign type</a></li>
				</ul>
			</div>
			
			<div>
				<ul class="nav navbar-nav">
					<li class="active"><a href="stud1avg">average for student</a></li>
				</ul>
			</div>
			
			<div>
				<ul class="nav navbar-nav">
					<li class="active"><a href="StudentInfo">Student Input</a></li>
				</ul>
			</div>
			
			<div>
				<ul class="nav navbar-nav">
					<li class="active"><a href="stud1assign1">one student one assign</a></li>
				</ul>
			</div>
			
		
		<div>
				<ul class="nav navbar-nav">
					<li class="active"><a href="assigntype">all assign type </a></li>
				</ul>
			</div>
			
			<div>
				<ul class="nav navbar-nav">
					<li class="active"><a href="Allassign">All Assignments</a></li>
				</ul>
			</div>
			</div></nav>

		<form action="stud1assign1avg" method="post" class="bs-example">
		
        <div class="form-group" style="width:30%">
            <label for="Student_name">Student Name</label>
            <input type="text" class="form-control" name="Student_name" placeholder="Student Name">
        </div>
        
        <div class="form-group" style="width:30%">
            <label for="assign_type">Assignment Type</label>
            <input type="text" class="form-control" name="assign_type" placeholder="assignment type">
        </div>
        
        <input style=margin-top:10 type="submit" value="submit" name="actSubmit" >    
	
	</form>

	<div class="bs-example">
	${message}
	</div>
	
</body>
</html>