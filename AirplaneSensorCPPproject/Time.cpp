


#include "Time.h"


Project2::Time::Time(unsigned hours, unsigned minutes, unsigned seconds) 
{
	this->seconds = hours*secondsInOneHour + minutes*secondsInOneMinute + seconds;
}

unsigned
Project2::Time::getTotalTimeAsSeconds() const
{
	return this->seconds;
}


Project2::Time
Project2::operator+=(Time& lhs, const Time& rhs)
{
	/*Time tempTime(unsigned hours, unsigned minutes, unsigned seconds);
	unsigned lhsHours = lhs.seconds / Time::secondsInOneHour;
	unsigned lhsMinutes = (lhs.seconds / 60) % 60;
	unsigned lhsSeconds = (lhs.seconds / 60 / 60) % 60;
	unsigned rhsHours = rhs.seconds / Time::secondsInOneHour;
	unsigned rhsMinutes = (rhs.seconds / 60) % 60;
	unsigned rhsSeconds = (rhs.seconds / 60 / 60) % 60;
	return tempTime(lhsHours + rhsHours, lhsMinutes + rhsMinutes, lhsSeconds + rhsSeconds);*/
	lhs.seconds = lhs.seconds + rhs.seconds;
	return lhs;
}

ostream&
Project2::operator<<(ostream& os, const Time& rhs)
{
	unsigned rhsHours = rhs.seconds / Time::secondsInOneHour;
	unsigned rhsMinutes = (rhs.seconds - rhsHours*Time::secondsInOneHour) / 60;
	unsigned rhsSeconds = rhs.seconds - rhsHours*Time::secondsInOneHour - rhsMinutes*Time::secondsInOneMinute;
	os << rhsHours << "h:" << rhsMinutes << "m:" << rhsSeconds << "s";
	return os;
}