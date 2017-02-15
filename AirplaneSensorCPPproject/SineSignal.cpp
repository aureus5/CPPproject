#include <math.h>
#include "PeriodicSignal.h"
#include "SineSignal.h"
#include "Time.h"

#define PI 3.14159265358979323846

Project2::SineSignal::SineSignal(double voltageOffset, Time &timeOffset,
	double minVoltage, double maxVoltage, Time &period) :PeriodicSignal(voltageOffset, timeOffset,
	minVoltage, maxVoltage, period)
{}

double 
Project2::SineSignal::getVoltageAtTime(Time &t) const
{
	//V(t) = valueOffset + min + (max - min) / 2
		//+ sine((t + timeOffset) * 2 * PI / period) * (max - min) / 2;

	double intermediate3;      // to carry the results of (t + timeOffset)
	intermediate3 = t.getTotalTimeAsSeconds() + PeriodicSignal::getTimeOffset().getTotalTimeAsSeconds();

	double maxMinusMin;       // to carry the result of (max-min)
	maxMinusMin = getMaxVoltage() - getMinVoltage();

	double intermediate4;    // to carry the result of sine((t + timeOffset) * 2 * PI / period) * (max - min) / 2
	intermediate4 = sin(intermediate3 * 2 * PI / (getPeriod().getTotalTimeAsSeconds()))*maxMinusMin/2;

	double sineSignalVoltage;  // to carry the result of voltage at Time &t
	sineSignalVoltage = PeriodicSignal::getVoltageOffset() + getMinVoltage() + maxMinusMin/2 + intermediate4;

	return sineSignalVoltage;
}