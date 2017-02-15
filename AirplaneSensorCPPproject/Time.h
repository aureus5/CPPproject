
#ifndef PROJECT2_TIME_H
#define PROJECT2_TIME_H

#include<iostream>
using std::ostream;

namespace Project2
{
	class Time
	{
		friend Time operator+=(Time& lhs, const Time& rhs);
		friend ostream& operator<<(ostream& os, const Time& rhs);
	public:
		static const unsigned secondsInOneHour = 3600;
		static const unsigned secondsInOneMinute = 60;
		Time(unsigned hours, unsigned minutes, unsigned seconds);
		unsigned getTotalTimeAsSeconds() const;
	private:
		unsigned seconds;
	};

	Time operator+=(Time& lhs, const Time& rhs);
	ostream& operator<<(ostream& os, const Time& rhs);
	
}

#endif