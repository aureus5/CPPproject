#include <math.h>
#include "PeriodicSignal.h"
#include "SawtoothSignal.h"
#include "Time.h"


Project2::SawtoothSignal::SawtoothSignal(double voltageOffset, Time &timeOffset,        //call PeriodicSignal constructor
	double minVoltage, double maxVoltage, Time &period) :PeriodicSignal(voltageOffset, timeOffset,
	minVoltage, maxVoltage, period)
{}

double
Project2::SawtoothSignal::getVoltageAtTime(Time &t) const
{
	double intermediate1;      // to carry the results of (t + timeOffset)
	intermediate1 = t.getTotalTimeAsSeconds() + PeriodicSignal::getTimeOffset().getTotalTimeAsSeconds();

	double maxMinusMin;       // to carry the result of (max-min)
	maxMinusMin = getMaxVoltage() - getMinVoltage();

	double intermediate2;    // to carry the result of ((t + timeOffset) * (max - min) / period) % (max - min)
	intermediate2 = fmod(intermediate1*maxMinusMin / getPeriod().getTotalTimeAsSeconds(), maxMinusMin);

	double sawToothVoltage;  // to carry the result of voltage at Time &t
	sawToothVoltage = PeriodicSignal::getVoltageOffset() + getMinVoltage() + intermediate2;
	
	return sawToothVoltage;
}