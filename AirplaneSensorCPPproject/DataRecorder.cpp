#include <iomanip>
#include "DataRecorder.h"


Project2::DataRecorder::DataRecorder(ostream &outPut) :out(outPut){}

void
Project2::DataRecorder::log(Time timestamp, string sensorName,
double sensorVoltage, string sensorUnits)
{
	out << timestamp << " - " << sensorName << " - " << std::fixed << std::setprecision(2)
		<< sensorVoltage << " " << sensorUnits << "\n";
}