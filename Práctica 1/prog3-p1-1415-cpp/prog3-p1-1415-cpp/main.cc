#include <iostream>
using namespace std;
#include "Coordinate.h"
#include <vector>



int main(int argc, char * argv[]) {
	Coordinate *c0;
	Coordinate c1(0,0);
	Coordinate *c2 = new Coordinate(10,10);
	Coordinate c3(4,3);
	Coordinate *c4 = new Coordinate(5,15);
	Coordinate *c5 = new Coordinate(*c4);
	Coordinate c6(2,5);
	
	cout << c1.to_string() << endl;
	cout << endl;
	
	cout << c2->to_string() << endl;
	cout << endl;

	cout << c3.to_string() << endl;
	cout << endl;
	
	cout << c4->to_string() << endl;
	cout << endl;
	
	Coordinate sumada = c3+c6;
	cout << c3.to_string() << "+" + c6.to_string() << "=" + sumada.to_string()  << endl;
	cout << endl;
	
	Coordinate* v[5];
	for (int i=0; i<5; i++) {
		v[i]= new Coordinate(i,4-i);
	}
	for (int i=0; i<5; i++) {
		cout << v[i]->getRow() << "," << v[i]->getColumn() << endl;
	}

	vector<Coordinate*> v2;
	for (int i=0; i<8; i++) {
		v2.push_back(new Coordinate(i, i));
		cout << v2[i]->to_string() << endl;
	}

	cout << "Coordinate count: " << Coordinate::getCoordinateCount() << endl;

	delete c2;
	delete c4;	
	delete c5;	
	
	for (int i=0; i<8; i++) {
		delete v2[i];
	}
	
}
