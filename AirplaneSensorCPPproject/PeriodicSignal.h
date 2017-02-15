#ifndef PROJECT2_PERIODICSIGNAL_H
#define PROJECT2_PERIODICSIGNAL_H

#include "Time.h"
#include "Signal.h"

namespace Project2
{
	class PeriodicSignal:public Signal
	{
	public:
		PeriodicSignal(double voltageOffset, Time &timeOffset,
		double minVoltage, double maxVoltage,  Time &period);
		virtual double getVoltageAtTime(Time &t) const=0;
	protected:
		double getMinVoltage() const;
		double getMaxVoltage() const;
		Time getPeriod() const;
	private:
		double minValue;
		double maxValue;
		Time period;
	};
}

#endif