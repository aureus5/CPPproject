#ifndef PROJECT2_TEMPERATURESENSOR_H
#define PROJECT2_TEMPERATURESENSOR_H

#include <string>
#include "Sensor.h"
#include "Signal.h"

using std::string;

namespace Project2
{
	class TemperatureSensor:public Sensor
	{
	public:
		TemperatureSensor(string name, Signal &source);
		virtual const string getUnits() const;
	};
}

#endif