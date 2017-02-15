#include "MonitoringSystem.h"

Project2::MonitoringSystem::MonitoringSystem(ostream &out) :recorder(out) {}

void
Project2::MonitoringSystem::addSensor(Sensor *s)
{
	sensors.push_back(s);
}

void
Project2::MonitoringSystem::takeReading(Time &t)
{
	for (size_t i = 0; i < sensors.size(); ++i)
		sensors[i]->takeReading(t, recorder);
}