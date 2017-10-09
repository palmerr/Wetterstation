<html>
<title> FTIT17 - Wetterstation Übermittlung</title>
<body>
<h1>FTIT17 - Wetterstation Übermittlung</h1>
<?php
	foreach($_GET as $key => $value){
		echo $key . ' : ' . $value;
	}
	
	if(empty($_GET["temp"] ) {
		echo "Parameter 'temp' fehlt."
		echo "Übermittlung fehlerhaft!";
	}
	if(empty(empty($_GET["dbg"] ) {
		echo "Parameter 'dbg' fehlt."
		echo "Übermittlung fehlerhaft!";
	}
	if(empty($_GET["token"] ) {
		echo "Parameter 'token' fehlt."
		echo "Übermittlung fehlerhaft!";
	}
	if(empty($_GET["timestamp"] ) {
		echo "Parameter 'timestamp' fehlt."
		echo "Übermittlung fehlerhaft!";
	}
	# value noch prüfen ob valide
	
	?>
</body>
