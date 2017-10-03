<?php
	if(isset($_GET['stationID'])){
		echo 'Station: ' . htmlspecialchars($_GET['stationID']) . "!";
	}
?>