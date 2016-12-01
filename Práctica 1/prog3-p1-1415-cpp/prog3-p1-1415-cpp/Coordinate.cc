#include "Coordinate.h"
#include <sstream>

int Coordinate::COORDINATE_COUNT = 0;

Coordinate::Coordinate(int row, int column) {
	this->row=row;
	this->column=column;
	COORDINATE_COUNT++;
}

Coordinate::Coordinate(const Coordinate& c) {
	this->row=c.row;
	this->column = c.column;
	COORDINATE_COUNT++;
}

Coordinate::~Coordinate() {
}

bool
Coordinate::operator==(const Coordinate& c) const {
   return (row==c.row && column==c.column);
}

string
Coordinate::to_string() const {
	stringstream concatenation;
	concatenation << "[" << row << "," << column << "]";
	return concatenation.str();
}

Coordinate
Coordinate::operator+(const Coordinate& c) const {
	return Coordinate(row+c.row, column+c.column);
}

