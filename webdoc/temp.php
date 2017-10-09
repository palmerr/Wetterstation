<html>
<title> FTIT17 - Wetterstation Übermittlung</title>
<body>
<?php
	foreach($_GET as $key 0> $value){
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
