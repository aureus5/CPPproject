#ifndef PROJECT2_CONSTANTSIGNAL_H
#define PROJECT2_CONSTANTSIGNAL_H

#include "Signal.h"
#include "Time.h"


namespace Project2
{
	class ConstantSignal:public Signal
	{
	public:
		ConstantSignal(double voltageOffset, Time &timeOffset);
		virtual double getVoltageAtTime(Time &t) const;
	};
}

#endif