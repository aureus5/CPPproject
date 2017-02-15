#include "VibrationSensor.h"

Project2::VibrationSensor::VibrationSensor(string name, Signal &source) :Sensor(name, source) {}

const string
Project2::VibrationSensor::getUnits() const
{
	return "Hertz (Hz)";
}