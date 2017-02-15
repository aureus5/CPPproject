#include "ConstantSignal.h"
#include "Signal.h"

Project2::ConstantSignal::ConstantSignal(double voltageOffset, Time &timeOffset):Signal(voltageOffset, timeOffset)
{
	//done implementing
}

double
Project2::ConstantSignal::getVoltageAtTime(Time &t) const
{
	return getVoltageOffset();
}