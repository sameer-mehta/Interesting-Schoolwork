//
//  path.c
//  
//
//  Created by Sameer Mehta on 2015-10-05.
//  

#include "path.h"
#include <stdio.h>
#include <stdlib.h>

//Initializes the Solution Path
void PathInit(Path *P, int size)
{
    P->pathSize = size;             //sets the Solution Path size to the size the user inputs
    P->maxSize = -1;                //intializes the top variable for the array Solution
    P->actualPath = (int *)malloc(sizeof(int)* size);   //allocates dynamic memory for the Solution Path
}//using DMA

//Adds the 'Position' variable to Solution Path using this function
int PathAddEntry(Path *P, int entry )
{
    P->maxSize++;                   //increments the index of the Solution array
    *(P->actualPath + P->maxSize) = entry;  //sets the value in the index incremented to above to the 'entry'
    
    return 0;
}

//Removes the last value for the Solution array
int PathRemoveEntry(Path *P )
{
    P->maxSize--;       //decrements the index value, therefore deleting the value in the index
    
    return 0;}

//Simply prints the path
void PathPrint(Path *P)
{
    int i;
    //Goes through the Solution Array until i equals the size the user first entered
    for(i =0; i <= P->maxSize; i++)
    {
        printf("%d ", P->actualPath[i]);
    }
    printf("\n");
}