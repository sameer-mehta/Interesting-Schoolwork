//  path.h
//  
//
//  Created by Sameer Mehta on 2015-10-05.
//  
//

#include <stdio.h>

//Struct, where the variables in the struct is used in the finding the Solution Path
typedef struct{
    int *actualPath;
    int pathSize;
    int maxSize;
} Path;

//Prototype functions to be usable in the 'path.c' file
void PathInit(Path *P, int size); //using DMA
int PathAddEntry(Path *P, int entry );
int PathRemoveEntry(Path *P );
void PathPrint(Path *P);
