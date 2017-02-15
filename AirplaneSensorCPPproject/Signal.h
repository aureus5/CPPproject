#ifndef PROJECT2_SIGNAL_H
#define PROJECT2_SIGNAL_H

#include "Time.h"

namespace Project2
{
	class Signal
	{
	public:
		Signal(double valueOffset, Time &timeOffset);
		virtual double getVoltageAtTime(Time &t) const=0;
	protected:
		double getVoltageOffset() const;
		Time getTimeOffset() const;
	private:
		double voltageOffset;
		Time timeOffset;
	};
}

#endif