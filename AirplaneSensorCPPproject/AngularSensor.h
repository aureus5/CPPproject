#ifndef PROJECT2_ANGULARSENSOR_H
#define PROJECT2_ANGULARSENSOR_H

#include<string>
#include "Sensor.h"
#include "Signal.h"
using std::string;

namespace Project2
{
	class AngularSensor:public Sensor
	{
	public:
		AngularSensor(string name, Signal &source);
	protected:
		virtual const string getUnits() const;
	};
}

#endif