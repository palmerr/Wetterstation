#!/usr/bin/python

# Sie benoetigen einen Cronjob-Eintrag der Form
# 30 * * * * pi <Pfad zum Script>/weather_data.py

import re, os, urllib2, time
import RPi.GPIO as GPIO

#-----------------------------
# globale Variablen
url = ""
# SensorID Pfad
w1path = "/sys/bus/w1/devices/"
# Error LED Pin Nummer
pin_led = 11
# send url
url = "http://server?temp={0}&sensorid={1}"
# mysql
user = 'pi'
password = 'pass'
server = 'localhost'
database = 'weather'
#------------------------------


# alle angeschlossenen SensorIDs holen
sensoridList=[]
for d in os.listdir(w1path):
	if d == 'w1_bus_master1':
		continue
        if os.path.isdir(os.path.join(w1path,d)):
                sensoridList.insert(-1, d)
		print "Sensor: " + d

def get_temp(path):
        temp = None
        try:
                GPIO.output(pin_led, GPIO.LOW)
                datei = open(path, "r")
                zeile = datei.readline()
                if re.match(r"([0-9a-f]{2} ){9}: crc=[0-9a-f]{2} YES", zeile):
                        zeile = datei.readline()
                        m = re.match(r"([0-9a-f]{2} ){9}t=([+-]?[0-9]+)", zeile)
                        if m:
                                temp = float(m.group(2))/1000
                datei.close()
        except IOError:
                print "Konnte Sensor (" + path  + ")nicht lesen."
                GPIO.output(pin_led, GPIO.HIGH)
        return temp


def send_temp(temp, sensorid):
        send_url = url.format(temp, sensorid)
        try:
                #result = urllib2.urlopen(send_url);
                print send_url
		f = open('temp.log', 'w')
		print >> f, time.strftime("%Y-%m-%d %H:%M:%S",time.localtime())
		print >> f, send_url
		f.close
		# sql insert string
		#
		#
        except urllib2.HTTPError as error:
                print error.code, error.reason
                GPIO.output(pin_led, GPIO.HIGH)

# main Funktion
if __name__ == '__main__':

	GPIO.setwarnings(False)
        GPIO.setmode(GPIO.BOARD)
        GPIO.setup(pin_led, GPIO.OUT)

        # jeden Sensor abfragen
       	for sensorid in sensoridList:
               	sensoridpath = w1path + "/%s/w1_slave" % sensorid
                #print "Get sensor from: " + sensoridpath

                temp = get_temp(sensoridpath)
		if None != temp:
               	        send_temp(temp, sensorid)
