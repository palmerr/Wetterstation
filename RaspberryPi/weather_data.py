#!/usr/bin/python

# Sie benoetigen einen Cronjob-Eintrag der Form
# 30 * * * * pi <Pfad zum Script>/weather_data.py

import re, os, urllib2
import RPi.GPIO as GPIO

#-----------------------------
# globale Variablen
url = ""
# SensorID Pfad
w1path = "/home/pi/w1/devices/"
# Error LED Pin Nummer
pin_led = 11
#------------------------------


# alle angeschlossenen SensorIDs holen
sensoridList=[]
for d in os.listdir(w1path):
        if os.path.isdir(os.path.join(w1path,d)):
                sensoridList.insert(-1, d)

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

	
def send_temp(temp):
        send_url = url % temp
        try:
                result = urllib2.urlopen(send_url);
        except urllib2.HTTPError as error:
                print error.code, error.reason
                GPIO.output(pin_led, GPIO.HIGH)

# main Funktion
if __name__ == '__main__':

        # jeden Sensor abfragen
        for sensorid in sensoridList:
                sensoridpath = "~/w1/devices/%s/w1_slave" % sensorid
                print "Get sensor from: " + sensoridpath


                GPIO.setwarnings(False)
                GPIO.setmode(GPIO.BOARD)
                GPIO.setup(pin_led, GPIO.OUT)

                temp = get_temp(sensoridpath)

                if None != temp:
                        send_temp(temp)
