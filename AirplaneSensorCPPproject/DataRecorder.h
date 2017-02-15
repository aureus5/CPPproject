#ifndef PROJECT2_DATARECORDER_H
#define PROJECT2_DATARECORDER_H

#include <iostream>
#include <string>
#include "Time.h"
using std::ostream;
using std::string;

namespace Project2
{
	class DataRecorder
	{
	public:
		ostream &out;
		DataRecorder(ostream &outPut);
		void log(Time timestamp, string sensorName,
			double sensorVoltage, string sensorUnits);
	};
}

#endif