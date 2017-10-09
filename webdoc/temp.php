<html>
<title> FTIT17 - Wetterstation Übermittlung</title>
<body>
<h1>FTIT17 - Wetterstation Übermittlung</h1>
<?php
	foreach($_GET as $key => $value){
		echo "<p>" . $key . ' : ' . $value . "</p>";
	}
	
	if(empty($_GET["temp"] )) {
		echo "<p><div>Parameter 'temp' fehlt.</div>";
		echo "<div>Übermittlung fehlerhaft!</div></p>";
	}
	if(empty($_GET["dbg"] )) {
		echo "<p><div>Parameter 'dbg' fehlt.</div>";
		echo "<div>Übermittlung fehlerhaft!</div></p>";
	}
	if(empty($_GET["token"] )) {
		echo "<p><div>Parameter 'token' fehlt.</div>";
		echo "<div>Übermittlung fehlerhaft!</div></p>";
	}
	if(empty($_GET["timestamp"] )) {
		echo "<p><div>Parameter 'timestamp' fehlt.</div>";
		echo "<div>Übermittlung fehlerhaft!</div></p>";
	}
	# value noch prüfen ob valide
	
	?>
</body>
