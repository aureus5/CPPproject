#include "PeriodicSignal.h"



Project2::PeriodicSignal::PeriodicSignal(double voltageOffset, Time &timeOffset,
	double minVoltage, double maxVoltage, Time &period) :Signal(voltageOffset, timeOffset),
	minValue(minVoltage), maxValue(maxVoltage), period(period)
{}

double
Project2::PeriodicSignal::getMinVoltage() const
{
	return minValue;
}

double
Project2::PeriodicSignal::getMaxVoltage() const
{
	return maxValue;
}

Project2::Time
Project2::PeriodicSignal::getPeriod() const
{
	return period;
}