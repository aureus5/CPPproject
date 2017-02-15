#include "Sensor.h"

Project2::Sensor::Sensor(string name, Signal &source) :name(name), source(&source){}

void
Project2::Sensor::takeReading(Time &t, DataRecorder &recorder)
{
	recorder.log(t, getName(), source->getVoltageAtTime(t), getUnits());
}

const string 
Project2::Sensor::getName() const
{
	return name;
}

const Project2::Signal& 
Project2::Sensor::getSource() const
{
	return *source;
}