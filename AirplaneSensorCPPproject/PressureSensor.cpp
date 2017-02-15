#include "PressureSensor.h"

Project2::PressureSensor::PressureSensor(string name, Signal &source) :Sensor(name, source) {}

const string
Project2::PressureSensor::getUnits() const
{
	return "Pounds per square inch (PSI)";
}
