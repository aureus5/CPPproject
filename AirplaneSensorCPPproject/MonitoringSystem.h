#ifndef PROJECT2_MONITORINGSYSTEM_H
#define PROJECT2_MONITORINGSYSTEM_H

#include <iostream>
#include <vector>
#include "DataRecorder.h"
#include "Sensor.h"
#include "Time.h"

using std::ostream;
using std::vector;

namespace Project2
{
	class MonitoringSystem
	{
	public:
		MonitoringSystem(ostream &out);
		void addSensor(Sensor *s);
		void takeReading(Time &t);
	private:
		DataRecorder recorder;
		vector<Sensor*> sensors;
	};
}

#endif