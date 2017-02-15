
#include <string>
#include "AngularSensor.h"
#include "Sensor.h"
#include "Signal.h"

using std::string;



Project2::AngularSensor::AngularSensor(string name, Signal &source) :Sensor(name, source) {}

const string 
Project2::AngularSensor::getUnits() const
{
	return "Radians";
}