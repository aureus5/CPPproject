#ifndef PROJECT2_PRESSURESENSOR_H
#define PROJECT2_PRESSURESENSOR_H

#include <string>
#include "Sensor.h"
#include "Signal.h"
using std::string;

namespace Project2
{
	class PressureSensor:public Sensor
	{
	public:
		PressureSensor(string name, Signal &source);
		virtual const string getUnits() const;
	};
}

#endif