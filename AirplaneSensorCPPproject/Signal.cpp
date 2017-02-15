#include "Signal.h"


Project2::Signal::Signal(double valueOffset, Time &timeOffset) :voltageOffset(valueOffset), 
timeOffset(timeOffset)
{}

double
Project2::Signal::getVoltageOffset() const
{
	return voltageOffset;
}

Project2::Time
Project2::Signal::getTimeOffset() const
{
	return timeOffset;
}