#ifndef PROJECT2_SINESIGNAL_H
#define PROJECT2_SINESIGNAL_H



namespace Project2
{
	class SineSignal:public PeriodicSignal
	{
	public:
		SineSignal(double voltageOffset, Time &timeOffset,
			double minVoltage, double maxVoltage, Time &period);
		virtual double getVoltageAtTime(Time &t) const;
	};
}

#endif