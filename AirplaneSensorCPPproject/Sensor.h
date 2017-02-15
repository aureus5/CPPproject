#ifndef PROJECT2_SENSOR_H
#define PROJECT2_SENSOR_H

#include <string>
#include "DataRecorder.h"
#include "Signal.h"
#include "Time.h"
using std::string;

namespace Project2
{
	class Sensor
	{
	public:
		Sensor(string name, Signal &source);
		void takeReading(Time &t, DataRecorder &recorder);
	protected:
		virtual const string getUnits() const=0;
		const string getName() const;
		const Signal& getSource() const;
	private:
		string name;
		Signal *source;
	};
}

#endif