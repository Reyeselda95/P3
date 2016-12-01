#ifndef _Coordinate__H
#define _Coordinate__H

#include <iostream>

using namespace std;

class Coordinate
{
	friend ostream& operator<<(ostream& , Coordinate&);
	// Counts the coordinates as they are created; it is not decremented when destroying objects
	static int COORDINATE_COUNT; 
	int row;
	int column;
	
public:
	Coordinate(int row, int column);
	Coordinate(const Coordinate&);
	~Coordinate();
	int getRow() const { return row; };
	int getColumn() const { return column; };
	bool operator==(const Coordinate&) const;
	string to_string() const;
	Coordinate operator+(const Coordinate& c) const;
	static int getCoordinateCount() { return COORDINATE_COUNT; }
};

#endif
