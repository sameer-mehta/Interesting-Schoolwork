//
//  vector.h
//  
//
//  Created by Sameer Mehta on 2015-10-11.
//  
//


#include <stdio.h>

//Stores the size and path user enters in one header file
typedef struct {
    int *item;
    int size;
} Vector;

//Prototype function used in the the 'path.c' file
void VectorRead(Vector *V);
