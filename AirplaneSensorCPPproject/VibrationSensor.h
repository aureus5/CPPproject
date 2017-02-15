#ifndef PROJECT2_VIBRATIONSENSOR_H
#define PROJECT2_VIBRATIONSENSOR_H

#include <string>
#include "Sensor.h"
#include "Signal.h"

using std::string;

namespace Project2
{
	class VibrationSensor:public Sensor
	{
	public:
		VibrationSensor(string name, Signal &source);
		virtual const string getUnits() const;
	};
}

#endif